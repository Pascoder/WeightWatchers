package client;



import chatroom.olmoClient.Controller.Splash_Controller;
import chatroom.olmoClient.Controller.Start_Controller;
import chatroom.olmoClient.Model.Splash_Model;
import chatroom.olmoClient.Model.Start_Model;

import chatroom.olmoClient.Views.Splash_View;
import chatroom.olmoClient.Views.Start_View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class JassClient extends Application {
    private static JassClient mainProgram; // singleton
    private Splash_View splashView;
    private static Start_View view;

    private ServiceLocator serviceLocator; // resources, after initialization

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        if (mainProgram == null) {
            mainProgram = this;
        } else {
            Platform.exit();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Create and display the splash screen and model
        Splash_Model splashModel = new Splash_Model();
        splashView = new Splash_View(primaryStage, splashModel);
        new Splash_Controller(this, splashModel, splashView);
        splashView.start();

        // Display the splash screen and begin the initialization
        splashModel.initialize();
        
    }

  
    public void startApp() {
        Stage appStage = new Stage();
        Start_Model model = new Start_Model();
        view = new Start_View(appStage, model);
        new Start_Controller(model, view);
 
        // Resources are now initialized
        serviceLocator = ServiceLocator.getServiceLocator();

        // Close the splash screen, and set the reference to null, so that all
        // Splash_XXX objects can be garbage collected
        splashView.stop();
        splashView = null;
        view.start();
    }

    
    @Override
    public void stop() {
        serviceLocator.getConfiguration().save();
        if (view != null) {
            // Make the view invisible
            view.stop();
        }

        // More cleanup code as needed
        serviceLocator.getLogger().info("Application terminated");
    }

    // Static getter for a reference to the main program object
    protected static JassClient getMainProgram() {
        return mainProgram;
    }
    
    public static void changeLocales() {
	view.stop();
	Stage appStage = new Stage();
        Start_Model model = new Start_Model();
        view = new Start_View(appStage, model);
        new Start_Controller(model, view);
        view.start();
    }
}
