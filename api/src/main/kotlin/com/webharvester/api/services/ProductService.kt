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

@Service
class ProductService @Autowired constructor(
    private val productRepository: ProductRepository,
    private  val productPriceDateRepository: ProductPriceDateRepository){

    fun getAllProductsWithPrices() : List<ProductPricesDTO>{
        return productRepository.findAll()
            .map {
                ProductPricesDTO(productPriceDateRepository
                    .findPricesByProductId(it.id))
            }
    }

    fun insertProductPrice(dto: InsertProductPriceDTO): ProductPriceDate {
        val optionalProduct = productRepository.findProductByPageUrl(dto.pageUrl)
        val product: Product = if (optionalProduct.isPresent) {
            optionalProduct.get()
        } else {
            productRepository.save(
                Product(
                    name = dto.name,
                    productType = dto.productType,
                    source = dto.source,
                    pageUrl = dto.pageUrl,
                    imageUrl = dto.imageUrl
                )
            )
        }
        return productPriceDateRepository.save(
            ProductPriceDate(
                price = dto.price,
                id = ProductPriceDateId(id = product.id, createdAtAsString = LocalDate.now().toString()),
                product = product
            )
        )
    }
}