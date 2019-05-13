package com.liveloc.model.group

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.*

@Database(entities = arrayOf(Group::class) , version = 1)
abstract class GroupDB : RoomDatabase() {

    abstract fun groupDAO() : GroupDAO

    companion object {
        var instance : GroupDB? = null

        fun getInstance ( context: Context ) : GroupDB{
            synchronized(this){
               if ( instance == null ){
                   try {
                       instance = Room.databaseBuilder(
                           context.getApplicationContext(),
                           GroupDB::class.java, "group_database"
                       )
                           .fallbackToDestructiveMigration()
                           .addCallback(roomCallback)
                           .build()
                   }catch (exception: Exception){
                       Log.d("GROUP" , exception.toString())
                   }

               }
                return instance!!
            }
        }

        var roomCallback = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsync(instance!!).execute()
            }
        }

        /**
         *  Insert Test Data
         */
        class PopulateDbAsync(val groupDB: GroupDB) : AsyncTask <Void, Void, Void>() {
            val groupDAO = groupDB.groupDAO()
            override fun doInBackground(vararg params: Void?): Void? {
                groupDAO.insert(Group(UUID.randomUUID().toString() , "TestGroup1", "TopSecretPassword"))
                groupDAO.insert(Group(UUID.randomUUID().toString() , "TestGroup2", "TopSecretPassword"))
                groupDAO.insert(Group(UUID.randomUUID().toString() , "TestGroup3", "TopSecretPassword"))
                return null
            }

        }

    }

}