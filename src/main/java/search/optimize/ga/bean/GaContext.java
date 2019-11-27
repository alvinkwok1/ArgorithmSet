package search.optimize.ga.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import search.optimize.ga.*;
import search.optimize.ga.code.IChromosomeDecoder;
import search.optimize.ga.code.IChromosomeEncoder;
import search.optimize.ga.selection.ISelectOperator;

/**
 * 求解过程中的GA上下文结构
 */
@Data
@Accessors(chain = true)
public class GaContext {

  /**
   * 用于编码的初始化数据
   */
  private Object initData;
  /**
   * 适应度评价函数
   */
  private IFitnessFunction fitnessFunction;
  /**
   * 中止函数
   */
  private IStopFunction stopFunction;
  /**
   * 初始化种群所需的编码器
   */
  private IChromosomeEncoder encoder;
  /**
   * 个体的解码器
   */
  private IChromosomeDecoder decoder;
  /**
   * GA代数
   */
  private int generation =0 ;

  // 种群大小
  private int populationSize = 10;
  /**
   * 当前代的种群信息
   */
  private Population population;

  /**
   * 选择算子
   */
  private ISelectOperator selectOperator;

  /**
   * 交叉概率
   */
  private double crossRate = 0.6D;
  /**
   * 交叉算子
   */
  private ICrossOperator crossOperator;

  /**
   * 突变概率
   */
  private double mutationRate = 0.1D;

  /**
   * 变异算子
   */
  private IMutationOperator mutationOperator;
  /**
   * -----------------------------------------------
   * ---------------精英保留策略
   * ----------------------------------------------
   */

  /**
   * 选择N个精英进入子代，0<=N<Npop(种群大小)
   */
  private int elitistSelectNum = 0;

  /**
   * 当前精英存放个数
   */
  private int currentElitistSize = 0;

  /**
   * 外部精英空间，需要通过初始化函数处理
   */
  private Chromosome[] elitistSpace;

  /**
   * 精英保留策略
   * 默认选择第二种策略
   */
  private int elitistStrategy = ELITIST_PRESERVATION_2;

  /**
   * 精英保留策略1
   * 将第t代种群与外部精英空间合并形成中间代Pt`，然后从Pt`中选择N个直接进入第t+1代。
   */
  public static final int ELITIST_PRESERVATION_1 = 1;
  /**
   * 精英保留策略2
   * 在t+1代中保留N个精英的位置，从t代形成交配池，选择Npop-N个进入t+1代，从外部精英空间选择N个直接进入t+1代，
   * 不经过交叉和
   */
  public static final int ELITIST_PRESERVATION_2 = 2;
}
