a) Descrição do jogo
O jogo “Ilha da Sobrevivência” é uma aventura com escolhas desenvolvido em Java. Seu objetivo é encontrar um meio de sobreviver em uma ilha deserta no Pacífico, após seu veleiro sofrer um naufrágio. Como água é o recurso mais crucial nesse tipo de situação, procure por uma fonte antes de três dias, o qual é o tempo máximo de sobrevivência para uma ilha tropical durante o verão.

b) Como executar
Crie as classes presentes no código fonte em uma IDE (como o IntelliJ) e execute o arquivo “Jogo.java”, o qual possui o método “main”.
A interface é textual e os comandos são simples, bastando digitar a numeração correspondente ao cenário que quer visitar.

c) Conceitos aplicados
1) Encapsulamento e Ocultação de Informações
Aplicado por meio de atributos “private” e métodos acessadores e modificadores na classe Jogador e Cena. A razão é proteger o estado interno do jogo contra modificações externas incorretas. Um exemplo são os dias restantes só diminuem sob regras estritas do método “gastarTempo()” da classe Jogador.

2) Herança
Aplicada por meio da palavra-chave “extends”, onde CenaDecisao e CenaEvento herdam da superclasse abstrata Cena. O motivo é o reaproveitamento de código e organização hierárquica. Um exemplo é que ambas as subclasses herdam automaticamente os atributos “id” e “descricao”.

3) Interface
Aplicada por meio da assinatura interface “Interativa” implementada pelas cenas. A razão é estabelecer um contrato obrigatório de comportamento para qualquer cenário do jogo. O exemplo é o método “executar()”, que toda cena é forçada a possuir e detalhar.

4) Classe Abstrata
Aplicada por meio da declaração “public abstract class Cena”. A justificativa é criar um modelo genérico que serve de base para outras classes, impedindo que o sistema instancie uma cena sem tipo definido. O exemplo é a própria classe Cena.
5) Polimorfismo
Aplicado na classe que contém o método “main” (Jogo) ao tratar todas as classes quer herdam da superclasse Cena. A razão é permitir que o código execute comportamentos diferentes com a mesma chamada. Um exemplo é o método “cena.executar()” resultar uma lógica na caverna e outra no acampamento.

6) Agregação/Composição
Aplicada por meio da classe “Jogo”, que possui coleções contendo instâncias de “Cena” e “Jogador”. O motivo é construir a estrutura e a história do jogo conectando diferentes objetos de forma articulada, pois só há sentido em instâncias da classe “Cena” e “Jogador” quando elas estão conectadas à classe “Jogo”. Um exemplo é o HashMap que agrupa todas as cenas.

7) Tratamento de Exceções 
Aplicado por meio de blocos try-catch e exceções customizadas como “LocalJaVisitadoException”. A justificativa é capturar erros de digitação ou violações de regras sem travar o programa. Outro exemplo é o tratamento de “NumberFormatException” para entradas que não sejam números.

8) Estruturas de Dados
Aplicado por meio das coleções do Java Collection Framework. A razão é organizar, armazenar e buscar informações do jogo de maneira eficiente na memória. Os exemplos são o HashMap para buscar cenários pelo ID e o ArrayList para o histórico.

9) Arquivos
Aplicado por meio das classes “BufferedWriter” e “BufferedReader” na persistência de dados. O motivo é salvar o progresso de forma permanente no computador para leitura posterior. O exemplo é a criação e atualização automática do arquivo local finais_desbloqueados.txt.

10) Documentação: uso do Javadoc
Aplicada por meio de comentários estruturados no formato por /**...*/. A justificativa é gerar uma documentação técnica legível e profissional do código para outros desenvolvedores. Os exemplos são o uso das palavras @param, @return e @exception nos métodos.

d) Detalhamento do item criativo
A funcionalidade extra do histórico de finais e dos locais visitados foi adotada para engajar o jogador na busca por 100% de conclusão. Ela foi implementada por meio de um ArrayList que registra os passos da partida e da classe GerenciadorArquivos, que grava os finais em um arquivo. Isso é justificado por oferecer uma experiência do usuário melhor e aplicar os conceitos obrigatórios de Arquivos e uso de Collections coerentemente ao restante do código.
Por meio do histórico de passos é possível identificar o caminho para cada final e ir eliminado as combinações de cenários até que todos os caminhos sejam percorridos.
