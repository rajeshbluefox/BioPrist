package bluefox.rajesh.medicalrepresentative.homeModule.historyFragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.databinding.FragmentHistoryBinding
import bluefox.rajesh.medicalrepresentative.homeModule.apiFunctions.HomeViewModel
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.VisitData
import bluefox.rajesh.medicalrepresentative.homeModule.historyFragment.supportFunctions.UpdateVisitDialog
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions
import bluefox.rajesh.medicalrepresentative.zNetwork.NetworkHelper
import bluefox.rajesh.medicalrepresentative.zSharedPreference.LoginData
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var doctorsListAdapter: VisitHistoryAdapter

    private lateinit var networkHelper: NetworkHelper

    private var visitHistoryList: ArrayList<VisitData> = ArrayList()

    private lateinit var updateVisitDialog: UpdateVisitDialog

    private var retryCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.reset()
//        LoginData.saveRepresentativeId(requireContext(),1)

        initViews()
        checkInternetandCallAPI()
        observers()
        onClickListeners()

    }

    private fun onClickListeners() {

        binding.etSelectStartDate.setOnClickListener {
            clickType = 1
            initDatePicker()
        }

        binding.etSelectEndDate.setOnClickListener {
            clickType = 2
            initDatePicker()
        }

//        binding.btClearDate.setOnClickListener {
//            binding.btClearDate.visibility=View.GONE
//            binding.btClearDate.text="Filter By Date"
//            selDateFilter = ""
//            initRecyclerView(visitHistoryList)
//        }
    }

    private fun initViews() {
        networkHelper = NetworkHelper(requireContext())

        updateVisitDialog = UpdateVisitDialog(layoutInflater, requireContext(), ::cancelVisit)
    }

    private fun checkInternetandCallAPI() {

        if (networkHelper.isNetworkConnected()) {
            getDoctorsList()
        } else
            UtilFunctions.showToast(requireContext(), "Check Your Internet Connection")
    }

    private fun getDoctorsList() {
        showShimmer()
        visitHistoryList.clear()
        homeViewModel.getVisitHistory(LoginData.getRepresentativeId(requireContext()))
    }

    private fun observers() {

        homeViewModel.getVisitHistoryResponse().observe(requireActivity()) {

            if (it.code != 195) {

                if (it.code == 200) {
                    try {
                        if (it.visitHistoryList.size != 0) {
                            visitHistoryList = it.visitHistoryList.reversed() as ArrayList<VisitData>

//                            initRecyclerView(visitHistoryList)

                            selStartDate=getTodayDate()
                            selEndDate=getTodayDate()
                            filterListByDate(selStartDate,selEndDate)

                        } else {
                            binding.emptyContent.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        hideShimmer()
//                    binding.pbProcessing.visibility = View.INVISIBLE

                    } catch (e: Exception) {
                        UtilFunctions.showToast(
                            requireContext(),
                            "Server Not Responding! Please Restart"
                        )
                    }

                } else if (it.code == 199) {
                    UtilFunctions.showToast(requireContext(), "No Products Found")
                } else if (it.code == -1) {
                    UtilFunctions.showToast(
                        requireContext(),
                        "Take Longer Time...\n Please Wait..."
                    )
                    retryAPICall()
                }

            }
        }

        homeViewModel.getUpdatedVisitResponse().observe(requireActivity())
        {
            if (it.code != 195) {
                if (it.code == 200) {
                    UtilFunctions.showToast(requireContext(), "Cancelled Sucessfully")
                    updateVisitDialog.hidePB()
                    updateVisitDialog.closeCancelDialog()
                    getDoctorsList()
                }
            }
        }

    }

    private fun retryAPICall() {
        if (retryCount <= 3) {
            checkInternetandCallAPI()
            retryCount++
        }
    }

    private fun initRecyclerView(visitHistory: ArrayList<VisitData>) {

        doctorsListAdapter = VisitHistoryAdapter(visitHistory, requireContext(), ::onCancelClicked)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            adapter = doctorsListAdapter
        }

    }

    private fun onCancelClicked(visitData: VisitData) {
        updateVisitDialog.openCancelDialog(visitData)
    }

    private fun cancelVisit(rowId: Int, description: String) {
        updateVisitDialog.showPB()
        homeViewModel.updateVisitStatus(rowId, 1, description)
    }

    fun showShimmer() {
        binding.loadingShimmerHistory.shimmerLayout.startShimmer()

        binding.loadingShimmerHistory.shimmerLayout.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE


    }

    private fun hideShimmer() {
        binding.loadingShimmerHistory.shimmerLayout.stopShimmer()

        binding.loadingShimmerHistory.shimmerLayout.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE

    }

    private val cal = Calendar.getInstance()

    private var selDateFilter = ""

    private var clickType = 0

    private var selStartDate = ""
    private var selEndDate = ""

    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat1 = "yyyy-MM-dd"
            val sdfCalc = SimpleDateFormat(myFormat1, Locale.US)

            val selectedDateForCalculation = sdfCalc.format(cal.time)

            if(clickType==1)
            {
                selStartDate = selectedDateForCalculation
                binding.etSelectStartDate.text = selectedDateForCalculation


            }else
            {
                selEndDate = selectedDateForCalculation
                binding.etSelectEndDate.text = selectedDateForCalculation
            }


//            binding.btClearDate.visibility=View.VISIBLE
            filterListByDate(selStartDate,selEndDate)

        }

    private fun initDatePicker() {

        val datePic = DatePickerDialog(
            requireContext(), dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )

        datePic.show()


    }

    fun filterListByDate(selectedStartDate: String, selectedEndDate: String) {
        val visitHistoryFilteredList: ArrayList<VisitData> = ArrayList()

        for (dateItem in visitHistoryList) {
            if (dateItem.VisitedDate in selectedStartDate..selectedEndDate) {
                visitHistoryFilteredList.add(dateItem)
            }
        }

        if (visitHistoryFilteredList.isEmpty()) {
            UtilFunctions.showToast(requireContext(), "No Data Found")
        }

        initRecyclerView(visitHistoryFilteredList)

    }


    fun getTodayDate(): String {
        val myFormat1 = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat1)
        return sdf.format(Date())
    }
}