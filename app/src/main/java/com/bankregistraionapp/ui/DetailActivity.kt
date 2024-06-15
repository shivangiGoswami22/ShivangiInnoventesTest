package com.bankregistraionapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ListAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bankregistraionapp.adapters.AccountAdapter
import com.bankregistraionapp.databinding.ActivityDetailBinding
import com.bankregistraionapp.databinding.ActivityMainBinding
import com.bankregistraionapp.model.BankRegistration
import com.bankregistraionapp.viewmodel.BankRegistrationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Calendar

class DetailActivity : AppCompatActivity() {
  lateinit  var binding: ActivityDetailBinding
    private val viewModel: BankRegistrationViewModel by viewModels()
    var accountList=ArrayList<BankRegistration>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var adapter= AccountAdapter(accountList)
        binding.accountRv.adapter= adapter
        lifecycleScope.launch(Dispatchers.IO) {
         var lsit=   viewModel.getAllRegistrations()
            for (i in 0..lsit.lastIndex){
                Log.d("data","PAN..${lsit.get(i).panNumber}")
            }
            accountList.addAll(viewModel.getAllRegistrations())
            adapter.notifyDataSetChanged()
            withContext(Dispatchers.Main){
                 adapter.notifyDataSetChanged()
            }
        }
    }

}
