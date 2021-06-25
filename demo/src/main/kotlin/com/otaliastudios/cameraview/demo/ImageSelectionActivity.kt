package com.otaliastudios.cameraview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File


class ImageSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selection_layout)
        val recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.setHasFixedSize(true)
        val mFiles = FileUtil.findImageFileInDirectory(
            FileUtil.getRiversideFolder().toString(),
            arrayOf("png", "jpg", "mp4")
        )
        val fileList = ArrayList<String>(mFiles.reversed().toList())
        recyclerView.adapter = GalleryAdapter(this, fileList, object : DeleteListener {
            override fun onFileDeleted(position: Int) {
                File(fileList.get(position)).delete()
                fileList.removeAt(position)
                recyclerView.adapter?.notifyItemRemoved(position)
            }
        })
    }


}

interface DeleteListener {
    fun onFileDeleted(position: Int)
}
