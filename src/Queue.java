public class Queue {

    int size;
    LinkedList l ;

    Queue(){
        l = new LinkedList();
    }

    boolean isEmpty(){
        return l.isEmpty();
    }

    void enqueue(Object x){
        l.addLast(x);
    }
    Object dequeue(){

        if(isEmpty()){
            System.out.println("Empty");
            return null;
        }


        Object o = l.getFirst();
        l.removeFirst();
        return o  ;

    }

    void printQueue(){
        l.displaylist();
    }


}