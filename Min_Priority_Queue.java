import java.util.*;

public class Min_Priority_Queue {								 //this class is used to form min priority queue
	ArrayList<Data_Class> heap;

	Min_Priority_Queue() {
		heap = new ArrayList<Data_Class>();
	}

	boolean isEmpty(){
		return heap.size() == 0;
	}
	
	int getSize() {                          				    //returns the size of the heap
		return heap.size();
	}
	
	Data_Class getMin(){
		return heap.get(0);
	}
	
	void insert(Data_Class element) {                			//inserts a new element in the heap
		
		heap.add(element);									    // insert element at end of heap (arraylist)

		// Up-heapify
		int childIndex = heap.size() - 1;

		while(childIndex > 0) {
			// Find parent index
			int parentIndex = (childIndex - 1) / 2;

			// Compare child with parent
			if(heap.get(parentIndex).frequency< heap.get(childIndex).frequency ){
				break;
			}

			// Swap if required
			Data_Class temp = heap.get(childIndex);
			heap.set(childIndex, heap.get(parentIndex));
			heap.set(parentIndex, temp);

			// Update childIndex
			childIndex = parentIndex;
		}

	}
	
	Data_Class removeMin() {											// Return the minimum element
		// Swap first and last element
		Data_Class ans = heap.get(0);
		heap.set(0, heap.get(heap.size() - 1));

		// Remove last element
		heap.remove(heap.size() - 1);

		// Down-heapify
		
		int parentIndex = 0;								   // Create parentIndex and start with 0

		while(parentIndex < heap.size()) {
			// Find left and right child
			int leftChildIndex = 2 * parentIndex + 1;
			int rightChildIndex = 2 * parentIndex + 2;

			// Compare parent, left, right and find the minimum one
			int minIndex = parentIndex;

			if(leftChildIndex < heap.size()) {
				if(heap.get(leftChildIndex).frequency < heap.get(minIndex).frequency) {
					minIndex = leftChildIndex;
				}
			}
			if(rightChildIndex < heap.size()) {
				if(heap.get(rightChildIndex).frequency < heap.get(minIndex).frequency) {
					minIndex = rightChildIndex;
				}
			}

			// If parent is minimum, return
			if(minIndex == parentIndex) {
				break;
			}

			// otherwise swap parent with minimum element
			Data_Class temp = heap.get(parentIndex);
			heap.set(parentIndex, heap.get(minIndex));
			heap.set(minIndex, temp);

			// Update parentIndex
			parentIndex = minIndex;
		}

		// return the minimum element (which we have deleted)
		return ans;
	}
}