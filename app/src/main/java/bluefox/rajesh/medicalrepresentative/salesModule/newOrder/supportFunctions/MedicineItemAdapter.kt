package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bluefox.rajesh.medicalrepresentative.databinding.ItemInvoiceBinding
import bluefox.rajesh.medicalrepresentative.databinding.ItemMedicinesBinding
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.InvoiceData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.MedicineData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.SelectedData


class MedicineItemAdapter(
    private var medicineDataList: ArrayList<MedicineData>,
    private var context: Context,
    private val listener: (medicineData: MedicineData) -> Unit,
) :
    RecyclerView.Adapter<MedicineItemAdapter.MedicineItemAdapterViewHolder>() {


    class MedicineItemAdapterViewHolder(var binding: ItemMedicinesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MedicineItemAdapterViewHolder {
        return MedicineItemAdapterViewHolder(
            ItemMedicinesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MedicineItemAdapterViewHolder, position: Int) {

        val medicineItem = medicineDataList[position]

        holder.binding.tvMedicineName.text = medicineItem.medicineName
        holder.binding.tvRateValue.text = medicineItem.rate
        holder.binding.tvMRPValue.text = medicineItem.mrp
        holder.binding.tvStockValue.text = medicineItem.stock

        holder.binding.btAddQty.setOnClickListener {
            medicineItem.customerId = SelectedData.salesCustomerData.customerId!!
            medicineItem.quantityType = "Quantity"
            listener.invoke(medicineItem)
        }


        holder.binding.btFreeQty.setOnClickListener {
            medicineItem.customerId = SelectedData.salesCustomerData.customerId!!
            medicineItem.quantityType = "Free Quantity"
            listener.invoke(medicineItem)
        }

    }


    override fun getItemCount(): Int {
        return medicineDataList.size
    }

}