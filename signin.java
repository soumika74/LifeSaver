package life.saver;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signin extends AppCompatActivity {
    DatabaseHelper helper= new DatabaseHelper(this);
    Button login,signup;
    TextView email, password;
    String mailstr, passtr,pass;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        email = (EditText) findViewById(R.id.usn1);
        password = (EditText) findViewById(R.id.pass1);
        login = (Button) findViewById(R.id.loginbt1);
        signup = (Button) findViewById(R.id.signupbt1);
        mAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog=new ProgressDialog(signin.this);
                        progressDialog.setCancelable(false);
                        progressDialog.setTitle("processing");
                        progressDialog.setMessage("please wait");
                        if(task.isSuccessful()){

                            if (mAuth.getCurrentUser().isEmailVerified()) {
                                Intent intent = new Intent(signin.this, next.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                //Toast.makeText(signin.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                Toast.makeText(signin.this, "please verify your email",Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(signin.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            Toast.makeText(signin.this, "no data found",Toast.LENGTH_LONG).show();

                        }
                    }
                });
                }

        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(signin.this, signup.class);
                    startActivity(intent);
                }
        });

    }
    }
