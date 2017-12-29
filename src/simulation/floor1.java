package simulation;

import java.util.NoSuchElementException;

public class floor1 
 {

    protected int Queue[] ;
    protected int front, rear, size = 80000, len;
 
    
    public floor1()
    {
        size = 80000;
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
    public int peek() 
    {
        if (isEmpty())
           throw new NoSuchElementException("Underflow Exception");
        return Queue[front];
    }    
    /*  Function to insert an element to the queue */
    public void insert(int i) 
    {
        if (rear == -1) 
        {
            front = 0;
            rear = 0;
            Queue[rear] = i;
           
        }
       // else if (rear + 1 >= size)
          //  throw new IndexOutOfBoundsException("Overflow Exception");
       // else if ( rear + 1 < size){
            Queue[++rear] = i;    
           
       // }
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
