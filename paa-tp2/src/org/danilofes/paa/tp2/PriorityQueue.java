package org.danilofes.paa.tp2;


public class PriorityQueue {

	private static final int EMPTY = -1; 

	private int size;
	private int[] elements;
	private int[] heap;
	
	private Comparator comparator;
	
	public PriorityQueue(int maxId, Comparator comparator) {
		this.size = 0;
		this.comparator = comparator;
		this.elements = new int[maxId + 1];
		this.heap = new int[maxId + 1];
		
		for (int i = 0; i <= maxId; i++) {
			this.heap[i] = i;
			this.elements[i] = i;
			this.size++;
		}
	}

	public boolean isEmpty() {
		return this.size <= 0;
	}
	
	public int popMin() {
		int min = this.heap[0];
		int last = this.size - 1;
		this.heap[0] = this.heap[last];
		this.elements[this.heap[0]] = 0;
		this.heap[last] = EMPTY;
		this.size--;
		this.elements[min] = EMPTY;
		minHeapify(0, this.size);
		return min;
	}

	public void decreaseKey(int id) {
		int pos = this.elements[id];
		if (pos == EMPTY) {
			return;
		}
		decreasePos(pos);
	}
	
	private void minHeapify(int pos, int n) {
		int max = 2 * pos + 1;

		if (max < n) {
			if ((max + 1) < n && this.comparator.compare(this.heap[max], this.heap[max + 1]) > 0) {
				++max;
			}
			if (this.comparator.compare(this.heap[max], this.heap[pos]) < 0) {
				int temp = this.heap[max];
				this.heap[max] = this.heap[pos];
				this.elements[this.heap[max]] = max;
				this.heap[pos] = temp;
				this.elements[this.heap[pos]] = pos;
				minHeapify(max, n);
			}
		}
	}

	public void buildMinHeap() {
		int i;

		for (i = size / 2 - 1; i >= 0; --i) {
			minHeapify(i, size);
		}
	}
	
	private void decreasePos(int pos) {
		if (pos < 1) {
			return;
		}
		int parent = (pos - 1)/2;
		if (this.comparator.compare(this.heap[pos], this.heap[parent]) < 0) {
			int temp = this.heap[pos];
			this.heap[pos] = this.heap[parent];
			this.elements[this.heap[pos]] = pos;
			this.heap[parent] = temp;
			this.elements[this.heap[parent]] = parent;
			decreasePos(parent);
		}
	}

	public abstract static class Comparator {
		public abstract int compare(int e1, int e2);
	}
}
