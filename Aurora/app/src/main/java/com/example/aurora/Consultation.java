package com.example.aurora;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class Consultation extends AppCompatActivity {
    //    DrawerLayout drawerLayout;

    ImageView imageView_logout;
    AlertDialog.Builder builder;
    DrawerLayout drawerLayout;
    String number;
    Button logout,home,dashboard,notifications;
    ImageView imageView,phone_call,home_go;
    TextView title, description,time,availability;

    int position;
    ListView listView;
    String mTitle[] = {"Mr. Raiyan","Ms. Nusrat","Mr. Fardin","Mr. Rahib","Mr. Zarif","Ms. Rain"};
    String mDescription[]={"01642346022","01799131602","01622036553","01642346023","01620418953", "01861690013"};
    String available_time_start[]={"19","8","9","7","11","20"};
    String available_time_end[]={"21","9","10","9","13","22"};
    String available[]={"19:00-21:00","08:00-09:00","09:00-10:00","07:00-09:00","11:00-13:00","20:00-22:00"};
    int images[]={R.drawable.doc2, R.drawable.doc1, R.drawable.doc3, R.drawable.doc4, R.drawable.doc6, R.drawable.doc5 };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_drawaer);
        drawerLayout = findViewById(R.id.drawer_layout);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        builder = new AlertDialog.Builder(Consultation.this);
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


        View includedLayout = findViewById(R.id.itsconsultation);
//        Button insideTheIncludedLayout = (Button)includedLayout.findViewById(R.id.button1);
        imageView = (ImageView) includedLayout.findViewById(R.id.consultaion);
        title =(TextView)includedLayout.findViewById(R.id.titleText_consultation);
        phone_call =(ImageView)includedLayout.findViewById(R.id.image_call);
        listView =(ListView)includedLayout.findViewById(R.id.listView_consultation);
//        description =(TextView)includedLayout.findViewById(R.id.textView2);

//        imageView = findViewById(R.id.another_imageView);

//        home_go = findViewById(R.id.home);
//        title = findViewById(R.id.titleText3);
//        time = findViewById(R.id.time);
//        availability = findViewById(R.id.availability);
//        description = findViewById(R.id.descriptionText);


        Intent intent = getIntent();

        Bundle bundle = this.getIntent().getExtras();
        int pic = bundle.getInt("image");
        String aTitle = intent.getStringExtra("title");
        String aDescription = intent.getStringExtra("description");

        imageView.setImageResource(pic);
        title.setText(aTitle);
//        description.setText(aDescription);


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

//        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        imageView = findViewById(R.id.another_imageView);
//        phone_call = findViewById(R.id.image_call);
//        title = findViewById(R.id.titleText3);
//        Intent intent = getIntent();
//
//        Bundle bundle = this.getIntent().getExtras();
//        int pic = bundle.getInt("image");
//        String aTitle = intent.getStringExtra("title");
////        String aDescription = intent.getStringExtra("description");
//
//        imageView.setImageResource(pic);
//        title.setText(aTitle);

//        View includedLayout_phone = findViewById(R.id.itsconsultation);

        //then create an adapter class

        MyAdapter adapter =  new MyAdapter(this, mTitle,mDescription,images,available,available_time_start,available_time_end);
        listView.setAdapter(adapter);


        //now set item click on list view

