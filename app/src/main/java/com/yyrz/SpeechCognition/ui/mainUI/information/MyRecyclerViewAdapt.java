package com.yyrz.SpeechCognition.ui.mainUI.information;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.yyrz.SpeechCognition.R;

public class MyRecyclerViewAdapt extends RecyclerView.Adapter<MyRecyclerViewAdapt.MyViewHolder> {

    private String[] key;
    private String[] value;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView1= itemView.findViewById(R.id.information_list_textView1);
            this.textView2= itemView.findViewById(R.id.information_list_textView2);
        }
    }

    MyRecyclerViewAdapt(String[] key, String[] value) {
        this.key=key;
        this.value=value;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapt.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_content,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.textView1.setText(key[position]);
            holder.textView2.setText(value[position]);
    }

    @Override
    public int getItemCount() {
        return key==null?0:key.length;
    }
}
