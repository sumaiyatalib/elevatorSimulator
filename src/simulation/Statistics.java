package simulation;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Statistics {

 private SimpleIntegerProperty targetFloor;
 private SimpleIntegerProperty currentFloor;
 private SimpleIntegerProperty Elevatormoved;
 private SimpleDoubleProperty waitTime;
 private SimpleIntegerProperty totalpeople;
 private SimpleIntegerProperty PeopleInElevator;
  private SimpleIntegerProperty clocktick;
 
  Statistics(int clocktick, int currentFloor, int targetFloor, int Elevatormoved, int totalpeople, double waitTime) 
 {
   this.currentFloor = new SimpleIntegerProperty (currentFloor);
    this.targetFloor = new SimpleIntegerProperty (targetFloor);
    this.totalpeople = new SimpleIntegerProperty (totalpeople);
    this.clocktick = new SimpleIntegerProperty (clocktick);
    this.Elevatormoved = new SimpleIntegerProperty (Elevatormoved);
     this.waitTime =  new SimpleDoubleProperty (waitTime);
    
    
 }

    public int getTargetFloor() {
        return targetFloor.get();
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public int getElevatormoved() {
        return Elevatormoved.get();
    }

    public double getWaitTime() {
        return waitTime.get();
    }

    public int getTotalpeople() {
        return totalpeople.get();
    }


    public int getClocktick() {
        return clocktick.get();
    }
}