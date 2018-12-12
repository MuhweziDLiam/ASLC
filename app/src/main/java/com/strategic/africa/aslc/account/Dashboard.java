package com.strategic.africa.aslc.account;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.strategic.africa.aslc.R;
import com.strategic.africa.aslc.adapter.ItemsAdapter;
import com.strategic.africa.aslc.model.Item;
import com.strategic.africa.aslc.util.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    private List<Item> ItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ItemsAdapter(ItemList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep Item_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView
        // keep Item_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Item Item = ItemList.get(position);
                Toast.makeText(getApplicationContext(), Item.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareItemData();
    }

    /**
     * Prepares sample data to provide data set to adapter
     */
    private void prepareItemData() {


        Item Item = new Item("Speakers", "", "");
        ItemList.add(Item);

        Item = new Item("Activity Feed", "", "");
        ItemList.add(Item);

        Item = new Item("Programme", "", "");
        ItemList.add(Item);

        Item = new Item("Events", "", "");
        ItemList.add(Item);

        Item = new Item("Sponsors and Partners", "", "");
        ItemList.add(Item);

        Item = new Item("Accommodation", "", "");
        ItemList.add(Item);

        Item = new Item("Information hub", "", "");
        ItemList.add(Item);

        Item = new Item("My Programme", "", "");
        ItemList.add(Item);

        Item = new Item("Quick notes", "", "");
        ItemList.add(Item);



        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged();
    }

}
