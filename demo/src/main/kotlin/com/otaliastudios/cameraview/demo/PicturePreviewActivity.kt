package com.otaliastudios.cameraview.demo

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.controls.PictureFormat
import com.otaliastudios.cameraview.size.AspectRatio
import java.io.File

class PicturePreviewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_preview)
        val file = intent.getSerializableExtra("file") as? File
        Glide.with(this)
            .load(file)
            .into(findViewById(R.id.image))
    }
}