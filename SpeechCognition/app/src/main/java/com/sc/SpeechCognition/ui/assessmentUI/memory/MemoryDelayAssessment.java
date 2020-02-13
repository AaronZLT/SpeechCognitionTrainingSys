package com.sc.SpeechCognition.ui.assessmentUI.memory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.sc.SpeechCognition.R;
import com.sc.SpeechCognition.common.viewmodel.CommonViewModel;

public class MemoryDelayAssessment extends Fragment {

    private CheckBox []checkBox=new CheckBox[15];
    private CommonViewModel commonViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root=inflater.inflate(R.layout.fragment_memory_delay_assessment, container, false);
        commonViewModel= CommonViewModel.getInstance(null);
        checkBox[0]=root.findViewById(R.id.assessment_delay_memory_checkBox1);
        checkBox[1]=root.findViewById(R.id.assessment_delay_memory_checkBox2);
        checkBox[2]=root.findViewById(R.id.assessment_delay_memory_checkBox3);
        checkBox[3]=root.findViewById(R.id.assessment_delay_memory_checkBox4);
        checkBox[4]=root.findViewById(R.id.assessment_delay_memory_checkBox5);
        checkBox[5]=root.findViewById(R.id.assessment_delay_memory_checkBox6);
        checkBox[6]=root.findViewById(R.id.assessment_delay_memory_checkBox7);
        checkBox[7]=root.findViewById(R.id.assessment_delay_memory_checkBox8);
        checkBox[8]=root.findViewById(R.id.assessment_delay_memory_checkBox9);
        checkBox[9]=root.findViewById(R.id.assessment_delay_memory_checkBox10);
        checkBox[10]=root.findViewById(R.id.assessment_delay_memory_checkBox11);
        checkBox[11]=root.findViewById(R.id.assessment_delay_memory_checkBox12);
        checkBox[12]=root.findViewById(R.id.assessment_delay_memory_checkBox13);
        checkBox[13]=root.findViewById(R.id.assessment_delay_memory_checkBox14);
        checkBox[14]=root.findViewById(R.id.assessment_delay_memory_checkBox15);
        ImageButton imageButton = root.findViewById(R.id.assessment_delay_memory_imageButton);
        for(int i=0;i<5;i++){
            checkBox[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        commonViewModel.moca.setMemory_Score(commonViewModel.moca.getMemory_Score()+1);}
                    else{
                        commonViewModel.moca.setMemory_Score(commonViewModel.moca.getMemory_Score()-1);}
                }
            });
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:跳转到下一个页面，此处暂时直接跳到finish页面
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_memoryDelayAssessment_to_finishProcess);
            }
        });
        return root;
    }

}
