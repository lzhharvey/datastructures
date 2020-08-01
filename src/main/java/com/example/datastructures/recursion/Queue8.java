package com.example.datastructures.recursion;

/**
 * @ClassName Queue8
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/1 10:31
 * @Version 1.0
 **/
public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max=8;
    //定义数组arr,保存皇后放置位置的结果，比如：arr={0,4,7，5,2，6,1,3}
    //index表示皇后的行，value表示皇后的列
    int[] array=new int[max];
    static int count=0;
    static int judgeCount=0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法",count);
        System.out.printf("一共判断冲突%d次",judgeCount);
    }
    //写一个方法，可以将皇后摆放的位置输出
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
    //摆放皇后时，检测与前面的皇后是否冲突
    //n 表示第n个皇后
    private boolean judge(int n){
        judgeCount++;
        for (int i=0;i<n;i++){
            // 说明
            //1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            //比如：array[3]=4表示第四个皇后，在第四列；如果前面的皇后有等于4的，表示出现同列的了，不行
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
            //abs表示绝对值
            //比如：n=3，表示第四个皇后，但在数组中index=3;
            //假设第四个皇后和第三个皇后处于同一斜线，那么它们的列的差值array[3] - array[2]=1；和第二个皇后同一斜线，列的差值为array[3] - array[1]=2
            //反正就是，同一斜线时，列的差值为=array[n] - array[i]；也就是n-1
            //3. 判断是否在同一行, 没有必要，n 每次都在递增
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
    //编写一个方法，放置第n个皇后
    //特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)，因此会有回溯
    private void check(int n) {
        if(n == max) {  //n = 8 , 其实8个皇后就已经放好
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for(int i = 0; i < max; i++) {
            //先把当前这个皇后 n , 放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)) { // 不冲突
                //接着放n+1个皇后,即开始递归
                //例如：
                //1.假设放前面7个皇后都放好了，放第8个时，进入check(7),然后for循第8行的所有列，一个一个试，看冲不冲突
                //如果试到有不冲突的，进入check(8),然后打印，退出check(8);继续check(7)的for循环，继续试其他列。
                //2.假设前面6个皇后都放好了，放第7个皇后时，进入check(6),然后for循环第6行的所有列，如果找到有一列和前
                //5个皇后不冲突，则check(7),进入第8行，看看第8行有几个位置放皇后时不冲突的
                check(n+1); //
            }
            //如果冲突，就继续执行 array[n] = i; 即将第n个皇后，放置在本行得 后移的一个位置
        }
    }
}