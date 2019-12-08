package com.example.rumi.imanagehotel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.rumi.imanagehotel.Database.NoticeDatabaseSource;

import java.util.ArrayList;

public class NoticeArrayAdapter extends ArrayAdapter<Notice> {

    private Context context;
    private ArrayList<Notice>notices;
    private NoticeDatabaseSource noticeTBL;

    public NoticeArrayAdapter(@NonNull Context context,  ArrayList<Notice>notices) {
        super(context, R.layout.notice_list_layout, notices);
        this.notices = notices;
        this.context = context;
        noticeTBL = new NoticeDatabaseSource(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.notice_list_layout,parent,false);

        TextView noticeText = convertView.findViewById(R.id.noticeLayoutTextView);
        TextView noticeDate = convertView.findViewById(R.id.noticeDateLayoutTextView);
        TextView noticePrivacy = convertView.findViewById(R.id.noticePrivacyLayoutTextView);
        final ImageView noticeMenuButton = convertView.findViewById(R.id.optionsIconForNoticeLayout);

        noticeText.setText(notices.get(position).getNoticeText());
        noticePrivacy.setText(notices.get(position).getNoticePrivacy());
        noticeDate.setText(notices.get(position).getNoticeDate());

        if(LoginPageActivity.getLoginStatus()== 1){
            noticeMenuButton.setVisibility(View.VISIBLE);
        }
        if(LoginPageActivity.getLoginStatus()== 2 || LoginPageActivity.getLoginStatus()== 3 ){
            noticeMenuButton.setVisibility(View.GONE);
        }

        noticeMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(context,noticeMenuButton);
                menu.getMenuInflater().inflate(R.menu.employee_options_menu,menu.getMenu());

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.employee_delete_menu){
                            boolean deleted = noticeTBL.deleteNoticeById(notices.get(position).getNoticeId());
                            notices.remove(position);
                            notifyDataSetChanged();
                        }
                        if(item.getItemId() == R.id.employee_update_menu){
                            context.startActivity(new Intent(context,Create_New_Notice.class).putExtra("notice",notices.get(position)));
                        }
                        return false;
                    }
                });
                menu.show();
            }
        });

        return convertView;
    }
}
