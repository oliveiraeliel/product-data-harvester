package com.webharvester.api.controllers

import com.webharvester.api.config.ApiResponse
import com.webharvester.api.dto.InsertProductPriceDTO
import com.webharvester.api.dto.ProductPricesDTO
import com.webharvester.api.models.ProductPriceDate
import com.webharvester.api.services.ProductService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductController @Autowired constructor(private val productService: ProductService){

    @GetMapping
    fun getAll(): ResponseEntity<List<ProductPricesDTO>> {
        val products = productService.getAllProductsWithPrices()
        return ResponseEntity(products, HttpStatus.OK)
    }

    @PostMapping
    fun insertProductPrice(@RequestBody @Valid insertProductPriceDTO: InsertProductPriceDTO,
                           bindingResult: BindingResult):
            ResponseEntity<ApiResponse<ProductPriceDate>>{
        val response: ApiResponse<ProductPriceDate>
        if (bindingResult.hasErrors()){
            val errors = bindingResult.fieldErrors.mapNotNull { it.defaultMessage } ?: emptyList()
            response = ApiResponse(success = false, errors = errors)
            return ResponseEntity.badRequest().body(response)
        }
        val prod = productService.insertProductPrice(insertProductPriceDTO)
        response = ApiResponse(success = true, data = prod)
        return ResponseEntity.ok(response)
    }
}
