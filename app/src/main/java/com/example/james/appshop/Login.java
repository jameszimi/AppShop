package com.example.james.appshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String textsigup = getString(R.string.sigupms);
        String linktext = getString(R.string.sigupms);
        int start = textsigup.indexOf(linktext);
        int end = start+linktext.length();
        SpannableString spannableString = new SpannableString(textsigup);
        spannableString.setSpan(new callsigupactivity(), start, end, 0);
        TextView textView = (TextView) findViewById(R.id.login_signup);
        textView.setText(spannableString);
        textView.setMovementMethod(new LinkMovementMethod());

        String textforget = getString(R.string.forgetpass);
        String linkforget = getString(R.string.forgetpass);
        int startforget = textforget.indexOf(linkforget);
        int endforget = startforget+linkforget.length();
        SpannableString spanforget = new SpannableString(textforget);
        spanforget.setSpan(new callforgetActivity(), startforget, endforget, 0);
        TextView textfoget = (TextView) findViewById(R.id.login_forgetpass);
        textfoget.setText(spanforget);
        textfoget.setMovementMethod(new LinkMovementMethod());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                Intent h = new Intent(Login.this, MainActivity.class);
                startActivity(h);
                break;
            //case R.id.nav_login:
            //    Intent l = new Intent(Login.this, Login.class);
            //    startActivity(l);
            //    break;
            case R.id.nav_check:
                Intent ch = new Intent(Login.this, Check.class);
                startActivity(ch);
                break;
            case R.id.nav_cate:
                Intent ca = new Intent(Login.this, Cast.class);
                startActivity(ca);
                break;
            case R.id.nav_contact:
                Intent co = new Intent(Login.this, Contact.class);
                startActivity(co);
                break;
            case R.id.nav_about:
                Intent ab = new Intent(Login.this, About.class);
                startActivity(ab);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class callsigupactivity extends ClickableSpan {
        @Override
        public void onClick(View view) {
            Intent callsigup = new Intent(Login.this, Signup.class);
            startActivity(callsigup);
            finish();
        }
    }

    private class callforgetActivity extends ClickableSpan {

        @Override
        public void onClick(View view) {
            Intent callforget = new Intent(Login.this, Forgetpass.class);
            startActivity(callforget);
            finish();
        }
    }
}
