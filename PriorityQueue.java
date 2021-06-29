/**
 * initialize DijkstraType structure
 */
class DijkstraType {
    int v;
    int value;
    public DijkstraType(int v, int value) {
        this.v = v;
        this.value = value;
    }
}
/**
 * PriorityQueue is similar to max-heap.
 * these functions is same as max-heap. I just changed larger than to less than.
 */
public class PriorityQueue {

    private DijkstraType[] minheap;
    public int size = 0;

    public PriorityQueue(int length){
        this.minheap = new DijkstraType[length+1];
    }

    public DijkstraType get_Min (){
        return minheap[1];
    }

    private  DijkstraType[]  Swap(DijkstraType[] minheap, int i, int j){
        DijkstraType tmp = minheap[i];
        minheap[i] = minheap[j];
        minheap[j] = tmp;
        return minheap;
    }

    private DijkstraType[] Heapify (DijkstraType[] minheap , int i){
        int left = 2*i;
        int right = left + 1;
        int minimum;
        if (left <= size && minheap[left].value < minheap[i].value){
            minimum = left;
        }else{
            minimum = i;
        }
        if (right <= size && minheap[right].value < minheap[i].value){
            minimum = right;
        }
        if(minimum != i ){
            minheap= Swap(minheap,i,minimum);
            return Heapify(minheap, minimum);
        }
        return minheap;
    }

    public void Min_Heap_Insert(DijkstraType elem ){
        size ++;
        minheap[size] = new DijkstraType(0,0);
        Heap_Increase_key(elem,size);
    }
    public void Heap_Increase_key ( DijkstraType elem, int i){
        minheap[i] = elem;
        while(i>1 && minheap[i/2].value > minheap[i].value ){
            minheap = Swap(minheap,i/2, i);
            i = i/2;
        }
    }


    public DijkstraType Heap_Exact_Min(){
        if(size ==0){
            System.out.println("Check size");
            return null;
        }else{
            DijkstraType min = minheap[1];
            minheap[1] = minheap[size];
            size --;
            minheap = Heapify(minheap,1);
            return min;
        }
    }




}
