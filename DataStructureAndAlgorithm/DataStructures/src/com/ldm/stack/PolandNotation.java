package com.ldm.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author 梁东明
 * 2022/8/24
 * 86139
 * 点击setting在Editor 的File and Code Templates 修改
 */
@SuppressWarnings({"all"})
public class PolandNotation {
    public static void main(String[] args) {

        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 1+((2+3)×4)-5 => 转成  1 2 3 + 4 × + 5 –
        //2. 因为直接对str 进行操作，不方便，因此 先将  "1+((2+3)×4)-5" =》 中缀的表达式对应的List
        //   即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
        //   即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]
        String expression = "1+((2+3)*4)-5";//注意表达式
        List<String> list = getMiddleExpressionListString(expression);
        System.out.println("中缀表达式= " + list);

        List<String> parseSuffixExpressionList = parseSuffixExpressionList(list);
        System.out.println("后缀表达式 = " + parseSuffixExpressionList);

        int res = calculator(parseSuffixExpressionList);
        System.out.println("最后的计算结果是 = " + res);

       /*
            先定义给逆波兰表达式
		//(30+4)×5-6  => 30 4 + 5 × 6 - => 164
		// 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
		//测试
		//说明为了方便，逆波兰表达式 的数字和符号使用空格隔开
		//String suffixExpression = "3 4 + 5 * 6 -"  ==29;
		//String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76
		//思路
		//1. 先将 "3 4 + 5 × 6 - " => 放到ArrayList中
		//2. 将 ArrayList 传递给一个方法，遍历 ArrayList 配合栈 完成计算
        String suffixExpression = "30 4 + 5 * 6 -";
		List<String> list = getListString(suffixExpression);
		System.out.println("rpnList=" + list);

        int res = calculator(list);
        System.out.println("res = " + res);*/

    }
    //3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
    //   即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈 符号栈s1 和存放中间结果的栈s2
        Stack<String> s1 = new Stack<>();

        //说明：因为s2 这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        //Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈s2
        List<String> s2 = new ArrayList<String>(); // 储存中间结果的Lists2

        //遍历传入的ls
        for (String item : ls) {
            //如果是一个数，就加入s2中,要使用正则表达式，开启多位数的识别
            if (item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while(!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//!!! 将 ( 弹出 s1栈， 消除小括号
            }else {
                //当item的优先级小于或者等于   s1 栈顶的运算符
                // 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                //编写一个Operate类，再编写getValue方法来获取当前运算符的优先级
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >=Operation.getValue(item)){
                    //把s1中的运算符加入s2，直到，item的优先级大于s1栈顶的运算符,退出循环，并且将item压入s1
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }

        }
        //将s1中剩余的运算符依次弹出并加入s2
        while(s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2; //注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List


    }


    //将中缀表达式转换成对应的list
    // s 对应的是中缀表达式expression
    public static List<String > getMiddleExpressionListString(String s){
        //定义一个List,存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0; //这时是一个指针，用于遍历 中缀表达式字符串
        String str; // 对多位数的拼接
        char c; // 每遍历到一个字符，就放入到c
        do {

            //为什么是47  58 你要去问编写ASCII码的那群家伙。
            //47对应的是0 58对应的是9，不信的可以去百度查一下
            //如果c是一个非数字，我需要加入到ls
            if((c=s.charAt(i)) < 48 ||  (c=s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; //i需要后移
            } else { //如果是一个数，需要考虑多位数
                str = ""; //先将str 置成"" '0'[48]->'9'[57]
                while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;//返回
    }

    //将一个逆波兰表达式， 依次将数据和运算符 放入到 ArrayList中
    public static List<String > getListString(String suffixExpression){
        //将表达式分割
        String[] s = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String  data : s) {
            list.add(data);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
	/*
	 * 1)从左至右扫描，将3和4压入堆栈；
		2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
		3)将5入栈；
		4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
		5)将6入栈；
		6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
	 */
    public static int calculator(List<String> ls){
        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历 ls
        for (String item : ls) {
            //使用正则表达式取出数来
            if ( item.matches("\\d+")){ //匹配的是多位数
                //入栈
                stack.push(item);
            }else {
                //pop出两个数，并运算，在入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num2 - num1;
                }else if (item.equals("*")){
                    res = num1 * num2;
                }else  if (item.equals("/")){
                    res = num2 / num2 ;
                }else {
                    throw new RuntimeException("没有这种运算符");
                }
            //把res入栈
            stack.push(""+ res  );
            }

        }
        //最后留在栈中的值就是运算得到的值
        return Integer.parseInt(stack.pop());
    }
}


//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }

}
