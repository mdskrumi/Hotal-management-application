package com.example.rumi.imanagehotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NoticeViewActivity extends AppCompatActivity {

    private TextView noticeviewNoticeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_view);

        noticeviewNoticeText = findViewById(R.id.noticeViewNoticeText);
        String text = getIntent().getStringExtra("TEXT");
        noticeviewNoticeText.setText(text);

    }
}
