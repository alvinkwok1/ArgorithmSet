package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;
import search.optimize.ga.bean.GaContext;
import search.optimize.ga.code.IChromosomeEncoder;

public class ChromosomeEncoder implements IChromosomeEncoder {
  public void encode(GaContext context, Chromosome chromosome) {
    char[] encodeSpace = new char[5];
    // 随机生成5个字符
    for (int i=0;i<5;i++) {
        encodeSpace[i] = (char) (Math.random()*26 + 'a');
    }
    chromosome.setEncodeSpace(encodeSpace);
  }
}
