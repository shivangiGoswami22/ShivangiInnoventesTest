package com.bankregistraionapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bankregistraionapp.databinding.ActivityMainBinding
import com.bankregistraionapp.model.BankRegistration
import com.bankregistraionapp.ui.DetailActivity
import com.bankregistraionapp.viewmodel.BankRegistrationViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Calendar

class MainActivity : AppCompatActivity() {
  lateinit  var binding: ActivityMainBinding
    private val viewModel: BankRegistrationViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding=  ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)
        binding.btnRegister.isEnabled = false
        binding?.btnRegister?.setOnClickListener {
            val panNumber = binding?.etPanNumber?.text.toString().trim()
            var birthDate=binding.etDate.text.toString()+"/"+binding.etMonth.text.toString()+"/"+binding.etYear.text.toString()
            if (validatePanNumber(panNumber) && isValidDate(birthDate)) {
                val registration = BankRegistration(panNumber=panNumber,birthDate= birthDate,name="")
                viewModel.insert(registration)
                Toast.makeText(this, "Details submitted successfully", Toast.LENGTH_LONG).show()
                var intent= Intent(this, DetailActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid PAN Number or Birth Date", Toast.LENGTH_LONG).show()
            }
        }
        binding.dontHavePan.setOnClickListener {
            finish()
        }
        val textWatcher = object : TextWatcher {
            override
            fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2:Int) {
            }
            override
            fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }
            override
            fun afterTextChanged(editable: Editable?) {
                if (editable != null && !editable.toString().equals("")) {
                    if ( binding.etDate.hasFocus()&& binding.etDate!!.text.hashCode() == editable.hashCode()) {
                       if (editable.toString().length==2) binding.etMonth.requestFocus()
                        if (binding.etMonth.length()>0 && binding.etYear.length()>0) checkdateValidation()
                    }
                    else if( binding.etMonth.hasFocus()&& binding.etMonth!!.text.hashCode() == editable.hashCode()) {
                        if (editable.toString().length==2) binding.etYear.requestFocus()
                        if (binding.etDate.length()>0 && binding.etYear.length()>0) checkdateValidation()

                    }
                    else if( binding.etYear.hasFocus()&& binding.etYear!!.text.hashCode() == editable.hashCode()) {
                        if (editable.toString().length==4) {
                            checkdateValidation()
                        }
                    }
                    else if( binding.etPanNumber.hasFocus()&& binding.etPanNumber!!.text.hashCode() == editable.hashCode()) {
                        if (editable.toString().length==10){
                            if (!validatePanNumber(editable.toString().trim()))
                            {
                                Toast.makeText(this@MainActivity, "Invalid PAN Number", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                }
            }
        binding.etDate.addTextChangedListener(textWatcher)
        binding.etMonth.addTextChangedListener(textWatcher)
        binding.etYear.addTextChangedListener(textWatcher)
        binding.etPanNumber.addTextChangedListener(textWatcher)

    }

    fun checkdateValidation(){
        var dob=binding.etDate.text.toString()+"/"+binding.etMonth.text.toString()+"/"+binding.etYear.text.toString()
        if (!isValidDate(dob)) {
            Toast.makeText(
                this@MainActivity,
                "Invalid Date of Birth",
                Toast.LENGTH_SHORT
            ).show()
            binding.btnRegister.isEnabled = false
        }
        else{
            binding.btnRegister.isEnabled = true
        }
    }

    private fun validatePanNumber(panNumber: String): Boolean {
        val panPattern = "[A-Z]{5}[0-9]{4}[A-Z]".toRegex()
        if (!panPattern.matches(panNumber)) {
            binding.btnRegister.isEnabled = false
            return false
        }
        else{
            binding.btnRegister.isEnabled = true
            return true
        }

    }

    private fun isValidDate(date: String): Boolean {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.isLenient = false
        return try {
            val parsedDate = dateFormat.parse(date)
            val calendar = Calendar.getInstance()
            parsedDate?.let {
                // Check if the date is not in the future
                if (parsedDate.after(calendar.time)) {
                    return false
                }
            }
            parsedDate != null
        } catch (e: Exception) {
            false
        }
    }


}
