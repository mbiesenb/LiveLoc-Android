package com.liveloc.db.model

import android.location.Location
import com.google.android.gms.maps.model.LatLng

class Location {
    var longitude : Double = 0.0
    var latitude : Double = 0.0

    constructor(){}
    constructor(longitude : Double , latitude : Double){
        this.longitude = longitude
        this.latitude = latitude
    }
    constructor(location: Location){
        this.longitude = location.longitude
        this.latitude = location.latitude
    }

    fun getGoogleLatLng() : LatLng = LatLng(latitude , longitude)

    override fun toString(): String =  "longitude: ${longitude} , latitude: ${latitude}"
    override fun equals(other: Any?): Boolean {
        if ( other !is com.liveloc.db.model.Location)  return false
        else{
            var otherLocation = other as com.liveloc.db.model.Location
            return longitude == otherLocation.longitude && latitude == otherLocation.latitude
        }
    }
}