package com.webharvester.api.repositories

import com.webharvester.api.models.Product
import com.webharvester.api.models.ProductId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, ProductId> {

}