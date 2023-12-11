package com.webharvester.api.dto

import com.webharvester.api.models.Product
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

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