package com.example.aurora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class Survey1 extends AppCompatActivity {

    String condition = "please select a field";
    String question2="I tended to over-react to situation";
    int sum = 0;
    String s;
    Button next;
    String cquestion1;
    EditText cquestion_set;
    RadioButton always,often,notmuch,never;
    EditText score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey1);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        Intent intent = getIntent();
        cquestion1 = intent.getExtras().getString("cquestion1");

        cquestion_set = findViewById(R.id.questiontext);
        cquestion_set.setText(cquestion1);
        cquestion_set.setVisibility(View.VISIBLE);

        always = findViewById(R.id.radio_Always);
        notmuch = findViewById(R.id.radio_not_much);
        never = findViewById(R.id.radio_never);
        often = findViewById(R.id.radio_often);
        next = findViewById(R.id.next);
        score = findViewById(R.id.score);
        score.setText("Score: "+"0");
        score.setVisibility(View.VISIBLE);


        always.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Phone_call.class);
//                intent.putExtra("cnumber", number);
//                startActivity(intent);
                sum = 1;
                if(always.isChecked())
                {
                    often.setChecked(false);
                    notmuch.setChecked(false);
                    never.setChecked(false);


                }
                s = String.valueOf(sum);
                score.setText("Score: "+s);
                score.setVisibility(View.VISIBLE);
                condition = "clicked";
            }
        });

        often.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Phone_call.class);
//                intent.putExtra("cnumber", number);
//                startActivity(intent);
                sum=2;
                if(often.isChecked())
                {
                    always.setChecked(false);
                    notmuch.setChecked(false);
                    never.setChecked(false);

                }
                s = String.valueOf(sum);
                score.setText("Score: "+s);
                score.setVisibility(View.VISIBLE);
                condition = "clicked";
            }
        });


        notmuch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Phone_call.class);
//                intent.putExtra("cnumber", number);
//                startActivity(intent);
                sum=3;
                if(notmuch.isChecked())
                {
                    always.setChecked(false);
                    often.setChecked(false);
                    never.setChecked(false);

                }
                s = String.valueOf(sum);
                score.setText("Score: "+s);
                score.setVisibility(View.VISIBLE);
                condition = "clicked";
            }
        });


        never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Phone_call.class);
//                intent.putExtra("cnumber", number);
//                startActivity(intent);
                sum=4;
                if(never.isChecked())
                {
                    always.setChecked(false);
                    often.setChecked(false);
                    notmuch.setChecked(false);

                }
                s = String.valueOf(sum);
                score.setText("Score: "+s);
                score.setVisibility(View.VISIBLE);
                condition = "clicked";
            }
        });
//        Toast.makeText(Survey1.this,sum,Toast.LENGTH_SHORT).show();

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Phone_call.class);
//                intent.putExtra("cnumber", number);
//                startActivity(intent);
                    if(condition.equals("clicked")) {
                        Intent intent = new Intent(getApplicationContext(), Survey2.class);
                        intent.putExtra("cquestion2", question2);
                        intent.putExtra("csum", sum);
                        intent.putExtra("cquestionnum", "Question2");
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Survey1.this,condition,Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }




//        survey = findViewById(R.id.button3);
//
//        survey.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Phone_call.class);
////                intent.putExtra("cnumber", number);
//                startActivity(intent);
//            }
//        });

}

