package com.example.github_ifno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Acer on 2017/8/2.
 */

public class MyAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public static final int SECTION_VIEW = 0;
    public static final int CONTENT_VIEW = 1;
    private static final int TYPE_TREE= 2;
   // private Main_page m_main_page;
    //Header header;
  //  public Product m_product;
    private Context m_context;
    public  int  AdapterPosition;
    MainActivity m_AlbumActivity;
    public ArrayList<String> m_file_click;
    public MyAdapter(Context context, MainActivity m_tmp, ArrayList<String> tmp_file_click)
    {
        m_file_click =  tmp_file_click;
        m_AlbumActivity = m_tmp;
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
//        TextView txtName;
        TextView file_name;
        TextView ssid_name;
        TextView file_value;
        /**
        ImageView m_heard_set;
        TextView info_text;
        TextView  price_txt;
         **/
        public VHItem(View itemView) {
            super(itemView);

            this.file_name = (TextView)itemView.findViewById(R.id.file_name);
            this.ssid_name = (TextView)itemView.findViewById(R.id.ssid_name);
            this.file_value = (TextView)itemView.findViewById(R.id.file_value);
     //      this.m_product_img = (ImageView)itemView.findViewById(R.id.product_img);
           /**
            this.m_heard_set = (ImageView)itemView.findViewById(R.id.heard_set);
            this.info_text = (TextView) itemView.findViewById(R.id.info_text);
            this.price_txt = (TextView) itemView.findViewById(R.id.price_txt);

            this.m_product_img.setOnClickListener(this);
            **/
            /**
            this.m_heard_set.setOnClickListener(this);
             **/
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

          //  m_AlbumActivity.multselect(getAdapterPosition());
            /**
            AlbumActivity.clock_index = getAdapterPosition();

            m_AlbumActivity.videoAdapter.m_file_data = m_file_click.get(getAdapterPosition())[0];
            m_AlbumActivity.m_filemanage.loadfile(m_AlbumActivity.videoAdapter.m_file_data);
            m_AlbumActivity.videoAdapter.notifyDataSetChanged();
            m_AlbumActivity.play_keep.set(false);
            ArrayList<file_data> tmp_array = new ArrayList<file_data>();
            for(file_data  jk : m_AlbumActivity.videoAdapter.m_file_data)
            {
                tmp_array.add(jk);
            }

            m_AlbumActivity.getAarraybitmap(m_AlbumActivity.videoAdapter,m_AlbumActivity.videoAdapter.m_file_data,tmp_array);
            m_AlbumActivity.recyclerView.setVisibility(View.GONE);
            m_AlbumActivity.videoPager.setVisibility(View.VISIBLE);
            m_AlbumActivity.videoAdapter.notifyDataSetChanged();
            m_AlbumActivity.recyclerView2.setVisibility(View.GONE);


            AlbumActivity.MENU_FLAG = 2;
            m_AlbumActivity.title_value.setText(m_AlbumActivity.title_value.getText()+"  "+((file_click)(m_file_click.get(getAdapterPosition()))).name);
             **/
        }
    }
    public void updatebitmap()
    {

    }
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}