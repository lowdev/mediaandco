package eu.entropy.mediapedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final List<MediaItem> TELEVISION_FRANCE = Arrays.asList(
            new MediaItem(1, R.drawable.tv_tf1), new MediaItem(2, R.drawable.tv_france2));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        MediaLogoAdapter mediaLogoAdapter = new MediaLogoAdapter(TELEVISION_FRANCE);
        mediaLogoAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<MediaItem>() {
            public void onItemClick(View view, MediaItem mediaItem) {
                Intent intent = new Intent(getApplicationContext(), MediaActivity.class);
                intent.putExtra("mediaId", mediaItem.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mediaLogoAdapter);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        // Show menu icon
        //final ActionBar ab = getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);
    }
}
