package com.example.beefollowsme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    public ImageView beeView;
    private Bee bee;
    private RelativeLayout beeWorldLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bee = new Bee(300,300,300,300,5);
        beeWorldLayout = (RelativeLayout) findViewById(R.id.container);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        beeView = (ImageView) inflater.inflate(R.layout.bee_image, null);
        beeView.setX(300f);
        beeView.setY(300f);
        beeView.setScaleY(.75f);
        beeWorldLayout.addView(beeView,0);

        Thread backgroundThread = new Thread(moveCalculations);
        backgroundThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        bee.setNewTarget((int) event.getX(), (int) event.getY());
        return true;
    }

    private Runnable moveCalculations = new Runnable() {
        @Override
        public void run() {
                try {
                    while(true) {
                        bee.move();
                        Thread.sleep(100);
                        handler.sendEmptyMessage(0);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    };

    public Handler handler = new Handler(Looper.myLooper()){
        public void handleMessage(Message msg){
            beeView.setX(bee.currentX);
            beeView.setY(bee.currentY);
        }
    };
}