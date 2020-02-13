package com.sc.SpeechCognition.ui.mainUI.information;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.sc.SpeechCognition.common.model.DoctorInformation;
import com.sc.SpeechCognition.common.myhttp.MyHTTP;
import android.os.Handler;

public class InformationViewModel extends ViewModel {
    private MutableLiveData<DoctorInformation> mutableLiveData;

    MutableLiveData<DoctorInformation> getMutableLiveData() {
        if(mutableLiveData==null){
            mutableLiveData=new MutableLiveData<>();
            mutableLiveData.setValue(new DoctorInformation());
        }
        return mutableLiveData;
    }

    public void setView(DoctorInformation doctorInformation){
        mutableLiveData.setValue(doctorInformation);
    }

    void getDoctorInformation(Handler handler) {
        new MyHTTP().get("SpeechCognition/daccount","daccount=SpeechCognition",handler,DoctorInformation.class);
    }
}