package hensel;

public class Application {

    public static void main(String[] args) {
        HenselLifting lifting = Parser.parse(args);

        lifting.solve();
        lifting.printSolutions();
    }
}