//        home_go.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Profile.class);
////                intent.putExtra("cnumber", number);
//                startActivity(intent);
//            }
//        });

        phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Phone_call.class);
                number="";
                intent.putExtra("cnumber", number);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Calendar calendar = Calendar.getInstance();
                int hour24hrs = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                float hour_minutes = hour24hrs + (minutes/60);
                float time_start = Float.valueOf(available_time_start[position]);
                float time_end = Float.valueOf(available_time_end[position]);


                if(position ==0 ){
//                    Toast.makeText(Consultation.this,"Copied Contact of Mr.Raiyan", Toast.LENGTH_SHORT).show();
                    number = mDescription[0];
//                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
//                    // this intent put our 0 index image to another activity
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("image", images[0]);
//                    intent.putExtras(bundle);
//                    // now put title and description to another activity
//                    intent.putExtra("title", mTitle[0]);
//                    intent.putExtra("description", mDescription[0]);
//                    // also put your position
//                    intent.putExtra("position", ""+0);
//                    startActivity(intent);
                }
                if(position == 1 ){
//                    Toast.makeText(Consultation.this,"Copied Contact of Mrs.Nusrat", Toast.LENGTH_SHORT).show();
                    number = mDescription[1];
//                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
//                    // this intent put our 0 index image to another activity
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("image", images[1]);
//                    intent.putExtras(bundle);
//                    // now put title and description to another activity
//                    intent.putExtra("title", mTitle[1]);
//                    intent.putExtra("description", mDescription[1]);
//                    // also put your position
//                    intent.putExtra("position", ""+1);
//                    startActivity(intent);
                }
                if(position ==2 ){
//                    Toast.makeText(Consultation.this,"Copied Contact of Fardin sir", Toast.LENGTH_SHORT).show();
                    number = mDescription[2];
//                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
//                    // this intent put our 0 index image to another activity
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("image", images[2]);
//                    intent.putExtras(bundle);
//                    // now put title and description to another activity
//                    intent.putExtra("title", mTitle[2]);
//                    intent.putExtra("description", mDescription[2]);
//                    // also put your position
//                    intent.putExtra("position", ""+2);
//                    startActivity(intent);
                }
                if(position ==3 ){
//                    Toast.makeText(Consultation.this,"Copied Contact of Mr.Rahib", Toast.LENGTH_SHORT).show();
                    number = mDescription[3];
//                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
//                    // this intent put our 0 index image to another activity
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("image", images[3]);
//                    intent.putExtras(bundle);
//                    // now put title and description to another activity
//                    intent.putExtra("title", mTitle[3]);
//                    intent.putExtra("description", mDescription[3]);
//                    // also put your position
//                    intent.putExtra("position", ""+3);
//                    startActivity(intent);
                }
                if(position ==4 ){
//                    Toast.makeText(Consultation.this,"Copied Contact of Mr.A", Toast.LENGTH_SHORT).show();
                    number = mDescription[4];
//                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
//                    // this intent put our 0 index image to another activity
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("image", images[4]);
//                    intent.putExtras(bundle);
//                    // now put title and description to another activity
//                    intent.putExtra("title", mTitle[4]);
//                    intent.putExtra("description", mDescription[4]);
//                    // also put your position
//                    intent.putExtra("position", ""+4);
//                    startActivity(intent);
                }
                if(position ==5 ){
//                    Toast.makeText(Consultation.this,"Copied Contact of Mr.B", Toast.LENGTH_SHORT).show();
                    number = mDescription[5];
//                    Intent intent = new Intent(getApplicationContext(), Consultation.class);
//                    // this intent put our 0 index image to another activity
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("image", images[5]);
//                    intent.putExtras(bundle);
//                    // now put title and description to another activity
//                    intent.putExtra("title", mTitle[5]);
////                    intent.putExtra("description", mDescription[5]);
//                    // also put your position
//                    intent.putExtra("position", ""+5);
//                    startActivity(intent);
                }

                if(hour_minutes >= time_start && hour_minutes <= time_end) {
//                    myavailability.setText("AVAILABLE");
//                    myavailability.setTextColor(Color.parseColor("#4CAF50"));
                    Toast.makeText(Consultation.this,mTitle[position]+" is available", Toast.LENGTH_SHORT).show();
                    phone_call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), Phone_call.class);
                            intent.putExtra("cnumber", number);
                            startActivity(intent);
                        }
                    });
                }

                else {
//                    myavailability.setText("UNAVAILABLE");
//                    myavailability.setTextColor(Color.parseColor("#F44336"));
                    Toast.makeText(Consultation.this,mTitle[position]+" is unavailable", Toast.LENGTH_SHORT).show();
                    phone_call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), Phone_call.class);
                            number="";
                            intent.putExtra("cnumber", number);
                            startActivity(intent);
                        }
                    });

                }
                /*
                 if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[0]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("title", mTitle[0]);
                    intent.putExtra("description", mDescription[0]);
                    // also put your position
                    intent.putExtra("position", ""+0);
                    startActivity(intent);


                }
                if (position == 1) {
                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[1]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("title", mTitle[1]);
                    intent.putExtra("description", mDescription[1]);
                    // also put your position
                    intent.putExtra("position", ""+1);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[2]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("title", mTitle[2]);
                    intent.putExtra("description", mDescription[2]);
                    // also put your position
                    intent.putExtra("position", ""+2);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[3]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("title", mTitle[3]);
                    intent.putExtra("description", mDescription[3]);
                    // also put your position
                    intent.putExtra("position", ""+3);
                    startActivity(intent);
                }
                if (position == 4) {
                    Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[4]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("title", mTitle[4]);
                    intent.putExtra("description", mDescription[4]);
                    // also put your position
                    intent.putExtra("position", ""+4);
                    startActivity(intent);
                }
                 */
            }
        });


