package com.namphan.androidduan1.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.namphan.androidduan1.R;
import com.namphan.androidduan1.adapter.MapAdapter;
import com.namphan.androidduan1.database.MapDao;
import com.namphan.androidduan1.model.Maps;

import java.util.ArrayList;
import java.util.List;

public class ListMapsACT extends AppCompatActivity {
    public static List<Maps> dsMaps = new ArrayList<>();
    ListView lvMaps;
    MapAdapter adapter = null;
    MapDao mapDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_maps_act);
        lvMaps =  findViewById(R.id.lvListMap);
        mapDao = new MapDao(this);
        dsMaps = mapDao.getAllMaps();
        adapter = new MapAdapter(ListMapsACT.this,dsMaps);
        lvMaps.setAdapter(adapter);




    }
    @Override
    protected void onResume() {
        super.onResume();
        dsMaps.clear();
        dsMaps = mapDao.getAllMaps();
        adapter.changeDataset( mapDao.getAllMaps());
    }


}
