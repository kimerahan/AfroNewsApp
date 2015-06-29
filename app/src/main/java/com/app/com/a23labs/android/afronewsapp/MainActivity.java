    package com.app.com.a23labs.android.afronewsapp;


    import android.app.ProgressDialog;
    import android.content.Intent;
    import android.content.res.Configuration;
    import android.os.AsyncTask;
    import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
    import android.util.Log;
    import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.ArrayList;


    public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {
        private ViewPager viewPager;
        private TabsPagerAdapter mAdapter;
        private ActionBar actionBar;
        // Tab titles
        private String[] tabs = { "Top News", "Tech", "Others","Health","Gossip" };
        private ListView mDrawerList;
        private DrawerLayout mDrawerLayout;
        private ArrayAdapter<String> lAdapter;
        private ActionBarDrawerToggle mDrawerToggle;
        private String mActivityTitle;

        // Declare Variables
        JSONObject jsonobject;
        JSONArray jsonArray;
        ListView listview;
        ListViewAdapter adapter;
        ProgressDialog mProgressDialog;
        ArrayList<Devotional> arraylist;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mDrawerList = (ListView)findViewById(R.id.navList);mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
            mActivityTitle = getTitle().toString();


            addDrawerItems();
            setupDrawer();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            // Initilization
            viewPager = (ViewPager) findViewById(R.id.pager);
            actionBar = getSupportActionBar();
            mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

            viewPager.setAdapter(mAdapter);
           // actionBar.setHomeButtonEnabled(false);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            // Adding Tabs
            for (String tab_name : tabs) {
               actionBar.addTab(actionBar.newTab().setText(tab_name)
                       .setTabListener(this));
            }

            /**
             * on swiping the viewpager make respective tab selected
             * */
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


                public void onPageSelected(int position) {
                    // on changing the page
                    // make respected tab selected
                   actionBar.setSelectedNavigationItem(position);
                }


                public void onPageScrolled(int arg0, float arg1, int arg2) {


                }


                public void onPageScrollStateChanged(int arg0) {
                }
            });



            Log.d("jsonArray", "starting"
            );
            // Get the view from listview_main.xml
            setContentView(R.layout.listview_main);
            // Execute DownloadJSON AsyncTask
            new DownloadJSON().execute();
        }


        @Override
        public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
         viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

        }
       private void addDrawerItems() {
        String[] osArray = {"NEWS", "TECH", "ABOUT", "HEALTH", "LEARN"};
        lAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(lAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if (position == 0) {
                   Toast.makeText(MainActivity.this, "TECH", Toast.LENGTH_SHORT).show();
                   // Toast.makeText(MainActivity.this, "NEWS", Toast.LENGTH_LONG).show();
                }
                else if (position == 1) {
                    Toast.makeText(MainActivity.this, "TECH", Toast.LENGTH_SHORT).show();
                }
                else if (position == 2) {
                    Toast.makeText(MainActivity.this, "ABOUT", Toast.LENGTH_SHORT).show();
                }
                else if (position == 3) {
                    Toast.makeText(MainActivity.this, "HEALTH", Toast.LENGTH_SHORT).show();
                }
                else if (position == 4) {
                    Toast.makeText(MainActivity.this, "LEARN", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

             private void setupDrawer(){
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

    /** Called when a drawer has settled in a completely open state. */
           public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            getSupportActionBar().setTitle("OTHERS");
            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

    /** Called when a drawer has settled in a completely closed state. */
         public void onDrawerClosed(View view) {
            super.onDrawerClosed(view);
            getSupportActionBar().setTitle(mActivityTitle);
            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            };

            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerLayout.setDrawerListener(mDrawerToggle);

            }


         protected void onPostCreate(Bundle savedInstanceState){
            super.onPostCreate(savedInstanceState);
            // Sync the toggle state after onRestoreInstanceState has occurred.
            mDrawerToggle.syncState();
            }


             public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            mDrawerToggle.onConfigurationChanged(newConfig);
            }

              public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            // Activate the navigation drawer toggle
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }

            return super.onOptionsItemSelected(item);
          }



        // DownloadJSON AsyncTask
        private class DownloadJSON extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Create a progressdialog
                mProgressDialog = new ProgressDialog(MainActivity.this);
                // Set progressdialog title
                mProgressDialog.setTitle("loading");
                // Set progressdialog message
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(false);
                // Show progressdialog
                mProgressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                // Create an array
                arraylist = new ArrayList<Devotional>();
                // Retrieve JSON Objects from the given URL address

                try {
                    jsonobject = JSONfunctions
                            .getJSONfromURL("http://phaneroostream.cloudapp.net/phaneroo/dashboard/api/devotionals.json");


                    JSONArray jsonArray
                            = jsonobject.
                            getJSONArray("devotionals");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonobject = jsonArray.getJSONObject(i);

                        // Set the JSON Objects into the array
                        arraylist.add(new Devotional(jsonobject.getString("title"),
                                jsonobject.getString("body").substring(0, 50),
                                jsonobject.getString("artlink")));
                    }
                } catch (JSONException e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void args) {
                // Locate the listview in listview_main.xml
                listview = (ListView) findViewById(R.id.listview);
                // Pass the results into ListViewAdapter.java
                adapter = new ListViewAdapter(MainActivity.this, arraylist);
                // Set the adapter to the ListView
                listview.setAdapter(adapter);
                // Close the progressdialog
                mProgressDialog.dismiss();
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        arraylist.get(position);
                        Intent intent = new Intent(MainActivity.this, SingleItemView.class);
                        intent.putExtra("title",arraylist.get(position).getTitle());
                        intent.putExtra("body", arraylist.get(position).getBody());
                        intent.putExtra("artlink", arraylist.get(position).getArtlink());
                        startActivity(intent);

                    }
                });


            }
        }

    }