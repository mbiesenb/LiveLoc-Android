package com.liveloc.db.model.group

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroupDAO {

    @Insert
    fun insert(group: Group)

    @Delete
    fun delete(group: Group)

    @Update
    fun update(group: Group)

    @Query("DELETE FROM `${Group.GROUP_TABLE_NAME}` WHERE 1 = 1 ")
    fun deleteAll()

    @Query("SELECT * FROM `${Group.GROUP_TABLE_NAME}`")
    fun getAll() : LiveData<List<Group>>
}