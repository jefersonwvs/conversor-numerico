package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class TelaController implements Initializable {

    //@FXML
    //private TextField txtBaseA;
    
    @FXML
    private ChoiceBox chBoxBaseA;
    
    @FXML
    private TextField txtNumeroBaseA;
    
    @FXML
    private Button btAparaB;
    
    @FXML
    private ChoiceBox chBoxBaseB;
    
    @FXML
    private TextField txtNumeroBaseB;
    
    @FXML
    private Button btBparaA;
    
    @FXML
    private void acaoBtAparaB() {
        try {
            int baseA = Integer.parseInt(chBoxBaseA.getValue().toString());
            String numero = txtNumeroBaseA.getText();
            numero = numero.replace(",", ".");
            int baseB = Integer.parseInt(chBoxBaseB.getValue().toString());
            txtNumeroBaseB.setText(Conversor.transforma(numero, baseA, baseB));
        } catch (IllegalArgumentException e) {
            Alert alerta = new Alert(null);
            alerta.setTitle("Erro");
            alerta.setContentText(e.getMessage());
            alerta.getButtonTypes().add(ButtonType.OK);
            alerta.show();
        }
    }
    
    @FXML
    private void acaoBtBparaA() {
        try {
            int baseB = Integer.parseInt(chBoxBaseB.getValue().toString());
            String numero = txtNumeroBaseB.getText();
            numero = numero.replace(",", ".");
            int baseA = Integer.parseInt(chBoxBaseA.getValue().toString());
            txtNumeroBaseA.setText(Conversor.transforma(numero, baseB, baseA));
        } catch (IllegalArgumentException e) {
            Alert alerta = new Alert(null);
            alerta.setTitle("Erro");
            alerta.setContentText(e.getMessage());
            alerta.getButtonTypes().add(ButtonType.OK);
            alerta.show();
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> bases = FXCollections.observableArrayList(2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        chBoxBaseA.getItems().addAll(bases);
        chBoxBaseA.getSelectionModel().select(8);
        chBoxBaseB.getItems().addAll(bases);
        chBoxBaseB.getSelectionModel().select(0);
    }    
    
}
