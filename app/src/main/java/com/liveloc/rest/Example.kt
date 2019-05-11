package com.liveloc.rest

import com.google.gson.annotations.SerializedName


class Example {
    @SerializedName("name")
    var name : String = ""

    @SerializedName("price")
    var price : Double = 0.0

    @SerializedName("photo_url")
    var photo_url : String = ""

    @SerializedName("category_url")
    var category_url : String = ""

    @SerializedName("vendor_url")
    var vendor_url : String = ""
}