
public class DoubleLinkedListCircular
{
    DoubleNode first;
    DoubleNode last;


    public int count;

    public DoubleLinkedListCircular() {
        first=last=null;

    }

    public Object getFirst() {
        if (count != 0)
            return first.element;
        else
            return null;
    }

    public Object getLast() {
        if (count != 0)
            return last.element;
        else
            return null;
    }

    public void addFirst(String x) {
        if (count == 0) {
            DoubleNode node=new DoubleNode(x);
            // first=last
            node.next=node.previous =node;
            first=node;
            last=first.previous;
        }
        else {
            DoubleNode temp = new DoubleNode(x);
            temp.next = first;
            first.previous = temp;
            first = temp;
            first.previous=last;
            last.next=first;
        }
        count++;
    }

    public void addLast(String x) {
        if (count == 0){
            DoubleNode node=new DoubleNode(x);
            // first=last
            node.next=node.previous =node;
            first=node;
            last=first.previous;
        }
        else {
            DoubleNode temp = new DoubleNode(x);
            last.next = temp;
            temp.previous = last;
            last = temp;
            //from me
            last.next = first;
            first.previous=last;
        }
        count++;
    }

    public void add(String x, int index) {
        if (index == 0)
            addFirst(x);
        else {
            if (index >= count)
                addLast(x);
            else {
                DoubleNode temp = new DoubleNode(x);
                DoubleNode current = first;
                for (int i = 1; i < index - 1; i++) {
                    current = current.next;
                }
                temp.next = current.next;
                current.next.previous = temp;
                temp.previous = current;
                current.next = temp;
                count++;

            }
        }
    }

    public boolean removeFirst() {
        if (count == 0)
            return false;
        else {
            if (count == 1)
                first = last = null;
            else {
                first = first.next;
                first.previous = last;
                last.next=first;
            }
            count--;
            return true;
        }
    }

    public boolean removeLast() {
        if (count == 0)
            return false;
        else {
            if (count == 1)
                first = last = null;
            else {
                DoubleNode current = first;
                for (int i = 0; i < count - 2; i++)
                    current = current.next;
                last = current;
                current.next.previous = null;
                current.next = first;
                first.previous=last;
            }
            count--;
            return true;

        }
    }

    public boolean remove(int index) {
        if (count == 0)
            return false;
        else if (count == 1) {
            first = last = null;
            return true;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == count - 1)
            return removeLast();
        else if (index < 0 || index > count)
            return false;
        else {
            DoubleNode current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = (current.next).next;
            count--;
            return true;
        }
    }

    public boolean removeValue(Object x) {
        if (count == 0)
            return false;
        else {
            if (first.element.equals(x))
                return removeFirst();
            else {
                DoubleNode previous = first;
                DoubleNode current = first.next;
                int c = 0 ;
                while (current != null && !current.element.equals(x)) {
                    previous = current;
                    current = current.next;
                }
                if (current != null) {
                    previous.next = current.next;
                    count--;
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void displayList() {
        if (!isEmpty()) {
            DoubleNode current = first;
            while (current != first.previous) {
                System.out.print(current.element);
                System.out.println(" ");
                current = current.next;
            }
        } else
            System.out.println("the list is empty");
    }

    public void removeAll(LinkedList list2) {

        if (!list2.isEmpty() && !isEmpty()) {

            Node current = list2.first;
            while (current != null) {
                search_delete(current.element);
                current = current.next;
            }

        } else
            System.out.println("the list is empty");
    }


DoubleNode addLocation(String location){
    if (first==null) {//check if the first is null
        DoubleNode node=new DoubleNode(location);
       // first=last
        node.next=node.previous =node;
        first=last=node;
        //last=first.previous;
        count++;
        return node;
    }else {
        DoubleNode pre =first;
        DoubleNode current = first.next;
        if(((String) pre.element).compareToIgnoreCase((location))>=0){//check the first node
            DoubleNode doubleNode = new DoubleNode(location);
            doubleNode.next= first;
            first.setPrevious(doubleNode);
            doubleNode.previous=last;
            last.next=doubleNode;
            first=doubleNode;
            count++;
            return doubleNode;
        }else {
            if (current!= first.previous) {
                while (current != first.previous) {//check the pre after this loop
                    int res = ((String) current.element).compareToIgnoreCase((location));
                    if (res >= 0 && ((String) pre.element).compareToIgnoreCase((location)) <= 0) {
                        DoubleNode node = new DoubleNode(location);
                        node.next = current;
                        current.setPrevious(node);
                        node.setPrevious(pre);
                        pre.setNext(node);
                        count++;
                        return node;
                      //  break;
                    }
                    pre = current;
                    current = current.next;
                }
               // System.out.println(first.element+"++");
            }else
            if (current == first.previous ) {// if the value grater than last node
                DoubleNode node = new DoubleNode(location);
               // System.out.println("is the last");
                last.next = node;
                node.setPrevious(last);
                node.setNext(first);
                first.setPrevious(node);
                last=node;
              //  System.out.println(MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first.element+"++");
                count++;
                return node;

            }
        }
    }
    return null;
}


    void addList(Object city,Martyrs martyrs){
        DoubleNode current = first;
        while(current != first.previous){
            if(current.element.equals(city)){
                martyrs.add2(martyrs,current);
            }
            current=current.next;

        }
    }

    public void search_delete(Object x ) {

        DoubleNode previous = first;
        DoubleNode current = first.next;
        while (current != first.previous && !current.element.equals(x)) {
            previous = current;
            current = current.next;
        }
        if (current.next != first){
            previous.next = current.next;
            count--;
        }
    }
    public Object search_return(Object x ) {

        DoubleNode current = first;
        for (int i = 0; i < count ; i++) {
            if (current.element.equals(x)) {
                System.out.println("yes");
               return (current);
            }
            current = current.next;
        }
        System.out.println("no");
        return ("there is no city has name :"+x);
    }

    public  boolean search(Object x ) {

        DoubleNode current = first;
        if (current == null) {
            return false;
        }
        while(current != first.previous){
            if ((current.element).equals(x) ){
                return true;
            }
            current = current.next;

        }
        return false;
    }



    public void displaylistFull(){ //to display the location and his list of martyr
        if (!isEmpty()){
            DoubleNode current = first;
            while (current != first.previous){
                System.out.println(current.element);
                displaylistMartyr(current);//display list of martyr
                System.out.print(" ");
                current=current.next;
            }
        }else
            System.out.println("the list is empty");
    }

    public void displaylistMartyr(DoubleNode city){
        Node current = city.headerList;
        if(current!=null) {
            while (current != null) {
                System.out.print(current.element);
                System.out.println(" ");
                current = current.next;
            }
        }else
            System.out.println("there is no martyr in" + city.element +"city");


    }
    public void search_displaylistOfMartyrs(Node city){ //to display the location and his list of martyr
        if (!isEmpty()){
            DoubleNode current = first;
            while (current!=null){
                if(current.element.equals(city)){
                    System.out.println(current.element);
                    displaylistMartyr(current);//display list of martyr
                    System.out.print(" ");
                }
                current=current.next;
            }
        }else
            System.out.println("the list is empty");
    }



}