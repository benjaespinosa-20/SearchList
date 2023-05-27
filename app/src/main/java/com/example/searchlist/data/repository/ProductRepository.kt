package com.example.searchlist.data.repository

import com.example.searchlist.data.model.PlpResults

interface ProductRepository {
    suspend fun getProduct(originalSearchTerm: String): PlpResults
}