/*
public class AvlTree {

    TreeNode root;

    AvlTree(){
        root=null;
    }
    private int getHeight(TreeNode node) {
        if (node != null) {
            return node.height;
        }else
            return  -1;
    }

    private void updateHeight(TreeNode node) {
        int Height_rightChild= getHeight(node.right);
        int Height_leftChild = getHeight(node.left);
        node.height =  Math.max(Height_leftChild, Height_rightChild) + 1;
    }

    private int CheckBalance(TreeNode node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    private TreeNode rotateLeft(TreeNode node) {
        TreeNode leftChild = node.left;

        node.left = leftChild.right;
        leftChild.right = node;

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }
    private TreeNode rotateRight(TreeNode node) {
        TreeNode rightChild = node.right;

        node.right = rightChild.left;
        rightChild.left = node;

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }

    private TreeNode reBalance(TreeNode node) {
        int balanceFactor = CheckBalance(node);

        // Left-heavy?
        if (balanceFactor > 1 ) {
            if (CheckBalance(node.left) <= 0) {    // Case 1
                // Rotate right
                node = rotateRight(node);
            } else {                                // Case 2
                // Rotate left-right
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }

        // Right-heavy?
        if (balanceFactor < -1) {
            if (CheckBalance(node.right) >= 0) {    // Case 3
                // Rotate left
                node = rotateLeft(node);
            } else {                                 // Case 4
                // Rotate right-left
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }

        return node;
    }

    public void insert(int key) {
        root = insert_Node(key, root);
    }

    TreeNode insertNode(int key, TreeNode node) {
        // No node at current position --> store new node at current position
        if (node == null) {
            node = new TreeNode(key);
        }

        // Otherwise, traverse the tree to the left or right depending on the key
        else if (key < (int)node.value) {
            node.left = insertNode(key, node.left);
        } else if (key > (int)node.value) {
            node.right = insertNode(key, node.right);
        }
      */
/*  if (key == (int)node.value) {
            node.right = insertNode(key, node.right);
        }*//*

        else {
          //  throw new IllegalArgumentException("BST already contains a node with key " + key);

        }

        return node;
    }

    TreeNode insert_Node(int key, TreeNode node) {
        node = insertNode(key, node);

        updateHeight(node);

        return reBalance(node);
    }

    public void deleteNode(int key) {
        root = deleteNode(key, root);
    }

    TreeNode deleteNode(int key, TreeNode node) {
        // No node at current position --> go up the recursion
        if (node == null) {
            return null;
        }

        // Traverse the tree to the left or right depending on the key
        if (key < (int)node.value) {
            node.left = deleteNode(key, node.left);
        } else if (key >(int) node.value) {
            node.right = deleteNode(key, node.right);
        }

        // At this point, "node" is the node to be deleted

        // Node has no children --> just delete it
        else if (node.left == null && node.right == null) {
            node = null;
        }

        // Node has only one child --> replace node by its single child
        else if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        }

        // Node has two children
        else {
            deleteNodeWithTwoChildren(node);
        }

        return node;
    }

    public void delete(int key) {
        root = deleteNode(key, root);
    }
    private void deleteNodeWithTwoChildren(TreeNode node) {
        // Find minimum node of right subtree ("inorder successor" of current node)
        TreeNode inOrderSuccessor = findMinimum(node.right);

        // Copy inorder successor's data to current node
        node.value = inOrderSuccessor.value;

        // Delete inorder successor recursively
        node.right = deleteNode((int)inOrderSuccessor.value, node.right);
    }
    private TreeNode findMinimum(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    TreeNode delete_Node(int key, TreeNode node) {
        node = deleteNode(key, node);

        // Node is null if the tree doesn't contain the key
        if (node == null) {
            return null;
        }

        updateHeight(node);

        return reBalance(node);
    }
    public void traverse_InOrder() {
        traverseInOrder(root);
    }

    public static void traverseInOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        traverseInOrder(node.left);
        System.out.println(node.value);
        traverseInOrder(node.right);
    }

    public static void traverseLevelOrder(TreeNode root, TreeNode visitor) {
        if (root == null) {
            return;
        }

        Queue queue = new Queue();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.dequeue();
            System.out.println(visitor.value);

            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
    }


}
*/
class AvlTree
{
     TreeNode root;

