
package com.jd.qudajiang.java_test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.jd.qudajiang.java_test.Node;

/**
 * @author qudajiang
 * @date 2018/10/12
 */
public class Algorithm {
    /**
     * 链表反转
     */
    static Node reverse(Node node) {
        if (node.next == null) {
            return node;
        } else {
            Node head = reverse(node.next);
            node.next.next = node;
            node = null;
            return head;
        }
    }

    public static boolean huiwenLink(Node node){
        Node fast = node;
        Node slow = node;
        Node pre = null;
        Node cur = node;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            cur.next = pre;
            pre = cur;
            cur = slow;
        }
        if (fast != null){
            slow = slow.next;
        }
        while (slow != null && pre != null){
            if (slow.val != pre.val){
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }

    /**
     * 发现链表中的环
     *
     * @param node
     */
    public static void findCircle(Node node) {
        Node slow = node;
        Node quick = node;
        Node meetNode = null;
        Node p = node;
        boolean firstMeet = true;
        int stepCount = 0;
        int firstMeetStep = 0;
        while (quick != null && quick.next != null && quick.next.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            stepCount++;
            if (quick.val == slow.val) {
                if (firstMeet) {
                    firstMeet = false;
                    meetNode = quick;
                    firstMeetStep = stepCount;
                } else {
                    break;
                }

            }
        }

        if (firstMeetStep == 0) {
            System.out.println("此单链表无环");
        } else {
            int circle = stepCount - firstMeetStep;
            System.out.println("环的长度为：" + circle);
            if (p.val == meetNode.val) {
                System.out.println("这是一个循环链表！！");
                return;
            }
            while (true) {
                p = p.next;
                meetNode = meetNode.next;
                if (p.val == meetNode.val) {
                    break;
                }
            }
            System.out.println("环的入口位置：" + p.val);
        }


    }

    /**
     * 两个有序链表合并
     */
    public static Node mergeOrderLink(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        Node merge;
        if (node1.val > node2.val){
            merge = node2;
            node2 = node2.next;
        }else {
            merge = node1;
            node1 = node1.next;
        }
        Node tail = merge;
        while (node1 != null && node2 != null){
            if (node1.val < node2.val){
                tail.next = node1;
                node1 = node1.next;
            }else {
                tail.next = node2;
                node2 = node2.next;
            }
            tail = tail.next;
        }
        if (node1 != null){
            tail.next = node1;
        }
        if (node2 != null){
            tail.next = node2;
        }
        return merge;
    }

    /**
     * 有序链表合并递归
     * @param node1
     * @param node2
     * @return
     */
    public static Node merge(Node node1,Node node2){
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        Node merge;
        if (node1.val < node2.val){
            merge = node1;
            node1 = node1.next;
            merge.next = merge(node1,node2);
        }else {
            merge = node2;
            node2 = node2.next;
            merge.next = merge(node1,node2);
        }
        return merge;
    }

    /**
     * 归并排序算法
     * @param arr 待排序数组
     * @param start 待排序区域的起始下标
     * @param end 待排序区域的结束下标
     * @param orderArr 临时数组，供两有序数组合并时使用
     */
    public static void guibingSort(int[] arr,int start,int end,int[] orderArr){
        if (start < end) {
            int middle = (start + end) / 2;
            guibingSort(arr, start, middle,orderArr);
            guibingSort(arr, middle + 1, end,orderArr);

            arrMerge(arr,start,middle,end,orderArr);
        }
    }

    /**
     * 目标数组的 两有序区间 合并
     * @param arr 目标数组
     * @param start 区间开始位置
     * @param middle 区间中间位置，即第一有序区间结束下标，加1后即是第二区间起始下标
     * @param end 区间结束位置
     * @param orderArr 临时数组供两有序数组合并时使用
     */
    public static void arrMerge(int[] arr,int start,int middle, int end,int[] orderArr){
        int i = start;
        int j = middle + 1;
        int t = 0;

        while (i <= middle && j <= end){
            if (arr[i]<arr[j]){
                orderArr[t] = arr[i];
                t++;
                i++;
            }else {
                orderArr[t] = arr[j];
                t++;
                j++;
            }
        }
        while (i <= middle){
            orderArr[t] = arr[i];
            t++;
            i++;
        }
        while (j <= end){
            orderArr[t] = arr[j];
            t++;
            j++;
        }

        t = 0;
        while (start<=end) {
            arr[start] = orderArr[t];
            start++;
            t++;
        }
    }

    /**
     * 快速排序1
     * @param arr 待排序数组
     * @param start 待排序区间起始位置
     * @param end 待排序区间结束下标
     */
    public static void quickSort(int[] arr,int start,int end){
        if (start < end){
            int separateIndex = seperate(arr,start,end);
            quickSort(arr,start,separateIndex - 1);
            quickSort(arr,separateIndex + 1,end);
        }

    }
    /**
     * 找到第K大元素
     * @param arr
     * @param k
     * @return
     */
    public static int findElement(int[] arr,int start, int end, int k){
        int seperate = seperate(arr, start, end);
        if (seperate + 1 == k){
            return arr[seperate];
        }else if (seperate + 1 < k){
            return findElement(arr,seperate+1,end,k);
        }else {
            return findElement(arr,start,seperate -1,k);
        }
    }
    /**
     * 找出中间元素，分出比此元素小的和大的
     * @param arr 目标元素
     * @param start 开始下标
     * @param end 结束下标
     * @return 中间元素下标
     */
    private static int seperate(int[] arr, int start, int end) {
        int i = start;
        int j = start;
        int temp;
        while (j < end){
            if (arr[j] < arr[end]){
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
            j++;
        }
        temp = arr[i];
        arr[i] = arr[end];
        arr[end] = temp;

        return i;
    }

    public static void quickSort2(int[] arr,int start,int end){
        if (start < end) {
            int i = start;
            int j = end;
            int k = arr[start];

            while (i < j) {
                while (i < j && arr[j] > k) {
                    j--;
                }
                if (i < j) {
                    arr[i] = arr[j];
                    i++;
                }
                while (i < j && arr[i] < k) {
                    i++;
                }
                if (i < j) {
                    arr[j] = arr[i];
                    j--;
                }
            }
            arr[i] = k;
            quickSort2(arr, start, i - 1);
            quickSort2(arr, i + 1, end);
        }
    }



    /**
     * 砍一棵树后仍然有序，有多少种砍法
     * @param arr
     * @return
     */
    public static int cutTree(int[] arr){
        int length = arr.length;
        int haveOrder = length;
        int count = 0;
        for (int i = 0;i < length;i++){
            int cut = arr[i];
            if (i == 0){
                arr[i] = arr[i+1];
            }else {
                arr[i] = arr[i-1];
            }
            for (int j = 0;j < length - 1;j++){
                count++;
                if (arr[j] > arr[j+1]){
                    haveOrder--;
                    break;
                }
            }
            arr[i] = cut;
        }
        System.out.println("砍树程序执行次数："+count);
        return haveOrder;

    }

    public static double openSquareRoot(int x){
        double left = 1;
        double right = x;
        double middle = 1;
        while (left <= right){
            middle = (left+right)/2;
            System.out.println("middle--->"+middle);
            if (middle * middle == x){
                return middle;
            }else if (middle * middle < x){
                left = middle + 0.000001;
            }else {
                right = middle - 0.000001;
            }
        }
        return middle;

    }

    /**
     * 判断[](){}是否有效
     * @return
     */
    public static boolean isValidBrackets(String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (stack.isEmpty()){
                stack.push(c);
            }else {
                Character peek = stack.peek();
                if (peek.equals('(') && c.equals(')')
                        || peek.equals('[') && c.equals(']')
                        || peek.equals('{') && c.equals('}')){
                    stack.pop();
                }else {
                    stack.push(c);
                }
            }


        }
        return stack.isEmpty();
    }

    /**
     * 判断回文
     */
    public static boolean isPalindrome(String s){
        StringBuilder clearStr = new StringBuilder();
        for (int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if (c >= '0' && c <= '9'
                    || c >= 'a' && c <= 'z'
                    || c >= 'A' && c <= 'Z'){
                clearStr.append(c);
            }
        }
        int front = 0;
        int behind = clearStr.length() - 1;
        while (front < behind){
            if (!String.valueOf(clearStr.charAt(front)).equalsIgnoreCase(String.valueOf(clearStr.charAt(behind)))){
                return false;
            }
            front++;
            behind--;
        }
        return true;
    }

    /**
     * 变位词判断
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        int[] table = new int[26];
        for (int i = 0;i < s.length();i++){
            table[s.charAt(i) - 'a']++;
            table[t.charAt(i) - 'a']--;
        }

        for (int i = 0;i < table.length;i++){
            if (table[i] != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 在一个数组中找到两个元素的和等于目标值
     */
    public static int[] twoSum(int[] nums,int target){
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0;i<nums.length;i++){
            int another = target - nums[i];
            if (map.containsKey(another)){
                return new int[]{map.get(another),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("没找到这样的元素");
    }

//    Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 对称二叉树
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }
    public static boolean isMirror(TreeNode node1,TreeNode node2){
        if (node1 == null && node2 == null){
            return true;
        }
        if (node1 == null || node2 == null){
            return false;
        }
        return node1.val == node2.val && isMirror(node1.left,node2.right) && isMirror(node1.right,node2.left);
    }

    public static int sum(int a,int b){

        while (true){
            if (b == 0){
                return a;
            }
            int m = a ^ b;
            int n = a & b;
            b = n << 1;
            a = m;

        }
    }

    /**
     * 开根号，保留整数位
     * @param x
     * @return
     */
    public static int sqrt(int x){
        long low = 1;
        long high = x;
        while (low <= high){
            long mid = (low + high)/2;
            if (mid * mid < x){
                low = mid + 1;
            }else if (mid * mid > x){
                high = mid - 1;
            }else {
                return (int)mid;
            }
        }
        return (int)high;
    }

    /**
     * single number
     * 一个整型数组，只有一个元素出现一次，其他均出现两次，找出那个一次的元素
     */
    public static int singleNumber(int[] nums) {
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            target = target ^ nums[i];
        }
        return target;
    }

    /**
     *给定一个数组，将数组向右旋转k步，其中k是非负的。
     */
    public static void rotate(int[] nums, int k) {

        while (k > 0){
            int last = nums[nums.length - 1];
            for (int i = nums.length - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = last;
            k--;
        }
    }

    /**
     * 罗马数字转换成int
     * @param s
     * @return
     */
    public static int romainToInt(String s){
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int index = s.length() - 1;
        int sum = 0;
        while (index > 0){
            char cur = s.charAt(index);
            char last = s.charAt(index - 1);
            if (map.get(cur) > map.get(last)){
                sum = sum + map.get(cur) - map.get(last);
                index--;
            }else {
                sum = sum + map.get(cur);
            }
            index--;
        }
        if (index == 0){
            sum = sum + map.get(s.charAt(0));
        }
        return sum;
    }

    /**
     * 反转字符串
     * @param s
     */
    public static void reverseString(char[] s) {
        int front = 0;
        int behind = s.length - 1;
        while (front < behind){
            char temp = s[front];
            s[front] = s[behind];
            s[behind] = temp;
            front++;
            behind--;
        }
    }
    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 反转整型
     * @param x
     * @return
     */
    public static int reverseInt(int x) {
        int re = 0;
        while (x != 0){
            int num = x % 10;
            x = x / 10;
            if (re > Integer.MAX_VALUE / 10
                    || re == Integer.MAX_VALUE / 10 && num > 7
                    || re < Integer.MIN_VALUE/10
                    || re == Integer.MIN_VALUE/10 && num < -8){
                return 0;
            }
            re = re * 10 + num;
        }
        return re;
    }

    /**
     * 反转二进制字节码
     * @param n
     * @return
     */
    public static int reverseBits(int n) {
        if (n == 0){
            return 0;
        }
        System.out.println("n = [" + n + "]");

        int sum = 0;
        int count = 32;
        while (count > 0){
            sum = (sum << 1) + (n & 1);
            n = n >> 1;
            count--;
        }
//        while (count > 0){
//            int i = n & 1;
//            sum = sum + i * (int) Math.pow(2, count -1);
//            n = n >> 1;
//            count--;
//        }
        return sum;
    }

    /**
     * 给定一个整数，编写一个函数来确定它是否是3的幂。
     * @param n
     * @return
     */
    public static boolean isPowerOfThree(int n) {
        if (n == 0){
            return false;
        }
        while (n % 3 == 0){
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 一个数组元素组成一个整型，+1后再转为数组
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {

        Stack<Integer> stack = new Stack<>();
        for (int i = digits.length - 1; i >= 0; i--) {
            stack.push(digits[i]);
        }
        int index = 0;
        while (index < digits.length){
            if (stack.elementAt(index) == 9){
                stack.setElementAt(0,index);
            }else {
                stack.setElementAt(stack.elementAt(index)+1,index);
                break;
            }
            if (index == digits.length - 1){
                stack.push(1);
            }
            index++;
        }
        int size = stack.size();
        int[] ints = new int[size];
        for (int i = 0; i < size; i++) {
            ints[i] = stack.pop();
        }
        return ints;
    }
    /**
     * 最长字符串前缀
     */
    public static String  longestCommonPrefix(String[] strs){
        //["flower","flow","flight"]
        if (strs.length == 0){
            return "";
        }
        String s = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(s) != 0){
                s = s.substring(0,s.length() - 1);
                if (s.isEmpty()){
                    return "";
                }
            }
        }
        return s;
        //解法二
//        if (strs == null || strs.length == 0) return "";
//        for (int i = 0; i < strs[0].length() ; i++){
//            char c = strs[0].charAt(i);
//            for (int j = 1; j < strs.length; j ++) {
//                if (i == strs[j].length() || strs[j].charAt(i) != c)
//                    return strs[0].substring(0, i);
//            }
//        }
//        return strs[0];
    }

    /**
     * 有序链表合并
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode merge = new ListNode(0);
        ListNode m = merge;
        ListNode p = l1;
        ListNode q = l2;

        while (p != null && q != null){
            if (p.val < q.val){
                m.next = p;
                m = p;
                p = p.next;
            }else {
                m.next = q;
                m = q;
                q = q.next;
            }
        }
        if (p != null){
            m.next = p;
        }
        if (q != null){
            m.next = q;
        }
        return merge.next;
    }
    /**
     * 有序 数组重复元素
     */
    public static int removeDuplicates(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[count] != nums[i]){
                count++;
                nums[count] = nums[i];
            }
        }
        return count + 1;
    }

