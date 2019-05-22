package com.liveloc.view.Adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.liveloc.db.model.group.Group
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liveloc.R



// group can be zero - parent can be zero
class GroupListAdapter( context: Context, var groups : List<Group>?) : ArrayAdapter<Group>(context , 0 , groups){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItem: View? = convertView
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.group_list_element_view , parent, false)
        val group = groups?.get(position!!)
        val name = listItem!!.findViewById(R.id.textView_name) as TextView
        name.setText(group?.name)
        return listItem
    }
}