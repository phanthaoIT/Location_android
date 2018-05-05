package Modules;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.thanh.doanlocation_nhom24.R;

/**
 * Created by Thanh on 4/26/2018.
 */

public class BaseActivity extends AppCompatActivity {

    protected android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void inItToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
