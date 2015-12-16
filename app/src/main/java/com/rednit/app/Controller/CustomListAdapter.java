package com.rednit.app.Controller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rednit.app.R;

/**
 * Created by pablohenrique on 9/27/15.
 */
public class CustomListAdapter
        extends ArrayAdapter<String> {

    private final Activity context;
    private final ResultPersonList resultPersonList;

    public CustomListAdapter(Activity context, ResultPersonList resultPersonLists) {
        super(context, R.layout.custom_list, resultPersonLists.getNames());
        this.context = context;
        this.resultPersonList = resultPersonLists;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(resultPersonList.getNames().get(position));
        new DownloadImageTask(imageView).execute(resultPersonList.getProfileUrls().get(position));
        extratxt.setText("Description " + resultPersonList.getNames().get(position));

        return rowView;
    }

}
