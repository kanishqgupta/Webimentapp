package com.yoursidea.webiment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView service;
    public ItemClickListener listener;
    public RequestViewHolder(@NonNull View itemView) {
        super(itemView);
        service=itemView.findViewById(R.id.emp_name);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v , getAdapterPosition(),false);
    }
}
