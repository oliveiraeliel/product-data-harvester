package com.webharvester.api.models

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "Products")
data class Product(
    @EmbeddedId
    val productId: ProductId,

    @Column(name = "name", length = 150, nullable = false)
    val name: String,

    @Column(name = "page_url", length = 255, nullable = false)
    val pageUrl: String,

    @Column(name = "image_url", length = 255)
    val imageUrl: String?,

    @Column(name = "price", nullable = false)
    val price: Float,

    @Column(name = "product_type", length = 50, nullable = false)
    val productType: String,

//    @Column(name = "created_at", columnDefinition = "DATE DEFAULT (DATE('now'))")
//    @Temporal(TemporalType.DATE)
//    val createdAt: Date = Date(),

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    val updatedAt: Date = Date(),

//    @PrePersist
//    @PreUpdate
//    fun updateTimestamps() {
//        updatedAt = Date()
//    }
)