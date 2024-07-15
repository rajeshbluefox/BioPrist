package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bluefox.rajesh.medicalrepresentative.databinding.ItemInvoiceBinding
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.InvoiceData


class InvoiceAdapter(
    private var invoiceDataList: ArrayList<InvoiceData>,
    private var context: Context,
    private val listener: (invoiceData: InvoiceData) -> Unit,
) :
    RecyclerView.Adapter<InvoiceAdapter.InvoiceAdapterViewHolder>() {


    class InvoiceAdapterViewHolder(var binding: ItemInvoiceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceAdapterViewHolder {
        return InvoiceAdapterViewHolder(
            ItemInvoiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: InvoiceAdapterViewHolder, position: Int) {

        val invoiceItem = invoiceDataList[position]


        holder.binding.tvInvoiceDateValue.text = invoiceItem.invoiceDate!!.substring(0, 10)
        holder.binding.tvInvoiceNoValue.text = invoiceItem.invoiceNumber

        val number = invoiceItem.invoiceAmount!!.toDouble()
        val trimmedNumber = String.format("%.1f", number)
        holder.binding.tvAmountValue.text = trimmedNumber


        val balanceNumber = invoiceItem.balanceAmount!!.toDouble()
        val trimmedBalanceNumber = String.format("%.1f", balanceNumber)
        holder.binding.tvBalanceValue.text = trimmedBalanceNumber

        holder.binding.tvDaysValue.text = invoiceItem.days.toString()

    }


    override fun getItemCount(): Int {
        return invoiceDataList.size
    }

}