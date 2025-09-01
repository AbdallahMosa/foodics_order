package models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "products")
@Serializable
data class Product(
    val category: Category,
    val description: String,
    @PrimaryKey
    val id: String,
    val image: String,
    val name: String,
    val price: Double,
    @ColumnInfo(name = "category_id")
    val categoryId: String = category.id
)