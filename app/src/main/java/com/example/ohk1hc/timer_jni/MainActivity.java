package com.example.ohk1hc.timer_jni;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btnStart)
    Button btnStart;

    @Bind(R.id.editTextInterval)
    EditText editTextInterval;

    @Bind(R.id.editTextDuration)
    EditText editTextDuration;

    @Bind(R.id.textViewLog)
    TextView textViewLog;

    @Bind(R.id.scrollView)
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        textViewLog.setMovementMethod(new ScrollingMovementMethod());

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final long intervalTime = Long.parseLong(editTextInterval.getText().toString()) * 1000;
                final long durationTime = Long.parseLong(editTextDuration.getText().toString()) * 1000;

                CountDownTimer countDownTimer = new CountDownTimer(durationTime, intervalTime) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String format = "New message: duration=%d, interval=%d\n";
                        textViewLog.append(String.format(format, durationTime, intervalTime));
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }

                    @Override
                    public void onFinish() {

                    }
                };

                textViewLog.setText("");
                countDownTimer.start();
            }
        });
    }

    private abstract static class SimpleTimer {
        public abstract void start(int duration, int interval);

        public interface TimeChangeListener {
            void onTick(int timeSinceTimerStart, int remainingTime);
            void onFinish();
        }

    }

}
