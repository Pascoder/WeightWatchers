package client;

import java.util.logging.Logger;
import client.Translator_JC;
import client.ServiceLocator_JC;
import javafx.scene.control.MenuBar;

public class Menu_Basic_View extends MenuBar {

    ServiceLocator_JC sl;
    Translator_JC t;
    Logger logger;

    protected Menu_Basic_View() {
	super();
	sl = ServiceLocator_JC.getServiceLocator();
	t = ServiceLocator_JC.getServiceLocator().getTranslator();
	sl.getLogger();

    }

}
