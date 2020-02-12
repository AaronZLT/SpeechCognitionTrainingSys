package com.yyrz.SpeechCognition.ui.mainUI.information;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yyrz.SpeechCognition.R;
import com.yyrz.SpeechCognition.common.exception.ErrorCode;
import com.yyrz.SpeechCognition.common.model.DoctorInformation;

public class InformationFragment extends Fragment {

    private InformationViewModel informationViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ImageView imageView;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_information, container, false);

        //初始化用到的变量完成
        recyclerView = root.findViewById(R.id.information_recyclerView);
        informationViewModel = ViewModelProviders.of(this).get(InformationViewModel.class);
        progressBar=root.findViewById(R.id.information_progressBar);
        imageView=root.findViewById(R.id.information_imageView);
        textView=root.findViewById(R.id.information_textView);

        //添加布局配件及相关配置
        imageView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        final Observer<DoctorInformation> observer = new Observer<DoctorInformation>() {
            @Override
            public void onChanged(DoctorInformation doctorInformation) {
                recyclerView.addItemDecoration(new MyItemDecoration(3));
                recyclerView.setAdapter(new MyRecyclerViewAdapt(doctorInformation.getKey(), doctorInformation.getValue()));
            }
        };
        informationViewModel.getMutableLiveData().observe(this, observer);

        //消息处理设置
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 100) {
                    progressBar.setVisibility(View.GONE);
                    imageView.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    informationViewModel.setView((DoctorInformation) msg.obj);
                } else if (msg.what == 101) {
                    imageView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    ErrorCode errorCode = (ErrorCode) msg.obj;
                    Toast.makeText(getContext(), errorCode.getCode() + " ：" + errorCode.getMsg(), Toast.LENGTH_LONG).show();
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), msg.what + " ：" + msg.obj.toString(), Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

        //发送网络请求
        informationViewModel.getDoctorInformation(handler);
        return root;
    }

}