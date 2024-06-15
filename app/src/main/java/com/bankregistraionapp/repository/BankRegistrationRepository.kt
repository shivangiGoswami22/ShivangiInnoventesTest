package com.bankregistraionapp.repository
import android.content.Context
import com.bankregistraionapp.BankRegistrationDatabase
import com.bankregistraionapp.interfaces.BankRegistrationDao
import com.bankregistraionapp.model.BankRegistration

class BankRegistrationRepository(context: Context) {
    private val bankRegistrationDao: BankRegistrationDao = BankRegistrationDatabase.getDatabase(context).bankRegistrationDao()

     fun insert(registration: BankRegistration) {
        bankRegistrationDao.insert(registration)
    }

     fun getAllRegistrations(): List<BankRegistration> {
        return bankRegistrationDao.getAllRegistrations()
    }
}
