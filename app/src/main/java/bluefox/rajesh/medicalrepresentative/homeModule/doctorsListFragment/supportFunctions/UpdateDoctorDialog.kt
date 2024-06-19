package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.supportFunctions

import android.app.AlertDialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.Doctor
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions


class UpdateDoctorDialog(
    layoutInflater: LayoutInflater,
    context: Context,
    private val listener: (doctor: Doctor) -> Unit
) {

    private val mLayoutInflater: LayoutInflater
    private val mContext: Context

    // Store the original values of the EditText fields
    private var originalValueEditText1: String = ""
    private var originalValueEditText2: String = ""
    private var originalValueEditText3: String = ""
    private var originalValueEditText4: String = ""

    private lateinit var btUpdate: TextView

    private lateinit var etDoctorName: EditText
    private lateinit var etBuildingName: EditText
    private lateinit var etLocationName: EditText
    private lateinit var etPhoneNumber: EditText

    // Declare global properties
    private lateinit var view: View
    private lateinit var dialog: AlertDialog.Builder
    private lateinit var messageBoxInstance: AlertDialog

    private lateinit var progressBar: ProgressBar
    private lateinit var mainLt: ConstraintLayout


    init {
        mLayoutInflater = layoutInflater
        mContext = context
    }

    fun openUpdateDoctorDialog(doctor: Doctor) {

        view = mLayoutInflater.inflate(R.layout.dialog_update_doctor, null)
        dialog = AlertDialog.Builder(mContext).setView(view)
        dialog.setCancelable(true)
        messageBoxInstance = dialog.show()

        progressBar=view.findViewById(R.id.progressBar)
        mainLt=view.findViewById(R.id.mainLt)

        etDoctorName = view.findViewById(R.id.etDoctorName)
        etBuildingName = view.findViewById(R.id.etBuildingName)
        etLocationName = view.findViewById(R.id.etLocationName)
        etPhoneNumber = view.findViewById(R.id.etPhoneNumber)

        btUpdate = view.findViewById(R.id.updateBt)
        val btCancel = view.findViewById<TextView>(R.id.cancelBt)

        etDoctorName.setText(doctor.DoctorName)
        etBuildingName.setText(doctor.BuildingName.toString())
        etLocationName.setText(doctor.Location)
        etPhoneNumber.setText(doctor.ContactNumber1)

        // Save the original values of the EditText fields
        originalValueEditText1 = etDoctorName.text.toString()
        originalValueEditText2 = etBuildingName.text.toString()
        originalValueEditText3 = etLocationName.text.toString()
        originalValueEditText4 = etPhoneNumber.text.toString()

        // Set a TextWatcher for each EditText
        setEditTextWatcher(etDoctorName, originalValueEditText1)
        setEditTextWatcher(etBuildingName, originalValueEditText2)
        setEditTextWatcher(etLocationName, originalValueEditText3)
        setEditTextWatcher(etPhoneNumber, originalValueEditText4)

        toggleButton(false)
        hidePB()


        btCancel.setOnClickListener {
            messageBoxInstance.dismiss()
        }

        btUpdate.setOnClickListener {
            checkFields(doctor.DoctorId)
        }

    }

    private fun checkFields(doctorId: Int) {
        val doctorName = etDoctorName.text.toString().trim()
        val buildingName = etBuildingName.text.toString().trim()
        val locationName = etLocationName.text.toString().trim()
        val phoneNumber = etPhoneNumber.text.toString().trim()

        if (doctorName.isEmpty()) {
            UtilFunctions.showToast(mContext, "Doctor Name cannot be empty")
        } else if (buildingName.isEmpty()) {
            UtilFunctions.showToast(mContext, "Building Name cannot be empty")
        } else if (locationName.isEmpty()) {
            UtilFunctions.showToast(mContext, "Location Name cannot be empty")
        } else if (phoneNumber.isEmpty()) {
            UtilFunctions.showToast(mContext, "Phone Number cannot be empty")
        } else {
            val mDoctor = Doctor()

            mDoctor.DoctorId = doctorId
            mDoctor.DoctorName = doctorName
            mDoctor.BuildingName = buildingName
            mDoctor.Location = locationName
            mDoctor.ContactNumber1 = phoneNumber

            showPB()
            listener.invoke(mDoctor)

        }
    }

    fun closeDialog()
    {
        messageBoxInstance.dismiss()
    }


    private fun setEditTextWatcher(editText: EditText, originalValue: String) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // Check if the current value is different from the original value
                val isValueChanged = s.toString() != originalValue

                toggleButton(isValueChanged)
            }
        })
    }

    fun toggleButton(status: Boolean)
    {
        btUpdate.isEnabled = status
        btUpdate.isClickable = status

        if(status)
            btUpdate.setBackgroundResource(R.drawable.button_one)
        else
            btUpdate.setBackgroundColor(R.drawable.button_disabled)
    }

    fun showPB()
    {
        progressBar.visibility=View.VISIBLE
        mainLt.visibility=View.GONE
    }

    fun hidePB()
    {
        progressBar.visibility=View.GONE
        mainLt.visibility=View.VISIBLE
    }


}