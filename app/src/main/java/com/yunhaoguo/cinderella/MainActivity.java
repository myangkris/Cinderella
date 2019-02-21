package com.yunhaoguo.cinderella;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitleBar();

        initView();

        setPushService();

    }

    private void setPushService() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());
    }

    private void initView() {
        RecyclerView rvMachines = findViewById(R.id.rv_machines);
        rvMachines.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        List<Machine> machines = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Machine machine = new Machine();
            machine.state = new Random().nextInt(3);
            machines.add(machine);
        }
        rvMachines.setAdapter(new MachinesAdapter(this, machines));
    }

    private void setTitleBar() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.darkBlue));

        getSupportActionBar().hide();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_profile:
                        Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.toolbar_balance:
                        Toast.makeText(MainActivity.this, "balance", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.toolbar_logout:
                        Toast.makeText(MainActivity.this, "logout", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

}
