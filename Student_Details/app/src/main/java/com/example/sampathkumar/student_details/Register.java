package com.example.sampathkumar.student_details;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    FirebaseFirestore mfire;

    TextInputEditText firstname_id,middlename_id,lastname_id,rollno_id,batch_id,mobile_id,password_1_id,password_2_id,email_id;
    Spinner department_id,section_id,bloodgroup_id;

    EditText dob_id;

    String firstname,middlename,lastname,rollno,department,section,dob,bloodgroup;
    String email,mobile,password_1,password_2,batch;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mfire=FirebaseFirestore.getInstance();

        submit=(Button) findViewById(R.id.register_submit);
        firstname_id=(TextInputEditText) findViewById(R.id.first_name);
        middlename_id=(TextInputEditText) findViewById(R.id.middle_name);
        lastname_id=(TextInputEditText) findViewById(R.id.last_name);
        rollno_id=(TextInputEditText) findViewById(R.id.rollno);
        batch_id=(TextInputEditText) findViewById(R.id.batch);
        mobile_id=(TextInputEditText) findViewById(R.id.mobilenumber);
        password_1_id=(TextInputEditText) findViewById(R.id.password_1);
        password_2_id=(TextInputEditText) findViewById(R.id.password_2);
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
                password_2=password_2_id.getText().toString();
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

                mfire.collection("student_details").document(rollno).set(m).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Register.this,"the details of Student is successfully Registred",Toast.LENGTH_LONG).show();
                        Intent login=new Intent(getApplicationContext(),Login.class);
                        startActivity(login);
                    }
                });




                Map m1=new HashMap();
                m1.put("c_doorno"," ");
                m1.put("c_address1"," ");
                m1.put("c_address2"," ");
                m1.put("c_postalarea"," ");
                m1.put("c_thaluk"," ");
                m1.put("c_district"," ");
                m1.put("c_pincode"," ");
                m1.put("p_doorno"," ");
                m1.put("p_address1"," ");
                m1.put("p_address2"," ");
                m1.put("p_postalarea"," ");
                m1.put("p_thaluk"," ");
                m1.put("p_district"," ");
                m1.put("p_pincode"," ");
                m1.put("nationality","");
                m1.put("state"," ");


                mfire.collection("address_details").document(rollno).set(m1).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });



                Map m2=new HashMap();
                m2.put("father_name"," ");
                m2.put("father_occupation"," ");
                m2.put("father_email"," ");
                m2.put("father_mobile"," ");
                m2.put("mother_name"," ");
                m2.put("mother_occupation"," ");
                m2.put("mother_email"," ");
                m2.put("mother_mobile"," ");
                m2.put("sibling1"," ");
                m2.put("sibling2"," ");
                m2.put("sibling3"," ");


                mfire.collection("family_details").document(rollno).set(m2).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });



                Map m3=new HashMap();
                m3.put("aadhar_number"," ");
                m3.put("pancard_number"," ");
                m3.put("smartcard_number"," ");


                mfire.collection("idproof_details").document(rollno).set(m3).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
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
