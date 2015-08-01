package eu.entropy.mediapedia.networkgraph.visjs;

public class Edge {

    public static Builder builder() {
        return new Builder();
    }

    private String from;
    private String to;
    private ArrowDirection arrows;

    private Edge(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.arrows = builder.arrowDirection;
    }

    public static class Builder {
        private String from;
        private String to;
        private ArrowDirection arrowDirection;

        private Builder(){}

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder arrowDeparture(ArrowDirection arrowDirection) {
            this.arrowDirection = arrowDirection;
            return this;
        }

        public Edge build() {
            return new Edge(this);
        }
    }
}
