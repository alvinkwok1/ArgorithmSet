package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;

/**
 * 当Ga需要结束时抛出结束异常
 */
public class StopException extends RuntimeException {

  private Chromosome chromosome;

  /**
   * 当结束时必须返回最优个体
   */
  public StopException(Chromosome chromosome) {
    super("ga stop");
    this.chromosome = chromosome;
  }

  /**
   * 获取中止条件下的最优个体
   * @return
   */
  public Chromosome getChromosome(){
    return chromosome;
  }
}
