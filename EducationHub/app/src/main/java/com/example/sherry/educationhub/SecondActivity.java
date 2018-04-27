package com.example.sherry.educationhub;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class SecondActivity extends AppCompatActivity {
    private EditText USERNAME;
    private EditText SUBJECT;
    private EditText GRADE;
    private EditText LOCATION,Password;
    private Button SUBMIT;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;


    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        USERNAME= (EditText) findViewById(R.id.etname);
        SUBJECT = (EditText) findViewById(R.id.etsubject);
        GRADE = (EditText) findViewById(R.id.etgrade);
        LOCATION = (EditText) findViewById(R.id.ETN);
        SUBMIT = (Button) findViewById(R.id.btnlog);
        Password=(EditText)findViewById(R.id.etpwd);




        SUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name =USERNAME.getText().toString().trim();
                String password =Password.getText().toString().trim();
                String subject=SUBJECT.getText().toString().trim();
                String classes=GRADE.getText().toString().trim();
                String location=LOCATION.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "enter your number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(subject)) {
                    Toast.makeText(getApplicationContext(), "Enter subjects!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(location)) {
                    Toast.makeText(getApplicationContext(), "Enter address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(classes)) {
                    Toast.makeText(getApplicationContext(), "Enter your grade!", Toast.LENGTH_SHORT).show();
                    return;
                }




                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }



                progressBar.setVisibility(View.VISIBLE);
                //create user
                firebaseAuth.createUserWithEmailAndPassword(name, password)
                        .addOnCompleteListener(SecondActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SecondActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SecondActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SecondActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);


    }

}



