package com.example.github_ifno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyAdapter m_Adapter;
    private RecyclerView m_users_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.Title_name));
    }
    @Override
    protected void onStart() {
        super.onStart();

        m_users_list = (RecyclerView)findViewById(R.id.users_list);
        m_users_list.setHasFixedSize(true);

        final GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 1);
        gridLayoutManager2.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        m_users_list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        m_users_list.setLayoutManager(gridLayoutManager2);


        DividerItemDecoration m_divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        m_divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.custom_divider));
        m_users_list.addItemDecoration(m_divider);


        ArrayList<String> kkk= new ArrayList<String>();
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");
        kkk.add("jjj");


        m_Adapter = new MyAdapter(MainActivity.this,MainActivity.this,kkk);
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




    }
}
