package life.saver;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splash_screen extends AppCompatActivity {
    private static int splash_time_out = 5000;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseUser loggedin=mAuth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        TextView TextView = findViewById(R.id.text1);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        TextView.startAnimation(animation);
        if(loggedin !=null && loggedin.isEmailVerified())
        {
            Intent intent = new Intent(getApplicationContext(), next.class);
            startActivity(intent);
            finish();
        }
            else{
                Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000  );
                    Intent intent = new Intent(getApplicationContext(), signin.class);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };timer.start();
    }
}
}

