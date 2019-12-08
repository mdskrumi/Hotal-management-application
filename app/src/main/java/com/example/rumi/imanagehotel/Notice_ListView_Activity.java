package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rumi.imanagehotel.Database.NoticeDatabaseSource;

public class Notice_ListView_Activity extends AppCompatActivity {

    private ListView noticeListView;
    private ImageView noticeAddButton;

    private NoticeDatabaseSource noticeTBL;

    private TextView datahave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice__list_view_);

        noticeListView = findViewById(R.id.noticeListView) ;
        noticeAddButton= findViewById(R.id.addNewNoticeButton);
        noticeTBL = new NoticeDatabaseSource(this);

        datahave = findViewById(R.id.noticeHave);

        updateNoticeList();


        getSecured();

        noticeAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notice_ListView_Activity.this,Create_New_Notice.class));
            }
        });

        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Notice_ListView_Activity.this,NoticeViewActivity.class);
                intent.putExtra("TEXT",Notice.getNotices().get(position).getNoticeText());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateNoticeList();
        getSecured();
    }

    private void updateNoticeList(){
        if(LoginPageActivity.getLoginStatus()==3){
            Notice.setNotices(noticeTBL.getAllNoticePublic());
        }else{
            Notice.setNotices(noticeTBL.getAllNotice());
        }

        if(Notice.getNotices().isEmpty()){
            datahave.setVisibility(View.VISIBLE);
        }else{
            datahave.setVisibility(View.GONE);
        }

        NoticeArrayAdapter noticeArrayAdapter = new NoticeArrayAdapter(this,Notice.getNotices());
        noticeListView.setAdapter(noticeArrayAdapter);
    }
    private void getSecured(){
        if(LoginPageActivity.getLoginStatus()== 1){
            noticeAddButton.setVisibility(View.VISIBLE);
        }
        if(LoginPageActivity.getLoginStatus()== 2 || LoginPageActivity.getLoginStatus()== 3 ){
            noticeAddButton.setVisibility(View.GONE);
        }
    }

}
