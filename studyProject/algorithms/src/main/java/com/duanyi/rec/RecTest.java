package com.duanyi.rec;

public class RecTest {

    /**
     * 递归求解斐波那契数列
     * @param n
     * @return
     */
    public static int feb(int n){
        if( n <= 2) {
            return 1;  //递归终止条件
        }
        return feb(n-1) + feb(n-2);
    }

    /**
     * for循环求解斐波那契数列
     * @param n
     * @return
     */
    public static int forFeb(int n){
        if(n <= 2) {
            return 1;
        }
        int a = 1;
        int b = 1;
        int c = 0;
        for (int i = 3; i <= n; i++){
            c = a+b;
            a = b;
            b = c;
        }
        return c;
    }

    private static int data[];

    /**
     * 数组做缓存，缓存中间计算结果，求解斐波那契数列
     * @param n
     * @return
     */
    public static int arrayFeb(int n){
        if(n<=2) {
            return 1;
        }
        if(data[n] > 0){
            return data[n];
        }
        data[n] = arrayFeb(n-1)+arrayFeb(n-2);
        return data[n];
    }

    /**
     * 尾递归求解斐波那契数列
     * @param n
     * @param res 当前结果
     * @param pre  上一次结果
     * @return
     */
    public static int tailFeb(int n, int res, int pre){
        if(n<=2) {
            return res;
        }
        //下一次递归的当前结果为当前结果+上一次结果，下次递归的上一次结果就为这次的当前结果
        return tailFeb(n-1,res+pre, res);
    }

    /**
     * 阶乘递归
     * @param n
     * @return
     */
    public static int fac( int n){
        if(n <= 1) {
            return 1;
        }
        return n * fac(n-1);
    }

    /**
     * 尾递归求解阶乘
     * @param n
     * @param res
     * @return
     */
    public static int tailFac(int n,int res){
        if(n <= 1) {
            return res;
        }
        return tailFac(n-1,n*res);
    }

    public static void main(String[] args) {
        //递归求解斐波那契数列  44:701408733花费的时间:2415ms
       /* for (int i =0;i<45;i++){
            long start = System.currentTimeMillis();
            System.out.println(i+":"+feb(i)+"花费的时间:"+(System.currentTimeMillis()-start)+"ms");
        }*/

        //for循环求解斐波那契数列  44:701408733花费的时间:0ms
        /*for (int i =0;i<45;i++){
            long start = System.currentTimeMillis();
            System.out.println(i+":"+forFeb(i)+"花费的时间:"+(System.currentTimeMillis()-start)+"ms");
        }*/

        //数组做缓存，缓存中间计算结果，求解斐波那契数列  44:701408733花费的时间:0ms
       /* data = new int[45];
        for (int i =0;i<45;i++){
            long start = System.currentTimeMillis();
            System.out.println(i+":"+arrayFeb(i)+"花费的时间:"+(System.currentTimeMillis()-start)+"ms");
        }*/

       //尾递归求解斐波那契数列   44:701408733花费的时间:0ms
       /* for (int i =0;i<45;i++){
            long start = System.currentTimeMillis();
            System.out.println(i+":"+tailFeb(i,1,1)+"花费的时间:"+(System.currentTimeMillis()-start)+"ms");
        }*/

       //阶乘递归   9:362880花费的时间:0ms
        for (int i =0;i<9;i++){
            long start = System.currentTimeMillis();
            System.out.println(i+":"+fac(i)+"花费的时间:"+(System.currentTimeMillis()-start)+"ms");
        }

      //尾递归求解阶乘
       /* for (int i =0;i<9;i++){
            long start = System.currentTimeMillis();
            System.out.println(i+":"+tailFac(i,1)+"花费的时间:"+(System.currentTimeMillis()-start)+"ms");
        }*/
    }

}
