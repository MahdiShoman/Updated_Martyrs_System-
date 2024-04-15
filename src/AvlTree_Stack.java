class AvlTree_Stack {
     Tree_Stack_Node root;
    static Tree_Stack_Node maxDateHaveMartyr; // from Stack size
    public AvlTree_Stack()
    {
        root = null;
    }
    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return root == null;
    }
    /* Make the tree logically empty */
    public void makeEmpty()
    {
        root = null;
    }
    /* Function to insert data */

    /* Function to get height of node */
    private int height(Tree_Stack_Node t )
    {
        return t == null ? -1 : t.height;
    }
    /* Function to max of left/right node */
    private int max(int lhs, int rhs)
    {
        return Math.max(lhs, rhs);
    }
     int getHeight_ofRoot(){
        return root.height;
    }

    /*
     * check to rotate in the  insert methode
     * */
  /*  public void insert_ByDate(Martyrs data) //insert martyr by Date // for second  AVL
    {
        root = insert_ByDate(data, root);
    }
    *//* Function to insert data recursively *//*
    private Tree_Stack_Node insert_ByDate(Martyrs x, Tree_Stack_Node t)
    {
        if (t == null) {// stop case
            t = new Tree_Stack_Node(x.getDateOfDeath());
      //      t.stack.push(x);
        }
        else if (x.getDateOfDeath() .compareToIgnoreCase(((Martyrs) t.value).getDateOfDeath())<0)// to left of Tree_Stack_Node
        {
            t.left = insert_ByDate( x, t.left );
            if( height( t.left ) - height( t.right ) == 2 )// will be between -1 & 1 // check it
                if( x.getDateOfDeath() .compareToIgnoreCase(((Martyrs) t.left.value).getDateOfDeath())<0 )// left rotate
                    t = rotateWithLeftChild( t );
                else
                    t = doubleWithLeftChild( t );
        }
        else if( x .getDateOfDeath() .compareToIgnoreCase(((Martyrs) t.value).getDateOfDeath())>0 ) // to right of Tree_Stack_Node
        {
            t.right = insert_ByDate( x, t.right );
            if( height( t.right ) - height( t.left ) == 2 )
                if( x.getDateOfDeath() .compareToIgnoreCase( ((Martyrs) t.right.value).getDateOfDeath())>0 )// right rotate
                    t = rotateWithRightChild( t );
                else
                    t = doubleWithRightChild( t );
        }
        // Duplicate; do nothing
        t.height = max( height( t.left ), height( t.right ) ) + 1; // check this statement
        return t;
    }*/
    boolean checkInsert = false;
    public boolean insert_Martyr_ByDate(Martyrs martyr) { // delete by Date // for second AVL // false --> cant find martyr
        root = insert_Martyr_ByDate(martyr, root);
        return checkInsert;
    }

    Tree_Stack_Node insert_Martyr_ByDate(Martyrs martyr, Tree_Stack_Node node) {

        if (node == null) {
            node=new Tree_Stack_Node(martyr.getDateOfDeath());
            return node;
        }
        //  the tree to the left or right depending on the key
       // System.out.println((((Martyrs) node.value).getDateOfDeath()));
        if (martyr.getDateOfDeath() .compareToIgnoreCase((((Martyrs) node.value).getDateOfDeath()))< 0) {//go to left
            node.left = insert_Martyr_ByDate(martyr, node.left);
        } else if (martyr.getDateOfDeath() .compareToIgnoreCase((((Martyrs) node.value).getDateOfDeath()))>0) { // go to right
            node.right = insert_Martyr_ByDate(martyr, node.right);
        }else if (martyr.getDateOfDeath() .compareToIgnoreCase((((Martyrs) node.value).getDateOfDeath()))==0) {// found it
            checkInsert=  insertObjectToStack(node,martyr);
        }

        return node;
    }
    private boolean insertObjectToStack(Tree_Stack_Node node , Martyrs newMartyr){
        if (!node.stack.isEmpty()) {
            boolean found = false;
            Stack temp= new Stack();
            while (!node.stack.isEmpty()){
                Martyrs tempMartyr = (Martyrs) node.stack.pop();
                if(tempMartyr.getName().equalsIgnoreCase(newMartyr.getName())) {//to decrease the time of check
                    if (compare(newMartyr, tempMartyr) == 5) {//check if this obj is actually founded
                       return false;
                    }
                }
                temp.push(tempMartyr);
            }
           if(!found){
               temp.push(newMartyr);
           }
            node.stack=temp;
            if (node.stack.size>maxDateHaveMartyr.stack.size){
                maxDateHaveMartyr=node;
            }
            return true;
        }else{
            node.stack.push(newMartyr);
            return true;
        }
    }


    public void delete_ByDate(String dateOfMartyr) { // delete by Date // for second AVL
        root = delete_ByDate(dateOfMartyr, root);
    }

    Tree_Stack_Node delete_ByDate(String dateOfMartyr, Tree_Stack_Node node) {

        if (node == null) {
            return null;
        }

        if (dateOfMartyr .compareToIgnoreCase(((Martyrs) node.value).getDateOfDeath())< 0) { // in the left of the node
            node.left = delete_ByDate(dateOfMartyr, node.left);
        } else if (dateOfMartyr .compareToIgnoreCase(((Martyrs) node.value).getDateOfDeath())>0) {// in the right of the node
            node.right = delete_ByDate(dateOfMartyr, node.right);
        }

        //  has no children
        else if (node.left == null && node.right == null) { // is the node to be deleted
            node = null;
        }

        //  has  one child then  replaced node by  single child
        else if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        }

        //  has two children
        else {
            deleteNodeWithTwoChildren_byDate(node);
        }

        return node;
    }

    boolean checkDelete = false;
    public boolean delete_Martyr_ByDate(Martyrs martyr) { // delete by Date // for second AVL // false --> cant find martyr
        root = delete_Martyr_ByDate(martyr, root);
        return checkDelete;
    }

    Tree_Stack_Node delete_Martyr_ByDate(Martyrs martyr, Tree_Stack_Node node) {
        // No node at current position --> go up the recursion
        if (node == null) {
            return null;
        }
        // Traverse the tree to the left or right depending on the key
        if (martyr.getDateOfDeath() .compareToIgnoreCase(((Martyrs) node.value).getDateOfDeath())< 0) {//go to left
            node.left = delete_Martyr_ByDate(martyr, node.left);
        } else if (martyr.getDateOfDeath() .compareToIgnoreCase(((Martyrs) node.value).getDateOfDeath())>0) { // go to right
            node.right = delete_Martyr_ByDate(martyr, node.right);
        }else {
          checkDelete=  deleteObjectFromStack(node,martyr);
        }

        return node;
    }
    private boolean deleteObjectFromStack (Tree_Stack_Node node , Martyrs oldMartyr){
        if (!node.stack.isEmpty()) {
            boolean found = false;
            Stack temp= new Stack();
            while (!node.stack.isEmpty()){
                Martyrs tempMartyr = (Martyrs) node.stack.pop();
                if(tempMartyr.getName().equalsIgnoreCase(oldMartyr.getName())) {//to decrease the time of check
                    if (compare(oldMartyr, tempMartyr) == 5) {//check if this obj i hava to update
                        found = true;
                        continue;
                    }
                }
                temp.push(tempMartyr);
            }
            node.stack=temp;
            if (node.stack.size>maxDateHaveMartyr.stack.size){
                maxDateHaveMartyr=node;
            }
            return found;
        }
        return false;

    }

    private void deleteNodeWithTwoChildren_byDate(Tree_Stack_Node node) {
        // Find minimum  of the right subtree
        Tree_Stack_Node minimumRightNode = findMinimum(node.right); // minimum from right

        node.value = minimumRightNode.value;

        // Delete mini
        node.right = delete_ByDate((String) minimumRightNode.value, node.right);
    }
    private Tree_Stack_Node findMinimum(Tree_Stack_Node node) {
        while (node.left != null) {
            node = node.left; // is the minimum node
        }
        return node;
    }


    // Rotate left
    private Tree_Stack_Node singleRotateLeft(Tree_Stack_Node k2)
    {
        Tree_Stack_Node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    // Rotate   right
    private Tree_Stack_Node singleRotateRight(Tree_Stack_Node k1)
    {
        Tree_Stack_Node k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

     /* Double rotate binary tree node: first left child
     with its right child; then node k3 with new left child */

    private Tree_Stack_Node doubleWithLeftChild(Tree_Stack_Node k3)
    {
        k3.left = singleRotateRight( k3.left );
        return singleRotateLeft( k3 );
    }

      /* Double rotate binary tree node: first right child
      with its left child; then node k1 with new right child*/

    private Tree_Stack_Node doubleWithRightChild(Tree_Stack_Node k1)
    {
        k1.right = singleRotateLeft( k1.right );
        return singleRotateRight( k1 );
    }
    // Functions to count number of nodes
   /* public int countNodes()
    {
        return countNodes(root);
    }
    private int countNodes(Tree_Stack_Node r)
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
    }*/
    /* Functions to search for an element */
    public boolean search(String val)
    {
        return search(root, val);
    }
    private boolean search(Tree_Stack_Node r, String val)
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



    public boolean search_update_Martyr_byDate(Martyrs oldVal, Martyrs newVal) //search and update the node information by  Date // val is the date
    {
        return search_update_Martyr_byDate(root, oldVal,newVal);
    }
    private boolean search_update_Martyr_byDate(Tree_Stack_Node node, Martyrs oldVal, Martyrs newMartyr) { // if return was false --> the martyr is not founded
        boolean found = false;
        while ((node != null) && !found) {
            String  rval = ((Martyrs)node.value).getDateOfDeath(); // acutual martyr
            String  oval = oldVal.getDateOfDeath();
            if (oval .compareToIgnoreCase((String) rval) <0)
                node = node.left;
            else if (oval .compareToIgnoreCase((String) rval) >0)
                node = node.right;
            else
            {
                if(updateObjectFromStack(node,oldVal,newMartyr)) {
                    found = true;
                    break;
                }else
                    return false;
            }
            found = search_update_Martyr_byDate(node, oldVal,newMartyr);
        }
        return found;
    }

     private boolean updateObjectFromStack (Tree_Stack_Node node , Martyrs oldMartyr, Martyrs newMartyr){
        if (!node.stack.isEmpty()) {
            boolean found = false;
            Stack temp= new Stack();
            while (!node.stack.isEmpty()){
                Martyrs tempMartyr = (Martyrs) node.stack.pop();
                if(tempMartyr.getName().equalsIgnoreCase(oldMartyr.getName())) {//to decrease the time of check
                    if (compare(oldMartyr, tempMartyr) == 5) {//check if this obj i hava to update it
                        if (!tempMartyr.getDateOfDeath().equalsIgnoreCase(newMartyr.getDateOfDeath())){// if it is
                            insert_Martyr_ByDate(newMartyr);
                            continue;
                        }
                        found = true;
                        tempMartyr.setName(newMartyr.getName());
                        tempMartyr.setAge(newMartyr.getAge());
                        tempMartyr.setLocation(newMartyr.getLocation());
                        tempMartyr.setGender(newMartyr.getGender());
                        break;
                    }
                }
                temp.push(tempMartyr);
            }
            node.stack=temp;
            if (node.stack.size>maxDateHaveMartyr.stack.size){
                maxDateHaveMartyr=node;
            }
            return found;
        }
        return false;

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
    private LinkedList find(Tree_Stack_Node r , String s)
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
    private void inorder(Tree_Stack_Node r)
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
    private void preorder(Tree_Stack_Node r)
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
    private void postorder(Tree_Stack_Node r)
    {
        if (r != null)
        {
            postorder(r.left);
            postorder(r.right);
            System.out.print(r.value +" ");
        }
    }
}
