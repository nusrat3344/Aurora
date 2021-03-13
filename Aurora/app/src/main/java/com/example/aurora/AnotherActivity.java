package com.example.aurora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class AnotherActivity extends AppCompatActivity {

//    DrawerLayout drawerLayout;
    Button logout,home,dashboard,notifications;
    ImageView imageView;
    TextView title;
    int position;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
//        drawerLayout = findViewById(R.id.drawer_layout);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

//        home = findViewById(R.id.home);
//        dashboard = findViewById(R.id.dashboard);
//        notifications = findViewById(R.id.notifications);
//        logout = findViewById(R.id.logout);
//        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
//
//        logout.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        dashboard.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
//                startActivity(intent);
//            }
//        });
//        notifications.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                Intent intent = new Intent(getApplicationContext(),Notifications.class);
//                startActivity(intent);
//            }
//        });
//
//        home.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//                recreate();
//            }
//        });

//        ActionBar actionBar = getSupportActionBar();
//
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowHomeEnabled(true);
//            // aslo set in menifest
//        }


        imageView = findViewById(R.id.another_imageView);
        title = findViewById(R.id.titleText3);
        editText = findViewById(R.id.dev);

        if (position == 0) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = R.drawable.construction;
            String aTitle = " ";
            String aDescription = "Page Under Development";

            imageView.setImageResource(pic);
            title.setText(aTitle);
            editText.setText(aDescription);

           // actionBar.setTitle(aTitle);
        }

        if (position == 1) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            String aDescription = intent.getStringExtra("description");

            imageView.setImageResource(pic);
            title.setText(aTitle);
            editText.setText(aDescription);

            //actionBar.setTitle(aTitle);
        }

        if (position == 2) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            String aDescription = intent.getStringExtra("description");

            imageView.setImageResource(pic);
            title.setText(aTitle);
            editText.setText(aDescription);

           // actionBar.setTitle(aTitle);
        }

        if (position == 3) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            String aDescription = intent.getStringExtra("description");

            imageView.setImageResource(pic);
            title.setText(aTitle);
            editText.setText(aDescription);

            //actionBar.setTitle(aTitle);
        }

        if (position == 4) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            String aDescription = intent.getStringExtra("description");

            imageView.setImageResource(pic);
            title.setText(aTitle);
            editText.setText(aDescription);

           //actionBar.setTitle(aTitle);
        }
        if (position == 5) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            String aDescription = intent.getStringExtra("description");

            imageView.setImageResource(pic);
            title.setText(aTitle);
            editText.setText(aDescription);

            //actionBar.setTitle(aTitle);
        }

    }

    //    zarif work nav drawer

//    public void ClickMenu(View view){
//        openDrawer(drawerLayout);
//    }
//
//    public static void openDrawer(DrawerLayout drawerLayout) {
//        drawerLayout.openDrawer(GravityCompat.START);
//    }
//    //
//    public void ClickLogo(View view){
//        //close drawer
//        closeDrawer(drawerLayout);
//    }
//
//    public static void closeDrawer(DrawerLayout drawerLayout) {
//        //close drawer layout
//        //check condition
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            //when drawer is open
//            //close drawer
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //Close drawer
//        closeDrawer(drawerLayout);
//    }


}
