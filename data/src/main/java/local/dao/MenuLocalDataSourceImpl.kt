package local.dao

import models.Category
import models.Product

class MenuLocalDataSourceImpl(
    private val menuDao: MenuDao,

    ) : MenuLocalDataSource {

    override suspend fun getCategories(): List<Category> {
        return menuDao.getCategories()
    }


    override suspend fun saveCategories(categories: List<Category>) {
        menuDao.insertCategories(categories)
    }

    override suspend fun getProducts(): List<Product> {
        return menuDao.getProducts()
    }

    override suspend fun saveProducts(products: List<Product>) {
        menuDao.insertProducts(products)
    }


    override suspend fun clearAllData() {
        menuDao.clearCategories()
        menuDao.clearProducts()
    }
}