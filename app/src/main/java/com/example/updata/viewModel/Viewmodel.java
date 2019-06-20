package com.example.updata.viewModel;

import android.app.Activity;
import android.app.Dialog;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.updata.ButtomDilog;
import com.example.updata.Model.FileUtils;
import com.example.updata.Model.Member;
import com.example.updata.Model.Upload;
import com.example.updata.R;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;


public class Viewmodel extends BaseObservable {
    private Activity context;
    public String button = "点击上传头像";
    private File file;
    public ObservableField<String> text = new ObservableField<>("");
    public ObservableField<Object> image = new ObservableField<>();


    public Viewmodel(Activity context) {
        this.context = context;
        image.set(R.mipmap.upload);
    }

    public void log() {
        ButtomDilog dilog = new ButtomDilog(context);
        dilog.show();
    }

    public void update() {
        Log.e(TAG, "update: ");
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog);
        dialog.show();
        if (file != null) {
            Upload.uploadImage(file, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.set(e.getMessage());
                            dialog.dismiss();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 500){
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context,"图片错误",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });
                    }
                    String body = response.body().string();
                    Member request = new Gson().fromJson(body, Member.class);
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.set("姓名： " + request.getName()
                                    + "\n描述：" + request.getDescription()
                                    + "\n粉丝数：" + request.getFollowers_count()
                                    + "\n朋友数：" + request.getFriends_count());
                            dialog.dismiss();
                        }
                    });
                }
            });
        } else {
            dialog.dismiss();
        }
    }

    public void setUrl(Object url) {
        Log.d("MyViewModel", "url" + url.toString());
        if (url instanceof Uri) {
            Log.d("MyViewModel", "uri");
            file = new File(Objects.requireNonNull(FileUtils.getFilePathByUri(context, (Uri) url)));
        } else {
            file = (File) url;
        }
        image.set(null);
        image.set(url);
    }

}
