package client;

import java.util.Locale;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Menu_Lobby_View extends Menu_Basic_View {
    
    
	Menu menu1;
    Menu menu2;

    ToggleGroup tg1;

    // MenuItems menu2
//    MenuItem menuItem21 = new MenuItem("item21");
//    MenuItem menuItem22 = new MenuItem("item22");
//    MenuItem menuItem23 = new MenuItem("item23");

    public Menu_Lobby_View() {
	super();
	
	menu1 = new Menu();
	menu2 = new Menu();
	
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
		sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
		sl.setTranslator(new Translator_JC(locale.getLanguage()));
		JassClient.changeLocales();

	    });

	}

	ImageView icon1 = new ImageView(new Image(getClass().getResourceAsStream("Icon-Language.png")));
	icon1.setFitHeight(20);
	icon1.setFitWidth(40);
	menu1.setGraphic(icon1);
	menu1.setText(t.getString("program.menu.language"));

//	menu2.getItems().addAll(menuItem21, menuItem22, menuItem23);

	this.getMenus().addAll(menu1);

	this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }


}
