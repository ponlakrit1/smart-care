package com.example.smartcare;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DialogInterface.OnClickListener{

    private DrawerLayout drawer;
    SharedPreferences prefs;

    TextView headername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Set toolbar string
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);
        getSupportActionBar().setTitle("หน้าแรก");

        // Set header name
        View header = navigationView.getHeaderView(0);

        prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        String getName = prefs.getString("name", null);
        String getLastname = prefs.getString("lastname", null);

        headername = (TextView) header.findViewById(R.id.headername);
        headername.setText(getName+" "+getLastname);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("ต้องการออกจากระบบ ?");
        builder.setPositiveButton("ตกลง", this);
        builder.setNegativeButton("ยกเลิก", this);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        getSupportActionBar().setTitle(item.getTitle());

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new HomeFragment()).commit();
        } else if (id == R.id.nav_food) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new FoodFragment()).commit();
        } else if (id == R.id.nav_cal) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new CalFragment()).commit();
        } else if (id == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new ProfileFragment()).commit();
        } else if (id == R.id.nav_logout){
            Intent myIntent = new Intent(Main2Activity.this, MainActivity.class);
            Main2Activity.this.startActivity(myIntent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which==DialogInterface.BUTTON_POSITIVE) {
            finish();
        }
    }
}
