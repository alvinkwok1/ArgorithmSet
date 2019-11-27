package search.optimize.ga.code;

import search.optimize.ga.bean.Chromosome;
import search.optimize.ga.bean.GaContext;

/**
 * 基因编码接口
 */
public interface IChromosomeEncoder {
  void encode(GaContext context,Chromosome chromosome);
}
