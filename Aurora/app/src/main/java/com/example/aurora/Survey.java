package com.example.aurora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class Survey extends AppCompatActivity {

    ImageView imageView;
    TextView title;
    Button survey;
    String question1="I found myself getting upset by quite trivial things";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        imageView = findViewById(R.id.image_survey);
//        phone_call = findViewById(R.id.image_call);
        title = findViewById(R.id.titleText3);
        Intent intent = getIntent();

        Bundle bundle = this.getIntent().getExtras();
        int pic = bundle.getInt("image");
        String aTitle = intent.getStringExtra("title");
//        String aDescription = intent.getStringExtra("description");

        imageView.setImageResource(pic);
        title.setText(aTitle);

        survey = findViewById(R.id.button3);

        survey.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Survey1.class);
                intent.putExtra("cquestion1", question1);
                startActivity(intent);
            }
        });

    }
}
