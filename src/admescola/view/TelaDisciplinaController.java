/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admescola.view;

import admescola.model.Disciplina;
import admescola.model.Turma;
import admescola.util.ResourceManager;
import admescola.util.TextFieldFormatter;
import admescola.util.TratamentoDado;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author iTuhh Z
 */
public class TelaDisciplinaController implements Initializable {

    @FXML
    private TableView<Disciplina> table;

    @FXML
    private TableColumn<Disciplina, String> colCodigo;

    @FXML
    private TableColumn<Disciplina, String> colNome;

    @FXML
    private TableColumn<Disciplina, String> colCargaHoraria;

    @FXML
    private TableColumn<Disciplina, String> colCreditos;

    @FXML
    private TableColumn<Disciplina, String> colPeriodo;

    @FXML
    private TableColumn<Disciplina, String> colAlocada;

    @FXML
    private TextField nome;

    @FXML
    private TextField codigo;

    @FXML
    private ComboBox periodo;

    @FXML
    private ToggleGroup cargaHoraria;

    @FXML
    private RadioButton ch30;

    @FXML
    private RadioButton ch60;

    @FXML
    private ArrayList<Disciplina> tempList;

    private File file = new File("ListaDisciplinas");
    
    private File filet = new File("ListaTurmas");

    private ObservableList<Disciplina> listDisciplinas = FXCollections.observableArrayList();
    
    private ObservableList<Turma> listTurma = FXCollections.observableArrayList();

    @FXML
    private TextField filtro;

    @FXML
    private VBox cadDisciplina;

    //HBOX
    @FXML
    private HBox btsCadastrar;

    @FXML
    private HBox btsAlterar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Carrega Lista;
        if (file.exists()) {
            System.out.println("Ok");
            listDisciplinas = carregarListaDisciplinas();
        }
        if(filet.exists()){
            System.out.println("ListaTurmas Loaded");
            listTurma = carregarListaTurma();
        }

        periodo.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        periodo.setPromptText("Selecione um periodo");
        periodo.setVisibleRowCount(5);

