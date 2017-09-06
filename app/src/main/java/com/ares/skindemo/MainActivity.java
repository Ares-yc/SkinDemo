package com.ares.skindemo;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn;
    private ImageView iamge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        iamge = (ImageView) findViewById(R.id.image);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            String skinPath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"skinplug01.skin";
            method.invoke(assetManager,skinPath);
            Resources resources = new Resources(assetManager,getResources().getDisplayMetrics(),getResources().getConfiguration());
            int drawableId = resources.getIdentifier("bg","drawable","com.ares.skinplug01");
            Drawable drawable = resources.getDrawable(drawableId);
            iamge.setImageDrawable(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
