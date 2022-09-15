package com.ldm.stack;

import com.sun.org.apache.xerces.internal.xs.ItemPSVI;

/**
 * @author 梁东明
 * 2022/8/23
 * 86139
 * 点击setting在Editor 的File and Code Templates 修改
 */
@SuppressWarnings({"all"})
public class Calculator {
    public static void main(String[] args) {

        //创建数栈
        //创建操作符栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operateStack = new ArrayStack2(10);


        //表达式
        String expression = "300+2*6-2";
        //索引
        int index = 0;
        int num1= 0 , num2 = 0 , operate = 0 , res = 0 ;
        String keepNum = "";  //用于拼接多位数
        char ch = ' ';
        //开始扫描表达式
        while (true) {
            //依次获取表达式中的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么
            if (operateStack.isOperate(ch)) {  //如果是运算符则进行以下处理
                //先判断符号栈是否为null，如果为null直接入符号栈
                if (!operateStack.isEmpty()){
                    //如果不为空 则比较符号栈顶的元素的优先级是否大于或等于即将入符号栈的元素，
                    //是就将符号栈的栈顶元素  出栈，数栈出两个数，对这两个数进行运算
                    //再将运算结果入数栈  ， 当前符号元素  入 操作栈
                    //operateStack.peek()返回的是符号栈的栈顶元素
                    if ( operateStack.priority(ch)<=operateStack.priority(operateStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operate = operateStack.pop();
                        res = numStack.cal(num1,num2,operate);
                        //把运算的结果入数栈
                        numStack.push(res);
                        //然后把当前符号元素入符号栈
                        operateStack.push(ch);
                    }else {
                        //否就将即将入栈的元素压入符号栈
                        operateStack.push(ch);
                    }
                } else {
                    operateStack.push(ch);
                }
            }else {
                //numStack.push(ch - 48); //? "1+3" '1' => 1
                //分析思路
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向expression的表达式的index 后再看一位,如果是数就进行扫描，如果是符号才入栈
                //3. 因此我们需要定义一个变量 字符串，用于拼接

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                }else{

                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意是看后一位，不是index++
                    if (operateStack.isOperate(expression.substring(index+1,index+2).charAt(0))) {
                        //如果后一位是运算符，则入栈 keepNum = "1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的!!!!!!, keepNum清空
                        keepNum = "";

                    }
                }


            }
            //让index++并判断是否已经完成对表达式的扫描
            index++;
            if (index >= expression.length()){
                break;
            }
        }
        //- 当表达式扫描完毕，就按照顺序从数栈和符号栈中pop出相应的数和符号，并运算。
        while (true) {
            //如果符号栈为null，则计算到最后结果，数栈只有一个结果
            if (operateStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operate = operateStack.pop();
            res = numStack.cal(num1,num2,operate);
            //把运算的结果入数栈
            numStack.push(res);
            //然后把当前符号元素入符号栈

        }
        //- 最后在数栈只有一个数字，就是表达式的结果。
        System.out.println("表达式 " + expression +" 的结果是: "+numStack.pop());

    }
}
class ArrayStack2{
    private int maxSize;     //栈的大小
    private int[] stack;      //存放栈的数据
    private int top = -1;     //初始化top = -1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize]; //初始化数组栈的容量
    }
    //查看当前栈顶的元素
    public int peek(){
        return stack[top];
    }

    //判断栈满
    public boolean isFull(){

        return top == maxSize - 1;
    }
    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        //判断栈满
        if (isFull()){
            System.out.println("栈满，无法插入元素");
            return;
        }
        top ++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        //判断是否空栈
        if (isEmpty()){
            throw new RuntimeException("栈空，无法出栈");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈元素遍历栈，从栈顶开始
    public void list(){
        //判断栈是否为null
        if (isEmpty()){
            System.out.println("没有元素");
            return;
        }
        //从栈顶显示元素
        for (int i = top; i > 0; i--) {
            System.out.println("第" + i +"个元素是："+stack[i]);
        }
    }
    //返回运算符的优先级、优先级是程序员来定的，优先级使用数字来表示
    //数字越大，优先级越大。
    public int priority(int operate){
        if (operate == '*' || operate == '/'){
            return 1;
        }
        else if (operate == '+' || operate == '-'){
            return 0;
        }
        else {
                //假定目前的表达式只有+ - * /
            return -1;
        }
    }
    //判断是不是一个运算符
    public boolean isOperate(char val){
        return val =='+' || val == '-' || val
                =='*' || val == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int operate){
        int res = 0;
        switch (operate){
            case '+' :
                res = num1 + num2;
                break;
            case '-' :
               res = num2 - num1;  //注意顺序,
                //res = num1 - num2;   //很好 ，你已经学会自己检查代码了，不再是复制黏贴
                //我一开是就是代码写错了，一直找不到原因，还是不够细心。
                //既然你已经走到这一步了，相信你以后一定会记忆深刻。。。。
            break;
            case '*' :
                res = num1 * num2;
            break;
            case '/' :
                //res = num1 / num2;
                res = num2 / num1;
            break;
            default:
                break;
        }
        return res;
    }
}
