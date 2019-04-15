package com.namphan.androidduan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.namphan.androidduan1.R;
import com.namphan.androidduan1.database.MapDao;
import com.namphan.androidduan1.model.Maps;

import java.util.List;

public class MapAdapter extends BaseAdapter {
    List<Maps> arrMaps;
    public Activity context;
    public LayoutInflater inflater;
    MapDao mapDao;

    @Override
    public int getCount() {
        return arrMaps.size();
    }

    @Override
    public Object getItem(int position) {
        return arrMaps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrMaps.size();
    }

    public MapAdapter(Activity context, List<Maps> arrMaps) {
        super();
        this.arrMaps = arrMaps;
        this.context = context;
        this.inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mapDao=new MapDao(context);
    }

    public static class ViewHolder {

        TextView txtLongtitui;
        TextView txtLatitui;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_maps,null);
            holder.txtLongtitui =  convertView.findViewById(R.id.longtitu);
            holder.txtLatitui = convertView.findViewById(R.id.latitui);

            holder.imgDelete = convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mapDao.deleteMapsID(arrMaps.get(position).getLongtitui()+"");
                    arrMaps.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }
        else
            holder= (ViewHolder) convertView.getTag();

        Maps _entry =  arrMaps.get(position);

        holder.txtLongtitui.setText(_entry.getLongtitui()+"");
        holder.txtLatitui.setText(_entry.getLatitui()+"");

        return convertView;
    }
    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<Maps> items){
        this.arrMaps = items;
        notifyDataSetChanged();
    }

}
