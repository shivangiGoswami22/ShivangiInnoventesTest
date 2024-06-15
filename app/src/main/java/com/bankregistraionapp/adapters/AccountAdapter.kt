package com.bankregistraionapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bankregistraionapp.databinding.ItemAccountLayoutBinding
import com.bankregistraionapp.model.BankRegistration

class AccountAdapter(private var accountList: ArrayList<BankRegistration>)
    : RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    var accList: ArrayList<BankRegistration>

    init {
        accList = accountList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AccountAdapter.ViewHolder {
        return ViewHolder(ItemAccountLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mBbinding.PanNumber.text = "Pan Number: "+accList.get(position).panNumber
        holder.mBbinding.dob.text ="Date of Birth: "+ accList.get(position).birthDate
    }

    override fun getItemCount(): Int {
        return accList.size

    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(binding: ItemAccountLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var mBbinding: ItemAccountLayoutBinding

        init {
            this.mBbinding = binding
        }
    }
}