    public AvlTree()
    {
        root = null;
    }
    //to check if tree is empty
    public boolean isEmpty()
    {
        return root == null;
    }
    // Make the tree  empty
    public void makeEmpty()
    {
        root = null;
    }
    // to get height of node
    private int height(TreeNode treeNode )
    {
        if (treeNode == null){
            return -1;
        }else
            return  treeNode.height;
    }
    // max of left,right
    private int max(int lhs, int rhs)
    {
        return Math.max(lhs, rhs);
    }

    int getHeight (){
        return root.height;
    }
    public void insert(Martyrs data)//insert martyr by name // for First  AVL
{
    root = insert(data, root);
}
    private TreeNode insert(Martyrs x, TreeNode t)
    {
        if (t == null) {// stop case
            t = new TreeNode(x);
        }
        else if (x.getName() .compareToIgnoreCase(((Martyrs) t.value).getName())<0)// to left of TreeNode
             {
            t.left = insert( x, t.left );
            int s =height( t.left ) - height( t.right );
            if(s < -1 || s > 1 ) {// will be between -1 & 1 // check it
                if (x.getName().compareToIgnoreCase(((Martyrs) t.left.value).getName()) < 0)// single left rotate
                    t = singleRotateLeft(t);
                else// the return > from the new
                    t = doubleWithLeftChild(t);
            }
        }
        else if( x .getName() .compareToIgnoreCase(((Martyrs) t.value).getName())>0 ) // to right of TreeNode
        {
            t.right = insert( x, t.right );
            int s =height( t.left ) - height( t.right );
            if(s < -1 || s > 1 ) {
                if (x.getName().compareToIgnoreCase(((Martyrs) t.right.value).getName()) > 0)// right rotate
                    t = singleRotateRight(t);
                else// the return < from the new
                    t = doubleWithRightChild(t);
            }
        }
         // Duplicate; do nothing
        t.height = max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }


    public void delete_byNames(String Martyr) {
        root = delete_byNames(Martyr, root);
    }

    TreeNode delete_byNames(String Martyr, TreeNode node) {
        // No node at current position --> go up the recursion
        if (node == null) {
            return null;
        }

        // Traverse the tree to the left or right depending on the key
        if (Martyr .compareToIgnoreCase(((Martyrs) node.value).getName())< 0) {
            node.left = delete_byNames(Martyr, node.left);
        } else if (Martyr .compareToIgnoreCase(((Martyrs) node.value).getName())>0) {
            node.right = delete_byNames(Martyr, node.right);
        }

        // At this point, "node" is the node to be deleted

        // Node has no children --> just delete it
        else if (node.left == null && node.right == null) {

            node = null;
        }

        // Node has only one child --> replace node by its single child
        else if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        }

        // Node has two children
        else {
            deleteNodeWithTwoChildren_byName(node);
        }

