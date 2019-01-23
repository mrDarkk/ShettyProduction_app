package com.bhupendra.production123;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhupendrabanothe on 29/03/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    Context context;
    ArrayList<listViewData> list;

    public RecyclerViewAdapter(Context context, ArrayList<listViewData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listview_layout, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        listViewData data = list.get(position);

        holder.email.setText(data.email);
        holder.name.setText(data.name);
        holder.msg.setText(data.msg);
        holder.phone.setText(data.phone);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView email, name, phone, msg;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            name = itemView.findViewById(R.id.name);
            msg = itemView.findViewById(R.id.msg);
        }
    }
}
