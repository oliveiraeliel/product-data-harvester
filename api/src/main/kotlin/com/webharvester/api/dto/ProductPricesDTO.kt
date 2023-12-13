package com.webharvester.api.dto

import com.webharvester.api.models.Product
import com.webharvester.api.models.ProductPriceDate
import java.time.LocalDate

data class ProductPricesDTO(
    val product: Product,
    val prices: List<PriceDate>
){
    constructor(productPriceDates: List<ProductPriceDate>):
            this(productPriceDates[0].product, productPriceDates.map { PriceDate(it) })
}

data class PriceDate(
    val price: Float,
    val date: LocalDate
) {
    constructor(productPriceDate: ProductPriceDate):
            this(productPriceDate.price, productPriceDate.id.createdAt)
}