package com.example.mohamedabdelaziz.project;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MainActivity extends ActivityGroup {

    private Button mButton_open_imo, mButton_open_line, mButton_screenShot;
    private ImageView mImageView_screen_image;
    private Bitmap bitmap = null;
    static final String IMO_PACKAGE = "com.imo.android.imoim";
    static final String LINE_PACKAGE = "jp.naver.line.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton_open_imo = findViewById(R.id.open_imo_button);
        mButton_open_line = findViewById(R.id.open_line_button);
        mButton_screenShot = findViewById(R.id.takeScreen_btn);
        mImageView_screen_image = findViewById(R.id.screen_image);
        mButton_open_imo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_app(IMO_PACKAGE);
            }
        });
        mButton_screenShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TakeScreenShot();
            }
        });
        mButton_open_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_app(LINE_PACKAGE);
            }
        });
        ///
        //get all installed packages
//        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
//        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0);
//        for (int i = 0; i < pkgAppsList.size(); i++) {
//            Log.d("PACKAGE  ", pkgAppsList.get(i).toString());
//        }
        ////
    }

    private void open_app(String packg) {
        PackageManager manager = getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(packg);
            if (i == null) {
                Toast.makeText(this, "app not installed", Toast.LENGTH_SHORT).show();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(i);
        } catch (Exception e) {
        }
    }

    private void TakeScreenShot() {
        bitmap = Bitmap.createBitmap(getWindow().getDecorView().findViewById(android.R.id.content).getWidth(),
                getWindow().getDecorView().findViewById(android.R.id.content).getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        getWindow().getDecorView().findViewById(android.R.id.content).draw(canvas);
        mImageView_screen_image.setImageBitmap(bitmap);
    }
}
