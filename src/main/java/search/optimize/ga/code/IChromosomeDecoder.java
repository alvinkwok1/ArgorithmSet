package search.optimize.ga.code;

import search.optimize.ga.bean.Chromosome;

/**
 * 基因解码接口
 */
public interface IChromosomeDecoder {
  void decode(Chromosome chromosome);
}
