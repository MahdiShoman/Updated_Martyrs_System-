public class TreeNode {
    Object value;
    TreeNode left;
    TreeNode right;

    int height;

    public TreeNode(){
        this.value = null;
        height=0;
        left=null;
        right=null;
    }
    public TreeNode(Object value) {
        this.value = value;
        height=0;
        left=null;
        right=null;
    }
}
