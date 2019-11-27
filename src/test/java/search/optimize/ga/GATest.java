package search.optimize.ga;


import org.junit.Test;
import search.optimize.ga.bean.GaContext;

public class GATest {
  @Test
  public void solve() {
    GaContext gaContext =
      new GaContext().setPopulationSize(6)
        .setElitistSelectNum(2)
      .setEncoder(new ChromosomeEncoder())
      .setDecoder(new ChromosomeDecoder())
      .setStopFunction(new StopFunction())
      .setFitnessFunction(new FitnessFunction())
      .setSelectOperator(new ChromosomeSelector())
      .setCrossOperator(new CrossOperator())
        .setElitistStrategy(GaContext.ELITIST_PRESERVATION_2)
      .setMutationOperator(new Mutation());
    GA<char[],Integer> ga = new GA<char[], Integer>();
    ga.solve(gaContext);
  }
}