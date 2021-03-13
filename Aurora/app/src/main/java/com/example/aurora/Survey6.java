package com.example.aurora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class Survey6 extends AppCompatActivity {

    String condition = "please select a field";
    String question7="I sometimes worry so much it affects my whole day";
    int sum,temp ;
    String s,t;
    Button next;
    String cquestion6,cquestionnum;
    EditText cquestion_set;
    RadioButton always,often,notmuch,never;
    EditText score;
    TextView title_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey6);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        Intent intent = getIntent();
        cquestion6 = intent.getExtras().getString("cquestion6");
        sum = intent.getExtras().getInt("csum");
        cquestionnum = intent.getExtras().getString("cquestionnum6");

        title_text = findViewById(R.id.titleText6);
        title_text.setText(cquestionnum);
        title_text.setVisibility(View.VISIBLE);

        cquestion_set = findViewById(R.id.questiontext6);
        cquestion_set.setText(cquestion6);
        cquestion_set.setVisibility(View.VISIBLE);

        always = findViewById(R.id.radio_Always);
        notmuch = findViewById(R.id.radio_not_much);
        never = findViewById(R.id.radio_never);
        often = findViewById(R.id.radio_often);
        next = findViewById(R.id.next6);
        score = findViewById(R.id.score6);
        s = String.valueOf(sum);
        score.setText("Score: "+s);
        score.setVisibility(View.VISIBLE);


        always.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Phone_call.class);
//                intent.putExtra("cnumber", number);
//                startActivity(intent);
                temp = 1;
//                sum = sum + 1;
                if(always.isChecked())
                {
                    often.setChecked(false);
                    notmuch.setChecked(false);
                    never.setChecked(false);


                }
                s = String.valueOf(sum);
                t = String.valueOf(temp);
                score.setText("Score: "+s+" + "+t);
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
                temp = 2;
//                sum = sum + 2;
                if(often.isChecked())
                {
                    always.setChecked(false);
                    notmuch.setChecked(false);
                    never.setChecked(false);

                }
                s = String.valueOf(sum);
                t = String.valueOf(temp);
                score.setText("Score: "+s+" + "+t);
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
                temp = 3;
//                sum = sum + 3;
                if(notmuch.isChecked())
                {
                    always.setChecked(false);
                    often.setChecked(false);
                    never.setChecked(false);

                }
                s = String.valueOf(sum);
                t = String.valueOf(temp);
                score.setText("Score: "+s+" + "+t);
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
                temp = 4;
//                sum = sum + 4;
                if(never.isChecked())
                {
                    always.setChecked(false);
                    often.setChecked(false);
                    notmuch.setChecked(false);

                }
                s = String.valueOf(sum);
                t = String.valueOf(temp);
                score.setText("Score: "+s+" + "+t);
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
                        sum = sum + temp;
                        Intent intent = new Intent(getApplicationContext(), Survey7.class);
                        intent.putExtra("cquestion7", question7);
                        intent.putExtra("cquestionnum7", "Question7");
                        intent.putExtra("csum", sum);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Survey6.this,condition,Toast.LENGTH_SHORT).show();
                    }
                }
            });





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
}
