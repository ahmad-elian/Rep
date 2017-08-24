package com.example.alifaqeeh.graduation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Patient_Display extends AppCompatActivity {
Spinner spSpeciality,spArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__display);

        spSpeciality=(Spinner)findViewById(R.id.speciality);
        String[] list=getResources().getStringArray(R.array.speciality);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,list);
        spSpeciality.setAdapter(adapter);






        spArea=(Spinner)findViewById(R.id.area2);
        String[] list2=getResources().getStringArray(R.array.area);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,list2);
        spArea.setAdapter(adapter2);

    }
}
