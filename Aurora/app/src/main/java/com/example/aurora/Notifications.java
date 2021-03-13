package com.example.aurora;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Notifications extends AppCompatActivity {

    Integer i = 1;
    DatabaseReference reff;
    Member_hw_day member;
    ImageView imageView_logout;
    AlertDialog.Builder builder;
    DrawerLayout drawerLayout;
    Button logout,home,dashboard,notifications;
    Switch sw;
    TimePickerDialog picker;
    EditText eText;
    Button btnGet;
    TextView tvw;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);


        createNotificationChannel();

        drawerLayout = findViewById(R.id.drawer_layout);

        builder = new AlertDialog.Builder(Notifications.this);
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
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
//        name = findViewById(R.id.name);
//        mail = findViewById(R.id.mail);
        sw = (Switch) findViewById(R.id.switch1);
        eText=(EditText) findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
//        eText.setText("08:00");

        button = findViewById(R.id.button);
        final int[] hour = new int[1];
        final int[] minute = new int[1];
        final int[] am_pm = new int[1];
        hour[0] = 8;
        minute[0] = 0;

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
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

                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });
        notifications.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                recreate();
            }
        });

        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });


//        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        String gmail = (googleSignInAccount.getEmail());
//        gmail = "kashfaqraiyan@gmail.com";
//        String firstWord = "Magic Word";
        if(gmail.contains("@")){
            gmail= gmail.substring(0, gmail.indexOf("@"));
            System.out.println(gmail);
        }
        if(i==1) {
            reff = FirebaseDatabase.getInstance().getReference().child("Notification Status");
            reff = reff.child(gmail);


            reff.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.exists()) {

                        System.out.println("raad rafsan");
                        String d = snapshot.child("Status").child("text").getValue().toString();
                        System.out.println(d);

                        if (d == "on")
                            sw.setChecked(true);
                        else
                            sw.setChecked(false);

                        i=2;

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        }


        if(i==2) {
            if (sw.isChecked()) {

                member = new Member_hw_day();
                member.setText("on");
                reff.child("Status").setValue(member);

                eText.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                // The toggle is enabled
                eText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar cldr = Calendar.getInstance();
                        hour[0] = cldr.get(Calendar.HOUR);
                        minute[0] = cldr.get(Calendar.MINUTE);
                        am_pm[0] = cldr.get(Calendar.AM_PM);
                        // time picker dialog
                        picker = new TimePickerDialog(Notifications.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                        eText.setText(sHour + ":" + sMinute);
                                    }
                                }, hour[0], minute[0], true);
                        picker.show();
                    }
                });
                //        btnGet=(Button)findViewById(R.id.button1);
                //        btnGet.setOnClickListener(new View.OnClickListener() {
                //            @Override
                //            public void onClick(View v) {
                //                tvw.setText( eText.getText());
                //            }
                //        });

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar_start_of_the_day = Calendar.getInstance();
                        Calendar calendar_work_hour = Calendar.getInstance();

                        calendar_start_of_the_day.set(Calendar.HOUR_OF_DAY, 2);
                        calendar_start_of_the_day.set(Calendar.MINUTE, 24);

                        calendar_work_hour.set(Calendar.HOUR_OF_DAY, hour[0]);
                        calendar_work_hour.set(Calendar.MINUTE, minute[0]);

                        Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
                        intent.setAction("MY_NOTIFICATION_MESSAGE");

                        Intent intent_daily = new Intent(getApplicationContext(), Notification_receiver.class);
                        intent_daily.setAction("MY_NOTIFICATION_MESSAGE_DAILY");

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        PendingIntent pendingIntent_daily = PendingIntent.getBroadcast(getApplicationContext(), 200, intent_daily, PendingIntent.FLAG_UPDATE_CURRENT);

                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar_work_hour.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                        AlarmManager alarmManager_daily = (AlarmManager) getSystemService(ALARM_SERVICE);
//                    alarmManager_daily.setRepeating(AlarmManager.RTC_WAKEUP, calendar_start_of_the_day.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent_daily);
                        alarmManager_daily.cancel(pendingIntent_daily);

                        Intent intent_home = new Intent(getApplicationContext(), Profile.class);
                        startActivity(intent_home);

                    }
                });
            } else {


                Calendar calendar_start_of_the_day = Calendar.getInstance();
                Calendar calendar_work_hour = Calendar.getInstance();

                calendar_start_of_the_day.set(Calendar.HOUR_OF_DAY, 2);
                calendar_start_of_the_day.set(Calendar.MINUTE, 24);

                calendar_work_hour.set(Calendar.HOUR_OF_DAY, hour[0]);
                calendar_work_hour.set(Calendar.MINUTE, minute[0]);

                Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
                intent.setAction("MY_NOTIFICATION_MESSAGE");

                Intent intent_daily = new Intent(getApplicationContext(), Notification_receiver.class);
                intent_daily.setAction("MY_NOTIFICATION_MESSAGE_DAILY");

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                PendingIntent pendingIntent_daily = PendingIntent.getBroadcast(getApplicationContext(), 200, intent_daily, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);

                AlarmManager alarmManager_daily = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager_daily.cancel(pendingIntent_daily);
//                    Intent intent_home = new Intent(getApplicationContext(),Profile.class);
//                    startActivity(intent_home);

                member = new Member_hw_day();
                member.setText("off");
                reff.child("Status").setValue(member);

                eText.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE);

