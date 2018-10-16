package com.example.sampathkumar.student_details;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Address_Details extends AppCompatActivity {



    FirebaseFirestore mfire;

    TextInputEditText c_door_id,c_address1_id,c_address2_id,c_postalarea_id,c_taluk_id,c_district_id,c_pincode_id;
    TextInputEditText p_door_id,p_address1_id,p_address2_id,p_postalarea_id,p_taluk_id,p_district_id,p_pincode_id;
    TextInputEditText nationality_id,state_id;
    Button submit_id;

    String c_door,c_address1,c_address2,c_postalarea,c_taluk,c_district,c_pincode;
    String p_door,p_address1,p_address2,p_postalarea,p_taluk,p_district,p_pincode;
    String nationality,state;
    String MyPREFERENCES="Login";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address__details);

        mfire = FirebaseFirestore.getInstance();

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        c_door_id = (TextInputEditText) findViewById(R.id.c_door);
        c_address1_id = (TextInputEditText) findViewById(R.id.c_address1);
        c_address2_id = (TextInputEditText) findViewById(R.id.c_address2);
        c_postalarea_id = (TextInputEditText) findViewById(R.id.c_postaddress);
        c_taluk_id = (TextInputEditText) findViewById(R.id.c_thaluk);
        c_district_id = (TextInputEditText) findViewById(R.id.c_district);
        c_pincode_id = (TextInputEditText) findViewById(R.id.c_pincode);
        p_door_id = (TextInputEditText) findViewById(R.id.p_door);
        p_address1_id = (TextInputEditText) findViewById(R.id.p_address1);
        p_address2_id = (TextInputEditText) findViewById(R.id.p_address2);
        p_postalarea_id = (TextInputEditText) findViewById(R.id.p_postaddress);
        p_taluk_id = (TextInputEditText) findViewById(R.id.p_thaluk);
        p_district_id = (TextInputEditText) findViewById(R.id.p_district);
        p_pincode_id = (TextInputEditText) findViewById(R.id.p_pincode);
        nationality_id = (TextInputEditText) findViewById(R.id.nationality);
        state_id = (TextInputEditText) findViewById(R.id.state);

        submit_id = (Button) findViewById(R.id.submit);

        submit_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                c_door = c_door_id.getText().toString();
                c_address1 = c_address1_id.getText().toString();
                c_address2 = c_address2_id.getText().toString();
                c_postalarea = c_postalarea_id.getText().toString();
                c_taluk = c_taluk_id.getText().toString();
                c_district = c_district_id.getText().toString();
                c_pincode = c_pincode_id.getText().toString();
                p_door = p_door_id.getText().toString();
                p_address1 = p_address1_id.getText().toString();
                p_address2 = p_address2_id.getText().toString();
                p_postalarea = p_postalarea_id.getText().toString();
                p_taluk = p_taluk_id.getText().toString();
                p_district = p_district_id.getText().toString();
                p_pincode = p_pincode_id.getText().toString();
                nationality = nationality_id.getText().toString();
                state=state_id.getText().toString();


                Map m = new HashMap();
                m.put("c_doorno", c_door);
                m.put("c_address1", c_address1);
                m.put("c_address2", c_address2);
                m.put("c_district", c_district);
                m.put("c_pincode", c_pincode);
                m.put("c_postalarea", c_postalarea);
                m.put("c_thaluk", c_taluk);
                m.put("p_doorno", p_door);
                m.put("p_address1", p_address1);
                m.put("p_address2", p_address2);
                m.put("p_district", p_district);
                m.put("p_pincode", p_pincode);
                m.put("p_postalarea", p_postalarea);
                m.put("p_thaluk", p_taluk);
                m.put("nationality",nationality);
                m.put("state",state);


                String rollno= sharedpreferences.getString("Rollno","");

                mfire.collection("address_details").document(rollno).set(m).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Address_Details.this, "the details of Student is successfully Registred", Toast.LENGTH_LONG).show();
                        Intent home = new Intent(getApplicationContext(), Home.class);
                        startActivity(home);
                    }
                });

            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();

        mfire=FirebaseFirestore.getInstance();


        c_door_id = (TextInputEditText) findViewById(R.id.c_door);
        c_address1_id = (TextInputEditText) findViewById(R.id.c_address1);
        c_address2_id = (TextInputEditText) findViewById(R.id.c_address2);
        c_postalarea_id = (TextInputEditText) findViewById(R.id.c_postaddress);
        c_taluk_id = (TextInputEditText) findViewById(R.id.c_thaluk);
        c_district_id = (TextInputEditText) findViewById(R.id.c_district);
        c_pincode_id = (TextInputEditText) findViewById(R.id.c_pincode);
        p_door_id = (TextInputEditText) findViewById(R.id.p_door);
        p_address1_id = (TextInputEditText) findViewById(R.id.p_address1);
        p_address2_id = (TextInputEditText) findViewById(R.id.p_address2);
        p_postalarea_id = (TextInputEditText) findViewById(R.id.p_postaddress);
        p_taluk_id = (TextInputEditText) findViewById(R.id.p_thaluk);
        p_district_id = (TextInputEditText) findViewById(R.id.p_district);
        p_pincode_id = (TextInputEditText) findViewById(R.id.p_pincode);
        nationality_id = (TextInputEditText) findViewById(R.id.nationality);
        state_id = (TextInputEditText) findViewById(R.id.state);

        String rollno= sharedpreferences.getString("Rollno","");
        mfire.collection("address_details").document(rollno).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();




                    String c_doorno=documentSnapshot.getString("c_doorno");
                    String c_address1=documentSnapshot.getString("c_address1");
                    String c_address2=documentSnapshot.getString("c_address2");
                    String c_district=documentSnapshot.getString("c_district");
                    String c_pincode=documentSnapshot.getString("c_pincode");
                    String c_postalarea=documentSnapshot.getString("c_postalarea");
                    String c_thaluk=documentSnapshot.getString("c_thaluk");
                    String p_doorno=documentSnapshot.getString("p_doorno");
                    String p_address1=documentSnapshot.getString("p_address1");
                    String p_address2=documentSnapshot.getString("p_address2");
                    String p_district=documentSnapshot.getString("p_district");
                    String p_pincode=documentSnapshot.getString("p_pincode");
                    String p_postalarea=documentSnapshot.getString("p_postalarea");
                    String p_thaluk=documentSnapshot.getString("p_thaluk");
                    String nationality=documentSnapshot.getString("nationality");
                    String state=documentSnapshot.getString("state");



                        c_door_id.setText(c_doorno);
                        c_address1_id.setText(c_address1);
                        c_address2_id.setText(c_address2);
                        c_district_id.setText(c_district);
                        c_pincode_id.setText(c_pincode);
                        c_postalarea_id.setText(c_postalarea);
                        c_taluk_id.setText(c_thaluk);
                        p_door_id.setText(p_doorno);
                        p_address1_id.setText(p_address1);
                        p_address2_id.setText(p_address2);
                        p_district_id.setText(p_district);
                        p_pincode_id.setText(p_pincode);
                        p_postalarea_id.setText(p_postalarea);
                        p_taluk_id.setText(p_thaluk);
                        nationality_id.setText(nationality);
                        state_id.setText(state);






                }
            }
        });

    }




}
