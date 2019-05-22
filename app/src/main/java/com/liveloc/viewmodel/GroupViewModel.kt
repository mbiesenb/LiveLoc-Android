package com.liveloc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.liveloc.db.model.group.Group
import com.liveloc.repositories.GroupRepository
import com.liveloc.rest.group.GroupApi
import com.liveloc.rest.group.GroupDTO

class GroupViewModel(application: Application) : AndroidViewModel(application) {

    var groupRepository : GroupRepository
    var allGroups : LiveData<List<Group>>

    init {
        // init local db
        groupRepository = GroupRepository(application)
        allGroups = groupRepository.getAll()

    }
    fun insert(group: Group) = groupRepository.insert(group)
    fun update(group: Group) = groupRepository.update(group)
    fun delete(group: Group) = groupRepository.delete(group)
    fun deleteAll() = groupRepository.deleteAll()
    fun getAll() = groupRepository.getAll()

    fun create(name: String , password : String) = groupRepository.createGroup(name , password)
    fun add(id : String ) = groupRepository.addGroup(id)

}