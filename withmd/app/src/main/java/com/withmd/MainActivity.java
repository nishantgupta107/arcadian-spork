package com.withmd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.withmd.activity.BooksFragment;
import com.withmd.activity.ContactFragment;
import com.withmd.activity.FilesFragment;
import com.withmd.activity.FragmentDrawer;
import com.withmd.activity.MainFragment;
import com.withmd.activity.NotesFragment;
import com.withmd.activity.PaperFragment;
import com.withmd.activity.SettingFragment;
import com.withmd.activity.SyllabusFragment;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        // display the first navigation drawer view on app launch
        displayView(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }






    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {

            case 0:
                fragment = new MainFragment();
                title = getString(R.string.title_home);
                break;

            case 1:
                fragment = new PaperFragment();
                title = getString(R.string.title_paper);
                break;

            case 2:
                fragment = new FilesFragment();
                title = getString(R.string.title_files);
                break;

            case 3:
                fragment = new NotesFragment();
                title = getString(R.string.title_notes);
                break;

            case 4:
                fragment = new BooksFragment();
                title = getString(R.string.title_books);
                break;

            case 5:
                fragment = new SyllabusFragment();
                title = getString(R.string.title_syllabus);
                break;

            case 6:
                fragment = new ContactFragment();
                title = getString(R.string.title_contact);
                break;

            case 7:
                fragment = new SettingFragment();
                title = getString(R.string.title_setting);
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

         public void contact(View view) {

             Intent intent = null, chooser=null;

             if(view.getId()==R.id.callme) {
                 chooser= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+918860695024"));

                 startActivity(chooser);
                 tToast("Calling WE-IP");
             }

             if(view.getId()==R.id.LaunchMaps) {
                 intent = new Intent(Intent.ACTION_VIEW);
                 intent.setData(Uri.parse("geo:28.721234,77.141775"));
                 chooser=Intent.createChooser(intent,"Launch Maps");
                 startActivity(chooser);
             }

             if(view.getId()==R.id.sendemail) {
                 intent = new Intent(Intent.ACTION_SEND);
                 intent.setData(Uri.parse("mailto:"));
                 String[] to= {"pushkardua@gmail.com","nishantgupta107@gmail.com"};
                 intent.putExtra(Intent.EXTRA_EMAIL,to);
                 intent.putExtra(Intent.EXTRA_SUBJECT, "WE-IP Feedback");
                 intent.putExtra(Intent.EXTRA_TEXT,"Sent Via App");
                 intent.setType("message/rfc822");
                 chooser=Intent.createChooser(intent,"Send Email");
                 startActivity(chooser);
             }
         }


        private void tToast(String s) {

            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(getApplicationContext(), s, duration);
            toast.show();
        }

    }