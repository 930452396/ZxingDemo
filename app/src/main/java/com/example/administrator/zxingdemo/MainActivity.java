package com.example.administrator.zxingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    @Bind(R.id.btn_open_scan)
    Button btnOpenScan;
    @Bind(R.id.btn_open_chose)
    Button btnOpenChose;

    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        rxPermissions = new RxPermissions(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE:
                    Log.e("", "onActivityResult: "+data.getExtras().getString("result"));
                    break;
            }
        }
    }

    @OnClick({R.id.btn_open_scan, R.id.btn_open_chose})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_scan:
                Toast.makeText(this, "点击饿了", Toast.LENGTH_SHORT).show();
//        rxPermissions .request(Manifest.permission.CAMERA)
//                .subscribe(granted -> {
//                    if (granted) { // Always true pre-M
//                        // I can control the camera now
//                    } else {
//                        // Oups permission denied
//                    }
//                });
                Intent intent = new Intent(getApplication(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.btn_open_chose:
                break;
        }
    }


}
