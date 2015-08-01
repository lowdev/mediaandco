package eu.entropy.mediapedia.networkgraph.visjs;

public class Node {
    private int id;
    private String label;

    private Node(Builder builder) {
        id = builder.id;
        label = builder.label;
    }

    public Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String label;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withLabel(String label) {
            this.label = label;
            return this;
        }

        public Node build() {
            return new Node(this);
        }
    };
}
