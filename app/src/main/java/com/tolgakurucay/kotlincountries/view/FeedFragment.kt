package com.tolgakurucay.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolgakurucay.kotlincountries.R
import com.tolgakurucay.kotlincountries.adapter.CountryAdapter
import com.tolgakurucay.kotlincountries.model.Country

import com.tolgakurucay.kotlincountries.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {
    private lateinit var  viewModel: FeedViewModel
    private val countryAdapter=CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //RecyclerView'i adapter'a bağlıyoruz
        countryList.layoutManager=LinearLayoutManager(context)
        countryList.adapter=countryAdapter

        //FeedViewModel'imizi initialize ediyoruz
        viewModel= ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()



        //Yukarı doğru çekip bıraktığımız zaman apiden verileri çekerek recyclerView'i doldur
        swipeRefreshLayout.setOnRefreshListener {
            countryList.visibility=View.GONE
            countryError.visibility=View.GONE
            countryLoading.visibility=View.VISIBLE
            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing=false
        }

        //canlı verileri gözlemle
        observeLiveData()




    }

    //viewModel içerisindeki mutableLiveData verilerimiz varsa şunu yap yoksa şunu yap
    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries->
            countries?.let {
                //country liste verimiz varsa recyclerView'i görünür yap ve adapter'i güncelle
                countryList.visibility=View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it){
                    countryError.visibility=View.VISIBLE
                }
                else
                {
                    countryError.visibility=View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if(it){
                    countryLoading.visibility=View.VISIBLE

                }
                else{
                    countryLoading.visibility=View.GONE
                }
            }

        })


    }
}