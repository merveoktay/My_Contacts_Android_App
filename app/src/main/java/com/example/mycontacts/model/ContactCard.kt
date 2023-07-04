package com.example.mycontacts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactCard")
data class ContactCard(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "surname")
    val surname:String,

    @ColumnInfo(name = "phoneNumber")
    val phoneNumber:String,

    @ColumnInfo(name = "address")
    val address:String?,

    @ColumnInfo(name = "mail")
    val mail:String?,

    @ColumnInfo(name = "cardGroup")
    val group:String
)
