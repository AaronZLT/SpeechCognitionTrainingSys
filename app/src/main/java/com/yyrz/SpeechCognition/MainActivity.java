package com.yyrz.SpeechCognition;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.yyrz.SpeechCognition.common.viewmodel.CommonViewModel;
import com.yyrz.SpeechCognition.ui.commonUi.dialog.MyDialogFragment;
import java.util.HashMap;
import java.util.Objects;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    public static HashMap<String,Integer> record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //推送相关
        JPushInterface.init(getApplicationContext());
        JPushInterface.setAlias(getApplicationContext(),1, "SpeechCognition");
        //初始化CommonViewModel
        CommonViewModel.getInstance(getApplication());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_information, R.id.nav_patient,
                R.id.nav_message, R.id.nav_setting, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        record=new HashMap<>();
        //TODO:此处将Moca变量与对应的页面对应起来，为了方便跳转到页面
        record.put("memory_Score",R.id.memoryDelayAssessment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(Objects.requireNonNull(navController.getCurrentDestination()).getId()==R.id.nav_home
           ||navController.getCurrentDestination().getId()==R.id.nav_information
           ||navController.getCurrentDestination().getId()==R.id.nav_logout
           ||navController.getCurrentDestination().getId()==R.id.nav_message
           ||navController.getCurrentDestination().getId()==R.id.nav_setting
           ||navController.getCurrentDestination().getId()==R.id.nav_patient
           ||navController.getCurrentDestination().getId()==R.id.patientList
        ){
            return false;
        }
        if(item.getItemId()==android.R.id.home){
            DialogFragment newFragment = new MyDialogFragment(navController);
            newFragment.show(this.getSupportFragmentManager(), "missiles");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(Objects.requireNonNull(navController.getCurrentDestination()).getId()==R.id.nav_home
                ||navController.getCurrentDestination().getId()==R.id.nav_information
                ||navController.getCurrentDestination().getId()==R.id.nav_logout
                ||navController.getCurrentDestination().getId()==R.id.nav_message
                ||navController.getCurrentDestination().getId()==R.id.nav_setting
                ||navController.getCurrentDestination().getId()==R.id.nav_patient
                ||navController.getCurrentDestination().getId()==R.id.patientList
        ){
            super.onKeyDown(keyCode,event);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            DialogFragment newFragment = new MyDialogFragment(navController);
            newFragment.show(this.getSupportFragmentManager(), "missiles");
        }
        return true;
    }

}
