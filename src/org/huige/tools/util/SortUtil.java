/**
 * 算法汇总
 */
package org.huige.tools.util;

/**
 * @author huige 2015年10月11日 下午11:05:45 Java中各种排序算法汇总工具包
 * 使用方法：调用SortUtil.getDefaultSort(int[] data)使用默认的改进后的快速排序方法排序
 * 调用SortUtil.getSort(int[] data, int algorithm)指定排序方式进行排序
 */
public class SortUtil {
	// 插入排序
	public final static int INSERT = 1;
	// 冒泡排序
	public final static int BUBBLE = 2;
	// 选择排序
	public final static int SELECTION = 3;
	// shell排序
	public final static int SHELL = 4;
	// 快速排序
	public final static int QUICK = 5;
	// 改进后的快速排序
	public final static int IMPROVED_QUICK = 6;
	// 归并排序
	public final static int MERGE = 7;
	// 改进后的归并排序
	public final static int IMPROVED_MERGE = 8;
	// 堆排序
	public final static int HEAP = 9;

	private static String[] name = { "insert", "bubble", "selection", "shell", "quick", "improved_quick", "merge",
			"improved_merge", "heap" };

	/**
	 * 获得对应算法序号的名称
	 * 
	 * @param algorithm
	 * @return
	 */
	public static String getSortAlgorithmtoString(int algorithm) {
		return name[algorithm - 1];
	}

	/**
	 * 交换数据使用
	 * 
	 * @param data
	 * @param i
	 * @param j
	 */
	private static void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	/**
	 * 获得默认排序方法的值 默认算法为改进后的 快速排序
	 * 
	 * @param data
	 */
	public static int[] getDefaultSort(int[] data) {
		return getSort(data, IMPROVED_QUICK);
	}

	/**
	 * 使用指定排序算法对整数数组进行排序
	 * 
	 * @param data
	 *            需要排序的数组
	 * @param algorithm
	 *            排序的算法序号，这里必须调用SortUtil.INSERT这种形式
	 * @return int[] 排好序的数组
	 */
	public static int[] getSort(int[] data, int algorithm) {
		switch (algorithm) {
		// 插入排序
		case INSERT: {
			insertSort(data);
			break;
		}
			// 冒泡排序
		case BUBBLE: {
			bubbleSort(data);
			break;
		}
			// 选择排序
		case SELECTION: {
			selectionSort(data);
			break;
		}
			// shell排序
		case SHELL: {
			shellSort(data);
			break;
		}
			// 快速排序
		case QUICK: {
			quickSort(data);
			break;
		}
			// 改进后的快速排序
		case IMPROVED_QUICK: {
			improved_quickSort(data);
			break;
		}
			// 归并排序
		case MERGE: {
			mergeSort(data);
			break;
		}
			// 改进后的归并排序
		case IMPROVED_MERGE: {
			improved_mergeSort(data);
			break;
		}
			// 堆排序
		case HEAP: {
			heapSort(data);
			break;
		}
		}
		return data;
	}

