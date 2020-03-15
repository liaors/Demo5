package com.maoye.demo5;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

public class AnimalActivity extends AppCompatActivity implements Animation.AnimationListener {

    private ImageButton imgbt;
    private View view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        initView();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.imgbt:
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setDuration(1000);
                animationSet.setAnimationListener(this);
                animationSet.setFillAfter(false);

                Animation scalex = new ScaleAnimation(100,0,0,0);
                animationSet.addAnimation(scalex);
                this.view.startAnimation(animationSet);
                Animation x = new TranslateAnimation(0,100,0,0);
                animationSet.addAnimation(x);

                imgbt.startAnimation(x);

                break;
            case R.id.imgbt2:
                break;
        }
    }
    private void initView() {
        imgbt = findViewById(R.id.imgbt);
        view = findViewById(R.id.view);
//        expandedButton2.setLeftX();
   }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
