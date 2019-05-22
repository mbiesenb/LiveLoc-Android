package com.liveloc.db.model.group

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.*



@Database(entities = arrayOf(Group::class) , version = 2)
abstract class GroupDB : RoomDatabase() {

    abstract fun groupDAO() : GroupDAO

    companion object {
        const val  GROUP_DB_NAME : String = "group_database"

        var instance : GroupDB? = null
        fun getInstance ( context: Context ) : GroupDB {
            synchronized(this){
               if ( instance == null ){
                   try {
                       instance = Room.databaseBuilder(
                           context.getApplicationContext(),
                           GroupDB::class.java, GROUP_DB_NAME
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
                DeleteDbAsync(instance!!)
                    .execute()
            }
        }

        /**
         *  Insert Test Data
         */
        /*class PopulateDbAsync(val groupDB: GroupDB) : AsyncTask <Void, Void, Void>() {
            val groupDAO = groupDB.groupDAO()
            override fun doInBackground(vararg params: Void?): Void? {
                groupDAO.insert(Group(UUID.randomUUID().toString() , "Family", "TopSecretPassword"))
                groupDAO.insert(Group(UUID.randomUUID().toString() , "Friends", "TopSecretPassword"))
                groupDAO.insert(Group(UUID.randomUUID().toString() , "Tourist Group", "TopSecretPassword"))
                return null
            }

        }*/
        class DeleteDbAsync(val groupDB: GroupDB) : AsyncTask <Void, Void, Void>() {
            val groupDAO = groupDB.groupDAO()
            override fun doInBackground(vararg params: Void?): Void? {
                //groupDAO.deleteAll()
                return null
            }

        }
    }

}