package com.example.rumi.imanagehotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rumi.imanagehotel.Database.CommentDatabaseHelper;

public class Comment_List_VIew_Page extends AppCompatActivity {

    private CommentDatabaseHelper coomentTBL;
    private ListView commentList;
    private TextView datahave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment__list__view__page);

        datahave = findViewById(R.id.commenthave);

        coomentTBL = new CommentDatabaseHelper(this);
        commentList = findViewById(R.id.commentListView);

        if(Comment.getComments().isEmpty()){
            datahave.setVisibility(View.VISIBLE);
        }else{
            datahave.setVisibility(View.GONE);
        }

        CommentArrayAdapter commentArrayAdapter = new CommentArrayAdapter(this,Comment.getComments());
        commentList.setAdapter(commentArrayAdapter);

    }
}
