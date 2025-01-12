import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class Statistics {
    Statistics(){

    }
TextArea t = new TextArea();

   /* int ageCount (int age , String location) {
        int count = 0;
            if (!MainScreen.linkedList.isEmpty()) {
               if (location.equals("all"))  {
                     Node current = MainScreen.linkedList.first;
                     while (current.next != null) { // O(n)
                        if (Double.parseDouble(((Martyrs) current.element).getAge()) == age) {
                        count++;
                        }
                    current = current.next;
                     }

                }else {
                    DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                    while (current != null) { // O(n^2)
                        if(current.element.equals(location)){
                            Node currentCheck = current.headerList;
                            while (currentCheck!=null){
                                if (Double.parseDouble(((Martyrs) currentCheck.element).getAge()) == age) {
                                    count++;
                                }
                                currentCheck=currentCheck.next;
                            }
                            break;
                        }
                        current = current.next;
                    }
                }
            } else{
            return count;
            }
        return count;
    }*/



    String ageAverage ( String location) {

        int count = 0;
        double average = 0;
        if (!MainScreen.linkedList.isEmpty()) {
            if (location.equals("all"))  {
                Node current = MainScreen.linkedList.first;
                while (current.next != null) { // O(n)
                        average+=Double.parseDouble(((Martyrs)current.element).getAge());
                  //  t.appendText( ((Martyrs)current.element).getLocation() +" ");
                        count++;
                    current = current.next;
                }
            }else {
                DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                while (current.next != null) { // O(n^2)
                    if(current.element.equals(location)){
                        Node currentCheck = current.headerList;
                        while (currentCheck!=null){
                                average+=Double.parseDouble(((Martyrs)currentCheck.element).getAge());
                                count++;
                            currentCheck=currentCheck.next;
                        }
                    }
                    current = current.next;
                }
            }
        } else{
            return "0";
        }
        return String.format("%.3f",(average/count) );
    }

    void avgAll(){
        int count = 0;
        double average = 0;
        DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        while (current.next != null) {
            t.appendText( current.element+" ");
                Node currentCheck = current.headerList;
                while (currentCheck!=null){
                    average+=Double.parseDouble(((Martyrs)currentCheck.element).getAge());
                    count++;
                    currentCheck=currentCheck.next;
                }
            t.appendText(String.format("%.3f",(average/count) )+" \n");
            current = current.next;
        }
    }
    int genderCount (char gender , String location) {
        int count = 0;
        if (!MainScreen.linkedList.isEmpty()) {
            if (location.equals("all"))  {
                Node current = MainScreen.linkedList.first;
                while (current.next != null) { // O(n)
                    if (((Martyrs)current.element).getGender()==gender) {
                        //average+=Double.parseDouble(((Martyrs)current.element).getAge());
                        count++;
                    }
                    current = current.next;
                }

            }else {
                DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                while (current.next != null) { // O(n^2)
                    if(current.element.equals(location)){
                        Node currentCheck = current.headerList;
                        while (currentCheck!=null){
                            if (((Martyrs)currentCheck.element).getGender()==gender) {
                                //average+=Double.parseDouble(((Martyrs)current.element).getAge());
                                count++;
                            }
                            currentCheck=currentCheck.next;
                        }
                    }
                    current = current.next;
                }
            }
        } else{
            return count;
        }
        return count;
    }

    double genderAverage (char gender , String location){
        int count = 0;
        double average = 0;
        if (location.equals("all")){
            if (!MainScreen.linkedList.isEmpty()) {
                Node current = MainScreen.linkedList.first;
                while (current.next != null) { // O(n)
                    if(((Martyrs)current.element).getGender()==gender){
                        average+=Double.parseDouble(((Martyrs)current.element).getAge());
                        count++;
                    }
                    current = current.next;
                }
            } else{
                average = 0;
                return average;
            }
        }
        return average/count;
    }


    String ratioMartyrsOnLocation (String location){
        int count =0;
        double result = 0;
        if (location.equalsIgnoreCase("all")){
            return ("chose country");
        }else {
            DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            while (current != null) { // O(n^2)
                if(((String)current.element).equalsIgnoreCase(location)) {
                    Node currentCheck = current.headerList;
                    while (currentCheck != null) {
                        count++;
                        currentCheck = currentCheck.next;
                    }
                     result = (count / MainScreen.linkedList.count) * 100;
                    count = 0;

                }
                current = current.next;
            }return (String.format("%.3f",result ));
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    int getNumOfMartyr (String location){
        if (location == null) {
            return -1;
        }else {
            DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            DoubleNode first = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            while (current != first.previous) {
                if (location.equalsIgnoreCase((String) current.element)) {
                  //  Locations s = (Locations) current.element;
                    return (countMartyr(current.avlNames.root));
                }
                current = current.next;
            }
        }
        return -1;
    }
    int countmart = 0 ;
    private int countMartyr(TreeNode r)
    {
        if (r != null)
        {
            countMartyr(r.left);
            countmart++;
            countMartyr(r.right);
        }
        return countmart;
    }
   /* int maximum_number_of_martyrs (){
        String locationOfMax = "";

    }*/



    BorderPane statisticsBorderPane = new BorderPane();
    GridPane centerGridPane = new GridPane();

    Label locationName_Label = new Label("location : ");
    Label locationName_result = new Label("all");


    Label numMartLocationLabel =new Label("  The numbers of martyrs in the selected location : ");
    //TextField numAgeField = new TextField();
  //  Button conformButton = new Button("Conform");

    Label numAgeResult= new Label();
    Label numAgeResultLabel= new Label();// it has a result ,  set style when you're done




   /* RadioButton r1 = new RadioButton("M");
    RadioButton r2 = new RadioButton("F");
    RadioButton r3 = new RadioButton("NA");*/


    Label maxDateOfDeath_result = new Label();
    Label  avgAgeOfAllMartyrs_result = new Label();
    Label ratioOFMartyrINThisCity_result = new Label();

    Button next = new Button("Next");
    Button previous  = new Button("Previous");

    static Alert alertWarning = new Alert(Alert.AlertType.WARNING);



    Label numGenderLabel_re = new Label();
    Label numGenderLabel_result = new Label();



    DoubleNode Current ;
    DoubleNode First;
    BorderPane statistic(){
        if (MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first!=null) {
            Current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
            First = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        }

        // the top has the name of the location
        DoubleNode current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        DoubleNode first=MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
        if (first!=null) {
            while (current.next != first) { // O(n^2)
                // locationSearch.getItems().add((current.element));
                if (((String)current.element).equalsIgnoreCase(Martyrs.locationName) ) {
                  Current=current;
                }
                //    a.add(current.element);
                current = current.next;
            }
        }

        HBox topHbox = new HBox();
        locationName_Label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        locationName_result.setStyle("-fx-font-weight: bold; -fx-font-size: 11px; -fx-text-fill: blue");
        topHbox.getChildren().addAll(locationName_Label,locationName_result);
        topHbox.setAlignment(Pos.CENTER);
        topHbox.minHeight(150);
        statisticsBorderPane.setTop(topHbox);

        //  the center have  all labels

        //  the first& second line


        centerGridPane.add(numMartLocationLabel,0,0);
        Label resmlocation = new Label(getNumOfMartyr(Martyrs.locationName)+"");
        centerGridPane.add (resmlocation,2,0);
    //    centerGridPane.add(numAgeField,2,0);
     //   centerGridPane.add(conformButton,3,0);

        centerGridPane.add(numAgeResult,3,1);

        // third line
        Label numGenderLabel =new Label("  The height of the 1st AVL tree : ");
        TilePane r = new TilePane();
        // create a toggle group
        ToggleGroup tg = new ToggleGroup();

        // create radiobuttons


        // add radiobuttons to toggle group



        // add label
        r.getChildren().add(numGenderLabel);
     //   r.getChildren().add();
       // r.getChildren().add(r2);



        centerGridPane.add(numGenderLabel,0,2);
        centerGridPane.add(r,2,2);
        centerGridPane.add(numGenderLabel_re, 0, 3);
        centerGridPane.add(numGenderLabel_result, 1, 3);

        Label avgAgeOfAllMartyrs =new Label("  The Average of  Martyrs Age    : ");

        centerGridPane.add(avgAgeOfAllMartyrs,0,4);centerGridPane.add(avgAgeOfAllMartyrs_result,1,4);

        Label maxDateOfDeath = new Label(" The max Death Date of Martyr : ");

        centerGridPane.add(maxDateOfDeath,0,6);centerGridPane.add(maxDateOfDeath_result,1,6);

        Label ratioOFMartyrINThisCity  = new Label("The Ratio Of Martyrs In this Location :  ");

        centerGridPane.add(ratioOFMartyrINThisCity,0,8); centerGridPane.add(ratioOFMartyrINThisCity_result,1,8);
        centerGridPane.add(t,0,9);


       // centerGridPane.add(textAreaReport,0,9);
        HBox centerHbox = new HBox();
        centerHbox.getChildren().add(centerGridPane);
        centerHbox.setAlignment(Pos.CENTER);
        statisticsBorderPane.setCenter(centerHbox);


        HBox bottomHbox = new HBox();
        bottomHbox.getChildren().addAll(previous,next);
        bottomHbox.setAlignment(Pos.CENTER);
        bottomHbox.setMinHeight(120);
        statisticsBorderPane.setBottom(bottomHbox);

        shown();
        MainButtonsEvent mainButtonsEvent = new MainButtonsEvent();
     //   conformButton.setOnAction(mainButtonsEvent);
        next.setOnAction(mainButtonsEvent);
        previous.setOnAction(mainButtonsEvent);
      //  r1.setOnAction(mainButtonsEvent);
     //   r2.setOnAction(mainButtonsEvent);


        return statisticsBorderPane;
    }

    public  class MainButtonsEvent implements EventHandler<ActionEvent> { // the handler button
        @Override
        public void handle(ActionEvent event) {

       /*     if (event.getSource() == conformButton) {
                try {
                    if (!centerGridPane.getChildren().contains(numAgeResultLabel)) {
                        numAgeResult.setText((getNumOfMartyr(Martyrs.locationName)) + "");
                        numAgeResultLabel.setText("The numbers of martyrs in the selected location  " + numAgeResultLabel.getText() + "  : ");
                        numAgeResultLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;  -fx-text-fill: green;");
                        centerGridPane.add(numAgeResultLabel, 2, 1);
                    }
                }catch (NumberFormatException e){
                    alertWarning.setContentText("the type of  age just integer");
                    alertWarning.show();
                }

            } else*/ if (event.getSource() == next) {
                System.out.println(First.element);
                if (First!=null) {
                    if (Current != First.previous) {
                        locationName_result.setText((String) Current.element);
                        avgAgeOfAllMartyrs_result.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;  -fx-text-fill: green;");
                        avgAgeOfAllMartyrs_result.setText(ageAverage((String) Current.element) + "");
                        getMaxDate(Current.avlDates);
                        shown();
                      //  gender();
                        Current = Current.next;

                    /*   String location = locationName_result.getText();
                       // if (location.equalsIgnoreCase("all")){
                       avgAgeOfAllMartyrs_result.setText(ageAverage(location)+"");
                       //the max date
                       ratioOFMartyrINThisCity_result.setText(ratioMartyrsOnLocation(location));*/

                    } else {
                        locationName_result.setText("all");
                        shown();
                       // gender();
                        avgAll();
                        Current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                        System.out.println("doneeee");
                    }
                }

            } else if (event.getSource() == previous) {
                // in insert store the data in martyr Obj
                if (First!=null) {
                    if (Current != First.previous) {
                        locationName_result.setText((String) Current.element);
                        avgAgeOfAllMartyrs_result.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;  -fx-text-fill: green;");
                        avgAgeOfAllMartyrs_result.setText(ageAverage((String) Current.element) + "");
                        getMaxDate(Current.avlDates);
                        shown();
                        // gender();
                        Current = Current.previous;


                    }//else if (event.getSource() == r1) {
                    //  numGenderLabel_result.setText((genderCount(r1.getText().charAt(0), locationName_result.getText())) + "");
                    numGenderLabel_re.setText("The numbers of martyrs in the selected location  " + "  : ");
                    numGenderLabel_re.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;  -fx-text-fill: green;");
                    // } else if (event.getSource() == r2) {
                    // numGenderLabel_result.setText((genderCount(r2.getText().charAt(0), locationName_result.getText())) + "");
                    numGenderLabel_re.setText("The Number of martyr has gender   : ");
                    numGenderLabel_re.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;  -fx-text-fill: green;");
                    //   }
                } else {
                    locationName_result.setText("all");
                    shown();
                    Current = MainScreen.DOUBLE_LINKED_LIST_CIRCULAR.first;
                    System.out.println("doneeee");
                }
            }


            }
        }


    void shown (){
        String location = locationName_result.getText();
           // avgAgeOfAllMartyrs_result.setText(ageAverage(location)+"");
            //the max date
           // Current.avlNames.traverseLevelOrder();
            ratioOFMartyrINThisCity_result.setText(ratioMartyrsOnLocation(location));

    }
  /*  void gender(){
        if(r1.isSelected()) {
            numGenderLabel_result.setText((genderCount(r1.getText().charAt(0), locationName_result.getText())) + "");
            numGenderLabel_re.setText("The Number of martyr has age  " + numAgeField.getText() + "  : ");
            numGenderLabel_re.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;  -fx-text-fill: green;");

        }else if(r2.isSelected()){
            numGenderLabel_result.setText((genderCount(r2.getText().charAt(0), locationName_result.getText())) + "");
            numGenderLabel_re.setText("The Number of martyr has gender   : ");
            numGenderLabel_re.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;  -fx-text-fill: green;");


        }
    }*/
  ArrayList<Integer> myList = new ArrayList<Integer>();
    void getMaxDate(AvlTree_Stack location_Avl_Stack ){
       getMaxDate(location_Avl_Stack.root);
        int maximum = myList.get(0);
        for (int i = 1; i < myList.size(); i++) {
            if (maximum < myList.get(i))
                maximum = myList.get(i);
        }
        System.out.println(" the date that had the maximum number of martyrs :"+maximum);
    }
    private void getMaxDate(Tree_Stack_Node r)
    {
        if (r != null)
        {
            getMaxDate(r.left);
            //System.out.println(r.value +" ");
            myList.add(Integer.parseInt(r.value+""));
            getMaxDate(r.right);
        }
    }
}
