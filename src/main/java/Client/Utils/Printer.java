package Client.Utils;

import ClassesBO.ContentBO;
import java.util.List;

public class Printer {

    public static void printLists(List<String> listToPrint){
        for(String string: listToPrint){
            System.out.println(string);
        }
    }

    public static void printContent(String contentString){
        ContentBO[] contentBOS = Parser.jsonContentToArray(contentString);

        System.out.println("All content available:");
        for(ContentBO contentBO: contentBOS){
            String id = "Id: " + Integer.toString(contentBO.getId());
            String title = "Title: " + contentBO.getTitle();
            String description = "Description: " + contentBO.getDescription();
            System.out.println(id + " " + title + " " + description);
        }
    }


    public static void printContent(List<ContentBO> contentList){
        System.out.println("All content available:");
        for(ContentBO contentBO: contentList){
            String id = "Id: " + Integer.toString(contentBO.getId());
            String title = "Title: " + contentBO.getTitle();
            String description = "Description: " + contentBO.getDescription();
            System.out.println(id + " " + title + " " + description);
        }
    }
    public static void optionsMenu() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Welcome to MyTube, tell us what do you want to do.\n" +
                "0: Exit\n"+
                "1: Upload\n"+
                "2: Download\n" +
                "3: List the available content\n" +
                "4: Search by keyWord\n" +
                "5: Delete Content\n" +
                "6: Modify Content\n");
    }
}
