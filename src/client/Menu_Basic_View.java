package client;

import java.util.Locale;
import java.util.logging.Logger;
import client.Translator_JC;
import client.ServiceLocator_JC;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Menu_Basic_View extends MenuBar {

    ServiceLocator_JC sl;
    Translator_JC t;
    Logger logger;
    Menu menu1, menu6;
    MenuItem infoMenu;

    ToggleGroup tg1;

    protected Menu_Basic_View() {
	super();
	sl = ServiceLocator_JC.getServiceLocator();
	t = ServiceLocator_JC.getServiceLocator().getTranslator();
	sl.getLogger();

	menu1 = new Menu();
	menu6 = new Menu();

	tg1 = new ToggleGroup();

	for (Locale locale : sl.getLocales()) {
	    RadioMenuItem itemLang = new RadioMenuItem(locale.getLanguage());

	    ImageView icon = new ImageView(
		    new Image(getClass().getResourceAsStream("Icon-" + locale.getLanguage() + ".png")));
	    icon.setFitHeight(20);
	    icon.setFitWidth(20);
	    itemLang.setGraphic(icon);
	    menu1.getItems().add(itemLang);
	    tg1.getToggles().add(itemLang);

	    if (locale.equals(t.getCurrentLocale())) {
		itemLang.setSelected(true);
	    }

	    itemLang.setOnAction(event -> {
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
			sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
			sl.setTranslator(new Translator_JC(locale.getLanguage()));
			sl.getConfiguration().save();
			ClientController.changeLanguage();
		    }

		});
	    });

	}

	ImageView icon1 = new ImageView(new Image(getClass().getResourceAsStream("Icon-Language.png")));
	icon1.setFitHeight(20);
	icon1.setFitWidth(40);
	menu1.setGraphic(icon1);

	infoMenu = new MenuItem();
	menu6.getItems().add(infoMenu);
	
	this.getMenus().addAll(menu1, menu6);

    }

    protected void setTextsBasic() {
	this.t = ServiceLocator_JC.getServiceLocator().getTranslator();
	menu1.setText(t.getString("program.menu.language"));
	menu6.setText(t.getString("menu.Info"));
	infoMenu.setText(t.getString("menu.Info1"));
    }

}
