/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admescola.view;

import admescola.AppEscola;
import admescola.model.Turma;
import admescola.model.Usuario;
import admescola.util.ResourceManager;
import admescola.util.TextFieldFormatter;
import admescola.util.TratamentoDado;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author iTuhh Z
 */
public class TelaProfessorController implements Initializable {

    @FXML
    private TableView<Usuario> table;

    @FXML
    private TableColumn<Usuario, String> colCpf;

    @FXML
    private TableColumn<Usuario, String> colRg;

    @FXML
    private TableColumn<Usuario, String> colNome;

    @FXML
    private TextField nome;

    @FXML
    private TextField cpf;

    @FXML
    private TextField rg;

    @FXML
    private TextField telefone;

    @FXML
    private TextField email;

    @FXML
    private TextField cidade;

    @FXML
    private ChoiceBox<String> uf;

    @FXML
    private TextField bairro;

    @FXML
    private TextField rua;

    @FXML
    private TextField numero;

    @FXML
    private TextField cep;

    @FXML
    private TextField complemento;

    @FXML
    private DatePicker nascimento;

    @FXML
    private ToggleGroup sexo;

    @FXML
    private RadioButton masc;

    @FXML
    private RadioButton fem;

    @FXML
    private RadioButton outros;

    @FXML
    private TextField filtro;

    @FXML
    private ArrayList<Usuario> tempList;

    @FXML
    private VBox cadProfessor;
    @FXML
    private VBox exibirProfessor;

    //Variaveis do TextField (Alterar)
    @FXML
    private TextField altNome;

    @FXML
    private TextField altCpf;

    @FXML
    private TextField altRg;

    @FXML
    private TextField altTelefone;

    @FXML
    private TextField altEmail;

    @FXML
    private TextField altCidade;

    @FXML
    private ChoiceBox<String> altUf;

    @FXML
    private TextField altBairro;

    @FXML
    private TextField altRua;

    @FXML
    private TextField altNumero;

    @FXML
    private TextField altCep;

    @FXML
    private TextField altComplemento;

    @FXML
    private DatePicker altNascimento;

    @FXML
    private ToggleGroup altSexo;

    @FXML
    private RadioButton altMasc;

    @FXML
    private RadioButton altFem;

    @FXML
    private RadioButton altOutros;

    @FXML
    private VBox telaAlterar;

    private Usuario selectedUser;

    private File file = new File("ListaProfessores");

    private File filet = new File("ListaTurmas");

    private AppEscola app;

    @FXML
    private Button remover;

    @FXML
    private Button alterar;

    @FXML
    private Button exibir;

    private LocalDate antes, depois;

    private ObservableList<Usuario> listProfessores = FXCollections.observableArrayList();

    private ObservableList<Turma> listTurma = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Carrega Lista;
        if (file.exists()) {
            System.out.println("Ok");
            listProfessores = carregarListaProfessor();
        }
        if (filet.exists()) {
            System.out.println("Filet ok");
            listTurma = carregarListaTurma();
        }

        //DatePicker limiter
        antes = LocalDate.of(1945, Month.JANUARY, 1);
        depois = LocalDate.of(1998, Month.JANUARY, 1);

        //Choice Box Atribuindo os Valores
        uf.getItems().addAll("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES",
                "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PE", "PI", "PR", "RJ",
                "RN", "RO", "RR", "RS", "SC", "SC", "SE", "SP", "TO");
        altUf.getItems().addAll("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES",
                "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PE", "PI", "PR", "RJ",
                "RN", "RO", "RR", "RS", "SC", "SC", "SE", "SP", "TO");

