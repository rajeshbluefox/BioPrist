package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bluefox.rajesh.medicalrepresentative.databinding.ItemDoctorVisitHistoryBinding
import bluefox.rajesh.medicalrepresentative.databinding.ItemNewOrdersBinding
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.VisitData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.SalesCustomerData


class NewOrdersAdapter(
    private var customerList: ArrayList<SalesCustomerData>,
    private var context: Context,
    private val listener: (salesCustomerData: SalesCustomerData) -> Unit,
) :
    RecyclerView.Adapter<NewOrdersAdapter.NewOrdersAdapterViewHolder>() {


    class NewOrdersAdapterViewHolder(var binding: ItemNewOrdersBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOrdersAdapterViewHolder {
        return NewOrdersAdapterViewHolder(
            ItemNewOrdersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewOrdersAdapterViewHolder, position: Int) {

        val customerItems = customerList[position]

        holder.binding.customerName.text=customerItems.customerName

        holder.binding.customerItem.setOnClickListener {
            listener.invoke(customerItems)
        }
//        val doctorVisitHistory = customerList[position]
//
//        holder.binding.doctorName.text=doctorVisitHistory.CustomerName
//        holder.binding.dateofvisitValue.text=doctorVisitHistory.VisitedDate
//
//
//        holder.binding.cancelBt.setOnClickListener {
//            listener.invoke(doctorVisitHistory)
//        }
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

}