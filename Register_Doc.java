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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.security.PublicKey;


public class Register_Doc extends AppCompatActivity {
    Button btnsubmet;
    EditText etname,etmail,etaddress,etphone,etmembership,etpass,etrepass;
    public String name,mail,address,phone,pass,membership,repass,spec,area,gender;

    RadioButton rdmale,rdfemale;
    Spinner spSpeci,spAreaa;

    private static String SOAP_ACTION = "http://tempuri.org/insertDoctor";
    private static String NAMESPACE = "http://tempuri.org/";
    private static String METHOD_NAME = "insertDoctor";
    private static String URL = "http://tabeb.somee.com/webservice.asmx?WSDL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__doc);


        rdmale=(RadioButton)findViewById(R.id.rdmale);
        rdfemale=(RadioButton)findViewById(R.id.rdfemale);

        etname=(EditText)findViewById(R.id.etnamed);
        etmail=(EditText)findViewById(R.id.etMaild);
        etaddress=(EditText)findViewById(R.id.etAddressd);
        etphone=(EditText)findViewById(R.id.etPhoned);
        etmembership=(EditText)findViewById(R.id.etMembership);
        etpass=(EditText)findViewById(R.id.etPassd);
        etrepass=(EditText)findViewById(R.id.etRePassd);


        spSpeci=(Spinner)findViewById(R.id.spSpeci);
        String[] list=getResources().getStringArray(R.array.speciality);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_l2,R.id.txt2,list);
        spSpeci.setAdapter(adapter);


        spAreaa=(Spinner)findViewById(R.id.spArea);
        String[] list2=getResources().getStringArray(R.array.area);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,R.layout.spinner_l2,R.id.txt2,list2);

        spAreaa.setAdapter(adapter2);


        btnsubmet=(Button) findViewById(R.id.btnRegDocDone);
        btnsubmet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register_Doc.this,Doc_Display.class);
                startActivity(intent);


                name = etname.getText().toString();
                mail = etmail.getText().toString();
                address = etaddress.getText().toString();
                phone = etphone.getText().toString();
                membership = etmembership.getText().toString();
                pass = etpass.getText().toString();
                repass = etrepass.getText().toString();

                spec = spSpeci.getSelectedItem().toString();
                area = spAreaa.getSelectedItem().toString();

                if(rdmale.isChecked() == true)
                    gender = "1";
                else
                    gender = "2";

                new LongOperation().execute("");

            }
        });

    }



    private class LongOperation extends AsyncTask<String, Void, String> {



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
                request.addProperty("membership",membership);
                request.addProperty("speciality",spec);
                request.addProperty("area",area);
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
                                    Intent i = new Intent(Register_Doc.this,MainActivity.class);
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
