package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import Models.DiaDiemUaThich;
import com.example.thanh.doanlocation_nhom24.R;

import java.util.List;

/**
 * Created by Thanh on 4/30/2018.
 */

public class CustomAdapter extends ArrayAdapter<DiaDiemUaThich> {

    private Context context;
    private int Resource;
    private List<DiaDiemUaThich> objects;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<DiaDiemUaThich> objects) {
        super(context, resource, objects);
        this.context = context;
        this.Resource = resource;
        this.objects = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(Resource,null);
            viewHolder = new ViewHolder();
            //Anh xa;
            viewHolder.imgDiaDiem = convertView.findViewById(R.id.imgDiaDiemUaThich);
            viewHolder.imgLoaiCuaHang = convertView.findViewById(R.id.imgLoaiCuaHang);
            viewHolder.txtTenCuaHang = convertView.findViewById(R.id.txtTenCuaHang);
            viewHolder.txtViTri = convertView.findViewById(R.id.txtViTri);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DiaDiemUaThich diaDiemUaThich = objects.get(position);
        viewHolder.imgDiaDiem.setImageResource(diaDiemUaThich.getImage());
        viewHolder.imgLoaiCuaHang.setImageResource(diaDiemUaThich.getImgLoaiDiaDiem());
        viewHolder.txtTenCuaHang.setText(diaDiemUaThich.getTenCuaHang());
        viewHolder.txtViTri.setText(diaDiemUaThich.getViTri());
        return convertView;
    }

    private class ViewHolder {
        ImageView imgDiaDiem;
        TextView txtTenCuaHang;
        TextView txtViTri;
        ImageView imgLoaiCuaHang;
    }
}
