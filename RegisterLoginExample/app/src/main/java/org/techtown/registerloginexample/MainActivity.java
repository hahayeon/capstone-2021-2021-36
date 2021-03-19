package org.techtown.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView tv_id, tv_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id = findViewById(R.id.tv_id);
        tv_pass = findViewById(R.id.tv_pass);

        Intent intent = getIntent();
        // String userID = intent.getStringExtra("userID");
        // String userPass = intent.getStringExtra("userPass");
        String userID = intent.getStringExtra("nickName"); // 구글 로그인 연동. nickName에 구글 사용자 이름 들어있음.
        // 실제론 파이어베이스에서 가지고 온 정보 사용할거임.

        tv_id.setText(userID);
        // tv_pass.setText(userPass);
    }
}