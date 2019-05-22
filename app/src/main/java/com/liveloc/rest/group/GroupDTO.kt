package com.liveloc.rest.group

import com.google.gson.annotations.SerializedName

class GroupDTO {

    constructor(name : String , password : String) {
        this.name = name
        this.password = password
    }

    @SerializedName("group_id")
    var groupID : String = ""

    @SerializedName("name")
    var name : String = ""

    @SerializedName("password")
    var password : String = ""

}