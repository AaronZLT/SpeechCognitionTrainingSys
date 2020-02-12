package com.yyrz.SpeechCognition.ui.commonUi.patientlist;

import android.content.Context;
import android.content.res.Resources;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.yyrz.SpeechCognition.R;
import com.yyrz.SpeechCognition.common.model.PatientInformation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.os.Handler;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    private ArrayList<PatientInformation> arrayList;
    private Context context;
    private Handler handler;

    MyItemRecyclerViewAdapter(ArrayList<PatientInformation> patientInformation,Context context,Handler handler) {
        this.context=context;
        arrayList=patientInformation;
        this.handler=handler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_patient_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if(position==0){
            Resources res=context.getResources();
            String [] temp=res.getStringArray(R.array.patient_information);
            holder.textView1.setText(temp[0]);
            holder.textView2.setText(temp[1]);
            holder.textView3.setText(temp[2]);
            holder.textView4.setText(temp[3]);
            holder.textView5.setText(temp[4]);
            return;
        }
        Gson gson=new Gson();
        PatientInformation patientInformation =gson.fromJson(gson.toJson(arrayList.get(position-1)),PatientInformation.class);
        holder.textView1.setText(patientInformation.getName());
        holder.textView2.setText(patientInformation.getGender());

        String birthday=patientInformation.getBirthday();
        LocalDate date1 = LocalDate.of(Integer.valueOf(birthday.substring(0,4)),Integer.valueOf(birthday.substring(5,7)), Integer.valueOf(birthday.substring(8,10)));
        String now= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
        LocalDate date2 = LocalDate.of(Integer.valueOf(now.substring(0,4)),Integer.valueOf(now.substring(5,7)), Integer.valueOf(now.substring(8,10)));
        int age = date1.until(date2).getYears();


        holder.textView3.setText(String.valueOf(age));
        holder.textView4.setText(patientInformation.getDepartment());
        holder.textView5.setText(patientInformation.getBed_number());
        holder.setPatientAccount(patientInformation.getAccount());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientInformation p=new PatientInformation();
                p.setAccount(holder.textView1.getText().toString());
                p.setBirthday(holder.textView2.getText().toString());
                p.setGender(holder.textView3.getText().toString());
                p.setDepartment(holder.textView4.getText().toString());
                p.setBed_number(holder.textView5.getText().toString());
                p.setAccount(holder.getPatientAccount());
                Message msg=new Message();
                msg.what=104;
                msg.obj=p;
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
         final View mView;
         final TextView textView1;
         final TextView textView2;
         final TextView textView3;
         final TextView textView4;
         final TextView textView5;
        private String patientAccount;

        ViewHolder(View view) {
            super(view);
            mView = view;
            textView1 = view.findViewById(R.id.assessment_patient_information_texView1);
            textView2 = view.findViewById(R.id.assessment_patient_information_texView2);
            textView3 = view.findViewById(R.id.assessment_patient_information_texView3);
            textView4 = view.findViewById(R.id.assessment_patient_information_texView4);
            textView5 = view.findViewById(R.id.assessment_patient_information_texView5);
        }

         String getPatientAccount() {
            return patientAccount;
        }

         void setPatientAccount(String account) {
            this.patientAccount = account;
        }
    }
}
