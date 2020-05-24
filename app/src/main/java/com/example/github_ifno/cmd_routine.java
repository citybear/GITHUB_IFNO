package com.example.github_ifno;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class cmd_routine implements Runnable {

    public static final int get_users = 1;
    public static final int get_oneuser = 2;
    MainActivity m_MainActivity;
    public Vector<int []> ｍ_queue = new Vector<int []>();
    private volatile int[] m_falg;
    private OkHttpClient  m_client;
    private ArrayList<Users> m_Users;
    private User_infoActivity  m_User_infoActivity;
    private oneUser m_oneUser;
    private String login_str;


    public cmd_routine(MainActivity activity,ArrayList<Users> tmp_Users) {
        m_MainActivity = activity;
        m_Users = tmp_Users;

        m_client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build();
        Thread t = new Thread(this);
        t.start();
    }

    public cmd_routine(User_infoActivity tmp_User_infoActivity,oneUser tmp_oneUser,String tmp_login_str) {
        m_User_infoActivity = tmp_User_infoActivity;
        m_oneUser = tmp_oneUser;
        login_str = tmp_login_str;
        m_client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build();
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        for (; ; ) {
            synchronized (this) {
                try {
                    if (ｍ_queue.size() == 0)
                        wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            switch (ｍ_queue.remove(0)[0]) {
                case get_users:

                    Request request = new Request.Builder()
                            .url("https://api.github.com/users?page=0&per_page=100")
                            .build();

                    m_client = new OkHttpClient()
                            .newBuilder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .build();


                    Call call = m_client.newCall(request);


                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            //        JSONObject reader3 = null;
                            try {


                                JSONObject reader = null;

                            //    reader = new JSONObject(response.body().string());

                                JSONArray array = new JSONArray(response.body().string());
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jsonObject = array.getJSONObject(i);

                                    Users tmp_users = new Users();


                                    tmp_users.setlogin(jsonObject.getString("login"));

                                    tmp_users.setid(jsonObject.getString("id"));

                                    tmp_users.setnode_id(jsonObject.getString("node_id"));

                                    tmp_users.setavatar_url(jsonObject.getString("avatar_url"));

                                    tmp_users.setgravatar_id(jsonObject.getString("gravatar_id"));

                                    tmp_users.seturl(jsonObject.getString("url"));

                                    tmp_users.sethtml_url(jsonObject.getString("html_url"));

                                    tmp_users.setfollowers_url(jsonObject.getString("followers_url"));

                                    tmp_users.setfollowing_url(jsonObject.getString("following_url"));

                                    tmp_users.setgists_url(jsonObject.getString("gists_url"));

                                    tmp_users.setrepos_url(jsonObject.getString("repos_url"));

                                    tmp_users.setevents_url(jsonObject.getString("events_url"));

                                    tmp_users.setorganizations_url(jsonObject.getString("organizations_url"));
                                    tmp_users.setreceived_events_url(jsonObject.getString("received_events_url"));
                                    tmp_users.settype(jsonObject.getString("type"));

                                    tmp_users.setsite_admin(jsonObject.getString("site_admin"));
                                    tmp_users.setstarred_url(jsonObject.getString("starred_url"));
                                    tmp_users.setsubscriptions_url(jsonObject.getString("subscriptions_url"));
                                    m_Users.add(tmp_users);



                                }

                                m_MainActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        m_MainActivity.change_ui();
                                    }
                                });


                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();

                        }
                    });


                    break;
                case get_oneuser:

                    request = new Request.Builder()
                            .url("https://api.github.com/users/"+login_str)
                            .build();

                    m_client = new OkHttpClient()
                            .newBuilder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .build();

                    call = m_client.newCall(request);


                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            //        JSONObject reader3 = null;
                            try {


                                JSONObject reader = null;
                                reader = new JSONObject(response.body().string());


                                    m_oneUser.setlocation(reader.getString("location"));
                                    m_oneUser.setname(reader.getString("name"));
                                    m_oneUser.setcompany(reader.getString("company"));
                                    m_oneUser.setsite_admin(reader.getString("site_admin"));
                                    m_oneUser.setblog(reader.getString("blog"));
                                    m_oneUser.setavatar_url(reader.getString("avatar_url"));



                                m_User_infoActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        m_User_infoActivity.updateui();
                                    }
                                });


                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();

                        }
                    });

                    break;
            }
        }
    }
    public void touch_trigger(int value_flag) {
        ｍ_queue.add(new int[]{value_flag});
        synchronized (this) {
            notify();
        }
    }

}