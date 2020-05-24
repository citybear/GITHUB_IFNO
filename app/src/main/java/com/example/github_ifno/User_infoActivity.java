package com.example.github_ifno;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class User_infoActivity extends AppCompatActivity {
    private oneUser m_oneUser;
    private cmd_routine m_cmd_routine;
    private TextView link_text;
    private TextView address_text;
    private TextView job_title;
    private TextView group_name;
    private TextView user_name;
    private ImageView close;
    private ImageView m_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        Bundle bundle = getIntent().getExtras();
        String login = bundle.getString("login");

        m_oneUser  =new oneUser();
        m_cmd_routine = new cmd_routine(User_infoActivity.this,m_oneUser,login);
    }
    @Override
    protected void onStart() {
        super.onStart();
        m_cmd_routine.touch_trigger(cmd_routine.get_oneuser);
        m_photo = (ImageView) findViewById(R.id.photo);
        link_text = (TextView)findViewById(R.id.link_text);
        address_text = (TextView)findViewById(R.id.address_text);
        job_title = (TextView)findViewById(R.id.job_title);
        group_name = (TextView)findViewById(R.id.group_name);
        user_name = (TextView)findViewById(R.id.user_name);
        close = (ImageView)findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });

    }

    public void updateui(){
        super.onStart();

        File has_file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "tmp_image/"+m_oneUser.getavatar_url()+".png");
        if (has_file.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(has_file.getAbsolutePath());
            m_photo.setImageBitmap(myBitmap);
        }else {
            new DownloadImageTask(m_photo)
                    .execute(m_oneUser.getavatar_url());
        }

        user_name.setText(m_oneUser.getname());
        group_name.setText(m_oneUser.getcompany());
        address_text.setText(m_oneUser.getlocation());
        link_text.setText(m_oneUser.getblog());
        if(m_oneUser.getsite_admin().equals("true"))
        {
            job_title.setVisibility(View.VISIBLE);
        }

        link_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(m_oneUser.getblog()));
                startActivity(browserIntent);

            }
        });



/**
        m_oneUser.getname();
        m_oneUser.getcompany();
        m_oneUser.getsite_admin();
        m_oneUser.getblog();
 **/
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
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

        protected void onPostExecute(Bitmap result) {
            m_photo.setImageBitmap(result);
        }
    }

}
