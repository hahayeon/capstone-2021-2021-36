package org.techtown.googleloginexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResultActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private TextView tv_result; // 닉네임 text
    private ImageView iv_profile; // 이미지 뷰
    private Button btn_logout; // 로그아웃 버튼
    private Button btn_revoke; // 회원탈퇴 버튼
    //private FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName"); // MainActivity로 부터 닉네임 전달받음.
        String photoUrl = intent.getStringExtra("photoUrl"); // MainActivity로 부터 프로필사진 Url 전달받음.

        tv_result = findViewById(R.id.tv_result);
        tv_result.setText(nickName); // 닉네임 text를 텍스트 뷰에 세팅.


        iv_profile = findViewById(R.id.iv_profile);
        Glide.with(this).load(photoUrl).into(iv_profile); // 프로필 url을 이미지 뷰에 세팅.



        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ResultActivity.this, "로그아웃 하셨습니다.", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn_revoke = findViewById(R.id.btn_revoke);
        btn_revoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(ResultActivity.this);
                alert_confirm.setMessage("정말 계정을 삭제 할까요?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                user.delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(ResultActivity.this, "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
                                                finish();
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            }
                                        });
                            }
                        }
                );
                alert_confirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ResultActivity.this, "취소되었습니다.", Toast.LENGTH_LONG).show();
                    }
                });
                alert_confirm.show();
            }

                /*
                Toast.makeText(ResultActivity.this, "회원탈퇴 하셨습니다.", Toast.LENGTH_SHORT).show();
                mAuth.getCurrentUser().delete();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                 */

        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}