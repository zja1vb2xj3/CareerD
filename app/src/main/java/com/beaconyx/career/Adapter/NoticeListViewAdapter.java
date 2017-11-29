package com.beaconyx.career.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beaconyx.career.Color.ColorManager;
import com.beaconyx.career.Model.NoticeModel;
import com.beaconyx.career.R;

import java.util.ArrayList;

/**
 * Created by pdg on 2017-11-29.
 */

public class NoticeListViewAdapter extends BaseAdapter {
    private ArrayList<NoticeModel> noticeModels;
    private ColorManager colorManager;
    private Context context;

    public NoticeListViewAdapter(Context context) {
        this.context = context;
        noticeModels = new ArrayList<>();
        colorManager = new ColorManager(context.getResources());
    }

    public void addItem(NoticeModel model) {
        noticeModels.add(model);
    }


    @Override
    public int getCount() {
        return noticeModels.size();
    }

    @Override
    public Object getItem(int position) {
        return noticeModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_notice_list_row, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.time = (TextView) view.findViewById(R.id.time);
            viewHolder.text = (TextView) view.findViewById(R.id.text);
            viewHolder.image = (ImageView) view.findViewById(R.id.image);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        String title = noticeModels.get(position).getTitle();
        viewHolder.title.setText(title);

        String time = noticeModels.get(position).getTime();
        viewHolder.time.setText(time);

        String text = noticeModels.get(position).getText();
        viewHolder.text.setText(text);

        Drawable rightArrow = context.getResources().getDrawable(R.mipmap.right_arrow);
        ColorFilter arrowColor = new LightingColorFilter(colorManager.getRightArrow(), colorManager.getRightArrow());
        rightArrow.setColorFilter(arrowColor);
        viewHolder.image.setImageDrawable(rightArrow);

        return view;
    }

    private class ViewHolder {
        private TextView title;
        private TextView time;
        private TextView text;
        private ImageView image;
    }
}
