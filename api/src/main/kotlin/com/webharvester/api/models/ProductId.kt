package com.webharvester.api.models

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date


@Embeddable
data class  ProductId(
    @Column(name = "id", length = 20)
    val id: String,

    @Column(name = "created_at")
    private var createdAtAsString: String
) : Serializable {
    companion object {
        private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }

    // Getter for createdAt
//    @Temporal(TemporalType.DATE)
    var createdAt: LocalDate
        get() = LocalDate.parse(createdAtAsString, dateFormatter)
        set(value) {
            createdAtAsString = value.format(dateFormatter)
        }
}
