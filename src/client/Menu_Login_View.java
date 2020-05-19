package client;

import java.util.Locale;



import client.Translator_JC;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.ServiceLocator;

public class Menu_Login_View extends Menu_Basic_View {

    Menu menu1;
    Menu menu2;

    ToggleGroup tg1;


    public Menu_Login_View() {
	super();
	
	menu1 = new Menu();
	
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
	    	Platform.runLater(new Runnable(){
				@Override
				public void run() {
					sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
					sl.setTranslator(new Translator_JC(locale.getLanguage()));
					sl.getConfiguration().save();
//					JassClient.changeLocales();
				}

			});
	    });

	}

	ImageView icon1 = new ImageView(new Image(getClass().getResourceAsStream("Icon-Language.png")));
	icon1.setFitHeight(20);
	icon1.setFitWidth(40);
	menu1.setGraphic(icon1);
	menu1.setText(t.getString("program.menu.language"));


	this.getMenus().addAll(menu1);

	this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    
	
	

	
    }
    
 

}
