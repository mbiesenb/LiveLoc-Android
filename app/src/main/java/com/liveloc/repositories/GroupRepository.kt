package com.liveloc.repositories

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.liveloc.model.group.Group
import com.liveloc.model.group.GroupDAO
import com.liveloc.model.group.GroupDB

class GroupRepository {
    var groupDAO : GroupDAO
    var allGroups : LiveData<List<Group>>

    constructor( application: Application){
        var database =  GroupDB.getInstance(application)
        groupDAO = database.groupDAO()
        allGroups = groupDAO.getAll()
    }

    companion object {
        class InsertGroupAsync(val groupDAO: GroupDAO) : AsyncTask<Group , Void , Void>(){
            override fun doInBackground(vararg params: Group?): Void? {
                groupDAO.insert(params.firstOrNull()!!)
                return null
            }

        }
        class UpdateGroupAsync(val groupDAO: GroupDAO) : AsyncTask<Group , Void , Void>(){
            override fun doInBackground(vararg params: Group?): Void? {
                groupDAO.update(params.firstOrNull()!!)
                return null
            }
        }
        class DeleteGroupAsync(val groupDAO: GroupDAO) : AsyncTask<Group , Void , Void>(){
            override fun doInBackground(vararg params: Group?): Void? {
                groupDAO.delete(params.firstOrNull()!!)
                return null
            }

        }
        class DeleteAllGroupAsync(val groupDAO: GroupDAO) : AsyncTask<Group , Void , Void>(){
            override fun doInBackground(vararg params: Group?): Void? {
                groupDAO.deleteAll()
                return null
            }

        }
    }

    fun insert(group: Group) = InsertGroupAsync(groupDAO).execute(group)
    fun update(group: Group) = UpdateGroupAsync(groupDAO).execute(group)
    fun delete(group: Group) = DeleteGroupAsync(groupDAO).execute(group)
    fun deleteAll() = DeleteAllGroupAsync(groupDAO).execute()
    fun getAll() = allGroups


}