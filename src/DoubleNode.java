public class DoubleNode {
    Object element;
    DoubleNode next;
    DoubleNode previous;
    Node headerList;
    AvlTree avlNames;
    AvlTree_Stack avlDates;

    public DoubleNode(){
        next=null;
        previous=null;
        headerList=null;
    }
    public DoubleNode(Object x){
        element=x;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public DoubleNode getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode previous) {
        this.previous = previous;
    }

    public Node getHeaderList() {
        return headerList;
    }

    public void setHeaderList(Node headerList) {
        this.headerList = headerList;
    }
}
