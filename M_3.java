/**
 * This file doesn't compile, it was coded with codepad and explained 
 * via phone/skype
 *
 * This class focuses on adding cousin links to each node in a tree
 * 
 * @author Michael Wilson
 * @version 1.0 */


import java.io.*;
public class M_3 {
    class myCodingTree<T>
{
    private Node<T> root;
    
    /** Private node class */
    private class Node<T> {
        private T data;
        private Node<T> left, right, cousin;
        
        private Node(final T data) {
            this(data, null, null, null);
        }
        
        private Node(final T data, final Node<T> left, final Node<T> right, final Node<T> right) {
             this.data = data; 
             this.left = left; 
             this.right = right;
             this.cousin = cousin;
        }
    }
    
    
    /** 
     * Write a method that will link each node to its direct right sibling/cousin
     * 
     * What data structure(s) will you use and why?
     * What is the RT of this algorithm?
     * 
     * @param root The root pointer of the tree */
    public void setUpLinks(Node<T> root) {
        // create a cache that stores the most recent node
        HashMap<Integer, ArrayList<Node<T>>> myHash = new HashMap<>();
        
        for(int i = 1; i < height(root); i++) {
            levelOrderMod(root, i, myHash, overallLevel);
        }
        
        // at this point we have successfully hashed the nodes
        for(Integer level : myHash.keySet()) {
            // gets the current level
            List<Node<T>> tempList = level.get(level);
            // run up to the second to last node in the level since the rightmost
            // node doesn't have a right cousin
            for(int i = 0; i < tempList.length - 2; i++) {
                tempList.get(i).setCousin(tempList.get(i + 1));
            }         
        }
        
        // we have now accomplished out task
    }
    
    /** 
     * Method that takes all the nodes at a certain level and modifies their pointers
     *
     * @param root The start point in the tree
     * @param level The current level that we are dealing with
     * @param myHash The hashmap which stores the nodes per level
     * @param overallLevel The level that this node is on */
    private levelOrderMod(Node<T> root, final int level, 
    HashMap<Integer, List<Node<T>>> myHash, final int overallLevel) {
        // we have reached the current level of this node 
        if(level == 1) {
            // need to set up the link, store in the hashed cache
            myHash.put(overallLevel, root);            
        } else {
            // traverse down the tree
            levelOrderMod(root.left, level - 1, myHash, overallLevel);
            levelOrderMod(root.right, level - 1, myHash, overallLevel);
        }
    }
     
    
    
    /**
     * Method that returns the height of this tree
     * 
     * @return The height of the given tree */
    public int height(Node<T> root) {
        if(root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }
    
    // thought process, run a level order traversal through the tree
    // keep track of each node per level 
    // run through each node and assign the links
    // this algorithm is nothing more than a slight mod to our DFS
}

// Now to answer the questions
/* 
    1) What data structure(s) will you use and why?
    We used a hashmap here since addition to the map is O(1) and retrieval is fast
    In the worst case scenario our tree could be a linked list with all nodes on the same level
    This would be a large M-ary tree
    
    2) What is the RT of this algorithm?
    For this part we need to analyze each method that we have created
        height - O(n), must run through all of the nodes to find the height
        
        levelOrderMod - for level 1 it's constant, for level 2 it takes 2 recursive calls
                        seeing this pattern we can then realize that it takes log(D) time 
                        to access a node at a depth of D
                        we can say that this takes Log(n) per level
                        
        setUpLinks - the first for loop takes log(h) iterations but since it makes log(h)
                     calls to levelOrderMod and goes through all nodes the RT is nLog(n), not too shabby
                     
                     the second for loop is a different story
                     first we iterate through each level in the tree which is log(n)
                     inside of this we iterate through that many nodes per level, we do know that the
                     tree structure is binary so the first level has 1, second has 2, third has 4, and so on
                     So what exactly is the RT of this? Well in the worst case the last hashed level will have
                     a totally of 2^level nodes, which is not very good, fortunately we can translate the level
                     to be log{base 2}(n) -> 2^level = 2^log(n) = n
                     So in this case we can deduce that we have nlog(n), oops happened to be the same
                     
                     adding these to RT together gives us nlog(n) + nlog(n) which is in the bounds 
                     of O(nlogn) for the run time
    
    There you have it! The last question they asked was are you confident that your 
    solution is optimal and what might you change, asked my friend who worked at ******
    about this and he said to use a small cache instead of a full fledged hashmap
    
    Bottom line: don't be afraid to make mistakes and if you do explain why the msitake 
     occurred and how you would fix it, also EAT BEFORE THE INTERVIEW! Helps out a lot!
     
    This was one of the several coding questions I was asked, they did also ask me about RT
    of ArrayLists/LinkedLists, DFS/BFS, sorting/searching algorithms, arrays & hashtables
    
    This was the last round for ***** this year(round 3) but I sadly didn't make it since I fumbled
    a lot and threw up during the interview ):
    But the recruiter told me to apply for full time in a couple of months, I just applied to work 
    at ***** **** **** for a summer internship, well this is all I have to say about this interview. 
    Signing off of this!
*/
    
}

                        
