public class BinaryHeap{
	// our class instances
	private int arr[];
	private int size;
	private final int DEFAULT_SIZE;

	//constructor for our BH class
	public BinaryHeap(){
		DEFAULT_SIZE =10;
		arr= new int [DEFAULT_SIZE];
		size=0;
	}

	//functions to access parent and left/right child

	//returns parent of current index
	private int getParent(int index){
		if ((index-1)/2 <0 || (index-1)/2 >=size)
			return -1;
		return (index-1)/2;
	}

	// returns left child of current index (if it exists)
	private int getLeftChild(int index){
		if (2*index+1 >=size || 2*index+1 <0)
			return -1;
		return 2*index+1;
	}

	// returns right child of current index (if it exists)
	private int getRightChild(int index){
		if (2*index+2 >=size || 2*index+2 <0)
			return -1;
		return 2*index+2;
	}

	//this function inserts element into the heap (with priority to the smallest number)
	public void add(int element){
		if (size == arr.length)
			expandArray();
		arr[size++]= element;
		int current_index = size-1;

		// swapping while going upward in tree if our element in current index is less than element in parent
		while (arr[getParent(current_index)] > arr[current_index] && getParent(current_index)>=0){
			swap(current_index,getParent(current_index));
			current_index = getParent(current_index);

		}

	}

	//function to remove smallest element in Binary Heap
	public int remove(){
		if (size == 0)
			return -1;
		swap(size-1, 0);
		--size;
		if (size>0){
			// after we swp first and last element in BH we will return BH to satisfy its properties once more
			fixPosition(0);
		}

		return arr[size];
	}




	//function to expand the array size if needed
	private void expandArray(){
		int temp[]= new int[arr.length*2];
		for (int i=0; i<arr.length;i++)
			temp[i] = arr[i];
		arr = temp;
	}

	//function to swap elements in array
	private void swap(int first_index, int second_index){
		int temp = arr[first_index];
		arr[first_index] = arr[second_index];
		arr[second_index]= temp;
	}

	
	private void fixPosition (int currentPosition){

		//if our current "node" has two children 
		if (getLeftChild(currentPosition) != -1 && getRightChild(currentPosition) != -1){ 

			if (arr[getLeftChild(currentPosition)]<arr[getRightChild(currentPosition)]){

				/* here we check if our left child is less than the current node (and swap if so) after checking if left child
					is less in value than the right child*/
				if (arr[getLeftChild(currentPosition)]<arr[currentPosition]){
					swap(getLeftChild(currentPosition),currentPosition);
					currentPosition = getLeftChild(currentPosition);
					fixPosition (currentPosition);
				}
			} 

			else{
				// if our right child is less than the left value we check if our right child is less than the current node (and swap if so)
				if (arr[getRightChild(currentPosition)]<arr[currentPosition]){
					swap(getRightChild(currentPosition),currentPosition);
					currentPosition = getRightChild(currentPosition);
					fixPosition (currentPosition);
				}
			}
		} 
		// else if we have a left child (only one "node")
		else if (getLeftChild(currentPosition) != -1 && arr[currentPosition]> arr[getLeftChild(currentPosition)]){
			swap(getLeftChild(currentPosition),currentPosition);
			currentPosition = getLeftChild(currentPosition);
			fixPosition (currentPosition);	
		}

	}

}


