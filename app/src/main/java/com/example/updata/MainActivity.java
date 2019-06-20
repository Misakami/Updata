package com.example.updata;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.updata.Model.Const;
import com.example.updata.Model.Upload;
import com.example.updata.viewModel.Viewmodel;
import com.example.updata.databinding.ActivityMainBinding;

import java.io.File;

import okhttp3.OkHttpClient;


public class MainActivity extends BaseActivity{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setViewModel(new Viewmodel(MainActivity.this));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Const.Chose_Image) {
            if (data == null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"没有选择图片",Toast.LENGTH_SHORT).show();
                    }
                });
                return;
            }
            Uri uri = data.getData();
            binding.getViewModel().setUrl(uri);
        } else if (requestCode == Const.Take_Photo) {
            File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
            if (outputImage.length() == 0) {
                return;
            }
            binding.getViewModel().setUrl(outputImage);
        }
    }
}
