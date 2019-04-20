package com.namphan.androidduan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.namphan.androidduan1.R;


import com.namphan.androidduan1.database.TheLoaiDAO;

import com.namphan.androidduan1.model.TheLoai;

import java.util.List;



public class ProductAdapter extends BaseAdapter {
    List<TheLoai> arrTheLoai;
    public Activity context;
    public LayoutInflater inflater;
    TheLoaiDAO theLoaiDAO;





    public ProductAdapter(Activity context, List<TheLoai> arrTheLoai) {
        super();
        this.arrTheLoai = arrTheLoai;
        this.context = context;
        this.inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDAO=new TheLoaiDAO(context);

    }

    @Override
    public int getCount() {
        return arrTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtMaTheLoai;
        TextView txtTenTheLoai;
        ImageView imgDelete,imgChon;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_product, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.txtMaTheLoai = (TextView) convertView.findViewById(R.id.tvMaTheLoai);
            holder.txtTenTheLoai = (TextView) convertView.findViewById(R.id.tvTenTheLoai);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);






            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theLoaiDAO.deleteTheLoaiByID(arrTheLoai.get(position).getMaMon());

                    arrTheLoai.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);

        }

        else
            holder=(ViewHolder)convertView.getTag();

        TheLoai _entry =  arrTheLoai.get(position);

        if (position % 3 ==0) {
            holder.img.setImageResource(R.drawable.cafeden);
        }else if (position % 3 == 1){
            holder.img.setImageResource(R.drawable.cappuccino);
        }else {
            holder.img.setImageResource(R.drawable.escup);
        }


        holder.txtMaTheLoai.setText(_entry.getGia()+" $");
        holder.txtTenTheLoai.setText(_entry.getTenMon());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<TheLoai> items){
        this.arrTheLoai = items;
        notifyDataSetChanged();
    }

}