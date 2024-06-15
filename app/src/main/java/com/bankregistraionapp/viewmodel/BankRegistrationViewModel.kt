package com.bankregistraionapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bankregistraionapp.BankRegistrationDatabase
import com.bankregistraionapp.model.BankRegistration
import com.bankregistraionapp.repository.BankRegistrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BankRegistrationViewModel( application: Application) : AndroidViewModel(application) {
    private val repository: BankRegistrationRepository

    init {
        repository = BankRegistrationRepository(application)
    }

    fun insert(registration: BankRegistration) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(registration)
    }

     fun getAllRegistrations(): List<BankRegistration> {
        return repository.getAllRegistrations()
    }
}
