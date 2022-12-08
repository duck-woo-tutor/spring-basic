package playground.memo;

import playground.memo.presentation.Controller;
import playground.memo.service.DefaultMenuService;
import playground.memo.service.MenuCRUD;

public class Application {
    public static void main(String[] args) {
        MenuCRUD menuService = new DefaultMenuService();
        Controller controller = new Controller(menuService);
        controller.run();
    }
}