    /**
     * 找出haystack中第一次出现needle的下标
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()){
            return 0;
        }
        for (int i = 0; i < haystack.length(); i++) {
            if (i + needle.length()-1 >= haystack.length()){
                return -1;
            }
            int needleIndex = 0;
            while (haystack.charAt(i + needleIndex) == needle.charAt(needleIndex)){
                needleIndex++;
                if (needleIndex == needle.length()){
                    return i;
                }

            }
        }
        return -1;
    }

    /**
     * 报数
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
//        1.     1
//        2.     11
//        3.     21
//        4.     1211
//        5.     111221
        String str = "1";
        for (int i = 0; i < n - 1; i++) {
            int num = 1;
            StringBuilder sb = new StringBuilder();
            char curCount = str.charAt(0);

            for (int j = 1; j < str.length(); j++) {
                if (str.charAt(j) != curCount){
                    sb.append(String.valueOf(num)).append(curCount);
                    curCount = str.charAt(j);
                    num = 1;
                }else {
                    num++;
                }
            }
            sb.append(String.valueOf(num)).append(curCount);
            str = sb.toString();
        }
        return str;
    }

    /**
     * 动态规划
     * 矩形左上角到右下角最短路径
     */
    public static void distMinDistance(){
        int[][] dist = new int[][]{
                {1,3,5,9},
                {2,1,3,4},
                {5,2,6,7},
                {6,8,4,3}};
        int n = 4;
        int i = miniDistBt(dist, n);
        System.out.println("最短路径："+i);
    }
    public static int miniDistBt(int[][] dist,int n){
        for (int k = 1; k < n; k++) {
            dist[0][k] = dist[0][k-1] + dist[0][k];
        }
        for (int k = 1; k < n; k++) {
            dist[k][0] = dist[k-1][0] + dist[k][0];
        }

        for (int k = 1; k < n; k++) {
            for (int l = 1; l < n; l++) {
                dist[k][l] = dist[k][l] + Math.min(dist[k][l-1],dist[k-1][l]);
            }
        }
        return dist[n-1][n-1];
    }

