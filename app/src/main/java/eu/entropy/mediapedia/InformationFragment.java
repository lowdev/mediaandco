package eu.entropy.mediapedia;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class InformationFragment extends Fragment {

    public static InformationFragment newInstance() {
        InformationFragment fragment = new InformationFragment();

        return fragment;
    }

    ImageView imageView;

    TextView textVibrant;
    TextView textVibrantLight;
    TextView textVibrantDark;
    TextView textMuted;
    TextView textMutedLight;
    TextView textMutedDark;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View companiesListView = inflater.inflate(R.layout.fragment_information_view, container, false);
        imageView = (ImageView) companiesListView.findViewById(R.id.image);
        textVibrant = (TextView) companiesListView.findViewById(R.id.textVibrant);
        textVibrantLight = (TextView) companiesListView.findViewById(R.id.textVibrantLight);
        textVibrantDark = (TextView) companiesListView.findViewById(R.id.textVibrantDark);
        textMuted = (TextView) companiesListView.findViewById(R.id.textMuted);
        textMutedLight = (TextView) companiesListView.findViewById(R.id.textMutedLight);
        textMutedDark = (TextView) companiesListView.findViewById(R.id.textMutedDark);

        Picasso.with(getActivity()).load(R.drawable.tv_tf1).fit().centerCrop().into(imageView,
                new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                doPalette(palette);
                            }
                        });
                    }

                    @Override
                    public void onError() {
                    }
                });


        return companiesListView;
    }

    public void doPalette(Palette palette) {
        {
            Palette.Swatch vibrant = palette.getVibrantSwatch();
            if (vibrant != null) {
                textVibrant.setBackgroundColor(vibrant.getRgb());
                textVibrant.setTextColor(vibrant.getBodyTextColor());
             }
        }
        {
            Palette.Swatch vibrantDark = palette.getDarkVibrantSwatch();
            if (vibrantDark != null) {
                textVibrantDark.setBackgroundColor(vibrantDark.getRgb());
                textVibrantDark.setTextColor(vibrantDark.getBodyTextColor());
            }
        }
        {
            Palette.Swatch vibrantLight = palette.getLightVibrantSwatch();
            if (vibrantLight != null) {
                textVibrantLight.setBackgroundColor(vibrantLight.getRgb());
                textVibrantLight.setTextColor(vibrantLight.getBodyTextColor());
            }
        }
        {
            Palette.Swatch muted = palette.getMutedSwatch();
            if (muted != null) {
                textMuted.setBackgroundColor(muted.getRgb());
                textMuted.setTextColor(muted.getBodyTextColor());
            }
        }
        {
            Palette.Swatch mutedDark = palette.getDarkMutedSwatch();
            if (mutedDark != null) {
                textMutedDark.setBackgroundColor(mutedDark.getRgb());
                textMutedDark.setTextColor(mutedDark.getBodyTextColor());
            }
        }
        {
            Palette.Swatch lightMuted = palette.getLightMutedSwatch();
            if (lightMuted != null) {
                textMutedLight.setBackgroundColor(lightMuted.getRgb());
                textMutedLight.setTextColor(lightMuted.getBodyTextColor());
            }
        }
    }
}
