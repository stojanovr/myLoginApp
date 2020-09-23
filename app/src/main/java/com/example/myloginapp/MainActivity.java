package com.example.myloginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //buttons for the login screen
    private Button login, signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //object of RegisterActivity class to access the arrayList
        final RegisterActivity registerActivity = new RegisterActivity();


        //button objects for the login screen
        login = (Button)findViewById(R.id.loginbutton);
        signup = (Button)findViewById(R.id.signup);

        //for getting the data from email and password.
        final EditText email = (EditText)findViewById(R.id.useremail);
        final EditText pass = (EditText)findViewById(R.id.userpassword);


        //login event on button click
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //validating if the input boxes are null
            if(email.length() == 0 || pass.length() == 0){
            Toast.makeText(MainActivity.this, "Enter your credentials to login!", Toast.LENGTH_SHORT).show();

            }else{
                //validating if the email is valid else show Toast message
                if(isValidEmail(email.getText().toString())) {

                    // check if the arrayList is not null
                    if (RegisterActivity.usersdata != null)
                        if (RegisterActivity.usersdata != null)
                            //match the email and password of users
                            for (int i = 0; i < RegisterActivity.usersdata.size(); i++) {
                                if (RegisterActivity.usersdata.get(i).getEmail().equals(email.getText().toString())
                                        && RegisterActivity.usersdata.get(i).getPassword().equals(pass.getText().toString())) {

                                    AlertDialog alertDialog1 = new AlertDialog.Builder(
                                            MainActivity.this).create();
                                    alertDialog1.setTitle("Login Successful:");
                                    alertDialog1.setMessage("Hello, "+RegisterActivity.usersdata.get(i).getFirstname() + "" +
                                            " " + RegisterActivity.usersdata.get(i).getFamilyname());
                                    alertDialog1.show();

                                    email.setText("");
                                    pass.setText("");

                                } else {

                                    Toast.makeText(MainActivity.this, "Please enter a valid username and password!", Toast.LENGTH_SHORT).show();
                                    break;

                                }
                            }

                }else{
                    Toast.makeText(MainActivity.this, "Please enter a valid email address!", Toast.LENGTH_SHORT).show();
                }

            }


            }
        });

        //click event on signup btn
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //change activity
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();

            }
        });


    }

    //method to validate email
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}