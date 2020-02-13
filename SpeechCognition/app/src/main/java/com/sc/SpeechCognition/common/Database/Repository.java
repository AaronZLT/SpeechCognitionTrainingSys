package com.sc.SpeechCognition.common.Database;

import android.content.Context;
import android.os.AsyncTask;
import com.sc.SpeechCognition.common.model.Moca;
import com.sc.SpeechCognition.common.viewmodel.CommonViewModel;

public class Repository {

    MocaDao mocaDao;

    public Repository(Context context) {
      Database database=Database.getInstance(context.getApplicationContext());
      mocaDao=database.getMocaDao();
    }

    public void InsertMoca(Moca...mocas){
        new InsertMoca(mocaDao).execute(mocas);
    }

    public void DeleteMoca(String... strings){
        new DeleteMoca(mocaDao).execute(strings);
    }

    public void QueryMoca(String... strings){
        new QueryMoca(mocaDao).execute(strings);
    }

    static class InsertMoca extends AsyncTask<Moca , Void, Void> {

        private MocaDao mocaDao;

        public InsertMoca(MocaDao mocaDao) {
            this.mocaDao = mocaDao;
        }

        @Override
        protected Void doInBackground(Moca... mocas) {

            for (Moca moca:mocas){
                mocaDao.delete(moca.getPaccount());
            }
            mocaDao.insert(mocas);
            return null;
        }
    }

    static class DeleteMoca extends AsyncTask<String,Void,Void>{
        private MocaDao mocaDao;

        public DeleteMoca(MocaDao mocaDao) {
            this.mocaDao = mocaDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            for(String temp:strings) {
                mocaDao.delete(temp);
            }
            return null;
        }
    }

    static class QueryMoca extends AsyncTask<String,Void,Void>{

        private MocaDao mocaDao;

        public QueryMoca(MocaDao mocaDao) {
            this.mocaDao = mocaDao;
        }


        @Override
        protected Void doInBackground(String... strings) {

            for(String temp:strings){
                CommonViewModel.getInstance(null).moca=mocaDao.findByPatientAccount(temp);
            }
            CommonViewModel.getInstance(null).exist.postValue(1);
            return null;
        }
    }
}
