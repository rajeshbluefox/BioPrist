package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bluefox.rajesh.medicalrepresentative.databinding.ItemAddedItemBinding
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.AddItemActivity
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.CustomerDetailsActivity
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.CustomersData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.ProductStockData
import bluefox.rajesh.medicalrepresentative.salesModule.ordersList.OrdersListActivity


class AddedItemsAdapter(
    private var medicinesList: ArrayList<ProductStockData>,
    private var context: Context,
    private val listener: () -> Unit,
) :
    RecyclerView.Adapter<AddedItemsAdapter.AddedItemsAdapterViewHolder>() {


    class AddedItemsAdapterViewHolder(var binding: ItemAddedItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddedItemsAdapterViewHolder {
        return AddedItemsAdapterViewHolder(
            ItemAddedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AddedItemsAdapterViewHolder, position: Int) {

        val medicineItems = medicinesList[position]

        holder.binding.tvMedicineName.text = medicineItems.productName

        if(context is OrdersListActivity)
        {
            holder.binding.tvCustomerName.visibility=View.VISIBLE
            holder.binding.tvCustomerNameValue.visibility=View.VISIBLE
            holder.binding.tvCustomerNameValue.text=medicineItems.customerName
        }

        if (medicineItems.quantity != "0")
            holder.binding.tvQuantity.text = medicineItems.quantity
        else{
            holder.binding.titleQ.visibility= View.GONE
            holder.binding.tvQuantity.visibility=View.GONE
        }

        if (medicineItems.freeQuantity!="0")
        {
            holder.binding.tvFreeQuantity.text = medicineItems.freeQuantity
        }else{
            holder.binding.titleFQ.visibility=View.GONE
            holder.binding.tvFreeQuantity.visibility=View.GONE
        }


        holder.binding.productItem.setOnClickListener {
//            listener.invoke()
        }

    }

    override fun getItemCount(): Int {
        return medicinesList.size
    }

}