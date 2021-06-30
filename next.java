package life.saver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class next extends AppCompatActivity {
    Button logout;
    Button add_details;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);
        logout=findViewById(R.id.logout);
        add_details=findViewById(R.id.button2);
        mAuth=FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog= new ProgressDialog(next.this);
                progressDialog.setCancelable(false);
                progressDialog.setTitle("processing");
                progressDialog.setMessage("please wait");
                mAuth.signOut();
                Toast.makeText(next.this,"logged out successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(next.this,signin.class));
                finish();
            }
        });
        add_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(next.this,signup2.class));
                //finish();
            }
        });

    }

}
