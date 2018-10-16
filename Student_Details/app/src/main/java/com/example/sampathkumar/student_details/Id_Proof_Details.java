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

public class Id_Proof_Details extends AppCompatActivity {


    FirebaseFirestore mfire;

    TextInputEditText aadhar_id,pan_id,smart_id;
    String aadhar,pan,smart;
    Button submit_id;
    String MyPREFERENCES="Login";

    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id__proof__details);

        mfire = FirebaseFirestore.getInstance();

         sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        aadhar_id = (TextInputEditText) findViewById(R.id.aadhar_number);
        pan_id = (TextInputEditText) findViewById(R.id.pan_number);
        smart_id = (TextInputEditText) findViewById(R.id.smartcard_number);

        submit_id = (Button) findViewById(R.id.submit);

        submit_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aadhar = aadhar_id.getText().toString();
                pan = pan_id.getText().toString();
                smart = smart_id.getText().toString();


                Map m = new HashMap();
                m.put("aadhar_number", aadhar);
                m.put("pancard_number", pan);
                m.put("smartcard_number", smart);

                String rollno= sharedpreferences.getString("Rollno","");


                mfire.collection("idproof_details").document(rollno).set(m).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Id_Proof_Details.this, "the details of Student is successfully Submitted", Toast.LENGTH_LONG).show();
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

        aadhar_id = (TextInputEditText) findViewById(R.id.aadhar_number);
        pan_id = (TextInputEditText) findViewById(R.id.pan_number);
        smart_id = (TextInputEditText) findViewById(R.id.smartcard_number);


        String rollno= sharedpreferences.getString("Rollno","");

        mfire.collection("idproof_details").document(rollno).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();

                    String aadhar=documentSnapshot.getString("aadhar_number");
                    String pan=documentSnapshot.getString("pancard_number");
                    String smart=documentSnapshot.getString("smartcard_number");




                        aadhar_id.setText(aadhar);
                        pan_id.setText(pan);
                        smart_id.setText(smart);






                }
            }
        });

    }


}
