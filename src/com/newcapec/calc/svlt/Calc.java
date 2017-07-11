package com.newcapec.calc.svlt;

/**
 * 负责运算具体类
 *
 * @author guoxianwei 2017-05-11 11:26:21
 */
public class Calc {

    public static String result; //存放计算结果

    /**
     * 计算方法
     *
     * @param firstVal  第一个参数
     * @param secondVal 第二个参数
     * @param operator  运算符号
     * @return 计算结果
     */
    public static String calculate(int firstVal, int secondVal, String operator) {
        //加法
        if ("add".equals(operator)) {
            int add_result = add(firstVal, secondVal);
            result = String.valueOf(add_result);
        //减法
        } else if ("subtract".equals(operator)) {
            int subtract_result = subtract(firstVal, secondVal);
            result = String.valueOf(subtract_result);
       //乘法
        } else if ("multiply".equals(operator)) {
            int multiply_result = multiply(firstVal, secondVal);
            result = String.valueOf(multiply_result);
        //除法
        }
        return result;
    }

    /**
     * 加法
     *
     * @param firstVal  第一个参数
     * @param secondVal 第二个参数
     * @return 计算结果
     */
    public static int add(int firstVal, int secondVal) {
        int result = firstVal + secondVal;
        return result;
    }

    /**
     * 减法
     *
     * @param firstVal  第一个参数
     * @param secondVal 第二个参数
     * @return 计算结果
     */
    public static int subtract(int firstVal, int secondVal) {
        int result = firstVal - secondVal;
        return result;
    }

    /**
     * 乘法
     *
     * @param firstVal  第一个参数
     * @param secondVal 第二个参数
     * @return 计算结果
     */
    public static int multiply(int firstVal, int secondVal) {
        int result = firstVal * secondVal;
        return result;
    }

    public static void main(String[] args) {

    }

 }


