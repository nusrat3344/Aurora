package com.example.aurora;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class VideoActivity  extends AppCompatActivity{

    ImageView imageView_logout;
    AlertDialog.Builder builder;
    DrawerLayout drawerLayout;
//    String number;
    Button logout,home,dashboard,notifications;
    String mTitle[] = {"NO EXCUSES","STOP WASTING TIME","GET UP AND GET IT DONE","Cute Baby Siblings","The Seed"};
    String mDescription[]={"3:20","3:55","4:30","10:11","1:22"};
    int images[]={R.drawable.vid1,R.drawable.vid2,R.drawable.vidx, R.drawable.vid4, R.drawable.vid5};
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_drawer);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        drawerLayout = findViewById(R.id.drawer_layout);

        builder = new AlertDialog.Builder(VideoActivity.this);
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


        View includedLayout_videoactivity = findViewById(R.id.itsvideo);
//        phone_call =(ImageView)includedLayout_phone.findViewById(R.id.image_call);
//        listView =(ListView)includedLayout_takeabreak.findViewById(R.id.listView_contacts);

//        imageView = (ImageView)includedLayout_takeabreak.findViewById(R.id.another_imageView_takeabreak);
////        phone_call = findViewById(R.id.image_call);
//        title = (TextView)includedLayout_takeabreak.findViewById(R.id.titleText_takeabreak);
////        description = findViewById(R.id.descriptionText);


        listView = (ListView)includedLayout_videoactivity.findViewById(R.id.list_video);
        //then create an adapter class

        VideoActivity.MyAdapter adapter =  new VideoActivity.MyAdapter(this, mTitle,mDescription,images);
        listView.setAdapter(adapter);


        //now set item click on list view

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position ==0 ){
                    Toast.makeText(VideoActivity.this,"Opening Youtube", Toast.LENGTH_SHORT).show();
//                    number = mDescription[2];
//                    Intent intent = new Intent(getApplicationContext(), Videoplay.class);
//                    startActivity(intent);
//                    Intent intent = new Intent(getApplicationContext(), Videoplay.class);
//                    // this intent put our 0 index image to another activity
//                    Bundle bundle = new Bundle();
////                    bundle.putInt("image", images[2]);
//                    intent.putExtras(bundle);
                    // now put title and description to another activity
//                    intent.putExtra("title", mTitle[2]);
//                    intent.putExtra("description", mDescription[2]);
                    // also put your position
//                    intent.putExtra("position", ""+2);
//                    startActivity(intent);
                    gotoUrl("https://www.youtube.com/watch?v=wnHW6o8WMas&t=18s");
                }
                if(position == 1 ){
                    Toast.makeText(VideoActivity.this,"Opening Youtube", Toast.LENGTH_SHORT).show();
                   gotoUrl("https://www.youtube.com/watch?v=QTB1YiWxxKU");
                }
                if(position ==2 ){
                    Toast.makeText(VideoActivity.this,"Opening Youtube", Toast.LENGTH_SHORT).show();
                    gotoUrl("https://www.youtube.com/watch?v=HwLK9dBQn0g");
                }
                if(position ==3 ){
                    Toast.makeText(VideoActivity.this,"Opening Youtube", Toast.LENGTH_SHORT).show();
                    gotoUrl("https://www.youtube.com/watch?v=eAnkDcH1mfw&t=481s");
                }
                if(position ==4 ){
                    Toast.makeText(VideoActivity.this,"Opening Youtube", Toast.LENGTH_SHORT).show();
                    gotoUrl("https://www.youtube.com/watch?v=sVPYIRF9RCQ");
                }

            }
        });
    }

    //URL Opener
    public void gotoUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c,R.layout.row, R.id.textView1, title);
            this.context=c;
            this.rTitle = title;
            this.rDescription=description;
            this.rImgs=imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image_contacts);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

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
