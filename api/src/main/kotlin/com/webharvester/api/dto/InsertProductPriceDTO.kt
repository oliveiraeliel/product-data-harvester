package com.webharvester.api.dto

import jakarta.validation.constraints.*

data class InsertProductPriceDTO(
    @field:NotBlank(message = "Name cannot be blank")
    val name: String,

    @field:NotBlank(message = "Page url cannot be blank")
    val pageUrl: String,

    @field:NotBlank(message = "Source cannot be blank")
    val source: String,

    val imageUrl: String,

    @field:PositiveOrZero(message = "Price must be a positive number")
    val price: Float,

    @field:NotBlank(message = "Page url cannot be blank")
    val productType: String
)