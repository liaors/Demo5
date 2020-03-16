package com.maoye.demo5;

import android.animation.Animator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView demo_iv;
    private TextView tv;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demo_iv = findViewById(R.id.demo_iv);
        tv = findViewById(R.id.tv);

        stringBuilder = new StringBuilder();
        ListenerManager.INSTANCE.setCallBack(new ListenerManager.CallBackLister() {
            @Override
            public void onCallBack(PersionBean persionBean) {
                tv.setText(stringBuilder.append(persionBean.getName()).append(persionBean.getAge()).append(","));
            }
        });
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.animalbt:
                float hypot = (float) Math.hypot(demo_iv.getHeight(), demo_iv.getWidth());
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(demo_iv, demo_iv.getRight(), demo_iv.getBottom(), hypot, 10);
                circularReveal.setDuration(6000);
                circularReveal.setInterpolator(new AccelerateDecelerateInterpolator());
                circularReveal.start();
                //                startActivity(new Intent(this,AnimalActivity.class));

                test("asg", 4);
                break;
        }
    }

    @Intype
    public int test(String aa, @IntRange(from = 1, to = 5) int bb) {
        Log.e("TAG", "test: " + aa + "bb:" + bb);
        return 2;
    }

}
