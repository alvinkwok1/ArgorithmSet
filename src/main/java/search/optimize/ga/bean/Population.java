package search.optimize.ga.bean;

import lombok.Data;

/**
 * 种群
 */
@Data
public class Population {
  /**
   * 整个种群的适应度
   */
  private double populationFitness;
  /**
   * 种群中的基因
   */
  private Chromosome[] chromosomes;

  public Population(int size){
    chromosomes = new Chromosome[size];
  }

  /**
   * 将个体放入种群的第i个位置
   * @param i 位置
   * @param chromosome 待放入的个体
   */
  public void add(int i,Chromosome chromosome){
    chromosomes[i]=chromosome;
  }

  /**
   * 取出第i个个体
   * @param i 位置
   * @return 第i个个体
   */
  public Chromosome get(int i){
    return chromosomes[i];
  }
}
