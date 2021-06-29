import java.util.ArrayList;
/**
 * this class for implementing Max-Heap.
 */
/**
 * implementation of Max-Heap is based on Hanan lecture.
 * reference: https://cs.anu.edu.au/courses/comp3600/week6-Heaps-aftClass.pdf
 */
public class Max_Heap {
    private ArrayList<Record_Type> maxheap = new ArrayList<>();
    public int size = 0;


    /**
     * swap function supports exchange the positions of two elements
     * input: heap, one position, another position
     * output: after exchanged heap
     */
    private ArrayList<Record_Type> Swap(ArrayList<Record_Type> maxheap, int i, int j){
        Record_Type tmp = maxheap.get(i);
        maxheap.set(i, maxheap.get(j));
        maxheap.set(j, tmp);
        return maxheap;
    }
    /**
     * Heapify function supports to keep properties of heap
     * input: heap, start position
     * output: heap
     */
    private ArrayList<Record_Type> Heapify (ArrayList<Record_Type> maxheap , int i){
        int left = 2*i;
        int right = left + 1;
        int maximum;
        if (left <= size && maxheap.get(left).level > maxheap.get(i).level){
            maximum = left;
        }else{
            maximum = i;
        }
        if (right <= size && maxheap.get(right).level < maxheap.get(i).level){
            maximum = right;
        }
        if(maximum != i ){
            maxheap= Swap(maxheap,i,maximum);
            return Heapify(maxheap, maximum);
        }
        return maxheap;
    }
    /**
     * Insert function supports to insert nodes to heap
     * input: element
     * output: heap after element be inserted
     */
    public void Max_Heap_Insert(Record_Type elem ){
        if(size==0){
            maxheap.add(new Record_Type(0,0,0,0));
        }
        size ++;
        maxheap.add(new Record_Type(0,0,0,0));
        Heap_Increase_key(elem,size);
    }
    public void Heap_Increase_key ( Record_Type elem, int i){
        maxheap.set(i, elem);
        while(i>1 && maxheap.get(i / 2).level < maxheap.get(i).level ){
            maxheap = Swap(maxheap,i/2, i);
            i = i/2;
        }
    }

    /**
     * Exact function supports to find the maximum number
     * input: none
     * output: the maximum node
     */
    public Record_Type Heap_Exact_Max(){
        if(size ==0){
            System.out.println("Check size");
            return null;
        }else{
            Record_Type max = maxheap.get(1);
            maxheap.set(1, maxheap.get(size));
            size --;
            maxheap = Heapify(maxheap,1);
            return max;
        }
    }




}
