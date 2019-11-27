package search.optimize.ga;

import search.optimize.ga.bean.Chromosome;

public class CrossOperator implements ICrossOperator {
  public Chromosome[] cross(Chromosome a, Chromosome b) {
    // 使用单点交叉算子进行基因重组
    // 生成两个不相同的点
    int pointA=0,pointB=0;
    while (pointA == pointB){
      pointA = (int) (Math.random()*5);
      pointB = (int) (Math.random()*5);
    }
    if (pointA > pointB) {
      int temp = pointA;
      pointA = pointB;
      pointB = temp;
    }
    // 先形成两个子代
    Chromosome[] child = new Chromosome[2];
    child[0] = new Chromosome();
    child[1] = new Chromosome();
    // 复制母代基因
    char[] newGena=new char[5];
    System.arraycopy(a.getEncodeSpace(),0,newGena,0,5);
    child[0].setEncodeSpace(newGena);
    char[] newGenb = new char[5];
    System.arraycopy(b.getEncodeSpace(),0,newGenb,0,5);
    child[1].setEncodeSpace(newGenb);
    // 替换子代的基因
    System.arraycopy(a.getEncodeSpace(),pointA,newGenb,pointA,pointB-pointA);
    System.arraycopy(b.getEncodeSpace(),pointA,newGena,pointA,pointB-pointA);
    return child;
  }
}
