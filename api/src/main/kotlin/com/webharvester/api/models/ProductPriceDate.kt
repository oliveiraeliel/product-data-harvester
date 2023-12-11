package com.webharvester.api.models

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table


@Entity
@Table(name = "ProductPrice")
data class ProductPriceDate(
    @EmbeddedId
    val id: ProductPriceDateId,

    @Column(name = "price")
    val price: Float,

    @ManyToOne
    @MapsId("id")
    val product: Product
)
