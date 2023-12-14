package com.webharvester.api.models

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

@Embeddable
data class  ProductPriceDateId (
    @Column(name = "id")
    val id: UUID,

    @Column(name = "created_at")
    private var createdAtAsString: String
) : Serializable {
    companion object {
        private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }

    var createdAt: LocalDate
        get() = LocalDate.parse(createdAtAsString, dateFormatter)
        set(value) {
            createdAtAsString = value.format(dateFormatter)
        }
}
