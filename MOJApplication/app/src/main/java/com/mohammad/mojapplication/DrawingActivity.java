package com.mohammad.mojapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.mohammad.mojapplication.Objects.Service;

import java.util.Random;


public class DrawingActivity extends AppCompatActivity{



    private DrawingV drawingV;
    private Button btnAccept, btnReset;
    private LinearLayout loButtons;
    private MOJManager mojManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mojManager = MOJManager.getMOJManager(this);

        Service service = mojManager.findServiceById(getIntent().getStringExtra("serviceID"));
        service.setServiceStatus("Awaiting Signature From User");
        mojManager.updateService(service);

        setContentView(R.layout.drawing);
        drawingV = (DrawingV) findViewById(R.id.drawingV);
        loButtons = (LinearLayout) findViewById(R.id.loDrawing);
        btnAccept = (Button) findViewById(R.id.btnConfirmSign);
        btnReset = (Button) findViewById(R.id.btnReset);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(DrawingActivity.this, MainActivity.class);

                i.putExtra("userID", getIntent().getStringExtra("userID"));

                Service service = mojManager.findServiceById(getIntent().getStringExtra("serviceID"));
                service.setServiceStatus("Waiting Approval...");

                mojManager.updateService(service);

                drawingV.setDrawingCacheEnabled(true);
                saveImage();
                drawingV.destroyDrawingCache();
                startActivity(i);
                DrawingActivity.this.finish();
            }
        });



        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingV.startNew();
            }
        });

    }


    public void saveImage() {
        Random ran = new Random();
        int rand = ran.nextInt(999999 - 91111) + 91111;

        String imgSaved = MediaStore.Images.Media.insertImage(
                getContentResolver(), drawingV.getDrawingCache(),
                rand+".png", "drawing");
    }






    }


