package view;

import controller.AppController;

import java.util.Scanner;

public class AppView {
    private final AppController appController;

    public AppView(AppController userController) {
        this.appController = userController;
    }

    public void run() {
        System.out.println("Enter next data: second name, name, surname, birthday, phone-number, gender. Or exit");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String receivedValue = scanner.nextLine();
                if (receivedValue.equalsIgnoreCase("exit")) break;
                appController.checkSizeValue(receivedValue);
                String[] resultArray = appController.getArrayForWriter(receivedValue.split(" "));
                appController.writeResultArray(resultArray);
            }
        }
    }
}
