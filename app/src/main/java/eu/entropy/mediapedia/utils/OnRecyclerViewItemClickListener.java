package eu.entropy.mediapedia.utils;

import android.view.View;

/**
 * Created by Lowrey on 7/9/2015.
 */
public interface OnRecyclerViewItemClickListener<Model> {
    public void onItemClick(View view, Model model);
}