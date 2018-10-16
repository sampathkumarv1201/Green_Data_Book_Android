package com.example.sampathkumar.student_details;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {


    Button family_id,mark_id,idproof_id,address_id,logout,personalinfo_id;
    String MyPREFERENCES="Login",Rollno="rollno";

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        family_id=(Button)findViewById(R.id.family);
        mark_id=(Button)findViewById(R.id.mark);
        idproof_id=(Button) findViewById(R.id.idproof);
        address_id=(Button) findViewById(R.id.address);
        logout=(Button) findViewById(R.id.logout);
        personalinfo_id=(Button) findViewById(R.id.personalinfo);


        family_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent family=new Intent(getApplicationContext(),Family_Details.class);
                startActivity(family);
            }
        });

        mark_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mark=new Intent(getApplicationContext(),Family_Details.class);
                startActivity(mark);
            }
        });

        address_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent address=new Intent(getApplicationContext(),Address_Details.class);
                startActivity(address);
            }
        });

        idproof_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent idproof=new Intent(getApplicationContext(),Id_Proof_Details.class);
                startActivity(idproof);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout=new Intent(getApplicationContext(),Login.class);
                startActivity(logout);
            }
        });

        personalinfo_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Intent logout=new Intent(getApplicationContext(),Personal_info.class);
                startActivity(logout);
            }
        });
    }
}