    /**
     * 最大子序列
     * 遍历的时候不停的加，只要sum大于0，就说明接着加后面的仍有可能遇到加的比前面大，
     * 这其中即使遇到负数也没关系，因为max记下来了曾经最大的，但遇到负数也还要加，因为只要加后为正数后面遇到的加上前面仍然可能更大，
     * 但是很关键的是！！
     * 一旦遇到加一个元素后小于0，那你再与后面的连起来合是绝不可能比你从这个元素之后的下个元素重新起步更大！所以这时候重新赋值sum
     */
    public static int  maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum > 0){
                sum = sum+nums[i];
            }else {
                sum = nums[i];
            }
            if (sum > max){
                max = sum;
            }
        }
        return max;
    }
    /**
     * 相交链表
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (true){
            if (pA == null && pB == null){
                return null;
            }
            if (pA == pB){
                return pA;
            }
            if (pA == null){
                pA = headB;
            }else {
                pA = pA.next;
            }
            if (pB == null){
                pB = headA;
            }else {
                pB = pB.next;
            }

        }
    }
    /**
     * 求众数
     */
    public int majorityElement(int[] nums) {
       return majorityElement(0,nums.length - 1,nums);
    }
    public int majorityElement(int left,int right,int[] nums) {
        if (left == right){
            return nums[left];
        }
        int middle = (left+right)/2;
        int leftMajority = majorityElement(left, middle, nums);
        int rightMajority = majorityElement(middle + 1, right, nums);
        if (leftMajority == rightMajority){
            return leftMajority;
        }
        int countLeft = 0;
        for (int i = left; i <= middle; i++) {
            if (nums[i] == leftMajority){
                countLeft++;
            }
        }
        int countRight = 0;
        for (int i = middle + 1; i <= right; i++) {
            if (nums[i] == rightMajority){
                countRight++;
            }
        }
        return countLeft > countRight ? leftMajority : rightMajority;
    }
    /**
     * excel表格列的第一行表示多少列
     */
    public int titleToNumber(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i)-'A'+1;
            sum = sum * 26 + num;
        }
        return sum;
    }
    /**
     * n!有多少0
     */
    public int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5){
            count = count + n/5;
            n = n/5;
        }
        return count;
    }

    /**
     * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        int index = 32;
        while (index > 0){
            if ((n & 1) == 1){
                count++;
            }
            n = n >> 1;
            index--;
        }
        return count;
    }
    /**
     * 打家劫舍
     */
    public static int rob(int[] nums) {
        int odd = 0;
        int even = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0){
                even += nums[i];
                even = Math.max(even,odd);
            }else {
                odd += nums[i];
                odd = Math.max(even,odd);
            }
        }
        return Math.max(odd,even);
    }
    /**
     * 快乐数
     */
    public static boolean isHappy(int n) {
        HashSet<Integer> integers = new HashSet<>();
        int sum = 0;
        while (sum != 1){
            sum = 0;
            while (n != 0){
                sum = sum + (n%10)*(n%10);
                n = n/10;
            }
            n = sum;
            if (integers.contains(sum)){
                return false;
            }
            integers.add(sum);
        }
        return true;
    }
    /**
     * 统计所有小于非负整数 n 的质数的数量。
     */
    public static int countPrimes(int n) {
        int count = 0;
        boolean[] status = new boolean[n];
        for (int i = 2; i < status.length; i++) {
            status[i] = true;
        }
        for (int i = 2; i < Math.sqrt(status.length); i++) {
            if (status[i]){
                for (int j = i*i; j < n; j+=i) {
                    status[j] = false;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (status[i]){
                count++;
            }
        }
        return count;
    }

    /**
     * 判断数组是否有重复元素
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        if (set.size() == nums.length){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 回文链表 1-->2-->3-->4
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null){
            return true;
        }
        if (head.next == null){
            return true;
        }
        ListNode quick = head;
        ListNode slow = head;
        while (quick != null && quick.next != null){
            quick = quick.next.next;
            slow = slow.next;
        }
        ListNode pre = null;
        ListNode ne = slow.next;
        while (ne != null){
            slow.next = pre;
            pre = slow;
            slow = ne;
            ne = ne.next;
        }
        slow.next = pre;
        while (slow != null){
            if (slow.val != head.val){
                return false;
            }
            slow = slow.next;
            head = head.next;
        }
        return true;
    }

    /**
     * deleteNode
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * lost number
     */
    public int missingNumber(int[] nums){
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        int sum1 = (n*(n+1))/2;
        return sum1 - sum;
    }

    /**
     * 移动零
     */
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[j] != 0){
                j++;
            }else {
                if (nums[i] != 0){
                    nums[j] = nums[i];
                    nums[i] = 0;
                    j++;
                }
            }
        }
    }

    /**
     * 求数组交集
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0,j = 0;i < nums1.length && j < nums2.length;){
            if (nums1[i] == nums2[j]){
                list.add(nums1[i]);
                i++;
                j++;
            }else if (nums1[i] > nums2[j]){
                j++;
            }else {
                i++;
            }
        }
        int[] common = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            common[i] = list.get(i);
        }

        return common;
    }
    public int getSum(int a, int b) {
        if ((a & b) == 0){
            return a ^ b;
        }
        return getSum(a ^ b,(a & b)<<1) ;
    }

    /**
     * 字符串中的第一个唯一字符
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
       HashMap<Character,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
           hashMap.put(s.charAt(i),hashMap.getOrDefault(s.charAt(i),0)+1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }

    /**
     * Fizz Buzz
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0){
                list.add("FizzBuzz");
            }else if (i % 3 == 0){
                list.add("Fizz");
            }else if (i % 5 == 0){
                list.add("Buzz");
            }else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    /**
     * 链表相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        int jingwei = 0;
        int x = p.val + q.val;
        if (x > 9){
            jingwei = 1;
            x = x - 10;
        }
        ListNode head = new ListNode(x);
        ListNode last = head;
        p = p.next;
        q = q.next;
        while (p != null && q != null){
            int k = p.val + q.val + jingwei;
            if (k > 9){
                jingwei = 1;
                k = k - 10;
            }else {
                jingwei = 0;
            }
            last.next = new ListNode(k);
            last = last.next;
            p = p.next;
            q = q.next;
        }
        while (p != null){
            int k = p.val+jingwei;
            if (k > 9){
                jingwei = 1;
                k = k - 10;
            }else {
                jingwei = 0;
            }
            last.next = new ListNode(k);
            last = last.next;
            p = p.next;
        }
        while (q!= null){
            int k = q.val+jingwei;
            if (k > 9){
                jingwei = 1;
                k = k - 10;
            }else {
                jingwei = 0;
            }
            last.next = new ListNode(k);
            last = last.next;
            q = q.next;
        }
        if (jingwei != 0){
            last.next = new ListNode(1);
        }
        return head;
//        int sum1 = 0;
//        int sum2 = 0;
//        int times = 1;
//        while (l1 != null){
//            sum1 += l1.val * times;
//            times *= 10;
//            l1 = l1.next;
//        }
//        times = 1;
//        while (l2 != null){
//            sum2 += l2.val * times;
//            times *= 10;
//            l2 = l2.next;
//        }
//        int sum = sum1+sum2;
//        int headVal = sum % 10;
//        ListNode head = new ListNode(headVal);
//        ListNode last = head;
//        sum /= 10;
//        while (sum != 0) {
//            int i = sum % 10;
//            last.next = new ListNode(i);
//            last = last.next;
//            sum /= 10;
//        }
//        return head;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        StringBuilder sb = new StringBuilder();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (sb.indexOf(String.valueOf(s.charAt(i))) != -1){
                sb.delete(0,sb.indexOf(String.valueOf(s.charAt(i)))+1);
            }
            sb.append(s.charAt(i));
            max = Math.max(max,sb.length());
        }
        return max;
    }

    /**
     * 判断最长回文子串
     */
    public static String longestPalindrome(String s) {
        if (s.isEmpty()){
            return "";
        }
        int longestPalindLength = 1;
        String longestPalindrome = s.substring(0,1);
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int right = 1; right < s.length(); right++) {
            for (int left = 0; left <= right; left++) {
                if (s.charAt(right) == s.charAt(left) && (right - left <= 2 || dp[left+1][right-1])){
                    dp[left][right] = true;
                    if (right - left + 1 > longestPalindLength){
                        longestPalindLength = right - left + 1;
                        longestPalindrome = s.substring(left,right+1);
                    }
                }
            }
        }
        return longestPalindrome;
    }

    /**
     * 字符串转数字
     */
    public static int myAtoi(String str) {
        str = str.trim();

        Pattern pattern = Pattern.compile("^[\\+\\-\\d]\\d*");
        Matcher matcher = pattern.matcher(str);
        String numStr = "";
        if (matcher.find()){
            numStr = str.substring(matcher.start(),matcher.end());
        }else {
            return 0;
        }
        if (numStr.equals("+") || numStr.equals("-")){
            return 0;
        }
        int integer;
        try {
            integer = Integer.valueOf(numStr);
        } catch (NumberFormatException e) {
            integer = numStr.charAt(0) == '-' ?  Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        return integer;
    }

    /**
     * 盛最多水的容器
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int maxArea = 0;
        while (i < j){
            if (height[i] < height[j]){
                maxArea = Math.max(maxArea,height[i] * (j - i));
                i++;
            }else {
                maxArea = Math.max(maxArea,height[j] * (j - i));
                j--;
            }
        }
        return maxArea;

    }

    /**
     * 括号生成
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generate("",0,0,n,list);
        return list;
    }
    public void generate(String cur,int open,int close,int n,List<String> list){
        if (cur.length() == 2*n){
            list.add(cur);
            return;
        }
        if (open < n) {
            generate(cur + "(", open + 1, close, n, list);
        }
        if (open > close) {
            generate(cur + ")", open, close + 1, n, list);
        }
    }
    /**
     * 除  超时
     */
    public static int divide(int dividend, int divisor) {
        if (divisor == Integer.MIN_VALUE){
            return 0;
        }else if (divisor == 1){
            return dividend;
        }else{
            int count = 0;
            if (dividend == Integer.MIN_VALUE){
                if (divisor == -1){
                    return Integer.MAX_VALUE;
                }else {
                    dividend = dividend + Math.abs(divisor);
                    count++;
                }
            }
            int num1 = Math.abs(dividend);
            int num2 = Math.abs(divisor);
            int temp2;


            while (num1 >= num2) {
                int yiwei = 1;
                temp2 = num2;
                while (num1 > temp2 << 1) {
                    yiwei = yiwei << 1;
                    temp2 = temp2 << 1;
                }
                count = count + yiwei;
                num1 = num1 - temp2;
            }
            boolean navigate = dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0;
            return navigate ? -count : count;
        }

    }

    /**
     * [0,1,2,4,5,6,7]
     * [4,5,6,7,0,1,2]
     * @param nums
     * @param target 1
     * @return
     */
    public static int search(int[] nums, int target) {
        int n = nums.length - 1;
        if (n == 0){
            return -1;
        }
        int rotateIndex = findRotateIndex(nums);
        if (rotateIndex == 0){
            return search(nums,0, n,target);
        }
        if (target < nums[0]){
            return search(nums,rotateIndex, n,target);
        }else {
            return search(nums,0,rotateIndex - 1,target);
        }
    }

    public static int search(int[] nums,int left,int right,int target){
        while (left <= right){
            int middle = ((right - left)>>1)+left;
            if (nums[middle] == target){
                return middle;
            }else if (nums[middle] > target){
                right = middle - 1;
            }else {
                left = middle + 1;
            }
        }
        return -1;
    }
    /**
     * 查找转折点
     * @param nums
     * @return
     */
    public static int findRotateIndex(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] <= nums[right]){
            return 0;
        }
        while (left <= right){
            int middle = ((right - left)>>1)+left;
            if (nums[middle] > nums[middle+1]){
                return middle+1;
            }
            if (nums[middle] > nums[nums.length-1]){
                left = middle+1;
            }else {
                right = middle - 1;
            }
        }
        return 0;
    }
    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
     * 找出给定目标值在数组中的开始位置和结束位置
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     */
    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0){
            return new int[]{-1,-1};
        }
        int left = searchTarget(nums,0,nums.length-1,target,true);
        int right = searchTarget(nums,0,nums.length-1,target,false);
        return new int[]{left,right};
    }

    public static int searchTarget(int[] nums,int left,int right,int target,boolean isLeft){
        while (left <= right){
            int middle = (left + right)/2;
            if (nums[middle] == target){
                if (isLeft) {
                    if (middle - 1 >= 0 && nums[middle - 1] == target) {
                        right = middle - 1;
                    } else {
                        return middle;
                    }
                }else {
                    if (middle + 1 <= nums.length-1 && nums[middle + 1] == target) {
                        left = middle + 1;
                    } else {
                        return middle;
                    }
                }
            }else if (nums[middle] > target){
                right = middle - 1;
            }else {
                left = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 数独
     */
    public boolean isValidSudoku(char[][] board) {
        HashMap<Character,Character>[] rows = new HashMap[9];
        HashMap<Character,Character>[] colums = new HashMap[9];
        HashMap<Character,Character>[] box = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            colums[i] = new HashMap<>();
            box[i] = new HashMap<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.'){
                    continue;
                }
                if (!rows[i].containsValue(board[i][j])
                        && !colums[j].containsValue(board[i][j])
                        && !box[(j/3+(i/3)*3)].containsValue(board[i][j])){
                    rows[i].put(board[i][j],board[i][j]);
                    colums[j].put(board[i][j],board[i][j]);
                    box[(j/3+(i/3)*3)].put(board[i][j],board[i][j]);
                }else {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     */
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] select = new boolean[nums.length];
        permute(lists,stack,nums,select);
        return lists;
    }

    public void permute(List<List<Integer>> lists,Stack<Integer> stack,int[] nums,boolean[] select){
        if (stack.size() == nums.length){
            lists.add(new ArrayList<Integer>(stack));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!select[i]) {
                stack.push(nums[i]);
                select[i] = true;
                permute(lists, stack, nums, select);
                stack.pop();
                select[i] = false;
            }
        }
    }

    /**
     * 旋转图像 矩阵
     * [ 5, 1, 9, 1],
     * [ 2, 4, 8, 0],
     * [ 3, 3, 6, 7],
     * [ 5, 4, 2, 6]
     */
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < (length+1)/2; i++) {
            for (int j = 0; j < length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length-j-1][i];
                matrix[length-j-1][i] = matrix[length-i-1][length-j-1];
                matrix[length-i-1][length-j-1] = matrix[j][length-i-1];
                matrix[j][length-i-1] = temp;
            }
        }
    }

    /**
     * 字母异位词分组
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> hashMap = new HashMap<>();
        for (String s : strs) {
            int[] count = new int[26];
            Arrays.fill(count,0);
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append(count[i]).append("-");
            }
            String key = sb.toString();
            if (!hashMap.containsKey(key)){
                hashMap.put(key,new ArrayList<String>());
                hashMap.get(key).add(s);
            }else {
                hashMap.get(key).add(s);
            }
        }
        return new ArrayList<>(hashMap.values());
    }
    /**
     * Pow(x, n)
     */
    public double myPow(double x, int n) {
        if (n < 0){
            return mPow(1.0/x,-n);
        }
        return mPow(x,n);
    }
    public double mPow(double x,int n){
        if (n == 0){
            return 1.0;
        }
        double half = mPow(x,n/2);
        if (n % 2 == 0){
            return half * half;
        }else {
            return half * half * x;
        }
    }

    /**
     * 螺旋矩阵
     *  [ 1, 2, 3 ,6 ],
     *  [ 4, 5, 6 ,7 ],
     *  [ 7, 8, 9 ,9 ]
     *  [ 7, 8, 9 ,9 ]
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int ROW = matrix.length;
        if(ROW == 0){
            return list;
        }
        int COLUM = matrix[0].length;

        boolean[][] arrive = new boolean[ROW][COLUM];
        int direct = 0;
        int row = 0;
        int colum = 0;
        int[] dr = {0,1,0,-1};
        int[] dc = {1,0,-1,0};
        for (int i = 0; i < ROW * COLUM; i++) {
            list.add(matrix[row][colum]);
            arrive[row][colum] = true;
            int r = row+dr[direct];
            int c = colum+dc[direct];
            if (r >= 0 && r < ROW && c >= 0 && c < COLUM && !arrive[r][c]){
                row = r;
                colum = c;
            }else {
                direct = (direct+1)%4;
                row = row+dr[direct];
                colum = colum+dc[direct];
            }
        }
        return list;
    }

    enum Index{
        GOOD,BAD,UNKNOWN
    }
    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个位置。
     *  [2,3,2,1,0,4]
     */
    public boolean canJump(int[] nums) {
        Index[] index = new Index[nums.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = Index.UNKNOWN;
        }
        index[index.length-1] = Index.GOOD;
        return jump(nums,0,index);
        //TODO 方法二
//        for (int i = index.length-1; i >= 0 ; i--) {
//            int jumpPosition = Math.min(index.length-1,i+nums[i]);
//            for (int j = i+1; j <= jumpPosition; j++) {
//                if (index[j] == Index.GOOD){
//                    index[i] = Index.GOOD;
//                    break;
//                }
//            }
//        }
//        return index[0] == Index.GOOD;
//        //TODO 方法三 贪心
//        int lastLeft = index.length - 1;
//        for (int i = index.length-1; i >= 0; i--) {
//            if (i+nums[i]>=lastLeft){
//                lastLeft = i;
//            }
//        }
//        return lastLeft == 0;
    }
    public boolean jump(int nums[],int curIndex,Index[] indices){
        if (indices[curIndex] != Index.UNKNOWN) {
            return indices[curIndex] == Index.GOOD;
        }

        for (int i = 1; curIndex + i < nums.length && i <= nums[curIndex]; i++) {
            if (jump(nums, curIndex + i,indices)) {
                indices[curIndex] = Index.GOOD;
                return true;
            }
        }
        indices[curIndex] = Index.BAD;
        return false;
    }

    class Interval{
        public Interval(int s,int e){
            start = s;
            end = e;
        }
        int start;
        int end;
    }
    class IntervalComparator implements Comparator<Interval>{
        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.start < o2.start ? -1 : o1.start == o2.start ? 0 : 1;
        }
    }
    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     * [[1 , 3 ],
     *  [2 , 6 ],
     *  [8 , 10],
     *  [15, 18]]
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0){
            return new int[0][0];
        }
        ArrayList<Interval> originList = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            Interval interval = new Interval(0,0);
            interval.start = intervals[i][0];
            interval.end = intervals[i][1];
            originList.add(interval);
        }
        Collections.sort(originList,new IntervalComparator());

        ArrayList<Interval> mergeList = new ArrayList<>();

        for (int i = 0; i < originList.size(); i++) {
            if (mergeList.isEmpty() || originList.get(i).start > mergeList.get(mergeList.size()-1).end){
                mergeList.add(originList.get(i));
            }else {
                mergeList.get(mergeList.size()-1).end = Math.max(mergeList.get(mergeList.size()-1).end,originList.get(i).end);
            }
        }

