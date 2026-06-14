package jogo.excecoes;

/**
 * Exceção lançada quando o contador de dias zera e o jogador morre.
 * @author Arthur Sardenberg Castro Couto
 * @version 1.0
 */
public class FimDeJogoException extends Exception {
    /**
     * Construtor da exceção de fim de jogo.
     * @param mensagem A mensagem detalhando a morte do jogador por exaustão
     */
    public FimDeJogoException(String mensagem) {
        super(mensagem);
    }
}