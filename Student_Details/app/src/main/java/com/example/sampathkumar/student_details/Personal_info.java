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
import android.widget.AdapterView;
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

public class Personal_info extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    FirebaseFirestore mfire;

    TextInputEditText firstname_id,middlename_id,lastname_id,rollno_id,batch_id,mobile_id,password_1_id,email_id;
    Spinner department_id,section_id,bloodgroup_id;

    EditText dob_id;



    String firstname,middlename,lastname,rollno,department,section,dob,bloodgroup;
    String email,mobile,password_1,batch;
    Button submit;
    String MyPREFERENCES="Login";

    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        mfire=FirebaseFirestore.getInstance();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        submit=(Button) findViewById(R.id.register_submit);
        firstname_id=(TextInputEditText) findViewById(R.id.first_name);
        middlename_id=(TextInputEditText) findViewById(R.id.middle_name);
        lastname_id=(TextInputEditText) findViewById(R.id.last_name);
        rollno_id=(TextInputEditText) findViewById(R.id.rollno);
        batch_id=(TextInputEditText) findViewById(R.id.batch);
        mobile_id=(TextInputEditText) findViewById(R.id.mobilenumber);
        password_1_id=(TextInputEditText) findViewById(R.id.password_1);
        email_id=(TextInputEditText) findViewById(R.id.email);
        department_id=(Spinner) findViewById(R.id.department1);
        section_id=(Spinner) findViewById(R.id.section);
        bloodgroup_id=(Spinner) findViewById(R.id.bloodgroup);
        dob_id=(EditText) findViewById(R.id.dob);

        department_id.setOnItemSelectedListener(this);
        section_id.setOnItemSelectedListener(this);
        bloodgroup_id.setOnItemSelectedListener(this);






        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                firstname=firstname_id.getText().toString();
                middlename=middlename_id.getText().toString();
                lastname=lastname_id.getText().toString();
                rollno=rollno_id.getText().toString();
                batch=batch_id.getText().toString();
                email=email_id.getText().toString();
                mobile=mobile_id.getText().toString();
                dob=dob_id.getText().toString();
                password_1=password_1_id.getText().toString();
                Log.e("ans",firstname+" "+middlename+" "+batch);

                Map m=new HashMap();
                m.put("firstname",firstname);
                m.put("middlename",middlename);
                m.put("lastname",lastname);
                m.put("rollno",rollno);
                m.put("batch",batch);
                m.put("department",department);
                m.put("section",section);
                m.put("dateofbirth",dob);
                m.put("bloodgroup",bloodgroup);
                m.put("email",email);
                m.put("mobile",mobile);
                m.put("password",password_1);

                String rollno= sharedpreferences.getString("Rollno"," ");
                Log.e("TAG",rollno);

                mfire.collection("student_details").document(rollno).set(m).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Personal_info.this,"the details of Student is successfully Registred",Toast.LENGTH_LONG).show();
                        Intent Ppersonalinfo=new Intent(getApplicationContext(),Personal_info.class);
                        startActivity(Ppersonalinfo);
                    }
                });

            }
        });






    }


    @Override
    protected void onResume() {
        super.onResume();

        mfire=FirebaseFirestore.getInstance();

        submit=(Button) findViewById(R.id.register_submit);
        firstname_id=(TextInputEditText) findViewById(R.id.first_name);
        middlename_id=(TextInputEditText) findViewById(R.id.middle_name);
        lastname_id=(TextInputEditText) findViewById(R.id.last_name);
        rollno_id=(TextInputEditText) findViewById(R.id.rollno);
        batch_id=(TextInputEditText) findViewById(R.id.batch);
        mobile_id=(TextInputEditText) findViewById(R.id.mobilenumber);
        password_1_id=(TextInputEditText) findViewById(R.id.password_1);
        email_id=(TextInputEditText) findViewById(R.id.email);
        department_id=(Spinner) findViewById(R.id.department1);
        section_id=(Spinner) findViewById(R.id.section);
        bloodgroup_id=(Spinner) findViewById(R.id.bloodgroup);
        dob_id=(EditText) findViewById(R.id.dob);

        department_id.setOnItemSelectedListener(this);
        section_id.setOnItemSelectedListener(this);
        bloodgroup_id.setOnItemSelectedListener(this);


        String rollno= sharedpreferences.getString("Rollno","");
        Log.e("TAG1",rollno);
        mfire.collection("student_details").document("15csr178").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();

                    String firstname1=documentSnapshot.getString("firstname");
                    String middlename=documentSnapshot.getString("middlename");
                    String lastname=documentSnapshot.getString("lastname");
                    String rollno=documentSnapshot.getString("rollno");
                    String batch=documentSnapshot.getString("batch");
                    String department1=documentSnapshot.getString("department");
                    String section=documentSnapshot.getString("section");
                    String dateofbirth=documentSnapshot.getString("dateofbirth");
                    String bloodgroup=documentSnapshot.getString("bloodgroup");
                    String email=documentSnapshot.getString("email");
                    String mobile=documentSnapshot.getString("mobile");
                    String password=documentSnapshot.getString("password");



                    firstname_id.setText(firstname1);
                    middlename_id.setText(middlename);
                    lastname_id.setText(lastname);
                    rollno_id.setText(rollno);
                    batch_id.setText(batch);
                    email_id.setText(email);
                    mobile_id.setText(mobile);
                    dob_id.setText(dateofbirth);
                    password_1_id.setText(password);



                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId())
        {
            case R.id.department1 :
                department= adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.section :
                section=adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.bloodgroup:
                bloodgroup=adapterView.getItemAtPosition(i).toString();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
