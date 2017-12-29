package simulation;


import java.util.ArrayList;

import java.util.ArrayList;

public class people {
    
int personID, lastPersonID,  targetfloor, startingfloor;
ArrayList<people> people_elevator;
int totpassengers;
double enterTime;
double leaveTime;
double timeLeft;


people(int startingfloor, int targetfloor, double enterTime)
{
    personID = nextPersonID();
   
    this.startingfloor = startingfloor;
    this.targetfloor = targetfloor;
    people_elevator = new ArrayList<>();
    totpassengers = 0;
    this.enterTime = enterTime;
    this.leaveTime = leaveTime;
    lastPersonID = 0;
    timeLeft = 0.0;
}

int nextPersonID()    
{
    lastPersonID++;
    return lastPersonID; 
}


void add(people p)
{
    people_elevator.add(p);
    totpassengers++;
 }

int getTargetFloor()
{
    return targetfloor;
}

int getTotalPassengers()
{
    return totpassengers;
}

double getEnteringTime()
{
    return enterTime;
}

int getStartingFloor()
{
    return startingfloor;
}


double getLeaveTime()
{
    return leaveTime;
}
public void setLeavingTime(int leavingTime) {
    timeLeft = leavingTime;
  }  
  
public double getWaitingTime() {
    if(timeLeft<0) {
      return 0;
    }
    return timeLeft-enterTime;
  } 
}
