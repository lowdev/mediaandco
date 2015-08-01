package eu.entropy.mediapedia.networkgraph.visjs;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.CompanyRepository;
import eu.entropy.mediapedia.company.StakeholderRepository;

public class VisjsModelConverter {

    private CompanyRepository companyRepository;

    public VisjsModelConverter() {
        companyRepository = new CompanyRepository();
    }

    public VisjsModel toVisjsModel(Company company) {
        List<Company> assets = companyRepository.findByIds(company.getAssets().keySet());
        List<Company> owners = companyRepository.findByIds(company.getOwners().keySet());

        List<Node> nodes = FluentIterable
                .from(Iterables.concat(assets, owners, Arrays.asList(company)))
                .transform(new ToNode())
                .toList();

        List<Edge> assetsEdges = FluentIterable
                .from(assets)
                .transform(new ToEdge(company.getId(), ArrowDirection.from))
                .toList();
        List<Edge> ownersEdges = FluentIterable
                .from(owners)
                .transform(new ToEdge(company.getId(), ArrowDirection.to))
                .toList();

        List<Edge> edges = Lists.newArrayList(Iterables.concat(assetsEdges, ownersEdges));
        return new VisjsModel(edges, nodes);
    }

    private Node toNode(Company company) {
        return Node.builder()
                .withId(company.getId())
                .withLabel(company.getName())
                .build();
    }

    private class ToNode implements Function<Company, Node> {
        @Override
        public Node apply(Company company) {
            return toNode(company);
        }
    }

    private class ToEdge implements Function<Company, Edge> {

        private String companyId;

        private ArrowDirection arrowDirection;

        public ToEdge(String companyId, ArrowDirection arrowDirection) {
            this.companyId = companyId;
            this.arrowDirection = arrowDirection;
        }

        @Override
        public Edge apply(Company company) {
            return Edge.builder()
                    .from(companyId)
                    .to(company.getId())
                    .arrowDeparture(arrowDirection)
                    .build();
        }
    }
}
