package com.example.rumi.imanagehotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.NoticeDatabaseSource;

public class Create_New_Notice extends AppCompatActivity {

    private EditText newNoticeEditText , newNoticeDate ;
    private RadioGroup newNoticeRadioGroup;
    private RadioButton newNoticeSelectedRadioButton;
    private Button newNoticeeSaveButton;

    private NoticeDatabaseSource noticeTBL;

    boolean wantToUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__new__notice);

        newNoticeEditText = findViewById(R.id.newNoticeEditText);
        newNoticeDate = findViewById(R.id.newNoticeDate);
        newNoticeRadioGroup = findViewById(R.id.newNoticeRadioGroup);
        newNoticeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                newNoticeSelectedRadioButton = findViewById(checkedId);
            }
        });
        newNoticeeSaveButton = findViewById(R.id.newNoticeSaveButton);
        noticeTBL = new NoticeDatabaseSource(this);

        final Notice innotice = (Notice) getIntent().getSerializableExtra("notice");

        if(innotice!=null){
            wantToUpdate = true;
            newNoticeEditText.setText(innotice.getNoticeText());
            newNoticeDate.setText(innotice.getNoticeDate());
            newNoticeeSaveButton.setText("Update");
        }
        else{
            wantToUpdate = false;
            newNoticeeSaveButton.setText("Save");
        }



        newNoticeeSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String noticeText = newNoticeEditText.getText().toString();
                String  noticePrivacy = newNoticeSelectedRadioButton.getText().toString();
                String noticeDate =  newNoticeDate.getText().toString();

                Notice notice = new Notice(noticeText,noticePrivacy,noticeDate);

                if(noticeText.isEmpty()){
                    newNoticeEditText.setError(getString(R.string.blankET));
                    return;
                }

                if(!wantToUpdate){
                    boolean noticeInserted = noticeTBL.insertNotice(notice);
                    if(noticeInserted){
                        Toast.makeText(Create_New_Notice.this,"Posted",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Create_New_Notice.this,"Failed",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    boolean noticeUpdate = noticeTBL.updateNotice(notice,innotice.getNoticeId());
                    if(noticeUpdate){
                        Toast.makeText(Create_New_Notice.this,"Updated",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Create_New_Notice.this,"Failed",Toast.LENGTH_LONG).show();
                    }
                }




                onBackPressed();
            }
        });


    }
}
