package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.databinding.FragmentDoctorsBinding
import bluefox.rajesh.medicalrepresentative.homeModule.DoctorsListAdapter
import bluefox.rajesh.medicalrepresentative.homeModule.apiFunctions.HomeViewModel
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.AreaItem
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.Doctor
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.LocationData
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.MarkVisitData
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.supportFunctions.AreaAdapter
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.supportFunctions.MarkDoctorDialog
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.supportFunctions.MarkedStatusDialog
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.supportFunctions.UpdateDoctorDialog
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions
import bluefox.rajesh.medicalrepresentative.zNetwork.NetworkHelper
import bluefox.rajesh.medicalrepresentative.zSharedPreference.LoginData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorsFragment : Fragment() {

    private lateinit var binding: FragmentDoctorsBinding

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var doctorsListAdapter: DoctorsListAdapter

    private lateinit var networkHelper: NetworkHelper

    private lateinit var markDoctorDialog: MarkDoctorDialog
    private lateinit var markStatusDialog: MarkedStatusDialog
    private lateinit var updateDoctorDialog: UpdateDoctorDialog

    private var retryCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_doctors, container, false)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.reset()
//        LoginData.saveRepresentativeId(requireContext(),1)

        initViews()
        editTextListener()
        observers()
//        getAreasList()

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        getLocation()
        checkInternetandCallAPI()
    }

    private fun initViews() {
        networkHelper = NetworkHelper(requireContext())
        markDoctorDialog =
            MarkDoctorDialog(layoutInflater, requireContext(), ::markDoctorAsVisitedtoDB)
        markStatusDialog = MarkedStatusDialog(layoutInflater, requireContext())
        updateDoctorDialog =
            UpdateDoctorDialog(layoutInflater, requireContext(), ::onUpdateDoctorClicked)
    }

    private fun markDoctorAsVisitedtoDB(doctor: Doctor) {
        Log.e("Test", "Doctor Marked ${doctor.DoctorName}")

        if (LocationData.isLocationFetched) {
            val markVisitData = MarkVisitData()
            markVisitData.RepId = LoginData.getRepresentativeId(requireContext())
            markVisitData.CustomerId = doctor.DoctorId
            markVisitData.Latitude = LocationData.latitude
            markVisitData.Longitude = LocationData.longitude
            markVisitData.ContactNumber = doctor.ContactNumber1
            markVisitData.giftGiven=doctor.giftGiven

            markStatusDialog.openMarkDoctorDialog(doctor)
            homeViewModel.markVisit(markVisitData)
        } else {
            getLocation()
            UtilFunctions.showToast(requireContext(), "Please allow Location")
        }

//        dummyDelay()

    }

    private fun dummyDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            markStatusDialog.hidePB()
        }, 3000)
    }

    private fun checkInternetandCallAPI() {

        if (networkHelper.isNetworkConnected()) {
            getAreasList()
//            getDoctorsList()
        } else
            UtilFunctions.showToast(requireContext(), "Check Your Internet Connection")
    }

    private fun observers() {

        homeViewModel.getDoctorsList().observe(requireActivity()) {

            doctorsList = it.DoctorsList

            if (it.code == 200) {
                try {
                    initRecyclerView()
                    hideShimmer()
//                    binding.pbProcessing.visibility = View.INVISIBLE

                } catch (e: Exception) {
                    UtilFunctions.showToast(
                        requireContext(),
                        "Server Not Responding! Please Restart"
                    )
                }

            } else if (it.code == 199) {
                UtilFunctions.showToast(requireContext(), "No Doctors Found")
            } else if (it.code == -1) {
                UtilFunctions.showToast(requireContext(), "Take Longer Time...\n Please Wait...")
                retryAPICall()
            }

        }

        homeViewModel.getUpdateDoctorResponse().observe(requireActivity()) {
            if (it.code != 195) {
                if (it.code == 200) {
                    UtilFunctions.showToast(requireContext(), "Update Sucessfull")
                    updateDoctorDialog.closeDialog()
                    getDoctorsList(SelectedArea.areaId)
                }
            }
        }

        homeViewModel.markVisitResponse().observe(requireActivity()) {
            if (it.code != 195) {
                if (it.code == 200) {
                    markStatusDialog.hidePB()
                    getDoctorsList(SelectedArea.areaId)
//                    UtilFunctions.showToast(requireContext(),it.message)
                }
            }
        }

        homeViewModel.getAreasListResponse().observe(requireActivity()) {

            areasList = it.areaList

            if (it.code != 195) {
//                UtilFunctions.showToast(requireContext(),"No areas")

                if (it.code == 200) {
                    if(areasList.isNotEmpty()) {
                        val areaId = areasList[0].areaId
                        SelectedArea.areaId = areaId!!.toInt()
                        getDoctorsList(areaId!!.toInt())
                        initAreaSpinner()
                    }else{
                        UtilFunctions.showToast(requireContext(),"No areas")
                    }
                }else{
                    hideShimmer()
                    UtilFunctions.showToast(requireContext(),"No areas Found")
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


    private var isFilteredList = false

    private fun editTextListener() {

        binding.searchBar.doOnTextChanged { text, _, _, _ ->

            Log.e("Test", text.toString())

            if (text != null) {
                isFilteredList = if (text.isEmpty()) {
                    initRecyclerView()
                    false
                } else {
                    filterItems(text.toString())
                    true
                }
            }
        }

    }

    private var filteredProducts: ArrayList<Doctor> = ArrayList()

    private fun filterItems(text: String) {

        filteredProducts.clear()

        for (localItems in doctorsList) {

            val extractedName = localItems.DoctorName.lowercase().substring(4)

            if (extractedName.startsWith(text.lowercase())) {
                filteredProducts.add(localItems)
            }
        }

        initRecyclerViewFilteredList()
    }

    private fun initRecyclerViewFilteredList() {

        doctorsListAdapter = DoctorsListAdapter(
            filteredProducts,
            requireContext(),
            ::onMarkSelected,
            ::openUpdateDoctorDialog
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            adapter = doctorsListAdapter
        }

    }


    override fun onResume() {
        super.onResume()

        binding.searchBar.text.clear()
    }

    private fun getDoctorsList(areaId: Int) {
//        binding.pbProcessing.visibility= View.VISIBLE
        showShimmer()
        doctorsList.clear()

            homeViewModel.postProductsList(
                LoginData.getRepresentativeId(requireContext()),
                areaId.toInt()
            )

    }

    private fun getAreasList()
    {
        areasList.clear()
        homeViewModel.getAreasList(LoginData.getRepresentativeId(requireContext()))
    }


    private fun initRecyclerView() {

        doctorsListAdapter = DoctorsListAdapter(
            doctorsList,
            requireContext(),
            ::onMarkSelected,
            ::openUpdateDoctorDialog
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            adapter = doctorsListAdapter
        }

    }

    private var doctorsList: ArrayList<Doctor> = ArrayList()

    private var areasList: ArrayList<AreaItem> = ArrayList()

    private fun onMarkSelected(doctor: Doctor) {
        markDoctorDialog.openMarkDoctorDialog(doctor)
    }

    private fun openUpdateDoctorDialog(doctor: Doctor) {
        updateDoctorDialog.openUpdateDoctorDialog(doctor)
    }

    private fun onUpdateDoctorClicked(doctor: Doctor) {
        postUpdateDoctorsDetails(doctor)
    }

    private fun postUpdateDoctorsDetails(doctor: Doctor) {
        updateDoctorDialog.showPB()
        homeViewModel.postUpdateDoctorsDetails(doctor)
    }

    private fun getLocation() {
        Log.e("Test", "Getting Location")

        if (checkLocationPermission()) {
            requestLocationUpdates()
        } else {
            requestLocationPermission()
        }
    }

    val LOCATION_PERMISSION_REQUEST_CODE = 100

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates()
            } else {
                UtilFunctions.showToast(requireContext(), "Location permission denied")
//                binding.tvCurrentLocation.text = "Location permission denied"
            }
        }
    }

    private lateinit var mFusedLocationClient: FusedLocationProviderClient


    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        mFusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude


                    LocationData.isLocationFetched = true

                    LocationData.latitude = latitude.toString()
                    LocationData.longitude = longitude.toString()

                    //current location
//                    currentLocation = "" + latitude + "," + longitude + ""
//                    enableSubmitButton()

                    val locationText = "Latitude: $latitude, Longitude: $longitude"

                    Log.e("Test", "$locationText");

//                    binding.tvCurrentLocation.text = locationText
//                    binding.tvCurrentLocation.text = getAddressFromLocation(requireActivity(), latitude, longitude).toString()

                } else {

//                    UtilFunctions.showToast(requireContext(),"Please turn ON Location")

                    checkAndTurnOnLocation()
                    //                    binding.tvCurrentLocation.text = "Location not available"
                }
            }
            .addOnFailureListener { e ->

//                binding.tvCurrentLocation.text = "Error getting location: ${e.message}"
            }
    }

    private fun checkAndTurnOnLocation() {
        val locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Location services are not enabled, show the location settings intent
            val settingsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(settingsIntent)
        } else {
            // Location services are already enabled
//            UtilFunctions.showToast(requireContext(),"Location is already turned on")
        }
    }

    private fun showShimmer() {
        binding.loadingShimmer.shimmerLayout.startShimmer()

        binding.loadingShimmer.shimmerLayout.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE


    }

    private fun hideShimmer() {
        binding.loadingShimmer.shimmerLayout.stopShimmer()

        binding.loadingShimmer.shimmerLayout.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE

    }

    private fun initAreaSpinner(
    ) {

        val newItem = AreaItem(
            "-1",
            "-1",
            "-1",
            "Select",
            "-1",
        )

        val newAcList = listOf(newItem) + areasList
        val adapter = AreaAdapter(requireContext(), android.R.layout.simple_spinner_item, areasList)

        binding.spSelectArea.adapter = adapter
        binding.spSelectArea.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val areaId = areasList[position].areaId
                SelectedArea.areaId = areaId!!.toInt()
                Log.e("Test","Selected Position $position")

                getDoctorsList(areaId!!.toInt())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (optional)
            }
        }
    }


}

object SelectedArea{
    var areaId = 0
}