        //Seta Colunas do Layout exibir.
        colCpf.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));
        colNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
        colRg.setCellValueFactory(new PropertyValueFactory<Usuario, String>("rg"));

        //Envolve a ObservableList em uma FilteredList
        FilteredList<Usuario> filterUser = new FilteredList<Usuario>(listProfessores, b -> true);

        //Seta o filtro do Predicate toda vez que o filtro muda.
        filtro.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterUser.setPredicate(usuario -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (usuario.getCpf().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (usuario.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (usuario.getRg().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        //Envolve a FilteredList em uma SortedList.
        SortedList<Usuario> sortedData = new SortedList(filterUser);
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        //Seta os itens na tabela
        table.setItems(sortedData);

        //Ao ter elemento selecionado libera os botoes Exibir, Alterar e Deletar.
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        table.setPlaceholder(new Label("Não há professores cadastrados."));

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

    public void exibirProfessor(ActionEvent event) throws IOException {
        Alert aviso;
        aviso = new Alert(AlertType.WARNING);
        int userSelected = table.getSelectionModel().getSelectedIndex();
        if (userSelected >= 0) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ExibirProfessor.fxml"));
            Parent novaCenaParent = loader.load();
            Scene novaCena = new Scene(novaCenaParent);

            //Da acesso ao controller do ExibirAluno;
            ExibirProfessorController controller = loader.getController();
            controller.initData(table.getSelectionModel().getSelectedItem());

            //Pega a informação do Stage
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(novaCena);
            window.show();
        } else {
            aviso.setTitle("");
            aviso.setContentText("Você não selecionou um professor.");
            aviso.showAndWait();
        }

    }

    /*public ObservableList<Usuario> getUsers() {
        return listProfessores;
    }*/
    //Poe visivel o layout de cadastro de professor.
    public void clickCadProfessor() {
        cadProfessor.setVisible(true);
        exibirProfessor.setVisible(false);
        telaAlterar.setVisible(false);
    }

    //Poe visivel o layout de exibir professor.
    public void clickExibirProfessor() {
        exibirProfessor.setVisible(true);
        cadProfessor.setVisible(false);
        telaAlterar.setVisible(false);
    }

    //Remove usuário
    public void removerUsuario() {
        Alert alerta, aviso;
        alerta = new Alert(AlertType.CONFIRMATION);
        aviso = new Alert(AlertType.WARNING);
        int selectedIndex = table.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            if (!listProfessores.isEmpty()) {
                Usuario selectedUser;
                selectedUser = table.getSelectionModel().getSelectedItem();
                alerta.setTitle("Remover Professor");
                alerta.setContentText("Deseja remover " + selectedUser.getNome() + "?");
                Optional<ButtonType> resposta = alerta.showAndWait();
                if (resposta.get() == ButtonType.OK) {
                    listProfessores.remove(selectedUser);
                    salvarListaProfessores();
                    if (!listTurma.isEmpty()) {
                        removerDaTurma(selectedUser);
                        salvarListaTurmas();
                    }
                }
            } else {
                aviso.setTitle("Remover Aluno");
                aviso.setContentText("Não há professores cadastrados.");
                aviso.showAndWait();
            }
        } else {
            aviso.setTitle("Remover Professor");
            aviso.setContentText("Você não selecionou um professor.");
            aviso.showAndWait();
        }
    }

    /**
     * Valida a entrada do usuário nos campos de texto.
     *
     * @return true se a entrada é válida
     */
    private boolean entradaValida() {
        String errorMessage = "";

        if (nome.getText() == null || nome.getText().length() == 0) {
            errorMessage += "'Nome'campo vazio. Preencha.\n";
        }
        if (!TratamentoDado.textAlphabet(nome)) {
            errorMessage += "'Nome' campo deve ser apenas letras.\n";
        }
        if (cpf.getText() == null || cpf.getText().length() != 11) {
            errorMessage += "'CPF' campo vazio, insuficiente ou acima permitido. Preencha.\n";
        }
        if (!TratamentoDado.isNumber(cpf)) {
            errorMessage += "'CPF' apenas números são aceitos.\n";
        }
        if (jaExiste(cpf.getText())) {
            errorMessage += "CPF já cadastrado!\n";
        }
        if (rg.getText() == null || rg.getText().length() == 0) {
            errorMessage += "'RG' campo vazio.\n";
        }
        if (!TratamentoDado.isNumber(rg)) {
            errorMessage += "'RG' apenas números são aceitos.\n";
        }
        if (email.getText() == null || email.getText().length() == 0) {
            errorMessage += "'E-mail' campo vazio. Preencha.\n";
        }
        if (!TratamentoDado.emailFormat(email)) {
            errorMessage += "E-mail inválido. Digite um e-mail valido.\n";
        }
        if (cidade.getText() == null || cidade.getText().length() == 0) {
            errorMessage += "'Cidade' campo vazio. Preencha.\n";
        }
        if (telefone.getText() == null || telefone.getText().length() == 0) {
            errorMessage += "'Telefone' campo vazio ou insuficente. Preencha.\n";
        }
        if (uf.getValue() == null) {
            errorMessage += "'UF' campo vazio.Preencha. \n";
        }
        if (bairro.getText() == null || bairro.getText().length() == 0) {
            errorMessage += "'Bairro' campo vazio. Preencha.\n";
        }
        if (rua.getText() == null || rua.getText().length() == 0) {
            errorMessage += "'Rua' campo vazio. Preencha.\n";
        }
        if (numero.getText() == null || numero.getText().length() == 0) {
            errorMessage += "'Número' campo vazio. Preencha.\n";
        }
        if (cep.getText() == null || cep.getText().length() == 0) {
            errorMessage += "'CEP' campo vazio ou fora do limite de caracteres\n";
        }

        if (!(masc.isSelected() || fem.isSelected() || outros.isSelected())) {
            errorMessage += "'Sexo' não selecionado. Selecione um.\n";
        }

        if (nascimento.getValue() == null) {
            errorMessage += "'Data de Nascimento' campo vazio. Preencha.\n";
        }
        if (nascimento.getValue() != null) {

            if (nascimento.getValue().isBefore(antes) || nascimento.getValue().isAfter(depois)) {
                errorMessage += "'Data de Nascimento' inválida.\n";
                System.out.println(nascimento.getValue());
            }
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
        RadioButton radio = (RadioButton) sexo.getSelectedToggle();
        Alert aviso = new Alert(AlertType.INFORMATION);
        if (entradaValida()) {
            Usuario professor = new Usuario(
                    nome.getText(), cpf.getText(), telefone.getText(), email.getText(), rg.getText(), cidade.getText(),
                    bairro.getText(), uf.getValue(), rua.getText(), numero.getText(), complemento.getText(),
                    cep.getText(), nascimento.getValue(), radio.getText()
            );

            listProfessores.add(professor);
            salvarListaProfessores();
            limparCampos();
            System.out.println(professor.getRg());
            aviso.setTitle("Cadastro Professor");
            aviso.setContentText(professor.getNome() + " foi cadastrado(a) com sucesso.");
            aviso.showAndWait();
        }
        System.out.println(listProfessores.size());
    }

    private boolean jaExiste(String valor) {

        int tam = listProfessores.size();
        for (int i = 0; i < tam; i++) {
            if (listProfessores.get(i).getCpf().equals(valor)) {
                return true;
            }
        }
        return false;
    }

    public void limparCampos() {
        nome.clear();
        cpf.clear();
        cidade.clear();
        sexo.selectToggle(null);
        bairro.clear();
        uf.getItems().clear();
        uf.getItems().addAll("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES",
                "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PE", "PI", "PR", "RJ",
                "RN", "RO", "RR", "RS", "SC", "SC", "SE", "SP", "TO");
        cep.clear();
        rua.clear();
        numero.clear();
        complemento.clear();
        telefone.clear();
        nascimento.setValue(null);
        email.clear();
        rg.clear();
    }

    @FXML
    private ObservableList<Usuario> carregarListaProfessor() {
        try {
            List<Usuario> list = (List<Usuario>) ResourceManager.load("ListaProfessores");
            return FXCollections.observableArrayList(list);
        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();
    }

    @FXML
    private void salvarListaProfessores() {
        tempList = new ArrayList<>(listProfessores);

        try {
            ResourceManager.save(tempList, "ListaProfessores");
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

    //Mascara do Telefone
    @FXML
    private void maskTelefone() {
        TextFieldFormatter mascara = new TextFieldFormatter();
        mascara.setMask("(##)#####-####");
        mascara.setCaracteresValidos("0123456789");
        mascara.setTf(telefone);
        mascara.formatter();
    }

    @FXML
    private void maskCpf() {
        TextFieldFormatter mascara = new TextFieldFormatter();
        mascara.setMask("###########");
        mascara.setCaracteresValidos("0123456789");
        mascara.setTf(cpf);
        mascara.formatter();
    }

    @FXML
    private void maskCep() {
        TextFieldFormatter mascara = new TextFieldFormatter();
        mascara.setMask("#####-###");
        mascara.setCaracteresValidos("0123456789");
        mascara.setTf(cep);
        mascara.formatter();

    }

    @FXML
    private void maskNumero() {
        TextFieldFormatter mascara = new TextFieldFormatter();
        mascara.setMask("###############");
        mascara.setCaracteresValidos("0123456789");
        mascara.setTf(numero);
        mascara.formatter();

    }

    @FXML
    private void maskRg() {
        TextFieldFormatter mascara = new TextFieldFormatter();
        mascara.setMask("#######");
        mascara.setCaracteresValidos("0123456789");
        mascara.setTf(rg);
        mascara.formatter();

    }

    public void btAlterar() {
        Alert aviso;
        aviso = new Alert(AlertType.WARNING);
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (!listProfessores.isEmpty()) {
                Usuario user;
                user = table.getSelectionModel().getSelectedItem();
                setAlterarData(user);
                telaAlterar.setVisible(true);
                cadProfessor.setVisible(false);
                exibirProfessor.setVisible(false);
            }
        } else {
            aviso.setTitle("Alterar Professor");
            aviso.setContentText("Você não selecionou um professor.");
            aviso.showAndWait();
        }
    }

    public void btAltVoltar() {
        telaAlterar.setVisible(false);
        exibirProfessor.setVisible(true);
    }

    public void btConfirmar() {
        Alert alerta;
        alerta = new Alert(AlertType.CONFIRMATION);
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        RadioButton radio = (RadioButton) altSexo.getSelectedToggle();
        Usuario user = table.getSelectionModel().getSelectedItem();
        if (entradaValidaAlt()) {
            alerta.setTitle("Alterar Dados");
            alerta.setContentText("Deseja alterar os dados de " + user.getNome() + "?");
            Optional<ButtonType> resposta = alerta.showAndWait();
            if (resposta.get() == ButtonType.OK) {
                user.setNome(altNome.getText());
                user.setNascimento(altNascimento.getValue());
                user.setSexo(radio.getText());
                user.setEmail(altEmail.getText());
                user.setBairro(altBairro.getText());
                user.setCidade(altCidade.getText());
                user.setTelefone(altTelefone.getText());
                user.setUf(altUf.getValue());
                user.setNumero(altNumero.getText());
                user.setCep(altCep.getText());
                user.setRua(altRua.getText());
                user.setComplemento(altComplemento.getText());
                listProfessores.set(selectedIndex, user);
                alterarNaTurma(user);
                salvarListaProfessores();
                salvarListaTurmas();
                btAltVoltar();
            }
        }

    }

    public void setAlterarData(Usuario user) {
        selectedUser = user;

        altNome.setText(selectedUser.getNome());
        altNascimento.setValue(selectedUser.getNascimento());
        altEmail.setText(selectedUser.getEmail());
        altTelefone.setText(selectedUser.getTelefone());
        altUf.getSelectionModel().select(selectedUser.getUf());
        altCidade.setText(selectedUser.getCidade());
        altBairro.setText(selectedUser.getBairro());
        altRua.setText(selectedUser.getRua());
        altNumero.setText(selectedUser.getNumero());
        altCep.setText(selectedUser.getCep());
        //altRg.setText(selectedUser.getRg());
        altComplemento.setText(selectedUser.getComplemento());
        if (selectedUser.getSexo().equalsIgnoreCase("masculino")) {
            altSexo.selectToggle(altMasc);
        } else if (selectedUser.getSexo().equalsIgnoreCase("feminino")) {
            altSexo.selectToggle(altFem);
        } else {
            altSexo.selectToggle(altOutros);
        }

    }

    //Validação do Alterar
    private boolean entradaValidaAlt() {
        String errorMessage = "";

        if (altNome.getText() == null || altNome.getText().length() == 0) {
            errorMessage += "'Nome'campo vazio. Preencha.\n";
        }
        if (!TratamentoDado.textAlphabet(altNome)) {
            errorMessage += "'Nome' campo deve ser apenas letras.\n";
        }
        if (altEmail.getText() == null || altEmail.getText().length() == 0) {
            errorMessage += "'E-mail' campo vazio. Preencha.\n";
        }
        /*if (rg.getText() == null || rg.getText().length() == 0) {
            errorMessage += "'RG' campo vazio.\n";
        }
        if (!TratamentoDado.isNumber(rg)) {
            errorMessage += "'RG' apenas números são aceitos.\n";
        }*/
        if (!TratamentoDado.emailFormat(altEmail)) {
            errorMessage += "E-mail inválido. Digite um e-mail valido.\n";
        }
        if (altCidade.getText() == null || altCidade.getText().length() == 0) {
            errorMessage += "'Cidade' campo vazio. Preencha.\n";
        }
        if (altTelefone.getText() == null || altTelefone.getText().length() == 0) {
            errorMessage += "'Telefone' campo vazio ou insuficente. Preencha.\n";
        }
        if (altUf.getValue() == null) {
            errorMessage += "'UF' campo vazio.Preencha. \n";
        }
        if (altBairro.getText() == null || altBairro.getText().length() == 0) {
            errorMessage += "'Bairro' campo vazio. Preencha.\n";
        }
        if (altRua.getText() == null || altRua.getText().length() == 0) {
            errorMessage += "'Rua' campo vazio. Preencha.\n";
        }
        if (altNumero.getText() == null || altNumero.getText().length() == 0) {
            errorMessage += "'Número' campo vazio. Preencha.\n";
        }
        if (altCep.getText() == null || altCep.getText().length() == 0) {
            errorMessage += "'CEP' campo vazio ou fora do limite de caracteres\n";
        }

        if (!(altMasc.isSelected() || altFem.isSelected() || altOutros.isSelected())) {
            errorMessage += "'Sexo' não selecionado. Selecione um.\n";
        }

        if (altNascimento.getValue() == null) {
            errorMessage += "'Data de Nascimento' campo vazio. Preencha.\n";
        }
        if (altNascimento.getValue() != null) {

            if (altNascimento.getValue().isBefore(antes) || altNascimento.getValue().isAfter(depois)) {
                errorMessage += "'Data de Nascimento' inválida.\n";
                System.out.println(altNascimento.getValue());
            }
        }

        //Inserir validação de data de altNascimento.
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

    //Remove professor da lista de turmas
    public void removerDaTurma(Usuario user) {
        int index = -1;
        if (!listTurma.isEmpty()) {
            for (Turma turma : listTurma) {
                if (turma.getProfessor() != null) {
                    if (turma.getProfessor().getCpf().equalsIgnoreCase(user.getCpf())) {
                        index = listTurma.indexOf(turma);
                        turma.setProfessor(null);
                        listTurma.set(index, turma);
                    }
                }
            }
        }

    }

    //Altera dados do professor da lista de turmas
    public void alterarNaTurma(Usuario user) {
        int index = -1;
        if (!listTurma.isEmpty()) {
            for (Turma turma : listTurma) {
                if (turma.getProfessor() != null) {
                    if (turma.getProfessor().getCpf().equalsIgnoreCase(user.getCpf())) {
                        index = listTurma.indexOf(turma);
                        turma.setProfessor(user);
                        listTurma.set(index, turma);
                    }
                }
            }
        }

    }
}
