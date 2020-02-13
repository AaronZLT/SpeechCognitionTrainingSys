package com.sc.SpeechCognition.jpush;

import android.content.Context;
import android.widget.Toast;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class MyJPushMessageReceiver extends JPushMessageReceiver {
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        /**
         * ErrorCode为 0，表示成功
         */
        if(jPushMessage.getErrorCode() == 0){
            Toast.makeText(context, "register success", Toast.LENGTH_SHORT).show();
        }else{
            JPushInterface.setAlias(context,1, "SpeechCognition");
            Toast.makeText(context, "register fail"+jPushMessage.getErrorCode(), Toast.LENGTH_SHORT).show();
        }
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        Toast.makeText(context,"收到了自定义消息"+customMessage.message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRegister(Context context, String registrationId) {
        //Log.e(TAG,"[onRegister] "+registrationId);
        Toast.makeText(context,"registrationId"+registrationId,Toast.LENGTH_LONG).show();

    }

}
