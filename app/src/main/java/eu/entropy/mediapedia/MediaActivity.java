package eu.entropy.mediapedia;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.GsonBuilder;

import org.solovyev.android.views.llm.DividerItemDecoration;
import org.solovyev.android.views.llm.LinearLayoutManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class MediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        String mediaFileName = getIntent().getIntExtra("mediaId", 0) + ".json";
        InputStream in = createInputStream(mediaFileName);
        Media media = new GsonBuilder().create().fromJson(createBufferedReader(in), Media.class);

        setupToolbar(media.getName());
        setupRecyclerView(media.getOwners(), R.id.owners);
        setupRecyclerView(media.getAssets(), R.id.assets);
    }

    private void setupRecyclerView(List<String> mediaAttributes, int recyclerViewId) {
        RecyclerView recyclerView = (RecyclerView) findViewById(recyclerViewId);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView, LinearLayoutManager.VERTICAL, false);
        layoutManager.setOverScrollMode(ViewCompat.OVER_SCROLL_NEVER);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        MediaAttributeAdapter mediaAttributeAdapter = new MediaAttributeAdapter(mediaAttributes);
        recyclerView.setAdapter(mediaAttributeAdapter);
    }

    private InputStream createInputStream(String mediaFileName) {
        try {
            return getAssets().open(mediaFileName);
        } catch (IOException e) {
            Log.e("file", mediaFileName + " file not found", e);
        }

        try {
            return getAssets().open("0.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private static BufferedReader createBufferedReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }

    private void setupToolbar(String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(title);
    }
}
