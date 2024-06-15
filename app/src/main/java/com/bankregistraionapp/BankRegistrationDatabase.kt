package com.bankregistraionapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bankregistraionapp.interfaces.BankRegistrationDao
import com.bankregistraionapp.model.BankRegistration

@Database(entities = [BankRegistration::class], version = 1, exportSchema = false)
abstract class BankRegistrationDatabase : RoomDatabase() {
    abstract fun bankRegistrationDao(): BankRegistrationDao

    companion object {
        @Volatile
        private var INSTANCE: BankRegistrationDatabase? = null

        fun getDatabase(context: Context): BankRegistrationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BankRegistrationDatabase::class.java,
                    "bank_registration_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
