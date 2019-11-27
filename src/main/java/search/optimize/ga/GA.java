package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;
import search.optimize.ga.bean.GaContext;
import search.optimize.ga.bean.Population;
import search.optimize.ga.code.IChromosomeEncoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * 计算流程
 */
public class GA<T, D> {

  private  static  Logger logger = Logger.getLogger(GA.class.getName());
  /**
   * 当Ga结束时选择最优的
   *
   * @param gaContext
   * @return
   */
  public D solve(GaContext gaContext) {
    long start,end;
    start = System.currentTimeMillis();
    // 检查Ga参数
    checkContext(gaContext);

    print(gaContext);
    try {
      int populationSize = gaContext.getPopulationSize();

      // 种群初始化
      Population population = initPopulation(gaContext);

      while (true) {
        // 进行解码
        for (int i = 0; i < populationSize; i++) {
          // 对第i个个体进行解码
          gaContext.getDecoder().decode(population.get(i));
        }
        // 进行适应度评价
        for (int i = 0; i < populationSize; i++) {
          // 对第i个个体进行评价，可能会抛出中止异常
          gaContext.getFitnessFunction()
            .evaluateChromosome(gaContext, population.get(i));
        }

        gaContext.getStopFunction().isStop(gaContext.getGeneration(), population, null);

        /**
         * ---------------------
         * 进行第t+1代的处理
         * ---------------------
         */

        // 根据适应度对个体进行适应度降序排序。
        Arrays.sort(population.getChromosomes(), new Comparator<Chromosome>() {
          public int compare(Chromosome a, Chromosome b) {
            if(a.getFitness()<b.getFitness()) {
              return 1;
            } else if(a.getFitness()==b.getFitness()) {
              return 0;
            } else {
              return -1;
            }
          }
        });

        // 精英代保留策略
        if (gaContext.getElitistStrategy() == GaContext.ELITIST_PRESERVATION_1) {
          // 从母代和精英个体空间的集合中取出最优的Npop个个体放入下一代精英空间中
          Chromosome[] mate = arrayMerge(populationSize, // 目标精英空间大小
            population.getChromosomes(), // 种群
            populationSize, // 种群大小
            gaContext.getElitistSpace(), // 上一代精英个体空间
            gaContext.getCurrentElitistSize()); // 上一代精英个体大小，初始为0

          gaContext.setElitistSpace(mate);
          gaContext.setCurrentElitistSize(populationSize);
          // 应用选择算子，形成匹配的双亲列表
          Chromosome[][] matchList = gaContext.getSelectOperator().select(mate);
          // 应用交叉算子和变异算子
          for (int i = 0; i < matchList.length; i++) {
            Chromosome[] newChromosome = applyCrossAndMutation(gaContext, matchList[i]);
            // 将个体加入到新种群
            population.add(i * 2, newChromosome[0]);
            population.add(i * 2 + 1, newChromosome[1]);
          }
        } else if (gaContext.getElitistStrategy() == GaContext.ELITIST_PRESERVATION_2) {
          // 执行精英保留策略二，直接复制
          // 对母代个体应用选择算子，形成交配池
          Chromosome[][] mutePool = gaContext.getSelectOperator().select(population.getChromosomes());
          // 从母代中取出N个作为精英个体
          Chromosome[] elitistList = Arrays.copyOf(population.getChromosomes(), gaContext.getElitistSelectNum());
          // 从中取出Npop - N个个体作为子代
          for (int i = 0; i <= (populationSize - gaContext.getElitistSelectNum()) / 2; i++) {
            Chromosome[] newChromosome = applyCrossAndMutation(gaContext, mutePool[i]);
            // 将个体加入到新种群
            population.add(i * 2, newChromosome[0]);
            population.add(i * 2 + 1, newChromosome[1]);
          }
          // 选取当前种群中最优的N个个体直接复制进入下一代
          int elitistPos = 0;
          for (int i = populationSize - gaContext.getElitistSelectNum(); i < populationSize; i++) {
            population.add(i, elitistList[elitistPos++]);
          }
        }
        gaContext.setGeneration(gaContext.getGeneration() + 1);
      }
    } catch (StopException stop) {
      end =System.currentTimeMillis();
      logger.info("耗时:"+(end-start)+"ms");
      logger.info("经历"+gaContext.getGeneration()+"代");
      /**
       * 返回解码结果
       */
      return (D) stop.getChromosome().getDecodeResult();
    }
  }

