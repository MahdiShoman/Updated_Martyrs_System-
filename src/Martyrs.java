import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.text.SimpleDateFormat;

public class Martyrs  {
    private String name;
    private String age;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String dateOfDeath;
    private char gender;
    javafx.scene.control.ComboBox<Object> locationSearch = new javafx.scene.control.ComboBox<>();

    public Martyrs(){

    }
    public Martyrs(String name, String age,  String dateOfDeath, char gender,String location){
        this.name=name;
        this.age=age;
        this.dateOfDeath=dateOfDeath;
        this.gender=gender;
        this.location=location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
    static String locationName ;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    void insert(String cityName ,Martyrs martyr){
      //  System.out.println(cityName);
        if (cityName!=null) {
            Object resultOfSearch = search_return_avlNamesOfDoubleNode(cityName);
            Object resultOfSearch_date = search_return_avlDatesOfDoubleNode(cityName);
            if (resultOfSearch != (cityName) && resultOfSearch_date!= cityName) { // if the city is not founded
                // now we need to find if the martyr is in or not ,(we need to loob the list),
                if (((AvlTree) resultOfSearch).search(martyr)) {// check equality between new martyr and others martyr in the tree
                    button.alertWarning.setTitle("Warning ");
                    button.alertWarning.setHeaderText(" the martyr is on the list actually >: ");
                    button.alertWarning.setContentText(" there are martyr has same name and location ");
                    button.alertWarning.showAndWait();
                } else {
                    ((AvlTree) resultOfSearch).insert(martyr);// insert martyr in the first AVL
                    MainScreen.avlTree.insert(martyr);
                    ((AvlTree_Stack) resultOfSearch_date).insert_Martyr_ByDate(martyr); // insert martyr in the second AVL
                    button.alertInformation.setTitle("Information  ");
                    button.alertInformation.setContentText("the martyr has been added !!");
                    button.alertInformation.setHeaderText("Don't forget save your work before exit ");
                    button.alertInformation.showAndWait();
                }

          /*  boolean founded = false;
            Node current =(Node) resultOfSearch;
            while (current.next!=null){
                if (current.element.equals(martyr)) {
                    founded = true;
                    button.alertWarning.setTitle("Warning ");
                    button.alertWarning.setHeaderText(" the martyr is on the list actually >: ");
                    button.alertWarning.showAndWait();
                    break;
                }
                current = current.next;
            }
           */

            }else {
                button.alertWarning.setTitle("Warning ");
                button.alertWarning.setHeaderText(" can't found this location ");
                button.alertWarning.showAndWait();
            }
        }else {//else print in the lable that is the city name is not exist
            button.alertWarning.setTitle("Warning ");
            button.alertWarning.setHeaderText(" there is no location name ,  please add it ");
            button.alertWarning.showAndWait();
        }


    } //take the header of the city name  and insert

    void update(Martyrs oldMartyr,Martyrs newMartyr ){//in update take the header of city and and wolk to reach the obj you wont and update

        Object resultOfSearch = search_return_avlNamesOfDoubleNode(oldMartyr.getLocation());// get the First avl
        Object resultOfSearch_date = search_return_avlDatesOfDoubleNode(oldMartyr.getLocation()); // get the second avl
       // Martyrs m = new Martyrs(name,age,dateOfDeath,gender);
        if(!(resultOfSearch).equals(oldMartyr.getName()) ){ // if  resultOfSearch != city Name  that's mean that is founded the city
            // now we need to find if the martyr is in or not ,(we need to loob the list),
           // && ((AvlTree_Stack)resultOfSearch_date).search_update_Martyr_byDate(oldMartyr, newMartyr)
            if (((AvlTree)resultOfSearch).search_update_byName(oldMartyr, newMartyr)  ) {
               MainScreen.avlTree.search_update_byName(oldMartyr, newMartyr);
                button.alertInformation.setTitle("Information  ");
                button.alertInformation.setContentText("the martyr has been updated !!");
                button.alertInformation.setHeaderText("Don't forget save your work before exit ");
                button.alertInformation.showAndWait();
            }else {
                button.alertWarning.setTitle("Warning ");
                button.alertWarning.setHeaderText(" the martyr is not on the list  >: ");
                button.alertWarning.showAndWait();
            }


           /* Node current =(Node) resultOfSearch;
            while (current!=null){
                if (((Martyrs)current.element).getName().equalsIgnoreCase(name)) {
                   // ((Martyrs)current.element).setName(name);
                    ((Martyrs)current.element).setAge(age);
                    ((Martyrs)current.element).setLocation(cityName);
                    ((Martyrs)current.element).setDateOfDeath(dateOfDeath);
                    ((Martyrs)current.element).setGender(gender);

                    break;
                }
                current = current.next;
            }

        }else{
            Martyrs martyrs = new Martyrs(name,age,dateOfDeath,gender);
            MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.addLast(cityName);
            System.out.println(cityName);
            martyrs.setLocation(cityName);
            MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.addList(cityName,martyrs);}*/
        }

    }
    void delete (String cityName , Martyrs martyr){
        Object resultOfSearch = search_return_avlNamesOfDoubleNode(cityName);
        Object resultOfSearch_date = search_return_avlDatesOfDoubleNode(cityName);
        if(!resultOfSearch.equals(cityName)){
            // now we need to find if the martyr is in or not ,(we need to loob the list),
             ((AvlTree)resultOfSearch).delete_byNames(martyr.getName());
             MainScreen.avlTree.delete_byNames(martyr.getName());
            ((AvlTree_Stack)resultOfSearch_date).delete_Martyr_ByDate(martyr);
                button.alertInformation.setTitle("Information  ");
                button.alertInformation.setContentText("the martyr has been updated !!");
                button.alertInformation.setHeaderText("Don't forget save your work before exit ");
                button.alertInformation.showAndWait();
             {
                button.alertWarning.setTitle("Warning ");
                button.alertWarning.setHeaderText(" the martyr is not on the list  >: ");
                button.alertWarning.showAndWait();
            }
       /*     Node previous = (Node) resultOfSearch;
            Node current = ((Node) resultOfSearch).next;
            while (current != null && !((Martyrs)current.element).getName().equals(martyr.getName())) {
                previous = current;
                current = current.next;
            }
            if (current != null) {
                previous.next = current.next;
               MainScreen.DOUBLE_LINKED_LIST_CIRCULAR. count--;
            }*/

        }
    }

    //in delete take the header and wolk to reach the obj you wont and delete it // use the hint of methode delete in linkedlist
    public Object search_return_avlNamesOfDoubleNode(Object x ) {//take the name of city and return the headerList of it's Node
                                                                // i can check the return uses the .getsours() == String
                                                                // or if it equals x
        DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
      /*  if (first==null) {
            first=(DoubleNode) x;
        }*/
            while (current != first.previous) {
                if (((String) current.element).equalsIgnoreCase((String) x)) {
                    //System.out.println("yes");
           /*     System.out.println(current.element);
                System.out.println(current.avlNames);*/
                    return (current.avlNames);
                }
                current = current.next;

            }

        //System.out.println("no");
       // return ("there is no city has this name");
        return x;
    }

    public Object search_return_avlDatesOfDoubleNode(Object x ) {//take the name of city and return the headerList of it's Node
        // i can check the return uses the .getsours() == String
        // or if it equals x
        DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        while(current != first.previous) {
            if (((String)current.element).equalsIgnoreCase((String) x)) {
                //System.out.println("yes");
                return (current.avlDates);
            }
            current = current.next;

        }
        //System.out.println("no");
        // return ("there is no city has this name");
        return x;
    }

    @Override
    public String toString() {
        return "Martyr{" +
                "name='" + name + '\'' +
                ", age=" + age +", location="+location+

                ", dateOfDeath=" + dateOfDeath +
                ", gender=" + gender +
                '}';
    }


    BorderPane borderMartyr = new BorderPane();
    BorderPane topBorder = new BorderPane();
    HBox topHbox = new HBox();
    HBox top_CenterHbox = new HBox();
    HBox top_BottomHbox = new HBox();
    GridPane bottomGridPane =new GridPane();
    Buttons button = new Buttons();
    Label showAllMartyrLabel = new Label("if you wont to show all martyr click on : ");
    Button showAllMartyrButton =new Button("show All Martyr");
    Label showSearchedMartyrLabel = new Label("if you wont to show the martyrs of chosen location  click on the box and chose  : ");
    ComboBox<Object> comboBoxForLocations = new ComboBox<>();
    Button conformButton = new Button("Conform");

    TextField locationSearchBox = new TextField();
    Button searchfButton = new Button("Search");
    TextField martyrSearchBox = new TextField();

    Button updateButton=new Button("update");
    public static  TableView<Object> table = new TableView<>();
    BorderPane martyrs (){
        //  the top of border have the search  and button can show all the martyr
        //the center have table view
        //  the bottom have (insert , update, delete ) and special case ware can show the martyr of searched location

        TableColumn<Object, String> martyrNameColumn = new TableColumn<>("Martyr Name");
        // catch column with obj
        martyrNameColumn.setCellValueFactory(data -> new SimpleStringProperty(((Martyrs)data.getValue()).getName()));
        martyrNameColumn.setMinWidth(120);

        TableColumn<Object, String> martyrAgeColumn = new TableColumn<>("Martyr Age");
        // catch column with obj
        martyrAgeColumn.setCellValueFactory(data -> new SimpleStringProperty(((Martyrs)data.getValue()).getAge()));
        martyrAgeColumn.setMinWidth(120);

        TableColumn<Object, String> cityCol = new TableColumn<>("City Name");
        // catch column with obj
        cityCol.setCellValueFactory(data -> new SimpleStringProperty(((Martyrs)data.getValue()).getLocation()));
        cityCol.setMinWidth(120);


        TableColumn<Object, String> martyrDeathDateColumn = new TableColumn<>("Martyr Death Date");
        // catch column with obj
        martyrDeathDateColumn.setCellValueFactory(data -> new SimpleStringProperty((((Martyrs)data.getValue()).getDateOfDeath())+""));
        martyrDeathDateColumn.setMinWidth(120);

        TableColumn<Object, String> martyrGenderColumn = new TableColumn<>("Martyr Gender");
        // catch column with obj
        martyrGenderColumn.setCellValueFactory(data -> new SimpleStringProperty(((Martyrs)data.getValue()).getGender()+""));
        martyrGenderColumn.setMinWidth(220);
        // Add the columns to the table
        //martyrNameColumn,martyrAgeColumn,martyrDeathDateColumn,martyrGenderColumn
        table.getColumns().addAll(martyrNameColumn,martyrAgeColumn,martyrDeathDateColumn,martyrGenderColumn,cityCol);
      //  table.getColumns().add(cityCol);


        showAllMartyrButton.setOnAction(actionEvent -> {
            setData(table);
        });

        // Set up the search box and button
      /*  searchfButton.setOnAction(actionEvent -> {//search of location
            table.getItems().clear();
            locationSearch.getItems().add("null");
            ArrayList<Object> a = new ArrayList<>();
            MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.displayList();
            DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            if (locationSearch.getValue()==null) {
                if (current!=null) {
                    while (current.next != first) { // O(n^2)
                        locationSearch.getItems().add((current.element));
                        a.add(current.element);
                        current = current.next;
                    }
                    locationSearch.setItems(FXCollections.observableArrayList(a));
                    table.getItems().clear();
                    // setAddedLocationData(table, locationSearch);
                }
            }
        });*/

        button.searchButton.setOnAction(event -> {
            table.getItems().clear();
          //  ArrayList<Object> a = new ArrayList<>();
            MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.displayList();
            AvlTree avlNames = null ;
            DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            locationSearch.getItems().add(" ");
            DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            if (first!=null) {
                while (current.next != first) { // O(n^2)
                   // locationSearch.getItems().add((current.element));
                    if (((String)current.element).equalsIgnoreCase(locationName) ) {
                        avlNames=current.avlNames;
                    }
                //    a.add(current.element);
                    current = current.next;
                }
            }
          //  locationSearch.setItems(FXCollections.observableArrayList(a));
            System.out.println("before");
            if (avlNames != null) {
                table.getItems().clear();
                setAddedData(table, martyrSearchBox.getText() , avlNames);
            }else {
                table.getItems().clear();
                setAddedData(table, martyrSearchBox.getText() , MainScreen.avlTree);
            }
            System.out.println("after");

        });

        if(!bottomGridPane.getChildren().contains(button.insertButton)){
        // top of top border

        GridPane topGridPane = new GridPane();
        martyrSearchBox.setPromptText("Enter Martyr Name");
        locationSearchBox.setPromptText("Enter Location Name");





        topGridPane.add(locationSearch,0,0);topGridPane.add(searchfButton,1,0);
        topGridPane.add(martyrSearchBox,0,1);topGridPane.add(button.searchButton,1,1);
        topHbox.getChildren().add(topGridPane);
        topHbox.setAlignment(Pos.CENTER);
        topBorder.setTop(topHbox);

        //center of top border

        top_CenterHbox.getChildren().addAll(showAllMartyrLabel,showAllMartyrButton);
        top_CenterHbox.setAlignment(Pos.CENTER);
        topBorder.setCenter(top_CenterHbox);

        // bottom of top border

        top_BottomHbox.getChildren().addAll(showSearchedMartyrLabel ,comboBoxForLocations,conformButton);
        borderMartyr.setTop(topBorder);


        borderMartyr.setCenter(table);

        button.labelInsert.setText("* If you want to insert a Martyr , click on ");
        bottomGridPane.add(button.labelInsert,0,0);bottomGridPane.add(button.insertButton,1,0);
        button.labelUpdate.setText(" * If you want to update the Data of Martyr  , click on  ");
        bottomGridPane.add(button.labelUpdate,0,3);bottomGridPane.add(updateButton,1,3);
        button.labelDelete.setText(" * If you want to delete a Martyr  , click on  ");
        bottomGridPane.add(button.labelDelete,0,6);bottomGridPane.add(button.deleteButton,1,6);
        HBox bottomHbox = new HBox();
        bottomHbox.getChildren().add(bottomGridPane);
        bottomHbox.setAlignment(Pos.CENTER);
        bottomHbox.setMinHeight(150);
        borderMartyr.setBottom(bottomHbox);
        }

        MainButtonsEvent mainButtonsEvent =new MainButtonsEvent();
        button.insertButton.setOnAction(mainButtonsEvent);
        updateButton.setOnAction(mainButtonsEvent);
        button.deleteButton.setOnAction(mainButtonsEvent);

        return borderMartyr;
    }
    private void setLocationData(TableView<String> table ){
        DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        System.out.println(current);
        while(current!=null){
            table.getItems().add((String) current.element);
            current=current.next;
        }
    }
    private void setAddedLocationData(TableView<Object> table , ComboBox location ){//add by location

       try {
           if (location.getValue()==" "){
               setData(table);
           }else {
               DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
               DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
               while (current != first.previous) {
                   if (((String) current.element).equalsIgnoreCase((String)location.getValue())) {
                       Node currentCheck = current.headerList;
                       while (currentCheck != null) {
                           table.getItems().add((Martyrs) currentCheck.element);
                           currentCheck = currentCheck.next;
                       }
                       break;
                   }
                   current = current.next;
               }
           }
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
       }

    private void setData(TableView<Object> table ){
        table.getItems().clear();
        if(!MainScreen.s.isEmpty()) {
            for (int i = 0; i < MainScreen.s.size(); i++) {
                table.getItems().add(MainScreen.s.get(i));
            }
        }
    }
    private void setAddedData(TableView<Object> table , String s ,AvlTree martyr){ // add by name on the table

        table.getItems().clear();
        LinkedList linkedList1 =martyr.find(s);
        Node current = linkedList1.first;
        while (current!=null){
            table.getItems().add(current.element);
            current=current.next;
        }
    /*    if(locationSearch.getValue()==null) {//if no location add

       *//*     DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            System.out.println(current.element);
            DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            while (current.next != first) {
                current.avlNames
                while (currentCheck != null) {

                    if (((Martyrs) currentCheck.value).getName().contains(s)) {
                        table.getItems().add((Martyrs) currentCheck.element);
                    }

                }
                current = current.next;
            }*//*
        }else {
            DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            System.out.println(current.element);
            DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            while (current.next != first) { // O(n^2)
                if(((String)current.element).equalsIgnoreCase((String) locationSearch.getValue())) {
                    Node currentCheck = current.headerList;
                    while (currentCheck != null) {
                        if (((Martyrs) currentCheck.element).getName().contains(s)) {
                            table.getItems().add((Martyrs) currentCheck.element);
                        }
                        currentCheck = currentCheck.next;
                    }
                    break;
                }
                current = current.next;
            }
        }*/

    }
    TextField insertNameField = new TextField();
    TextField insertAgeField = new TextField();
    TextField insertLocationField = new TextField();
    TextField insertDateField = new TextField();
    TextField insertGenderField = new TextField();

    TextField updateNameField = new TextField();
    TextField updateAgeField = new TextField();
    TextField updateLocationField = new TextField();
    TextField updateDateField = new TextField();
    TextField updateGenderField = new TextField();


    TextField deleteNameField = new TextField();
    TextField deleteAgeField = new TextField();
    TextField deleteLocationField = new TextField();
    TextField deleteDateField = new TextField();
    TextField deleteGenderField = new TextField();
    public void add (Martyrs x , DoubleNode header){

        if (header.headerList==null) {
            header.headerList=new Node(x);
        }else {
            Node pre = header.headerList;
            Node current = header.headerList.next;
            Node node = new Node(x);
            int res = ((Martyrs) current.element).getDateOfDeath().compareTo((x.getDateOfDeath()));//(x.getDateOfDeath().compareTo((((Martyrs) current.element).getDateOfDeath())))
            while (current.next != null) {
                if ((((Martyrs) current.element).getDateOfDeath().compareTo((x.getDateOfDeath()))) > 0) {
                    //addFirst(x);
                    node.next = current;
                    header.headerList=node;
                    //current.next=null;
                    // header=node;
                }else{

                }
                if (res <= 0) {
                    node.next = current.next.next;
                    current.next.next = node;
                    break;
                } else {
                    node.next = current.next;
                    current.next = node;
                }
                current = current.next;
            }
            if (res <= 0) {
                //   node.next = current.next.next;
                current.next = node;
                node.next = null;
            }
        }


    }
    void add2(Martyrs x , DoubleNode header){ // to add martyr according the date
        if (header.headerList==null) {//check if the header is null
            header.headerList=new Node(x);
        }else {
            Node pre = header.headerList;
            Node current = header.headerList.next;
            if(((Martyrs) pre.element).getDateOfDeath().compareTo((x.getDateOfDeath()))>=0){//check the first node
                Node node = new Node(x);
                node.next=pre;
                header.headerList=node;
            }else {
                while (current.next!= null) {//check the pre after this loop (O(n))
                    int res = ((Martyrs) current.element).getDateOfDeath().compareTo((x.getDateOfDeath()));
                    if (res >= 0 && ((Martyrs) pre.element).getDateOfDeath().compareTo((x.getDateOfDeath()))<0 ) {
                        Node node = new Node(x);
                        node.next=current;
                        pre.next=node;
                        break;
                    }

                    pre=current;
                    current = current.next;
                }
            }
        }
    }

    void add3( String cityName,Martyrs x ){
        DoubleNode header = new DoubleNode(cityName);
        DoubleNode current1 = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        while (current1 != first.previous) {
            if (((String)current1.element).equalsIgnoreCase(cityName)) {
                header=current1;
                break;
            }
            current1 = current1.next;
        }
        if (header.headerList==null) {//check if the headr is null
            header.headerList=new Node(x);
        }else {
            Node pre = header.headerList;
            Node current = header.headerList.next;
            if(((Martyrs) pre.element).getDateOfDeath().compareTo((x.getDateOfDeath()))>=0){//check the first node
                Node node = new Node(x);
                node.next=pre;
                header.headerList=node;
            }else {
                while (current.next!= null) {//check the pre after this loop
                    int res = ((Martyrs) current.element).getDateOfDeath().compareTo((x.getDateOfDeath()));
                    if (res >= 0 && ((Martyrs) pre.element).getDateOfDeath().compareTo((x.getDateOfDeath()))<0 ) {
                        Node node = new Node(x);
                        node.next=current;
                        pre.next=node;
                        break;
                    }
                /*    if (res <= 0) {
                            current.next=new Node(x);
                            break;
                    }*/
                   /* if (res == 0) {

                    }*/
                    pre=current;
                    current = current.next;
                }
            }
        }
    }
    public  class MainButtonsEvent implements EventHandler<ActionEvent> { // the handler button
        @Override
        public void handle(ActionEvent event) {

            if (event.getSource() ==  button.insertButton){
                // add method , check empty
                if (!bottomGridPane.getChildren().contains(insertNameField)){
                    Button conform = new Button("conform");


                    insertNameField.setPromptText("Insert Name");
                    insertAgeField.setPromptText("Insert Age");
                    insertLocationField.setPromptText("Insert Location");
                    insertDateField.setPromptText("Insert Date");
                    insertGenderField.setPromptText("Insert Gender");

                    bottomGridPane.add(insertNameField,0,1);bottomGridPane.add(insertAgeField,1,1);bottomGridPane.add(insertLocationField,2,1);
                    bottomGridPane.add(insertDateField,0,2);bottomGridPane.add(insertGenderField,1,2); bottomGridPane.add(conform,2,2);


                    MainScreen.avlTree.inorder();
                    System.out.println("___________________________");
                    conform.setOnAction(e ->{
                       Martyrs martyrs = new Martyrs(insertNameField.getText(),insertAgeField.getText(),(insertDateField.getText()),insertGenderField.getText().trim().charAt(0),insertLocationField.getText().trim());// to add  a location to list
                        if(insertNameField.getText().trim().isEmpty() ||insertAgeField.getText().trim().isEmpty()
                                || insertLocationField.getText().trim().isEmpty()||insertDateField.getText().trim().isEmpty() || insertGenderField.getText().trim().isEmpty()){
                            button.alertWarning.setTitle("Warning ");
                            button.alertWarning.setHeaderText(" you Should fill All field to insert");
                            button.alertWarning.show();
                        } else {
                            insert(insertLocationField.getText(),martyrs);
                            //add3(insertLocationField.getText(),martyrs);
                        }
                        MainScreen.avlTree.inorder();
                    });

                }else{

                    button.alertWarning.setTitle("Warning ");
                    button.alertWarning.setHeaderText("add the information to inset new martyr ");
                    button.alertWarning.showAndWait();
                }

           } else if (event.getSource() ==  updateButton) {
                button.alertWarning.setTitle("Warning ");
                button.alertWarning.setHeaderText("before update the martyr , search for him in the top to be sure of his data  ");
                button.alertWarning.show();
                MainScreen.avlTree.inorder();
                System.out.println("____________________________");
                if (!bottomGridPane.getChildren().contains(updateNameField)){
                    Button conformButton = new Button("conform");

                    updateNameField.setPromptText("update Name");
                    updateAgeField.setPromptText("update Age");
                    updateLocationField.setPromptText("update Location");
                    updateDateField.setPromptText("update Date");
                    updateGenderField.setPromptText("update Gender");

                    bottomGridPane.add(updateNameField,0,4);bottomGridPane.add(updateAgeField,1,4);bottomGridPane.add(updateLocationField,2,4);
                    bottomGridPane.add(updateDateField,0,5);bottomGridPane.add(updateGenderField,1,5); bottomGridPane.add(conformButton,2,5);


                conformButton.setOnAction(e -> {
                    if(!(updateNameField.getText().trim().isEmpty() ||updateAgeField.getText().trim().isEmpty()
                            || updateLocationField.getText().trim().isEmpty()||updateDateField.getText().trim().isEmpty() || updateGenderField.getText().trim().isEmpty())){
                        Martyrs newMartyr = new Martyrs(updateNameField.getText(),updateAgeField.getText(),(updateDateField.getText()),updateGenderField.getText().trim().charAt(0),insertLocationField.getText().trim());// to add  a location to list
                       // newMartyr.setLocation(updateLocationField.getText());
                        Martyrs oldMartyr = (Martyrs) table.getSelectionModel().getSelectedItem();
                        update(oldMartyr, newMartyr);

                    }else {
                        button.alertWarning.setTitle("Warning ");
                        button.alertWarning.setHeaderText(" you Should fill All field to update");
                        button.alertWarning.showAndWait();
                    }
                  //  MainScreen.doubleLinkedList.displaylist();
                    MainScreen.avlTree.inorder();
                });
                //methode update  , check the empty , to lower case , trim

                 }else{
                    button.alertWarning.setTitle("Warning ");
                    button.alertWarning.setHeaderText("add the information to update to a martyr ");
                    button.alertWarning.show();

                }
            }else if (event.getSource() ==  button.deleteButton){
                button.alertWarning.setTitle("Warning ");

                button.alertWarning.setHeaderText("before delete the martyr , search for him in the top to be sure of his data  ");
                button.alertWarning.show();
                MainScreen.avlTree.inorder();
                System.out.println("____________________________");
                if (!bottomGridPane.getChildren().contains(deleteNameField)){
                Button conformButton = new Button("conform");
                deleteNameField.setPromptText(" Name");
                deleteAgeField.setPromptText(" Age");
                deleteLocationField.setPromptText(" Location");
                deleteDateField.setPromptText(" Date");
                deleteGenderField.setPromptText(" Gender");

                bottomGridPane.add(deleteNameField,0,7);bottomGridPane.add(deleteAgeField,1,7);bottomGridPane.add(deleteLocationField,2,7);
                bottomGridPane.add(deleteDateField,0,8);bottomGridPane.add(deleteGenderField,1,8); bottomGridPane.add(conformButton,2,8);




                conformButton.setOnAction(e ->{
                    //if(!(deleteNameField.getText().trim().isEmpty() ||deleteAgeField.getText().trim().isEmpty()
                        //    || deleteLocationField.getText().trim().isEmpty()||deleteDateField.getText().trim().isEmpty() || deleteGenderField.getText().trim().isEmpty())){
                        Martyrs martyrs = (Martyrs) table.getSelectionModel().getSelectedItem();// to add  a location to list
                        delete(deleteLocationField.getText(),martyrs);
                    /*}else {
                        button.alertWarning.setTitle("Warning ");
                        button.alertWarning.setHeaderText(" you Should fill All field to delete");
                        button.alertWarning.showAndWait();
                    }*/
                    MainScreen.avlTree.inorder();
                });
                //search_delete , check the empty  to lower case,trim
                }else{
                    button.alertWarning.setTitle("Warning ");
                    button.alertWarning.setHeaderText("add the information to delete to a martyr ");
                    button.alertWarning.show();

                }

            }
        }
    }


}
