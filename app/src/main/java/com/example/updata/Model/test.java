package com.example.updata.Model;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class test {
    public static void main(String[] args) {
        Upload.uploadImage(new File("C:\\Users\\misaka\\Desktop\\realDonaldTrump.jpg"), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                System.out.println(body);
                Member request = new Gson().fromJson(body,Member.class);
                System.out.println(request.getName());
            }
        });
    }
}
