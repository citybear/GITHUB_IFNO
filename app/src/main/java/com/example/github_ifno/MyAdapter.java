package com.example.github_ifno;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Acer on 2017/8/2.
 */

public class MyAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_ITEM = 1;

    //Header header;
  //  public Product m_product;
    private Context m_context;
    public  int  AdapterPosition;
    MainActivity m_MainActivity;
    public ArrayList<String> m_file_click;
    private static final String ACTIVITY_TAG="MyAdapter";

    public MyAdapter(Context context, MainActivity m_tmp, ArrayList<String> tmp_file_click)
    {
        m_file_click =  tmp_file_click;
        m_MainActivity = m_tmp;
        m_context = context;
    }
    public void setvalue(ArrayList<String> tmp_file_click)
    {
        m_file_click =  tmp_file_click;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /**
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item, parent, false);
            //v.setPadding(5,5,5,5);
         return VHItem(v);
**/
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item, parent, false);
        //v.setPadding(5,5,5,5);

        return new VHItem(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){

          //  Product_value currentItem = (Product_value) m_product.m_Product_value_array.get(position);
          VHItem VHitem = (VHItem)holder;
      //      VHitem.file_name.setText(m_file_click.get(position)[0]);
     //       VHitem.ssid_name.setText(m_file_click.get(position)[1]);

            /**
            if(m_AlbumActivity.LPS.equals(""))
            {
                VHitem.file_value.setText("");
                return;
            }

            if(m_file_click.get(position)[0].contains(m_AlbumActivity.LPS) || m_file_click.get(position)[0].equals(m_AlbumActivity.LPS)) {
                    VHitem.file_value.setText("now");
            }else
            {
                VHitem.file_value.setText("");
            }
             **/
            //VHitem.m_product_img.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.product_sp));
    //    Picasso.with(context).load(www.google.com/image/1).placeholder(context.getResources().getDrawable(R.drawable.default_person_image)).error(context.getResources().getDrawable(R.drawable.default_person_image)).into(pictureView);
  //          Picasso.with(m_context).load(toUtf8String("https://www.esunbank.com.tw/bank/-/media/esunbank/images/home/personal/wealth/pages/derivatives.png?h=200&la=en&w=200")).into(VHitem.m_product_img);

        /**
        if(m_product.m_Product_value_array.get(position).b2c_is_track.equals("true"))
        {
            VHitem.m_heard_set.setImageResource(R.drawable.heard_fill);
        }else
        {
            VHitem.m_heard_set.setImageResource(R.drawable.heart_icon);
        }
            VHitem.info_text.setText(currentItem.name);
            VHitem.price_txt.setText(currentItem.b2c_price);
**/
    }

    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
       return  m_file_click.size();
    }
    class VHItem extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView user_name;
        private TextView job_title;
        private ConstraintLayout item_layout;

        private CardView cardview_id;

        public VHItem(View itemView) {
            super(itemView);
            user_name = (TextView)itemView.findViewById(R.id.user_name);
            job_title = (TextView)itemView.findViewById(R.id.job_title);
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
            m_MainActivity.startActivity(intent);

        }
    }
}