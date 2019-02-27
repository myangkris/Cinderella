package com.yunhaoguo.cinderella;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.yunhaoguo.cinderella.adapter.MachinesAdapter;
import com.yunhaoguo.cinderella.common.BasicActivity;
import com.yunhaoguo.cinderella.entity.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends BasicActivity {

    private List<Machine> machines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitleBar();

        initView();

    }



    private void initView() {
        RecyclerView rvMachines = findViewById(R.id.rv_machines);
        rvMachines.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));

        List<Machine> machines = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Machine machine = new Machine();
            machine.setState(new Random().nextInt(3));
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
    public MainContract.Presenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void getMachineStates(List<Machine> machines) {
        this.machines = machines;
    }
}
