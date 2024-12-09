package com.example.recieve_sms;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class RecieveSms extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = smsMessage.getDisplayOriginatingAddress();
                    String messageBody = smsMessage.getMessageBody();
                    Toast.makeText(context, "SMS reçu de: " + sender + " - Message: " + messageBody, Toast.LENGTH_LONG).show();
                    Log.d("SmsReceiver", "SMS reçu de: " + sender + " - Message: " + messageBody);
                }
            }
        }
    }
}