        return node;
    }
    private void deleteNodeWithTwoChildren_byName(TreeNode node) {
        // Find minimum  of the right subtree
        TreeNode minimumRight = findMinimum(node.right);

        node.value = minimumRight.value;

        // Delete min
        node.right = delete_byNames((String) minimumRight.value, node.right);
    }

    private TreeNode findMinimum(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

   /* public void delete_ByDate(String key) { // delete by Date // for second AVL
        root = delete_ByDate(key, root);
    }

    TreeNode delete_ByDate(String key, TreeNode node) {
        // No node at current position --> go up the recursion
        if (node == null) {
            return null;
        }

        // Traverse the tree to the left or right depending on the key
        if (key .compareToIgnoreCase(((Martyrs) node.value).getDateOfDeath())< 0) {
            node.left = delete_ByDate(key, node.left);
        } else if (key .compareToIgnoreCase(((Martyrs) node.value).getDateOfDeath())>0) {
            node.right = delete_ByDate(key, node.right);
        }

        // At this point, "node" is the node to be deleted

        // Node has no children --> just delete it
        else if (node.left == null && node.right == null) {
            node = null;
        }

        // Node has only one child --> replace node by its single child
        else if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        }

        // Node has two children
        else {
            deleteNodeWithTwoChildren_byDate(node);
        }

        return node;
    }
    private void deleteNodeWithTwoChildren_byDate(TreeNode node) {
        // Find minimum node of right subtree ("inorder successor" of current node)
        TreeNode inOrderSuccessor = findMinimum(node.right);

        // Copy inorder successor's data to current node
        node.value = inOrderSuccessor.value;

        // Delete inorder successor recursively
        node.right = delete_ByDate((String) inOrderSuccessor.value, node.right);
    }
*/
    /* Rotate binary tree node with left child */
    private TreeNode singleRotateLeft(TreeNode k2)
    {
        TreeNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    /* Rotate binary tree node with right child */
    private TreeNode singleRotateRight(TreeNode k1)
    {
        TreeNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = max( height( k2.right ), k1.height ) + 1;
        return k2;
    }
    //Double rotate  tree node
    private TreeNode doubleWithLeftChild(TreeNode k3)
    {
        k3.left = singleRotateRight( k3.left );
        return singleRotateLeft( k3 );
    }
    //Double rotate  tree node
    private TreeNode doubleWithRightChild(TreeNode k1)
    {
        k1.right = singleRotateLeft( k1.right );
        return singleRotateRight( k1 );
    }
    /* Functions to count number of nodes */
    public int countNodes()
    {
        return countNodes(root);
    }
    private int countNodes(TreeNode r)
    {
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    /* Functions to search for an element */
    public boolean search(String val)
    {
        return search(root, val);
    }
    private boolean search(TreeNode r, String val)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            String rval = ((Martyrs)r.value).getName();
            if (val .compareToIgnoreCase(rval) <0)
                r = r.left;
            else if (val .compareToIgnoreCase(rval) >0)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
    public boolean search(Martyrs martyrs)// search by name and check equality of objects
    {
        return search(root,martyrs);
    }
    private boolean search(TreeNode r, Martyrs martyrs)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            String rval = ((Martyrs)r.value).getName();
            String martyrsVal = martyrs.getName();
            if (martyrsVal .compareToIgnoreCase(rval) <0)
                r = r.left;
            else if (martyrsVal .compareToIgnoreCase(rval) >0)
                r = r.right;
            else
            {
                if(((Martyrs)r.value).getLocation().equalsIgnoreCase(martyrsVal) &&
                        ((Martyrs)r.value).getAge().equalsIgnoreCase(martyrs.getAge()) &&
                        ((Martyrs)r.value).getDateOfDeath().equalsIgnoreCase(martyrs.getDateOfDeath()) &&
                        ((Martyrs)r.value).getGender()== martyrs.getGender()) {
                    found = true;
                    break;
                }else {
                    return false;
                }
            }
            found = search(r, martyrsVal);
        }
        return found;
    }
    public boolean search_update_byName(Martyrs oldMartyr,Martyrs newMartyr)//search and update the node information by  Name
    {
        return search_update_byName(root, oldMartyr,newMartyr);
    }
    private boolean search_update_byName(TreeNode treeNode, Martyrs oldMartyr, Martyrs newMartyr) { // if return was false --> the martyr is not founded
        boolean found = false;
        while ((treeNode != null) && !found)
        {
            String  rval = ((Martyrs)treeNode.value).getName();
            String oldVal = oldMartyr.getName();
            if (oldVal .compareToIgnoreCase( rval) <0)
                treeNode = treeNode.left;
            else if (oldVal .compareToIgnoreCase( rval) >0)
                treeNode = treeNode.right;
            else
            { // do compare method for compare new and old info of martyr
                if (compare(oldMartyr,((Martyrs)treeNode.value))==5) {
                   // if (((Martyrs) treeNode.value).getName().equals(newVal.getName())) {
                        found = true;
                        ((Martyrs) treeNode.value).setName(newMartyr.getName());
                        ((Martyrs) treeNode.value).setAge(newMartyr.getAge());
                        ((Martyrs) treeNode.value).setLocation(newMartyr.getLocation());
                        ((Martyrs) treeNode.value).setDateOfDeath(newMartyr.getDateOfDeath());
                        ((Martyrs) treeNode.value).setGender(newMartyr.getGender());
                        break;
                   /* } else{
                        insert(newVal);
                        return false;
                    }*/
                }
            }
            found = search_update_byName(treeNode, oldMartyr,newMartyr);
        }
        return found;
    }
    static int compare (Martyrs c1,Martyrs c2){
        int res =0;
        if (c1.getName().equalsIgnoreCase(c2.getName())) {
            res++;
        }
        if (c1.getAge().equalsIgnoreCase(c2.getAge())) {
            res++;
        }
        if (c1.getDateOfDeath().equalsIgnoreCase(c2.getDateOfDeath())) {
            res++;
        }
        if (c1.getLocation().equalsIgnoreCase(c2.getLocation())) {
            res++;
        }
        if (c1.getGender()==(c2.getGender())) {
            res++;
        }
        return res;
    }


    LinkedList linkedList = new LinkedList();
    public LinkedList find(String s)
    {
      //  linkedList =null;
        linkedList=find(root , s);
        return linkedList;
    }
    private LinkedList find(TreeNode r , String s)
    {
        if (s!=null) {
            if (r != null) {
                find(r.left, s);
                if (((Martyrs) r.value).getName().contains(s)) {
                    System.out.println(r.value + " ");
                    linkedList.addLast(r.value);
                }
                find(r.right, s);
            }
        }else{
            if (r != null) {
                find(r.left, null);
                    System.out.println(r.value + " ");
                    linkedList.addLast(r.value);
                find(r.right, null);
            }
        }
        return linkedList;
    }
    /* Function for inorder traversal */
    public void inorder()
    {
        inorder(root);
    }
    private void inorder(TreeNode r)
    {
        if (r != null)
        {
            inorder(r.left);
            System.out.println(r.value +" ");
            inorder(r.right);
        }
    }
    /* Function for preorder traversal */
    public void preorder()
    {
        preorder(root);
    }
    private void preorder(TreeNode r)
    {
        if (r != null)
        {
            System.out.print(r.value +" ");
            preorder(r.left);
            preorder(r.right);
        }
    }
    /* Function for postorder traversal */
    public void postorder()
    {
        postorder(root);
    }
    private void postorder(TreeNode r)
    {
        if (r != null)
        {
            postorder(r.left);
            postorder(r.right);
            System.out.print(r.value +" ");
        }
    }

    public  void traverseLevelOrder(TreeNode root, TreeNode visitor) {
        if (root == null) {
            return;
        }

        Queue queue = new Queue();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.dequeue();
            System.out.println(visitor.value);

            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
    }
}

/* Class AVL Tree Test */
/*
public class AVLTreeTest
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        */
/* Creating object of AVLTree *//*

        AVLTree avlt = new AVLTree();

        System.out.println("AVLTree Tree Test\n");
        char ch;
        */
/*  Perform tree operations  *//*

        do
        {
            System.out.println("\nAVLTree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. count nodes");
            System.out.println("4. check empty");
            System.out.println("5. clear tree");

            int choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Enter integer element to insert");
                    avlt.insert( scan.nextInt() );
                    break;
                case 2 :
                    System.out.println("Enter integer element to search");
                    System.out.println("Search result : "+ avlt.search( scan.nextInt() ));
                    break;
                case 3 :
                    System.out.println("Nodes = "+ avlt.countNodes());
                    break;
                case 4 :
                    System.out.println("Empty status = "+ avlt.isEmpty());
                    break;
                case 5 :
                    System.out.println("\nTree Cleared");
                    avlt.makeEmpty();
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            */
/*  Display tree  *//*

            System.out.print("\nPost order : ");
            avlt.postorder();
            System.out.print("\nPre order : ");
            avlt.preorder();
            System.out.print("\nIn order : ");
            avlt.inorder();

            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }
}*/
