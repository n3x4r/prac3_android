package com.udl.android.thirdapp;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


//https://developer.android.com/training/permissions/requesting.html
public class MainActivity extends Activity implements View.OnClickListener {

    private static final int MYPERMISSIONS_CALL_PHONE = 0;
    private static final int MYPERMISSIONS_SMS = 1;
    private static final int MYPERMISSIONS_EX_STORAGE = 2 ;
    private static final int RESULT_LOAD_IMAGE = 0;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

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
        Button btn11 = (Button) findViewById(R.id.button11);

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
        btn11.setOnClickListener(this);

    }

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
                    button7();
                }
                break;
            case R.id.button8:
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    accessResources(Manifest.permission.SEND_SMS,MYPERMISSIONS_SMS);
                }else {
                    button8();
                }
                break;
            case R.id.button9:
                Toast.makeText(this, "Accediendo a Email", Toast.LENGTH_LONG).show();
                in = new Intent(Intent.ACTION_SEND);
                in.setType("*/*");
                in.putExtra(Intent.EXTRA_EMAIL, new String[] { "sss@udl.cat" });
                in.putExtra(Intent.EXTRA_SUBJECT, "Test Email");
                in.putExtra(Intent.EXTRA_TEXT, "This is a Test");
                startActivity(in);
                break;
            case R.id.button10:
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    accessResources(Manifest.permission.READ_EXTERNAL_STORAGE,MYPERMISSIONS_EX_STORAGE);
                }else {
                    button10();
                }
                break;
            case R.id.button11:
                in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (in.resolveActivity(getPackageManager()) != null) {
                    //startActivity(in);
                    startActivityForResult(in, REQUEST_IMAGE_CAPTURE);

                }
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
                    button7();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            case MYPERMISSIONS_SMS:{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    button8();

                } else {
                    in = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(in, RESULT_LOAD_IMAGE);

                }
                return;
            }

            case MYPERMISSIONS_EX_STORAGE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    button10();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }else if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private void button7(){
        Toast.makeText(this, "Realizando llamada", Toast.LENGTH_LONG).show();
        in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:617773082"));
        startActivity(in);
    }

    private void button8(){
        Toast.makeText(this, "Enviando sms", Toast.LENGTH_LONG).show();
        in = new Intent(Intent.ACTION_SENDTO);
        in.setData(Uri.parse("smsto:" + Uri.encode("646888777")));
        in.putExtra("sms_body", "Hello this is a Test");
        startActivity(in);
    }
    private void button10(){
        Toast.makeText(this, "Accediendo a Galeria", Toast.LENGTH_LONG).show();
        in = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(in, RESULT_LOAD_IMAGE);
    }
}
