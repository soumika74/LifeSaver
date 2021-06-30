package life.saver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signup extends AppCompatActivity
{
    DatabaseHelper helper = new DatabaseHelper(this);
    private FirebaseAuth mAuth;
    //signup2 un= new signup2();
    Button signup;
    EditText name, ppw2,ppwr2,em,ph,name2;
    String pw2str, namestr, mailstr, name2str, pwr2str, phstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        name=(EditText) findViewById(R.id.usn2);
        ppw2=(EditText) findViewById(R.id.pw2);
        ppwr2=(EditText) findViewById(R.id.pwr2);
        em=(EditText) findViewById(R.id.email2);
        ph=(EditText) findViewById(R.id.phn2);
        name2=(EditText) findViewById(R.id.editText);
        TextView in = (TextView) findViewById(R.id.textView);
        //ImageView bg= (ImageView)findViewById(R.id.bgsignup) ;

        //*********************************************************************
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        name2.startAnimation(animation);
        name.startAnimation(animation);
        ppw2.startAnimation(animation);
        ppwr2.startAnimation(animation);
        em.startAnimation(animation);
        ph.startAnimation(animation);
        in.startAnimation(animation);

        //**********************************************************************
        mAuth=FirebaseAuth.getInstance();
        signup= (Button) findViewById(R.id.signupbt2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw2str=(String) ppw2.getText().toString();
                pwr2str =(String) ppwr2.getText().toString();
                namestr= (String) name.getText().toString();
                mailstr= (String) em.getText().toString();
                phstr= (String) ph.getText().toString();
                name2str= name2.getText().toString();
                //int ph_int=Integer.parseInt(phstr);
                //*******************************************************************

                if(name.length()==0){
                    name.setError("invalid");
                    if(ppw2.length()<5){
                        ppw2.setError("password too short");
                    }
                    if(ppwr2.length()<5){
                        ppwr2.setError("password too short");
                    }
                    if(em.length()==0){
                        em.setError("invalid");
                    }
                    if(ph.length()!=10 ){
                        ph.setError("invalid");
                    }
                    if(!(pw2str).equals(pwr2str)){
                        ppw2.setError("password doesnt match");
                        ppwr2.setError("password doesnt match");
                    }}
                //*********************************************************************
                else if(ppw2.length()<5){
                    ppw2.setError("password too short");
                    if(ppwr2.length()<5){
                        ppwr2.setError("password too short");
                    }
                    if(em.length()==0){
                        em.setError("invalid");
                    }
                    if(ph.length()!=10){
                        ph.setError("invalid");
                    }
                    if(!(pw2str).equals(pwr2str)){
                        ppw2.setError("password doesnt match");
                        ppwr2.setError("password doesnt match");
                    }}
                //**********************************************************************
                else if(ppwr2.length()<5){
                    ppwr2.setError("password too short");
                    if(em.length()==0){
                        em.setError("invalid");
                    }
                    if(ph.length()!=10){
                        ph.setError("invalid");
                    }
                    if(!(pw2str).equals(pwr2str)){
                        ppw2.setError("password doesnt match");
                        ppwr2.setError("password doesnt match");

                    }}
                //**********************************************************************
                else if(em.length()==0){
                    em.setError("invalid");
                    if(ph.length()!=10){
                        ph.setError("invalid");
                    }
                    if(!(pw2str).equals(pwr2str)){
                        ppw2.setError("password doesnt match");
                        ppwr2.setError("password doesnt match");
                    }}
                //************************************************************************
                else if(ph.length()!=10){
                    ph.setError("invalid");
                    if(!(pw2str).equals(pwr2str)) {
                        ppw2.setError("password doesnt match");
                        ppwr2.setError("password doesnt match");
                    }}

                //************************************************************************
                else if(!(pwr2str).equals(pw2str)) {
                    ppw2.setError("password doesnt match");
                    ppwr2.setError("password doesnt match");
                }
                else if((name2.length()==0))
                {
                    name2.setError("invalid");
                }
                //***********************************************************************

                else {
                    mAuth.createUserWithEmailAndPassword(mailstr, pw2str).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(signup.this, "done, please verify", Toast.LENGTH_LONG).show();


                                            Intent i = new Intent(signup.this, signin.class);
                                            startActivity(i);
                                            finish();
                                        } else {
                                            Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(signup.this, "email not verified", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });
        /*Button next1=(Button) findViewById(R.id.next);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw2str=(String) ppw2.getText().toString();
                pwr2str =(String) ppwr2.getText().toString();
                namestr= (String) name.getText().toString();
                mailstr= (String) em.getText().toString();
                phstr= (String) ph.getText().toString();
                name2str= name2.getText().toString();

                DATABASE c = new DATABASE();
                c.setName(name2str);
                c.setUsername(namestr);
                c.setEmail(mailstr);
                c.setPassword(pw2str);
                c.setPhone(phstr);
                String g=c.getName();
                Toast.makeText(life.saver.signup.this, "is"+g,Toast.LENGTH_LONG).show();
                startActivity(new Intent(signup.this, signup2.class));
            }
        });*/
    }
    public void onclicksignin (View view){
        Intent intent= new Intent(signup.this, signin.class);
        startActivity(intent);
        finish();
    }
}