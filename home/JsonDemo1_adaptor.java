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

public class JsonDemo1_adaptor extends BaseAdapter {

    Context context1;
    ArrayList<String> name1=new ArrayList<>();
    ArrayList<String> rollno1=new ArrayList<>();

    public JsonDemo1_adaptor(Context context1, ArrayList<String> name1, ArrayList<String> rollno1) {
        this.context1 = context1;
        this.name1 = name1;
        this.rollno1 = rollno1;
    }


    @Override
    public int getCount() {
        return rollno1.size();
    }



    @Override
    public Object getItem(int i) {

        return rollno1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater)context1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.single_list_item1,viewGroup,false);

        TextView names1,roll1;

        names1=(TextView)view.findViewById(R.id.names1);
        roll1=(TextView)view.findViewById(R.id.rolls1);

        names1.setText("HI  "+name1.get(i));
        roll1.setText("your rollno. is : "+rollno1.get(i));

        return view;
    }
}
