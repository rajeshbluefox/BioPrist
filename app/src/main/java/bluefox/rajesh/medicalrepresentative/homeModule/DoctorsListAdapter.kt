package bluefox.rajesh.medicalrepresentative.homeModule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.databinding.ItemDoctorDetailsBinding
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.Doctor


class DoctorsListAdapter(
    private var productsList: ArrayList<Doctor>,
    private var context: Context,
    private val listener: (doctor: Doctor) -> Unit,
    private val updateListener: (doctor: Doctor) -> Unit
) :
    RecyclerView.Adapter<DoctorsListAdapter.AdapterOrderFormViewHolder>() {


    class AdapterOrderFormViewHolder(var binding: ItemDoctorDetailsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterOrderFormViewHolder {
        return AdapterOrderFormViewHolder(
            ItemDoctorDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterOrderFormViewHolder, position: Int) {

        val doctor = productsList[position]

//        Picasso.get().load(product.Photo).into(holder.binding.productImage)

        holder.binding.doctorName.text = doctor.DoctorName
        holder.binding.buildingNameValue.text=doctor.BuildingName
        holder.binding.locationValue.text=doctor.Location
        holder.binding.phoneNumberValue.text=doctor.ContactNumber1


//        if(doctor.isSelected)
//        {
//            holder.binding.selectBT.setBackgroundResource(R.drawable.button_two)
//            val textColor = ContextCompat.getColor(context, R.color.white_to_black)
//            holder.binding.selectBT.text="Visited"
//            holder.binding.selectBT.setTextColor(textColor)
//        }

        holder.binding.selectBT.setOnClickListener {
            listener.invoke(doctor)
        }

        holder.binding.updateBt.setOnClickListener {
            updateListener.invoke(doctor)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}