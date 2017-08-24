package com.example.alifaqeeh.graduation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

public class Register_patient extends AppCompatActivity {
       Spinner m,y,d;
       Button  b1;

    EditText etname,etmail,etaddress,etphone, etpass,etrepass;
    String name,mail,address,phone,pass,gender,year,day,month;
    RadioButton rdmale,rdfemale;

    private static String SOAP_ACTION = "http://tempuri.org/insertPatients";
    private static String NAMESPACE = "http://tempuri.org/";
    private static String METHOD_NAME = "insertPatients";
    private static String URL = "http://tabeb.somee.com/webservice.asmx?WSDL";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);


       rdmale=(RadioButton)findViewById(R.id.rdmale);
       rdfemale=(RadioButton)findViewById(R.id.rdfemale);

       y=(Spinner)findViewById(R.id.s1);
        m=(Spinner)findViewById(R.id.s2);
        d=(Spinner)findViewById(R.id.s3);

        List<String> users = new ArrayList<String>();
        for (int i=2010;i>1950;i--)
        {
            users.add(i+"");

        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, users);

        y.setAdapter(dataAdapter);



        String[] list =getResources().getStringArray(R.array.month);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list );
        m.setAdapter(adapter );



          List<String> day2 = new ArrayList<String>();
        for (int j=1;j<32;j++)
        {
            day2.add(j+"");

        }
        ArrayAdapter<String> aadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, day2);

        d.setAdapter(aadapter);




        etname=(EditText)findViewById(R.id.etnamep );
        etmail=(EditText)findViewById(R.id.etMailp );
        etaddress=(EditText)findViewById(R.id.etAddressp );
        etphone=(EditText)findViewById(R.id.etPhonep );
        etpass=(EditText)findViewById(R.id.etPassp );
        etrepass=(EditText)findViewById(R.id.etRePassp );




        b1=(Button) findViewById(R.id.btnRegPatDone);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register_patient.this,Patient_Display.class);
                startActivity(intent);


                name=etname.getText().toString();
                mail=etmail.getText().toString();
                address=etaddress.getText().toString();
                phone=etphone.getText().toString();
                pass=etpass.getText().toString();

                 month=m.getSelectedItem().toString();

                day=d.getSelectedItem().toString();
                year=y.getSelectedItem().toString();


               if(rdmale.isChecked() == true)
                    gender = "1";
                else
                    gender = "2";

                new  LongOperation2().execute("");

            }
        });


    }


    private class LongOperation2 extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... params) {
            try {


                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


                //Use this to add parameters
                request.addProperty("name",name);
                request.addProperty("Email",mail);
                request.addProperty("address",address);
                request.addProperty("phone",phone);
                request.addProperty("pass",pass);
                request.addProperty("gender",gender);
                request.addProperty("Age","01/01/1999");

                //Declare the version of the SOAP request
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.setOutputSoapObject(request);
                envelope.dotNet = true;

                try {
                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                    //this is the actual part that will call the webservice
                    androidHttpTransport.call(SOAP_ACTION, envelope);

                    // Get the SoapResult from the envelope body.
                    SoapObject result = (SoapObject)envelope.bodyIn;

                    if(result != null)
                    {
                        if(result.getProperty(0).toString().equals("1"))
                        {
                            runOnUiThread(new Runnable() {

                                public void run() {

                                    Toast.makeText(getApplicationContext(), "تم التسجيل بنجاح يتم الان التحقق ... شكرا لك", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(Register_patient.this,MainActivity.class);
                                    startActivity(i);

                                }
                            });
                        }
                        else if(result.getProperty(0).toString().equals("0"))
                        {
                            runOnUiThread(new Runnable() {

                                public void run() {

                                    Toast.makeText(getApplicationContext(),"هنالك خطأ ... يرجى المحاولة لاحقا",Toast.LENGTH_LONG).show();

                                }
                            });

                        }
                        else if(result.getProperty(0).toString().equals("2"))
                        {
                            runOnUiThread(new Runnable() {

                                public void run() {

                                    Toast.makeText(getApplicationContext(),"هذا البريد الالكتروني او رقم الهاتف مسجل به مسبقا",Toast.LENGTH_LONG).show();

                                }
                            });

                        }

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "No Response",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            catch (Exception c)
            {
                Toast.makeText(getApplicationContext(),"fffff",Toast.LENGTH_LONG).show();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {


        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}
