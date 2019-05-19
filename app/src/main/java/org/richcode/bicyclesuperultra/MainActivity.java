package org.richcode.bicyclesuperultra;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.richcode.bicyclesuperultra.Adapter.PagerAdapter;
import org.richcode.bicyclesuperultra.Dialog.DialogEditMessage;
import org.richcode.bicyclesuperultra.Dialog.DialogExit;
import org.richcode.bicyclesuperultra.Dialog.DialogLogin;
import org.richcode.bicyclesuperultra.Dialog.MyDialogListener;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("지도"));
        tabLayout.addTab(tabLayout.newTab().setText("꿀팁"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(false);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_sub_menu_item02:
                        Intent intent2 = new Intent(getApplicationContext(),WebViewActivity.class);
                        intent2.putExtra("link","https://map.naver.com/");
                        startActivity(intent2);
                        break;
                    case R.id.nav_sub_menu_item03:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.kakao.com/o/gOhADiib")));
                        break;
                    case R.id.nav_sub_menu_item04:
                        startActivity(new Intent(getApplicationContext(), RecordActivity.class));
                        break;
                    case R.id.nav_sub_menu_item05:
                        startActivity(new Intent(getApplicationContext(), WeatherActivity.class));
                        break;
                }

                return true;
            }
        });

        DialogLogin dialogLogin = new DialogLogin(this);
        dialogLogin.setCanceledOnTouchOutside(false);
        dialogLogin.setCancelable(false);
        dialogLogin.show();
    }

    @Override
    public void onBackPressed() {
        DialogExit exitDialog = new DialogExit(MainActivity.this);
        exitDialog.setCanceledOnTouchOutside(false);
        exitDialog.setDialogListener(new MyDialogListener() {
            @Override
            public void onPositiveClicked(String input) {
                finish();
            }
            @Override
            public void onNegativeClicked() {

            }
        });
        exitDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                DialogEditMessage dialogEditMessage = new DialogEditMessage(this);
                dialogEditMessage.setCanceledOnTouchOutside(false);
                dialogEditMessage.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
