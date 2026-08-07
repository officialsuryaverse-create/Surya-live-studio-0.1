package com.surya.livestudio

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CameraProActivity : AppCompatActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var zoomBar: SeekBar
    private var camera: Camera? = null
    private var lensFacing = CameraSelector.LENS_FACING_BACK
    private var portrait = true
    private var flashEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_pro)

        previewView = findViewById(R.id.previewView)
        zoomBar = findViewById(R.id.zoomBar)

        zoomBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, value: Int, fromUser: Boolean) {
                camera?.cameraControl?.setLinearZoom(value / 100f)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        findViewById<Button>(R.id.btnSwitch).setOnClickListener {
            lensFacing =
                if (lensFacing == CameraSelector.LENS_FACING_BACK)
                    CameraSelector.LENS_FACING_FRONT
                else
                    CameraSelector.LENS_FACING_BACK
            startCamera()
        }

        findViewById<Button>(R.id.btnRotate).setOnClickListener {
        findViewById<Button>(R.id.btnFlash).setOnClickListener {
            flashEnabled = !flashEnabled
            camera?.cameraControl?.enableTorch(flashEnabled)
        }

            portrait = !portrait
            requestedOrientation =
                if (portrait)
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                else
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)
        } else {
            startCamera()
        }
    }

    private fun startCamera() {
        val future = ProcessCameraProvider.getInstance(this)

        future.addListener({
            val provider = future.get()

            val preview = Preview.Builder().build()
            preview.surfaceProvider = previewView.surfaceProvider

            val selector = CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build()

            provider.unbindAll()
            camera = provider.bindToLifecycle(this, selector, preview)

        }, ContextCompat.getMainExecutor(this))
    }
}
