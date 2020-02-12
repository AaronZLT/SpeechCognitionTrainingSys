package com.yyrz.SpeechCognition.ui.commonUi.patientlist;

import android.os.Handler;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.yyrz.SpeechCognition.common.model.PatientInformation;
import com.yyrz.SpeechCognition.common.myhttp.MyHTTP;
import java.util.ArrayList;

public class PatientViewModel extends ViewModel {
    private MutableLiveData<ArrayList<PatientInformation>> mutableLiveData;

    MutableLiveData<ArrayList<PatientInformation>> getMutableLiveData() {
        if(mutableLiveData==null){
            mutableLiveData=new MutableLiveData<>();
            mutableLiveData.setValue(new ArrayList<PatientInformation>());
        }
        return mutableLiveData; }

    public void setView(ArrayList<PatientInformation> list){
        mutableLiveData.setValue(list);
    }

    void getPatientInformation(Handler handler) {
        ArrayList<PatientInformation> arrayList = new ArrayList<>();
        new MyHTTP().get("SpeechCognition/patient","daccount=SpeechCognition",handler, arrayList.getClass());
    }
}
