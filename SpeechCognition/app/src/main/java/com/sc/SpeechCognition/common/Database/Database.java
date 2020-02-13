package com.sc.SpeechCognition.common.Database;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.sc.SpeechCognition.common.model.Moca;

@androidx.room.Database(entities = {Moca.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static Database instance;

    public synchronized static Database getInstance(Context context) {
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),Database.class,"sc").build();
        }
        return instance;
    }

    protected Database() { }

    public abstract MocaDao getMocaDao();
}
