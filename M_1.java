/**
 * This file does compile, it was coded with codepad and explained 
 * via phone/skype.
 * My exaplanations are between the JAVADOC comments. 
 * In this case Emmanuel asked me to help him with the Run time analysis of 
 * each function.
 *
 * @author Michael Wilson, Emmanuel
 * @version 1.0 */

import java.util.*;

public class M_1 { // i am going to post the questions here now
    
    public static void main(final String[] args) {
        int n = 3;
        System.out.println("Value from f1 is: " + f1(n));    
        System.out.println("Value from f2 is: " + f2(n));
        System.out.println("Value from f3 is: " + f3(n));
        System.out.println("Value from f4 is: " + f4(n));
        System.out.println("Value from f5 is: " + f5(n));
        System.out.println("Value from f6 is: " + f6(n));
        System.out.println("Value from f7 is: " + f7(n));
    }   
    
        
    public static int f1(int N) {  // this method should O(n)
         int x = 0; //O(1)
        for(int i = 0; i < N; i++)  // O(n) 
            x++;          
        return x; 
            
    } // what do you think about this method, how u will approach it?


    //Big oh of this method
    /** O(n * O(n)) --> O(n * n) === O(n^2) **/
    public static int f2(int N) {  
        int x = 0; //O(1)
        for(int i = 0; i < N; i++) // O(n)
                // O(n)`
            for(int j = 0; j < i; j++) 
                x++;
        return x;
    }


    // Big oh of this method
    /** O(n^2) */
    public static int f3(int N) {
        
        // O(1)
        if (N == 0) return 1;
        else{ 
            
            int x = 0;
            // O(N)
            for(int i = 0; i < N; i++)
                   x += f3(N-1);
            return x;
        }
    }

    // O(N^2)
    public static int f3_loop(int N) {
        int x = 0;
        // O(N)
        for(int i = 0; i < N; i++) {  
            
            // O(N), O(N - 1), O(N - 2)... O(1)
            // O(N)
            for(int j = 0; j < i; j++) {
                x += i;
            }
        }
        return x;
    }


    // Big O of this method 
    // f4(N/2) --> O(log(n))
    // F1(N) --> (N)

    /**
     * Suppose you have a recursive function that makes a call in the following format
     * return a * REC_FUNCTION(n/b) + O(SOME BOUND OF A FUNCTION(n^d))
     * 
     * O(SOME FUNCTION) = F1(N) + {F4(N/2): some function } --> O(n^dlog(n)) = O(nlog(n))
     *
     * Since we know the layout of this problem uses 3 functions we can  use the master's 
     * theorem twice by separating each part
     *
     * First dealing with call of f(1) and one call of F4(N/2)
     * 1 * f1(n/1) + log(n^d)
     * d === 1 =  log_(b)a === log_(1)1 === 1
     * 
     * 
     * return 1 * (n/1)log(n/1) + f4((n/2)^d)
     * return {f4(n/2): this is some function} + 1 * {(n/1)Log(n/1)}
     *
     * d === 1 = log_(b)a === log_(1)1 === 1 
     *
     * RT: O(n^d) = O(n)
     *
     * --> RT of REC_FUNCTION IS:
     *
     * O(n ^ d)          --> d > log_(b)a
     * O(n^d * log(n))   --> d = log_(b)a
     * O(n^(log_(b)a))   --> d < log_(b)a 
     *
     * RT: O(n*log(n)) 
     */

    public static int f4(int N) { 
        if (N == 0) return 0;
         return f4(N/2) + f1(N) + f4(N/2);
    }

    /** We know that we're going to make log(n)
     * we know that max RT of just the return call is: MAX(logn, n, logn) --> n
     * for(i == N, i > 0, i/=2)
     *    for(j = 0; j < N; j++)
     * log(n) * n --> O(nlog(n)) */



