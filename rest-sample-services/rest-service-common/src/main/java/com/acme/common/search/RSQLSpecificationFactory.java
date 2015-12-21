package com.acme.common.search;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.acme.common.rest.model.BaseEntity;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

/**
 * Factory class for {@link RSQLSpecification}.
 * @param <T> The entity to work on
 */
@Component
public class RSQLSpecificationFactory<T extends BaseEntity> {

    /**
     * Create a {@link Specification} given a search string.
     * @param search The string to search
     * @return The {@link Specification}
     */
    public final Specification<T> createRSQLSpecification(final String search) {
        if (search != null) {
            Node rootNode = new RSQLParser().parse(search);
            return rootNode.accept(new CustomRSQLVisitor<T>());
        } else {
            return null;
        }
    }
}
