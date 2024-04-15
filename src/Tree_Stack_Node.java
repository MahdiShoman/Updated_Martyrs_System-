public class Tree_Stack_Node {

    Object value;
    Tree_Stack_Node left;
    Tree_Stack_Node right;

    int height;
    Stack stack ;

    public Tree_Stack_Node(Object value) {
        this.value = value;
        height=0;
        left=null;
        right=null;
        stack=new Stack();
    }


}
