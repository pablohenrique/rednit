package com.rednit.app.Controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rednit.app.Model.ResultPerson;
import com.rednit.app.R;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by pablohenrique on 9/27/15.
 */
public class CustomListAdapter
        extends ArrayAdapter<String> {

    private final Activity context;
//    private final String[] itemname;
//    private final Integer[] imgid;
    private final ResultPersonList resultPersonList;

//    public CustomListAdapter(Activity context, ResultPersonList resultPersonLists, String[] itemname, Integer[] imgid) {
//        super(context, R.layout.custom_list, itemname);
//        this.context = context;
//        this.itemname = itemname;
//        this.imgid = imgid;
//        this.resultPersonList = resultPersonLists;
//    }

    public CustomListAdapter(Activity context, ResultPersonList resultPersonLists) {
        super(context, R.layout.custom_list, resultPersonLists.getNames());
        this.context = context;
//        this.itemname = null;
//        this.imgid = null;
        this.resultPersonList = resultPersonLists;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

//        txtTitle.setText(itemname[position]);
//        imageView.setImageResource(imgid[position]);
//        extratxt.setText("Description "+itemname[position]);

        txtTitle.setText(resultPersonList.getNames().get(position));
        new DownloadImageTask(imageView).execute(resultPersonList.getProfileUrls().get(position));
        extratxt.setText("Description " + resultPersonList.getNames().get(position));

        return rowView;
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            bmImage.setImageBitmap(result);
        }
    }

}
