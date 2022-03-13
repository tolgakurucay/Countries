package com.tolgakurucay.kotlincountries.view

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolgakurucay.kotlincountries.R
import com.tolgakurucay.kotlincountries.databinding.FragmentCountryBinding
import com.tolgakurucay.kotlincountries.util.downloadFromUrl
import com.tolgakurucay.kotlincountries.util.placeHolderProgressBar
import com.tolgakurucay.kotlincountries.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.recycler_view_row.*


class CountryFragment : Fragment() {
    private var countryUuid=0
    private lateinit var viewModel:CountryViewModel
    private lateinit var dataBinding:FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)

        //return inflater.inflate(R.layout.fragment_country, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //önceki fragmentten gelen veri varsa bunu countryUuid içerisine at
        arguments?.let {
            countryUuid=CountryFragmentArgs.fromBundle(it).countryUuid

        }


        //viewModel'i initialize et
        viewModel=ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        //canlı verileri gözlemle
        observeLiveData()



    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country->
            country?.let {

                dataBinding.selectedCountry=it

                /*countryName.setText(country.countryName)
                countryCapital.setText(country.countryCurrency)
                countryLanguage.setText(country.countryLanguage)
                countryCurrency.setText(country.countryCurrency)
                countryRegion.setText(country.countryRegion)



                context?.let {
                   countryImage.downloadFromUrl(country.imageUrl, placeHolderProgressBar(it.applicationContext))
                }
                    */




            }
        })

    }


}