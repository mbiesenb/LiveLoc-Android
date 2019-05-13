package com.liveloc.model.group

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group")
class Group {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var clientGroupId : Int = 0

    @ColumnInfo(name = "serverId")
    var serverGroupId : String = ""

    @ColumnInfo(name = "name")
    var name : String = ""

    @ColumnInfo(name = "password")
    var password : String = ""

    constructor(serverGroupId : String , name : String , password : String){
        this.serverGroupId = serverGroupId
        this.name = name
        this.password = password
    }

}