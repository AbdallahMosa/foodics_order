package local.dao

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import models.Category

class Converter {

    @TypeConverter
    fun fromCategory(category: Category): String {
        return "${category.id}|${category.name}"
    }

    @TypeConverter
    fun toCategory(categoryString: String): Category {
        val parts = categoryString.split("|")
        return Category(id = parts[0], name = parts[1])
    }
}