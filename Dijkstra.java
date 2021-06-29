import java.util.ArrayList;
import java.io.*;

/**
 * this class for implementing Dijkstra algorithm.
 */
public class Dijkstra {
    int inf = 9999999;
    int n = 25;
    int [][] matrix = new int[n][n];
    /**
     * store edges information
     */
    int [][] edges = {{1,3, 8,9,10,12},
            {0,2,8,9,10,11,12,14},
            {1,11,12,14,22,23,24},
            {0,4,5},
            {3},
            {3,6},
            {5,7},
            {4,6,16,17,18},
            {0,1,9},
            {0,1,8},
            {0,1},
            {1,2},
            {0,1,2,13,14},
            {12,14},
            {1,2,12,13,15},
            {14,23},
            {7,17,18,19,20},
            {7,16},
            {7,16,19},
            {16,18,20},
            {16,19,21},
            {20},
            {2,24},
            {2,15,24},
            {2,22,23}
    };
    /**
     * store distance information
     */
    int [][] dis ={
            {6,4,13,12,57,14},
            {6,4,14,12,61,3,11,8},
            {4,5,11,8,8,12,14},
            {4,3,7},
            {3},
            {7,2},
            {2,3},
            {9,3,6,6,8},
            {13,14,11},
            {12,12,11},
            {57,61},
            {3,5},
            {14,11,11,8,4},
            {8,9},
            {8,8,4,9,4},
            {4,10},
            {8,6,5,8,6},
            {6,6},
            {8,5,6},
            {8,6,6},
            {6,6,5},
            {5},
            {8,14},
            {12,10,6},
            {14,8,6}
    };
    //record road information.
    String [][] roads =  {{"","A23","","B23 and state cir","","","","", "Morshead Dr","Pialligo Ave","Brindabella Rd and Uriarra Rd","","Lady Denman Dr"},
            {"A23","","A23","","","","","", "Morshead Dr","Pialligo Ave","Brindabella Rd and Uriarra Rd","Coranderrk St","Parkes way","","Belconnen way"},
            {"","A23","","","","","","", "","","","Cowper st and LimeStone","Belconnen way","","Belconnen way","","","","","","","","Belconnen way","Gungahlin Dr","Gungahlin Dr"},
            {"B23 and State Cir","","","","Captain Cook Cres","Kent st"},
            {"","","","Captain Cook Cres"},
            {"","","","Kent st","","","Callam st and Webster st"},
            {"","","","","","Callam st and Webster st","","Ainsworth st"},
            {"","","","","Hindmarsh Dr","","Ainsworth st","","","","","","","","","","Athllon Dr","Athllon Dr and Sulwood Dr","Athllon Dr"},
            {"Morshead Dr","Morshead Dr","","","","","","","","Pialligo Ave"},
            {"pialligo Ave","Pialligo Ave","","","","","","","Pialligo Ave"},
            {"Brindabella Rd and Uriarra Rd","Brindabella Rd and Uriarra Rd"},
            {"","Coranderrk st","Cowper st and Limestone Ave"},
            {"Lady Denman Dr","Parkes way","Belconnen way","","","","","","","","","","","Belconnen way","Belconnen way and coulter Dr"},
            {"","","","","","","","","","","","","Belconnen way","","Ginninderra Dr"},
            {"","Belconnen Way","Belconnen way","","","","","","","","","","Belconnen way and Coulter Dr","Ginninderra Dr","","Wanderlight Ave and Aikman Dr"},
            {"","","","","","","","","","","","","","","Wanderlight Ave and Aikman Dr","","","","","","","","","Gundaroo Dr and Baldwin Dr"},
            {"","","","","","","","Athllon Dr","","","","","","","","","","Dvakeford Dr and Summerland cct","Erindale Dr","Isabella Dr","Drakeford Dr"},
            {"","","","","","","","Athlon Dr and Sulwood Dr","","","","","","","","","Dvakeford Dr and Summerland cct"},
            {"","","","","","","","Athllon Dr","","","","","","","","","Erindale Dr","","","Stemberg Cres Dr"},
            {"","","","","","","","","","","","","","","","","Isabella Dr","","Stemberg Cres Dr","","Isabella Dr"},
            {"","","","","","","","","","","","","","","","","Drakeford Dr","","","Isabella Dr","","Outtrim Ave and Tharwa Dr"},
            {"","","","","","","","","","","","","","","","","","","","","Outtrim Ave and Tharwa Dr"},
            {"","","Belconnen Way","","","","","","","","","","","","","","","","","","","","","","Gungahlin Dr"},
            {"","","Gungahlin Dr","","","","","","","","","","","","","Gundarro Dr and Baldwin Dr","","","","","","","","","Wanganeen Ave"},
            {"","","Gungahlin Dr","","","","","","","","","","","","","","","","","","","","Horse park Dr","Wanganeen Ave"}};



