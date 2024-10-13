package com.example.miciudad.Ciudad

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

class CiudadViewModel: ViewModel() {

    private lateinit var firestore: FirebaseFirestore
    val ciudadListMutable = MutableLiveData<List<Ciudad>>()
    var ciudadList = arrayListOf<Ciudad>()

    fun getCiudad (){
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("Sitios")
            .get()
            .addOnSuccessListener { result ->
                val ciudadLugar = mutableListOf<Ciudad>()
                for(document in result) {
                    val id = document.id
                    val data = document.data

                    val nombreCiudad = data["nombre"] as String
                    val descripcionCiudad = data["descripcion"] as String
                    val imagenCiudad = data["imagen"] as String
                    val ubicacionCiudad = data["ubicacion"] as GeoPoint

                    val ciudad = Ciudad(nombreCiudad,descripcionCiudad,imagenCiudad,ubicacionCiudad)
                    ciudadList.add(ciudad)
                }
                ciudadListMutable.value = ciudadList
            }
            .addOnFailureListener {
                ciudadListMutable.value = emptyList()
            }
    }
}