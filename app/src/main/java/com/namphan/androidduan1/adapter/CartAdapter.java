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
import com.namphan.androidduan1.database.CartDao;
import com.namphan.androidduan1.database.TheLoaiDAO;
import com.namphan.androidduan1.model.Cart;
import com.namphan.androidduan1.model.TheLoai;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    List<Cart> arrCart;
    public Activity context;
    public LayoutInflater inflater;
    CartDao cartDao;





    public CartAdapter(Activity context, List<Cart> arrCart) {
        super();
        this.arrCart = arrCart;
        this.context = context;
        this.inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cartDao=new CartDao(context);

    }

    @Override
    public int getCount() {
        return arrCart.size();
    }

    @Override
    public Object getItem(int position) {
        return arrCart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {

        TextView txtGia,txtTenMon;

        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CartAdapter.ViewHolder holder;
        if(convertView==null)
        {
            holder = new CartAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_cart, null);
            holder.txtGia =  convertView.findViewById(R.id.tvGia);
            holder.txtTenMon=convertView.findViewById(R.id.tvTenMon);

            holder.imgDelete = convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartDao.deleteTheLoaiByID(arrCart.get(position).getMaMon());

                    arrCart.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);

        }

        else
            holder=(CartAdapter.ViewHolder)convertView.getTag();

        Cart _entry =  arrCart.get(position);




        holder.txtGia.setText(_entry.getGia()+" $");
        holder.txtTenMon.setText(_entry.getTenMon());

        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<Cart> items){
        this.arrCart = items;
        notifyDataSetChanged();
    }


}
