package com.liveloc.view.mapview

import com.liveloc.db.model.Location
import com.liveloc.db.model.Person

/**
 *  Defining all the Maps Functions in Interface,
 *  so it is easier the replace GoogleMaps through other Map Provider later
 */
interface MapViewInterface {
    fun drawMyself(location: Location)
    fun drawPerson(persons : List<Person>)
    fun isMapReady() : Boolean
}