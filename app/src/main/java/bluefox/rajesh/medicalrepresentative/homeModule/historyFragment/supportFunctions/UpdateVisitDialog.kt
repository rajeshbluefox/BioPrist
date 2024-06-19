package bluefox.rajesh.medicalrepresentative.homeModule.historyFragment.supportFunctions

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.VisitData
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions


class UpdateVisitDialog(
    layoutInflater: LayoutInflater,
    context: Context,
    private val listener: (rowId:Int,remarks:String) -> Unit
) {

    private val mLayoutInflater: LayoutInflater
    private val mContext: Context

    private lateinit var dialog: AlertDialog.Builder
    private lateinit var messageBoxInstance: AlertDialog

    private lateinit var progressBar : ProgressBar
    private lateinit var contentLayout :  ConstraintLayout

    init {
        mLayoutInflater = layoutInflater
        mContext = context

    }


    fun openCancelDialog(visitData: VisitData) {
        val view = mLayoutInflater.inflate(R.layout.alert_cancel, null)
        dialog = AlertDialog.Builder(mContext,R.style.CurvedDialog).setView(view)
        dialog.setCancelable(false)
        messageBoxInstance = dialog.show()

        progressBar=view.findViewById(R.id.progressBar)
        contentLayout=view.findViewById(R.id.cancelContentLt)

        val btYes = view.findViewById<TextView>(R.id.btYes)
        val btNo = view.findViewById<TextView>(R.id.btNo)

        val etRemarks=view.findViewById<EditText>(R.id.etDescription)

        hidePB()

        btNo.setOnClickListener {
            messageBoxInstance.dismiss()
        }

        btYes.setOnClickListener {

            val mRemarks = etRemarks.text.toString()
            if(mRemarks.isEmpty())
                UtilFunctions.showToast(mContext,"Remarks Empty")
            else{
                listener.invoke(visitData.RowId,mRemarks)
            }

        }
    }

    fun closeCancelDialog() {
        messageBoxInstance.dismiss()
    }

    fun showPB()
    {
        progressBar.visibility=View.VISIBLE
        contentLayout.visibility=View.GONE
    }

    fun hidePB()
    {
        progressBar.visibility=View.GONE
        contentLayout.visibility=View.VISIBLE
    }

}