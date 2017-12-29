package simulation;

import java.util.NoSuchElementException;

public class peopleElevator {
 

    protected int Queue[] ;
    protected int front, rear, size = 72000, len;
 
    
    public peopleElevator()
    {
        size = 72000;
        len = 0;
        Queue = new int[size];
        front = -1;
        rear = -1;
    
    }
    
    public boolean isEmpty() 
    {
        return front == -1;
    }    
    /*  Function to check if queue is full */
    public boolean isFull() 
    {
        return front==0 && rear == size -1 ;
    }    
    /*  Function to get the size of the queue */
    public int getSize()
    {
        return len ;
    }    
    /*  Function to check the front element of the queue */
    public double peek() 
    {
        if (isEmpty())
           throw new NoSuchElementException("Underflow Exception");
        return Queue[front];
    }    
    /*  Function to insert an element to the queue */
    public void insert(int i) 
    {
        try{
        if (rear == -1) 
        {
            front = 0;
            rear = 0;
            Queue[rear] = i;
           
        }
        else if ( rear + 1 < size || rear + 1 >= size){
            Queue[++rear] = i;    
           
        }
        else
        {
            System.out.print("");
        }
            
        }
        catch(Exception e)
        {
            System.out.print("");
        }
                
        
        len++ ;    
    }    
    /*  Function to remove front element from the queue */
    public int remove() 
    {
        if (isEmpty())
           throw new NoSuchElementException("Underflow Exception");
        else 
        {
            len-- ;
            int p  = Queue[front];
            if ( front == rear) 
            {
                front = -1;
                rear = -1;
            }
            else
                front++;                
            return p;
        }        
    }
}



