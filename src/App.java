public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println("Good Morning");
        System.out.println("Nathan printed this 3rd line");
        generate[] genny = new generate[2];
        genny[0] = new generate();
        genny[1] = new generate();
        for (int i=0;i<2;i++){
            System.out.println(genny[i]);
        }
        
        Rider rider = new Rider();
    }
}
