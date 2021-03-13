package com.example.aurora;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    DatabaseReference reff_retrieve;
    TextView date_graph,result_graph,time_graph;
    Button retrieve,response;
    ImageView imageView_logout;
    AlertDialog.Builder builder;
    DrawerLayout drawerLayout;
    Button logout,home,dashboard,notifications;
    final ArrayList<String> aList = new ArrayList<String>();
//    String[] xAxis = new String[]{" "," "," "," "," "," "," "," "," "," "," "," "," "};
    String [] xAxis = new String[100];


    //barchart variables
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet,barDataSet1;
    ArrayList barEntries,barEntries1;


    Integer index = 1 ;
    Integer flag = 0;
    float x = 1f;
//    float xLabel =1f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        final GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);


        for (int i=0; i<100; i++) xAxis [i] = " ";
        barEntries = new ArrayList<>();
        barEntries1 = new ArrayList<>();

//        barEntries.add(new BarEntry(1f,2));
//        barEntries.add(new BarEntry(2f,4));
//        barEntries.add(new BarEntry(3f,1));
//        barEntries.add(new BarEntry(4f,5));
//        barEntries.add(new BarEntry(5f,3));
//        barEntries.add(new BarEntry(6f,2));
//        barEntries.add(new BarEntry(7f,2));




        final String[] xAxisLables = new String[1000] ;
        final Float[] yValues = new Float[1000];

        drawerLayout = findViewById(R.id.drawer_layout);

        builder = new AlertDialog.Builder(Dashboard.this);
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

        retrieve = findViewById(R.id.retrieve);
        response = findViewById(R.id.daily_response);

        response.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });


        retrieve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                String gmail = (googleSignInAccount.getEmail());
                if(gmail.contains("@")){
                    gmail= gmail.substring(0, gmail.indexOf("@"));
                    System.out.println(gmail);
                }

//                reff = reff.child(gmail);
//                reff_retrieve = FirebaseDatabase.
//                reff_retrieve = FirebaseDatabase.getInstance().getReference("Users").child(gmail).child("26|02|21");
                reff_retrieve = FirebaseDatabase.getInstance().getReference("Users").child(gmail);
                System.out.println("ahareeeeeeee1111");
                reff_retrieve.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        System.out.println("ahareeeeeeee222");
                        flag = 1;
                        if (datasnapshot.exists()) {

                            System.out.println("tadaaaaaaaaaaaaa3333");

                            for(DataSnapshot dataSnap : datasnapshot.getChildren()){
                                String d = dataSnap.child("date").getValue().toString();
                                String r = dataSnap.child("result").getValue().toString();
                                System.out.println(d+" abc\n"+r+" abc");
                                Float n = Float.parseFloat(r );

                                xAxis [index] = d;
                                yValues [index] = n;
                                System.out.println(xAxis[index]+"\n"+yValues[index]);

                                if(n<=20)
                                    barEntries.add(new BarEntry(x,n));
                                else
                                    barEntries1.add(new BarEntry(x,n));
                                x = x+1f;
                                index++;



                            }
//

//                            barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(aList));

                            barChart = findViewById(R.id.barChart);
                            barDataSet = new BarDataSet(barEntries,"Data Set");//FF4500
                            barDataSet.setColors(ColorTemplate.rgb("#B22222"));
                            barDataSet1 = new BarDataSet(barEntries1,"Data Set1");
                            barDataSet1.setColors(ColorTemplate.rgb("#FFFA7E4D"));

                            barData = new BarData(barDataSet,barDataSet1);
                            
                            barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxis));

                            barChart.setData(barData);
                            barChart.animateY(5000);


                            barDataSet.setValueTextColor(Color.WHITE);

                            barDataSet.setValueTextSize(16f);

                            barDataSet1.setValueTextColor(Color.WHITE);

                            barDataSet1.setValueTextSize(16f);



//                            String t = datasnapshot.child("time").getValue(String.class);
//                            String d = datasnapshot.child("date").getValue(String.class);
//                            String r = datasnapshot.child("result").getValue(String.class);
//
//
//                            System.out.println("noOoOoOoO" +t+"hello"+d+"hello"+r+"hello");
//
//                            date_graph.setText(d);
//                            time_graph.setText(t);
//                            result_graph.setText(r);
//
//                            System.out.println("yesssssssssss");
                        }
                        else{
                            date_graph.setText("hello");
                            time_graph.setText("hunny");
                            result_graph.setText("bunny");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                String [] xlabel = new String[index];
//
//                for (int i = 0 ;i<index; i++){
////                    System.out.println(xAxisLables[i]);
//                    xlabel[i] = xAxisLables[i];
//                }


            }
        });
//    result_graph,time_graph;
//        logout = findViewById(R.id.logout);
//        name = findViewById(R.id.name);
//        mail = findViewById(R.id.mail);
//        if(googleSignInAccount != null){
//            name.setText(googleSignInAccount.getDisplayName());
//            mail.setText(googleSignInAccount.getEmail());
//        }
//        logout.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
//            }
//        });
        dashboard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                recreate();
            }
        });
        notifications.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(getApplicationContext(),Notifications.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(getApplicationContext(),Profile.class);
                startActivity(intent);
            }
        });
    }

    public void ClickMenu(View view){
        Profile.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        //close drawer
        Profile.closeDrawer(drawerLayout);
    }

//    public void ClickHome(View view){
//        //Redirect activity to home
//        Profile.redirectActivity(this,Profile.class);
//    }
//
//    public void ClickDashboard(View view){
//        recreate();
//    }
//
//    public void ClickNotifications(View view){
//        //Redirect activity to Notifications
//        Profile.redirectActivity(this, com.example.aurora.Notifications.class);
//    }

//    public void ClickLogout(View view){
//        //Close app
//        Profile.logout(this);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close Drawer
        Profile.closeDrawer(drawerLayout);
    }
}