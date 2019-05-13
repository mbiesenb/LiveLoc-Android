package com.liveloc.location

import android.app.Activity
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.liveloc.view.mapview.MapViewInterface


class GpsLocation(val activity: Activity, val mapInterface: MapViewInterface) {
    private var locationManager: LocationManager? = null

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.d("LOCATION", "Lon ${location.longitude} ${location.latitude}")
            if ( mapInterface.isMapReady() ){
                mapInterface.drawMyself(location = com.liveloc.model.Location(location))
            }
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    init {
        // Create persistent LocationManager reference
        locationManager = activity.getSystemService(LOCATION_SERVICE) as LocationManager?;
        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
        } catch (ex: SecurityException) {
            Log.d("LOCATION", ex.toString());
        } catch (ex: Exception){
            Log.d("LOCATION" , ex.toString())
        }
    }



}