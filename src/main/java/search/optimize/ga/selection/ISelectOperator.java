package search.optimize.ga.selection;

import search.optimize.ga.bean.Chromosome;

/**
 * 选择算子
 */
public interface ISelectOperator {
  Chromosome[][] select(Chromosome[] chromosomes);
}
