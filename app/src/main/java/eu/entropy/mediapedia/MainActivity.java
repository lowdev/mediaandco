package eu.entropy.mediapedia;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final List<MediaItem> TELEVISION_FRANCE = Arrays.asList(
            new MediaItem(1, R.drawable.tv_tf1), new MediaItem(2, R.drawable.tv_france2));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView mediaGridView = (GridView) findViewById(R.id.mediaGridView);
        mediaGridView.setAdapter(new MediaLogoAdapter(this, TELEVISION_FRANCE));
        mediaGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MediaActivity.class);
                intent.putExtra("mediaId", id);
                startActivity(intent);
            }
        });
    }
}
