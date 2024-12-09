package com.example.sms_project;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //propeiéte
    /*private EditText phone,message;*/
    private Button envoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        initActivity();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void initActivity(){
        //récuperation objets graphiques
        Log.i("avant","avant");
        /*phone = (EditText) findViewById(R.id.txtPhone);
        message = (EditText) findViewById(R.id.txtMessage);*/
        envoi = (Button) findViewById(R.id.btnEnvoi);
        createOnClickEnvoiButton();
        Log.i("apres","apres");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("Permission", "Permission SEND_SMS accordée");
            } else {
                Log.i("Permission", "Permission SEND_SMS refusée");
            }
        }
    }


    private void createOnClickEnvoiButton(){
        envoi.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view){
                SmsManager.getDefault().sendTextMessage("5554",null,"abc", null,null);

            }
        });


    }
}