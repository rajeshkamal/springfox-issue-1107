package com.acme.common.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specifications;

import com.acme.common.rest.model.BaseEntity;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.LogicalOperator;
import cz.jirutka.rsql.parser.ast.Node;

/**
 * Utility class to build a RSQLSpec.
 *
 * @param <T> The type to work on
 */
public class RSQLSpecBuilder<T extends BaseEntity> {

    /**
     * Create a spec given a {@link Node)}.
     *
     * @param node The input {@link Node}
     * @return The specification for the {@link Node}
     */
    public final Specifications<T> createSpecification(final Node node) {
        if (node instanceof LogicalNode) {
            return createSpecification((LogicalNode) node);
        }
        if (node instanceof ComparisonNode) {
            return createSpecification((ComparisonNode) node);
        }
        return null;
    }

    /**
     * Create the spec for a {@link LogicalNode}.
     *
     * @param logicalNode The {@link LogicalNode} to create the spec for
     * @return The {@link Specifications}
     */
    private Specifications<T> createSpecification(final LogicalNode logicalNode) {
        List<Specifications<T>> specs = new ArrayList<Specifications<T>>();
        Specifications<T> temp;
        for (Node node : logicalNode.getChildren()) {
            temp = createSpecification(node);
            if (temp != null) {
                specs.add(temp);
            }
        }

        Specifications<T> result = specs.get(0);
        if (logicalNode.getOperator() == LogicalOperator.AND) {
            for (int i = 1; i < specs.size(); i++) {
                result = Specifications.where(result).and(specs.get(i));
            }
        } else if (logicalNode.getOperator() == LogicalOperator.OR) {
            for (int i = 1; i < specs.size(); i++) {
                result = Specifications.where(result).or(specs.get(i));
            }
        }

        return result;
    }

    /**
     * Create the spec for a {@link ComparisonNode}.
     *
     * @param comparisonNode The {@link ComparisonNode} to create the spec for
     * @return The {@link Specifications}
     */
    private Specifications<T> createSpecification(final ComparisonNode comparisonNode) {
        Specifications<T> result = Specifications.where(new RSQLSpecification<T>(comparisonNode.getSelector(),
                        comparisonNode.getOperator(),
                        comparisonNode.getArguments()));
        return result;
    }
}
