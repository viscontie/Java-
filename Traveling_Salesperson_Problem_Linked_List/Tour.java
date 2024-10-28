/**
 * This class creates a Tour of Points using a 
 * Linked List implementation.  The points can
 * be inserted into the list using two heuristics.
 * @author Ella Visconti and Katherine Baker
 * @author Layla Oesper, modified code 09-22-2017
 */
import java.util.StringJoiner;
public class Tour {

    /** A helper class that defines a single node for use in a tour.
     * A node consists of a Point, representing the location of that
     * city in the tour, and a pointer to the next Node in the tour.
     */
    private class Node {
        private Point p;
        private Node next;
	
        /** Constructor creates a new Node at the given Point newP
         * with an intital next value of null.
         * @param newP - the point to associate with the Node.
         */
        public Node(Point newP) {
            p = newP;
            next = null;
        }

        /** Constructor creates a new Node at the given Point newP
         * with the specified next node.
         * @param newP - the point to associate with the Node.
         * @param nextNode - the nextNode this node should point to.
         */
        public Node(Point newP, Node nextNode) {
            p = newP;
            next = nextNode;
        }
	
        /**
         * Return the Point associated with this Node. 
         * (Same value can also be accessed from a Node object node
         * using node.p)
         * @return The Point object associated with this node.
         */
        public Point getPoint() {
            return p;
        }
        
        /**
         * Return the next Node associated with this Node. 
         * (Same value can also be accessed from a Node object node
         * using node.next)
         * @return The next Node object linked from this node..
         */
	   public Node getNext() {
	       return next;
	   }
          
    } // End Node class
    

    // Tour class Instance variables
    private Node head;
    private int size;
    //Add other instance variables you think might be useful.
    
    
    /**
     * Constructor for the Tour class.  By default sets head to null.
     */
    public Tour() {
        head = null;
    }
    
    // ADD YOUR METHODS BELOW HERE
    /**
     * 
     * @return the int size of the LinkedList
     */
    public int size()   {
        return size; 
    }

    /**
     * creates a StringJoiner that returns a string
     * of all the points in the list.
     */
    public String toString()    {
        StringJoiner joiner = new StringJoiner("\n");

        Node current = head;
        while (current != null) {
            joiner.add(current.getPoint().toString());
            current = current.next;
        }
        return joiner.toString();
    }

    /**
     * draws the route through the points
     * by drawing the point and the line through the point it connects to
     */
    public void draw()  {
        Node currentPoint = head;
        Node nextPoint = head.next;
        for (int i = 0; i < size; i++)  {
            if (i < size - 1) {
                (currentPoint.getPoint()).draw();
                (currentPoint.getPoint()).drawTo((nextPoint.getPoint()));
                currentPoint = nextPoint;
                nextPoint = nextPoint.next;
            } else  {
                (currentPoint.getPoint()).draw();
                (currentPoint.getPoint()).drawTo(head.getPoint());
            }
        }
    }

    /**
     * calculates the total distance of the route through the list
     * @return the distance
     */
    public double distance()    {
        double distance = 0;
        Node currentPoint = head;
        Node nextPoint = head.next;
        for (int i = 0; i < size; i++)  {
            if (i < size - 1)   {
                distance = distance + (currentPoint.getPoint()).distanceTo((nextPoint.getPoint()));
                currentPoint = nextPoint;
                nextPoint = nextPoint.getNext();
            } else  {
                distance = distance + (currentPoint.getPoint()).distanceTo((head.getPoint()));
            }
        }
        return distance;
    }

    /**
     * constructs a route through all of the points that is created by
     * adding Point p after the point it is nearest to
     * @param p
     */
    public void insertNearest(Point p)  {
        double minDist = Double.MAX_VALUE;
        Node current = head;
        Node refIndex = head;
        if (head == null) {
            Node n = new Node(p, head);
            this.head = n;
        }
        else    {
            double distance = 0;
            for (int i = 0; i < size; i++)  {
                distance = p.distanceTo(current.getPoint());
                if (distance < minDist)   {
                    minDist = distance;
                    refIndex = current;
                }
                current = current.next;
            }
            refIndex.next = new Node(p, refIndex.next);
        }
        size++;
    }
    
    /**
     * constructs the shortest route through the points by
     * comparing the total distances of each route with p inserted between each point
     * and inserts Point p where it would result in the shortest total distance
     * @param p
     */
    public void insertSmallest(Point p)     {
        double smallestDist = Double.MAX_VALUE;
        double newDistance = 0;
        Node current = head;
        Node refNode = head;
        double currentTotalDistance = 0;
        if (head == null) {
            Node n = new Node(p, head);
            this.head = n;
        }
        else    {
            double distance = 0;
            for (int i = 0; i < size; i++)  {
                if (current.next == null)   {
                    distance = (current.getPoint()).distanceTo(head.getPoint());
                }   else    {
                    distance = (current.getPoint()).distanceTo(current.next.getPoint());
                }
                currentTotalDistance = currentTotalDistance + distance;
                current = current.next;
            }
            current = head;
            for (int i = 0; i < size; i++)  {
                if (current.next == null)   {
                    newDistance = currentTotalDistance - (current.getPoint()).distanceTo(head.getPoint()) +
                (current.getPoint()).distanceTo(p) + p.distanceTo(head.getPoint());
                } else  {
                    newDistance = currentTotalDistance - (current.getPoint()).distanceTo(current.next.getPoint()) +
                (current.getPoint()).distanceTo(p) + p.distanceTo(current.next.getPoint());
                }
                if (newDistance < smallestDist)    {
                    smallestDist = newDistance;
                    refNode = current;
                }
                current = current.next;
            }
            refNode.next = new Node(p, refNode.next);
    }
    size++;
}
    // ADD YOUR METHODS ABOVE HERE
   
    public static void main(String[] args) {
        /* Use your main() function to test your code as you write it. 
         * This main() will not actually be run once you have the entire
         * Tour class complete, instead you will run the NearestInsertion
         * and SmallestInsertion programs which call the functions in this 
         * class. 
         */
        
        
        //One example test could be the follow (uncomment to run):
        
        Tour tour = new Tour();
        Point p = new Point(0,0);
        tour.insertNearest(p);
        p = new Point(0,100);
        tour.insertNearest(p);
        p = new Point(100, 100);
        tour.insertNearest(p);
        System.out.println("Tour distance =  " + tour.distance());
        System.out.println("Number of points = " + tour.size());
        System.out.println(tour.toString());
        
         

        // the tour size should be 3 and the distance 341.42 (don't forget to include the trip back
        // to the original point)
    
        // uncomment the following section to draw the tour, setting w and h to the max x and y 
        // values that occur in your tour points
	
        /*
        int w = 100 ; //Set this value to the max that x can take on
        int h = 100 ; //Set this value to the max that y can take on
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);
        StdDraw.setPenRadius(.005);
        tour.draw(); 
        */
    }
   
}