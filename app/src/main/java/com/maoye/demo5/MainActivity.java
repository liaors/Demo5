package com.maoye.demo5;

import android.animation.Animator;
import android.content.Intent;
import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {
    private ImageView demo_iv;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demo_iv = findViewById(R.id.demo_iv);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
//           startService(new Intent(this,PersionService.class));

        try {
            // 编码
           String aa =  android.util.Base64.encodeToString("sssedrftgsdrfh/,gsdfgsd我，额ss".getBytes("UTF-8"), android.util.Base64.URL_SAFE);
            Log.e("TAG", "onCreate: "+aa );
            System.out.println("aa:"+aa); // 输出为: c29tZSBzdHJpbmcv
            // 解码


           String bb = new String(Base64.decode(aa,Base64.URL_SAFE),"UTF-8");
           String cc = String.valueOf(Base64.decode(aa,Base64.CRLF));
           ;
            Log.e("TAG", "onCreate: bb:"+bb );
            Log.e("TAG", "onCreate: cc:"+cc );
        }catch (java.io.UnsupportedEncodingException e){
            e.printStackTrace();
        }
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
