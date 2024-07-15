package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bluefox.rajesh.medicalrepresentative.databinding.ItemInvoiceBinding
import bluefox.rajesh.medicalrepresentative.databinding.ItemMedicinesBinding
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.InvoiceData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.MedicineData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.ProductStockData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.SelectedData
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions


class MedicineItemAdapter(
    private var medicineDataList: ArrayList<ProductStockData>,
    private var context: Context,
    private val listener: (medicineData: ProductStockData) -> Unit,
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

        holder.binding.tvMedicineName.text = medicineItem.productName
        holder.binding.tvRateValue.text = medicineItem.ratePurchase
        holder.binding.tvMRPValue.text = medicineItem.mrp
        holder.binding.tvStockValue.text = medicineItem.totItemsIn

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

        holder.binding.btSaveQty.setOnClickListener {
            val quantity = holder.binding.etQuantity.text.toString()
            val freeQuantity = holder.binding.etFreeQuantity.text.toString()

            if (quantity.isNotEmpty() || freeQuantity.isNotEmpty()) {
                medicineItem.customerId = SelectedData.salesCustomerData.customerId
                medicineItem.customerName = SelectedData.salesCustomerData.customerName

                if (quantity.isNotEmpty())
                    medicineItem.quantity = quantity
                if (freeQuantity.isNotEmpty())
                    medicineItem.freeQuantity = freeQuantity

                holder.binding.etQuantity.text.clear()
                holder.binding.etFreeQuantity.text.clear()

                listener.invoke(medicineItem)
            } else {
                UtilFunctions.showToast(context, "Enter Quantity")
            }
        }

    }


    override fun getItemCount(): Int {
        return medicineDataList.size
    }

}