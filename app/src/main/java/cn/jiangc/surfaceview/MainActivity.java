package cn.jiangc.surfaceview;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static cn.jiangc.surfaceview.R.drawable.dress_plus_active;

public class MainActivity extends AppCompatActivity {

    private SurfaceView surfaceView;
    private View layout;
    private ImageButton back;
    private ImageButton dress;
    private ImageButton danmu;
    private ImageButton playermore;

    private SurfaceHolder surfaceHolder;
    private boolean touchflag = false;              //layout显示|| 不显示标志
    private boolean gouwuflag = false;              //购物图标资源替换标志
    private boolean danmuflag = false;              //弹幕图标资源替换标志
    private boolean playteflag = false;             //更多选项图标资源替换标志

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.surfaceview);
        surfaceView = findViewById(R.id.surface);
        layout = findViewById(R.id.inc);
        Log.e("jiangc", String.valueOf((layout == null ? true:false)));
        layout.setVisibility(INVISIBLE);
        getWindow().getDecorView().setSystemUiVisibility(INVISIBLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        buttonListener  buttonlistener = new buttonListener();
        back = findViewById(R.id.back);
        dress = findViewById(R.id.dress);
        danmu = findViewById(R.id.danmu);
        playermore = findViewById(R.id.playermore);
        back.setOnClickListener(buttonlistener);
        dress.setOnClickListener(buttonlistener);
        danmu.setOnClickListener(buttonlistener);
        playermore.setOnClickListener(buttonlistener);


        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("jiangc", "surface  touch" + "touchflag = " + touchflag );
                        if (false == touchflag)
                        {
                            layout.setVisibility(VISIBLE);
                            touchflag = true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (touchflag)
                {
                    Log.e("jiangc", "layout  touch" + "touchflag = " + touchflag);
                    layout.setVisibility(INVISIBLE);
                    touchflag = false;
                }
            }
        });
    }

    public class buttonListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.back:
                    Toast.makeText(MainActivity.this, "返回按钮", Toast.LENGTH_LONG).show();
                    break;
                case R.id.dress:
                    if (false == gouwuflag)
                    {
                        dress.setImageResource(R.drawable.dress_plus_active);
                        gouwuflag = true;
                    }else if(true == gouwuflag)
                    {
                        dress.setImageResource(R.drawable.dress_plus);
                        gouwuflag = false;
                    }
                    break;
                case R.id.danmu:
                    if (false == danmuflag)
                    {
                        danmu.setImageResource(R.drawable.danmu_on);
                        danmuflag = true;
                    }
                    else
                    {
                        danmu.setImageResource(R.drawable.danmu_off);
                        danmuflag = false;
                    }
                    break;
                case R.id.playermore:
                    if (false == playteflag)
                    {
                        playermore.setImageResource(R.drawable.player_more_btn_pressed);
                        playteflag = true;
                    }
                    else
                    {
                        playermore.setImageResource(R.drawable.player_more_btn);
                        playteflag = false;
                    }
                    break;
            }

        }
    }



}
