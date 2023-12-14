package com.webharvester.api.repositories

import com.webharvester.api.models.ProductPriceDate
import com.webharvester.api.models.ProductPriceDateId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface ProductPriceDateRepository: JpaRepository<ProductPriceDate, ProductPriceDateId> {
    @Query("SELECT PPD FROM ProductPriceDate PPD, Product P " +
            "WHERE P.id = :productId AND PPD.id.id = P.id")
    fun findPricesByProductId(@Param("productId") productId: UUID): List<ProductPriceDate>
}