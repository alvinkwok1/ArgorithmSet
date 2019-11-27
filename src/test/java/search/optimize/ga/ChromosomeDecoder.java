package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;
import search.optimize.ga.code.IChromosomeDecoder;

public class ChromosomeDecoder implements IChromosomeDecoder {

  private static final String targetStr ="hello";
  public void decode(Chromosome chromosome) {
    // 计算对应值出现再正确位置的个数
    int num = 0;
    char[] encodeSpace = (char[]) chromosome.getEncodeSpace();
    for (int i=0;i<targetStr.length();i++) {
      if(targetStr.charAt(i) ==encodeSpace[i]) {
        num ++;
      }
    }
//    System.out.println(new String(encodeSpace));
    chromosome.setDecodeResult(num);
  }
}
