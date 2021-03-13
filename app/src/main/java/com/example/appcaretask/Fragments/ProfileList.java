package com.example.appcaretask.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcaretask.Bean.ProfileBean;
import com.example.appcaretask.R;
import com.example.appcaretask.RoomDB.TaskAppDatabase;
import com.example.appcaretask.SqlLiteDB.DatabaseHelperClass;

import java.util.List;

public class ProfileList extends Fragment {
    RecyclerView rv;
    TextView norecorsfound_tv;
    ProfileListAdapter adapter;
    TaskAppDatabase appDatabase;
    Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profillist, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUI(view);
        initRecyclerView(view);
        getAllDataFromdb();

    }

    private void initUI(View view) {
        rv = view.findViewById(R.id.rv);
        norecorsfound_tv = view.findViewById(R.id.norecorsfound_tv);

    }

    private void initRecyclerView(View view) {
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());
    }

    private void getAllDataFromdb() {

        //Eanable for room DataBase

        // appDatabase = TaskAppDatabase.getInstance(getActivity());
        //List<ProfileBean> profileList = appDatabase.taskDao().getAll();

        //Eanable for SQLite DataBase

        DatabaseHelperClass databaseHelperClass=new DatabaseHelperClass(getActivity());
        List<ProfileBean> profileList = databaseHelperClass.getEmployeeList();

        if (profileList.size() != 0) {
            rv.setVisibility(View.VISIBLE);
            norecorsfound_tv.setVisibility(View.GONE);
             adapter = new ProfileListAdapter(mContext, profileList);
            rv.setAdapter(adapter);
        }
        else {
            rv.setVisibility(View.GONE);
            norecorsfound_tv.setVisibility(View.VISIBLE);
        }
    }
}




