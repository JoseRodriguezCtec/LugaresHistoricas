package com.diego.miciudadhistoira

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map:GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val title = intent.getStringExtra("TITLE")
        val description = intent.getStringExtra("DESCRIPTION")
        val image = intent.getStringExtra("IMAGE")

        findViewById<TextView>(R.id.txtNombre).text = title
        findViewById<TextView>(R.id.txtDescripcion).text = description
        findViewById<ImageView>(R.id.ImgLugar).let {
            Glide.with(this)
                .load(image)
                .placeholder(R.drawable.image)
                .into(it)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val lat = intent.getDoubleExtra("LATITUDE", 0.0)
        val lon = intent.getDoubleExtra("LONGITUDE", 0.0)
        val title = intent.getStringExtra("TITLE")

        val location = LatLng(lat, lon)
        map.addMarker(MarkerOptions().position(location).title(title))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))

    }
}