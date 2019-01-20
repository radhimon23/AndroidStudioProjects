package com.example.gauravkapadiya.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Keval on 8/26/2017.
 */

public class List_Adapter extends BaseAdapter {

    String name[]={};
    int img[]={};
    Context mcontext;
    LayoutInflater inflater;


    public List_Adapter(Context context, String[] name, int[] img) {
        this.name = name;
        this.img = img;
        this.mcontext=context;
        inflater.from(context);

    }

    @Override
    public int getCount() {

        return name.length;
    }

    @Override
    public Object getItem(int position)
    {
        return name[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public class ViewHolder{
        TextView mytext;
        ImageView myimg;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater=(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =inflater.inflate(R.layout.list_adapter,parent,false);

        ViewHolder holder=new ViewHolder();

        convertView.setTag(holder);

      /*  TextView mytext;
        CircleImageView myimg;
*/
        holder.myimg=(ImageView) convertView.findViewById(R.id.cardimg);
        holder.mytext=(TextView)convertView.findViewById(R.id.drink);

        holder.mytext.setText(name[position]);
        holder.myimg.setImageResource(img[position]);

        return convertView;
    }
}

