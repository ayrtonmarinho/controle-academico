/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admescola.view;

import admescola.model.Disciplina;
import admescola.model.Turma;
import admescola.model.Usuario;
import admescola.model.UsuarioTurma;
import admescola.util.ResourceManager;
import admescola.util.TratamentoDado;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author iTuhh Z
 */
public class TelaTurmaController implements Initializable {

    @FXML
    private TableView<Turma> tableTurma;

    @FXML
    private TableColumn<Turma, String> codTurma;

    @FXML
    private TableColumn<Turma, String> cdDisciplina;

    @FXML
    private TableColumn<Turma, String> tHorario;

    @FXML
    private TableColumn<Turma, String> tProfessor;

    @FXML
    private TableColumn<Turma, String> qtdAlunos;

    //PorProfessor
    @FXML
    private TableView<Turma> tabelaPorProfessor;

    @FXML
    private TableColumn<Turma, String> tpCpf;

    @FXML
    private TableColumn<Turma, String> tpNomeDisciplina;

    @FXML
    private TableColumn<Turma, String> tpCodigoDisciplina;

    @FXML
    private TableColumn<Turma, String> tpCodigoTurma;

    @FXML
    private TableColumn<Turma, String> tpHorario;

    //Por Disciplina
    @FXML
    private TableView<Turma> tabelaPorDisciplina;

    @FXML
    private TableColumn<Turma, String> tdCodigoDisciplina;

    @FXML
    private TableColumn<Turma, String> tdNomeDisciplina;

    @FXML
    private TableColumn<Turma, String> tdCodigoTurma;

    @FXML
    private TableColumn<Turma, String> tdProfessor;

    @FXML
    private TableColumn<Turma, String> tdHorario;

    //Tabela por Aluno
    @FXML
    private TableView<UsuarioTurma> tabelaPorAluno;

    @FXML
    private TableColumn<UsuarioTurma, String> taCpf;

    @FXML
    private TableColumn<UsuarioTurma, String> taNomeDisciplina;

    @FXML
    private TableColumn<UsuarioTurma, String> taCodigoTurma;

    @FXML
    private TableColumn<UsuarioTurma, String> taCodigoDisciplina;

    @FXML
    private TableColumn<UsuarioTurma, String> taHorario;

    //Tabela Lista Alunos da Turma
    @FXML
    private TableView<Usuario> tableListAluno;

    @FXML
    private TableColumn<Usuario, String> listCpf;

    @FXML
    private TableColumn<Usuario, String> listNome;

    // table Aluno
    @FXML
    private TableView<Usuario> tableAluno;

    @FXML
    private TableColumn<Usuario, String> nomeAluno;

    @FXML
    private TableColumn<Usuario, String> cpfAluno;

    @FXML
    private TableView<Usuario> tableProfessor;

    @FXML
    private TableColumn<Usuario, String> nomeProfessor;

    @FXML
    private TableColumn<Usuario, String> cpfProfessor;

    @FXML
    private TableView<Usuario> addTableProfessor;

    @FXML
    private TableColumn<Usuario, String> addNomeProfessor;

    @FXML
    private TableColumn<Usuario, String> addCpfProfessor;

    @FXML
    private TableView<Disciplina> tableDisciplina;

    @FXML
    private TableColumn<Disciplina, String> codDisciplina;

    @FXML
    private TableColumn<Disciplina, String> nomeDisciplina;

    //Table dos Alunos Exibir
    @FXML
    private TableView<Usuario> teAlunos;

    @FXML
    private TableColumn<Usuario, String> teNome;

    @FXML
    private TableColumn<Usuario, String> teCpf;

    //Buttons
    @FXML
    private Button btRemoverProfessor;

    //VBox
    @FXML
    private VBox telaCadastrar;

    @FXML
    private VBox telaExibir;

    @FXML
    private VBox telaAdicionar;

    @FXML
    private VBox btTodos;

    @FXML
    private VBox btPorAluno;

    @FXML
    private VBox btPorProfessor;

    @FXML
    private VBox btPorDisciplina;

    @FXML
    private VBox exibirTurmas;

    //Hbox
    @FXML
    private HBox barraBtsExibir;

    //Listas usadas
    private ObservableList<Usuario> listProfessores = FXCollections.observableArrayList();

    private ObservableList<Usuario> listAlunos = FXCollections.observableArrayList();

    private ObservableList<Disciplina> listDisciplinas = FXCollections.observableArrayList();

    private ObservableList<Turma> listTurma = FXCollections.observableArrayList();

    //Lista auxiliares
    private ObservableList<Usuario> altListProfessores = FXCollections.observableArrayList();

    private ObservableList<UsuarioTurma> listPorAlunos = FXCollections.observableArrayList();

    private ObservableList<Usuario> listaAlunosTurma = FXCollections.observableArrayList();

    private ObservableList<Usuario> altListDisciplinas = FXCollections.observableArrayList();

    //teste
    private ObservableList<Usuario> alunos = FXCollections.observableArrayList();

    //Files
    private File file = new File("ListaAlunos");
    private File fileProfessor = new File("ListaProfessores");
    private File fileDisciplinas = new File("ListaDisciplinas");
    private File fileTurmas = new File("ListaTurmas");

