package com.example.miciudad.Ciudad

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

class CiudadViewModel: ViewModel() {

    private lateinit var firestore: FirebaseFirestore
    val ciudadListMutable = MutableLiveData<List<Ciudad>>()
    var ciudadList = arrayListOf<Ciudad>()

    fun getCiudad() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("Sitios")
            .get()
            .addOnSuccessListener { result ->
                val ciudadList = mutableListOf<Ciudad>()
                for (document in result) {
                    val id = document.id
                    val data = document.data

                    // Agregar log para depuración
                    Log.d("CiudadViewModel", "Datos del documento $id: $data")

                    // Usar el operador seguro y proporcionar valores por defecto
                    val nombreCiudad = data["nombreCiudad"] as String
                    val descripcionCiudad = data["descripcionCiudad"] as String
                    val imagenCiudad = data["imagenCiudad"] as String
                    val ubicacionCiudad = data["ubicacionCiudad"] as GeoPoint

                    // Crear un objeto Ciudad
                    val ciudad = Ciudad(nombreCiudad, descripcionCiudad, imagenCiudad, ubicacionCiudad)
                    ciudadList.add(ciudad)
                }
                // Actualizar la lista mutable
                ciudadListMutable.value = ciudadList
            }
            .addOnFailureListener { exception ->
                Log.e("CiudadViewModel", "Error al obtener datos: ${exception.message}", exception)
                ciudadListMutable.value = emptyList()
            }
    }

}