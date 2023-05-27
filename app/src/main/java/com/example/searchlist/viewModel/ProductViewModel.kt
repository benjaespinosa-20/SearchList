package com.example.searchlist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.searchlist.data.repository.ProductRepository
import com.example.searchlist.utils.Resource
import kotlinx.coroutines.Dispatchers

class ProductViewModel (val repository: ProductRepository): ViewModel() {

    fun getProduct(originalSearchTerm: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try{
            emit(Resource.Success(repository.getProduct(originalSearchTerm)))
        } catch(e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

class ProductViewModelFactory(private val repo: ProductRepository): ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ProductRepository::class.java).newInstance(repo)
    }

}