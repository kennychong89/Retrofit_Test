package com.nano.kenny.retrofit_test;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Itunes;
import model.Result;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {
    private ItuneService ituneService;
    private ItuneAPI ituneAPI;
    private CustomTrackAdapter customTrackAdapter;
    private final ArrayList<Result> resultEntries;
    private ListView listView;

    public MainActivity() {
        ituneAPI = new ItuneAPI();
        ituneService = ituneAPI.getItuneService();
        resultEntries = new ArrayList<Result>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> queryMap = new HashMap<String, String>();
        //?amgArtistId={amgArtistId}&entity=song&limit=10&sort=top
        queryMap.put("amgArtistId", "1055684");
        queryMap.put("entity", "song");
        queryMap.put("limit", "10");
        queryMap.put("sort", "top");

        customTrackAdapter = new CustomTrackAdapter(getApplicationContext(),
                R.layout.list_track_items,
                resultEntries
        );

        listView = (ListView)findViewById(R.id.listview_toptracks);
        listView.setAdapter(customTrackAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            }
        });

        ituneService.getArtistTopTenTracks(queryMap, new Callback<Itunes>() {
            @Override
            public void success(final Itunes itunes, Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<Result> results = itunes.getResults();
                        resultEntries.addAll(results);
                        customTrackAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e("retroerror", retrofitError.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
