package com.liveloc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.liveloc.model.group.Group
import com.liveloc.repositories.GroupRepository

class GroupViewModel(application: Application) : AndroidViewModel(application) {

    var groupRepository : GroupRepository
    var allGroups : LiveData<List<Group>>

    init {
        groupRepository = GroupRepository(application)
        allGroups = groupRepository.getAll()
    }
    fun insert(group: Group) = groupRepository.insert(group)
    fun update(group: Group) = groupRepository.update(group)
    fun delete(group: Group) = groupRepository.delete(group)
    fun deleteAll() = groupRepository.deleteAll()
    fun getAll() = groupRepository.getAll()
}