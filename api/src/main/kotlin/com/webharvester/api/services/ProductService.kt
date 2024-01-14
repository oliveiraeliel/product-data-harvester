package com.webharvester.api.services

import com.webharvester.api.dto.InsertProductPriceDTO
import com.webharvester.api.dto.PriceDate
import com.webharvester.api.dto.ProductPricesDTO
import com.webharvester.api.models.Product
import com.webharvester.api.models.ProductPriceDate
import com.webharvester.api.models.ProductPriceDateId
import com.webharvester.api.repositories.ProductPriceDateRepository
import com.webharvester.api.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.Date
import java.util.UUID

@Service
class ProductService @Autowired constructor(
    private val productRepository: ProductRepository,
    private  val productPriceDateRepository: ProductPriceDateRepository){

    fun getAllProductsWithPrices() : List<ProductPricesDTO>{
        return productRepository.findAll()
            .map { product ->
                val productPriceDates = productPriceDateRepository.findPricesByProductId(product.id)
                if (productPriceDates.isNotEmpty()) {
                    ProductPricesDTO(product, productPriceDates.map { PriceDate(it) })
                } else {
                    ProductPricesDTO(product, emptyList())
                }
            }
    }

    fun insertProductPrice(dto: InsertProductPriceDTO): ProductPriceDate {
        val pageUrl = pageUrlNormalizer(dto.pageUrl)
        val optionalProduct = productRepository.findProductByPageUrl(pageUrl)
        val id: UUID
        val createdAt: Date
        if (optionalProduct.isPresent){
            id = optionalProduct.get().id
            createdAt = optionalProduct.get().createdAt
        } else {
            createdAt = Date()
            id = generateUniqueProductId()
        }
        val product = productRepository.save(
            Product(
                id = id,
                source = dto.source,
                name = dto.name,
                productType = dto.productType,
                imageUrl = dto.imageUrl,
                pageUrl = pageUrl,
                createdAt = createdAt,
                brand = dto.brand,
                rating = dto.rating,
                reviewCount = dto.reviewCount
            )
        )
        return productPriceDateRepository.save(
                ProductPriceDate(
                    price = dto.price,
                    id = ProductPriceDateId(id = product.id, createdAtAsString = LocalDate.now().toString()),
                    product = product
                )
            )
    }

    private fun generateUniqueProductId(): UUID {
        var id: UUID
        do id = UUID.randomUUID()
        while (productRepository.findById(id).isPresent)
        return id
    }

    private fun pageUrlNormalizer(pageUrl: String) : String {
        val page = "$pageUrl/"
            .replace("https://", "")
            .replace("www.", "")
            .replace(Regex("/+"), "/")
        return "https://$page"
    }
}