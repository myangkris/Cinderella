package com.yunhaoguo.cinderella;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Window window = this.getWindow();

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(getResources().getColor(R.color.darkBlue));
        getSupportActionBar().hide();


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


}
