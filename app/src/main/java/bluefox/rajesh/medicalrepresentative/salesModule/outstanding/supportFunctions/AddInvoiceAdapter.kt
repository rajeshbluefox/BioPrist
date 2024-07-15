package bluefox.rajesh.medicalrepresentative.salesModule.outstanding.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bluefox.rajesh.medicalrepresentative.databinding.ItemAddInvoiceBinding
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.InvoiceData


class AddInvoiceAdapter(
    private var invoiceDataList: ArrayList<InvoiceData>,
    private var context: Context,
    private val listener: (invoiceData: InvoiceData) -> Unit,
) :
    RecyclerView.Adapter<AddInvoiceAdapter.AddInvoiceAdapterViewHolder>() {


    class AddInvoiceAdapterViewHolder(var binding: ItemAddInvoiceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddInvoiceAdapterViewHolder {
        return AddInvoiceAdapterViewHolder(
            ItemAddInvoiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AddInvoiceAdapterViewHolder, position: Int) {

        val invoiceItem = invoiceDataList[position]

        holder.binding.tvInvoiceDateValue.text = invoiceItem.invoiceDate
        holder.binding.tvInvoiceNoValue.text = invoiceItem.invoiceNumber
        holder.binding.tvAmountValue.text = invoiceItem.invoiceAmount
        holder.binding.tvBalanceValue.text = invoiceItem.balanceAmount

    }


    override fun getItemCount(): Int {
        return invoiceDataList.size
    }

}