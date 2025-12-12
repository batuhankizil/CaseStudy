package com.example.casestudy.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.CircleAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createCircleAnnotationManager

@Composable
fun MapScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                MapView(context).apply {
                    mapboxMap.loadStyle(Style.MAPBOX_STREETS) {}

                    val suluovaPoint = Point.fromLngLat(35.612263, 40.864102)
                    mapboxMap.setCamera(
                        CameraOptions.Builder()
                            .center(suluovaPoint)
                            .zoom(14.0)
                            .build()
                    )

                    val annotationApi = annotations
                    val circleAnnotationManager = annotationApi.createCircleAnnotationManager()

                    val markerPoint = Point.fromLngLat(35.61226281039344, 40.86410170504059)

                    val circleAnnotationOptions = CircleAnnotationOptions()
                        .withPoint(markerPoint)
                        .withCircleRadius(12.0)
                        .withCircleColor("#EF4444")
                        .withCircleStrokeWidth(2.0)
                        .withCircleStrokeColor("#FFFFFF")

                    circleAnnotationManager.create(circleAnnotationOptions)
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        FloatingActionButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            containerColor = Color.White,
            contentColor = Color.Black
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}
