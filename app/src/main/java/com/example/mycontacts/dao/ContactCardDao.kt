package com.example.mycontacts.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mycontacts.model.ContactCard

@Dao
interface ContactCardDao {
    @Insert
    fun insert( contactCard: ContactCard ) : Long

    @Query("select * from contactCard")
     fun getAll() : List<ContactCard>

    @Query("select * from contactCard order by id desc;")
    fun getTopTen():List<ContactCard>

    @Query("select * from contactCard where name like :name ")
    fun searchName( name: String ) : List<ContactCard>

    @Query("select * from contactCard where  cardGroup like :group ")
    fun searchGroup(group:String) : List<ContactCard>

    @Query("select * from contactCard where id =:id")
    fun findById( id: Int ) : ContactCard

    @Delete
    fun delete(contactCard: ContactCard)

    @Update
    fun update(contactCard: ContactCard )


}