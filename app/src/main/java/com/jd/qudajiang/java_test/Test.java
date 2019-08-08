package com.jd.qudajiang.java_test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author qudajiang
 * @date 2018/9/7
 */
public class Test {
    public static void main(String[] args) {

//        findCircleTest();

//        mergeOrderLink();

//        testGuibingSort();

//        testQuickSort();

//        testfindElement();

//        testCutTree();


//        testOpenSquareRoot();

//        testIsValidBrackets();

//        testIsPalindrome();

//        testIsAnagram();
//        testTwoSum();
//        testSum();
//        testSqrt();
//        testSingleNumber();
//        testRotateArray();
//        testRomainToInt();
//        testReverseString();
//        testReverseList();
//        testReverseInt();
//        testReverseBits();
//        testIsPowerOfThree();
//        testPlusOne();
//        testLongestCommonPrefix();
//        testCountAndSay();
//        testMiniDist();
//        testMaxSubArray();
//        testIsHappy();
//        testMyAtoi();
//        testDivide();
//        testSearch();
//        testSearchRange();
//        testNumDecodings();
//        testMiniPath();
        testLeastNumbers();
    }

    private static void testLeastNumbers() {
        ArrayList<Integer> list = Algorithm.getLeastNumbers(new int[]{3, 7, 2, 1, 9, 0, 4}, 4);
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * [
     [1,3,1],
     [1,5,1],
     [4,2,1]
     ]
     */
    private static void testMiniPath() {
        int[][] path = {{1,3,1},{1,5,1},{4,2,1}};
        int i = Algorithm.minPathSum(path);
        System.out.println("最短路径：："+i);
    }

    private static void testNumDecodings() {
        Algorithm.numDecodings("817");
    }

    private static void testSearchRange() {
        int[] a = new int[]{1,8,10};
        System.out.println("搜索范围为："+Arrays.toString(Algorithm.searchRange(a,8)));
    }

    private static void testSearch() {
        Algorithm.search(new int[]{4,5,6,7,0,1,2},7);
    }

    private static void testDivide() {
        int a = Integer.MIN_VALUE,b = Integer.MAX_VALUE;
        System.out.println(a+"/"+b+"的商为:"+Algorithm.divide(a,b));
    }

    private static void testMyAtoi() {
        String str = "-343-345-kdf";
        System.out.println(str+"--字符串转数字后为-->"+Algorithm.myAtoi(str));
    }


    private static void testIsHappy() {
        System.out.println("19是不是快乐数？"+Algorithm.isHappy(19));
    }

    private static void testMaxSubArray() {
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(Arrays.toString(a)+"的最大子序列和为："+Algorithm.maxSubArray(a));
    }

    private static void testMiniDist() {
        Algorithm.distMinDistance();
    }

    private static void testCountAndSay() {
        int n = 30;
        System.out.println("第"+n +"次报数为："+Algorithm.countAndSay(n));
    }

    private static void testLongestCommonPrefix() {
        String[] strings = new String[]{"abcd","abc","ab"};
        String s = Algorithm.longestCommonPrefix(strings);
        System.out.println(Arrays.toString(strings)+"的最长公共前缀为："+s);
    }

    private static void testPlusOne() {
        int[] a = new int[]{9};
        System.out.println("原数组："+Arrays.toString(a));
        int[] ints = Algorithm.plusOne(a);
        System.out.println("加1后数组："+Arrays.toString(ints));
    }

    private static void testIsPowerOfThree() {
        int a = 4;
        System.out.println(a+"是3的幂吗？"+Algorithm.isPowerOfThree(a));
    }

    private static void testReverseBits() {
        int a = 0b00000010100101000001111010011100;
        int i = Algorithm.reverseBits(a);
        System.out.println(Integer.toBinaryString(a)+"反转后得到：--->"+Integer.toBinaryString(i));
    }

    private static void testReverseInt() {
        int a = 1534236469;
        int i = Algorithm.reverseInt(a);
        System.out.println(a+"反转后得到："+i);
    }

    private static void testReverseList() {
        Algorithm.ListNode nodeLis = createNodeLis(1, 2, 3, 4, 5, 6);
        Algorithm.ListNode p = nodeLis;
        System.out.print("反转前：");
        while (p != null){
            System.out.print(p.val+",");
            p = p.next;
        }
        p = Algorithm.reverseList(nodeLis);
        System.out.print("--->反转后：");
        while (p != null){
            System.out.print(p.val+",");
            p = p.next;
        }
    }

    private static Algorithm.ListNode createNodeLis(int ...data) {
        Algorithm.ListNode head = new Algorithm.ListNode(data[0]);
        Algorithm.ListNode p = head;
        for (int i = 1; i < data.length; i++) {
            Algorithm.ListNode listNode1 = new Algorithm.ListNode(data[i]);
            p.next = listNode1;
            p = listNode1;
        }
        return head;
    }

    private static void testReverseString() {
        char[] chars = new char[]{'h','e','l','l','o'};
        System.out.println("字符串："+Arrays.toString(chars));
        Algorithm.reverseString(chars);
        System.out.println("-->反转后："+Arrays.toString(chars));

    }

    private static void testRomainToInt() {
        String s = "MCMXCIV";
        int mcmxciv = Algorithm.romainToInt(s);
        System.out.println(s+" 罗马数字转换为整型为："+mcmxciv);
    }

    private static void testRotateArray() {
        int[] a = new int[]{1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(a) + "after exchanged--->");
        Algorithm.rotate(a,3);
        System.out.println(Arrays.toString(a));

    }

    private static void testSingleNumber() {
        int[] a = new int[]{4,1,2,1,2,3,3};
        int i = Algorithm.singleNumber(a);
        System.out.println(Arrays.toString(a)+"--single number is："+i);
    }

    private static void testSqrt() {
        int a = 45474563;
        int sqrt = Algorithm.sqrt(a);
        System.out.println(a+"开根号为："+sqrt);
    }

    private static void testSum() {
        int a = 0,b = 200;
        Algorithm.sum(a,b);
        System.out.println("求和："+a+"+"+b+"="+Algorithm.sum(a,b));
    }


    private static void testTwoSum() {
        int[] ints = Algorithm.twoSum(new int[]{0,5,2,5,9,1,5}, 10);
        System.out.println(""+ints[0]+ints[1]);
    }

    private static void testIsAnagram() {
        String s = "anagram",t = "nagaram";
        System.out.println(Algorithm.isAnagram(s,t)? "是变位词":"不是变位词");
    }

    private static void testIsPalindrome() {
        String s = "";
        System.out.println(Algorithm.isPalindrome(s) ? (s + "-----是回文") : (s+"-----不是回文"));
    }

    private static void testIsValidBrackets() {
        String s = "{(){}[]({{}[]})}";
        boolean validBrackets = Algorithm.isValidBrackets(s);
        System.out.println(validBrackets ? "有效括号串" : "无效括号串");
    }

    private static void testOpenSquareRoot() {
        int k = 100;
        double v = Algorithm.openSquareRoot(k);
        System.out.println(k+"开平方根："+String.format("%.6f",v));
    }

    private static void testCutTree() {
        int[] arr1 = {3,4,5,4};
        int[] arr2 = {4,5,2,3,4};
        int[] arr3 = {1,2,3,3,5,6,7};
        System.out.println("{3,4,5,4}---->"+Algorithm.cutTree(arr1));
        System.out.println("{4,5,2,3,4}---->"+Algorithm.cutTree(arr2));
        System.out.println("{1,2,3,3,5,6,7}---->"+Algorithm.cutTree(arr3));
    }

    private static void testfindElement() {
        int[] a = {11,8,0,9,7,1,2,5,3};
        int k = 6;
        int element = Algorithm.findElement(a, 0, a.length - 1, k);
        System.out.println("第"+k+"大元素"+element);
    }

    private static void testGuibingSort() {
        int[] a = {11,8,3,9,7,1,2,5,3};
        Algorithm.guibingSort(a,0,a.length-1,new int[9]);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private static void testQuickSort() {
        int[] a = {3};
        Algorithm.quickSort(a,0,a.length-1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * //两个有序链表合并
     */
    private static void mergeOrderLink() {
        Node node1 = new Node();
        node1.val = 1;
        Node node3 = new Node();
        node3.val = 3;
        Node node4 = new Node();
        node4.val = 4;
        Node node10 = new Node();
        node10.val = 10;

        node1.next = node3;
        node3.next = node4;
        node4.next = node10;

        Node node2 = new Node();
        node2.val = 2;
        Node node5 = new Node();
        node5.val = 5;
        Node node7 = new Node();
        node7.val = 7;
        Node node11 = new Node();
        node11.val = 11;

        node2.next = node5;
        node5.next = node7;
        node7.next = node11;
        Node node = Algorithm.merge(node2, node1);
        while (node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }

    /**
     * //监测链表中的环
     */
    static void findCircleTest() {
        Node node1 = new Node();
        node1.val = 1;
        Node node2 = new Node();
        node2.val = 2;
        Node node3 = new Node();
        node3.val = 3;
        Node node4 = new Node();
        node4.val = 4;
        Node node5 = new Node();
        node5.val = 5;
        Node node6 = new Node();
        node6.val = 6;
        Node node7 = new Node();
        node7.val = 7;

//        Node head = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node5;

        Algorithm.findCircle(node1);
    }

}