    // All postcodes in ACT
    int [] pp = new int[]{2600, 2601, 2602, 2603, 2604, 2605, 2606, 2607, 2609, 2610, 2611, 2612, 2614, 2615, 2616, 2617, 2900, 2902, 2903, 2904, 2905, 2906, 2911,2913,2914};
    public ArrayList<Integer> postcode = new ArrayList<>();


    //information about hospitals in ACT
    int [] hospital = {2617, 2600, 2605};
    String [] hname = {"University of Canberra Hospital or Calvary Public Hospital Bruce", "Barton Private Hospital or Calvary John James Hospital", "Centenary Hospital for Women and Children"};
    /**
     * Initial postcode information and matrix
     */
    public void Init(){
        for (int i=0; i<pp.length;i++){
            postcode.add(pp[i]);
        }
        for(int i=0; i<n;i++){
            for(int j=0; j<n;j++){
                matrix[i][j] = inf;
            }
        }
        for (int i =0;i<edges.length; i++){
            for(int j=0; j<edges[i].length;j++){
                matrix[i][edges[i][j]] = dis[i][j];
                matrix[edges[i][j]][i] = dis[i][j];
            }
        }

    }
    /**
     * For finding the shortest path to destination
     * input: start point.
     * output: a string shows path. For example: "A->B->C ..."
     */

    public String Find_path(int v,Boolean test, int [] destination, int [][] matrix, int [][] edges, int n ){
        if (!test) {
            v = postcode.indexOf(v);
            matrix = this.matrix;
            destination = this.hospital;
            n = this.n;
            edges = this.edges;
        }
        int [] p = new int[n];
        int [] d = new int [n];
        Boolean [] s = new Boolean[n];
        PriorityQueue queue = new PriorityQueue(n);
        //init stage
        for(int i= 0; i <n; i++){
            d[i] = matrix[v][i];
            s[i] = false;
            if(d[i]==inf){
                p[i] = 0;
            }else{
                p[i] = v;
                queue.Min_Heap_Insert(new DijkstraType(i,d[i]));
            }
        }
        d[v] =0;
        s[v] = true;

        while(queue.size != 0){
            // use Min_Heap find the minimum distance
            DijkstraType dj = queue.Heap_Exact_Min();
            int u = dj.v;
            s[u] = true;
            // update distance
            for(int i=0; i< edges[u].length; i++){
                int u1 = edges[u][i];
                if(!s[u1]) {
                    int newdis = d[u] + matrix[u][u1];
                    if (newdis < d[u1]) {
                        d[u1] = newdis;
                        p[u1] = u;
                        queue.Min_Heap_Insert(new DijkstraType(u1, d[u1]));
                    }
                }
            }
        }
        int minidis = inf;
        int index=0;
        String name = "";
        for(int i= 0; i<destination.length;i++){
            int position;
            if (!test) {
                position = postcode.indexOf(destination[i]);
            }else{
                position = destination[i];
            }
            int dis = d[position];
            if (dis<minidis){
                minidis = dis;
                index = position;
                if(!test) {
                    name = hname[i];
                }else{
                    name = String.valueOf(i);
                }
            }
        }
        if(v==index){
            return name+" is near by your location";
        }

        return  searchPath(p,v,index,n,name, test);
    }
    /**
     * For finding the path to the nearest destination
     * input: shortest path set, start point, the nearest destination, the total number of vertices, the name of destination
     * output: path
     */
    public String searchPath(int[] p, int v, int u, int n , String name, Boolean test ){
        int [] q = new int[n];
        int tot = 0;
        q [tot] = u;
        tot ++;
        int temp = p[u];
        while(temp != v ){
            q [tot] = temp;
            tot ++;
            temp = p[temp];
        }
        q[tot] = v;
        String ss = "Current Position -> ";
        for(int i=tot; i>=1; i--){
            if(!test) {
                ss = ss + roads[q[i]][q[i - 1]] + "-> ";
            }else{
                ss = ss + String.valueOf(q[i]) + "->";
            }
        }
        if(!test) {
            ss = ss + name;
        }else{
            ss = ss + "destination";
        }
        return ss;
    }




}
