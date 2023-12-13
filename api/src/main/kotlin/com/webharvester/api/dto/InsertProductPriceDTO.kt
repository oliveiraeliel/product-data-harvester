package com.webharvester.api.dto

import jakarta.validation.constraints.*

data class InsertProductPriceDTO(
    @NotBlank(message = "Name cannot be blank")
    val name: String,

    @NotBlank(message = "Page url cannot be blank")
    val pageUrl: String,

    @NotBlank(message = "Source cannot be blank")
    val source: String,

    val imageUrl: String,

    @PositiveOrZero(message = "Price must be a positive number")
    val price: Float,

    @NotBlank(message = "Page url cannot be blank")
    val productType: String
)