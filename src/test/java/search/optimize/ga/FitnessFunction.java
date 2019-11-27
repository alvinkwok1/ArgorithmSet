package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;
import search.optimize.ga.bean.GaContext;

public class FitnessFunction implements IFitnessFunction {
  public void evaluate(GaContext context, Chromosome chromosome) {
    Integer num = (Integer) chromosome.getDecodeResult();
    chromosome.setFitness(num *1.0 /5);
  }
}
