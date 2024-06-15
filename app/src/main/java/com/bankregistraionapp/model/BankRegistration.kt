package com.bankregistraionapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bank_registration")
data class BankRegistration(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val panNumber: String,
    val birthDate: String,
    val name: String

)