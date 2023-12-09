package com.webharvester.api.controllers

import com.webharvester.api.models.Product
import com.webharvester.api.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductController @Autowired constructor(private val productService: ProductService){

    @GetMapping
    fun getAll(): ResponseEntity<List<Product>> {
        val products = productService.getAllProducts()
        println(products)
        return ResponseEntity(products, HttpStatus.OK)
    }
}
