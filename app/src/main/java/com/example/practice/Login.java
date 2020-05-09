package com.example.practice;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    GlobalFunctions uiBars = new GlobalFunctions();
    private View decorView;

    private EditText email,pass;
    private Button login;

    private FirebaseAuth mAuth;
    private static final String TAG = "message";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance();

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility==0){
                    decorView.setSystemUiVisibility(uiBars.hideSystemBars());
                }
            }
        });

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);


        Typeface Orbiter = Typeface.createFromAsset(getAssets(),"fonts/earthorbitertitle.ttf");
        TypefaceSpan typefaceSpan = new CustomTypefaceSpan(Orbiter);

        email.setHint(mapHint(typefaceSpan,"Email"));
        pass.setHint(mapHint(typefaceSpan,"Password"));


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Login.this,Home.class);
//                Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_SHORT).show();
//                startActivity(intent);

                String emailText = email.getText().toString().trim();
                String passText = pass.getText().toString().trim();
                Authenticate(emailText,passText);
            }
        });


    }

    public static SpannableString mapHint(TypefaceSpan typefaceSpan, String hint){
        SpannableString newHint = new SpannableString(hint);
        newHint.setSpan(typefaceSpan,0,newHint.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return newHint;
    }




    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if( currentUser!= null ){

            Intent intent = new Intent(Login.this, CustomerHome.class);
            startActivity(intent);
        }
    }


    public void Authenticate(String email, String pass){

        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(pass)){
            Toast.makeText(Login.this,"Please enter your credentials",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email)){
            Toast.makeText(Login.this,"Please enter username", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pass)){
            Toast.makeText(Login.this,"Please enter password", Toast.LENGTH_SHORT).show();
        }
        else{




            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(Login.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(Login.this, CustomerHome.class);
                                        startActivity(intent);
                                    }
                                },1000);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(Login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }


                        }
                    });






        }
    }






    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            decorView.setSystemUiVisibility(uiBars.hideSystemBars());
        }
    }



}
