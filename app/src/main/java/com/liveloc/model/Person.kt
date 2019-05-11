package com.liveloc.model

class Person {
    var name : String = ""
    var location : Location = Location()


    companion object {
        const val MYNAME = "ME"
        val NOT_READY = Person()
    }
    constructor(){}
    constructor(name : String , location: Location){
        this.name = name
        this.location = location
    }

    override fun equals(other: Any?): Boolean {
        if ( other !is Person) return false
        else{
            var otherPerson = other as Person
            return name.equals(otherPerson.name) && location.equals(otherPerson.location)
        }
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + location.hashCode()
        return result
    }

}