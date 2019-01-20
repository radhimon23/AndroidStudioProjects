package com.example.gauravkapadiya.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DrawerAdapter_home extends BaseAdapter {

	private List<DrawerItem> mDrawerItems;
	private LayoutInflater mInflater;
	private final boolean mIsFirstType;
	 Context context;
    private int selectedPosition = 0;
	 //Choose between two types of list items

	public DrawerAdapter_home(Context context, List<DrawerItem> items, boolean isFirstType) {
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mDrawerItems = items;
		mIsFirstType = isFirstType;
		this.context=context;
	}



	@Override
	public int getCount() {
		return mDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return Integer.parseInt(mDrawerItems.get(position).getid());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;


				convertView = mInflater.inflate(R.layout.list_view_item, parent, false);

			holder = new ViewHolder();
			holder.icon = (ImageView) convertView.findViewById(R.id.icon); // holder.icon object is null if mIsFirstType is set to false
			holder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);



		DrawerItem item = mDrawerItems.get(position);


			holder.icon.setImageResource(item.getIcon1());
		holder.title.setText(item.getTitle());
//        selectedPosition=position;

        if (position == selectedPosition)
        {

            holder.title .setTextColor(context.getResources().getColor(R.color.colorPrimary));

        }

		//Log.d("drawer items",item.getTitle());
		return convertView;
	}

    public void setSelectedPosition(int position) {

        this.selectedPosition = position;

    }
	
	private static class ViewHolder {
		public ImageView icon;
		public /*Roboto*/TextView title;
	}
}
