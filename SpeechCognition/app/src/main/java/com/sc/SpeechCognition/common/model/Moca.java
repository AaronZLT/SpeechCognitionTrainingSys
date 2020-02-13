package com.sc.SpeechCognition.common.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.sc.SpeechCognition.common.util.BeanFieldAnnotation;

@Entity
public class Moca {

    @ColumnInfo
    @BeanFieldAnnotation(order = 1)
    private int alternateLineTest_Score;

    @ColumnInfo
    @BeanFieldAnnotation(order = 2)
    private int visualSpaceSkill_Score;

    @ColumnInfo
    @BeanFieldAnnotation(order = 3)
    private int named_Score;

    @ColumnInfo
    @BeanFieldAnnotation(order = 4)
    private int attention_Score;

    @ColumnInfo
    @BeanFieldAnnotation(order = 5)
    private int fluentLanguage_Score;

    @ColumnInfo
    @BeanFieldAnnotation(order = 6)
    private int abstract_Score;

    @ColumnInfo
    @BeanFieldAnnotation(order = 7)
    private int memory_Score;

    @ColumnInfo
    @BeanFieldAnnotation(order =8 )
    private int directional_Score;

    @ColumnInfo
    @BeanFieldAnnotation(order = 9)
    private String assessmentRecords;

    @ColumnInfo
    @BeanFieldAnnotation(order = 10)
    private String daccount = "SpeechCognition";

    @NonNull
    @PrimaryKey
    @BeanFieldAnnotation(order = 11)
    private String paccount;


    @ColumnInfo
    @BeanFieldAnnotation(order = 12)
    private String date;

    public String getDaccount() {
        return daccount;
    }

    public void setDaccount(String daccount) {
        this.daccount = daccount;
    }

    @NonNull
    public String getPaccount() {
        return paccount;
    }

    public void setPaccount(@NonNull String paccount) {
        this.paccount = paccount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAlternateLineTest_Score() {
        return alternateLineTest_Score;
    }

    public void setAlternateLineTest_Score(int alternateLineTest_Score) {
        this.alternateLineTest_Score = alternateLineTest_Score;
    }

    public int getVisualSpaceSkill_Score() {
        return visualSpaceSkill_Score;
    }

    public void setVisualSpaceSkill_Score(int visualSpaceSkill_Score) {
        this.visualSpaceSkill_Score = visualSpaceSkill_Score;
    }

    public int getNamed_Score() {
        return named_Score;
    }

    public void setNamed_Score(int named_Score) {
        this.named_Score = named_Score;
    }

    public int getAttention_Score() {
        return attention_Score;
    }

    public void setAttention_Score(int attention_Score) {
        this.attention_Score = attention_Score;
    }

    public int getFluentLanguage_Score() {
        return fluentLanguage_Score;
    }

    public void setFluentLanguage_Score(int fluentLanguage_Score) {
        this.fluentLanguage_Score = fluentLanguage_Score;
    }

    public int getAbstract_Score() {
        return abstract_Score;
    }

    public void setAbstract_Score(int abstract_Score) {
        this.abstract_Score = abstract_Score;
    }

    public int getMemory_Score() {
        return memory_Score;
    }

    public void setMemory_Score(int memory_Score) {
        this.memory_Score = memory_Score;
    }

    public int getDirectional_Score() {
        return directional_Score;
    }

    public void setDirectional_Score(int directional_Score) {
        this.directional_Score = directional_Score;
    }

    public String getAssessmentRecords() {
        return assessmentRecords;
    }

    public void setAssessmentRecords(String assessmentRecords) {
        this.assessmentRecords = assessmentRecords;
    }

    public String getDestination(){
        if(alternateLineTest_Score==-1) {
            alternateLineTest_Score=0;
            return"alternateLineTest_Score";}
        if(visualSpaceSkill_Score==-1){
            visualSpaceSkill_Score=0;
            return"visualSpaceSkill_Score";}
        if(named_Score==-1){
            named_Score=0;
            return"named_Score";}
        if(attention_Score==-1){
            attention_Score=0;
            return"attention_Score";}
        if(fluentLanguage_Score==-1){
            fluentLanguage_Score=0;
            return"fluentLanguage_Score";}
        if(abstract_Score==-1){
            abstract_Score=0;
            return"abstract_Score";}
        if(memory_Score==-1){
            memory_Score=0;
            return"memory_Score";}
        return null;
    }
}
