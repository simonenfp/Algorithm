package com.jd.qudajiang.java_test;

/**
 * @author qudajiang
 * @date 2019/8/1
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next, Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}
