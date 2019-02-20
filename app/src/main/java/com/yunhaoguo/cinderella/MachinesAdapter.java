package com.yunhaoguo.cinderella;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella
 * 文件名:     MachinesAdapter
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/20 12:31 AM
 * 描述:      TODO
 */


public class MachinesAdapter extends RecyclerView.Adapter<MachinesAdapter.ViewHolder> {

    List<Machine> machines;
    Context context;

    public MachinesAdapter(Context context, List<Machine> machines) {
        this.machines = machines;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_machine, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        switch (machines.get(i).state) {
            case 0:
                viewHolder.ivMachine.setImageResource(R.drawable.machine_available);
                break;
            case 1:
                viewHolder.ivMachine.setImageResource(R.drawable.machine_in_use);
                break;
            case 2:
                viewHolder.ivMachine.setImageResource(R.drawable.machine_fixing);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return machines.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMachine;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMachine = itemView.findViewById(R.id.iv_machine);
        }
    }
}
