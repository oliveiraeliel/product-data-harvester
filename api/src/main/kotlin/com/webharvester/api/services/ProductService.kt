package com.webharvester.api.services

import com.webharvester.api.models.Product
import com.webharvester.api.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService @Autowired constructor(private val productRepository: ProductRepository){
    fun getAllProducts() : List<Product>{
        return productRepository.findAll();
    }
}