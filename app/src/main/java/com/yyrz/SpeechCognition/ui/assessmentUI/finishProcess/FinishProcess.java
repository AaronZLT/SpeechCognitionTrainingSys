package com.yyrz.SpeechCognition.ui.assessmentUI.finishProcess;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.yyrz.SpeechCognition.R;
import com.yyrz.SpeechCognition.common.exception.ErrorCode;
import com.yyrz.SpeechCognition.common.viewmodel.CommonViewModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FinishProcess extends Fragment {
    private CommonViewModel commonViewModel;
    private Handler handler;
    private Group wait;
    private Group success;
    private Group tryAgain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_finish_process, container, false);
        wait = root.findViewById(R.id.assessment_finish_wait);
        success = root.findViewById(R.id.assessment_finish_success);
        tryAgain = root.findViewById(R.id.assessment_finish_tryAgain);
        Button button1 = root.findViewById(R.id.assessment_finish_button1);
        Button button2 = root.findViewById(R.id.assessment_finish_button2);
        wait.setVisibility(View.VISIBLE);
        tryAgain.setVisibility(View.INVISIBLE);
        success.setVisibility(View.INVISIBLE);
        commonViewModel=CommonViewModel.getInstance(null);


        handler = new Handler(new Handler.Callback(){
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == 105) {
                    wait.setVisibility(View.GONE);
                    tryAgain.setVisibility(View.GONE);
                    success.setVisibility(View.VISIBLE);
                    commonViewModel.DeleteMoca();
                    commonViewModel.clear();

                } else if (msg.what == 103) {
                    wait.setVisibility(View.GONE);
                    tryAgain.setVisibility(View.VISIBLE);
                    success.setVisibility(View.GONE);
                    ErrorCode errorCode = (ErrorCode) msg.obj;
                    Toast.makeText(getContext(), errorCode.getCode() + " ：" + errorCode.getMsg(), Toast.LENGTH_LONG).show();
                } else if (msg.what == 106) {
                    wait.setVisibility(View.GONE);
                    tryAgain.setVisibility(View.VISIBLE);
                    success.setVisibility(View.GONE);
                    Toast.makeText(getContext(), msg.what+" : "+msg.obj, Toast.LENGTH_LONG).show();
                } else {
                    wait.setVisibility(View.GONE);
                    tryAgain.setVisibility(View.VISIBLE);
                    success.setVisibility(View.GONE);
                    ErrorCode errorCode = (ErrorCode) msg.obj;
                    Toast.makeText(getContext(), errorCode.getCode() + " ：" + errorCode.getMsg(), Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commonViewModel.postData(handler);
                wait.setVisibility(View.VISIBLE);
                tryAgain.setVisibility(View.INVISIBLE);
                success.setVisibility(View.INVISIBLE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commonViewModel.DeleteMoca();
                commonViewModel.clear();
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_finishProcess_to_nav_home);
            }
        });

        commonViewModel.moca.setDate(new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(System.currentTimeMillis())));
        commonViewModel.postData(handler);
        return root;
    }

}
