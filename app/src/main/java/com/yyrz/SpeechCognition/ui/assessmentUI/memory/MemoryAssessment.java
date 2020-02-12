package com.yyrz.SpeechCognition.ui.assessmentUI.memory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.yyrz.SpeechCognition.R;

public class MemoryAssessment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         final View root=inflater.inflate(R.layout.fragment_memory_assessment, container, false);

         ImageButton imageButton = root.findViewById(R.id.memory_assessment_imageButton);
         imageButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 NavController navController=Navigation.findNavController(view);
                 navController.navigate(R.id.action_memory_assessment_to_memoryDelayAssessment);
             }
         });
        return root;
    }

}
