package life.saver;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class signup2 extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    EditText age, state, city, description;
    String agestr, statestr, citystr, descriptionstr, genderstr, genderstr1, diabetics="no", bp="no";
    String pw2str, namestr, mailstr, name2str, phstr,mane_copy;
    Button signup2;
    Spinner spinner;
    ProgressDialog progressDialog;
    //private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup2);


        age = (EditText) findViewById(R.id.age);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);
        description = (EditText) findViewById(R.id.description);
        spinner = (Spinner) findViewById(R.id.gender);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        //*************************************************************************************
        age.startAnimation(animation);
        city.startAnimation(animation);
        state.startAnimation(animation);
        description.startAnimation(animation);

        //***************************************************************************************
        List<String> list = new ArrayList<>();
        list.add(0, "gender");
        list.add(" male ");
        list.add(" female ");
        list.add(" others ");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("gender")) {
                   // Toast.makeText(signup2.this, "the gender should not be empty", Toast.LENGTH_LONG).show();
                } else {
                    genderstr = (String) parent.getItemAtPosition(position).toString();
                    Toast.makeText(signup2.this, genderstr, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        signup2 = (Button) findViewById(R.id.signup2);
       // mAuth=FirebaseAuth.getInstance();
        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DATABASE obj=new DATABASE();
                pw2str =obj.getPassword();
                name2str =obj.getName();
                namestr =obj.getUsername();
                mailstr = obj.getEmail();
                phstr = obj.getPhone();
                agestr =  age.getText().toString();
                statestr =  state.getText().toString();
                citystr =  city.getText().toString();
                descriptionstr =  description.getText().toString();
                genderstr1 = genderstr;
                //String uname = helper.seacrhuname(namestr);

                if (agestr.length() == 0) {
                    age.setError("invalid");
                    if (statestr.length() == 0) {
                        state.setError("invalid");
                    } else if (citystr.length() == 0) {
                        city.setError("invalid");
                    }

                } else if (statestr.length() == 0) {
                    state.setError("invalid");
                    if (citystr.length() == 0) {
                        city.setError("invalid");
                    }
                } else if (citystr.length() == 0) {
                    city.setError("invalid");
                } else if (descriptionstr.length() == 0) {
                    description.setError("invalid");
                }
                else
                {
                    progressDialog=new ProgressDialog(signup2.this);
                    progressDialog.setCancelable(false);
                    progressDialog.setTitle("processing");
                    progressDialog.setMessage("please wait");

                    HashMap<String,Object> map=new HashMap<>();
                    map.put("name", "name2str");
                    map.put("age","age");
                    map.put("city","city");
                    map.put("state","state");
                    map.put("email","mailstr");
                    map.put("diabetics",diabetics);
                    map.put("gender",genderstr1);
                    map.put("phone",phstr);
                    map.put("bp",bp);
                    map.put("description",descriptionstr);
                   // FirebaseDatabase.getInstance().getReference().child("rahitnath713203@gmail.com").updateChildren(map);
                    //Toast.makeText(signup2.this, "is"+ name2str , Toast.LENGTH_LONG).show();
                }
            }
        });
        Button back=(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup2.this, signup.class));
            }
        });
    }

//private String get_name(DATABASE c){
   // ContentValues values=new ContentValues();
   // values.put(name2str,c.getEmail());
    //return name2str;
//}
    /*
    name2str=name
    namestr=username
    mailstr=mail
    pw2str=
     */
    public void get_data (DATABASE data){
        String name= data.getName();
        name2str=name;
    }


    public void onclickdiabetics (View view){
        diabetics="yes";
    }
    public void onclickbp (View view){
        bp="yes";
    }
}
