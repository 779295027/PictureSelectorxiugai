package com.sss.pictureselectorxiugai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.entity.LocalMedia
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bean = LocalMedia()
        bean.compressPath =
            "https://v3.3asxy.com/attachment/images/2/2019/12/s3ON2OzDGZVgZdYR9zd3cTPURftT2e.jpg"
        bean.path =
            "https://v3.3asxy.com/attachment/videos/2/2019/12/K0allJ495gA4GL2G2dlYRGmaL2G4s0.mp4"
        button.setOnClickListener {
            PictureSelector.create(this).themeStyle(R.style.picture_default_style)
                .loadImageEngine(PictureSelectorGlideEngine.createGlideEngine())
                .openExternalPictureVideo(bean.path, bean.compressPath)
        }

        button2.setOnClickListener {
            val bean = LocalMedia()
            bean.compressPath =
                "https://v3.3asxy.com/attachment/images/2/2019/12/s3ON2OzDGZVgZdYR9zd3cTPURftT2e.jpg"
            bean.path =
                "https://v3.3asxy.com/attachment/images/2/2019/12/s3ON2OzDGZVgZdYR9zd3cTPURftT2e.jpg"

            PictureSelector.create(this).themeStyle(R.style.picture_default_style)
                .loadImageEngine(PictureSelectorGlideEngine.createGlideEngine())
                .setOnImageViewLongClickListener {
                    Log.e("------------------", "----${it.path}----")
                    Toast.makeText(this, "长按", Toast.LENGTH_SHORT).show()
                }
                .openExternalPreview(0, arrayListOf(bean))
        }

    }
}
