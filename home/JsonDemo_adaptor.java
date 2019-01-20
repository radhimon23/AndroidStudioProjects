package com.example.gauravkapadiya.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by radhikamonpara on 12/03/18.
 */

public class JsonDemo_adaptor extends BaseAdapter {

    Context context;
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> rollno=new ArrayList<>();
    ArrayList<String> std=new ArrayList<>();


    public JsonDemo_adaptor(Context context, ArrayList<String> name, ArrayList<String> rollno, ArrayList<String> std) {
        this.context = context;
        this.name = name;
        this.rollno = rollno;
        this.std = std;
    }

    @Override
    public int getCount() {
        return rollno.size();
    }

    @Override
    public Object getItem(int i) {
        return rollno.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.single_list_item,viewGroup,false);


        TextView names,roll,standard;

        names=(TextView)view.findViewById(R.id.names);
        roll=(TextView)view.findViewById(R.id.rollno);
        standard=(TextView)view.findViewById(R.id.std);


        names.setText("Your Name Is : "+name.get(i));
        roll.setText("Your RollNo Is : "+rollno.get(i));
        standard.setText("Your Standard Is : "+std.get(i));

        return view;
    }
}
