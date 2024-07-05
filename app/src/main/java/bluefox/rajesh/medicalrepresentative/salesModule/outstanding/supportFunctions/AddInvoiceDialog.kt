package bluefox.rajesh.medicalrepresentative.salesModule.outstanding.supportFunctions

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.InvoiceData


class AddInvoiceDialog(
    layoutInflater: LayoutInflater,
    context: Context,
    private val listener: () -> Unit
) {

    private val mLayoutInflater: LayoutInflater
    private val mContext: Context

    private lateinit var dialog: AlertDialog.Builder
    private lateinit var messageBoxInstance: AlertDialog


    private lateinit var btAddItem : TextView
    private lateinit var btSubmit : TextView

    private lateinit var rvInvoice : RecyclerView

    private lateinit var invoiceDataList: ArrayList<InvoiceData>


    init {
        mLayoutInflater = layoutInflater
        mContext = context

        invoiceDataList = ArrayList()

        initViews()
    }


    private fun initViews() {
        val view = mLayoutInflater.inflate(R.layout.dialog_add_invoice, null)
        dialog = AlertDialog.Builder(mContext, R.style.CurvedDialog).setView(view)
        dialog.setCancelable(true)
        messageBoxInstance = dialog.create()

        rvInvoice = view.findViewById(R.id.rvInvoice)

        btAddItem = view.findViewById(R.id.btAddItem)
        btSubmit = view.findViewById(R.id.btSubmit)



        btAddItem.setOnClickListener {

            val invoiceData1 = InvoiceData()
            invoiceData1.invoiceDate="${0+dummyPosition}/06/2024"
            invoiceData1.invoiceNumber="I000$dummyPosition"
            invoiceData1.amount="${1000+dummyPosition}"
            invoiceData1.balance="${500+dummyPosition}"
            invoiceDataList.add(invoiceData1)

            Log.e("Test","Size : ${invoiceDataList.size}")
            addInvoiceAdapter.notifyItemInserted(dummyPosition-1)
            rvInvoice.scrollToPosition(invoiceDataList.size - 1)
            dummyPosition++
        }

        btSubmit.setOnClickListener {
            close()
        }

    }

    private lateinit var addInvoiceAdapter: AddInvoiceAdapter


    private fun initAddInvoiceAdapter(invoiceList: ArrayList<InvoiceData>) {

        addInvoiceAdapter = AddInvoiceAdapter(invoiceList, mContext, ::onEntered)

        rvInvoice.apply {
            layoutManager = LinearLayoutManager(
                mContext,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = addInvoiceAdapter
        }

    }

    private fun onEntered(invoiceData: InvoiceData)
    {

    }

    fun openAddInvoiceButton() {

        fillDummyList()

        messageBoxInstance.show()

        openKeyboard()

    }

    private fun close()
    {
        messageBoxInstance.dismiss()
    }


    var dummyPosition = 1

    private fun fillDummyList()
    {
        val invoiceData1 = InvoiceData()
        invoiceData1.invoiceDate="${0+dummyPosition}/06/2024"
        invoiceData1.invoiceNumber="I000$dummyPosition"
        invoiceData1.amount="${1000+dummyPosition}"
        invoiceData1.balance="${500+dummyPosition}"
        invoiceDataList.add(invoiceData1)
        dummyPosition++

        initAddInvoiceAdapter(invoiceDataList)
    }

    private fun openKeyboard()
    {
        // Get the window of the dialog
        val window = messageBoxInstance.window
        val layoutParams = window?.attributes

        // Get the screen width and set the dialog width to 75% of it
        val displayMetrics = mContext.resources.displayMetrics
        layoutParams?.height = (displayMetrics.heightPixels * 0.50).toInt()

        // Apply the layout parameters to the dialog window
        window?.attributes = layoutParams

        // Ensure the keyboard is shown
//        val inputMethodManager = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        window?.decorView?.post {
//            val viewToFocus = window.decorView.findFocus() ?: window.decorView.findViewById<View>(R.id.rvInvoice)
//            viewToFocus?.let {
//                it.requestFocus()
//                inputMethodManager.showSoftInput(it, InputMethodManager.SHOW_IMPLICIT)
//            }
//        }
    }

}