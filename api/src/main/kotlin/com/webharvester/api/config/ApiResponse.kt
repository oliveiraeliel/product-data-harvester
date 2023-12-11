package com.webharvester.api.config

data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val errors: List<String>? = null
)
