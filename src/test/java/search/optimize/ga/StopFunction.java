package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;
import search.optimize.ga.bean.Population;

public class StopFunction implements IStopFunction {
  public void isStop(int generation, Population population, Chromosome chromosome) throws StopException {
    if (chromosome !=null && chromosome.getFitness() ==1) {
      throw new StopException(chromosome);
    }
  }
}
