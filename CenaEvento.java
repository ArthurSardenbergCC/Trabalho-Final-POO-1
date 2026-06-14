package jogo.cenas;

import java.util.Scanner;
import jogo.excecoes.LocalJaVisitadoException;
import jogo.modelo.GerenciadorArquivos;
import jogo.modelo.Jogador;

/**
 * Representa uma cena de final de jogo ou evento único.
 * @author Arthur Sardenberg Castro Couto
 * @version 1.0
 */
public class CenaEvento extends Cena {
    private boolean jaVisitado = false;
    private String tipoEvento;
    private int visitasCaverna = 0;

    /**
     * Construtor da cena que processa um acontecimento isolado, um easter egg ou um final de jogo.
     * @param id O identificador da cena de evento
     * @param descricao O desfecho narrativo ou texto do acontecimento
     * @param custoPA O custo de interagir com este evento
     * @param tipoEvento A categoria lógica do evento (exemplo: "Montanha", "Caverna", "Unico", "Final")
     */
    public CenaEvento(String id, String descricao, int custoPA, String tipoEvento) {
        super(id, descricao, custoPA);
        this.tipoEvento = tipoEvento;
    }

    /**
     * Avalia as condições do jogador para entregar diferentes resultados ou finais conforme o tipo do evento.
     * @param jogador A referência ao status atual do personagem e seu inventário/tempo
     * @param scanner A ferramenta de leitura de terminal (mantida pela consistencia do Polimorfismo da Interface)
     * @return A String "FIM" caso o evento acabe a aventura, ou "acampamento" para voltar ao hub
     * @exception LocalJaVisitadoException Se for do tipo "Unico" e o jogador tentar ativá-lo pela segunda vez
     */
    @Override
    public String executar(Jogador jogador, Scanner scanner) throws LocalJaVisitadoException {
        jogador.adicionarHistorico(this.getId());

        if (tipoEvento.equals("Unico") && jaVisitado) {
            throw new LocalJaVisitadoException("Você já visitou o local (" + this.getId() + ") e não há sentido em explorá-lo novamente!");
        }

        jaVisitado = true;

        if (tipoEvento.equals("OutraIlha")) {
            System.out.println("\n" + this.getDescricao());
            jogador.destruirBote();
            jogador.gastarTempo(this.getCustoPA());
            return "acampamento";
        }

        if (tipoEvento.equals("Montanha")) {
            if (jogador.getDiasRestantes() == 1 && jogador.isBoteIntacto()) {
                System.out.println("\nFinal 2 - Resgate Milagroso: Você decide subir a montanha com suas últimas forcas, avista um navio, dispara o sinalizador e é salvo!");
                GerenciadorArquivos.salvarFinal("Final 2");
                return "FIM";
            } else {
                System.out.println("\n" + this.getDescricao());
                jogador.gastarTempo(this.getCustoPA());
                return "acampamento";
            }
        }

        if (tipoEvento.equals("Caverna")) {
            visitasCaverna++;
            if (visitasCaverna == 3) {
                System.out.println("\nFinal 3 - Wilson: Você encontra a bola Wilson, um dispositivo com internet Starlink e suprimentos. Você foi salvo!");
                GerenciadorArquivos.salvarFinal("Final 3");
                return "FIM";
            } else {
                System.out.println("\nVisita " + visitasCaverna + " da Caverna: " + this.getDescricao());
                jogador.gastarTempo(this.getCustoPA());
                return "acampamento";
            }
        }

        if (tipoEvento.equals("Final")) {
            System.out.println("\n" + this.getDescricao());
            GerenciadorArquivos.salvarFinal(this.getId());
            return "FIM";
        }

        System.out.println("\n" + this.getDescricao());
        jogador.gastarTempo(this.getCustoPA());
        return "acampamento";
    }
}