package com.example.loader.utils.lambda;

/**
 * lambda 测试
 *
 * @author ONEC
 */
public class CommonStart {
    private static final String mess = "我是局部";

    public static void main(String[] s) {
        /**
         * 实现方法加法
         */
        MathOperation addition = (a, b) ->
                a + b;
        CommonStart commonStart = new CommonStart();
        /**
         * 参数：实现
         */
        int sum = commonStart.operation(10, 20, addition);
        System.out.println("lambda计算两个数的和：" + sum);


        /**
         * 作用域
         */
        ShowMessage showMessage = (message -> System.out.println(mess + "输出的message：" + message));
        commonStart.show("小样", showMessage);
    }

    /**
     * 定义接口
     */
    interface MathOperation {

        /**
         * 数据计算：
         *
         * @param a
         * @param b
         * @return
         */
        int operation(int a, int b);
    }

    /**
     * 返回接口定义的数据
     *
     * @param a
     * @param b
     * @param mathOperation
     * @return
     */
    private int operation(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    /**
     * 展示信息
     */
    interface ShowMessage {
        /**
         * 展示信息
         *
         * @param message
         */
        void showMessage(String message);
    }

    /**
     * 实现展示
     *
     * @param message
     * @param showMessage
     * @return
     */
    private void show(String message, ShowMessage showMessage) {
        showMessage.showMessage(message);
    }
}
