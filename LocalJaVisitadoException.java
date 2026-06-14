package jogo.excecoes;

/**
 * Exceção lançada quando o jogador tenta visitar um local de uso único pela segunda vez.
 * @author Arthur Sardenberg Castro Couto
 * @version 1.0
 */
public class LocalJaVisitadoException extends Exception {
    /**
     * Construtor da exceção para controle de locais de uso único.
     * @param mensagem A mensagem detalhando o erro ou aviso de local já visitado
     */
    public LocalJaVisitadoException(String mensagem) {
        super(mensagem);
    }
}
