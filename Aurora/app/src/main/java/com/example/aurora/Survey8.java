package com.example.aurora;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Survey8 extends AppCompatActivity {

    String condition = "please select a field";
//    String question7="No more query";
    long maxID = 1;
    DatabaseReference reff;
    Member member;
    int sum,temp ;
    String s,t;
    Button next;
    String cquestion8,cquestionnum;
    EditText cquestion_set;
    RadioButton always,often,notmuch,never;
    EditText score;
    TextView title_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey8);
        final GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        reff = FirebaseDatabase.getInstance().getReference().child("Users");

        String gmail = (googleSignInAccount.getEmail());
//        gmail = "kashfaqraiyan@gmail.com";
//        String firstWord = "Magic Word";
        if(gmail.contains("@")){
            gmail= gmail.substring(0, gmail.indexOf("@"));
            System.out.println(gmail);
        }

        reff = reff.child(gmail);
        member = new Member();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                    maxID = 1 + (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Intent intent = getIntent();
        cquestion8 = intent.getExtras().getString("cquestion8");
        sum = intent.getExtras().getInt("csum");
        cquestionnum = intent.getExtras().getString("cquestionnum8");

        title_text = findViewById(R.id.titleText8);
        title_text.setText(cquestionnum);
        title_text.setVisibility(View.VISIBLE);

        cquestion_set = findViewById(R.id.questiontext8);
        cquestion_set.setText(cquestion8);
        cquestion_set.setVisibility(View.VISIBLE);

        always = findViewById(R.id.radio_Always);
        notmuch = findViewById(R.id.radio_not_much);
        never = findViewById(R.id.radio_never);
        often = findViewById(R.id.radio_often);
        next = findViewById(R.id.next8);
        score = findViewById(R.id.score8);
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
                        String gmail = googleSignInAccount.getEmail();
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
                        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM");
                        String formatteddate = df.format(date);
                        String formatteddate2 = df2.format(date);
                        formatteddate = formatteddate.replace("-", "|");
                        formatteddate2 = formatteddate2.replace("-", "|");

//                        String date_month_year = formatteddate;

                        Calendar c = Calendar.getInstance();

                        int Hr24 = c.get(Calendar.HOUR_OF_DAY);
                        int Min = c.get(Calendar.MINUTE);

                        String sHr24 = Integer.toString(Hr24);
                        String sMin = Integer.toString(Min);

                        String time = sHr24 + ":" + sMin;

                        sum = sum + temp;

//                member.setGmail(gmail);
//                        member.setDate(formatteddate);
                        member.setDate(formatteddate2);
                        member.setTime(time);
                        String s_sum = Integer.toString(sum);
                        member.setResult(s_sum);
//                reff.push().setValue(member);
                        reff.child(formatteddate).setValue(member);
                        Toast.makeText(Survey8.this, "submitted", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), Results.class);
//                intent.putExtra("cquestion7", question7);
//                intent.putExtra("cquestionnum7", "Question7");
                        intent.putExtra("csum", sum);
                        startActivity(intent);
                    }
                    else{
//                        condition = condition.back;
//                        Toast toast = Toast.makeText(Survey8.this, condition, Toast.LENGTH_LONG);
//                        toast.getView().setBackgroundColor(Color.parseColor("#FF0000"));
//                        toast.show();
                        Toast.makeText(Survey8.this,condition,Toast.LENGTH_SHORT).show();
//                        Toast.
//                        toast.show();
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
