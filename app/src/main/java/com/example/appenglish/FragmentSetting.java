package com.example.appenglish;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.User;
import com.example.appenglish.Utils.Utils;

import java.time.LocalDateTime;

public class FragmentSetting extends Fragment {
    View view;
    TextView tvChangeImg;
    ImageView imageView;
    EditText txtFullName, txtUserNameST, txtPasswordST, txtNewPassword;
    EngLishAppDatabaseAdapter engLishAppDatabaseAdapter;
    Button btnSavaST,btnLogout;
    private String linkImg = "";
    private static final int REQUEST_SELECT_IMAGE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        tvChangeImg = view.findViewById(R.id.tvChangeImg);
        imageView = view.findViewById(R.id.imgAvatar);

        txtFullName = view.findViewById(R.id.txtFullName);
        txtUserNameST = view.findViewById(R.id.txtUserNameST);
        txtPasswordST = view.findViewById(R.id.txtPasswordST);
        txtNewPassword = view.findViewById(R.id.txtNewPassword);

        btnSavaST = view.findViewById(R.id.btnSaveST);
        btnLogout = view.findViewById(R.id.btnLogout);
        try {
            User.users = engLishAppDatabaseAdapter.getRowUserProfile(LoginActivity.user.getID());
            txtFullName.setText(User.users.get(0).getFull_name());
            txtUserNameST.setText(User.users.get(0).getUser_name());
            linkImg = User.users.get(0).getImg_avatar();

        } catch (Exception e) {

        }
        if(linkImg.trim().equals("")){
            //ảnh rỗng sẽ đưa ra avatar mặc định
            imageView.setImageResource(R.drawable.images);
        }
        else{
            //ảnh không rỗng đưa ra ảnh
            Glide.with(this).load(linkImg).into(imageView);
        }

        tvChangeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_SELECT_IMAGE);
            }
        });

        btnSavaST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nếu pass rỗng thì không đổi pass
                if (txtNewPassword.getText().toString().trim().equals("")) {
                    engLishAppDatabaseAdapter.updateUser(txtUserNameST.getText().toString().trim(), txtFullName.getText().toString().trim(), User.users.get(0).getPassword(), linkImg);
                } else {
                    //check mật khẩu hiện tại
                    if (txtPasswordST.getText().toString().trim().equals(User.users.get(0).getPassword())) {
                        engLishAppDatabaseAdapter.updateUser(txtUserNameST.getText().toString().trim(), txtFullName.getText().toString().trim(), txtNewPassword.getText().toString().trim(), linkImg);
                    }
                    else {
                        Utils.showToast(getContext(),"Mật khẩu không chính xác !");
                    }
                }
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển đến màn login
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                //reset user
                resetUser();
            }
        });
        return view;
    }

    private void resetUser() {
        //lưu tài khoản mật khẩu thành rỗng
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor user = sharedPreferences.edit();
        user.putString("USERNAME", ""); // Lưu một chuỗi với khóa "key"
        user.putString("PASSWORD", ""); // Lưu một chuỗi với khóa "key"
        user.apply(); // Áp dụng các thay đổi vào Shared Preferences
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            Glide.with(this).load(selectedImageUri).into(imageView);
            //lấy đường link để cho vào database
            linkImg = selectedImageUri.toString();
        }
    }

}