//        description.setText(aDescription);
//        phone_call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Phone_call.class);
//                intent.putExtra("cnumber", number);
//                startActivity(intent);
//            }
//        });




    }
    class MyAdapter extends ArrayAdapter<String>{

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];
        String rAvailability[];
        String rAvailability_start[];
        String rAvailability_end[];

        MyAdapter (Context c, String title[], String description[], int imgs[],String availability[],String availability_start[],String availability_end[]) {
            super(c, R.layout.row_contacts, R.id.textView1, title);
            this.context=c;
            this.rTitle = title;
            this.rDescription=description;
            this.rImgs=imgs;
            this.rAvailability = availability;
            this.rAvailability_start = availability_start;
            this.rAvailability_end = availability_end;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row_contacts, parent, false);
            ImageView images = row.findViewById(R.id.image_contacts);
            TextView myTitle = row.findViewById(R.id.textView3);
            TextView myDescription = row.findViewById(R.id.textView4);
            TextView mytime = row.findViewById(R.id.time);
            TextView myavailability = row.findViewById(R.id.availability);

            Calendar calendar = Calendar.getInstance();
            int hour24hrs = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);
            float hour_minutes = hour24hrs + (minutes/60);
            float time_start = Float.valueOf(rAvailability_start[position]);
            float time_end = Float.valueOf(rAvailability_end[position]);

            if(hour_minutes >= time_start && hour_minutes <= time_end) {
                myavailability.setText("AVAILABLE");
                myavailability.setTextColor(Color.parseColor("#32CD32"));
//                Toast.makeText(Consultation.this,"Doctor is available", Toast.LENGTH_SHORT).show();
//                phone_call.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(),Phone_call.class);
//                        intent.putExtra("cnumber", number);
//                        startActivity(intent);
//                    }
//                });
            }

            else {
                myavailability.setText("UNAVAILABLE");
                myavailability.setTextColor(Color.parseColor("#FF0000"));
//                Toast.makeText(Consultation.this,"Doctor is unavailable", Toast.LENGTH_SHORT).show();
            }
            //now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);
            mytime.setText(rAvailability[position]);


            return row;
        }
    }
//    class MyAdapter extends ArrayAdapter<String> {
//
//        Context context;
//        String rTitle[];
//        String rDescription[];
//        int rImgs[];
//
//        MyAdapter (Context c, String title[], String description[], int imgs[]) {
//            super(c,R.layout.row, R.id.textView1, title);
//            this.context=c;
//            this.rTitle = title;
//            this.rDescription=description;
//            this.rImgs=imgs;
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
//            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View row = layoutInflater.inflate(R.layout.row, parent, false);
//            ImageView images = row.findViewById(R.id.image);
//            TextView myTitle = row.findViewById(R.id.textView1);
//            TextView myDescription = row.findViewById(R.id.textView2);
//
//            //now set our resources on views
//            images.setImageResource(rImgs[position]);
//            myTitle.setText(rTitle[position]);
//            myDescription.setText(rDescription[position]);
//
//
//            return row;
//        }
//    }
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


