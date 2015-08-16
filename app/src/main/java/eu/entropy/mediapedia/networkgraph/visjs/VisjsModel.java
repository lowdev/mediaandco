package eu.entropy.mediapedia.networkgraph.visjs;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;

import java.util.Arrays;
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

    public void removeNodes(String[] nodesToExclude) {
        this.nodes = FluentIterable.from(this.nodes)
                .filter(new ByIds(Arrays.asList(nodesToExclude)))
                .toList();
    }

    private static class ByIds implements Predicate<Node> {
        private List<String> nodesToExclude;

        public ByIds(List<String> nodesToExclude) {
            this.nodesToExclude = nodesToExclude;
        }

        @Override
        public boolean apply(Node node) {
            return !nodesToExclude.contains(node.getId());
        }
    }
}
