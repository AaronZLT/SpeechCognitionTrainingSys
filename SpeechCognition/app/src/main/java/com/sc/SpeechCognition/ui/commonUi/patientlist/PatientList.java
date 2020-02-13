package com.sc.SpeechCognition.ui.commonUi.patientlist;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sc.SpeechCognition.R;
import com.sc.SpeechCognition.common.exception.ErrorCode;
import com.sc.SpeechCognition.common.model.Moca;
import com.sc.SpeechCognition.common.model.PatientInformation;
import com.sc.SpeechCognition.common.viewmodel.CommonViewModel;
import com.sc.SpeechCognition.ui.mainUI.information.MyItemDecoration;
import java.util.ArrayList;

import static com.sc.SpeechCognition.MainActivity.*;

public class PatientList extends Fragment {
    private TextView textView;
    private ProgressBar progressBar;
    private ImageView imageView;
    private Handler handler;
    private RecyclerView recyclerView;
    private PatientViewModel patientViewModel;
    private CommonViewModel commonViewModel;
    private String patientAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        //初始化fragmen组件
        final View root = inflater.inflate(R.layout.fragment_patient_list, container, false);
        textView=root.findViewById(R.id.assessment_patient_textView);
        imageView=root.findViewById(R.id.assessment_patient_imageView);
        progressBar=root.findViewById(R.id.assessment_patient_progressBar);
        recyclerView=root.findViewById(R.id.assessment_patient_recyclerview);
        patientViewModel= ViewModelProviders.of(this).get(PatientViewModel.class);
        commonViewModel=CommonViewModel.getInstance(null);
        //消息处理
        handler= new Handler(new Handler.Callback(){
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 100) {
                    progressBar.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    patientViewModel.setView((ArrayList<PatientInformation>) msg.obj);
                } else if (msg.what == 101) {
                    imageView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    ErrorCode errorCode = (ErrorCode) msg.obj;
                    Toast.makeText(getContext(), errorCode.getCode() + " ：" + errorCode.getMsg(), Toast.LENGTH_LONG).show();
                }else if(msg.what == 104){
                    PatientInformation p=(PatientInformation) msg.obj;
                    commonViewModel.moca.setPaccount(p.getAccount());
                    patientAccount=p.getAccount();
                    recyclerView.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    commonViewModel.QueryMoca();
                }else {
                    imageView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), msg.what + " ：" + msg.obj.toString(), Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });


        //设置适配器及相关设置
        final Observer<ArrayList<PatientInformation>> observer = new Observer<ArrayList<PatientInformation>>() {
            @Override
            public void onChanged(ArrayList<PatientInformation> patientInformation) {
                recyclerView.addItemDecoration(new MyItemDecoration(3));
                recyclerView.setAdapter(new MyItemRecyclerViewAdapter(patientInformation,getContext(),handler));
            }
        };

        commonViewModel.exist.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    progressBar.setVisibility(View.GONE);
                    switch (commonViewModel.flag) {
                        case 1: {
                            if (commonViewModel.moca == null) {
                                commonViewModel.moca = new Moca();
                                commonViewModel.moca.setPaccount(patientAccount);
                                NavController navController = Navigation.findNavController(root);
                                navController.navigate(R.id.memory_assessment);
                            } else {
                                NavController navController = Navigation.findNavController(root);
                                navController.navigate(record.get(commonViewModel.moca.getDestination()));
                            }
                        }break;
                        case 2:{
                            //TODO:跳到训练界面
                        }

                        break;

                    }
                }
            }
        });
        patientViewModel.getMutableLiveData().observe(this, observer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        imageView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        //发起网络请求
        patientViewModel.getPatientInformation(handler);
        return root;
                }
}

