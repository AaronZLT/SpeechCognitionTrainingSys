package com.yyrz.SpeechCognition.ui.mainUI.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.yyrz.SpeechCognition.R;
import com.yyrz.SpeechCognition.common.viewmodel.CommonViewModel;

public class HomeFragment extends Fragment {

    private Handler handler;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Button assessmentButton = root.findViewById(R.id.home_assessment_button);
        Button trainingButton = root.findViewById(R.id.home_training_button);


        //添加配置
        assessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonViewModel.getInstance(null).flag=1;
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_nav_home_to_patientList);
            }
        });
        trainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonViewModel.getInstance(null).flag=2;
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_nav_home_to_patientList);
            }
        });
        handler=new Handler(new Handler.Callback(){
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                Toast.makeText(getContext(),msg.obj.toString(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return root;
    }
}
