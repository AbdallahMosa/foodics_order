package local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import models.Category
import models.Product

@Database(
    entities = [Category::class, Product::class],
    version = DATABASE_VERSION_ONE,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}

private const val DATABASE_VERSION_ONE = 1