package com.example.thanh.doanlocation_nhom24;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import Adapter.PageAdapter;
import Modules.BaseActivity;

public class DiaDiemCuaBanActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_diem_cua_ban);

        inItToolBar("Địa điểm của bạn");

        AnhXa();
        setupViewPapge(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }


    private void setupViewPapge(ViewPager viewPager) {
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }

    private void AnhXa() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager =findViewById(R.id.viewPaper);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
