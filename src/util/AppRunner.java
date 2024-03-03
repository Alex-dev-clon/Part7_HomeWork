package util;

import controller.AppController;
import model.MyRepository;
import model.impl.AppRepository;
import view.AppView;

public class AppRunner {
    private AppRunner() {
    }

    public static void startApplication() {
        MyRepository repository = new AppRepository();
        AppController controller = new AppController(repository);
        try {
            AppView view = new AppView(controller);
            view.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
