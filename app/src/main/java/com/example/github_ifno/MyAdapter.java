package com.example.github_ifno;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Acer on 2017/8/2.
 */

public class MyAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_ITEM = 1;

    private Context m_context;
    public  int  AdapterPosition;
    MainActivity m_MainActivity;
    public ArrayList<Users> m_Users;
    private static final String ACTIVITY_TAG="MyAdapter";
    private ArrayList<Integer> has_serch=new ArrayList<Integer>();


    public MyAdapter(Context context, MainActivity m_tmp, ArrayList<Users> tmp_Users)
    {
        m_Users =  tmp_Users;
        m_MainActivity = m_tmp;
        m_context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item, parent, false);
        return new VHItem(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){

          //  Product_value currentItem = (Product_value) m_product.m_Product_value_array.get(position);
          VHItem VHitem = (VHItem)holder;
            VHitem.user_name.setText(m_Users.get(position).getlogin());
            if(m_Users.get(position).getsite_admin().equals("true")) {
                VHitem.job_title.setVisibility(View.VISIBLE);
            }else
            {
                VHitem.job_title.setVisibility(View.GONE);
            }

        File has_file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "tmp_image/"+m_Users.get(position).getlogin()+".png");
        if (has_file.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(has_file.getAbsolutePath());
                VHitem.poto.setImageBitmap(myBitmap);
        }




        new DownloadImageTask(VHitem.poto)
                .execute(m_Users.get(position).getavatar_url(),m_Users.get(position).getlogin());



        if(position >0 && position%19 == 0 && position < 100) {  //20筆0~19因此除以19

            for(int value : has_serch)
            {
                if(value == position)
                {
                    return;
                }
            }
            has_serch.add(position);

            m_MainActivity.m_cmd_routine.touch_trigger(cmd_routine.get_users,(position/19)*20);
        }
    }

    public int getItemViewType(int position) {

        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
       return  m_Users.size();
    }
    class VHItem extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView user_name;
        private TextView job_title;
        private ConstraintLayout item_layout;
        private ImageView poto;

        private CardView cardview_id;

        public VHItem(View itemView) {
            super(itemView);
            user_name = (TextView)itemView.findViewById(R.id.user_name);
            job_title = (TextView)itemView.findViewById(R.id.job_title);
            poto = (ImageView)itemView.findViewById(R.id.poto);
            item_layout = (ConstraintLayout)itemView.findViewById(R.id.item_layout);

            this.user_name.setOnClickListener(this);
            this.job_title.setOnClickListener(this);
            this.item_layout.setOnClickListener(this);




            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            Log.v(ACTIVITY_TAG, "onclick");
            Intent intent = new Intent(m_MainActivity, User_infoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("login", m_Users.get(getAdapterPosition()).getlogin());
            intent.putExtras(bundle);

            m_MainActivity.startActivity(intent);



        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        String getlogin;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            getlogin = urls[1];
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

        protected void onPostExecute(Bitmap result) {
            // m_photo.setImageBitmap(result);

            File folder = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "tmp_image");
            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdirs();
            }
            if (success) {
                // Do something on success
            } else {
                // Do something else on failure
            }

            File has_file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "tmp_image/"+getlogin+".png");
            if (!has_file.exists()) {

                try {
                    FileOutputStream out = new FileOutputStream(has_file);
                    result.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.flush();
                    out.close();
                    m_MainActivity.change_ui();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

}