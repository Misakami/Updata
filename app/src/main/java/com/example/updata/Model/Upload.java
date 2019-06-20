package com.example.updata.Model;


import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class Upload {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.MINUTES)
            .build();

    public static void uploadImage(File outputImage , Callback callback) {
        //2.创建RequestBody
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_PNG, outputImage);

        //3.构建MultipartBody
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "testImage.jpg", fileBody)
                .build();

        //4.构建请求
        Request request = new Request.Builder()
                .url("http://192.168.1.110:8000/tools/social_align/test")
                .post(requestBody)
                .build();

        //5.发送请求
        client.newCall(request).enqueue(callback);
    }
}
