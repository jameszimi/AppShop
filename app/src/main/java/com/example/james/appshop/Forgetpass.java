package com.example.james.appshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Forgetpass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);

        Button buttonCancle = (Button) findViewById(R.id.cancle);
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent caclebutton = new Intent(Forgetpass.this, Login.class);
                startActivity(caclebutton);
                finish();
            }
        });
    }
}
