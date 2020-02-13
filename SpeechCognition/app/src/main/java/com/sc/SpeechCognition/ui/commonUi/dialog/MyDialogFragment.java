package com.sc.SpeechCognition.ui.commonUi.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import com.sc.SpeechCognition.R;
import com.sc.SpeechCognition.common.viewmodel.CommonViewModel;

public class MyDialogFragment extends DialogFragment {
    private NavController navController;
    public MyDialogFragment(NavController navController){
        this.navController=navController;
    }
    private CommonViewModel commonViewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("您确定要终止本次活动返回吗？")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        commonViewModel=CommonViewModel.getInstance(null);

                        switch (navController.getCurrentDestination().getId()) {
                            case R.id.memory_assessment:
                            case R.id.memoryDelayAssessment:
                                commonViewModel.exist.setValue(0);
                                commonViewModel.moca.setMemory_Score(-1);break;
                            //TODO:此处加入其他界面的情况，将跳出页面的得分更改为-1
                        }
                        commonViewModel.InsertMoca();
                        commonViewModel.clear();
                        navController.navigate(R.id.nav_home);
                    }
                });
        return builder.create();
    }
}
