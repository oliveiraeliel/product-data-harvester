package com.webharvester.api.repositories

import com.webharvester.api.models.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface ProductRepository : JpaRepository<Product, UUID> {
    fun findProductByPageUrl(pageUrl: String): Optional<Product>;
}