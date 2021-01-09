/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admescola;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author iTuhh Z
 */
public class AppEscola extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;
   
    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AdminEscola");
        this.primaryStage.setResizable(false);
        this.primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        Parent root = FXMLLoader.load(getClass().getResource("view/TelaInicial.fxml"));
        
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
  
    
    public Stage getPrimaryStage(){
        return primaryStage;
    }
}
