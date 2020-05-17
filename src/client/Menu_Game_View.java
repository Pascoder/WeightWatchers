package client;

import java.util.Locale;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;



	//Leon


public class Menu_Game_View extends Menu_Basic_View {
    
    
    Menu menu1;
    Menu menu2;

   ToggleGroup tg1;
    
    // MenuItem menu1
    RadioMenuItem menuItem21, menuItem22;

    public Menu_Game_View() {
	super();
	
	
	 menuItem21 = new RadioMenuItem(t.getString("gameMenu.CH"));
	 menuItem22 = new RadioMenuItem(t.getString("gameMenu.FR"));
	    
	
	menu1 = new Menu(t.getString("program.menu.file"));
	menu2 = new Menu(t.getString("gameMenu.Cards"));

	menu2.getItems().addAll(menuItem21, menuItem22);

	tg1 = new ToggleGroup();
	tg1.getToggles().addAll(menuItem21, menuItem22);
	
	if(sl.getConfiguration().isFrenchCards())
	    menuItem22.setSelected(true);
	else
	    menuItem21.setSelected(true);
	
	this.getMenus().addAll(menu1, menu2);

	this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }


}
