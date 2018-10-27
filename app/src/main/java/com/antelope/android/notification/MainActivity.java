package com.antelope.android.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.send_notice)
    Button mSendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.send_notice)
    public void onViewClicked() {
        //获取NotificationManager实例来对通知进行管理
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel("1",
                    "channel1", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            manager.createNotificationChannel(channel);

            /*Notification API不稳定，几乎Android系统的每一个版本都会对通知进行修改，
            使用support-v4库中提供的NotificationCompat类的构造器来创建Notification对象，
            就可以保证我们的程序在所有的Android系统版本上都能正常工作了*/
            notification = new NotificationCompat.Builder(MainActivity.this, "1")
                    .setContentTitle("ContentTitle")
                    .setContentText("ContentText")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                    .build();
        } else {
            notification = new NotificationCompat.Builder(MainActivity.this)
                    .setContentTitle("ContentTitle")
                    .setContentText("ContentText")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                    .build();
        }

        //让通知显示出来，每个通知所指定的id是不同的
        manager.notify(1, notification);
    }
}
