package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bluefox.rajesh.medicalrepresentative.databinding.ItemAddedItemBinding
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.MedicineData


class AddedItemsAdapter(
    private var medicinesList: ArrayList<MedicineData>,
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

        holder.binding.tvMedicineName.text=medicineItems.medicineName
        holder.binding.tvQuantity.text=medicineItems.quantity

        holder.binding.productItem.setOnClickListener {
//            listener.invoke()
        }

    }

    override fun getItemCount(): Int {
        return medicinesList.size
    }

}