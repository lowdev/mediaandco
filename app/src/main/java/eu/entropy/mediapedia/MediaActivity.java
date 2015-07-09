package eu.entropy.mediapedia;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MediaActivity extends AppCompatActivity {

    public static MediaActivity createMediaActivity() {
        return new MediaActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        String mediaFileName = getIntent().getLongExtra("mediaId", 0) + ".json";
        InputStream in = createInputStream(mediaFileName);

        Media media = new GsonBuilder().create().fromJson(createBufferedReader(in), Media.class);

        final TextView mediaTextView = (TextView) findViewById(R.id.mediaTextView);
        mediaTextView.setText(media.getName());
    }

    private InputStream createInputStream(String mediaFileName) {
        try {
            return getAssets().open(mediaFileName);
        } catch (IOException e) {
            Log.e("file", mediaFileName + " file not found", e);
        }

        return new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
    }

    private static BufferedReader createBufferedReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }
}
