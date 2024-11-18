package com.wing.httpdemo;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.wing.http.XHttp;
import com.wing.http.XHttpSDK;
import com.wing.http.exception.ApiException;
import com.wing.http.subsciber.BaseSubscriber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        XHttpSDK.init(this.getApplication());
        XHttpSDK.debug("XHttp3");
        XHttpSDK.setBaseUrl("http://192.168.31.220:48080/");

        XHttp.post("/app-api/member/auth/send-sms-code")
                .upJson("{\"mobile\":\"18830817137\", \"scene\":\"1\"}")
                .headers("tenant-id", "1")
                .execute(Boolean.class).subscribe(new BaseSubscriber<Boolean>() {
                    @Override
                    protected void onError(ApiException e) {
                        Log.i("TAG111111111", "onError: " + e.getDetailMessage());
                    }

                    @Override
                    protected void onSuccess(Boolean loginResult) {
                        Log.i("TAG111111111", "onSuccess: " + loginResult);
                    }
                });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                XHttp.post("/app-api/member/auth/sms-login")
                        //使用的是 application/json;charset=UTF-8
                        .upJson("{\"mobile\":\"18830817137\", \"code\":\"9999\"}")
//                        .upJson(JsonUtil.toJson(new LoginPasswordRequest()))
                        //使用的是 application/x-www-form-urlencoded;charset=UTF-8
                        .params("mobile","18830817137")
                        .params("code","9999")
                        //添加请求头
                        .headers("tenant-id", "1")
                        .execute(LoginResult.class).subscribe(new BaseSubscriber<LoginResult>() {
                            @Override
                            protected void onError(ApiException e) {
                                Log.i("TAG22222", "onError: " + e.getDetailMessage());
                            }

                            @Override
                            protected void onSuccess(LoginResult loginResult) {
                                Log.i("TAG22222", "onSuccess: " + loginResult.getUserId());
                            }
                        });
            }
        }, 3000);


    }
}