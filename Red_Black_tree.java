import java.util.ArrayList;

/**
 * this class is for implementation Red Black tree
 * implementation ideas based on Hanna lecture and my previous implementation in COMP2100
 * Reference: https://cs.anu.edu.au/courses/comp3600/week7-RB.pdf
 */
public class Red_Black_tree {
    public Node root;
    //Design node
    /**
     * initialize node structure
     */
    public class Node{
        Colour colour;       // Node colour
        Record_Type  value;             // Node value
        Node parent;      // Parent node
        Node l, r;
        public Node(Record_Type value){
            this.value = value;
            this.colour = Colour.RED;
            this.parent = null;
            this.l = new Node();
            this.r = new Node();
            this.l.parent = this;
            this.r.parent = this;
        }
        public Node(){
            this.value = null;
            this.colour = Colour.BLACK;
        }
    }
    /**
     * initialize empty tree
     */
    public Red_Black_tree(){
        root = null;
    }
    /**
     * insertnode method only for inserting value like binary tree. I will fix color later
     * input: a root node, and a node need to be added
     * output: no output
     */
    private void insertvalue(Node root, Node x){
        if(x.value.id > root.value.id){
            if(root.r.value == null){
                root.r = x;
                x.parent = root;
            }else{
                insertvalue(root.r, x);
            }
        }else{
            if(x.value.id<root.value.id){
                if(root.l.value == null){
                    root.l = x;
                    x.parent = root;
                }else{
                    insertvalue(root.l, x);
                }
            }
        }

    }
    /**
     *  insert new node to this tree and fix color
     * input: a root node, and a node need to be added
     * output: no output
     */
    private void insert( Node x){
        if (root == null) {
            root = x;
        } else {
            insertvalue(root, x);
        }
        x.colour = Colour.RED;
        //fix color
        while ( x.parent != null && x.parent.colour == Colour.RED ) {
            boolean left = x.parent == x.parent.parent.l ;
            Node uncle;
            if (left) {
                //category1  x’s parent is the left child of x’s grandparent
                uncle = x.parent.parent.r;
            }else{
                //category2  x’s parent is the right child of x’s grandparent
                uncle = x.parent.parent.l;
            }
            //case1  x’s uncle is red
            if (uncle.colour == Colour.RED) {
                //Recolor x’s parent and uncle to be black and x’s
                //grandparent to be red
                x.parent.colour = Colour.BLACK;
                uncle.colour = Colour.BLACK;
                x.parent.parent.colour = Colour.RED;
                //check x'grandparent
                x = x.parent.parent;
            } else {
                if (x.parent.r.value != null && x.parent.l.value != null) {
                    if (x.value.id == (left ? x.parent.r.value.id : x.parent.l.value.id)) {
                        x = x.parent;
                        if (left) {
                            //case2 x’s uncle is black and x is a right child of its parent
                            //Left-rotate x’s parent
                            rotateLeft(x);
                            //continue with case3
                        } else {
                            //case5 x’s uncle is black and x is a left child of its parent
                            //right-rotate x’s parent
                            rotateRight(x);

                        }
                    }
                }
                x.parent.colour = Colour.BLACK;
                x.parent.parent.colour = Colour.RED;
                x = x.parent.parent;
                if (left) {
                    //case3 x's uncle is black and x is a left child of its parent
                    if (x.value == root.value) root = x.l;
                    rotateRight(x);
                } else {
                    //case4 x's uncle is black and x is a right child of its parent
                    if (x.value == root.value) root = x.r;
                    rotateLeft(x);
                }

            }

            }
            root.colour = Colour.BLACK;
        }
    /**
     * for adding new record to this tree
     * input: a new record
     * output: no output
     */
    public void insert(Record_Type r) {
        Node node = new Node(r);
        if (node != null)
            insert(node);
    }
    /**
     * for searching patient's information based on Id
     * input: given Id
     * output: a patient's record
     */
    public Record_Type search(int id){
        return find(id, root);
    }
    /**
     * searching Id through this red black tree
     * input: given Id, a start node for searching
     * output: a patient's record
     */
    private Record_Type find(int id, Node root) {
        if (root == null) {
            return null;
        }else {

            if (id > root.value.id) {
                return find(id, root.r);
            } else {
                if (id < root.value.id)
                    return find(id, root.l);
                else
                    return root.value;
            }
        }
    }
    /**
     * left rotate for fixing this tree
     * input: a node
     * output: no output
     */
    private void rotateLeft( Node x) {
        Node y = x.r;
        x.r = y.l;
        y.l.parent = x;
        y.parent = x.parent;
        if(x.parent == null){
            this.root = y;
        }else{
            if(x == x.parent.l){
                x.parent.l = y;
            }else{
                x.parent.r = y;
            }
        }
        y.l = x;
        x.parent = y;
    }
    /**
     * right rotate for fixing this tree
     * input: a node
     * output: no output
     */
    private void rotateRight( Node x) {
        Node y = x.l;
        x.l = y.r;
        y.r.parent = x;
        y.parent = x.parent;
        if(x.parent == null){
            this.root = y;
        }else{
            if(x == x.parent.r){
                x.parent.r = y;
            }else{
                x.parent.l = y;
            }
        }
        y.r = x;
        x.parent = y;
    }
    /**
     * enum colour
     */
    public enum Colour {
        RED,
        BLACK;
    }


    }
