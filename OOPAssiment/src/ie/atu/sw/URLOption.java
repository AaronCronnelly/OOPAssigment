package ie.atu.sw;

public class URLOption {

   
    public static void execute() {
        System.out.println("Executing URL Option");
        specifyURL();
    }

    private static void specifyURL() {
        // Logic for specifying a URL
        System.out.println("You selected to specify a URL.");
        System.out.print("Enter the URL: ");
        String url = Runner.kb.nextLine();

       
        System.out.println("URL specified: " + url);
    }
}
