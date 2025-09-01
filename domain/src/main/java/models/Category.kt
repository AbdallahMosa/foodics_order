package models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "categories")
@Serializable
data class Category(
    @PrimaryKey
    val id: String,
    val name: String
)