package com.example.gauravkapadiya.home.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gauravkapadiya.home.Complains;
import com.example.gauravkapadiya.home.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.gauravkapadiya.home.R.drawable.drainage;

/**
 * Created by radhikamonpara on 22/03/18.
 */

public class complains_adaptor extends BaseAdapter {

    Context context2;

    ArrayList<String> image = new ArrayList<>();
    ArrayList<String> username = new ArrayList<>();
    ArrayList<String> complain_name = new ArrayList<>();

    public complains_adaptor(Complains complains, ArrayList<String> image, ArrayList<String> username, ArrayList<String> complain_name) {
        this.image = image;
        this.username = username;
        this.complain_name = complain_name;
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public Object getItem(int i) {
        return image.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        LayoutInflater inflater=(LayoutInflater)context2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.complains_list_item,viewGroup,false);

        TextView usernames,complains;
        ImageView images;

        images = (ImageView)view.findViewById(R.id.image1);
        images.setImageResource(drainage);


        Picasso.with(context2)
                .load("")
                .placeholder(R.drawable.drainage)
                .into(images);






        usernames=(TextView)view.findViewById(R.id.username);
        complains=(TextView)view.findViewById(R.id.complain_name);

        usernames.setText("USER IS "+username.get(i));
        complains.setText("COMPLAIN : "+complain_name.get(i));

        return view;
    }
}
