package search.optimize.ga.bean;

import lombok.Data;

/**
 * 基因编码
 */
@Data
public class Chromosome<T,D> {
  /**
   * 基因的适应度，初始化为0，经过评价后赋值
   */
  private double fitness =0;
  /**
   * 编码空间
   */
  private T encodeSpace;
  /**
   * 解码结果
   */
  private D decodeResult;
}
