package com.liveloc.model.group

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

    @Query("DELETE FROM `group`")
    fun deleteAll()

    @Query("SELECT * FROM `group`")
    fun getAll() : LiveData<List<Group>>
}