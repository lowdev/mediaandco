package eu.entropy.mediapedia.networkgraph.visjs;

import java.util.List;

public class VisjsModel {
    private List<Edge> edges;
    private List<Node> nodes;

    public VisjsModel(List<Edge> edges, List<Node> nodes) {
        this.edges = edges;
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
