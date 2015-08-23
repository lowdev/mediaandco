package eu.entropy.mediapedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashScreenActivity extends Activity {
    private static int SPLASH_TIME_OUT = 3000;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
