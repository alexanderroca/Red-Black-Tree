public class main {
    public static void main(String[] args){
        RedBlackTree rbt = new RedBlackTree();

        rbt.insert_T(30);
        rbt.insert_T(20);
        rbt.insert_T(40);
        rbt.insert_T(10);
        rbt.insert_T(25);
        rbt.insert_T(50);

         /* The constructed AVL Tree would be
             30B
            /  \
          20B   40B
         /  \     \
        10R  25R    50R
        */
    }
}
