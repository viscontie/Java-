 /* class MenuItem creates a menu of items with four instance variables
 @Ella Visconti
 */
public class MenuItem {
   
    private String name;
    private boolean isDrink;
    private int price;
    private boolean withChips;

    //should be static because the conversion form dollars to pound always remains the same, so we do not want to be able to change it 
    final static double DOLLARS_PER_POUND = 1.2;

    //constructor, initializing variables
    public MenuItem(String name, boolean isDrink, int price){
        this.name = name;
        this.isDrink = isDrink;
        this.price = price;
    }
    //4 instance variable constructor
    public MenuItem(String name, boolean isDrink, int price, boolean withChips){
        this.name = name;
        this.isDrink = isDrink;
        this.price = price;
        this.withChips = withChips;
    }

    //returns the name
    public String getName(){
        return name;
    }
    //returns if there is a drink or not
    public boolean getDrink(){
        return isDrink;
    }
    //returns price of item
    public int getPrice(){
        return price;
    }
    //returns if there are chips with the item
    public boolean getChips(){
        return withChips;
    }
    //converts to correct price and returns the info for the item
    public String toString(){
        double dollars = (double)price/100;
        double pounds = dollars*DOLLARS_PER_POUND;
        String formattedDollar = String.format("%.2f",dollars);
        String formattedPounds = String.format("%.2f",pounds);
        return (name + " $" + formattedDollar+" £" + formattedPounds);
    }
    //creates menu object
    public static void main(String[] args){
        MenuItem menuItem1 = new MenuItem("Burrito",false, 1050,true);
        MenuItem menuItem2 = new MenuItem("Lemonade",true, 399,false);
        System.out.println(menuItem1);
        System.out.println(menuItem2);
    
    }
}
