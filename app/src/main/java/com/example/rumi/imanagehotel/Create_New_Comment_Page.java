package com.example.rumi.imanagehotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.CommentDatabaseHelper;

public class Create_New_Comment_Page extends AppCompatActivity {

    private EditText commnetEdintText;
    private Button commentButton;
    private CommentDatabaseHelper commentTBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__new__comment__page);

        commnetEdintText = findViewById(R.id.newCommentEditText);
        commentButton = findViewById(R.id.newCommontAddButton);
        commentTBL = new CommentDatabaseHelper(this);

        final int roomId = getIntent().getIntExtra("roomId",0);

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentText = commnetEdintText.getText().toString();
                if(commentText.isEmpty()){
                    commnetEdintText.setError(getString(R.string.blankET));return;
                }
                Comment comment = new Comment(roomId, Customer.getLoggedInCustomer().getCusId(),commentText);
                boolean insert =  commentTBL.insertCommnent(comment);
                if(insert){
                    Toast.makeText(Create_New_Comment_Page.this,"Posted" , Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Create_New_Comment_Page.this,"Failed", Toast.LENGTH_LONG).show();
                }
                onBackPressed();
            }
        });

    }
}
