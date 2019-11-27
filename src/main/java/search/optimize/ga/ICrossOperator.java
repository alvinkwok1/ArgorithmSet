package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;

/**
 * 交叉算子
 */
public interface ICrossOperator {
  /**
   * 将个体a与个体b进行交叉，并返回两个新的交叉后的个体
   * @param a 母代1
   * @param b 母代2
   * @return [子代1，子代2]
   */
  Chromosome[] cross(Chromosome a, Chromosome b);
}