  public Population initPopulation(GaContext context) {
    int populationSize = context.getPopulationSize();
    Population population = new Population(populationSize);
    IChromosomeEncoder encoder = context.getEncoder();
    for (int i = 0; i < populationSize; i++) {
      // 应用编码器对基因进行初始化
      Chromosome chromosome = new Chromosome();
      encoder.encode(context, chromosome);
      // 将新个体加入到种群中
      population.add(i, chromosome);
    }
    return population;
  }

  /**
   * 已知A，B是两个有序列表，且从大到小排序
   * 现在需要从A,B中抽取出N个个体形成新的列表
   *
   * @param maxSize N，最大选取数目
   * @param a       有序列表A
   * @param aLen    a的长度
   * @param b       有序列表B
   * @param bLen    列表B的长度
   * @return 长度为N的列表，倒序
   */
  Chromosome[] arrayMerge(int maxSize, Chromosome[] a, int aLen, Chromosome[] b, int bLen) {
    Chromosome[] c = new Chromosome[maxSize];
    int minLen = aLen > bLen ? bLen : aLen;
    int aPos = 0, bPos = 0, cPos = 0;
    while (aPos < minLen && bPos < minLen && cPos < maxSize) {
      Chromosome a1 = a[aPos];
      Chromosome b1 = b[bPos];
      if (a1.getFitness() >= b1.getFitness()) {
        c[cPos++] = a1;
        aPos++;
      } else {
        c[cPos++] = b1;
        bPos++;
      }
    }
    while (cPos < maxSize && aPos < aLen) {
      c[cPos++] = a[aPos++];
    }
    while (cPos < maxSize && bPos < bLen) {
      c[cPos++] = b[bPos++];
    }
    return c;
  }

  /**
   * 对一组母代应用交叉和变异算子
   *
   * @return
   */
  private Chromosome[] applyCrossAndMutation(GaContext gaContext, Chromosome[] parent) {
    // 根据交叉概率决定是否进行交叉操作
    double rate = Math.random();
    Chromosome[] newChromosome = parent;
    if (rate > (1 - gaContext.getCrossRate())) {
      newChromosome = gaContext.getCrossOperator().cross(parent[0], parent[1]);
    }
    rate = Math.random();
    // 根据变异概率决定是否应用变异算子
    if (rate > (1 - gaContext.getMutationRate())) {
      newChromosome[0] = gaContext.getMutationOperator().mutation(newChromosome[0]);
    }
    // 将个体加入到新种群

    rate = Math.random();
    // 根据变异概率决定是否应用变异算子
    if (rate > (1 - gaContext.getMutationRate())) {
      newChromosome[1] = gaContext.getMutationOperator().mutation(newChromosome[1]);
    }
    return newChromosome;
  }

  void checkContext(GaContext context){
    if(context.getPopulationSize()%2!=0) {
      throw new IllegalArgumentException("种群大小应该是个偶数");
    }
    if (context.getElitistSelectNum()%2!=0) {
      throw new IllegalArgumentException("保留精英群体的数目应该是偶数");
    }
    if (context.getElitistSelectNum()>context.getPopulationSize()) {
      throw new IllegalArgumentException("精英个体数量不能等于种群大小");
    }
  }

  void print(GaContext context) {

    logger.info("种群大小:"+context.getPopulationSize());
    logger.info("保留精英个数:"+context.getElitistSelectNum());
    logger.info("变异概率:"+context.getMutationRate());
    logger.info("交叉概率:"+context.getCrossRate());
    logger.info("精英保留策略:"+context.getElitistStrategy());
  }
}