	/**
	 * 插入排序 SortUtil.INSERT
	 * 
	 * @param data
	 */
	private static void insertSort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			for (int j = i; (j > 0) && (data[j] < data[j - 1]); j--) {
				swap(data, j, j - 1);
			}
		}
	}

	/**
	 * 冒泡排序 SortUtil.BUBBLE
	 * 
	 * @param data
	 */
	private static void bubbleSort(int[] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = data.length - 1; j > i; j--) {
				if (data[j] < data[j - 1]) {
					swap(data, j, j - 1);
				}
			}
		}
	}

	/**
	 * 选择排序 SortUtil.SELECTION
	 * 
	 * @param data
	 */
	private static void selectionSort(int[] data) {
		for (int i = 0; i < data.length; i++) {
			int lowIndex = i;
			for (int j = data.length - 1; j > i; j--) {
				if (data[j] < data[lowIndex]) {
					lowIndex = j;
				}
			}
			swap(data, i, lowIndex);
		}
	}

	/**
	 * shell排序 SortUtil.SHELL
	 * 
	 * @param data
	 */
	private static void shellSort(int[] data) {
		for (int i = data.length / 2; i > 2; i /= 2) {
			for (int j = 0; j < i; j++) {
				insertSort(data, j, i);
			}
		}
		insertSort(data, 0, 1);
	}

	/**
	 * shell排序子方法
	 * 
	 * @param data
	 * @param j
	 * @param i
	 */
	private static void insertSort(int[] data, int start, int inc) {
		for (int i = start + inc; i < data.length; i += inc) {
			for (int j = i; (j >= inc) && (data[j] < data[j - inc]); j -= inc) {
				swap(data, j, j - inc);
			}
		}
	}

	/**
	 * 快速排序 SortUtil.QUICK
	 * 
	 * @param data
	 */
	private static void quickSort(int[] data) {
		quickSortBegin(data, 0, data.length - 1);
	}

	private static void quickSortBegin(int[] data, int i, int j) {
		int pivotIndex = (i + j) / 2;
		swap(data, pivotIndex, j);

		int k = partition(data, i - 1, j, data[j]);
		swap(data, k, j);
		if ((k - i) > 1)
			quickSortBegin(data, i, k - 1);
		if ((j - k) > 1)
			quickSortBegin(data, k + 1, j);

	}

	private static int partition(int[] data, int l, int r, int pivot) {
		do {
			while (data[++l] < pivot)
				;
			while ((r != 0) && data[--r] > pivot)
				;
			swap(data, l, r);
		} while (l < r);
		swap(data, l, r);
		return l;
	}

	/**
	 * 改进后的快速排序 SortUtil.IMPROVED_QUICK
	 * 
	 * @param data
	 */
	private static void improved_quickSort(int[] data) {
		int MAX_STACK_SIZE = 4096;
		int THRESHOLD = 10;
		int[] stack = new int[MAX_STACK_SIZE];

		int top = -1;
		int pivot;
		int pivotIndex, l, r;

		stack[++top] = 0;
		stack[++top] = data.length - 1;

		while (top > 0) {
			int j = stack[top--];
			int i = stack[top--];

			pivotIndex = (i + j) / 2;
			pivot = data[pivotIndex];

			swap(data, pivotIndex, j);

			// partition
			l = i - 1;
			r = j;
			do {
				while (data[++l] < pivot)
					;
				while ((r != 0) && (data[--r] > pivot))
					;
				swap(data, l, r);
			} while (l < r);
			swap(data, l, r);
			swap(data, l, j);

			if ((l - i) > THRESHOLD) {
				stack[++top] = i;
				stack[++top] = l - 1;
			}
			if ((j - l) > THRESHOLD) {
				stack[++top] = l + 1;
				stack[++top] = j;
			}

		}
		insert_ImprovedQuickSort(data);
	}

	private static void insert_ImprovedQuickSort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			for (int j = i; (j > 0) && (data[j] < data[j - 1]); j--) {
				swap(data, j, j - 1);
			}
		}
	}

	/**
	 * 归并排序 SortUtil.MERGE
	 * 
	 * @param data
	 */
	private static void mergeSort(int[] data) {
		int[] temp = new int[data.length];
		mergeSortBegin(data, temp, 0, data.length - 1);
	}

	private static void mergeSortBegin(int[] data, int[] temp, int l, int r) {
		int mid = (l + r) / 2;
		if (l == r)
			return;
		mergeSortBegin(data, temp, l, mid);
		mergeSortBegin(data, temp, mid + 1, r);
		for (int i = l; i <= r; i++) {
			temp[i] = data[i];
		}
		int i1 = l;
		int i2 = mid + 1;
		for (int cur = l; cur <= r; cur++) {
			if (i1 == mid + 1)
				data[cur] = temp[i2++];
			else if (i2 > r)
				data[cur] = temp[i1++];
			else if (temp[i1] < temp[i2])
				data[cur] = temp[i1++];
			else
				data[cur] = temp[i2++];
		}
	}

	/**
	 * 改进后的归并排序 SortUtil.IMPROVED_MERGE
	 * 
	 * @param data
	 */
	private static void improved_mergeSort(int[] data) {
		int[] temp = new int[data.length];
		improved_mergeSortBegin(data, temp, 0, data.length - 1);
	}

	private static void improved_mergeSortBegin(int[] data, int[] temp, int l, int r) {
		int i, j, k;
		int THRESHOLD = 10;
		int mid = (l + r) / 2;
		if (l == r)
			return;
		if ((mid - l) >= THRESHOLD)
			improved_mergeSortBegin(data, temp, l, mid);
		else
			insert_improved_mergeSort(data, l, mid - l + 1);
		if ((r - mid) > THRESHOLD)
			improved_mergeSortBegin(data, temp, mid + 1, r);
		else
			insert_improved_mergeSort(data, mid + 1, r - mid);

		for (i = l; i <= mid; i++) {
			temp[i] = data[i];
		}
		for (j = 1; j <= r - mid; j++) {
			temp[r - j + 1] = data[j + mid];
		}
		int a = temp[l];
		int b = temp[r];
		for (i = l, j = r, k = l; k <= r; k++) {
			if (a < b) {
				data[k] = temp[i++];
				a = temp[i];
			} else {
				data[k] = temp[j--];
				b = temp[j];
			}
		}
	}

	private static void insert_improved_mergeSort(int[] data, int start, int len) {
		for (int i = start + 1; i < start + len; i++) {
			for (int j = i; (j > start) && data[j] < data[j - 1]; j--) {
				swap(data, j, j - 1);
			}
		}
	}

	/**
	 * 堆排序 SortUtil.HEAP
	 * 
	 * @param data
	 */
	private static void heapSort(int[] data) {
		MaxHeap h = new MaxHeap();
		h.init(data);
		for (int i = 0; i < data.length; i++)
			h.remove();
		System.arraycopy(h.queue, 1, data, 0, data.length);
	}

	private static class MaxHeap {

		void init(int[] data) {
			this.queue = new int[data.length + 1];
			for (int i = 0; i < data.length; i++) {
				queue[++size] = data[i];
				fixUp(size);
			}
		}

		private int size = 0;

		private int[] queue;

		public void remove() {
			swap(queue, 1, size--);
			fixDown(1);
		}

		// fixdown
		private void fixDown(int k) {
			int j;
			while ((j = k << 1) <= size) {
				if (j < size && queue[j] < queue[j + 1])
					j++;
				if (queue[k] > queue[j]) // 不用交换
					break;
				swap(queue, j, k);
				k = j;
			}
		}

		private void fixUp(int k) {
			while (k > 1) {
				int j = k >> 1;
				if (queue[j] > queue[k])
					break;
				swap(queue, j, k);
				k = j;
			}
		}

	}
}
