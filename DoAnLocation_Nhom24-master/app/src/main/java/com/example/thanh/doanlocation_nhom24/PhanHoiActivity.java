package com.example.thanh.doanlocation_nhom24;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import Modules.BaseActivity;

public class PhanHoiActivity extends BaseActivity {

    private EditText edEmail,edNoiDung;
    private final String EMAIL = "thanhvantran052@gmail.com";
    private String email="",noidung="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_hoi);
        inItToolBar("");

        AnhXa();
        AddSuKien();
    }

    private void AddSuKien() {
    }

    private void AnhXa() {
        edEmail = findViewById(R.id.edEmail);
        edNoiDung = findViewById(R.id.edNoiDungPhanHoi);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.phan_hoi_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.itPhanHoi: {
                email = edEmail.getText().toString();
                if(email.equals("")) {
                    Toast.makeText(this, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
                }
                else {
                    noidung = edNoiDung.getText().toString();
                    if(noidung.equals("")){
                        Toast.makeText(this, "Bạn chưa nhập nội dung", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent emailPhanHoi = new Intent(Intent.ACTION_SENDTO);
                        emailPhanHoi.setData(Uri.parse("mailto:"+EMAIL));
                        emailPhanHoi.putExtra(Intent.EXTRA_SUBJECT,email);
                        emailPhanHoi.putExtra(Intent.EXTRA_TEXT,noidung);

                        try {
                            startActivity(Intent.createChooser(emailPhanHoi,"Send email"));
                            finish();
                        }
                        catch(android.content.ActivityNotFoundException ex){
                            Toast.makeText(this, "Mail của bạn không tồn tại!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            break;
            case android.R.id.home:
            {
                onBackPressed();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
