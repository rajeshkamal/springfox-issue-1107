package com.acme.common.search;

import org.springframework.data.jpa.domain.Specification;

import com.acme.common.rest.model.BaseEntity;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;

/**
 * A Custom RSQLVisitor.
 *
 * @param <T>
 *            Return type of the visitor method
 */
public class CustomRSQLVisitor<T extends BaseEntity> implements RSQLVisitor<Specification<T>, Void> {

    /**
     * The builder used for the visitor.
     */
    private RSQLSpecBuilder<T> builder;

    /**
     * The constructor for the Custom RSQLVisitor.
     */
    public CustomRSQLVisitor() {
        builder = new RSQLSpecBuilder<T>();
    }

    public final Specification<T> visit(final AndNode node, final Void param) {
        return builder.createSpecification(node);
    }

    public final Specification<T> visit(final OrNode node, final Void param) {
        return builder.createSpecification(node);
    }

    public final Specification<T> visit(final ComparisonNode node, final Void params) {
        return builder.createSpecification(node);
    }
}
