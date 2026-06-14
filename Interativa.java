package jogo.modelo;

import java.util.Scanner;
import jogo.excecoes.FimDeJogoException;
import jogo.excecoes.LocalJaVisitadoException;

/**
 * Interface que define o comportamento interativo das cenas.
 * @author Arthur Sardenberg Castro Couto
 * @version 1.0
 */
public interface Interativa {
    /**
     * Método que executa a interação da cena.
     * @param jogador O jogador atual
     * @param scanner O scanner para ler entradas
     * @return O ID da próxima cena
     * @exception LocalJaVisitadoException Se o local não puder ser visitado novamente
     * @exception FimDeJogoException Se o jogador morrer de exaustão
     */
    String executar(Jogador jogador, Scanner scanner) throws LocalJaVisitadoException, FimDeJogoException;
}