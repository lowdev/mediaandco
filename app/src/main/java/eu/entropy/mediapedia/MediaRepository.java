package eu.entropy.mediapedia;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Lowrey on 7/2/2015.
 */
public class MediaRepository {
    private static final List<Media> TELEVISION_FRANCE = Arrays.asList(
            new Media(R.drawable.tv_france_tf1_2013),
            new Media(R.drawable.tv_france_france2_2013));

    public List<Media> getTelevisionFrance() {
        return TELEVISION_FRANCE;
    }
}
