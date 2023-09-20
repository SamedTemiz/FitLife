package com.samedtemiz.fitlife.viewmodel


import android.annotation.SuppressLint
import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.Locale


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _location = MutableLiveData<LocationData>()
    val location: LiveData<LocationData> = _location

    private val locationManager =
        com.samedtemiz.fitlife.components.LocationManager(context, 10000, 15f)
    private val geocoder = Geocoder(context, Locale.getDefault())

    var isProcess: Boolean = false

    init {
        isProcess = true
        locationManager.startLocationTracking()
        locationManager.locationData.observeForever { location ->
            // Konum değiştiğinde LiveData'yı güncelleyin
            location?.let {
                try {
                    val addressList: MutableList<Address>? =
                        geocoder.getFromLocation(it.latitude, it.longitude, 1)

                    if (addressList != null && addressList.isNotEmpty()) {
                        val address = addressList[0]

                        _location.value = LocationData(
                            country = address.countryName,
                            city = address.adminArea,
                            district = address.subAdminArea,// Doğru şekilde "address.locality" kullanın
                            latlng = it
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
}

data class LocationData(
    var country: String,
    var city: String,
    var district: String,
    var latlng: Location
)