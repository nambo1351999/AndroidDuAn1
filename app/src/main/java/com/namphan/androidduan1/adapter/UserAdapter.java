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
import com.namphan.androidduan1.database.UserDao;
import com.namphan.androidduan1.model.User;

import java.util.List;


public class UserAdapter extends BaseAdapter {
    List<User> arrUse;
    public Activity context;
    public LayoutInflater inflater;
    UserDao userDao;

    @Override
    public int getCount() {
        return arrUse.size();
    }

    @Override
    public Object getItem(int position) {
        return arrUse.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrUse.size();
    }

    public UserAdapter(Activity context, List<User> arrUse) {
        super();
        this.arrUse = arrUse;
        this.context = context;
        this.inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userDao=new UserDao(context);
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_user, null);
            holder.img =  convertView.findViewById(R.id.ivIcon);
            holder.txtName = convertView.findViewById(R.id.tvName);
            holder.txtPhone =  convertView.findViewById(R.id.tvPhone);
            holder.imgDelete = convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userDao.deleteNguoiDungByID(arrUse.get(position).getEmail());
                    arrUse.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();

        User _entry =  arrUse.get(position);
        if (position % 3 ==0) {
            holder.img.setImageResource(R.drawable.emone);
        }else if (position % 3 == 1){
            holder.img.setImageResource(R.drawable.emtwo);
        }else {
            holder.img.setImageResource(R.drawable.emthree);
        }         holder.txtName.setText(_entry.getFistname());
        holder.txtPhone.setText(_entry.getPhone());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<User> items){
        this.arrUse = items;
        notifyDataSetChanged();
    }

}
