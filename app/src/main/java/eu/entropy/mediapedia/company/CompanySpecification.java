package eu.entropy.mediapedia.company;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;

import java.util.ArrayList;
import java.util.List;

public class CompanySpecification {

    public static Builder builder(){
        return new Builder();
    }

    private String country;
    private MediaType mediaType;
    private String query;
    private List<String> ids;

    private CompanySpecification(Builder builder) {
        this.country = builder.country;
        this.mediaType = builder.mediaType;
        this.query = builder.query;
        this.ids = builder.ids;
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
            conditions.add(String.format("name='%s*' or name='*%s'", query, query));
        }
        if (!ids.isEmpty()) {
            conditions.addAll(FluentIterable
                    .from(ids)
                    .transform(new ToCondition())
                    .toList());
        }

        return Joiner.on(" and ").join(conditions);
    }

    public static class Builder {
        private String country;
        private MediaType mediaType = MediaType.NONE;
        private String query;
        private List<String> ids = new ArrayList<>();

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

        public Builder ids(List<String> ids) {
            this.ids = ids;
            return this;
        }

        public CompanySpecification build() {
            return new CompanySpecification(this);
        }
    }

    private static class ToCondition implements Function<String, String> {
        @Override
        public String apply(String input) {
            return String.format("name='%s'", input);
        }
    }
}
