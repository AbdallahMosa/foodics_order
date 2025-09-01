package local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import models.Category
import models.Product

@Dao

interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(product: List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<Category>)


    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<Category>

    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<Product>

    @Query("DELETE FROM products")
    suspend fun clearProducts()

    @Query("DELETE FROM categories")
    suspend fun clearCategories()
}