package com.example.aurora;

import android.content.Context;
import android.content.Intent;
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

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class MusicActivity extends AppCompatActivity {


    ImageView imageView_logout;
    AlertDialog.Builder builder;
    DrawerLayout drawerLayout;
    //    String number;
    Button logout,home,dashboard,notifications;
    String mTitle[] = {"Music1","Music2","Music3","Music4","Music5","Music6"};
    String mDescription[]={"When Words Fail Music Speaks","When Words Fail Music Speaks","When Words Fail Music Speaks","When Words Fail Music Speaks","When Words Fail Music Speaks","When Words Fail Music Speaks"};
    int images[]={R.drawable.music,R.drawable.music,R.drawable.music,R.drawable.music,R.drawable.music,R.drawable.music};
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_drawer);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);


        drawerLayout = findViewById(R.id.drawer_layout);

        builder = new AlertDialog.Builder(MusicActivity.this);
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

        View includedLayout_music = findViewById(R.id.itsmusic);
        listView = (ListView)includedLayout_music.findViewById(R.id.list_music);
        //then create an adapter class

        MusicActivity.MyAdapter adapter =  new MusicActivity.MyAdapter(this, mTitle,mDescription,images);
        listView.setAdapter(adapter);


        //now set item click on list view

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position ==0 ){
                    Toast.makeText(MusicActivity.this,"When Words Fail Music Speaks", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), Musicplay.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
//                    bundle.putInt("image", images[2]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
//                    intent.putExtra("title", mTitle[2]);
//                    intent.putExtra("description", mDescription[2]);
                    // also put your position
//                    intent.putExtra("position", ""+2);
                    startActivity(intent);
//                    number = mDescription[0];
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
                    Toast.makeText(MusicActivity.this,"When Words Fail Music Speaks", Toast.LENGTH_SHORT).show();
//                    number = mDescription[1];

                    Intent intent = new Intent(getApplicationContext(), Musicplay1.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
//                    bundle.putInt("image", images[2]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
//                    intent.putExtra("title", mTitle[2]);
//                    intent.putExtra("description", mDescription[2]);
                    // also put your position
//                    intent.putExtra("position", ""+2);
                    startActivity(intent);
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
                    Toast.makeText(MusicActivity.this,"When Words Fail Music Speaks", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Musicplay2.class);
                    Bundle bundle = new Bundle();
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                if(position ==3 ){
                    Toast.makeText(MusicActivity.this,"When Words Fail Music Speaks", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Musicplay3.class);
                    Bundle bundle = new Bundle();
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                if(position ==4 ){
                    Toast.makeText(MusicActivity.this,"When Words Fail Music Speaks", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Musicplay4.class);
                    Bundle bundle = new Bundle();
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                if(position ==5 ){
                    Toast.makeText(MusicActivity.this,"When Words Fail Music Speaks", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Musicplay5.class);
                    Bundle bundle = new Bundle();
                    intent.putExtras(bundle);
                    startActivity(intent);
                }



            }
        });
    }
    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c,R.layout.row_takeabreak, R.id.textView3, title);
            this.context=c;
            this.rTitle = title;
            this.rDescription=description;
            this.rImgs=imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row_takeabreak, parent, false);
            ImageView images = row.findViewById(R.id.image_contacts);
            TextView myTitle = row.findViewById(R.id.textView3);
            TextView myDescription = row.findViewById(R.id.textView4);

            //now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);


            return row;
        }
    }

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
