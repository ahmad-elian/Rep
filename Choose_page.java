package com.example.alifaqeeh.graduation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class Choose_page extends Activity {
    Button bL,bRD,bRP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_page);


        bL=(Button)findViewById(R.id.btnLogin);
        bRD=(Button)findViewById(R.id.btnRegDoc);
        bRP=(Button)findViewById(R.id.btnRegPat);



        bL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Choose_page.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        bRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Choose_page.this,Register_patient.class);
                startActivity(intent);
            }
        });

        bRD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Choose_page.this,Register_Doc.class);
                startActivity(intent);
            }
        });
    }

    }
