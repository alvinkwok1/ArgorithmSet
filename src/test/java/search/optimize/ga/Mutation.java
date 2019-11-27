package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;

public class Mutation implements IMutationOperator {
  public Chromosome mutation(Chromosome chromosome) {
    int point = (int) (Math.random()*5);
    char[] encodeSpace = (char[]) chromosome.getEncodeSpace();
    encodeSpace[point]= (char) (Math.random()*26+'a');
    return chromosome;
  }
}
