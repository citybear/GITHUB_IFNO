package com.example.github_ifno;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    public ArrayList<Users> m_Users;
    private static final String ACTIVITY_TAG="MyAdapter";

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
            Bundle bundle = new Bundle();
            bundle.putString("login", m_Users.get(getAdapterPosition()).getlogin());
            intent.putExtras(bundle);

            m_MainActivity.startActivity(intent);



        }
    }
}