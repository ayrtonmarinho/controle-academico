# Controle Acadêmico

###### Autor/Desenvolvedor: [Ayrton Marinho](https://www.linkedin.com/in/ayrton-marinho-34a69811a/)

O controle acadêmico foi um projeto da cadeira Pratica de Programação da Graduação de Ciência da Computação (UNICAP) . Resumindo:

#### O projeto foi construído em Java junto com JavaFX usando FXML.

Foi desenvolvido uma aplicação que permite o cadastro de Aluno, Professor, Disciplina e Turma. 

- O cadastro de aluno e professor são idênticos, apenas com umas alterações no atributos.
- Disciplina é um registro simples, mas que obedece regras de turno e carga horaria.
- Turma relacionas as três entidades, obedecendo regras de choque de horário, etc.

-----------------------------------
### Imagens do Projeto do Controle Acadêmico

#### Módulo Aluno
##### Tela Inicial
A tela inicial possui um design moderno e exibe os quatro módulos do projeto.
![ControleAcademicoTelaPrincipal](https://user-images.githubusercontent.com/76691413/104735456-5dd8ee80-5720-11eb-8d63-1f4320e02959.PNG)



##### Tela Cadastro de Aluno
A tela de cadastro de aluno permite um cadastro de aluno considerando todas as validações. 
![ControleAcademicoTelaAluno01](https://user-images.githubusercontent.com/76691413/104737391-efe1f680-5722-11eb-9ce0-ad1c6a21c2b1.PNG)

##### Tela Exibir Alunos
A tela exibir alunos exibe em uma table view todos os alunos cadastrados. Ainda é possivel busca-los utilizadon CPF ou NOME.
![ControleAcademicoTelaExibirAluno](https://user-images.githubusercontent.com/76691413/104737626-40f1ea80-5723-11eb-8860-59fcd8b5781b.PNG)

##### Exibição dos dados de um aluno
Quando clicado em exibir em um aluno da tela Exibir Alunos, uma nova tela surgirá mostrando todos os dados dele.
![ControleAcademicoTelaExibicaoDoAluno](https://user-images.githubusercontent.com/76691413/104738101-d9886a80-5723-11eb-99a5-47775d98a3e6.PNG)

###### OBS: módulo de professor e aluno são identicos apenas mudando algum dado a ser registrado, como RG do professor. Abaixo uma tela de alteração de professor, onde é possivel alterar os dados de um professor. Aluno tem a mesma tela, mas por questão de ilustração eu demonstro a tela de professor em questão de alteração.
![ControleAcademicoTelaAlteracao](https://user-images.githubusercontent.com/76691413/104738599-819e3380-5724-11eb-8685-3cb7a21a5590.PNG)

#### Módulo Disciplina
##### Tela Cadastro de Disciplinas
A tela cadastro de disciplina permite o cadastro da mesma. Ela possui os campos de cadastro e validações necessárias. Um fato importante é que Disciplinas alocadas em turmas (o proximo modulo a ser mostrado após esse) jamais podem ser removidas, e estão limitadas alteração apenas do nome. As três imagens a seguir mostram o cadastro, advertencia do que não pode ser alterado e os campos bloquados a alteração.
![ControleAcademicoTelaDisciplina](https://user-images.githubusercontent.com/76691413/104739447-7dbee100-5725-11eb-842f-1219633c77c4.PNG)

![ControleAcademicoTelaAlteracaoDisciplina](https://user-images.githubusercontent.com/76691413/104739573-9c24dc80-5725-11eb-8929-ca93112d411f.PNG)

![ControleAcademicoTelaAlteracaoDisciplinaImpedida](https://user-images.githubusercontent.com/76691413/104739618-aa72f880-5725-11eb-8051-e9be6f1ca05e.PNG)

#### Módulo Turma
##### Tela Cadastrar Turma
A tela cadastrar turma permite o cadastro de uma turma. Este módulo se relaciona com os outros três módulos (aluno, professor, disciplina). Permite o cadastro de turma sem professores, podendo adiciona-lo depois. O código da turma é gerado automaticamente com base no turno e horário. Existe um limite de digito para cada horario com turno que vai de 0 a 9, caso exeda esse numero será impossivel cadastrar uma turma como o mesmo horario e turno, ou seja, no maximo tera 10 turmas nessa composição.
![ControleAcademicoTelaTurma](https://user-images.githubusercontent.com/76691413/104740513-ccb94600-5726-11eb-906f-e202c0e3fdfb.PNG)

##### Tela Adicionar Professor/Aluno
A tela que adiciona professor e aluno permite a adição obedecendo as regras. Nenhum aluno podera se matricular em uma turma no mesmo horario. O mesmo vale para professores, que nao podem pegar turmas para lecionar que causem choque de horario.
![ControleAcademicoTelaCadastrarProfessorAluno](https://user-images.githubusercontent.com/76691413/104740776-1c980d00-5727-11eb-8b50-1c406a42cc78.PNG)


##### Tela Exibir Turma
A tela exibir turma mostra todas as informações referente a turma, alem do maximo de alunos, etc. 
![ControleAcademicoTelaExibirTurma](https://user-images.githubusercontent.com/76691413/104741142-8dd7c000-5727-11eb-9b0f-0a1b490e4801.PNG)

###### OBS: alem disso é possivel buscar por aluno, professor, disciplina e pela propria turma.
###### OBS II: o método de busca usa predicados e popula uma tableview com o resultado da busca. Exemplo: na busca eu coloco "B" todos os daquele determinado modulo que começem com "B" irão ser mostrados na tableview, o mesmo vale pra numeros. A relação da busca é exatamente com o modulo que ela esta atrelada. Em turma a busca abraça além do modulo, incluindo professores, alunos, disciplinas.

###### OBS III: o projeto foi desenvolvido na cadeira de pratica de programação como disse antes. Há funcionalidades para serem revisadas e melhoradas, mesmo assim foi um prazer construi-lo e me orgulho por ser um dos meus primeiros projetos com interface visual.
