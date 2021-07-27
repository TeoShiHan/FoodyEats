public class App {
    public static void main(String[] args) throws Exception {
        
        generate[] genny = new generate[2];
        genny[0] = new generate();
        genny[1] = new generate();
        for (int i=0;i<2;i++){
            System.out.println(genny[i]);
        }
    }
}
