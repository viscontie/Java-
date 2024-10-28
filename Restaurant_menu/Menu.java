/*Menu class stores and displays multiple MenuItems
by copying them into an array
@Ella Visconti
*/
public class Menu{
    private static MenuItem[] items;

    //constructor initializes variables and array
    public Menu( MenuItem[] items){
        Menu.items = new MenuItem[4];
        Menu.items = items;
    }
    //returns the cheapest item in the array
    public static MenuItem cheapestItem(MenuItem[] items){
        MenuItem cheapest = items[0];
        for (int i = 0; i < items.length; i++){
            //System.out.println(i);
            if (Menu.items[i].getPrice() < cheapest.getPrice()){
                cheapest = items[i];
            }
        }
        return cheapest;
    }
    //displays the Menu array
    public void show()
    {
        System.out.println("Food");
        int i;
        for (i = 0; i < items.length; i++){
            if (items[i].getDrink() == false)
            {
                System.out.println(items[i].toString());
            }
        }
        System.out.println();
        System.out.println("Drink");
        for (i = 0;i < items.length; i++){
            if (items[i].getDrink() == true)
            {
                System.out.println(items[i].toString());
            }
        }
            
    
}
    //creates MenuItems array and Menu object
    public static void main(String[] args){
        MenuItem menuItem1 = new MenuItem("Burrito",false, 1050,true);
        MenuItem menuItem2 = new MenuItem("3 Tacos",false, 1350,true);
        MenuItem menuItem3 = new MenuItem("Lemonade",true, 399,false);
        MenuItem menuItem4 = new MenuItem("Jarritos",true, 300,true);
        MenuItem[] menuItems= {menuItem1,menuItem2,menuItem3,menuItem4};
        Menu items= new Menu(menuItems);
        items.show();
        System.out.println(cheapestItem(items.items).getName() + " is the cheapest item.");
    }
}