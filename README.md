# Covid-19-Safe-System
This is a software that support both medical workers and residences in ACT. For medical workers, this system can help workers find patient’s information efficient by given id, decide which person should be treated at first under insufficient resource, add new patient’s information record, and delete these recover patients records. As for residences in ACT, this system guides people who are fell unwell to find a path to the closest hospital by given postcode.

## Functionalities
1. Help medical workers to find the most serious patient to treat. (In emergency case, medical instruments and resources may insufficient).
2. Search patient’s information based on the given unique ID.
3. By giving a postcode, identity the shortest path to the nearest hospital from the center of the
area.

## Methods
### For finding the most serious patient
Due to daily-updated of the new patient records, the traditional sort algorithm is not suitable for this problem. Thus, I decided to use the Max-Heap structure to handle this issue.There are some reasons for using it: Firstly, Max-Heap can find the maximum value in an array very fast. So, medical workers can find the highest risk patient very fast in an emergency.Secondly,Max-Heap is more effective to maintain than insertion sorting in the dynamic situation. For example, when a new record is inserted, insertion sorting needs 𝑂(𝑛) but Max- heap only takes 𝑂(log (𝑛)) to heapify.
### For searching patient’s information
Based on the daily-updated of the new patient record, the data structure is needed to improve efficiency for searching. So, I decided to use the Red-Black tree based on id for each record.
The reasons are listed as follows: Firstly, the Red-Black tree is stable than the Binary Search tree because the Red-Black tree avoids the worst case (the height of the tree is n). For example, If we inset a sorted array to Binary Search tree, then the height of this tree would be n and the time complexity of searching would be 𝜃(𝑛). Secondly, the reason I chose the Red-Black tree is that data need to be updated frequently and the AVL tree would cost too much for keeping completed balance. Thirdly, the Red Black tree saves much memory than the Hash table because the range of ID is uncertain. For example, if there are only two records and their IDs are one and one million, the Hash table creates indexes from one to one million, which would waste memory. Therefore, the Red-Black tree is a good method to handle this problem.

### For identifying nearest hospital
Dijkstra's algorithm can be used to find the nearest hospital because this problem is a single source shortest paths problem. So, I decided to use Dijkstra’s algorithm with the priority queue to find the shortest path for this problem.
There are several reasons: Firstly, the distance between any two places cannot be negative. So, Dijkstra’s algorithm can handle this problem. Secondly, the priority queue can improve Dijkstra’s algorithm efficiency. By using the priority queue, the extract-min step can be improved to 𝑂(𝑙𝑜𝑔(𝑉)). Thirdly, the time complexity of Dijkstra’s algorithm with the priority queue is 𝑂(𝐸 ∗ 𝑙𝑜𝑔(𝑉)) and the time complexity of Bellman-Ford algorithm is 𝑂(𝑉𝐸) (where V means the number of vertexes and E means the number of edges). So, Dijkstra’s algorithm with the priority queue has lower time complexity than Bellman-Ford algorithm. Therefore, Dijkstra’s algorithm with the priority queue is a good method to deal with this problem.
So, we can always find some shortest paths between the center point of the postcode area and all hospitals by using Dijkstra's algorithm with the priority queue and then select the nearest hospital and final path based on the minimum distance of these shortest paths.
