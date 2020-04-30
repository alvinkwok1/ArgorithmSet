package string.singlepattern;

/**
 * 利用字符串匹配自动机进行串的匹配
 */
public class FiniteAutoMata {
  private int ASCII_TABLE_SIZE = 255;
  // 有限字母表
  private char[] charTable = new char[ASCII_TABLE_SIZE];
  // 字母表长度
  private int charTableSize = 0;
  // 状态表
  private int[][] transTable = null;
  // 匹配的模式
  private char[] p = new char[0];

  public FiniteAutoMata(char[] pattern) {
    if (null != pattern) {
      p = pattern;
    }
  }

  /**
   * 根据模式串创建字母表
   */
  private void createCharTable() {
    for (int i = 0; i < p.length; i++) {
      boolean match = false;
      for (int j = 0; j < charTableSize; j++) {
        if (p[i] == p[j]) {
          match = true;
          break;
        }
      }
      if (match) {
        continue;
      } else {
        charTable[charTableSize++] = p[i];
      }
    }
  }

  private boolean isSuffix(int k, int q, char a) {
    if (p[k - 1] != a) {
      return false;
    }
    for (int i = 0; i <= k - 2; i++) {
      if (p[k - i - 2] != p[q - i - 1]) {
        return false;
      }
    }
    // 检查
    return true;
  }


  public void createTransTable() {
    createCharTable();
    int m = p.length;
    // 初始化转移表
    transTable = new int[m + 1][ASCII_TABLE_SIZE];
    for (int q = 0; q <= m; q++) {
      for (int j = 0; j < charTableSize; j++) {
        char a = charTable[j];
        int k = (m < q + 1 ? m : q + 1);
        while (k > 0 && !isSuffix(k, q, a)) {
          k--;
        }
        transTable[q][a] = k;
      }
    }
  }

  public int match(char[] t) {
    int q =0;
    for (int i=0;i<t.length;i++) {
      if(transTable[q][t[i]] == p.length) {
        return i-p.length+1;
      }
      q = transTable[q][t[i]];
    }
    return -1;
  }


  public static void main(String[] args) {
    FiniteAutoMata finiteAutoMata = new FiniteAutoMata("abad".toCharArray());
    finiteAutoMata.createTransTable();
    System.out.println(finiteAutoMata.match("abcdabad".toCharArray()));
  }
}
