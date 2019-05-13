package com.liveloc.view.mapview

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.liveloc.model.Location
import com.liveloc.model.Person


class GoogleMaps(mapFragment: SupportMapFragment) : OnMapReadyCallback, MapViewInterface {

    lateinit var mMap: GoogleMap

    var myself : Person = Person.NOT_READY
    var myself_marker : Marker? = null
    var persons = listOf<Person>()


    init {
        // Initialize Google Maps
        //val mapFragment = liv.supportFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney, Australia, and move the camera.
       // val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
    }

    override fun drawMyself(location: Location) {
        var myGoogleMapsLatLng = location.getGoogleLatLng()

        if ( myself.equals(Person.NOT_READY)){
            var myMarker = mMap.addMarker(
                MarkerOptions().position(myGoogleMapsLatLng)
                    .title(Person.MYNAME)
            )
            myself_marker = myMarker
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myGoogleMapsLatLng,18f));
        }else{
            myself_marker!!.position = myGoogleMapsLatLng
        }
        this.myself = Person(Person.MYNAME , location)
    }
    override fun isMapReady(): Boolean  = mMap != null
}