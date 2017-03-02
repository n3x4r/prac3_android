package com.udl.android.thirdapp;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Contacts;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


//https://developer.android.com/training/permissions/requesting.html
public class MainActivity extends Activity implements View.OnClickListener {

    private static final int MYPERMISSIONS_CALL_PHONE = 0;
    private static final int MYPERMISSIONS_SMS = 1;
    private Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.button4);
        Button btn5 = (Button) findViewById(R.id.button5);
        Button btn6 = (Button) findViewById(R.id.button6);
        Button btn7 = (Button) findViewById(R.id.button7);
        Button btn8 = (Button) findViewById(R.id.button8);
        Button btn9 = (Button) findViewById(R.id.button9);
        Button btn10 = (Button) findViewById(R.id.button10);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);

    }

    //para sms
    //putextra(Keydelsistema,)+ uri.paser("sms")
    @SuppressWarnings("deprecation")
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.button1:
                Toast.makeText(this, "Seleccionado Localizacion por coordenadas", Toast.LENGTH_LONG).show();
                String lat = "41.60788";
                String lon = "0.623333";
                in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + lat + ',' + lon));
                startActivity(in);
                break;
            case R.id.button2:
                Toast.makeText(this, "Seleccionado Localizacion por direccion", Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + "Carrer de Jaume II, 69, Lleida"));
                startActivity(in);
                break;
            case R.id.button3:
                Toast.makeText(this, "Accediendo a la web", Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.eps.udl.cat/"));
                startActivity(in);
                break;
            case R.id.button4:
                Toast.makeText(this, "Buscando en Google", Toast.LENGTH_LONG).show();
                //in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/search?q=" + "escola politecnica superior UdL"));
                in = new Intent(Intent.ACTION_WEB_SEARCH);
                in.putExtra(SearchManager.QUERY, "escola politecnica superior UdL");
                startActivity(in);
                break;
            case R.id.button5:
                Toast.makeText(this, "Marcando el Tlfn.", Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:666666666" ));
                startActivity(in);
                break;
            case R.id.button6:
                Toast.makeText(this, "Accediendo a contactos", Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_VIEW);
                in.setData(Contacts.People.CONTENT_URI);
                startActivity(in);
                break;
            case R.id.button7:
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    accessResources(Manifest.permission.CALL_PHONE,MYPERMISSIONS_CALL_PHONE);
                }else{
                    Toast.makeText(this, "Realizando llamada", Toast.LENGTH_LONG).show();
                    in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:617773082"));
                    startActivity(in);
                }
                break;
            case R.id.button8:
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    accessResources(Manifest.permission.SEND_SMS,MYPERMISSIONS_SMS);
                }else {
                    Toast.makeText(this, "Enviar sms", Toast.LENGTH_LONG).show();
                    in = new Intent(Intent.ACTION_SENDTO);
                    in.setData(Uri.parse("smsto:" + Uri.encode("97343354")));
                    in.putExtra("sms_body", "Hello this is a Test");
                    startActivity(in);
                }
                break;
            case R.id.button9:
                in = new Intent(Intent.ACTION_SEND);
                in.setType("*/*");
                in.putExtra(Intent.EXTRA_EMAIL, new String[] { "someone@gmail.com" });
                in.putExtra(Intent.EXTRA_SUBJECT, "Test Email");
                in.putExtra(Intent.EXTRA_TEXT, "This is a Test");
                startActivity(in);
                break;
            case R.id.button10:
                in=new Intent(Intent.ACTION_PICK);
                in.setType("image/*");
                startActivity(in);
                break;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MYPERMISSIONS_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(this, "Realizando llamada", Toast.LENGTH_LONG).show();
                    in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:617773082"));
                    startActivity(in);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            case MYPERMISSIONS_SMS:{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(this, "Enviar sms", Toast.LENGTH_LONG).show();
                    in = new Intent(Intent.ACTION_SENDTO);
                    in.setData(Uri.parse("smsto:" + Uri.encode("97343354")));
                    in.putExtra("sms_body", "Hello this is a Test");
                    startActivity(in);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }


            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    public void accessResources(String typeAccess, int constantReference){

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    typeAccess)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{typeAccess},
                        constantReference);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

    }


}
