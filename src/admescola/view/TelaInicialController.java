/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admescola.view;

import admescola.model.Usuario;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author iTuhh Z
 */
public class TelaInicialController implements Initializable {

    @FXML
    private Button btAluno, btVoltar;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private ObservableList<Usuario> listaAlunos = FXCollections.observableArrayList();

    private File file = new File("ListaAlunos");

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void mudarCenaToAluno(ActionEvent event) throws IOException {
        Parent novaCenaParent = FXMLLoader.load(getClass().getResource("TelaAluno.fxml"));
        Scene novaCena = new Scene(novaCenaParent);

        //Pega a informação do Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(novaCena);
        window.show();
    }

    public void mudarCenaToProfessor(ActionEvent event) throws IOException {
        Parent novaCenaParent = FXMLLoader.load(getClass().getResource("TelaProfessor.fxml"));
        Scene novaCena = new Scene(novaCenaParent);

        //Pega a informação do Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(novaCena);
        window.show();
    }

    public void mudarCenaToDisciplina(ActionEvent event) throws IOException {
        Parent novaCenaParent = FXMLLoader.load(getClass().getResource("TelaDisciplina.fxml"));
        Scene novaCena = new Scene(novaCenaParent);

        //Pega a informação do Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(novaCena);
        window.show();
    }
    
    public void mudarCenaToTurmas(ActionEvent event) throws IOException {
        Parent novaCenaParent = FXMLLoader.load(getClass().getResource("TelaTurma.fxml"));
        Scene novaCena = new Scene(novaCenaParent);

        //Pega a informação do Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(novaCena);
        window.show();
    }

    public void fecharAplicacao(ActionEvent event) {
        System.exit(0);
    }

    public ObservableList<Usuario> getListaAlunos() {
        return listaAlunos;
    }

}
