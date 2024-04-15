import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Locations  {
    String city;
       int numOfMartyr ;

    public Locations() {
    }
    public Locations(String city){
        this.city=city;
    }

    public String getCity() {
        return city;
    }

    BorderPane borderLocations = new BorderPane();

    GridPane bottomGridPane = new GridPane();
    private final javafx.scene.control.TableView<String> table = new javafx.scene.control.TableView<>();
    javafx.scene.control.ComboBox<Object> locationsComboBox = new javafx.scene.control.ComboBox<>();

    Buttons button = new Buttons();

    TextField searchBox = new TextField();

    int getNumOfMartyr (){
        return numOfMartyr;
    }

    void update (String cityName , String newCityName){
        boolean s =false;
            DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        if (first!=null) {
            if (MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.count==1) {//check first element
                System.out.println("null");
                first.element=newCityName;
                s = true;
            }
            while (current != first.previous) {
                if (current.element.equals(cityName)) {
                    current.element = newCityName;
                    s = true;
                }
                current = current.next;
            }
        }
        if(!s){
            button.labelUpdateResult.setText("the location doesn't  exist !!");
        }

    }

    public void search_delete(Object x ) {

        DoubleNode previous = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first.next;
      /*  if(((String)previous.element).equalsIgnoreCase((String)x)){
            previous=current;
            current=current.next;
          //  previous.next = current.next;
            MainScreen.DOUBLE_LINKED_LIST_CIRCULAR. count--;

        *//*    first=previous;
            first.next=current;*//*
            return;
        }*/
        DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        if (first!=null) {
            if (MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.count==1) {//check first element
                System.out.println("null");
               first.next=null;
               first.previous=null;
               return;
            }
            while (current != first.previous && !((String) current.element).equalsIgnoreCase((String) x)) {
                previous = current;
                current = current.next;
            }
            if (current.next != first) {
                previous.next = current.next;
                MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.count--;
            } else {
                button.labelDeleteResult.setText("the location doesn't  exist !!");
            }
        }
    }
    Button insertBT = new Button("Insert");
    Button updateBT = new Button("Update");
    Button deleteBT = new Button("Delete");
    Button moreDetailsBT = new Button("More Details");
    Label moreDetailsLabel = new Label(" * click on the button for more details (after choose the location from table) : ");

    Stage detailsStage = new Stage();
    TabPane tabPane = new TabPane();

    Martyrs martyrs = new Martyrs();
    Statistics statistics = new Statistics();

    Tab tab1 = new Tab("Martyr",MainScreen.martyrs.martyrs());
    Tab tab2 = new Tab("Statistic"  , statistics.statistic());
    VBox vBox = new VBox(tabPane);
    Scene scene = new Scene(vBox ,700,500);
    public BorderPane getLocations() {
        // add the field search and the button in the Top of border pane
        HBox topHbox = new HBox();

        TextField searchLocationField = new TextField();
        //??
        searchLocationField.setPromptText("search of location");


        topHbox.getChildren().addAll(locationsComboBox, button.searchButton);

        topHbox.setAlignment(Pos.CENTER);

        borderLocations.setTop(topHbox);

        //add location of martyrs from double linkedlist in the center of border pane


        // Set up the table columns
        TableColumn<String, String> cityCol = new TableColumn<>("City Name");
        // catch column with obj
        cityCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));

        cityCol.setMinWidth(220);


        // Add the columns to the table
        table.getColumns().add(cityCol);
        //table.getItems().clear();
        //setData(table);

        // Set up the search box and button

        searchBox.setPromptText("Enter search term");
      //  button.searchButton.setOnAction(event -> {
         /*   if(searchLocationField.getText().isEmpty()){
                table.getItems().clear();
                setData(table);
            }else {
                if(!MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.search(searchLocationField.getText())) {
                    table.getItems().clear();
                    System.out.println("before");
                    setAddedData(table, searchLocationField.getText());
                    System.out.println("after");
                }else {
                    button.alertWarning.setTitle("Warning ");
                    button.alertWarning.setHeaderText(" city does not exist ");
                    button.alertWarning.show();

                }
            }*/

     //   });
        locationsComboBox.setEditable(true);

         /*   locationsComboBox.setOnAction(e -> {
                while (locationsComboBox.getEditor().getText().equalsIgnoreCase("")) {
                 //   String s = (String) locationsComboBox.getValue();
                    DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                    DoubleNode first = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                   // table.getItems().clear();
                    while (current != first.previous) {
                        String s = (String) locationsComboBox.getValue();
                        if (locationsComboBox.getEditor().getText() != null) {
                            if (((String) current.element).contains(s)) {
                                setAddedData(table, (String) locationsComboBox.getValue());
                            }
                        } else {
                            table.getItems().clear();
                            setData(table);
                        }
                        current = current.next;
                    }
                }

            });
*/ locationsComboBox.getItems().add("");
        button.searchButton.setOnAction(actionEvent -> {
           // System.out.println(locationsComboBox.getValue().toString());
          //  System.out.println(locationsComboBox.getEditor().getText());

            if(locationsComboBox.getEditor().getText().equals("")) {
               // System.out.println("bbbbb");
//                setData(table);
                ArrayList<String> a = new ArrayList<>();
                MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.displayList();
                DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                DoubleNode first = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
               // System.out.println(first.element);
                table.getItems().clear();
                locationsComboBox.getItems().clear();
                locationsComboBox.getItems().add("");
                if (first!=null) {
                    System.out.println(MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.count);
                    if (MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.count==1) {//check first element
                        System.out.println("null");
                        locationsComboBox.getItems().add((first.element));
                        a.add((String) first.element);
                      //  table.getItems().add(a.get(0));
                    }
                    System.out.println(first.element+"  this first location");
                    while (current != first.previous) { // O(n^2)// add the cities to comboBox
                        locationsComboBox.getItems().add((current.element));
                        a.add((String) current.element);
                        current = current.next;
                    }
                    for (String s : a) {
                        table.getItems().add(s);
                    }
                }

                // table.setItems((ObservableList<String>) a);
              //  setData(table);
               // locationsComboBox.getItems().addAll(FXCollections.observableArrayList(a));//setItems();
              //  table.getItems().clear();
                //setAddedLocationData(table, locationSearch);
            }else {
                table.getItems().clear();
               // locationsComboBox.getItems().clear();
               // locationsComboBox.getItems().add("");
              //  ArrayList<Object> a = new ArrayList<>();
                MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.displayList();
                System.out.println("location box has a string");

                setAddedData(table, locationsComboBox.getEditor().getText());
            }
        });
        borderLocations.setCenter(table);

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);





        //add options in the bottom
        MainButtonsEvent mainButtonsEvent = new MainButtonsEvent();


        button.labelInsert.setText("* If you want to insert a location, click on ");
        bottomGridPane.add( button.labelInsert ,0,2);bottomGridPane.add(insertBT,1,2);
        insertBT.setOnAction(mainButtonsEvent);

        button.labelUpdate.setText(" * If you want to update the locations of the martyrs, click on  ");
        bottomGridPane.add(button.labelUpdate ,0,4);bottomGridPane.add(updateBT,1,4);
        updateBT.setOnAction(mainButtonsEvent);

        button.labelDelete.setText(" * If you want to delete a location, click on  ");
        bottomGridPane.add( button.labelDelete ,0,6);bottomGridPane.add(deleteBT,1,6);
        deleteBT.setOnAction(mainButtonsEvent);

        bottomGridPane.add(moreDetailsLabel,0,8);bottomGridPane.add(moreDetailsBT,1,8);
        moreDetailsBT.setOnAction(mainButtonsEvent);
       

        borderLocations.setBottom(bottomGridPane);

        return borderLocations;
    }
   /* private void setAddedLocationData(TableView<String> table , ComboBox location ){//add by location

        try {
            if (location.getValue()==null ){
                setData(table);
            }else {
                DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                while (current != first.previous) {
                    if (((String) current.element).equalsIgnoreCase((String)location.getValue())) {
                        Node currentCheck = current.headerList;
                        while (currentCheck != null) {
                           // table.getItems().add( currentCheck.element);
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
    }*/
   /* private void setData(TableView<String> table ){
        table.getItems().clear();
        DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                                  MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.displayList();
        DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        if (first!=null) {
            System.out.println(first.element);
            while (current != first.previous ) {
                table.getItems().add((String) current.element);
                current = current.next;
            }
        }
    }*/
    private void setAddedData(TableView<String> table , String s ){
       // s.toLowerCase();

        DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                                MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.displayList();
        DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        if (first!=null) {
            System.out.println((current.element)+"___check");
            if (MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.count==1) {//check first element
                System.out.println("null");
                locationsComboBox.getItems().add((first.element));

                  table.getItems().add((String) first.element);
            }
            while (current != first.previous) {
                //  System.out.println(current.element+s);
                ///((String)current.element).toLowerCase();
                if ((((String) current.element)).equalsIgnoreCase (s)) {
                    table.getItems().add((String) current.element);
                }
                current = current.next;
            }
        }
    }



    public  class MainButtonsEvent implements EventHandler<ActionEvent> { // the handler button
        @Override
        public void handle(ActionEvent event) {

            if (event.getSource() ==  insertBT){
                // add method , check empty
                   Button conform = new Button("conform");
                if (!bottomGridPane.getChildren().contains(button.insertField)){
                        bottomGridPane.add(button.insertField,0,3);bottomGridPane.add(conform,1,3);bottomGridPane.add(button.labelInsertResult,2,3);
                    conform.setOnAction(e ->{
                       // Locations l=new Locations(button.insertField.getText());// to add  a location to list
                        if(button.insertField.getText().trim().isEmpty()){
                           button.labelInsertResult.setText("the field is Empty");
                        }else if (!MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.search(button.insertField.getText())){
                            MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.addLocation(button.insertField.getText());
                           // System.out.println(MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.getFirst()+"hihihihih");
                           // System.out.println("ddddddddddddddddd");
                            button.labelInsertResult.setText("Done !");
                            button.alertWarning.setTitle("Warning ");
                            button.alertWarning.setHeaderText("Dont forget save your work before exit ");
                            button.alertWarning.show();
                            MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.displayList();
                        }else{
                            button.labelInsertResult.setText("the location is already  exist !!");
                        }
                    });

                }else{
                    button.labelInsertResult.setText("enter city to insert it !");
                }
                System.out.println("_______");
                MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.displayList();


            }else if (event.getSource() ==  updateBT){  //

                Button conformButton = new Button("conform");
               // manageEvent.setText("add city to update it , then click Conform");
                button.beforeUpdateField.setPromptText("Old Name");
                button.afterUpdateField.setPromptText("New Name");
                if (!bottomGridPane.getChildren().contains(button.afterUpdateField)) {
                    bottomGridPane.add(button.beforeUpdateField, 0, 5);
                    bottomGridPane.add(button.afterUpdateField, 1, 5);
                    bottomGridPane.add(conformButton, 2, 5);
                    bottomGridPane.add(button.labelUpdateResult, 3, 5);
                }
               // MainScreen.doubleLinkedList. displaylist();
                conformButton.setOnAction(e ->{
                    if (button.beforeUpdateField.getText().isEmpty()||button.afterUpdateField.getText().isEmpty()) {
                            button.labelUpdateResult.setText("the fields are Empty");
                    }else {
                            update(button.beforeUpdateField.getText(), button.afterUpdateField.getText());
                    }
                MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.displayList();
                });
                //methode update  , check the empty , to lower case , trim


            }else if (event.getSource() ==  deleteBT){

                Button conformButton = new Button("conform");
                //manageEvent.setText("add city to delete it , then click Conform");
                if (!bottomGridPane.getChildren().contains(button.deleteField)) {
                    bottomGridPane.add(button.deleteField, 0, 7);
                    bottomGridPane.add(conformButton, 1, 7);
                    //bottomGridPane.add(manageEvent,2, 7);
                }
                conformButton.setOnAction(e ->{
                    if (button.deleteField.getText().isEmpty()) {
                        button.labelUpdateResult.setText("the fields are Empty");
                    }else {
                        search_delete(button.deleteField.getText());
                    }

                    MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.displayList();
                });
                //search_delete , check the empty  to lower case,trim

            }else if (event.getSource() ==  moreDetailsBT){
                String  s = table.getSelectionModel().getSelectedItem();
                if (s!=null){
                    System.out.println("Y");
                    Martyrs.locationName = s;
                    detailsStage.setScene(scene);
                    detailsStage.show();
                }else {
                    System.out.println("N");
                    button.alertWarning.setTitle("Warning ");
                    button.alertWarning.setHeaderText("choose a city from the table !! ");
                    button.alertWarning.show();
                }

            }

        }
    }
}
