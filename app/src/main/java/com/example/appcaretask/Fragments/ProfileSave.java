package com.example.appcaretask.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appcaretask.Bean.ProfileBean;
import com.example.appcaretask.Common.Constants;
import com.example.appcaretask.MainActivity;
import com.example.appcaretask.R;
import com.example.appcaretask.RoomDB.TaskAppDatabase;
import com.example.appcaretask.SqlLiteDB.DatabaseHelperClass;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ProfileSave extends Fragment {

    private TaskAppDatabase appDatabase;
    ProfileBean bean = new ProfileBean();
    EditText edit_name;
    Spinner dept_sp;
    ImageButton upload_img;
    ImageView imgPreview;
    Button save;
    String dept_str = "";
    String imageUrl = "";
    Uri selectedImageUri = null;
    private static final int STORAGE_PERMISSION_CODE_Storage = 101;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profilesave, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        appDatabase = TaskAppDatabase.getInstance(getActivity());

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Select Department");
        arrayList.add("IAS");
        arrayList.add("IFS");
        arrayList.add("IRS");
        arrayList.add("IPS");
        arrayList.add("IT");


        edit_name = view.findViewById(R.id.name);
        dept_sp = view.findViewById(R.id.department);
        upload_img = view.findViewById(R.id.uplaodimage);
        save = view.findViewById(R.id.save);
        imgPreview = view.findViewById(R.id.imgPreview);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dept_sp.setAdapter(arrayAdapter);
        dept_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dept_str = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateFeilds()) {
                    setDatatopbean();

                    //Eanable for room DataBase
                    //  appDatabase.taskDao().insert(bean);

                    //Eanable for SQLite DataBase

                    DatabaseHelperClass databaseHelperClass=new DatabaseHelperClass(getActivity());
                    databaseHelperClass.addEmployee(bean);

                    Constants.displayLongToast(getActivity(), "Your Profile Saved!");
                    edit_name.setText("");
                    imageUrl = "";
                    dept_sp.setSelection(0);
                    imgPreview.setVisibility(View.GONE);


                }
            }
        });


    }

    private void setDatatopbean() {

        bean.setName(edit_name.getText().toString());
        bean.setDepartment(dept_str);
        bean.setImg_url(imageUrl);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK)
                selectedImageUri = data.getData();
            imageUrl = selectedImageUri.toString();
            imgPreview.setVisibility(View.VISIBLE);

            Glide.with(getActivity())
                    .load(imageUrl)
                    .into(imgPreview);
        }
    }


    private boolean validateFeilds() {
        boolean validate = false;

        if (TextUtils.isEmpty(edit_name.getText().toString())) {
            validate = false;
            Constants.displayLongToast(getActivity(), "Name cant be empty");
        } else if (TextUtils.isEmpty(dept_str) || dept_str.equalsIgnoreCase("Select Department")) {
            validate = false;
            Constants.displayLongToast(getActivity(), "Dept cant be empty");

        } else if (TextUtils.isEmpty(imageUrl)) {
            validate = false;
            Constants.displayLongToast(getActivity(), "Image cant be empty");

        } else {


            validate = true;

        }


        return validate;
    }


   /* public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(getActivity(), permission) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE_Storage) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            } else {
                Toast.makeText(getActivity(), "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }*/

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select a picture"), 101);

    }


}
