package com.example.aurora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Repeating_activity extends AppCompatActivity {


    DatabaseReference reff_repeating;
    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";

    private ImageView btn_smile;
    private ImageView btn_sad;
    private ImageView btn_angry;
    Member_hw_day member;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeating_activity_layout);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

//        Date date = Calendar.getInstance().getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
////        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM");
//        String formatteddate = df.format(date);

        btn_smile = (ImageView) findViewById(R.id.btn_smile);
        btn_sad = (ImageView) findViewById(R.id.btn_sad);
        btn_angry = (ImageView) findViewById(R.id.btn_angry);

        btn_smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity2();
            }
        });
        btn_sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity3();
            }
        });
        btn_angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity4();
            }
        });
    }

    public void openMainActivity2(){
        String text = "Happy";
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        String dash = " - ";
        String text_to_send = currentDate+dash+text;


        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");

//        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM");
        String formatteddate = df.format(date);
        formatteddate = formatteddate.replace("-", "|");

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        String gmail = (googleSignInAccount.getEmail());
//        gmail = "kashfaqraiyan@gmail.com";
//        String firstWord = "Magic Word";
        if(gmail.contains("@")){
            gmail= gmail.substring(0, gmail.indexOf("@"));
            System.out.println(gmail);
        }

        reff_repeating = FirebaseDatabase.getInstance().getReference().child("Daily Response");
        reff_repeating = reff_repeating.child(gmail);

        member = new Member_hw_day();
        member.setText(text_to_send);
        reff_repeating.child(formatteddate).setValue(member);

        Intent intent = new Intent(this, Profile.class);
//        intent.putExtra(EXTRA_TEXT, text_to_send);
        startActivity(intent);
    }

    public void openMainActivity3(){
        String text = "Sad";
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        String dash = " - ";
        String text_to_send = currentDate+dash+text;

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
//        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM");
        String formatteddate = df.format(date);
        formatteddate = formatteddate.replace("-", "|");

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        String gmail = (googleSignInAccount.getEmail());
//        gmail = "kashfaqraiyan@gmail.com";
//        String firstWord = "Magic Word";
        if(gmail.contains("@")){
            gmail= gmail.substring(0, gmail.indexOf("@"));
            System.out.println(gmail);
        }

        reff_repeating = FirebaseDatabase.getInstance().getReference().child("Daily Response");
        reff_repeating = reff_repeating.child(gmail);

        member = new Member_hw_day();
        member.setText(text_to_send);
        reff_repeating.child(formatteddate).setValue(member);

        Intent intent = new Intent(this, TakeaBreak.class);

        Bundle bundle = new Bundle();
        bundle.putInt("image", R.drawable.take_a_break);
        intent.putExtras(bundle);
        // now put title and description to another activity
        intent.putExtra("title", "Take a break");
        intent.putExtra("description", "You can hear some verse from Quran");
//        intent.putExtra(EXTRA_TEXT, text_to_send);
        startActivity(intent);
    }

    public void openMainActivity4(){
        String text = "Stressed";
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        String dash = " - ";
        String text_to_send = currentDate+dash+text;

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
//        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM");
        String formatteddate = df.format(date);
        formatteddate = formatteddate.replace("-", "|");

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        String gmail = (googleSignInAccount.getEmail());
//        gmail = "kashfaqraiyan@gmail.com";
//        String firstWord = "Magic Word";
        if(gmail.contains("@")){
            gmail= gmail.substring(0, gmail.indexOf("@"));
            System.out.println(gmail);
        }

        reff_repeating = FirebaseDatabase.getInstance().getReference().child("Daily Response");
        reff_repeating = reff_repeating.child(gmail);

        member = new Member_hw_day();
        member.setText(text_to_send);
        reff_repeating.child(formatteddate).setValue(member);

        Intent intent = new Intent(this, Relax.class);
//        intent.putExtra(EXTRA_TEXT, text_to_send);
        startActivity(intent);
    }
}
