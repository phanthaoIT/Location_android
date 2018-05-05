package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.thanh.doanlocation_nhom24.TabDiaDiemSeDen;
import com.example.thanh.doanlocation_nhom24.TabYeuThich;

//Dùng để điều khiển 2 tab
public class PageAdapter extends FragmentStatePagerAdapter {

    private final TabYeuThich tabYeuThich;
    private final TabDiaDiemSeDen tabDiaDiemSeDen;

    public PageAdapter(FragmentManager fm) {
        super(fm);
        tabYeuThich = new TabYeuThich();
        tabDiaDiemSeDen = new TabDiaDiemSeDen();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return tabYeuThich;
            case 1:
                return tabDiaDiemSeDen;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Yêu Thích";
            case 1:
                return "Sẽ Đến";
        }
        return "";
    }
}
