package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.supportFunctions

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.Doctor
import bluefox.rajesh.medicalrepresentative.homeModule.modalClass.Product
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions


class MarkDoctorDialog(
    layoutInflater: LayoutInflater,
    context: Context,
    private val listener: (doctor: Doctor) -> Unit
) {

    private val mLayoutInflater: LayoutInflater
    private val mContext: Context


    private var isGiftGiven = -1

    init {
        mLayoutInflater = layoutInflater
        mContext = context
    }

    fun openMarkDoctorDialog(doctor: Doctor) {

        val view = mLayoutInflater.inflate(R.layout.alert_mark_doctor, null)
        val dialog = AlertDialog.Builder(mContext).setView(view)
        dialog.setCancelable(true)
        val messageBoxInstance = dialog.show()

        val btYes = view.findViewById<TextView>(R.id.btYes)
        val btNo = view.findViewById<RelativeLayout>(R.id.cancelBt)

        val tvDoctorName=view.findViewById<TextView>(R.id.doctorName)

        tvDoctorName.text=doctor.DoctorName

        btNo.setOnClickListener {
            messageBoxInstance.dismiss()
        }

        btYes.setOnClickListener {
            if(isGiftGiven!=-1) {
                doctor.giftGiven=isGiftGiven
                listener.invoke(doctor)
                messageBoxInstance.dismiss()
            }else{
                UtilFunctions.showToast(mContext,"Mark Gift Given Status")
            }
        }

        val rgGiftGiven = view.findViewById<RadioGroup>(R.id.giftRG)

        rgGiftGiven.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.ggYes -> {
                    // Handle "Yes" selected
                    isGiftGiven = 1
                }
                R.id.ggNo -> {
                    // Handle "No" selected
                    isGiftGiven = 0
                }
            }
        }

    }





}