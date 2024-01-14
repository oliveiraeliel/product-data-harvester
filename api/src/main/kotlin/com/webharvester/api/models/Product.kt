package com.webharvester.api.models

import jakarta.persistence.*
import java.util.Date
import java.util.UUID

@Entity
@Table(name = "Products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "source", length = 50)
    val source: String,

    @Column(name = "name", length = 150, nullable = false)
    val name: String,

    @Column(name = "page_url", length = 255, unique = true , nullable = false)
    val pageUrl: String,

    @Column(name = "image_url", length = 255)
    val imageUrl: String?,

    @Column(name = "product_type", length = 50, nullable = false)
    val productType: String,

    @Column(name = "rating")
    val rating: Float,

    @Column(name = "reviewCount")
    val reviewCount: Int,

    @Column(name = "brand")
    val brand: String,

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    val createdAt: Date = Date(),

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    val updatedAt: Date = Date(),
)