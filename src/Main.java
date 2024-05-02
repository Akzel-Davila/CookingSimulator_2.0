public class Main {
    public static void main(String[] args) {

        MainFrame frame = new MainFrame("Cooking Sim 2.0 Electric Boogaloo");
        Ingredients i = new Ingredients("lettuce,tomato");
        Cooking c = new Cooking(i);
    }
}