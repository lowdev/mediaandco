package eu.entropy.mediapedia.company;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

public class CompanySpecification {

    public static Builder builder(){
        return new Builder();
    }

    private String country;
    private MediaType mediaType;
    private String query;

    private CompanySpecification(Builder builder) {
        this.country = builder.country;
        this.mediaType = builder.mediaType;
        this.query = builder.query;
    }

    public String getCountry() {
        return country;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public String getQuery() {
        return query;
    }

    public String getClause() {
        List<String> conditions = new ArrayList<>();
        if (null != country) {
            conditions.add(String.format("country='%s'", country));
        }
        if (!MediaType.NONE.equals(mediaType)) {
            conditions.add(String.format("mediaType='%s'", mediaType.getFolderName()));
        }
        if (null != query) {
            conditions.add(String.format("contains='%s'", query));
        }

        return Joiner.on(" and ").join(conditions);
    }

    public static class Builder {
        private String country;
        private MediaType mediaType = MediaType.NONE;
        private String query;

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder mediaType(MediaType mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public Builder query(String query) {
            this.query = query;
            return this;
        }

        public CompanySpecification build() {
            if (country.isEmpty() || mediaType.equals(MediaType.NONE)) {
                throw new RuntimeException("Can't build the specification.");
            }

            return new CompanySpecification(this);
        }
    }
}
