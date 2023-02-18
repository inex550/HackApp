package ru.faaen.hackapp.features.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.faaen.hackapp.R

class MapsFragment : Fragment(), GoogleMap.OnMarkerClickListener {

    private val callback = OnMapReadyCallback { googleMap ->

        for (i in cities.indices) {
            var city = LatLng(cities[i].latitude, cities[i].longitude)
            googleMap.addMarker(
                MarkerOptions()
                    .position(city)
                    .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_map))
                    .title(cities[i].name)
                    .snippet(cities[i].description)
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(city))
            googleMap.setOnMarkerClickListener(this)

        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val dialog = BottomSheetDialog(requireContext())

        val view = layoutInflater.inflate(R.layout.button_sheet, null)

        val btnClose = view.findViewById<Button>(R.id.bt_close_map)
        val btnMore = view.findViewById<Button>(R.id.bt_more_map)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title_map)
        val tvDescription = view.findViewById<TextView>(R.id.tv_description_map)
        tvTitle.text = marker.title
        tvDescription.text = marker.snippet
        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        btnMore.setOnClickListener {
            // Todo about click()
        }

//        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()

        return true

    }
}