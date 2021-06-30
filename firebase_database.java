package life.saver;

import android.content.ContentValues;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class firebase_database extends AppCompatActivity {
    String name, email, age,diabetics,bp,phone,state,city,gender,des;
    private void collect_values(DATABASE c){
        ContentValues values=new ContentValues();
        values.put(name,c.getName());
        values.put(age,c.getAge());
        values.put(city,c.getCity());
        values.put(state,c.getState());
        values.put(email,c.getEmail());
        values.put(diabetics,c.getDiabetics());
        values.put(gender,c.getGender());
        values.put(phone,c.getPhone());
        values.put(bp,c.getBp());
        values.put(des,c.getDescription());
        HashMap<String,Object> map=new HashMap<>();
        map.put("name", name);
        map.put("age",age);
        map.put("city",city);
        map.put("state",state);
        map.put("email",email);
        map.put("diabetics",diabetics);
        map.put("gender",gender);
        map.put("phone",phone);
        map.put("bp",bp);
        map.put("description",des);
        FirebaseDatabase.getInstance().getReference().child(email).updateChildren(map);

    }


}
