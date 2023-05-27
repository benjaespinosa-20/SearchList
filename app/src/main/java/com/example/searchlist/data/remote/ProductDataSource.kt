package com.example.searchlist.data.remote

import com.example.searchlist.data.model.PlpResults
import com.example.searchlist.data.service.WebService

class ProductDataSource (private val webService: WebService) {
    suspend fun getProduct(originalSearchTerm: String): PlpResults = webService.getProduct(originalSearchTerm)
}