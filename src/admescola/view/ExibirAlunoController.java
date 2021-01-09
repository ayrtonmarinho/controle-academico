/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admescola.view;

import admescola.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author iTuhh Z
 */
public class ExibirAlunoController implements Initializable {

    private Usuario selectedUser;

    @FXML
    private Label userName;

    @FXML
    private Label userCpf;

    @FXML
    private Label userSexo;

    @FXML
    private Label userNascimento;

    @FXML
    private Label userEmail;

    @FXML
    private Label userTelefone;

    @FXML
    private Label userUf;

    @FXML
    private Label userCidade;

    @FXML
    private Label userBairro;

    @FXML
    private Label userRua;

    @FXML
    private Label userNumero;

    @FXML
    private Label userCep;

    @FXML
    private Label userComplemento;
    
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void voltarTelaAluno(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TelaAluno.fxml"));
        Parent novaCenaParent = loader.load();
        Scene novaCena = new Scene(novaCenaParent);

        //Da acesso ao controller do ExibirAluno;
        TelaAlunoController controller = loader.getController();
        controller.clickExibirAluno();

        //Pega a informação do Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(novaCena);
        window.show();

    }

    public void initData(Usuario user) {
        selectedUser = user;
        LocalDate dataNascimento = selectedUser.getNascimento();
        userName.setText(selectedUser.getNome());
        userCpf.setText(selectedUser.getCpf());
        userNascimento.setText(format.format(dataNascimento));
        userSexo.setText(selectedUser.getSexo());
        userEmail.setText(selectedUser.getEmail());
        userTelefone.setText(selectedUser.getTelefone());
        userUf.setText(selectedUser.getUf());
        userBairro.setText(selectedUser.getBairro());
        userCidade.setText(selectedUser.getCidade());
        userRua.setText(selectedUser.getRua());
        userCep.setText(selectedUser.getCep());
        userNumero.setText(selectedUser.getNumero());
        userComplemento.setText(selectedUser.getComplemento());
    }

}
