package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;
import search.optimize.ga.bean.GaContext;

/**
 * 适应值函数接口
 */
public interface IFitnessFunction {

  /**
   * 对一个个体进行评价
   * @param context ga上下文
   * @param chromosome 待评价个体
   * @throws StopException 如果评价结束，满足中止条件，可以抛出中止异常
   */
  default void  evaluateChromosome(GaContext context, Chromosome chromosome){
    evaluate(context,chromosome);
    // 进行中止条件判定
    context.getStopFunction().isStop(context.getGeneration(),context.getPopulation(),chromosome);
  }

  /**
   * 对一个个体进行评价
   * @param context ga上下文
   * @param chromosome 待评价个体
   */
  void  evaluate(GaContext context,Chromosome chromosome);
}
