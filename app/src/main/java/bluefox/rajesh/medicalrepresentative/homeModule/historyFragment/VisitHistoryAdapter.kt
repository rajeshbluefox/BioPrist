package bluefox.rajesh.medicalrepresentative.homeModule.historyFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bluefox.rajesh.medicalrepresentative.databinding.ItemDoctorVisitHistoryBinding
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.Doctor
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.VisitData


class VisitHistoryAdapter(
    private var productsList: ArrayList<VisitData>,
    private var context: Context,
    private val listener: (visitData: VisitData) -> Unit,
) :
    RecyclerView.Adapter<VisitHistoryAdapter.VisitHistoryAdapterViewHolder>() {


    class VisitHistoryAdapterViewHolder(var binding: ItemDoctorVisitHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitHistoryAdapterViewHolder {
        return VisitHistoryAdapterViewHolder(
            ItemDoctorVisitHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VisitHistoryAdapterViewHolder, position: Int) {

        val doctorVisitHistory = productsList[position]

        holder.binding.doctorName.text=doctorVisitHistory.CustomerName
        holder.binding.dateofvisitValue.text=doctorVisitHistory.VisitedDate


        holder.binding.cancelBt.setOnClickListener {
            listener.invoke(doctorVisitHistory)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}