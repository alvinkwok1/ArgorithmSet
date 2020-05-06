package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;

import java.util.HashMap;

/**
 * 突变算子(变异算子)
 */
public interface IMutationOperator {
  Chromosome mutation(Chromosome chromosome);
}
