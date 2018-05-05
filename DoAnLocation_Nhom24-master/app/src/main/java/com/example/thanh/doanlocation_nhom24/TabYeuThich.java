package com.example.thanh.doanlocation_nhom24;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.CustomAdapter;
import Models.DiaDiemUaThich;

public class TabYeuThich extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;


    public TabYeuThich() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TabYeuThich newInstance(String param1) {
        TabYeuThich fragment = new TabYeuThich();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_yeu_thich,null);
        ListView listView = view.findViewById(R.id.lvDanhSachYeuThich);
        ArrayList<DiaDiemUaThich> dsUuThich = new ArrayList<>();
        dsUuThich.add(new DiaDiemUaThich(1,1,R.drawable.ic_launcher_background,R.drawable.ic_local_cafe_black_24dp,"Cafe Trung Nguyên","Ho Chi Minh"));
        dsUuThich.add(new DiaDiemUaThich(1,1,R.drawable.ic_launcher_background,R.drawable.ic_local_cafe_black_24dp,"Cafe Trung Nguyên 2","Ho Chi Minh"));
        CustomAdapter customAdapter = new CustomAdapter(getContext(),R.layout.layout_custom_listview_ddut,dsUuThich);
        listView.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
//        Todo
        return view;
    }

}