    //TextFields
    @FXML
    private TextField tfCodTurma;

    @FXML
    private TextField tfCodDisciplina;

    @FXML
    private TextField tfCpfProfessor;

    //Labels
    @FXML
    private Label tAdcProfessor;

    @FXML
    private Label tAdcCodTurma;

    @FXML
    private Label tAdcCodDisciplina;

    @FXML
    private Label tAdcNomeDisciplina;

    @FXML
    private Label tAdcHorario;

    @FXML
    private Label cTurma;

    @FXML
    private Label cDisciplina;

    @FXML
    private Label cHorario;

    @FXML
    private Label cProfessor;

    @FXML
    private Label cCpf;

    @FXML
    private Label cQtdAtual;

    @FXML
    private Label cQtdMax;

    @FXML
    private Label cAno;

    @FXML
    private Label adcAno;

    //Radio Buttons
    @FXML
    private ToggleGroup turno;

    @FXML
    private RadioButton manha;

    @FXML
    private RadioButton tarde;

    @FXML
    private RadioButton noite;

    //Util 
    private Usuario utilProfessor;

    private Usuario utilAluno;

    private Disciplina utilDisciplina;

    private LocalDate data = LocalDate.now();

    private LocalDate semester;

    private int ano;

    private String dataLetiva;

    @FXML
    private TextField filtroTodos;

    @FXML
    private TextField filtroPorProfessor;

    @FXML
    private TextField filtroPorDisciplina;

    @FXML
    private TextField filtroPorAlunos;

    //ComboBox
    @FXML
    private ComboBox selectHorario;

    @FXML
    private ComboBox anoLetivo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (file.exists()) {
            System.out.println("ok");
            listAlunos = carregarListaAluno();
        }
        if (fileProfessor.exists()) {
            System.out.println("ok");
            listProfessores = carregarListaProfessor();
        }
        if (fileDisciplinas.exists()) {
            System.out.println("ok");
            listDisciplinas = carregarListaDisciplina();
        }
        if (fileTurmas.exists()) {
            System.out.println("ok");
            listTurma = carregarListaTurma();
        }

        ano = data.getYear();
        semester = LocalDate.of(ano, Month.JUNE, 30);
        if (data.isBefore(semester)) {
            dataLetiva = ano + ".1";
        } else if (data.isAfter(semester)) {
            dataLetiva = ano + ".2";
        }
        selectHorario.setPromptText("Selecione o Horário");
        anoLetivo.getItems().add(dataLetiva);
        anoLetivo.setPromptText("Selecione um Ano");

