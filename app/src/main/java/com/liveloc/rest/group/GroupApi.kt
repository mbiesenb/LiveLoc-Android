package com.liveloc.rest.group

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class GroupApi {
    fun createGroup(groupDTO: GroupDTO, fResponse: (GroupDTO) -> Unit) {
        val apiService = GroupApiService.create()
        val call = apiService.createGroup(groupDTO)
        call.enqueue(object : Callback<GroupDTO> {
            override fun onResponse(call: Call<GroupDTO>, response: retrofit2.Response<GroupDTO>?) {
                if (response != null) {
                    var groupDTO: GroupDTO? = response.body()
                    if ( groupDTO == null){
                        Log.d("REST", "Error while parsing")
                    }else{
                        fResponse(groupDTO)
                    }
                }
            }
            override fun onFailure(call: Call<GroupDTO>, t: Throwable) {
                var groupDTO = GroupDTO("Family & Friends","" )
                fResponse(groupDTO)
            }
        })
    }

    fun getGroup(groupId: String, fResponse: (GroupDTO) -> Unit) {
        val apiService = GroupApiService.create()
        val call = apiService.getGroup()
        call.enqueue(object : Callback<GroupDTO> {
            override fun onResponse(call: Call<GroupDTO>, response: retrofit2.Response<GroupDTO>?) {
                if (response != null) {
                    var groupDTO: GroupDTO? = response.body()
                    if ( groupDTO == null){
                        Log.d("REST", "Error while parsing")
                    }else{
                        fResponse(groupDTO)
                    }
                }
            }
            override fun onFailure(call: Call<GroupDTO>, t: Throwable) {
                var groupDTO = GroupDTO("Family & Friends","" )
                fResponse(groupDTO)
            }
        })
    }


}