    /** log(n) * n == RT: O(nLog(n)) */
    //Big oh of this method 
    public static int f5(int N) { 
        int x = 0;
        // log(n)
         for(int i = N; i > 0; i = i/2)
                // O(n) + O(n/2) + O(n/4)
               x += f1(i);
                
    return x; 
                                
    }


    /**
     * Each call to this one makes a call to two more methods
     * 
     * This can be viewed as a binary tree structure and the RT:(TOTAL NODES)
     * TOTAL_NODES = 2^n+1 - 1 --> RT: O(2^(n + 1)) */

    /** N === 4
                                   f(4)
                            /                
                      f6(3)
            /                   \
           f(2)                   f(2)
        /         \          
       f(1)       f(1)
      /  \       /    \
    f(0) f(0)   f(0)  f(0)

    EACH RECURSIVE CALL IS MAKING A COMPLETE BINARY TREE 

    FIND THE NUMBER OF TOTAL CALLS MADE, --> Running time


    DEPTH        NODES
    0            1 = 2^d = 2 ^ 0
    1            2 = 2^d = 2^1
    2            4 = 2^2
    3            8 = 2^3
    ...
    ...
    ...
    k            2^k      ---> total number of nodes we've seen 
                          ---> 2^(k + 1) - 1
    k + 1        2^k+1
    ...
    ...
    n            2^n
                            --> total number of nodes: 2^n+1 - 1
                                            trinary:  3^n+1 - 1  
                    --> O(n^2) === O(n^(2 + 1) - 1) == O(n^3)                       
    n + 1        2 ^ n + 1*/

    //Big oh of this method 
    public static int f6(int N) { 
        if (N == 0)
            return 1;
         return f6(N-1) + f6(N-1);
                                
    } 

    /**
     * This one is fairly interesting in the sense that it's recursive but makes 
     * a constant reduction of the input, you can use the master's theorem for this
     * 
     * MASTER'S THEOREM
     * 
     * Suppose you have a recursive function that makes a call in the following format
     * return a * REC_FUNCTION(n/b) + O(SOME BOUND OF A FUNCTION(n^d))
     *
     * --> RT of REC_FUNCTION IS:
     *
     * O(n ^ d)          --> d > log_(b)a
     * O(n^d * log(n))   --> d = log_(b)a
     * O(n^(log_(b)a))   --> d < log_(b)a
     *
     * Let's plug in some numbers for the F7
     * a = 1, b = 2, n = n, d = 0 (since n^0 = 1 and 1 is constant) 
     *
     * 1 + f7(N/2) <==> O(n ^ d) + a * f7(n/b)
     *
     * log_(b)a = log_(2)1 == 0 & d == 0 --> we are in the second case 
     * 
     * From the master's theorem we conclude that the RT of f7 is O(1*log(n)) or O(log(n))
     * Another way to look at this is like the binary search algorithm
     */

    //Big oh of this method 
    public static int f7(int N) { 
        if (N == 1) 
            return 0;
         return 1 + f7(N/2);
    }

}
/*

Queue and Stack
Here are API’s for a queue and a stack of integers:
public class QueueOfInts 
------------------------------------------------------------
QueueOfInts() // creates empty queue
boolean isEmpty() // true if queue is empty
void enqueue(int item) //enqueues one int
int dequeue() //dequeues one int



public class StackOfInts 
------------------------------------------------------------
StackOfInts()  //creates empty stack
boolean isEmpty()  //true if stack is empty
void push(int item)  //push one int onto stack
int pop()   //pop one int off stack


Write a pseudo code using the above methods to reverses the items in a QueueOfInts q 
by using an intermediate StackOfInts s.*/

/**
 * q2s --> s2q
 *
 *def rev_queue(q):
 *   s = new StackOfInts()
 *   n = len(q)
 *       
 *  q --> s
 *   for i from 1 to n
 *      s.push(q.dequeue())
 * 
 *  s --> q
 *  // reversed the contents in our queue via the stack
 *  while the stack is not empty
 *      q.enqueue(s.pop()) */


