package eu.entropy.mediapedia;

/**
 * Created by Lowrey on 7/2/2015.
 */
public class MediaItem {

    private int id;

    private int drawableId;

    public MediaItem(int id, int drawableId) {
        this.id = id;
        this.drawableId = drawableId;
    }

    public int getId() {
        return id;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
