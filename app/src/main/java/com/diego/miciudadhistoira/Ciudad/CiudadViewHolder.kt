package com.example.miciudad.Ciudad

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diego.miciudadhistoira.MapActivity
import com.diego.miciudadhistoira.R
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton


class CiudadViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(
    R.layout.item_lugar, parent, false)) {
    private var imgCiudad : ImageView? = null
    private var nombreCiudad: TextView? = null
    private var descripcionCiudad: TextView? = null
    private var btnUbicacion: MaterialButton? = null

    init {
        imgCiudad = itemView.findViewById(R.id.ImgLugar)
        nombreCiudad = itemView.findViewById(R.id.txtNombre)
        descripcionCiudad = itemView.findViewById(R.id.txtDescripcion)
        btnUbicacion = itemView.findViewById(R.id.btnUbicacion)
    }

    fun data(ciudad: Ciudad){
        nombreCiudad?.text = ciudad.nombreCiudad
        descripcionCiudad?.text = ciudad.descripcionCiudad
        imgCiudad?.let {
            Glide.with(itemView.context)
                .load(ciudad.imagenCiudad)
                .placeholder(R.drawable.image)
                .into(it)
        }
        btnUbicacion?.setOnClickListener {
            val context = itemView.context
            val intent = Intent(context, MapActivity::class.java).apply {
                putExtra("LATITUDE", ciudad.ubicacionCiudad.latitude)
                putExtra("LONGITUDE", ciudad.ubicacionCiudad.longitude)
                putExtra("TITLE", ciudad.nombreCiudad)
                putExtra("DESCRIPTION", ciudad.descripcionCiudad)
                putExtra("IMAGE", ciudad.imagenCiudad)
            }
            context.startActivity(intent)
        }
    }




































}