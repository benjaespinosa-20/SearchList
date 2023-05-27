package com.example.searchlist.data.repository

import com.example.searchlist.data.model.PlpResults
import com.example.searchlist.data.remote.ProductDataSource

class ProductRepositoryImp(private val dataSource: ProductDataSource): ProductRepository {
    override suspend fun getProduct(originalSearchTerm: String): PlpResults = dataSource.getProduct(originalSearchTerm)
}