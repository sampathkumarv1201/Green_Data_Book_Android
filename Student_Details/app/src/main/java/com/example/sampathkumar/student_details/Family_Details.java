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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Family_Details extends AppCompatActivity {

    FirebaseFirestore mfire;

    TextInputEditText f_name_id,f_occupation_id,f_email_id,f_mobile_id;
    TextInputEditText m_name_id,m_occupation_id,m_email_id,m_mobile_id;
    TextInputEditText sibiling2_id,sibiling1_id,sibiling3_id;
    Button submit_id;

    String f_name,f_occupation,f_email,f_mobile,m_name,m_occupation,m_email,m_mobile;
    String sibiling1,sibiling2,sibiling3;

    String MyPREFERENCES="Login";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family__details);

        mfire = FirebaseFirestore.getInstance();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        f_name_id = (TextInputEditText) findViewById(R.id.father_name);
        Log.i("fname",f_name_id.toString());
        f_occupation_id = (TextInputEditText) findViewById(R.id.f_occupation);
        f_email_id = (TextInputEditText) findViewById(R.id.f_email);
        f_mobile_id = (TextInputEditText) findViewById(R.id.f_mobile);
        m_name_id = (TextInputEditText) findViewById(R.id.mother_name);
        m_occupation_id = (TextInputEditText) findViewById(R.id.m_occupation);
        m_email_id = (TextInputEditText) findViewById(R.id.m_email);
        m_mobile_id = (TextInputEditText) findViewById(R.id.m_mobile);
        sibiling1_id = (TextInputEditText) findViewById(R.id.sibiling1);
        sibiling2_id = (TextInputEditText) findViewById(R.id.sibiling2);
        sibiling3_id = (TextInputEditText) findViewById(R.id.sibiling3);

        submit_id = (Button) findViewById(R.id.submit);

        submit_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                f_name = f_name_id.getText().toString();
                f_occupation = f_occupation_id.getText().toString();
                f_email = f_email_id.getText().toString();
                f_mobile = f_email_id.getText().toString();
                m_name = m_name_id.getText().toString();
                m_occupation = m_occupation_id.getText().toString();
                m_email = m_email_id.getText().toString();
                m_mobile = m_mobile_id.getText().toString();
                sibiling1 = sibiling1_id.getText().toString();
                sibiling2 = sibiling2_id.getText().toString();
                sibiling3 = sibiling3_id.getText().toString();


                Map m = new HashMap();
                m.put("father_name", f_name);
                m.put("father_occupation", f_occupation);
                m.put("father_email", f_email);
                m.put("father_mobile", f_mobile);
                m.put("mother_name", m_name);
                m.put("mother_occupation", m_occupation);
                m.put("mother_email", m_email);
                m.put("mother_mobile", m_mobile);
                m.put("sibiling1", sibiling1);
                m.put("sibiling2", sibiling2);
                m.put("sibiling3", sibiling3);

                String rollno= sharedpreferences.getString("Rollno","");
                mfire.collection("family_details").document(rollno).set(m).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Family_Details.this, "the details of Student is successfully Registred", Toast.LENGTH_LONG).show();
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

            f_name_id = findViewById(R.id.father_name);
            f_occupation_id=(TextInputEditText) findViewById(R.id.f_occupation);
            f_email_id=(TextInputEditText) findViewById(R.id.f_email);
            f_mobile_id=(TextInputEditText) findViewById(R.id.f_mobile);
            m_name_id=(TextInputEditText) findViewById(R.id.mother_name);
            m_occupation_id=(TextInputEditText) findViewById(R.id.m_occupation);
            m_email_id=(TextInputEditText) findViewById(R.id.m_email);
            m_mobile_id=(TextInputEditText) findViewById(R.id.m_mobile);
            sibiling1_id=(TextInputEditText) findViewById(R.id.sibiling1);
            sibiling2_id=(TextInputEditText) findViewById(R.id.sibiling2);
            sibiling3_id=(TextInputEditText) findViewById(R.id.sibiling3);

           String rollno= sharedpreferences.getString("Rollno","");


            mfire.collection("family_details").document(rollno).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot documentSnapshot=task.getResult();

                        String f_name=documentSnapshot.getString("father_name");
                        String f_occupation=documentSnapshot.getString("father_occupation");
                        String f_email=documentSnapshot.getString("father_email");
                        String f_mobile=documentSnapshot.getString("father_mobile");
                        String m_name=documentSnapshot.getString("mother_name");
                        String m_occupation=documentSnapshot.getString("mother_occupation");
                        String m_email=documentSnapshot.getString("mother_email");
                        String m_mobile=documentSnapshot.getString("mother_mobile");
                        String sibling1=documentSnapshot.getString("sibling1");
                        String sibling2=documentSnapshot.getString("sibling2");
                        String sibling3=documentSnapshot.getString("sibling3");




                        Toast.makeText(getApplicationContext(),"Executed",Toast.LENGTH_LONG).show();
                        f_name_id.setText(f_name);
                        f_occupation_id.setText(f_occupation);
                        f_email_id.setText(f_email);
                        f_mobile_id.setText(f_mobile);
                        m_name_id.setText(m_name);
                        m_occupation_id.setText(m_occupation);
                        m_email_id.setText(m_email);
                        m_mobile_id.setText(m_mobile);
                        sibiling1_id.setText(sibling1);
                        sibiling2_id.setText(sibling2);
                        sibiling3_id.setText(sibling3);






                    }
                }
            });

        }





}
