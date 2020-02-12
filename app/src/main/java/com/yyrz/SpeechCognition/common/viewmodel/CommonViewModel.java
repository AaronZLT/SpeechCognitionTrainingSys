package com.yyrz.SpeechCognition.common.viewmodel;

import android.app.Application;
import android.os.Handler;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.yyrz.SpeechCognition.common.Database.Repository;
import com.yyrz.SpeechCognition.common.model.Moca;
import com.yyrz.SpeechCognition.common.myhttp.MyHTTP;

public class CommonViewModel extends ViewModel {

    //Integer值为0是表示为不在任何状态,1表示在评估阶段，2表示在训练阶段。
    public int flag;
    public Moca moca;
    public String trainingAccount;
    //监视的值为0表示初始状态，1表示查询结束
    public MutableLiveData<Integer> exist;
    private Repository repository;
    private static CommonViewModel instance;

    private CommonViewModel(){}

    public static CommonViewModel getInstance(Application application){
        if(instance==null){
            instance=new CommonViewModel();
            instance.flag=0;
            instance.moca=new Moca();
            instance.repository=new Repository(application);
            instance.exist=new MutableLiveData<>();
            instance.exist.setValue(0);
            instance.trainingAccount=null;
        }
        return instance;
    }

    public void postData(Handler handler){
        new MyHTTP().post("moca",handler,moca);
    }

    public void clear() {
        instance.moca=new Moca();
        instance.flag=0;
        instance.exist.setValue(0);
        instance.trainingAccount=null;
    }

    public void InsertMoca(){
        repository.InsertMoca(moca);
    }

    public void DeleteMoca(){
        repository.DeleteMoca(moca.getPaccount());
    }

    public void QueryMoca(){
        repository.QueryMoca(moca.getPaccount());
    }

}