//        int start = originList.get(0).start;
//        int end = originList.get(0).end;
//        for (int i = 1; i < originList.size(); i++) {
//            Interval cur = originList.get(i);
//            if (cur.start > end){
//                mergeList.add(new Interval(start,end));
//                start = cur.start;
//                end = cur.end;
//            }else if (cur.end > end){
//                end = cur.end;
//            }
//        }
//        mergeList.add(new Interval(start,end));

        int[][] ints = new int[mergeList.size()][2];
        for (int i = 0; i < mergeList.size(); i++) {
            ints[i] = new int[]{mergeList.get(i).start,mergeList.get(i).end};
        }
        return ints;
    }

    /**
     * 从网格的左上角到右下角有多少种走法，只能向右或者向下
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        // TODO 2
//        int[] curSave = new int[n];
//        Arrays.fill(curSave,1);
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                curSave[j] = curSave[j]+curSave[j-1];
//            }
//        }
//        return curSave[n-1];
        return backPath(m-1,n-1);
    }
    public int backPath(int row,int column){
        if (row == 0 || column == 0){
            return 1;
        }
        return backPath(row-1,column)+backPath(row,column-1);
    }

    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0，
     * 则将其所在行和列的所有元素都设为 0。请使用原地算法
     */
    public void setZeroes(int[][] matrix) {
        boolean colum0 = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    if (j == 0){
                        colum0 = true;
                    }else {
                        matrix[0][j] = 0;
                    }
                }
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0){
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0){
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (matrix[0][0] == 0){
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (colum0){
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * 颜色分类
     */
    public void sortColors(int[] nums) {
        //TODO 方法1 原地
        int cur = 0,m = 0,n = nums.length - 1;
        while (cur <= n){
            if (nums[cur] == 0){
                int temp = nums[m];
                nums[m] = nums[cur];
                nums[cur] = temp;
                m++;
                if (cur < m){
                    cur = m;
                }
            }else if (nums[cur] == 2){
                int temp = nums[n];
                nums[n] = nums[cur];
                nums[cur] = temp;
                n--;
            }else {
                cur++;
            }
        }
        //TODO 方法2
//        int[] colors = new int[3];
//        for (int i = 0; i < nums.length; i++) {
//            colors[nums[i]] = colors[nums[i]]+1;
//        }
//        int j = 0;
//        for (int i = 0; i < colors.length; i++) {
//            while (colors[i] != 0){
//                nums[j] = i;
//                j++;
//                colors[i] = colors[i] - 1;
//            }
//        }
    }

    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<Integer>());
        for (int i = 0; i < nums.length; i++) {
            int size = lists.size();
            for (int j = 0; j < size; j++) {
                List<Integer> newSub = new ArrayList<>(lists.get(j));
                newSub.add(nums[i]);
                lists.add(newSub);
            }
        }
        return lists;
    }

    /**
     *解码方法
     * 输入: "12"
     * 输出: 2
     * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）
     */
    public static int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0'){
            return 0;
        }
        int[] dp = new int[s.length()+1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i < s.length()+1; i++) {
            if (s.charAt(i-1) != '0'){
                dp[i] = dp[i]+dp[i-1];
            }
            if (s.charAt(i-1)<'7' && s.charAt(i-2) == '2' || s.charAt(i-2) == '1'){
                dp[i] = dp[i]+dp[i-2];
            }
        }
        return dp[dp.length-1];
    }

    /**
     * 二叉树中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }



        if (root == null){
            return list;
        }
        inOrder(list,root);
        return list;
    }
    public void inOrder(ArrayList<Integer> list,TreeNode root){
        if (root == null){
            return;
        }
        inOrder(list,root.left);
        list.add(root.val);
        inOrder(list,root.right);
    }

    /**
     * 二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }
    public boolean isValidBST(TreeNode root,Integer upper,Integer lower){
        if (root == null){
            return true;
        }
        int val = root.val;
        if (upper != null && val >= upper){
            return false;
        }
        if (lower != null && val <= lower){
            return false;
        }
        if (!isValidBST(root.left,val,lower)){
            return false;
        }
        if (!isValidBST(root.right,upper,val)){
            return false;
        }
        return true;

    }

    /**
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> treeList = new ArrayList<>();
        if (root == null){
            return treeList;
        }
        //TODO 方法二 迭代
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()){
//            treeList.add(new ArrayList<Integer>());
//            int size = queue.size();
//            int index = treeList.size() - 1;
//            for (int i = 0; i < size; i++) {
//                TreeNode remove = queue.remove();
//                treeList.get(index).add(remove.val);
//                if (remove.left != null){
//                    queue.add(remove.left);
//                }
//                if (remove.right != null){
//                    queue.add(remove.right);
//                }
//            }
//        }

        helperBFS(root,0,treeList);
        return treeList;
    }
    public void helperBFS(TreeNode treeNode,int level,ArrayList<List<Integer>> treeList){
        if (treeList.size() == level){
            treeList.add(new ArrayList<Integer>());
        }
        treeList.get(level).add(treeNode.val);
        if (treeNode.left != null){
            helperBFS(treeNode.left,level+1,treeList);
        }
        if (treeNode.right != null){
            helperBFS(treeNode.right,level+1,treeList);
        }
    }
    /**
     * 二叉树的锯齿形层次遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<List<Integer>> treeList = new ArrayList<>();
        if (root == null){
            return treeList;
        }
        //TODO 方法二迭代
//        Stack<TreeNode> stack1 = new Stack<>();
//        Stack<TreeNode> stack2 = new Stack<>();
//        stack1.add(root);
//        while (!stack1.isEmpty() || !stack2.isEmpty()){
//            treeList.add(new ArrayList<Integer>());
//            int index = treeList.size() - 1;
//            if (!stack1.isEmpty()) {
//                while (!stack1.isEmpty()) {
//                    TreeNode pop = stack1.pop();
//                    treeList.get(index).add(pop.val);
//                    if (pop.left != null) {
//                        stack2.add(pop.left);
//                    }
//                    if (pop.right != null) {
//                        stack2.add(pop.right);
//                    }
//                }
//            }else if (!stack2.isEmpty()){
//                while (!stack2.isEmpty()) {
//                    TreeNode pop = stack2.pop();
//                    treeList.get(index).add(pop.val);
//                    if (pop.right != null) {
//                        stack1.add(pop.right);
//                    }
//                    if (pop.left != null) {
//                        stack1.add(pop.left);
//                    }
//                }
//            }
//        }
        helperZigzagBFS(root,0,treeList);
        return treeList;
    }
    public void helperZigzagBFS(TreeNode treeNode,int level,ArrayList<List<Integer>> treeList){
        if (treeList.size() == level){
            treeList.add(new ArrayList<Integer>());
        }
        if (level % 2 == 0){

            treeList.get(level).add(treeNode.val);
        }else treeList.get(level).add(0,treeNode.val);
        if (treeNode.left != null){
            helperBFS(treeNode.left,level+1,treeList);
        }
        if (treeNode.right != null){
            helperBFS(treeNode.right,level+1,treeList);
        }
    }

    /**
     * 从前序与中序遍历序列构造二叉树
     * 前序遍历序列：ABCDEF
     * 中序遍历序列：CBDAEF
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
    public TreeNode buildTreeHelper(int[] preorder,int[] inorder,int preStart,int preEnd,int inStart,int inEnd){
        if (preStart > preEnd || inStart > inEnd){
            return null;
        }
        TreeNode node = new TreeNode(preorder[preStart]);
        int rootIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (preorder[preStart] == inorder[i]){
                rootIndex = i;
                break;
            }
        }
        node.left = buildTreeHelper(preorder,inorder,preStart+1,preStart+rootIndex-inStart,inStart,rootIndex-1);
        node.right = buildTreeHelper(preorder,inorder,preStart+rootIndex-inStart+1,preEnd,rootIndex+1,inEnd);
        return node;
    }

    /**
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *说明：每次只能向下或者向右移动一步。
     * [1,3,1]
     * [1,5,1]
     * [4,2,1]
     */
    public static int minPathSum(int[][] grid) {
        //TODO 方法一动态规划
//        int m = grid.length;
//        if (m == 0){
//            return 0;
//        }
//        for (int i = 1; i < m; i++) {
//            grid[i][0] = grid[i-1][0]+grid[i][0];
//        }
//        int n = grid[0].length;
//        for (int i = 1; i < n; i++) {
//            grid[0][i] = grid[0][i-1]+grid[0][i];
//        }
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                grid[i][j] = Math.min(grid[i-1][j],grid[i][j-1]) + grid[i][j];
//            }
//        }
//        return grid[m -1][n -1];

        int m = grid.length;
        if (m == 0){
            return 0;
        }
        int n = grid[0].length;
        return miniPath(grid,m-1,n-1);
    }
    public static int miniPath(int[][] grid,int i,int j){
        if (i == 0 && j == 0){
            return grid[0][0];
        }
        if (i < 0 || j < 0){
            return Integer.MAX_VALUE;
        }
        int min = grid[i][j] + Math.min(miniPath(grid,i-1,j),miniPath(grid,i,j-1));
        return min;
    }
    /**
     * 填充每个节点的下一个右侧节点指针
     *给定一个完全二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点
     *填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
     * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     */
    public Node connect(Node root) {
        if(root == null){
            return null;
        }
        if (root.left != null){
            root.left.next = root.right;
            if (root.next != null){
                root.right.next = root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    /**
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * 说明: 叶子节点是指没有子节点的节点。
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        sum = sum - root.val;
        if (sum == 0 && root.left == null && root.right == null){
            return true;
        }else {
            return  hasPathSum(root.left,sum) || hasPathSum(root.right,sum);
        }
    }

    /**
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     * 说明: 叶子节点是指没有子节点的节点。
     * @param root
     * @param sum
     * @return
     */
    List<List<Integer>> mLists = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        backPath(root,sum,new ArrayList<Integer>());
        return mLists;
    }
    public void backPath(TreeNode treeNode,int remain,List<Integer> list){
        if (treeNode == null){
            return;
        }
        remain = remain - treeNode.val;
        list.add(treeNode.val);
        if (remain == 0 && treeNode.left == null && treeNode.right == null){
            mLists.add(new ArrayList<Integer>(list));
        }else {
            backPath(treeNode.left,remain,list);
            backPath(treeNode.right,remain,list);
        }
        list.remove(list.size()-1);

    }

    /**
     *给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
     * 要求返回这个链表的深拷贝。
     * 1.复制每个节点插入源节点后
     */
    public Node copyRandomList(Node head) {
        Node pNode = head;
        //复制每个节点插入对应源节点后
        while (pNode != null){
            Node cloneNode = new Node(pNode.val, null, null);
            cloneNode.next = pNode.next;
            pNode.next = cloneNode;
            pNode = cloneNode.next;
        }
        pNode = head;
        //复制节点的随机指针指向相应节点
        while (pNode != null){
            if (pNode.random != null){
                //注意判断 有的random可能为null
                pNode.next.random = pNode.random.next;
            }
            pNode = pNode.next.next;
        }
        //拆分出来
        pNode = head;
        Node cloneHead = null;
        Node cloneNode = null;
        //注意 处理边界条件
        if (pNode != null){
            cloneHead = pNode.next;
            cloneNode = pNode.next;
            pNode.next = pNode.next.next;
            //注意 重置pNode
            pNode = pNode.next;
        }

        while (pNode != null){
            cloneNode.next = cloneNode.next.next;
            //注意 重置cloneNode
            cloneNode = cloneNode.next;
            pNode.next = pNode.next.next;
            //注意 重置pNode
            pNode = pNode.next;
        }
        return cloneHead;
    }

    /**
     * 将二叉搜索树转换为双向链表
     */
    public TreeNode convertTreeToLink(TreeNode node){
        convert(node);
        return head;
    }
    TreeNode pre = null;
    TreeNode head = null;
    public void convert(TreeNode treeNode){
        if (treeNode == null){
            return;
        }
        convert(treeNode.left);
        treeNode.left = pre;
        if (pre != null){
            pre.right = treeNode;
        }
        //注意 重置pre节点
        pre = treeNode;
        if (head == null){
            head = treeNode;
        }
        convert(treeNode.right);
    }

    /**
     * 数组中出现次数超过一半的数字
     */
    public int moreThanHalfNum(int nums[]){
        int target = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            count = target == nums[i] ? count+1:count-1;
            if (count == 0){
                count = 1;
                target = nums[i];
            }
        }
        count = 0;
        for (int e : nums) {
            if (e == target){
                count++;
            }
        }
        return count > nums.length/2 ? target : 0;
    }

    /**
     * 最小的K个数
     */
    public static ArrayList<Integer> getLeastNumbers(int[] nums,int k){
        findTargetElement(nums,k);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(nums[i]);
        }
        return list;
    }

    private static void findTargetElement(int[] nums, int k) {
        int l = 0;
        int r = nums.length-1;
        while (l < r){
            int sperate = sperate(nums,l,r);
            if (sperate == k){
                break;
            }else if (sperate > k){
                r = sperate-1;
            }else {
                l = sperate+1;
            }
        }
    }
    private static int sperate(int[] num,int l,int r){
        int i = l,j = l;
        int n = num[r];
        while (j < r){
            if (num[j] < n){
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
                i++;
            }
            j++;
        }
        int temp = num[i];
        num[i] = n;
        num[r] = temp;
        return i;
    }
    /**
     * 编写一个程序判断给定的数是否为丑数
     * 丑数就是只包含质因数 2, 3, 5 的正整数。
     */
    public boolean isUgly(int num) {
        if (num == 0){
            return false;
        }
        while(true){
            if (num%2 == 0){
                num = num/2;
            }else if (num%3 == 0){
                num = num/3;
            }else if (num%5 == 0){
                num = num/5;
            }else {
                break;
            }
        }
        return num == 1;
    }
    /**
     * 编写一个程序，找出第 n 个丑数。
     * 丑数就是只包含质因数 2, 3, 5 的正整数
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i1 = 0,i2 = 0,i3 = 0;
        for (int i = 1; i < n; i++) {
            int next1 = dp[i1] * 2;
            int next2 = dp[i2] * 3;
            int next3 = dp[i3] * 5;
            dp[i] = Math.min(next1,Math.min(next2, next3));
            if (dp[i] == next1){
                i1++;
            }
            if (dp[i] == next2){
                i2++;
            }
            if (dp[i] == next3){
                i3++;
            }
        }
        return dp[n-1];
    }
    /**
     * 二叉树的深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left)+1,maxDepth(root.right)+1);
    }
    /**
     * 平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }else {
            return balanceHelper(root) != -1;
        }
    }
    public int balanceHelper(TreeNode treeNode){
        if (treeNode == null){
            return 0;
        }
        int left = balanceHelper(treeNode.left);
        if (left == -1){
            return -1;
        }
        int right = balanceHelper(treeNode.right);
        if (right == -1){
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left,right);

    }
}
