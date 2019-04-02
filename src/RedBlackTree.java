public class RedBlackTree {
    public static boolean RED = true;
    public static boolean BLACK = false;
    private Node root;

    // Check color Node is RED
    public boolean isRed(Node n){
        if(n == null)
            return false;
        return n.isColor() == RED;
    }

    // Check color Node is BLACK
    public boolean isBlack(Node n){
        if(n == null)
            return false;
        return n.isColor() == BLACK;
    }

    // PreOrder
    public void preOrder(Node n){
        if(n == null)
            return;
        System.out.print(n.getKey() + " ");
        preOrder(n.getLeft());
        preOrder(n.getRight());
    }

    // InOrder
    public void inOrder(Node n){
        if(n == null)
            return;
        inOrder(n.getLeft());
        System.out.print(n.getKey() + " ");
        inOrder(n.getRight());
    }

    //PostOrder
    public void postOrder(Node n){
        if(n == null)
            return;
        postOrder(n.getLeft());
        postOrder(n.getRight());
        System.out.print(n.getKey() + " ");
    }

    // LL ROTATION
    public Node rightRotate(Node n){
        Node left = n.getLeft();
        Node left_right = left.getRight();
        left_right.setParent(n);
        n.setParent(n.getLeft());
        n.getLeft().setParent(n.getParent());

        left.setRight(n);
        n.setLeft(left_right);

        return left;
    }

    // RR ROTATION
    public Node leftRotate(Node n){
        Node right = n.getRight();
        Node right_left = right.getLeft();
        right_left.setParent(n);
        n.setParent(n.getRight());
        n.getRight().setParent(n.getParent());

        right.setLeft(n);
        n.setRight(right_left);

        return right;
    }

    public boolean insert_T(int key){

        try {
            root = insert(root, key);
            return true;
        } catch (NodeAlreadyExists nodeAlreadyExists) {
            System.out.println(nodeAlreadyExists.getMessage() + key);
            return false;
        }
    }

    // Insertion of a new Node to the RED-BLACK Tree
    public Node insert(Node n, int key) throws NodeAlreadyExists {
        if (n == null)
            return (new Node(key, null)); //TODO: Determine what type of object to keep
        else {
            if (n.getKey() == key)
                throw new NodeAlreadyExists();

            // Insertion
            if (key < n.getKey()) {
                n.setLeft(insert(n.getLeft(), key));
                n.getLeft().setParent(n);
            }   //if
            else if (key > n.getKey()) {
                n.setRight(insert(n.getRight(), key));
                n.getRight().setParent(n);
            }   //else-if
            else {
                return n;
            }   //else
        }

        checkRootRedBlackTree(n);
        return n;
    }

    // New Node is added as the root of the tree
    public void checkRootRedBlackTree(Node n) {
        if (n.getParent() == null)
            root.setColor(RED);
        else
            checkParentBlackRedBlackTree(n);
    }

    // Parent of new node is black
    public void checkParentBlackRedBlackTree(Node n){
        if(n.getParent().isColor() == BLACK)
            return;
        else
            checkParentANDUncle(n);
    }

    // Parent and uncle of new node are red
    public void checkParentANDUncle(Node n){
        Node uncle = getUncle(n);
        Node grandParent = getGrandParent(n);
        if(uncle != null && uncle.isColor() == RED){
            n.getParent().setColor(BLACK);
            uncle.setColor(BLACK);
            grandParent.setColor(RED);
            checkRootRedBlackTree(n);
        }   //if
        else
            checkParentREDUncleBLACKRightChild(n);
    }

    /* Parent (P) is red but uncle(U) is black, new node (n)
        is inserted as the right child of P, P is the left child of G*/
    public void checkParentREDUncleBLACKRightChild(Node n){
        Node grandParent = getGrandParent(n);
        if(n == n.getParent().getRight() && n.getParent() == grandParent.getLeft()){
            leftRotate(n.getParent());
            n = n.getLeft();
        }   //if
        else if(n == n.getParent().getLeft() && n.getParent() == grandParent.getRight()){
            rightRotate(n.getParent());
            n = n.getRight();
        }   //else-if
        checkParentREDUncleBLACKLeftChild(n);
    }

    /*Parent (P) is red but uncle(U) is black, new node (n)
        is inserted as the left child of P, P is the left child of G*/
    public void checkParentREDUncleBLACKLeftChild(Node n){
        Node grandParent = getGrandParent(n);
        if(n == n.getParent().getLeft() && n.getParent() == grandParent.getLeft())
            rightRotate(grandParent);
        else if(n == n.getParent().getRight() && n.getParent() == grandParent.getRight()){
            n.getParent().setColor(BLACK);
            grandParent.setColor(RED);
        }   //else-if
    }

    public Node getGrandParent(Node n){
        Node grandParent = null;
        if(n.getParent().getParent() != null)
            grandParent = n.getParent().getParent();
        return grandParent;
    }

    public Node getUncle(Node n){
        Node uncle = null;
        if(n.getParent().getLeft() != null && n.getParent().getLeft().getKey() != n.getKey())
            uncle = n.getParent().getLeft();
        else if(n.getParent().getRight() != null && n.getParent().getRight().getKey() != n.getKey())
            uncle = n.getParent().getRight();
        return uncle;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
