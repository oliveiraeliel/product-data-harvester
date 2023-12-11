package com.webharvester.api.repositories

import com.webharvester.api.models.ProductPriceDate
import com.webharvester.api.models.ProductPriceDateId
import org.springframework.data.jpa.repository.JpaRepository

interface ProductPriceDateRepository: JpaRepository<ProductPriceDate, ProductPriceDateId> {

}