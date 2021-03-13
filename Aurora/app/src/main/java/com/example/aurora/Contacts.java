package com.example.aurora;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class Contacts extends AppCompatActivity {

//    DrawerLayout drawerLayout;
    TextView name,mail;
//    Button logout,home,dashboard,notifications;
    ListView listView;
    String mTitle[] = {"Survey","Relax","Take a break","Chat","Forum","Consultation"};
    String mDescription[]={"Take a survey to know your psychological state","Throw your stress out","Enjoy some audios or videos","Share your thoughts with your friends","Help others by sharing your traumatic situations and way to tackle", "Emergency case"};
    int images[]={R.drawable.survey, R.drawable.relax, R.drawable.take_a_break, R.drawable.chat, R.drawable.forum, R.drawable.consultation };
    //so images and name are in array
    //paste images in drawable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

//        drawerLayout = findViewById(R.id.drawer_layout);


//        home = findViewById(R.id.home);
//        dashboard = findViewById(R.id.dashboard);
//        notifications = findViewById(R.id.notifications);
//        logout = findViewById(R.id.logout);
//        name = findViewById(R.id.name);
//        mail = findViewById(R.id.mail);

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

        listView = findViewById(R.id.listView_contacts);
        //then create an adapter class

        MyAdapter adapter =  new MyAdapter(this, mTitle,mDescription,images);
        listView.setAdapter(adapter);


        //now set item click on list view

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position ==0 ){
                    Toast.makeText(Contacts.this,"Take a survey to know your psychological state", Toast.LENGTH_SHORT).show();
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
                if(position == 1 ){
                    Toast.makeText(Contacts.this,"Throw your stress out", Toast.LENGTH_SHORT).show();
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
                if(position ==2 ){
                    Toast.makeText(Contacts.this,"Enjoy some audios or videos", Toast.LENGTH_SHORT).show();
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
                if(position ==3 ){
                    Toast.makeText(Contacts.this,"Share your thoughts with your friends", Toast.LENGTH_SHORT).show();
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
                if(position ==4 ){
                    Toast.makeText(Contacts.this,"Help others by sharing your traumatic situations and way to tackle", Toast.LENGTH_SHORT).show();
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
                if(position ==5 ){
                    Toast.makeText(Contacts.this,"Emergency case", Toast.LENGTH_SHORT).show();
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
        //item check is done now checking listview
    }
//    ListView listView;
//    String mTitle[] = {"facebook","whatsapp","twitter","instagram","youtube","linkedin","pininterest"};
//    String mDescription[]={"facebook description","whatsapp description","twitter description","instagram description","youtube description", "linkedin description","pininterest description"};
//    int images[]={R.drawable.facebook, R.drawable.whatsapp, R.drawable.twitter, R.drawable.instagram, R.drawable.youtube, R.drawable.linkedin, R.drawable.pininterest };
//    //so images and name are in array
//    //paste images in drawable

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        listView = findViewById(R.id.listView);
//        //then create an adapter class
//
//        MyAdapter adapter =  new MyAdapter(this, mTitle,mDescription,images);
//        listView.setAdapter(adapter);
//
//
//        //now set item click on list view
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                if(position ==0 ){
//                    Toast.makeText(Profile.this,"facebook description", Toast.LENGTH_SHORT).show();
//                }
//                if(position ==0 ){
//                    Toast.makeText(Profile.this,"whatsapp description", Toast.LENGTH_SHORT).show();
//                }
//                if(position ==0 ){
//                    Toast.makeText(Profile.this,"twitter description", Toast.LENGTH_SHORT).show();
//                }
//                if(position ==0 ){
//                    Toast.makeText(Profile.this,"instagram description", Toast.LENGTH_SHORT).show();
//                }
//                if(position ==0 ){
//                    Toast.makeText(Profile.this,"youtube description", Toast.LENGTH_SHORT).show();
//                }
//                if(position ==0 ){
//                    Toast.makeText(Profile.this,"linkedin description", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        //item check is done now checking listview
//    }

    class MyAdapter extends ArrayAdapter<String>{

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

//    zarif work nav drawer

//public void ClickMenu(View view){
//    openDrawer(drawerLayout);
//}
//
//    public static void openDrawer(DrawerLayout drawerLayout) {
//        drawerLayout.openDrawer(GravityCompat.START);
//    }
////
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
//    public void ClickHome(View view){
//        //Recreate activity
//        recreate();
//    }
//
//    public void ClickDashboard(View view){
//        //Redirect activity to dashboard
//        redirectActivity(this,Dashboard.class);
//    }
//
//    public void ClickNotifications(View view){
//        //Redirect activity to notifications
//        redirectActivity(this,Notifications.class);
//    }

//    public void ClickLogout(View view){
//        //Close app
//        logout(this);
//    }

//    public static void logout(final Activity activity) {
//        //Initialize alert dialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        //Set title
//        builder.setTitle("Logout");
//        //Set message
//        builder.setMessage("Are you sure you want to logout?");
//        //Positive yes button
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //Finish activity
//                activity.finishAffinity();
//                //Exit app
//                System.exit(0);
//            }
//        });
//        //Negative no button
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //Dismiss dialog
//                dialog.dismiss();
//            }
//        });
//        //show dialog
//        builder.show();
//    }


//    public static void redirectActivity(Activity activity,Class aClass){
//        //Initialize intent
//        Intent intent = new Intent(activity,aClass);
//        //Set flag
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        //Start activity
//        activity.startActivity(intent);
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        //Close drawer
//        closeDrawer(drawerLayout);
//    }
}