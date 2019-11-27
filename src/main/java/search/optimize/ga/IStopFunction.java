package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;
import search.optimize.ga.bean.Population;

/**
 * 中止条件设定函数
 * 根据迭代次数进行中止
 * 可以根据种群情况进行中止 （根据种群的整体情况）
 * 可以根据正在迭代的基因进行中止（当适应度计算完成后）
 */
public interface IStopFunction {
  /**
   * 判断Ga是否可以中止
   * @param generation 当前迭代次数，默认值为0
   * @param population 当前种群情况
   * @param chromosome 当前基因评价完成后的情况
   * @throws StopException 当判断可以结束时抛出结束异常，会由上层进行捕获处理
   */
  void isStop(int generation,Population population, Chromosome chromosome) throws StopException;
}
