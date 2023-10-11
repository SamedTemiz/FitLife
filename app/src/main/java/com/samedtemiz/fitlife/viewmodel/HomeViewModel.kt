package com.samedtemiz.fitlife.viewmodel


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.samedtemiz.fitlife.data.api.GoogleApi
import com.samedtemiz.fitlife.data.api.LatLngData
import com.samedtemiz.fitlife.data.api.LocationRequest
import com.samedtemiz.fitlife.data.api.RetrofitClient
import com.samedtemiz.fitlife.data.model.air.AirQualityResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _location = MutableLiveData<LocationData>()
    val location: LiveData<LocationData> = _location

    private val locationManager =
        com.samedtemiz.fitlife.components.LocationManager(context, 10000, 15f)
    private val geocoder = Geocoder(context, Locale.getDefault())

    val googleService = RetrofitClient.getGoogleRetrofit().create(GoogleApi::class.java)

    private val _airQualityData = MutableLiveData<AirQualityResponse>()
    val airQualityData: LiveData<AirQualityResponse> = _airQualityData

    var isProcess: Boolean = false
    init {
        isProcess = true

        locationManager.startLocationTracking()
        locationManager.locationData.observeForever { location ->
            // Konum değiştiğinde LiveData'yı güncelleyin
            location?.let {loc ->
                try {
                    // Air Quality
                    getAirQualityData(loc.latitude, loc.longitude)

                    val addressList: MutableList<Address>? =
                        geocoder.getFromLocation(loc.latitude, loc.longitude, 1)

                    if (addressList != null && addressList.isNotEmpty()) {
                        val address = addressList[0]

                        _location.value = LocationData(
                            country = address.countryName,
                            city = address.adminArea,
                            district = address.subAdminArea,// Doğru şekilde "address.locality" kullanın
                            latlng = loc
                        )
                    } else {
                        Log.e("Geocoder", "Geocoder address is null or empty")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("Geocoder", "Geocoder error: ${e.message}")
                }

            }
        }
    }

    fun getAirQualityData(latitude: Double, longitude: Double){
        val locationRequest = LocationRequest(LatLngData(latitude, longitude))

        val call = googleService.getCurrentConditions(locationRequest, com.samedtemiz.fitlife.BuildConfig.MAPS_API_KEY)

        call.enqueue(object : Callback<AirQualityResponse> {
            override fun onResponse(call: Call<AirQualityResponse>, response: Response<AirQualityResponse>) {
                if (response.isSuccessful) {
                    _airQualityData.value = response.body()
                    Log.e("RESPONSE SUCCESS", response.body()!!.regionCode)
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorBody?.let{
                        Log.e("API Error", errorBody)
                    }
                }
            }

            override fun onFailure(call: Call<AirQualityResponse>, t: Throwable) {
                // Error processing
                val errorMessage = "API Request Error: ${t.message}"
                Log.e("API Error", errorMessage)
            }
        })
    }
}

data class LocationData(
    var country: String,
    var city: String,
    var district: String,
    var latlng: Location
)