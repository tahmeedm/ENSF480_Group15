public class TestProgram {
    public static void main(String[] args) {
        System.out.println("Hello from Java in TestProgram!");
        if (args != null && args.length > 0) {
            System.out.print("This is user's input: ");
            for (String arg : args) {
                System.out.print(arg + " ");
            }
            System.out.println();
        }
    }
}