//                    Intent intent = new Intent(Notifications.this, Notification_receiver.class);
//                    PendingIntent sender = PendingIntent.getBroadcast(Notifications.this, 0, intent, 0);
//                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

//                    alarmManager.cancel(sender);
//                    cancelAll();
                // The toggle is disabled
            }
        }


        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    member = new Member_hw_day();
                    member.setText("on");
                    reff.child("Status").setValue(member);

                    eText.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    // The toggle is enabled
                    eText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Calendar cldr = Calendar.getInstance();
                            hour[0] = cldr.get(Calendar.HOUR);
                            minute[0] = cldr.get(Calendar.MINUTE);
                            am_pm[0] = cldr.get(Calendar.AM_PM);
                            // time picker dialog
                            picker = new TimePickerDialog(Notifications.this,
                                    new TimePickerDialog.OnTimeSetListener(){
                                        @Override
                                        public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                            eText.setText(sHour + ":" + sMinute);
                                        }
                                    }, hour[0], minute[0] ,true);
                            picker.show();
                        }
                    });
                    //        btnGet=(Button)findViewById(R.id.button1);
                    //        btnGet.setOnClickListener(new View.OnClickListener() {
                    //            @Override
                    //            public void onClick(View v) {
                    //                tvw.setText( eText.getText());
                    //            }
                    //        });

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Calendar calendar_start_of_the_day = Calendar.getInstance();
                            Calendar calendar_work_hour = Calendar.getInstance();

                            calendar_start_of_the_day.set(Calendar.HOUR_OF_DAY,2);
                            calendar_start_of_the_day.set(Calendar.MINUTE, 24);

                            calendar_work_hour.set(Calendar.HOUR_OF_DAY, hour[0]);
                            calendar_work_hour.set(Calendar.MINUTE, minute[0]);

                            Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
                            intent.setAction("MY_NOTIFICATION_MESSAGE");

                            Intent intent_daily = new Intent(getApplicationContext(), Notification_receiver.class);
                            intent_daily.setAction("MY_NOTIFICATION_MESSAGE_DAILY");

                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            PendingIntent pendingIntent_daily = PendingIntent.getBroadcast(getApplicationContext(), 200, intent_daily, PendingIntent.FLAG_UPDATE_CURRENT);

                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar_work_hour.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                            AlarmManager alarmManager_daily = (AlarmManager) getSystemService(ALARM_SERVICE);
//                            alarmManager_daily.setRepeating(AlarmManager.RTC_WAKEUP, calendar_start_of_the_day.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent_daily);
                            alarmManager_daily.cancel(pendingIntent_daily);
                            Intent intent_home = new Intent(getApplicationContext(), Profile.class);
                            startActivity(intent_home);

                        }
                    });
                } else {


                    Calendar calendar_start_of_the_day = Calendar.getInstance();
                    Calendar calendar_work_hour = Calendar.getInstance();

                    calendar_start_of_the_day.set(Calendar.HOUR_OF_DAY,2);
                    calendar_start_of_the_day.set(Calendar.MINUTE, 24);

                    calendar_work_hour.set(Calendar.HOUR_OF_DAY, hour[0]);
                    calendar_work_hour.set(Calendar.MINUTE, minute[0]);

                    Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
                    intent.setAction("MY_NOTIFICATION_MESSAGE");

                    Intent intent_daily = new Intent(getApplicationContext(), Notification_receiver.class);
                    intent_daily.setAction("MY_NOTIFICATION_MESSAGE_DAILY");

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    PendingIntent pendingIntent_daily = PendingIntent.getBroadcast(getApplicationContext(), 200, intent_daily, PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);

                    AlarmManager alarmManager_daily = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager_daily.cancel(pendingIntent_daily);
//                    Intent intent_home = new Intent(getApplicationContext(),Profile.class);
//                    startActivity(intent_home);

                    member = new Member_hw_day();
                    member.setText("off");
                    reff.child("Status").setValue(member);

                    eText.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);

//                    Intent intent = new Intent(Notifications.this, Notification_receiver.class);
//                    PendingIntent sender = PendingIntent.getBroadcast(Notifications.this, 0, intent, 0);
//                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

//                    alarmManager.cancel(sender);
//                    cancelAll();
                    // The toggle is disabled
                }
            }
        });
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "lemubit Channel";
            String description = "You gotta listen";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("lemubitNotify", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
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
//        //Redirect activity to Dashboard
//        Profile.redirectActivity(this,Dashboard.class);
//
//    }
//
//    public void ClickNotifications(View view){
//        recreate();
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