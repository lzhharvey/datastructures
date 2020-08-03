package com.example.datastructures.search;

import java.util.Arrays;

public class FibonacciSearch {

	public static int maxSize = 20;
	public static void main(String[] args) {
		int [] arr = {1,8, 10, 89, 1000, 1234};
		
		System.out.println("index=" + fibSearch(arr, 89));// 0
		
	}

	//因为后面我们mid=low+F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
	//非递归方法得到一个斐波那契数列
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}

	/**
	 *编写斐波那契查找算法
	 *由斐波那契数列 `F[k]=F[k-1]+F[k-2]` 的性质，可以得到 `（F[k]-1）=（F[k-1]-1）+（F[k-2]-1）+1`
	 *只要顺序表的长度为`F[k]-1`，则可以将该表分成长度为`F[k-1]-1`和`F[k-2]-1`的两段，中间位置为`mid=low+F(k-1)-1`
	 * 使用非递归的方式编写算法
	 * @param a  数组
	 * @param key 我们需要查找的关键码(值)
	 * @return 返回对应的下标，如果没有-1
	 */
	public static int fibSearch(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		//表示斐波那契分割数值的下标
		int k = 0;
		//存放mid值
		int mid = 0;
		//获取到斐波那契数列
		int f[] = fib();
		//1.数组的长度不一定等于f[k] - 1,黄金分割点
		while(high > f[k] - 1) {
			k++;
		}
		//2.因为f[k]的值可能大于a数组的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
		//不足的部分会使用0填充
		int[] temp = Arrays.copyOf(a, f[k]);
		//3.实际上需求使用a数组最后的数填充 temp
		//举例:
		//temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
		for(int i = high + 1; i < temp.length; i++) {
			temp[i] = a[high];
		}

		//现在temp数组长度为：f[k]
		//可以将该数组分成长度为`F[k-1]-1`和`F[k-2]-1`的两段，中间位置为`mid=low+F(k-1)-1`
		//4.使用while来循环处理，找到我们的数 key
		while (low <= high) { // 只要这个条件满足，就可以找
			mid = low + f[k - 1] - 1;
			if(key < temp[mid]) { //我们应该继续向数组的前面查找(左边)
				high = mid - 1;
				//为甚是 k--
				//说明
				//1. 全部元素 = 前面的元素 + 后边元素
				//2. f[k] = f[k-1] + f[k-2]
				//因为前面有f[k-1]个元素,把f[k-1]个元素这一段看成一个整体，这一个整体也是要找一个黄金分割点mid
				//然后把k-1带入上面的式子，得到f[k-1] = f[k-2] + f[k-3]
				//所以可以继续拆分f[k-1] = f[k-2] + f[k-3],黄金分割点在f[k-2]与f[k-3]交界处,既mid这个点
				//所以mid=low+f[k-2]-1;索引从0开始，要-1
				//因此这里要k--
				k--;
			} else if ( key > temp[mid]) { // 我们应该继续向数组的后面查找(右边)
				low = mid + 1;
				//为什么是k -=2
				//说明
				//1. 全部元素 = 前面的元素 + 后边元素
				//2. f[k] = f[k-1] + f[k-2]
				//3. 因为后面我们有f[k-2] ,把k-2带入上面的式子,得：f[k-2] = f[k-3] + f[k-4]
				//因此：mid=low+f[k-3]-1
				//k-3=k-1-X  x=2 所以得k-=2
				k -= 2;
			} else { //找到
				//需要确定，返回的是哪个下标
				if(mid <= high) {
					return mid;
				} else {
					//high是数组arr的最后一个元素的坐标
					//如果找到的元素的坐标大于high,是因为temp的长度大于原数组
					//但是大于的部分都是和原数组的最后一个值，是一样的
					return high;
				}
			}
		}
		return -1;
	}
}
