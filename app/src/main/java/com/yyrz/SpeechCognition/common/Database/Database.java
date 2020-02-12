package com.yyrz.SpeechCognition.common.Database;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.yyrz.SpeechCognition.common.model.Moca;

@androidx.room.Database(entities = {Moca.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static Database instance;

    public synchronized static Database getInstance(Context context) {
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),Database.class,"yyrz").build();
        }
        return instance;
    }

    protected Database() { }

    public abstract MocaDao getMocaDao();
}
