package jogo.cenas;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import jogo.excecoes.FimDeJogoException;
import jogo.excecoes.LocalJaVisitadoException;
import jogo.modelo.Jogador;

/**
 * Representa uma cena onde o jogador deve tomar uma decisão de navegação.
 * @author Arthur Sardenberg Castro Couto
 * @version 1.0
 */
public class CenaDecisao extends Cena {
    private Map<Integer, String> opcoes;
    private int visitas = 0;

    /**
     * Construtor da cena de decisão que contém escolhas de navegação.
     * @param id O identificador único do cenario
     * @param descricao O texto base do cenário apresentado ao jogador
     * @param custoPA A quantidade de energia gasta ao adentrar este local
     */
    public CenaDecisao(String id, String descricao, int custoPA) {
        super(id, descricao, custoPA);
        this.opcoes = new HashMap<>();
    }

    /**
     * Atrela uma nova rota ou escolha possível a este cenário específico.
     * @param numero O número que o jogador deve digitar no terminal para selecionar esta rota
     * @param idDestino O ID da proxima cena que será carregada caso esta opção seja escolhida
     * @param texto O nome ou a ação que descreve a escolha exibida na tela
     */
    public void adicionarOpcao(int numero, String idDestino, String texto) {
        opcoes.put(numero, idDestino);
        setDescricao(getDescricao() + "\n" + numero + " - " + texto);
    }

    /**
     * Processa a exibição do cenário, controla o gasto de PAs e gerencia a entrada numérica do usuário.
     * @param jogador A referência ao status atual do personagem
     * @param scanner O leitor de teclado para captar as escolhas numéricas do usuário
     * @return O ID do próximo cenário escolhido pelo usuário ou o retorno forçado ao acampamento
     * @exception FimDeJogoException Caso os dias do jogador zerassem logo ao entrar no cenário
     */
    @Override
    public String executar(Jogador jogador, Scanner scanner) throws FimDeJogoException {
        if (jogador.getDiasRestantes() <= 0) {
            throw new FimDeJogoException("Você passou três dias sem encontrar água. Exausto e sem esperanças, encolhe-se em um canto e aceita seu destino. Você morreu!");
        }

        visitas++;
        jogador.adicionarHistorico(this.getId());

        int paGasto = (visitas > 1) ? 0 : this.getCustoPA();

        if (paGasto == 0 && this.getCustoPA() > 0) {
            System.out.println("\nVocê já passou por este local. Pelo menos, dessa vez, consegue não perder muito tempo...");
            System.out.println(this.getDescricao());
        } else {
            System.out.println("\n" + this.getDescricao());
            if (paGasto > 0) {
                jogador.gastarTempo(paGasto);
            }
        }


        if (jogador.getPontosAcaoAtual() == 2 && paGasto > 0) {
            return "acampamento";
        }

        if (opcoes.isEmpty()) {
            return "acampamento";
        }

        int escolha = -1;
        while (true) {
            System.out.print("\nEscolha uma opção: ");
            String entrada = scanner.nextLine();

            try {
                escolha = Integer.parseInt(entrada);
                if (opcoes.containsKey(escolha)) {
                    break;
                } else {
                    System.out.print("Opção inválida no mapa.");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Por favor, digite apenas números inteiros.");
            }
        }

        return opcoes.get(escolha);
    }
}