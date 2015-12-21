package com.acme.common.search;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;

/**
 * Enum of RSQL Comparison Operators.
 */
public enum RSQLComparisonOperator {

   /**
    * == operator.
    */
   EQUAL(RSQLOperators.EQUAL),

   /**
    * != operator.
    */
   NOT_EQUAL(RSQLOperators.NOT_EQUAL),

   /**
    * =gt=, > operator.
    */
   GREATER_THAN(RSQLOperators.GREATER_THAN),

   /**
    * =ge=, >= operator.
    */
   GREATER_THAN_OR_EQUAL(RSQLOperators.GREATER_THAN_OR_EQUAL),

   /**
    * =lt=, < operator.
    */
   LESS_THAN(RSQLOperators.LESS_THAN),

   /**
    * =le=, <= operator.
    */
   LESS_THAN_OR_EQUAL(RSQLOperators.LESS_THAN_OR_EQUAL),

   /**
    * =in= operator.
    */
   IN(RSQLOperators.IN),

   /**
    * =out= operator.
    */
   NOT_IN(RSQLOperators.NOT_IN);

   /**
    * The comparison operator.
    */
   private ComparisonOperator operator;

   /**
    * The constructor that takes in the {@link ComparisonOperator} operator.
    *
    * @param operator The {@link ComparisonOperator} that is used for this
    *                 instance
    */
   RSQLComparisonOperator(final ComparisonOperator operator) {
      this.operator = operator;
   }

   /**
    * Return the {@link RSQLComparisonOperator} given a {@link
    * ComparisonOperator}.
    *
    * @param operator The input {@link ComparisonOperator}
    * @return The {@link RSQLComparisonOperator}
    */
   public static RSQLComparisonOperator fromValue(final ComparisonOperator operator) {
      for (RSQLComparisonOperator enumVal : RSQLComparisonOperator.values()) {
         if (enumVal.value() == operator) {
            return enumVal;
         }
      }
      throw new IllegalArgumentException(operator.toString());
   }

   /**
    * Return the current value.
    *
    * @return The current value
    */
   public ComparisonOperator value() {
      return this.operator;
   }

}
