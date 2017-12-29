package simulation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.util.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController implements Initializable {

    @FXML
    public TableView<Statistics> tableID;

    @FXML
    public TableColumn<Statistics, Integer> Curr_Floor;

    @FXML
    public TableColumn<Statistics, Integer> ClockTicks;


    @FXML
    public TableColumn<Statistics,Integer> Tar_Floor;

    @FXML
    public TableColumn<Statistics, Integer> InElevator;
    
    @FXML
    public TableColumn<Statistics, Double> WaitingTime;

    @FXML
    public TableColumn<Statistics,Integer> TotalPeople;

    @FXML
    public TableColumn<Statistics, Integer> ElevatorMoved;
    
    @FXML
    private BarChart<String, Integer> dist;
    ArrayList <people>graph = new ArrayList<>();
    


    @FXML
    public TextField msg;
    ObservableList<Statistics> data = FXCollections.observableArrayList();
  private static final int randomSeed=100;                       
  private static final int peopleIncrementCount=3;               
  private static final int incrementInterval=60 * 1;                 
                                                                
  private static final int elevatorCapacity=6;                   
  
  private int newPeopleCount;
  Random r = new Random();
  private int floorNo = 0;
  private int clockTicks;             
  private int elevatorFloor;          
  private int howManyFloors;          
  private int howManyPeopleMax;       
  private double delayBetweenStepsMilli; 
  private Random rgenerator;          
  private ArrayList allPeopleList;    
  private ArrayList floors;    
  private ArrayList elevatorList;    
  private int totalPassengers = 0;
  private double wait = 0;
  private int y = 0;
  int peopleAtfloor = 0;
  int CurrentFloor = 1 , targetFloor = 1;
  int id= 0;
  int totalfloors = 5;
  int currentFloor = 0 ;
  ArrayList <people> allpeople = new ArrayList<>();
  
 
  clock CT = new clock();
  current CF = new current();
  target TF = new target();
  peopleElevator EM = new peopleElevator();
  elevator PE = new elevator();
  delay D = new delay();
  delay DE = new delay();
  
  @FXML
    void simulate(ActionEvent event){
    howManyFloors = 5;
    howManyPeopleMax = 90;
    delayBetweenStepsMilli = 0.6;
    floors = new ArrayList(); 
    
    clockTicks = 0;
    for(int i=0; i <= howManyFloors; i++) {  
      floors.add(new ArrayList());
    }
    elevatorList = new ArrayList();  
    allPeopleList = new ArrayList(); 
    elevatorFloor = 1;        
    clockTicks = 0;             
    rgenerator = new Random(randomSeed); 
    run();
    
        
        }
   public int getCurrentFloor() {
    return elevatorFloor;
  }
  
  public int getMaxFloor() {
    return howManyFloors;
  }
  
  public int getMinFloor() {
    return 1;
  }
  
  
  public int getClockTicks() {
    return clockTicks;
  }
  
  
  public int getRemainingPeopleCount() {
    int count = elevatorList.size();    // number of people in elevator
    for(int i=1; i <= howManyFloors; i++) {  
      ArrayList people = (ArrayList)(floors.get(i));
      count += people.size();    // number of people waiting on floors
    }  
    return count;
  } 
  
  public ArrayList getPeopleAtFloor(int f) {
    return (ArrayList)floors.get(f);
  } 
  
  
  public ArrayList getAllPeopleWaiting() {
    ArrayList waiting = new ArrayList();  
                                          
    for(int i=1; i<=howManyFloors; i++) {
      Iterator itr = ((ArrayList)floors.get(i)).iterator();
      while(itr.hasNext()) {
        waiting.add((people)itr.next());
      } 
    }
    return waiting;
  }  
  
  public people getLongestWaitingPerson(ArrayList waitingPeople) {
    people mostWaiting = null;
    double time;
    double lowestEnteringTime = getClockTicks();
    Iterator itr = waitingPeople.iterator();
    // go through all people waiting and find person who entered the simulation
    // the longest time ago
    while(itr.hasNext()) {
      people person = (people)itr.next();
      time = person.getEnteringTime();
      if(time <= lowestEnteringTime) {
        lowestEnteringTime = time;
        mostWaiting = person;
      }   
    }
    return mostWaiting;
  }
  
    
   
  
   public ArrayList loadPeople(people p) {

        ArrayList people = (ArrayList) floors.get(CurrentFloor);


        people.remove(p);
        if (totalPassengers < 6) {
            elevatorList.add(p);
            totalPassengers++;
                 System.out.println("Number Loaded: "+ totalPassengers+" with target floor: "+p.getTargetFloor()+"on floor: "+p.getStartingFloor()+"with total passengers: "+totalPassengers);
                 TF.insert(p.getTargetFloor());
           
                 PE.insert( totalPassengers);
        } else {
            wait =  p.getEnteringTime() - p.getLeaveTime();
            
            D.insert(wait);
            DE.insert(wait);
            //    System.out.print("Delay: "+ delay);
        }

        return elevatorList;
    }

  
  
  
  
  
  
  
    public int moveToFloor(ArrayList<people> allpeople) {
        int []arr = new int[allpeople.size()];
        
        for(int i  = 0 ; i < arr.length ; i++)
        {
            arr[i] = allpeople.get(i).getTargetFloor();
        }
        
        int N = allpeople.size();
        int i, j, temp;
        int element;
        for (i = 1; i< N; i++) 
        {
            j = i;
            temp = arr[i];    
            while (j > 0 && temp < arr[j-1])
            {
              
                arr[j] = arr[j-1];
                j = j-1;
            }
            arr[j] = temp;            
        }
 
        for(int b = 0 ; b < arr.length ; b++)
        {
        
        int direction;
        if (arr[b] > 5 || arr[b] < 1) {
            System.out.println("FLOOR UNREACHABLE");
        }

        if (arr[b] > CurrentFloor) {
            direction = 1; // up
        } else if (arr[b] < CurrentFloor) {
            direction = -1; // down
        } else {
            direction = 0; // idle
        }

        while (CurrentFloor != arr[b] && CurrentFloor <= 5) {
            CurrentFloor = CurrentFloor + direction;
            incrementClock(15);
            System.out.println("Elevator moved To: " + CurrentFloor);
            EM.insert(CurrentFloor);
            delay(1);
        }
        unloadElevator();
        incrementClock(10);
        }
        
return totalPassengers;

    }  
  public void unloadElevator() {
        Iterator itr = elevatorList.iterator();

        while (itr.hasNext()) {
            people p = (people) itr.next();
            if (p.getTargetFloor() == CurrentFloor) {
                itr.remove();
                totalPassengers--;   
                System.out.println("Number of People In Elevator: "+totalPassengers);
                
                p.setLeavingTime(getClockTicks());
            }
        }
        if(totalPassengers == -2 || clockTicks< 72000){
        generatePeople();
        }
        
        else if(clockTicks > 72000)
        {
     //       System.exit(0);
        }
    }
    
  public int getTotalWaitingTime() {
    int waitingTime = 0;
    Iterator itr = allPeopleList.iterator();
    while(itr.hasNext()) {
      waitingTime += ((people)itr.next()).getWaitingTime();
    }
    return waitingTime;
  }
 
  void incrementClock(int units) {
    for(int i=1; i<=units; i++) {
      clockTicks++;
      CT.insert(clockTicks);
    }
    
  }    
  
  
  public void generatePeople() {
       floorNo++;
    
        ArrayList <people> allpeople = new ArrayList<>();
        
        for(int j = 1 ; j <= totalfloors ; j++ )
        {
         newPeopleCount = r.nextInt(5) + 1; 
            for (int i = 0; i < newPeopleCount; i++) { 
            currentFloor = CurrentFloor;
            
            targetFloor = randomInt(1, 5, r);
            
            
             if (currentFloor != targetFloor && currentFloor < 5) {
                 CF.insert(currentFloor);
               ArrayList person = (ArrayList) (floors.get(currentFloor)); 
               people p = new people(currentFloor, targetFloor, getClockTicks());
               person.add(p); 
               allpeople = person;
               allpeople = loadPeople(p);
              
    
             
            }
            }
        
        
       for(int a = 0 ; a < totalfloors ; a++)
        {
            int passengers = moveToFloor(allpeople);
            
          
        
       
                
            }
        
    if(!CT.isEmpty() && !CF.isEmpty() && !TF.isEmpty() && !PE.isEmpty() && !EM.isEmpty() && !D.isEmpty() ){// && !CF.isEmpty() && !TF.isEmpty()){
            Statistics s = new Statistics(CT.remove(),CF.remove(),TF.remove(),EM.remove(),PE.remove(),D.remove());
               data.add(s);
   
    }
   // delay(1);
  }
  }
  
  
  
        public static double getExponentialNumbers(Random rand, double meanTime)
        {
            return Math.abs(-meanTime * Math.log(rand.nextDouble()));
        }

  
  
      public int randomInt(int min, int max, Random r)
    {
        long range = (long)max - (long)min + 1;
        long frac = (long)(range * r.nextDouble());
        int r_num = (int)(frac+min);
        return r_num;
       
    }

  
  public void run() {        
  //  generatePeople();
    while (clockTicks <3000) { 
        generatePeople();
    }
  }
    
    
   
   @FXML
   void VISUALIZE(ActionEvent event)
   {
     int y = 0 ;
     ArrayList person = graph;
     people p = getLongestWaitingPerson(person);
     double wait;
     
     for(int i = 0 ; i < 30 ; i++)
     {
         
       wait = (getClockTicks() - graph.get(i).getWaitingTime()) / 60;
         XYChart.Series<String, Integer> chart = new XYChart.Series<>();
    
                chart.getData().add(new XYChart.Data(""+graph.get(i).getEnteringTime(), wait));
                dist.getData().add(chart);
                            
     }
     
   }
     
   
   
   public void delay(int i) {
    try {         
      Thread.sleep(i * (long)this.delayBetweenStepsMilli);
    } catch (Throwable e) {
    } 
  }  
   

@Override
    public void initialize(URL url, ResourceBundle rb){ 
     
         ClockTicks.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("clocktick"));
         Curr_Floor.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("currentFloor"));
         Tar_Floor.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("targetFloor"));
         ElevatorMoved.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("Elevatormoved"));
         WaitingTime.setCellValueFactory(new PropertyValueFactory<Statistics, Double>("waitTime"));
         TotalPeople.setCellValueFactory(new PropertyValueFactory<Statistics , Integer>("totalpeople"));
         tableID.setItems(data);
        
    } 
}