package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    //to access login and sigup buttons
    private Button login, signup;
    public  static List<Users> usersdata;//arraylist to store data about users who will register.

    public List<Users> getUsersdata() {
        return usersdata;
    }
    //constructor
    public void setUsersdata(List<Users> usersdata) {
        this.usersdata = usersdata;
    }
    int diff1;

    Calendar myCalendar;
    EditText dob1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //arrayList object
        usersdata = new ArrayList<>();

        //to access login and signup buttons
        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);

        //to access the Edittext like getting name ,email..
        final EditText firstname = (EditText)findViewById(R.id.fname);
        final EditText famname = (EditText)findViewById(R.id.familyname);
        dob1 = (EditText)findViewById(R.id.dob);
        final EditText email1 = (EditText)findViewById(R.id.email);
        final EditText pass1 = (EditText)findViewById(R.id.password);


        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dob1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    new DatePickerDialog(RegisterActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                return false;
            }
        });

        //click event on login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // switch to next activity by calling intent object
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();

            }
        });

        ////click event on signup button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //storing the data of input boxes into variables
                String fname = firstname.getText().toString();
                String familyname = famname.getText().toString();
                String dob = dob1.getText().toString();
                String email = email1.getText().toString();
                String password = pass1.getText().toString();


               //validating the entered data
               if(fname.length() != 0 || familyname.length() != 0 || dob.length() != 0 || email.length() != 0 || password.length() != 0){

                   //checking the length of the chars
                   if(firstname.length() >= 3 && firstname.length() <= 30){

                       //if email is valid
                     if(isValidEmail(email)){

                       if(password.length() < 8){
                           Toast.makeText(RegisterActivity.this, "Password must have at least eight characters!", Toast.LENGTH_SHORT).show();
                       }else{

                           //creating the object of Users and adding into arrayList
                           Users users = new Users(fname, familyname, email, dob, password);
                           usersdata.add(users);//adding the user to data structure

                           //showing message after success
                           Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                           //going back to login Screen
                           startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                           finish();

                       }

                     }else{
                         Toast.makeText(RegisterActivity.this, "Please enter valid email, all fields are required!", Toast.LENGTH_SHORT).show();
                     }

                   }else{
                       Toast.makeText(RegisterActivity.this, "Name length must be between 3 and 30 characters!", Toast.LENGTH_SHORT).show();
                   }

               }else{
                   Toast.makeText(RegisterActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
               }

            }
        });

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob1.setText(sdf.format(myCalendar.getTime()));
    }


    //method for validating email
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}