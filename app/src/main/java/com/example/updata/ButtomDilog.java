package com.example.updata;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.updata.Model.Const;
import com.example.updata.Model.CreateJour;


public class ButtomDilog {
    private Activity context;
    private AlertDialog.Builder builder;
    private View view;
    private Button take_photo;
    private Button choose_photo;
    private Button cancel;
    private AlertDialog dialog;
    private CreateJour createJour;

    public ButtomDilog(Activity context) {
        this.context = context;
        builder =new AlertDialog.Builder(context);
        view = LayoutInflater.from(context).inflate(R.layout.layout_buttomstyle,null);
        take_photo = (Button) view.findViewById(R.id.take_photo);
        take_photo.setOnClickListener(take_photo());
        choose_photo = (Button) view.findViewById(R.id.album);
        choose_photo.setOnClickListener(choose_photo());
        cancel = (Button) view.findViewById(R.id.cancel);
        dialog = new AlertDialog.Builder(context).create();
        createJour = new CreateJour();
    }


    public void show(){
        dialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Window window =dialog.getWindow();
        window.setContentView(view);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.animation_buttom);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void close(){
        dialog.dismiss();
    }

    private View.OnClickListener take_photo() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                createJour.getImage(context,Const.Take_Photo);
            }
        };
    }

    private View.OnClickListener choose_photo() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
                createJour.getImage(context, Const.Chose_Image);
            }
        };
    }
}
