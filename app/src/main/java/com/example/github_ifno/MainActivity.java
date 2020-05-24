package com.example.github_ifno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyAdapter m_Adapter;
    private RecyclerView m_users_list;
    private cmd_routine m_cmd_routine;
    private ArrayList<Users> m_Users = new ArrayList<Users>();
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.Title_name));
        m_cmd_routine = new cmd_routine(MainActivity.this, m_Users);
    }

    @Override
    protected void onStart() {
        super.onStart();

        m_users_list = (RecyclerView) findViewById(R.id.users_list);
        m_users_list.setHasFixedSize(true);

        final GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 1);
        gridLayoutManager2.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        m_users_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        m_users_list.setLayoutManager(gridLayoutManager2);


        DividerItemDecoration m_divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        m_divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        m_users_list.addItemDecoration(m_divider);


        m_Adapter = new MyAdapter(MainActivity.this, MainActivity.this, m_Users);
        m_users_list.setAdapter(m_Adapter);


        m_users_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(-1)) {
                } else if (!recyclerView.canScrollVertically(1)) {
                } else if (dy < 0) {
                    // ((MemberActivity) getActivity()).guide_tool.setVisibility(View.VISIBLE);
                } else if (dy > 0) {
                    // ((MemberActivity) getActivity()).guide_tool.setVisibility(View.GONE);
                }
            }
        });


        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_EXTERNAL_STORAGE
            );
        } else {

            m_cmd_routine.touch_trigger(cmd_routine.get_users);
        }

    }

    public void change_ui() {
        m_Adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    m_cmd_routine.touch_trigger(cmd_routine.get_users);
                }
        }
    }
}
