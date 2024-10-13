package com.example.miciudad.Ciudad

import com.google.firebase.firestore.GeoPoint

class Ciudad (
    val nombreCiudad: String,
    val descripcionCiudad: String,
    val imagenCiudad: String,
    val ubicacionCiudad: GeoPoint
)