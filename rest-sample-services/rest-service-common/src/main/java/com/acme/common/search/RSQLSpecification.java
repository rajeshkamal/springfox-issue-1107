package com.acme.common.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.acme.common.rest.model.BaseEntity;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

/**
 * The RSQLSpecification.
 *
 * @param <T> The type to work on
 */
public class RSQLSpecification<T extends BaseEntity> implements
        Specification<T> {

    /**
     * The property.
     */
    private String property;

    /**
     * The {@link cz.jirutka.rsql.parser.ast.ComparisonOperator}.
     */
    private ComparisonOperator operator;

    /**
     * List of arguments.
     */
    private List<String> arguments;

    /**
     * The constructor.
     *
     * @param property  The property
     * @param operator  The {@link ComparisonOperator}
     * @param arguments The arguments
     */
    public RSQLSpecification(final String property, final ComparisonOperator operator, final List<String> arguments) {
        super();
        this.property = property;
        this.operator = operator;
        this.arguments = arguments;
    }

    /**
     * Convert the {@link CriteriaQuery} to predicate.
     *
     * @param root    The {@link Root}
     * @param query   The {@link CriteriaQuery}
     * @param builder The {@link CriteriaBuilder}
     * @return The {@link Predicate} formed
     */
    public final Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query,
                                       final CriteriaBuilder builder) {

        List<Object> args = castArguments(root);
        Object argument = args.get(0);
        switch (RSQLComparisonOperator.fromValue(operator)) {
            case EQUAL:
                if (argument instanceof String) {
                    return builder.like(root.<String>get(property),
                            argument.toString().replace('*', '%'));
                } else if (argument == null) {
                    return builder.isNull(root.get(property));
                } else {
                    return builder.equal(root.get(property), argument);
                }
            case NOT_EQUAL:
                if (argument instanceof String) {
                    return builder.notLike(root.<String>get(property),
                            argument.toString().replace('*', '%'));
                } else if (argument == null) {
                    return builder.isNotNull(root.get(property));
                } else {
                    return builder.notEqual(root.get(property), argument);
                }
            case GREATER_THAN:
                return builder.greaterThan(root.<String>get(property),
                        argument.toString());
            case GREATER_THAN_OR_EQUAL:
                return builder.greaterThanOrEqualTo(root.<String>get(
                        property), argument.toString());
            case LESS_THAN:
                return builder.lessThan(root.<String>get(property), argument
                        .toString());
            case LESS_THAN_OR_EQUAL:
                return builder.lessThanOrEqualTo(root.<String>get(property),
                        argument.toString());
            case IN:
                return root.get(property).in(args);
            case NOT_IN:
                return builder.not(root.get(property).in(args));
            default:
                return null;
        }
    }

    /**
     * Cast arguments for a given {@link Root}.
     * @param root The {@link Root} whose arguments has to be casted
     * @return The List of casted arguments
     */
    private List<Object> castArguments(final Root<T> root) {
        List<Object> args = new ArrayList<Object>();
        Class<? extends Object> type = root.get(property).getJavaType();

        for (String argument : arguments) {
            if (type.equals(Integer.class)) {
                args.add(Integer.parseInt(argument));
            } else if (type.equals(Long.class)) {
                args.add(Long.parseLong(argument));
            } else {
                args.add(argument);
            }
        }

        return args;
    }
}
