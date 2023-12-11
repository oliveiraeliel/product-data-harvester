package com.webharvester.api.controllers

import com.webharvester.api.config.ApiResponse
import com.webharvester.api.dto.InsertProductPriceDTO
import com.webharvester.api.models.Product
import com.webharvester.api.models.ProductPriceDate
import com.webharvester.api.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/products")
class ProductController @Autowired constructor(private val productService: ProductService){

    @GetMapping
    fun getAll(): ResponseEntity<List<Product>> {
        val products = productService.getAllProducts()
        return ResponseEntity(products, HttpStatus.OK)
    }

    @GetMapping("/a")
    fun getAll2(): ResponseEntity<List<ProductPriceDate>> {
        val products = productService.getAllPrices()
        return ResponseEntity(products, HttpStatus.OK)
    }

    @PostMapping
    fun insertProductPrice(@Valid @RequestBody insertProductPriceDTO: InsertProductPriceDTO,
                           bindingResult: BindingResult):
            ResponseEntity<ApiResponse<ProductPriceDate>>{
        val response: ApiResponse<ProductPriceDate>
        println(bindingResult.hasErrors())
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
