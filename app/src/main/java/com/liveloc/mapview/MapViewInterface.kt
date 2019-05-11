package com.liveloc.mapview

import com.liveloc.model.Location

/**
 *  Defining all the Maps Functions in Interface,
 *  so it is easier the replace GoogleMaps through other Map Provider later
 */
interface MapViewInterface {
    fun drawMyself(location: Location)
    fun isMapReady() : Boolean
}