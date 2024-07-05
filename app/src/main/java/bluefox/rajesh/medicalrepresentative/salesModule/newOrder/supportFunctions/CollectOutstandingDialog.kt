package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import bluefox.rajesh.medicalrepresentative.R




class CollectOutstandingDialog(
    layoutInflater: LayoutInflater,
    context: Context,
    private val listener: () -> Unit
) {

    private val mLayoutInflater: LayoutInflater
    private val mContext: Context

    private lateinit var dialog: AlertDialog.Builder
    private lateinit var messageBoxInstance: AlertDialog

    init {
        mLayoutInflater = layoutInflater
        mContext = context

        initViews()
    }


    fun initViews() {
        val view = mLayoutInflater.inflate(R.layout.alert_dialog_collect_out_standing, null)
        dialog = AlertDialog.Builder(mContext, R.style.CurvedDialog).setView(view)
        dialog.setCancelable(true)
        messageBoxInstance = dialog.create()

        val etCollectAmount = view.findViewById<TextView>(R.id.etCollectAmount)
        val btSubmit = view.findViewById<TextView>(R.id.btSubmit)


        btSubmit.setOnClickListener {
            listener.invoke()
        }
    }

    fun openCollectOutstandingDialog() {
        messageBoxInstance.show()

    }

    fun close()
    {
        messageBoxInstance.dismiss()
    }

}