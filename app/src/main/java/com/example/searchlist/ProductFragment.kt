package com.example.searchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.searchlist.data.remote.ProductDataSource
import com.example.searchlist.data.repository.ProductRepositoryImp
import com.example.searchlist.data.service.RetrofitClient
import com.example.searchlist.databinding.FragmentProductBinding
import com.example.searchlist.ui.ProductAdapter
import com.example.searchlist.utils.Resource
import com.example.searchlist.viewModel.ProductViewModel
import com.example.searchlist.viewModel.ProductViewModelFactory

class ProductFragment : Fragment(R.layout.fragment_product) {
    private lateinit var binding: FragmentProductBinding

    private val viewModel by viewModels<ProductViewModel>{
        ProductViewModelFactory(
            ProductRepositoryImp(
            ProductDataSource(RetrofitClient.webservice)
        )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)


        var request = "celular"
        viewModel.getProduct(request).observe(viewLifecycleOwner){ list ->
            when(list){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvList.adapter = ProductAdapter(list.data.records)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                }
            }

        }

    }
}