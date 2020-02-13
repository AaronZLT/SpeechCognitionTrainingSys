package com.sc.SpeechCognition.ui.commonUi.patientlist;

import android.os.Handler;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.sc.SpeechCognition.common.model.PatientInformation;
import com.sc.SpeechCognition.common.myhttp.MyHTTP;
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
        new MyHTTP().get("default:8080","default=sc",handler,arrayList.getClass());

    }
}
