package com.ldm.linkedlist;

import java.util.Stack;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class DemoStack {
    public static void main(String[] args) {
        Stack<Object> stack = new Stack<>();
        //入栈
        stack.add("ldm");
        stack.add("lxj");
        stack.add("xbb");
        //出栈
        while ((stack.size() > 0)) {
            //pop是取出栈顶元素
            System.out.println(stack.pop());
        }
    }
}
