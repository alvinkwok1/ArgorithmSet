package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;
import search.optimize.ga.selection.ISelectOperator;

public class ChromosomeSelector implements ISelectOperator {
  public Chromosome[][] select(Chromosome[] chromosomes) {
    // 创建交配池
    Chromosome[][] mute = new Chromosome[chromosomes.length/2][2];
    // 按顺序形成交配池
    for (int i=0;i<chromosomes.length/2;i++){
      mute[i][0]=chromosomes[i*2];
      mute[i][1]=chromosomes[i*2+1];
    }
    return mute;
  }
}
