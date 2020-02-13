package com.sc.SpeechCognition.common.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.sc.SpeechCognition.common.model.Moca;

@Dao
public interface MocaDao {

    @Query("SELECT * FROM MOCA WHERE paccount =:patientAccount")
    Moca findByPatientAccount(String patientAccount);

    @Insert
    void insert(Moca... mocas);

    @Query("DELETE FROM MOCA WHERE paccount=:patientAccount")
    void delete(String patientAccount);
}