        //Seta Colunas do Layout exibir.
        colCodigo.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("codigo"));
        colNome.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("nome"));
        colCargaHoraria.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("cargaHoraria"));
        colCreditos.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("creditos"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("periodo"));
        colAlocada.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("registrada"));

        //Envolve a ObservableList em uma FilteredList
        FilteredList<Disciplina> filterUser = new FilteredList<Disciplina>(listDisciplinas, b -> true);

        //Seta o filtro do Predicate toda vez que o filtro muda.
        filtro.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterUser.setPredicate(disciplina -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (disciplina.getCodigo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (disciplina.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (disciplina.getCargaHoraria().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (disciplina.getPeriodo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (disciplina.getCreditos().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (disciplina.getRegistrada().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        //Envolve a FilteredList em uma SortedList.
        SortedList<Disciplina> sortedData = new SortedList(filterUser);
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        //Seta os itens na tabela
        table.setItems(sortedData);

        //Ao ter elemento selecionado libera os botoes Exibir, Alterar e Deletar.
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Editable Table
        table.setPlaceholder(new Label("Não há disciplinas cadastradas."));

    }

    //Volta para tela inicial
    public void voltarTelaInicial(ActionEvent event) throws IOException {
        Parent novaCenaParent = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
        Scene novaCena = new Scene(novaCenaParent);

        //Pega a informação do Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        novaCena.setFill(javafx.scene.paint.Color.TRANSPARENT);
        window.setScene(novaCena);
        window.show();

    }

    //Poe visivel o layout de cadastro de disciplina.
    public void clickCadDisciplina() {
        cadDisciplina.setVisible(true);
    }

    //Remove disciplina
    public void removerDisciplina() {
        Alert alerta, aviso;
        alerta = new Alert(Alert.AlertType.CONFIRMATION);
        aviso = new Alert(Alert.AlertType.WARNING);
        int selectedIndex = table.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            if (!listDisciplinas.isEmpty()) {
                Disciplina selectedUser;
                selectedUser = table.getSelectionModel().getSelectedItem();
                if (!selectedUser.isAlocada() == true) {
                    alerta.setTitle("Remover Disciplina");
                    alerta.setContentText("Deseja remover " + selectedUser.getNome() + "?");
                    Optional<ButtonType> resposta = alerta.showAndWait();
                    if (resposta.get() == ButtonType.OK) {
                        listDisciplinas.remove(selectedUser);
                        salvarListaDisciplinas();
                    }
                } else {
                    aviso.setTitle("Remover Disciplina");
                    aviso.setContentText(selectedUser.getNome() + " está alocada em uma turma. Não é possivel remover.");
                    aviso.showAndWait();
                }
            } else {
                aviso.setTitle("Remover Disciplina");
                aviso.setContentText("Não há disciplinas cadastradas.");
                aviso.showAndWait();
            }
        } else {
            aviso.setTitle("Remover Disciplina");
            aviso.setContentText("Você não selecionou uma disciplina.");
            aviso.showAndWait();
        }
    }

    private boolean entradaValida() {
        String errorMessage = "";

        if (nome.getText() == null || nome.getText().length() == 0) {
            errorMessage += "'Nome'campo vazio. Preencha.\n";
        }
        if (!TratamentoDado.textAlphabet(nome)) {
            errorMessage += "'Nome' campo deve ser apenas letras.\n";
        }
        if (codigo.getText() == null) {
            errorMessage += "'Código' campo vazio.\n";
        }
        if (jaExiste(codigo.getText())) {
            errorMessage += "'Código' já cadastrado.\n";
        }
        if (!TratamentoDado.codigoFormat(codigo)) {
            errorMessage += "'Codigo' inválido. \n";
        }
        if (periodo.getValue() == null) {
            errorMessage += "'Periodo' campo vazio. \n";
        }
        if (!(ch30.isSelected() || ch60.isSelected())) {
            errorMessage += "'Carga Horária' inválida. Selecione uma das opções.\n";
        }

        //Inserir validação de data de nascimento.
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos Inválidos");
            alert.setHeaderText("Por favor, corrija os campos inválidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }

    }

    public void btCadastro() {
        RadioButton radio = (RadioButton) cargaHoraria.getSelectedToggle();
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        String creditos;
        if (entradaValida()) {
            if (radio.getText().equalsIgnoreCase("30 horas")) {
                creditos = "2";
            } else {
                creditos = "4";
            }
            Disciplina disciplina = new Disciplina(codigo.getText(), nome.getText(), radio.getText(), creditos, periodo.getValue().toString());

            listDisciplinas.add(disciplina);
            salvarListaDisciplinas();
            limparCampos();
            aviso.setTitle("Cadastro Aluno");
            aviso.setContentText("A disciplina " + disciplina.getNome() + " foi cadastrada com sucesso.");
            aviso.showAndWait();
        }
        System.out.println(listDisciplinas.size());
    }

    private boolean jaExiste(String valor) {

        int tam = listDisciplinas.size();
        for (int i = 0; i < tam; i++) {
            if (listDisciplinas.get(i).getCodigo().equalsIgnoreCase(valor)) {
                return true;
            }
        }
        return false;
    }

    public void btAlterar() {
        Alert aviso;
        aviso = new Alert(Alert.AlertType.WARNING);
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (!listDisciplinas.isEmpty()) {
                Disciplina user;
                user = table.getSelectionModel().getSelectedItem();
                setAlterarData(user);
                if (user.isAlocada()) {
                    aviso.setTitle("Alterar Disciplina");
                    aviso.setContentText("A disciplina " + user.getNome() + " está alocada em uma turma. Por este motivo não será permitido alterar Periodo e Carga Horária.");
                    aviso.showAndWait();
                    ch30.setDisable(true);
                    ch60.setDisable(true);
                    periodo.setDisable(true);
                }
                btsAlterar.setVisible(true);
                btsCadastrar.setVisible(false);
                codigo.setDisable(true);
                table.setDisable(true);
            }
        } else {
            aviso.setTitle("Alterar Disciplina");
            aviso.setContentText("Você não selecionou uma disciplina.");
            aviso.showAndWait();
        }
    }

    public void btAltVoltar() {
        btsAlterar.setVisible(false);
        btsCadastrar.setVisible(true);
        codigo.setDisable(false);
        table.setDisable(false);
        ch30.setDisable(false);
        ch60.setDisable(false);
        periodo.setDisable(false);
        limparCampos();
    }

    public void setAlterarData(Disciplina user) {
        Disciplina selectedUser = user;

        codigo.setText(selectedUser.getCodigo());
        nome.setText(selectedUser.getNome());
        periodo.getSelectionModel().select(selectedUser.getPeriodo());
        if (selectedUser.getCargaHoraria().equalsIgnoreCase(ch30.getText())) {
            cargaHoraria.selectToggle(ch30);
        } else if (selectedUser.getCargaHoraria().equalsIgnoreCase(ch60.getText())) {
            cargaHoraria.selectToggle(ch60);
        }

    }

    public void btConfirmar() {
        Alert alerta;
        alerta = new Alert(Alert.AlertType.CONFIRMATION);
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        RadioButton radio = (RadioButton) cargaHoraria.getSelectedToggle();
        Disciplina user = table.getSelectionModel().getSelectedItem();
        if (entradaValidaAlt()) {
            alerta.setTitle("Alterar Dados");
            alerta.setContentText("Deseja alterar os dados de " + user.getNome() + "?");
            Optional<ButtonType> resposta = alerta.showAndWait();
            if (resposta.get() == ButtonType.OK) {
                String creditos = null;
                if (radio.getText().equalsIgnoreCase("30 horas")) {
                    creditos = "2";
                } else if (radio.getText().equalsIgnoreCase("60 horas")) {
                    creditos = "4";
                }
                user.setNome(nome.getText());
                user.setCargaHoraria(radio.getText());
                user.setCreditos(creditos);
                user.setPeriodo(periodo.getValue().toString());
                listDisciplinas.set(selectedIndex, user);
                alterarNaTurma(user);
                salvarListaDisciplinas();
                salvarListaTurmas();
                btAltVoltar();
            }
        }

    }

    private boolean entradaValidaAlt() {
        String errorMessage = "";

        if (nome.getText() == null || nome.getText().length() == 0) {
            errorMessage += "'Nome'campo vazio. Preencha.\n";
        }
        if (!TratamentoDado.textAlphabet(nome)) {
            errorMessage += "'Nome' campo deve ser apenas letras.\n";
        }
        if (periodo.getValue() == null) {
            errorMessage += "'Periodo' campo vazio. \n";
        }
        if (!(ch30.isSelected() || ch60.isSelected())) {
            errorMessage += "'Carga Horária' inválida. Selecione uma das opções.\n";
        }

        //Inserir validação de data de nascimento.
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos Inválidos");
            alert.setHeaderText("Por favor, corrija os campos inválidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }

    }

    public void limparCampos() {
        nome.clear();
        codigo.clear();
        cargaHoraria.selectToggle(null);
        periodo.getSelectionModel().clearSelection();
    }

    @FXML
    private ObservableList<Disciplina> carregarListaDisciplinas() {
        try {
            List<Disciplina> list = (List<Disciplina>) ResourceManager.load("ListaDisciplinas");
            return FXCollections.observableArrayList(list);
        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();
    }

    @FXML
    private void salvarListaDisciplinas() {
        tempList = new ArrayList<>(listDisciplinas);

        try {
            ResourceManager.save(tempList, "ListaDisciplinas");
        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Load e Save de turma
    @FXML
    private ObservableList<Turma> carregarListaTurma() {
        try {
            List<Turma> list = (List<Turma>) ResourceManager.load("ListaTurmas");
            return FXCollections.observableArrayList(list);
        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();
    }

    @FXML
    private void salvarListaTurmas() {
        ArrayList<Turma> tempList = new ArrayList<>(listTurma);

        try {
            ResourceManager.save(tempList, "ListaTurmas");
        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void maskCodigo() {
        TextFieldFormatter mascara = new TextFieldFormatter();
        mascara.setMask("UUU####");
        mascara.setCaracteresValidos("0123456789ABCDEFGHIJKLMNOPQRSTUVXYZ");
        mascara.setTf(codigo);
        mascara.formatter();

    }
    
    public void alterarNaTurma(Disciplina user) {
        int index = -1;
        if (!listTurma.isEmpty()) {
            for (Turma turma : listTurma) {
                if (turma.getDisciplina().getCodigo().equalsIgnoreCase(user.getCodigo())) {
                    index = listTurma.indexOf(turma);
                    turma.setDisciplina(user);
                    listTurma.set(index, turma);
                }
            }
        }

    }

}