        //Seta Colunas AlunoTable.
        cpfAluno.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));
        nomeAluno.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));

        //Seta Colunas ProfTable
        cpfProfessor.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));
        nomeProfessor.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));

        //Seta Colunas ProfTable
        addCpfProfessor.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));
        addNomeProfessor.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));

        //Seta Colunas DiscTable
        codDisciplina.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("codigo"));
        nomeDisciplina.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("nome"));

        //Seta Colunas TurmaTableTodos
        codTurma.setCellValueFactory(new PropertyValueFactory<Turma, String>("codigo"));
        tHorario.setCellValueFactory(new PropertyValueFactory<Turma, String>("horario"));
        qtdAlunos.setCellValueFactory(new PropertyValueFactory<Turma, String>("qtdMatriculados"));

        cdDisciplina.setCellValueFactory(cellData -> {
            Turma t = cellData.getValue();
            String result = t.getCodigoDisciplina();
            return new ReadOnlyStringWrapper(result);
        });
        tProfessor.setCellValueFactory(cellData -> {
            Turma t = cellData.getValue();
            String result = "Sem professor";
            if (t.getProfessor() != null) {
                result = t.getProfessor().getNome();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });

        //Seta Colunas TurmaTableProfessor
        tpCodigoTurma.setCellValueFactory(new PropertyValueFactory<Turma, String>("codigo"));
        tpHorario.setCellValueFactory(new PropertyValueFactory<Turma, String>("horario"));
        tpCpf.setCellValueFactory(cellData -> {
            Turma t = cellData.getValue();
            String result = "Sem professor";
            if (t.getProfessor() != null) {
                result = t.getProfessor().getCpf();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });
        tpCodigoDisciplina.setCellValueFactory(cellData -> {
            Turma t = cellData.getValue();
            String result = "";
            if (t.getDisciplina() != null) {
                result = t.getDisciplina().getCodigo();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });
        tpNomeDisciplina.setCellValueFactory(cellData -> {
            Turma t = cellData.getValue();
            String result = "";
            if (t.getDisciplina() != null) {
                result = t.getDisciplina().getNome();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });

        //Seta Colunas TurmaTableDisciplina
        tdCodigoTurma.setCellValueFactory(new PropertyValueFactory<Turma, String>("codigo"));
        tdHorario.setCellValueFactory(new PropertyValueFactory<Turma, String>("horario"));
        tdProfessor.setCellValueFactory(cellData -> {
            Turma t = cellData.getValue();
            String result = "Sem professor";
            if (t.getProfessor() != null) {
                result = t.getProfessor().getNome();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });
        tdCodigoDisciplina.setCellValueFactory(cellData -> {
            Turma t = cellData.getValue();
            String result = "";
            if (t.getDisciplina() != null) {
                result = t.getDisciplina().getCodigo();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });
        tdNomeDisciplina.setCellValueFactory(cellData -> {
            Turma t = cellData.getValue();
            String result = "";
            if (t.getDisciplina() != null) {
                result = t.getDisciplina().getNome();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });

        //Seta Colunas TurmaTableAluno
        taCpf.setCellValueFactory(cellData -> {
            UsuarioTurma t = cellData.getValue();
            String result = "";
            if (t.getAluno() != null) {
                result = t.getAluno().getCpf();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });
        taCodigoDisciplina.setCellValueFactory(cellData -> {
            UsuarioTurma t = cellData.getValue();
            String result = "";
            if (t.getDisciplina() != null) {
                result = t.getDisciplina().getCodigo();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });
        taNomeDisciplina.setCellValueFactory(cellData -> {
            UsuarioTurma t = cellData.getValue();
            String result = "";
            if (t.getDisciplina() != null) {
                result = t.getDisciplina().getNome();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });
        taCodigoTurma.setCellValueFactory(cellData -> {
            UsuarioTurma t = cellData.getValue();
            String result = "";
            if (t.getAluno() != null) {
                result = t.getTurma().getCodigo();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });
        taHorario.setCellValueFactory(cellData -> {
            UsuarioTurma t = cellData.getValue();
            String result = "";
            if (t.getAluno() != null) {
                result = t.getTurma().getHorario();
                return new ReadOnlyStringWrapper(result);
            }
            return new ReadOnlyStringWrapper(result);
        });

        //Buscar turma.
        //Envolve a ObservableList em uma FilteredList
        FilteredList<Turma> filterTurma = new FilteredList<Turma>(listTurma, b -> true);

        //Seta o filtro do Predicate toda vez que o filtro muda.
        filtroTodos.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filterTurma.setPredicate(turma -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (turma.getCodigo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (turma.getProfessor() == null) {
                    return false;
                } else if (turma.getDisciplina().getCodigo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (turma.getProfessor().getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        }
        );
        //Buscar Professor por CPF
        //Envolve a FilteredList em uma SortedList.
        SortedList<Turma> sortedData = new SortedList(filterTurma);

        sortedData.comparatorProperty()
                .bind(tableTurma.comparatorProperty());

        //Envolve a ObservableList em uma FilteredList
        FilteredList<Turma> filterProfessor = new FilteredList<Turma>(listTurma, b -> true);

        //Seta o filtro do Predicate toda vez que o filtro muda.
        filtroPorProfessor.textProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    filterProfessor.setPredicate(professor -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        String lowerCaseFilter = newValue.toLowerCase();
                        if (professor.getProfessor() == null) {
                            return false;
                        } else if (professor.getProfessor().getCpf().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }

                    });
                }
                );
        //Envolve a FilteredList em uma SortedList.
        SortedList<Turma> sortedDataProf = new SortedList(filterProfessor);

        sortedDataProf.comparatorProperty()
                .bind(tableTurma.comparatorProperty());

        //Buscar disciplina
        //Envolve a ObservableList em uma FilteredList
        FilteredList<Turma> filterDisciplina = new FilteredList<Turma>(listTurma, b -> true);

        //Seta o filtro do Predicate toda vez que o filtro muda.
        filtroPorDisciplina.textProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    filterDisciplina.setPredicate(disciplina -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        String lowerCaseFilter = newValue.toLowerCase();
                        if (disciplina.getDisciplina().getCodigo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }

                    });
                }
                );

        //Envolve a FilteredList em uma SortedList.
        SortedList<Turma> sortedDataDiscp = new SortedList(filterDisciplina);

        sortedDataDiscp.comparatorProperty()
                .bind(tableTurma.comparatorProperty());

        //Buscar disciplina
        //Envolve a ObservableList em uma FilteredList
        FilteredList<UsuarioTurma> filterAluno = new FilteredList<UsuarioTurma>(listPorAlunos, b -> true);

        //Seta o filtro do Predicate toda vez que o filtro muda.
        filtroPorAlunos.textProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    filterAluno.setPredicate(aluno -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        String lowerCaseFilter = newValue.toLowerCase();
                        if (aluno.getAluno().getCpf().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }

                    });
                }
                );
        //Envolve a FilteredList em uma SortedList.
        SortedList<UsuarioTurma> sortedDataAluno = new SortedList(filterAluno);

        sortedDataAluno.comparatorProperty()
                .bind(tabelaPorAluno.comparatorProperty());

        //Coloca os itens de determinada lista, cada.
        tableAluno.setItems(listAlunos);

        tableProfessor.setItems(listProfessores);

        tableDisciplina.setItems(listDisciplinas);

        addTableProfessor.setItems(listProfessores);

        tableTurma.setItems(sortedData);

        tabelaPorDisciplina.setItems(sortedDataDiscp);

        tabelaPorAluno.setItems(sortedDataAluno);

        tabelaPorProfessor.setItems(sortedDataProf);

        tableListAluno.setItems(alunos);

        //Seta o placeholder de mensagens especificas
        tableAluno.setPlaceholder(
                new Label("Não há alunos cadastrados."));
        tableProfessor.setPlaceholder(
                new Label("Não há professores cadastrados."));
        addTableProfessor.setPlaceholder(
                new Label("Não há professores cadastrados."));
        tableDisciplina.setPlaceholder(
                new Label("Não há disciplinas cadastradas."));

        //addDisciplina(null);
        tableDisciplina.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> addDisciplina(newValue));
        tableProfessor.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> addProfessor(newValue));

    }

    public ObservableList<Usuario> getUsersAluno() {
        return listAlunos;
    }

    public void addProfessor(Usuario usuario) {
        if (usuario != null) {
            tfCpfProfessor.setText(usuario.getCpf());
        }
    }

    public void exibirTurma() {
        Alert aviso = new Alert(AlertType.WARNING);
        int selectionIndex = tableTurma.getSelectionModel().getSelectedIndex();
        if (selectionIndex >= 0) {
            if (!listTurma.isEmpty()) {
                Turma turma = tableTurma.getSelectionModel().getSelectedItem();
                List<Usuario> list = turma.getAlunos();
                alunos = FXCollections.observableArrayList(list);
                teCpf.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));
                teNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
                teAlunos.getItems().clear();
                teAlunos.getItems().addAll(alunos);

                cTurma.setText(turma.getCodigo());
                cDisciplina.setText(turma.getDisciplina().getCodigo());
                cHorario.setText(turma.getHorario());
                cAno.setText(turma.getAnoLetivo());
                cQtdAtual.setText(turma.qtdMatriculadosToString());
                cQtdMax.setText(turma.qtdMaxToString());
                if (turma.getProfessor() != null) {
                    cProfessor.setText(turma.getProfessor().getNome());
                    cCpf.setText(turma.getProfessor().getCpf());

                } else {
                    cProfessor.setText("Sem Professor");
                    cCpf.setText("");
                }
                btTodos.setVisible(false);
                exibirTurmas.setVisible(true);
                barraBtsExibir.setDisable(true);

            }
        } else {
            aviso.setTitle("Exibir Turma");
            aviso.setContentText("Você não selecionou uma turma.");
            aviso.showAndWait();
        }
    }

    public void setDataPeA() {
        Alert aviso = new Alert(AlertType.WARNING);
        int selectedIndex = tableTurma.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (!listTurma.isEmpty()) {
                Turma turma = tableTurma.getSelectionModel().getSelectedItem();
                List<Usuario> list = turma.getAlunos();
                ObservableList<Usuario> listAddAluno = FXCollections.observableArrayList(list);
                listCpf.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));
                listNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
                tableListAluno.getItems().clear();
                tableListAluno.getItems().addAll(listAddAluno);
                if (turma.getProfessor() != null) {
                    tAdcProfessor.setText(turma.getProfessor().getNome());
                    btRemoverProfessor.setVisible(true);

                } else {
                    tAdcProfessor.setText("Sem Professor");
                    btRemoverProfessor.setVisible(false);
                }
                tAdcCodTurma.setText(turma.getCodigo());
                tAdcCodDisciplina.setText(turma.getDisciplina().getCodigo());
                tAdcNomeDisciplina.setText(turma.getDisciplina().getNome());
                tAdcHorario.setText(turma.getHorario());
                adcAno.setText(turma.getAnoLetivo());
                telaExibir.setVisible(false);
                telaAdicionar.setVisible(true);
            }
        } else {
            aviso.setTitle("Adicionar");
            aviso.setContentText("Não há turma selecionada.");
            aviso.showAndWait();
        }
    }

    public void adicionarAluno() {
        Alert info = new Alert(AlertType.INFORMATION);
        Alert aviso = new Alert(AlertType.WARNING);
        int selectedIndex = tableTurma.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (!listTurma.isEmpty()) {
                Turma turma = tableTurma.getSelectionModel().getSelectedItem();
                String horarioAtual = turma.getHorario();
                if (!listAlunos.isEmpty()) {
                    int selectedIndexAluno = tableAluno.getSelectionModel().getSelectedIndex();
                    if (selectedIndexAluno >= 0) {
                        Usuario aluno = tableAluno.getSelectionModel().getSelectedItem();
                        if (!turma.isFull()) {
                            if (!alunoRepetido(aluno, turma.getAlunos())) {
                                if (!choqueHorarioAluno(aluno, horarioAtual)) {
                                    turma.getAlunos().add(aluno);
                                    turma.setQtdMatriculados(turma.getQtdMatriculados() + 1);
                                    listTurma.set(selectedIndex, turma);
                                    info.setTitle("Adicionar Aluno");
                                    info.setContentText("Aluno adicionado.");
                                    info.showAndWait();
                                    salvarListaTurmas();
                                    setDataPeA();
                                } else {
                                    aviso.setTitle("Adicionar Aluno");
                                    aviso.setContentText("O aluno já esta matriculado em uma turma com o mesmo horário.");
                                    aviso.showAndWait();
                                }
                            } else {
                                aviso.setTitle("Adicionar Aluno");
                                aviso.setContentText("O aluno já esta matriculado nesta turma.");
                                aviso.showAndWait();
                            }

                        }
                    } else {
                        aviso.setTitle("Adicionar Aluno");
                        aviso.setContentText("Você não selecionou um aluno.");
                        aviso.showAndWait();
                    }
                }
            }
        }

    }

    public void adicionarProfessor() {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        Alert info = new Alert(AlertType.INFORMATION);
        Alert aviso = new Alert(AlertType.WARNING);
        int selectedIndex = tableTurma.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (!listTurma.isEmpty()) {
                Turma turma = tableTurma.getSelectionModel().getSelectedItem();
                String horarioAtual = turma.getHorario();
                if (!listProfessores.isEmpty()) {
                    int selectedProfIndex = addTableProfessor.getSelectionModel().getSelectedIndex();
                    if (selectedProfIndex >= 0) {
                        Usuario professor = addTableProfessor.getSelectionModel().getSelectedItem();
                        if (!choqueHorarioProfessor(professor, horarioAtual)) {
                            if (turma.getProfessor() != null) {
                                if (!turma.getProfessor().getCpf().equalsIgnoreCase(professor.getCpf())) {
                                    alerta.setTitle("Adicionar Professor");
                                    alerta.setContentText("Já existe um professor cadastrado. Deseja substituir?");
                                    Optional<ButtonType> resposta = alerta.showAndWait();
                                    if (resposta.get() == ButtonType.OK) {
                                        turma.setProfessor(professor);
                                        listTurma.set(selectedIndex, turma);
                                        info.setTitle("Adicionar Professor");
                                        info.setContentText("Professor adicionado.");
                                        info.showAndWait();
                                        setDataPeA();
                                        salvarListaTurmas();
                                    }
                                } else {
                                    aviso.setTitle("Adicionar Professor");
                                    aviso.setContentText("Este professor já esta na turma.");
                                    aviso.showAndWait();
                                }
                            } else {
                                turma.setProfessor(professor);
                                listTurma.set(selectedIndex, turma);
                                info.setTitle("Adicionar Professor");
                                info.setContentText("Professor adicionado.");
                                info.showAndWait();
                                setDataPeA();
                                salvarListaTurmas();
                            }

                        } else {
                            aviso.setTitle("Adicionar Professor");
                            aviso.setContentText("Professor já esta cadastrado em uma turma com o mesmo horario.");
                            aviso.showAndWait();
                        }
                    } else {
                        aviso.setTitle("Adicionar Professor");
                        aviso.setContentText("Você não selecionou um professor.");
                        aviso.showAndWait();
                    }
                }
            }
        }

    }

    public void removerAluno() {
        Alert info = new Alert(AlertType.INFORMATION);
        Alert confirma = new Alert(AlertType.CONFIRMATION);
        Alert aviso = new Alert(AlertType.WARNING);
        int selectedIndex = tableTurma.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (!listTurma.isEmpty()) {
                Turma turma = tableTurma.getSelectionModel().getSelectedItem();
                if (!listTurma.get(selectedIndex).getAlunos().isEmpty()) {
                    int selectedIndexAluno = tableListAluno.getSelectionModel().getSelectedIndex();
                    if (selectedIndexAluno >= 0) {
                        Usuario aluno = tableListAluno.getSelectionModel().getSelectedItem();
                        confirma.setTitle("Remover Aluno");
                        confirma.setContentText("Deseja remover o aluno " + aluno.getNome() + "?");
                        Optional<ButtonType> resposta = confirma.showAndWait();
                        if (resposta.get() == ButtonType.OK) {
                            turma.getAlunos().remove(aluno);
                            turma.setQtdMatriculados(turma.getQtdMatriculados() - 1);
                            listTurma.set(selectedIndex, turma);
                            info.setTitle("Remover Aluno");
                            info.setContentText("Aluno removido.");
                            info.showAndWait();
                            setDataPeA();
                            salvarListaTurmas();

                        }

                    } else {
                        aviso.setTitle("Remover Aluno");
                        aviso.setContentText("Você não selecionou um aluno.");
                        aviso.showAndWait();
                    }
                } else {
                    aviso.setTitle("Remover Aluno");
                    aviso.setContentText("Não há alunos cadastrados.");
                    aviso.showAndWait();
                }
            }
        }
    }

    public void removerProfessor() {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        Alert info = new Alert(AlertType.INFORMATION);
        int selectedIndex = tableTurma.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (!listTurma.isEmpty()) {
                Turma turma = tableTurma.getSelectionModel().getSelectedItem();
                if (turma.getProfessor() != null) {
                    alerta.setTitle("Remover Professor");
                    alerta.setContentText("Deseja remover o professor " + turma.getProfessor().getNome() + "?");
                    Optional<ButtonType> resposta = alerta.showAndWait();
                    if (resposta.get() == ButtonType.OK) {
                        turma.setProfessor(null);
                        listTurma.set(selectedIndex, turma);
                        Alert infor = new Alert(AlertType.INFORMATION);
                        //tAdcProfessor.setText("Sem Professor");
                        btRemoverProfessor.setVisible(false);
                        infor.setTitle("Informação");
                        infor.setContentText("Professor removido.");
                        infor.showAndWait();
                        setDataPeA();
                        salvarListaTurmas();

                    }
                }

            }
        }

    }

    public void cadastrarTurma() {
        Alert info = new Alert(AlertType.INFORMATION);
        Alert aviso = new Alert(AlertType.WARNING);
        if (entradaValida()) {
            Disciplina disciplina = tableDisciplina.getSelectionModel().getSelectedItem();
            Usuario professor = tableProfessor.getSelectionModel().getSelectedItem();
            Turma turma = new Turma(tfCodTurma.getText(), disciplina, selectHorario.getValue().toString(), anoLetivo.getValue().toString());
            if (tfCpfProfessor.getText() != null || tfCpfProfessor.getText().length() != 0) {
                if (!choqueHorarioProfessor(professor, selectHorario.getValue().toString())) {
                    turma.setProfessor(professor);
                    tableDisciplina.getSelectionModel().getSelectedItem().setAlocada(true);
                    listTurma.add(turma);
                    info.setTitle("Cadastro de Turma");
                    info.setContentText("Turma cadastrada.");
                    info.showAndWait();
                    limparCamposCadastro();
                    salvarListaTurmas();
                    salvarListaDisciplinas();
                } else {
                    aviso.setTitle("Cadastro de Turma");
                    aviso.setContentText("O professor selecionado já esta cadastrado em uma turma com o mesmo horario. Escolha outro professor.");
                    aviso.showAndWait();
                }
            } else {
                tableDisciplina.getSelectionModel().getSelectedItem().setAlocada(true);
                listTurma.add(turma);
                info.setTitle("Cadastro de Turma");
                info.setContentText("Turma cadastrada.");
                info.showAndWait();
                limparCamposCadastro();
                salvarListaTurmas();
                salvarListaDisciplinas();
            }

        }
    }

    public void removerTurma() {
        Alert aviso, alerta;
        alerta = new Alert(AlertType.CONFIRMATION);
        aviso = new Alert(AlertType.WARNING);
        int selectedIndex = tableTurma.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (!listTurma.isEmpty()) {
                Turma turma = tableTurma.getSelectionModel().getSelectedItem();
                if (turma.getAlunos().isEmpty() && turma.getProfessor() == null) {
                    alerta.setTitle("Remover Turma");
                    alerta.setContentText("Deseja remover " + turma.getCodigo() + " " + turma.getDisciplina().getCodigo() + "?");
                    Optional<ButtonType> resposta = alerta.showAndWait();
                    if (resposta.get() == ButtonType.OK) {
                        listTurma.remove(turma);
                        listDisciplinas.get(indexDisciplina(turma.getDisciplina())).setAlocada(false);
                        salvarListaTurmas();
                        salvarListaDisciplinas();
                        tableTurma.getSelectionModel().clearSelection();
                    }
                } else {
                    aviso.setTitle("Remover Professor");
                    aviso.setContentText("Existe professores ou alunos na turma.");
                    aviso.showAndWait();
                }
            }
        } else {
            aviso.setTitle("Remover Turma");
            aviso.setContentText("Você não selecionou uma turma.");
            aviso.showAndWait();
        }
    }

    public boolean alunoRepetido(Usuario aluno, ArrayList<Usuario> alunos) {
        int tam = listTurma.size();
        boolean result = false;
        for (Usuario alunoat : alunos) {
            if (alunoat.getCpf().equalsIgnoreCase(aluno.getCpf())) {
                result = true;
            }
        }
        return result;
    }

    public boolean choqueHorarioProfessor(Usuario professor, String horario) {
        boolean result = false;
        for (Turma turma : listTurma) {
            if (turma.getProfessor() != null) {
                if (turma.getProfessor().getCpf().equalsIgnoreCase(professor.getCpf()) && turma.getHorario().equalsIgnoreCase(horario)) {
                    result = true;
                }else{
                    
                }
            }
        }
        return result;
    }

    public boolean choqueHorarioAluno(Usuario aluno, String horario) {
        int tam = listTurma.size();
        for (int i = 0; i < tam; i++) {
            int tamA = listTurma.get(i).getAlunos().size();
            for (int j = 0; j < tamA; j++) {
                if (listTurma.get(i).getAlunos().get(j).getCpf().equalsIgnoreCase(aluno.getCpf()) && listTurma.get(i).getHorario().equalsIgnoreCase(horario)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int indexDisciplina(Disciplina disciplina) {
        int tam = listDisciplinas.size();
        int valor = -1;
        for (int i = 0; i < tam; i++) {
            if (listDisciplinas.get(i).getCodigo().equalsIgnoreCase(disciplina.getCodigo())) {
                valor = i;
            }
        }
        return valor;
    }

    public void btTodos() {
        btTodos.setVisible(true);
        btPorAluno.setVisible(false);
        btPorProfessor.setVisible(false);
        btPorDisciplina.setVisible(false);
        tableTurma.getSelectionModel().clearSelection();
    }

    public void btPorAluno() {
        btTodos.setVisible(false);
        btPorAluno.setVisible(true);
        btPorProfessor.setVisible(false);
        btPorDisciplina.setVisible(false);
        listPorAlunos.clear();
        preencherListaPorAluno();
        tabelaPorAluno.getSelectionModel().clearSelection();
    }

    public void btPorProfessor() {
        btTodos.setVisible(false);
        btPorAluno.setVisible(false);
        btPorProfessor.setVisible(true);
        btPorDisciplina.setVisible(false);
        tabelaPorProfessor.getSelectionModel().clearSelection();
    }

    public void btPorDisciplina() {
        btTodos.setVisible(false);
        btPorAluno.setVisible(false);
        btPorProfessor.setVisible(false);
        btPorDisciplina.setVisible(true);
        tabelaPorDisciplina.getSelectionModel().clearSelection();
    }

    public void preencherListaPorAluno() {
        int tam = listTurma.size();
        UsuarioTurma user;
        if (!listTurma.isEmpty()) {
            for (int i = 0; i < tam; i++) {
                if (listTurma.get(i).getAlunos() != null) {
                    int tamAr = listTurma.get(i).getAlunos().size();
                    for (int j = 0; j < tamAr; j++) {
                        if (listTurma.get(i).getAlunos().get(j) != null) {
                            Usuario aluno = listTurma.get(i).getAlunos().get(j);
                            Turma turma = listTurma.get(i);
                            Disciplina disciplina = listTurma.get(i).getDisciplina();
                            user = new UsuarioTurma(aluno, disciplina, turma);
                            listPorAlunos.add(user);
                        }
                    }
                }
            }
        }

    }

    //Seta o turno com base no turno marcado e disciplina selecionada.
    public void setHorario() {
        int selectedIndex = tableDisciplina.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (turno.getSelectedToggle() != null) {
                RadioButton turno = (RadioButton) this.turno.getSelectedToggle();
                Disciplina disciplina = tableDisciplina.getSelectionModel().getSelectedItem();
                selectHorario.getItems().clear();
                if (disciplina.getCreditos().equalsIgnoreCase("4")) {
                    if (manha.isSelected()) {
                        selectHorario.getItems().addAll("2AB - 5AB", "2CD - 5CD", "2EF - 5EF", "3AB - 6AB", "3CD - 6CD", "3EF - 6EF");
                        tfCodTurma.setText(setSerialNumber("MS" + disciplina.getPeriodo() + "-", tfCodDisciplina.getText()));
                    } else if (tarde.isSelected()) {
                        selectHorario.getItems().addAll("2GH - 5GH", "2IJ - 5IJ", "2LM - 5LM", "3GH - 6GH", "3IJ - 6IJ", "3LM - 6LM");
                        tfCodTurma.setText(setSerialNumber("TS" + disciplina.getPeriodo() + "-", tfCodDisciplina.getText()));
                    } else if (noite.isSelected()) {
                        selectHorario.getItems().addAll("2NO - 5NO", "2PQ - 5PQ", "3NO - 6NO", "3PQ - 6PQ");
                        tfCodTurma.setText(setSerialNumber("NS" + disciplina.getPeriodo() + "-", tfCodDisciplina.getText()));
                    }
                } else if (disciplina.getCreditos().equalsIgnoreCase("2")) {
                    if (manha.isSelected()) {
                        selectHorario.getItems().addAll("4AB", "4CD", "4EF");
                        tfCodTurma.setText(setSerialNumber("MS" + disciplina.getPeriodo() + "-", tfCodDisciplina.getText()));
                    } else if (tarde.isSelected()) {
                        selectHorario.getItems().addAll("4GH", "4IJ", "4LM");
                        tfCodTurma.setText(setSerialNumber("TS" + disciplina.getPeriodo() + "-", tfCodDisciplina.getText()));
                    } else if (noite.isSelected()) {
                        selectHorario.getItems().addAll("4NO", "4PQ");
                        tfCodTurma.setText(setSerialNumber("NS" + disciplina.getPeriodo() + "-", tfCodDisciplina.getText()));
                    }
                }

            }
        } else {
            System.out.println("Nao rolou!");
        }

    }

    public String setSerialNumber(String tCode, String dCode) {

        String vCode = "";
        String rCode = "";
        char[] cCode;
        String ttCode = "OI";

        for (Turma turma : listTurma) {
            if (turma.getDisciplina().getCodigo().equalsIgnoreCase(dCode) && turma.getCodigo().contains(tCode)) {
                vCode = turma.getCodigo();
            }
        }
        if (vCode.equalsIgnoreCase("")) {
            rCode = tCode + "0";
        } else {
            if (vCode.charAt(4) == '9') {
                cCode = vCode.toCharArray();
                cCode[4] = 'E';
                for (int i = 0; i < 5; i++) {
                    rCode += Character.toString(cCode[i]);
                }
            } else {
                int numero = Integer.parseInt(Character.toString(vCode.charAt(4)));
                numero = numero + 1;
                String serial = Integer.toString(numero);
                cCode = vCode.toCharArray();
                cCode[4] = serial.charAt(0);
                for (int i = 0; i < 5; i++) {
                    rCode += Character.toString(cCode[i]);
                }
            }
        }
        return rCode;
    }

    public void btEVoltar() {
        telaAdicionar.setVisible(false);
        exibirTurmas.setVisible(false);
        tableTurma.getSelectionModel().clearSelection();
        btTodos.setVisible(true);
        telaExibir.setVisible(true);
        barraBtsExibir.setDisable(false);
    }

    public void addDisciplina(Disciplina disciplina) {
        if (disciplina != null) {
            tfCodDisciplina.setText(disciplina.getCodigo());
            turno.selectToggle(null);
            tfCodTurma.clear();
            selectHorario.getItems().clear();
        }
    }

    public void clickTelaCadastrar() {
        telaCadastrar.setVisible(true);
        telaExibir.setVisible(false);
        telaAdicionar.setVisible(false);

    }

    public void clickTelaExibir() {
        telaCadastrar.setVisible(false);
        telaExibir.setVisible(true);
        telaAdicionar.setVisible(false);

    }

    public void clickTelaAdicionar() {
        telaCadastrar.setVisible(false);
        telaExibir.setVisible(false);
        telaAdicionar.setVisible(true);
    }

    private boolean entradaValida() {
        String errorMessage = "";
        if (codigoExiste(tfCodTurma.getText())) {
            errorMessage += "'Turma' o maximo de turmas para esse horario e disciplina ja foi atingido. Escolha outro horario ou turno.\n";
        }
        if (selectHorario.getValue() == null) {
            errorMessage += "'Horário' campo vazio. Selecione um (ex: 2GH 5GH) \n";
        }
        if (anoLetivo.getValue() == null) {
            errorMessage += "'Ano levito' não selecionado. Selecione um.\n";
        }
        if (!(manha.isSelected() || tarde.isSelected() || noite.isSelected())) {
            errorMessage += "'Turno inválido. Selecione uma das opções.\n";
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

    public boolean choqueDeHorarioAluno(String horario, Usuario user) {
        int tam = listTurma.size();
        for (int i = 0; i < tam; i++) {
            int tamA = listTurma.get(i).getAlunos().size();
            for (int j = 0; j < tamA; j++) {
                if (listTurma.get(i).getAlunos().get(j).getCpf().equalsIgnoreCase(user.getCpf())) {

                }
            }
        }
        return false;
    }

    public boolean codigoExiste(String turma) {
        char letra[] = turma.toCharArray();
        if (letra[4] == 'E') {
            return true;
        } else {
            return false;
        }
    }

    public void limparCamposCadastro() {
        tfCodTurma.clear();
        tfCodDisciplina.clear();
        tfCpfProfessor.clear();
        turno.selectToggle(null);
        anoLetivo.getSelectionModel().clearSelection();
        selectHorario.getSelectionModel().clearSelection();
        tableDisciplina.getSelectionModel().clearSelection();
        tableProfessor.getSelectionModel().clearSelection();
    }

    public void voltarTelaInicial(ActionEvent event) throws IOException {
        Parent novaCenaParent = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
        Scene novaCena = new Scene(novaCenaParent);

        //Pega a informação do Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        novaCena.setFill(javafx.scene.paint.Color.TRANSPARENT);
        window.setScene(novaCena);
        window.show();

    }

    //Metodos de Carregamento das Listas
    @FXML
    private ObservableList<Usuario> carregarListaProfessor() {
        try {
            List<Usuario> list = (List<Usuario>) ResourceManager.load("ListaProfessores");
            return FXCollections.observableArrayList(list);

        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();
    }

    @FXML
    private ObservableList<Usuario> carregarListaAluno() {
        try {
            List<Usuario> list = (List<Usuario>) ResourceManager.load("ListaAlunos");
            return FXCollections.observableArrayList(list);

        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();
    }

    @FXML
    private ObservableList<Disciplina> carregarListaDisciplina() {
        try {
            List<Disciplina> list = (List<Disciplina>) ResourceManager.load("ListaDisciplinas");
            return FXCollections.observableArrayList(list);

        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();
    }

    @FXML
    private ObservableList<Turma> carregarListaTurma() {
        try {
            List<Turma> list = (List<Turma>) ResourceManager.load("ListaTurmas");
            return FXCollections.observableArrayList(list);

        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.emptyObservableList();
    }

    @FXML
    private void salvarListaProfessores() {
        ArrayList<Usuario> tempList = new ArrayList<>(listProfessores);

        try {
            ResourceManager.save(tempList, "ListaProfessores");

        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void salvarListaAlunos() {
        ArrayList<Usuario> tempList = new ArrayList<>(listAlunos);

        try {
            ResourceManager.save(tempList, "ListaAlunos");

        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void salvarListaDisciplinas() {
        ArrayList<Disciplina> tempList = new ArrayList<>(listDisciplinas);

        try {
            ResourceManager.save(tempList, "ListaDisciplinas");

        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void salvarListaTurmas() {
        ArrayList<Turma> tempList = new ArrayList<>(listTurma);

        try {
            ResourceManager.save(tempList, "ListaTurmas");

        } catch (Exception ex) {
            Logger.getLogger(TelaProfessorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
