package com.example.aurora;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.data.BarEntry;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


//        Intent intent = getIntent();
//        String text = "ami Nusrat tui ke";

        ListView listView = (ListView) findViewById(R.id.track_list);

        final ArrayList<String> arrayList=new ArrayList<String>();
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
//        arrayList.add(text);

        reff = FirebaseDatabase.getInstance().getReference().child("Daily Response");

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        String gmail = (googleSignInAccount.getEmail());
//        gmail = "kashfaqraiyan@gmail.com";
//        String firstWord = "Magic Word";
        if(gmail.contains("@")){
            gmail= gmail.substring(0, gmail.indexOf("@"));
            System.out.println(gmail+"hello");
        }

        reff = reff.child(gmail);

        reff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if (snapshot.exists()) {

                    System.out.println("tadaaaaaaaaaaaaa3333");

                    for (DataSnapshot dataSnap : snapshot.getChildren()) {
                        String value = dataSnap.getValue().toString();
                        System.out.println(value + " abc\n");
                        arrayList.add(value);
                    }
                    arrayAdapter.notifyDataSetChanged();

                }
                else{
                    System.out.println("Tadaaaaaaaaaaaaa");
                }
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}