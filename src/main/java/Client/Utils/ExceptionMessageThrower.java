package Client.Utils;

public class ExceptionMessageThrower {

    public static void ioExceptionMessage(Exception e) {
        System.out.println("IO Error: " + e.getMessage());
        e.printStackTrace();
    }
}
