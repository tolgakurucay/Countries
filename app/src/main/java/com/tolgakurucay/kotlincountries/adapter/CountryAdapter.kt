package com.tolgakurucay.kotlincountries.adapter

import android.content.SharedPreferences
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.tolgakurucay.kotlincountries.model.Country
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.tolgakurucay.kotlincountries.R
import com.tolgakurucay.kotlincountries.databinding.RecyclerViewRowBinding
import com.tolgakurucay.kotlincountries.util.downloadFromUrl
import com.tolgakurucay.kotlincountries.util.placeHolderProgressBar
import com.tolgakurucay.kotlincountries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.recycler_view_row.view.*

class CountryAdapter(val countryList:ArrayList<Country>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {




    class CountryViewHolder(val view: RecyclerViewRowBinding): RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater=LayoutInflater.from(parent.context)
       // val view=inflater.inflate(R.layout.recycler_view_row,parent,false)
        val view=DataBindingUtil.inflate<RecyclerViewRowBinding>(inflater,R.layout.recycler_view_row,parent,false)
        return CountryViewHolder(view)
    }


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.country=countryList[position]
        holder.view.listener=this
       /*holder.view.name.setText(countryList[position].countryName)
        holder.view.region.setText(countryList[position].countryRegion)
       // Glide.with(holder.view).load(countryList[position].imageUrl).into()
        holder.view.imageView.downloadFromUrl(countryList[position].imageUrl, placeHolderProgressBar(holder.view.context))

        holder.view.setOnClickListener {
            val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            action.setCountryUuid(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)

        }*/

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList: List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()//adapter yenilenir
    }

    override fun onCountryClicked(v: View) {
        val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment()
        action.setCountryUuid(v.countryUuid.text.toString().toInt())
        Navigation.findNavController(v).navigate(action)

    }


}