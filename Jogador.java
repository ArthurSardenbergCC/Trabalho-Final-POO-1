package jogo.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o Jogador, encapsulando seus atributos e estado.
 * @author Arthur Sardenberg Castro Couto
 * @version 1.0
 */
public class Jogador {
    private int diasRestantes;
    private int pontosAcao;
    private boolean boteIntacto;
    private List<String> historicoCaminhos;

    /**
     * Construtor padrão da classe Jogador.
     * Inicializa o personagem com 3 dias restantes, 2 pontos de ação, bote intacto e um histórico vazio.
     */
    public Jogador() {
        this.diasRestantes = 3;
        this.pontosAcao = 2; 
        this.boteIntacto = true;
        this.historicoCaminhos = new ArrayList<>();
    }

    /**
     * Deduz os pontos de ação do jogador e gerencia a virada do dia
     * caso os pontos cheguem a zero ou fiquem negativos.
     * @param pa A quantidade de pontos de ação a ser descontado
     */
    public void gastarTempo(int pa) {
        this.pontosAcao -= pa;
        if (this.pontosAcao <= 0) {
            this.diasRestantes--;
            this.pontosAcao = 2;
            System.out.println("\nNão há mais luz solar. Você retorna ao acampamento para descansar. ");
            System.out.println("Fim do dia. Dias restantes: " + this.diasRestantes + " \n");
        }
    }

    /**
     * Adiciona um cenário ao array de rastreamento do jogador para formar o mapa de ações finais.
     * @param local O ID do cenário visitado
     */
    public void adicionarHistorico(String local) {
        historicoCaminhos.add(local);
    }

    /**
     * Altera o estado do bote de sobrevivência para destruído, bloqueando rotas que dependam dele.
     */
    public void destruirBote() {
        this.boteIntacto = false;
    }

    /**
     * Consulta o saldo atual de Pontos de Ação (PA) do jogador no dia corrente.
     * @return O valor inteiro representando a energia/tempo restante para ações
     */
    public int getPontosAcaoAtual() {
        return pontosAcao;
    }

    /**
     * Recupera a quantidade de dias que o jogador ainda tem para sobreviver.
     * * @return O numero inteiro de dias restantes
     */
    public int getDiasRestantes() {
        return diasRestantes;
    }

    /**
     * Verifica a condição atual do bote de sobrevivência do personagem.
     * @return true se o bote estiver utilizável, false caso já tenha sido rasgado
     */
    public boolean isBoteIntacto() {
        return boteIntacto;
    }

    /**
     * Recupera a lista sequencial de todos os cenários pelos quais o jogador navegou.
     * @return Uma lista contendo os identificadores (IDs) das cenas visitadas
     */
    public List<String> getHistoricoCaminhos() {
        return historicoCaminhos;
    }
}