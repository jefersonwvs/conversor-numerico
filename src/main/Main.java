package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();

        //String numero = "AEFAAEF";
        //System.out.println(Conversor.transforma("ABC", 16, 15));
        //System.out.println(Conversor.configuraAlgarismo(9));
    }

    @Override
    public void start(Stage palco) {
        
        try {
            FXMLLoader carregador = new FXMLLoader(getClass().getResource("/main/Tela.fxml"));
            AnchorPane painel = carregador.load();
            
            Scene cena = new Scene(painel);
            palco.setScene(cena);
            palco.getIcons().add(new Image("/main/icon-abacus.png"));
            palco.setTitle("Conversor de número entre bases numéricas");
            palco.setResizable(false);
            palco.show();
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
