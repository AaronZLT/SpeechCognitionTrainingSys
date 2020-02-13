package com.sc.SpeechCognition.ui.commonUi.patientlist;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyPatientDecoration extends RecyclerView.ItemDecoration{

    private int top;

    public MyPatientDecoration(int top) {
        this.top = top;
    }

    @Override
    public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = this.top;
    }
}
