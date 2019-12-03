package com.example.activitat6_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CALLBACK_CALENDAR = 30;
    private static final int CALLBACK_CONTACTS = 50;
    private Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permisosCalendar();
        permisosContacts();
        btn1 = (Button) findViewById(R.id.botonCalendar);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {permisosCalendar();
            }
        });

        btn2 = (Button) findViewById(R.id.botonContacts);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {permisosContacts();
            }
        });
    }
    private boolean demanaPermisos(){
        return(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1);
    }
    private void permisosCalendar(){
        if( demanaPermisos() ){
            int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CALENDAR);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.WRITE_CALENDAR},CALLBACK_CALENDAR);
            }
        }
    }
    private void permisosContacts(){
        if( demanaPermisos() ){
            int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CONTACTS);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.WRITE_CONTACTS},CALLBACK_CONTACTS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[]grantResults) {
        switch (requestCode) {
            case CALLBACK_CALENDAR: {
            // Si es cancela la petició l'aray de tornada es buit.
            if (grantResults.length > 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permís concedit
                Toast.makeText(MainActivity.this,"Permiso concedido al calendario",Toast.LENGTH_LONG).show();
            } else {
                // permís denegat
                // Desactivar la funcionalitat relacionada amb el permís
                Toast.makeText(MainActivity.this,"Permiso denegado al calendario",Toast.LENGTH_LONG).show();
            }
            return;
            }
            case CALLBACK_CONTACTS: {
                // Si es cancela la petició l'aray de tornada es buit.
                if (grantResults.length > 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permís concedit
                    Toast.makeText( MainActivity.this,"Permiso concedido a contacts",Toast.LENGTH_LONG).show();
                } else {
                    // permís denegat
                    // Desactivar la funcionalitat relacionada amb el permís
                    Toast.makeText(MainActivity.this,"Permiso denegado a contacts",Toast.LENGTH_LONG).show();
                }
                return;
            }

        // altres CASE per altres permisos
        }
    }
}
