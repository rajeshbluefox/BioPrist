package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions


class AddQuantityDialog(
    layoutInflater: LayoutInflater,
    context: Context,
    private val listener: (quantity: String) -> Unit
) {

    private val mLayoutInflater: LayoutInflater
    private val mContext: Context

    private lateinit var dialog: AlertDialog.Builder
    private lateinit var messageBoxInstance: AlertDialog

    private lateinit var tvTitle : TextView
    private lateinit var etQuantity : EditText

    init {
        mLayoutInflater = layoutInflater
        mContext = context

        initViews()
    }


    private fun initViews() {
        val view = mLayoutInflater.inflate(R.layout.alert_dialog_add_quantity, null)
        dialog = AlertDialog.Builder(mContext, R.style.CurvedDialog).setView(view)
        dialog.setCancelable(true)
        messageBoxInstance = dialog.create()

        tvTitle = view.findViewById(R.id.title1)
        etQuantity = view.findViewById(R.id.etEnterQuantity)
        val btSubmit = view.findViewById<TextView>(R.id.btSubmit)


        btSubmit.setOnClickListener {
            val quantity = etQuantity.text.toString()

            if(quantity.isNotEmpty())
            {
                listener.invoke(quantity)
            }else{
                UtilFunctions.showToast(context = mContext,"Enter quantity")
            }
        }
    }

    fun openAddQuantityDialog(title: String) {
        tvTitle.text=title
        messageBoxInstance.show()

    }

    fun close()
    {
        messageBoxInstance.dismiss()
    }

}