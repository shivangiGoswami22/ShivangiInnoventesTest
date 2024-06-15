package com.bankregistraionapp.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bankregistraionapp.model.BankRegistration

@Dao
interface BankRegistrationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(registration: BankRegistration)

    @Query("SELECT * FROM bank_registration")
     fun getAllRegistrations(): List<BankRegistration>
}
