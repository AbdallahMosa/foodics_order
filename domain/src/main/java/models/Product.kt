package models

import kotlinx.serialization.Serializable


@Serializable
data class Product(
    val category: Category,
    val description: String,
    val id: String,
    val image: String,
    val name: String,
    val price: Double
)