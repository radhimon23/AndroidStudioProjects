package com.example.gauravkapadiya.home;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button l1,reg,contact;


    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ListView mDrawerList;
    private List<DrawerItem> mDrawerItems;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l1=(Button)findViewById(R.id.list);
        reg=(Button)findViewById(R.id.reg);
        contact=(Button)findViewById(R.id.contact);

        clickevents();

        // Toolbar for actionbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(myToolbar);

        //setting TITLE of actionbar
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.app_name);



        //Navigation drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTitle = mDrawerTitle = getTitle();
        mDrawerList = (ListView) findViewById(R.id.left_drawer);



        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.nav_header_main, mDrawerList, false);
        mDrawerList.addHeaderView(header, null, false);
        prepareNavigationDrawerItems();
        setAdapter();
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, myToolbar,
                R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }
    public void clickevents(){
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,List_View.class);
                startActivity(i);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =new Intent(MainActivity.this,Login.class);
                startActivity(i1);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =new Intent(MainActivity.this,ContactUs.class);
                startActivity(i1);
            }
        });
    }



    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id1) {


            switch (position) {


                case 1:
                    //Home
                   // show_inteirstitial_ad();

                   /* //i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/
                    break;

                case 2:
                    //ADD Beard STYLE
                    /*Intent about = new Intent(Category.this, AddBeardStyle.class);

                    startActivity(about);*/
                    break;

                case 3:
                    //SEARCH

                   /* Intent search = new Intent(Category.this, Search.class);
                    startActivity(search);
*/
                    break;
                case 4:
                    //MY POST

                   /* Intent my = new Intent(Category.this, MyPost.class);
                    startActivity(my);*/

                    break;

                case 5:
                    //FAVOURITE

                  /*  Intent iet = new Intent(Category.this, AddToList.class);
                    startActivity(iet);*/


                    break;
                case 6:
                    //ABOUT US


                   /* Intent i = new Intent(Category.this, Aboutus.class);
                    startActivity(i);*/
                    break;
                case 7:
                    //CONTACT US
                  /*  String os = System.getProperty("os.version"); // OS version
                    String version = android.os.Build.VERSION.SDK;     // API Level
                    String device = android.os.Build.DEVICE;           // Device
                    String model = android.os.Build.MODEL;           // Model
                    String product = android.os.Build.PRODUCT;          // Product


                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setType("plain/text");
                    sendIntent.setData(Uri.parse("support@pragmainfotech.co.in"));
                    sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                    sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@pragmainfotech.co.in"});
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact Enquiry From Latest Beard Styles ");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Device Information:\nOs:" + os + "\nVersion:" + version + "\nDevice Model:" + model + "\nMessage:");

                    startActivity(sendIntent);
*/                    break;
                case 8:
                    //SHARE VIA
                    /*Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Install Latest Beard Styles see all the trendy and fashionable Beard Styles / Beard Cuts and be a trendsetter.\n Lastest Beard Styles: https://goo.gl/MwHDFw";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Lastest Beard Styles");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
*/                    break;
                case 9:
                    //RATE US
                  /*  Uri uri = Uri.parse("market://details?id=com.pragma.beardstyles&hl=en"
                    );
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.pragma.beardstyles&hl=en"
                                )));
                    }*/
                    break;
                case 10:
                    //MORE APPS
                   /* Intent in = new Intent(Category.this, Moreapps.class);
                    startActivity(in);*/


                    break;


            }
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    private void prepareNavigationDrawerItems() {
        mDrawerItems = new ArrayList<>();
        //DrawerItem(id,image,name);

        mDrawerItems.add(new DrawerItem("0", R.drawable.favorite, "Home"));
        mDrawerItems.add(new DrawerItem("1", R.drawable.favorite, "Add Beard Style"));
        mDrawerItems.add(new DrawerItem("2", R.drawable.favorite, "Search"));
        mDrawerItems.add(new DrawerItem("3", R.drawable.favorite, "My Post"));
        mDrawerItems.add(new DrawerItem("4", R.drawable.favorite, "Favourite"));
        mDrawerItems.add(new DrawerItem("5", R.drawable.favorite, "About us"));
        mDrawerItems.add(new DrawerItem("6", R.drawable.favorite, "Contact us"));
        mDrawerItems.add(new DrawerItem("7", R.drawable.favorite, "Share Via"));
        mDrawerItems.add(new DrawerItem("8", R.drawable.favorite, "Rate Us"));
        mDrawerItems.add(new DrawerItem("9", R.drawable.favorite, "More Apps"));


        Log.d("drawer items", mDrawerItems.get(1).getTitle());

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    private void setAdapter() {


        boolean isFirstType = true;
        Log.d("drawer items", mDrawerItems.get(1).getTitle());

        BaseAdapter adapter = new DrawerAdapter_home(this, mDrawerItems, isFirstType);

        mDrawerList.setAdapter(adapter);
    }
}

