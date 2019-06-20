package com.example.updata.Model;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class CreateJour implements I_CreateP {

    @Override
    public void getImage(Activity context, int i) {
        Uri imageUri;
        if (i == Const.Chose_Image) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            context.startActivityForResult(intent, Const.Chose_Image);
        } else if (i == Const.Take_Photo) {
            File outputImage = new File(context.getExternalCacheDir(), "output_image.jpg");
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT >= 24) {
                imageUri = FileProvider.getUriForFile(context, "take_photo", outputImage);
            } else {
                imageUri = Uri.fromFile(outputImage);
            }
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            context.startActivityForResult(intent, Const.Take_Photo);
        }
    }
}
