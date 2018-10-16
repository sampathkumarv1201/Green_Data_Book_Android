package com.example.sampathkumar.student_details;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {

    Button signin,signup;

    FirebaseFirestore mfire;

    TextInputEditText username_id,password_id;
    String username,password;
    String MyPREFERENCES="Login",Rollno="username";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);

        username_id= (TextInputEditText) findViewById(R.id.username);
        password_id = (TextInputEditText) findViewById(R.id.password);

        mfire=FirebaseFirestore.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                username = username_id.getText().toString();
                password = password_id.getText().toString();

                mfire.collection("student_details").document(username).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot=task.getResult();

                            String Pass=documentSnapshot.getString("password");



                                if (password.equals(Pass))
                                {
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString("Rollno", username);
                                    editor.commit();
                                    Intent home= new Intent(getApplicationContext(),Home.class);
                                    startActivity(home);

                                }
                                else
                                {
                                    Toast.makeText(Login.this,  "Password Is Wrong ", Toast.LENGTH_LONG).show();
                                    Intent login= new Intent(getApplicationContext(),Login.class);
                                    startActivity(login);
                                }

                        }
                    }
                });
         /*       mfire.collection("stud_det").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(final DocumentSnapshot documentSnapshot : task.getResult())
                            {
                                String user=documentSnapshot.getString("rollno");
                                String pass=documentSnapshot.getString("password");

                                if(user.equals(username))
                                {
                                    if(pass.equals(password))
                                    {
                                        Intent home= new Intent(getApplicationContext(),Home.class);
                                        startActivity(home);
                                    }
                                }
                                else
                                {
                                    Intent login= new Intent(getApplicationContext(),Login.class);
                                    startActivity(login);
                                }
                            }
                        }
                    }
                });*/
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(),Register.class);
                startActivity(register);
            }
        });
    }
}
