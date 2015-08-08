package eu.entropy.mediapedia.networkgraph.visjs;

public class Node {
    private String id;
    private String label;
    private String group;
    private int level;

    private Node(Builder builder) {
        id = builder.id;
        label = builder.label;
        group = builder.group;
        level = builder.level;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String label;
        private String group;
        private int level;

        private Builder() {}

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withLabel(String label) {
            this.label = label;
            return this;
        }

        public Builder withGroup(String group) {
            this.group = group;
            return this;
        }

        public Builder withLevel(int level) {
            this.level = level;
            return this;
        }

        public Node build() {
            return new Node(this);
        }
    };
}
