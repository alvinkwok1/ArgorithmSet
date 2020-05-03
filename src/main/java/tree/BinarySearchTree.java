package tree;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<V> implements Iterable {


  class Node<V> {
    // k是关键词
    int key;
    // v是卫星数据
    V v;
    // 维护树结构的数据
    Node<V> left, right, p;

    public Node(int k, V v) {
      this.key = k;
      this.v = v;
    }
  }

  // 二叉搜索树的根节点
  private transient Node<V> root;
  // 当前存储的节点数量
  int size;

  /**
   * 向一颗binary tree添加一个key为key,卫星数据为v
   * 的节点。
   * 运算过程中add方法将会先挑选出一个双亲节点，将要插入的
   * 节点的双亲指针指向双亲节点，然后根据key和双亲节点进行比较，
   * 如果小于双亲节点，那么新节点必定是双亲的左孩子，否则是右孩子
   * 此时需要考虑的是，如果双亲为空，那么说明当前树为空
   *
   * @param key
   * @param value
   */
  public void add(int key, V value) {
    Node<V> y = null, x;
    Node<V> n = new Node<>(key, value);
    x = this.root;
    // 寻找可插入的双亲节点
    while (x != null) {
      y = x;
      if (x.key > n.key)
        x = x.left;
      else x = x.right;
    }
    n.p = y;
    // 将插入节点加入到双亲节点下
    if (y == null)
      this.root = n; // 是一颗空树
    else if (y.key > n.key)
      y.left = n;
    else y.right = n;
    ++size;
  }

  /**
   * 从给定的以n为根的子树中寻找到最小的key元素所在的节点
   *
   * @param x 以n为根节点的子树
   * @return
   */
  private Node<V> minimum(Node<V> x) {
    while (x.left != null) {
      x = x.left;
    }
    return x;
  }

  /**
   * 从给定的以n为根的子树中寻找到最大的key元素所在的节点
   *
   * @param x 以n为根节点的子树
   * @return
   */
  private Node<V> maximum(Node<V> x) {
    while (x.right != null) {
      x = x.right;
    }
    return x;
  }

  /**
   * 将以u为根的子树，替换为以v为根的子树
   *
   * @param u
   * @param v
   */
  private void transPlant(Node<V> u, Node<V> v) {
    if (u.p == null)
      this.root = v;// 树根
    // 将u替换为v
    if (u.p.right == u)
      u.p.right = v;
    else
      u.p.left = v;
    // 更新v的双亲节点为u的双亲
    if (v != null)
      v.p = u.p;
  }


  /**
   * 使用迭代的形式对二叉搜索树进行搜索
   * 使用了BBST的特性
   *
   * @param k
   * @return
   */
  private Node<V> search(int k) {
    Node<V> x = this.root;
    while (x != null && x.key != k) {
      if (k < x.key)
        x = x.left;
      else x = x.right;
    }
    return x;
  }

  /**
   * 从BBST中搜索出对于k对应的元素的卫星数据
   *
   * @param k
   * @return
   */
  public V get(int k) {
    Node<V> n = search(k);
    return n == null ? null : n.v;
  }


  public V remove(int key) {
    // 查找到要删除的节点
    Node<V> z = search(key);
    if (z != null)
      remove(z);
    return z == null ? null : z.v;
  }

  /**
   * 从树中移除指定的节点z
   *
   * @param z
   */
  private void remove(Node<V> z) {
    if (z.left == null)
      transPlant(z, z.right);
    else if (z.right == null)
      transPlant(z, z.left);
    else {
      // 查找到z的后继
      Node<V> y = minimum(z.right);
      if (y.p != z) {
        transPlant(y, y.right);
        y.right = z.right;
        y.right.p = y;
      }
      transPlant(z, y);
      y.left = z.left;
      y.left.p = y;
    }
    size--;
  }


  /**
   * 内置的中序遍历迭代器
   */
  class Itr implements Iterator {
    Stack<Node<V>> stack = new Stack<>();
    // 用来保证节点不会被重复访问
    Node<V> lastRet = null;

    public Itr() {
      Node<V> n = BinarySearchTree.this.root;
      if (n != null) {
        stack.push(n);
      }
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public Object next() {
      if (!hasNext()) {
        throw new NoSuchElementException("no such element");
      }
      Node<V> v = stack.pop();
      while (v.left != null && v.left != lastRet) {
        stack.push(v);
        v = v.left;
      }
      if (v.right != null) {
        stack.push(v.right);
      }
      lastRet = v;
      return v.v;
    }
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator iterator() {
    return new Itr();
  }

  public static void main(String[] args) {
    BinarySearchTree<Object> bsTree = new BinarySearchTree();
    bsTree.add(7, 7);
    bsTree.add(2, 2);
    bsTree.add(5, 5);
    bsTree.remove(5);
    bsTree.add(9, 9);
    bsTree.add(8,8);
    Iterator it = bsTree.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }

}
