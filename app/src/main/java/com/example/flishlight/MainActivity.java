package com.example.flishlight;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.security.Policy;

public class MainActivity extends AppCompatActivity {
    EditText time;
    TextView output;
    Context context = this;
    boolean IsFlashAviable = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dexter.withContext(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        IsFlashAviable = true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(context, "Camera permition is requared", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                })
                .check();
        time = (EditText) findViewById(R.id.editTextTime);
        output = (TextView) findViewById(R.id.textView);


    }
    public  void onStartPressed(View view){
        if(IsFlashAviable){
            CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            //Add flashlight logic


            int timeInMinutes = Integer.parseInt(time.getText().toString());
            new CountDownTimer(timeInMinutes * 60*1000, 1000) {

                public void onTick(long millisUntilFinished) {
                    output.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {

                }
            }.start();
        }
        else{
            Toast.makeText(context, "Camera permition is requared", Toast.LENGTH_SHORT).show();
        }




    }

}