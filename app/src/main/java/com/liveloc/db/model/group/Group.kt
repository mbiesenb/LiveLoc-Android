package com.liveloc.db.model.group

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.liveloc.rest.group.GroupDTO

@Entity(tableName = Group.GROUP_TABLE_NAME)
class Group {

    companion object {
        const val GROUP_TABLE_NAME = "group"
    }

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var clientGroupId : Int = 0

    @ColumnInfo(name = "groupId")
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
    constructor(groupDTO: GroupDTO){
        this.serverGroupId = groupDTO.groupID
        this.name = groupDTO.name
        this.password = ""
    }

}