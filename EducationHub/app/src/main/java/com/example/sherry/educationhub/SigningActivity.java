package com.example.sherry.educationhub;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class  SigningActivity extends AppCompatActivity {


    private Button login;
    private EditText USERNAME, USERPASSWORD;
    private FirebaseAuth auth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing);

        USERNAME=(EditText)findViewById(R.id.etname);
        USERPASSWORD = (EditText)findViewById(R.id.etpass);
        login = (Button)findViewById(R.id.btln);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        FirebaseDatabase database=FirebaseDatabase.getInstance();

        auth = FirebaseAuth.getInstance();




        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(SigningActivity.this, MainActivity.class));
            finish();
        }




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = USERNAME.getText().toString();
                String password = USERPASSWORD.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(username,password)
                        .addOnCompleteListener(SigningActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);

                                Intent intent = new Intent(SigningActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
            }
        });
    }
}






