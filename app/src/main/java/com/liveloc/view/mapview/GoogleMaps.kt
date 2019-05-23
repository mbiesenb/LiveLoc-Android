package com.liveloc.view.mapview

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.liveloc.db.model.Location
import com.liveloc.db.model.Person
import android.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory




class GoogleMaps(mapFragment: SupportMapFragment) : OnMapReadyCallback, MapViewInterface {

    lateinit var mMap: GoogleMap

    var myself : Person = Person.NOT_READY
    var myself_marker : Marker? = null
    var person_markers : HashMap<String , Marker> = hashMapOf()
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
        this.myself = Person(Person.MYNAME, location)
    }
    override fun isMapReady(): Boolean  = mMap != null

    override fun drawPerson(persons: List<Person>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        for ( person in persons){
            var marker = person_markers[person.server_person_id]
            marker!!.position = person.location.getGoogleLatLng()
        }
    }

    fun getMarker(person : Person){
        if (person_markers.containsKey(person.server_person_id)){
            var marker = person_markers[person.server_person_id]!!
            marker.position = person.location.getGoogleLatLng()
        }else{
            var newMarker = mMap.addMarker(
                MarkerOptions()
                    .position(person.location.getGoogleLatLng())
                    .title("Sydney")
                    .snippet("Population: 4,627,300")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_delete))
            );
            person_markers.put(person.server_person_id,newMarker)
        }
    }
}