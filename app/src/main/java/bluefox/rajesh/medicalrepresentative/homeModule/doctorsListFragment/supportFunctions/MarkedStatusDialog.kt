package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.supportFunctions

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.Doctor
import com.airbnb.lottie.LottieAnimationView


class MarkedStatusDialog(
    layoutInflater: LayoutInflater,
    context: Context
) {

    private val mLayoutInflater: LayoutInflater
    private val mContext: Context

    lateinit var progressBar : ProgressBar

    lateinit var btYes : TextView

    lateinit var tvMainTitle: TextView

    lateinit var loadingAnimation : LottieAnimationView

    init {
        mLayoutInflater = layoutInflater
        mContext = context
    }

    fun openMarkDoctorDialog(doctor: Doctor) {

        val view = mLayoutInflater.inflate(R.layout.alert_marked_status, null)
        val dialog = AlertDialog.Builder(mContext).setView(view)
        dialog.setCancelable(false)
        val messageBoxInstance = dialog.show()

        btYes = view.findViewById<TextView>(R.id.btYes)
        tvMainTitle=view.findViewById<TextView>(R.id.mainTitle)

        loadingAnimation=view.findViewById<LottieAnimationView>(R.id.animation_view)

        progressBar =view.findViewById(R.id.progressBar)
        showPB()


        btYes.setOnClickListener {
            messageBoxInstance.dismiss()
        }

    }

    fun showPB()
    {
        progressBar.visibility=View.VISIBLE
        btYes.visibility=View.GONE
        tvMainTitle.visibility=View.GONE


    }

    fun hidePB()
    {
        progressBar.visibility=View.GONE

        btYes.visibility=View.VISIBLE
        tvMainTitle.visibility=View.VISIBLE

        loadingAnimation.visibility=View.VISIBLE
        loadingAnimation.playAnimation()

    }


}