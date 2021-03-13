package com.example.aurora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Results extends AppCompatActivity {

    ImageView imageView_logout;
    AlertDialog.Builder builder;
    DrawerLayout drawerLayout;
    Button logout,home,dashboard,notifications;
    int sum;
    private FirebaseAuth mAuth;
    EditText resultshow;
    Button resultconsultant,resulthappy;
    String s;
    String mTitle[] = {"Survey","Relax","Take a break","Chat","Forum","Consultation"};
    String mDescription[]={"Take a survey to know your psychological state","Throw your stress out","Enjoy some audios or videos","Share your thoughts with your friends","Help others by sharing your traumatic situations and way to tackle", "Emergency case"};
    int images[]={R.drawable.survey, R.drawable.relax, R.drawable.take_a_break, R.drawable.chat, R.drawable.forum, R.drawable.consultation };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_drawer);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        drawerLayout = findViewById(R.id.drawer_layout);

        builder = new AlertDialog.Builder(Results.this);
        imageView_logout = findViewById(R.id.imageView);

        imageView_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to Sign out from Aurora ?")
                        .setCancelable(false)
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),"Signing out",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"Cancelled",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Sign out");
                alert.show();

            }
        });

        home = findViewById(R.id.home);
        dashboard = findViewById(R.id.dashboard);
        notifications = findViewById(R.id.notifications);
//        logout = findViewById(R.id.logout);

//        logout.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
        dashboard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });
        notifications.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(getApplicationContext(), Notifications.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });

        View includedLayout_results = findViewById(R.id.itsresult);
//        phone_call =(ImageView)includedLayout_phone.findViewById(R.id.image_call);
//        listView =(ListView)includedLayout_results.findViewById(R.id.list_video);

        resultshow = (EditText)includedLayout_results.findViewById(R.id.resultshow);
        resultconsultant = (Button)includedLayout_results.findViewById(R.id.result_consultation);
        resulthappy = (Button)includedLayout_results.findViewById(R.id.result_happy);
        Button graph = (Button) includedLayout_results.findViewById(R.id.graph);
        Intent intent = getIntent();
//        cquestion8 = intent.getExtras().getString("cquestion8");
        sum = intent.getExtras().getInt("csum");
        s = String.valueOf(sum);
        resultshow.setText("Score: "+s);
        resultshow.setVisibility(View.VISIBLE);

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    FirebaseUser user = mAuth.getCurrentUser();
////        createNotificationChannel();
//                    if(user!=null){
//                Toast.makeText(Results.this,date,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(intent);
            }
//                }
        });

        if(sum>1 &&sum<=16)
        {
            resultconsultant.setVisibility(View.VISIBLE);
            resultconsultant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Phone_call.class);
//                intent.putExtra("cnumber", number);
////                startActivity(intent);
//                    Intent intent = new Intent(getApplicationContext(),Consultation.class);
////                intent.putExtra("cquestion7", question7);
////                intent.putExtra("cquestionnum7", "Question7");
////                    intent.putExtra("csum", sum);
//                    startActivity(intent);
                    Toast.makeText(Results.this,"Emergency case", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Consultation.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[5]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("title", mTitle[5]);
//                    intent.putExtra("description", mDescription[5]);
                    // also put your position
                    intent.putExtra("position", ""+5);
                    startActivity(intent);
                }
            });
        }
//        else if(sum>12&&sum<=25)
//        {
//            Toast.makeText(Results.this,"Suggested to hear quran", Toast.LENGTH_SHORT).show();
//            resultquran.setVisibility(View.VISIBLE);
//            resultquran.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    if(user!=null){
//                    Intent intent = new Intent(getApplicationContext(),Profile.class);
//                    startActivity(intent);
//                }
////                }
//            });
//
//        }
        else if(sum>16&&sum<=32)
        {
            Toast.makeText(Results.this,"You are all good", Toast.LENGTH_SHORT).show();
            resulthappy.setVisibility(View.VISIBLE);
            resulthappy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    FirebaseUser user = mAuth.getCurrentUser();
////        createNotificationChannel();
//                    if(user!=null){
                        Intent intent = new Intent(getApplicationContext(),Profile.class);
                        startActivity(intent);
                    }
//                }
            });

        }
    }

    //    zarif work nav drawer

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    //
    public void ClickLogo(View view){
        //close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open
            //close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }
}
