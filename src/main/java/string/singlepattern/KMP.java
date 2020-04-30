package string.singlepattern;

public class KMP {
  private static int[] computePrefixFunction(String pattern) {
    int[] pi = new int[pattern.length()];
    pi[0] = -1;
    int k = -1;
    int q = 1;
    for (; q < pattern.length(); q++) {
      while (k > -1 && pattern.charAt(k + 1) != pattern.charAt(q)) {
        k = pi[k];
      }
      if (pattern.charAt(k + 1) == pattern.charAt(q)) {
        k = k + 1;
      }
      pi[q] = k;
    }
    return pi;
  }

  private static int match(String x, String pattern) {
    int n = x.length();
    int m = pattern.length();
    if (m == 0) {
      return 0;
    }
    int[] pi = computePrefixFunction(pattern);
    int q = -1;
    for (int i = 0; i < n; i++) {
      while (q > -1 && pattern.charAt(q + 1) != x.charAt(i)) {
        q = pi[q];
      }
      if (pattern.charAt(q + 1) == x.charAt(i)) {
        q = q + 1;
      }
      if (q == m - 1) {
        return i - m + 1;
      }
    }
    return pi[0];
  }
}
