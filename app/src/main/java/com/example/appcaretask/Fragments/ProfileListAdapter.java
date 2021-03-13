package com.example.appcaretask.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appcaretask.Bean.ProfileBean;
import com.example.appcaretask.R;
import com.example.appcaretask.RoomDB.TaskAppDatabase;
import com.example.appcaretask.SqlLiteDB.DatabaseHelperClass;

import java.util.List;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.MyViewHolder> {
    Context mcontext;
    List<ProfileBean> profileList;


    public ProfileListAdapter(Context activity, List<ProfileBean> profileList) {
        this.mcontext = activity;
        this.profileList = profileList;

    }

    @NonNull
    @Override
    public ProfileListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ProfileListAdapter.MyViewHolder holder, final int position) {

        final ProfileBean bean = profileList.get(position);
        holder.name_tv.setText("Name : "+bean.getName());
        holder.dept_tv.setText("Department : "+bean.getDepartment());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position,bean);
            }
        });

        Glide.with(mcontext)
                .load(bean.getImg_url())
                .placeholder(R.drawable.progress_animation)
                // .error(R.drawable.ic_launcher_background)
                .into(holder.profile_image);
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public void removeItem(int position, ProfileBean bean) {
        profileList.remove(position);
        notifyItemRemoved(position);

        //Eanable for room DataBase

       // TaskAppDatabase appDatabase = TaskAppDatabase.getInstance(mcontext);
      // appDatabase.taskDao().delete(bean);

        //Eanable for SQLite DataBase
        DatabaseHelperClass databaseHelperClass=new DatabaseHelperClass(mcontext);
        databaseHelperClass.deleteEmployee(bean.getSno());
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dept_tv, name_tv;
        ImageView profile_image;
        ImageButton delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image = itemView.findViewById(R.id.profile_image);
            name_tv = itemView.findViewById(R.id.name_tv);
            dept_tv = itemView.findViewById(R.id.dept_tv);
            delete = itemView.findViewById(R.id.delete);

        }
    }



}


