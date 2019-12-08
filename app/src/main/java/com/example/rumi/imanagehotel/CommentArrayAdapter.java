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
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.rumi.imanagehotel.Database.CommentDatabaseHelper;
import com.example.rumi.imanagehotel.Database.CustomerDatabaseSource;

import java.util.ArrayList;

public class CommentArrayAdapter extends ArrayAdapter<Comment> {

    private Context context;
    private ArrayList<Comment>comments;
    private CustomerDatabaseSource cusTbl;
    private CommentDatabaseHelper commentTBL;

    public CommentArrayAdapter( Context context,   ArrayList<Comment> comments) {
        super(context,R.layout.comment_list_layout,comments);
        this.context = context;
        this.comments = comments;
        cusTbl = new CustomerDatabaseSource(context);
        commentTBL = new CommentDatabaseHelper(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.comment_list_layout,parent,false);
        TextView userName = convertView.findViewById(R.id.commentLayoutUsername);
        TextView roomId = convertView.findViewById(R.id.commentLayoutRoomId);
        final TextView commentText = convertView.findViewById(R.id.commentLayoutComment);

        userName.setText(cusTbl.getCustomerDetailById(comments.get(position).getCusId()).getCusUserName());
        roomId.setText(String.format(Integer.toString(comments.get(position).getRoomId()), "%d"));
        commentText.setText(comments.get(position).getComment());

        commentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(context,commentText);
                menu.getMenuInflater().inflate(R.menu.delete_menu,menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.only_delete_menu){
                            boolean deleted = commentTBL.deleteCommnentByCommentId(comments.get(position).getCommentId());
                            comments.remove(position);
                            notifyDataSetChanged();
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
