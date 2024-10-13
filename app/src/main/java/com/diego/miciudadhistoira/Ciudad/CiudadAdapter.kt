package com.example.miciudad.Ciudad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CiudadAdapter(): RecyclerView.Adapter<CiudadViewHolder>() {

    private var ciudadList = emptyList<Ciudad>()

    fun setCiudad(ciudad: List<Ciudad>){
        ciudadList = ciudad
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiudadViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        return CiudadViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = ciudadList.size

    override fun onBindViewHolder(holder: CiudadViewHolder, position: Int) {
        val ciudad : Ciudad = ciudadList[position]
        holder.data(ciudad)
    }
}