package jogo.controle;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import jogo.cenas.Cena;
import jogo.cenas.CenaDecisao;
import jogo.cenas.CenaEvento;
import jogo.excecoes.FimDeJogoException;
import jogo.excecoes.LocalJaVisitadoException;
import jogo.modelo.GerenciadorArquivos;
import jogo.modelo.Jogador;

/**
 * Classe para controlar a execução do jogo.
 * @author Arthur Sardenberg Castro Couto
 * @version 1.0
 */
public class Jogo {
    private Map<String, Cena> mapaCenas;
    private Jogador jogador;

    /**
     * Construtor padrão da classe de controle. Instancia o jogador e cria a árvore de escolhas do mapa.
     */
    public Jogo() {
        mapaCenas = new HashMap<>();
        jogador = new Jogador();
        inicializarCenas();
    }

    /**
     * Método interno responsável por instanciar, configurar custos, adicionar opcões e
     * popular o HashMap principal que serve como organizador das cenas.
     */
    private void inicializarCenas() {
        CenaDecisao acampamento = new CenaDecisao("acampamento", "Você está no Acampamento Provisório. Para onde deseja ir?", 0);
        acampamento.adicionarOpcao(1, "floresta_ocidental", "Floresta Ocidental");
        acampamento.adicionarOpcao(2, "montanha_isolada", "Montanha Isolada");
        acampamento.adicionarOpcao(3, "floresta_oriental", "Floresta Oriental");

        CenaDecisao florestaOcidental = new CenaDecisao("floresta_ocidental", "Você explora a floresta ocidental com seu facão. Existe uma praia do outro lado e uma caverna.", 1);
        florestaOcidental.adicionarOpcao(1, "segunda_praia", "Segunda praia da ilha");
        florestaOcidental.adicionarOpcao(2, "caverna", "Caverna");

        CenaDecisao segundaPraia = new CenaDecisao("segunda_praia", "Você chegou a uma praia ampla. Há uma construção, uma outra ilha e uma entrada de caverna.", 1);
        segundaPraia.adicionarOpcao(1, "casamata", "Pequena construção");
        segundaPraia.adicionarOpcao(2, "outra_ilha", "Outra ilha");
        segundaPraia.adicionarOpcao(3, "nova_entrada", "Nova entrada para as cavernas");

        CenaEvento florestaOriental = new CenaEvento("floresta_oriental", "Voce esbarra numa cobra, se machuca ao fugir e perde o dia cuidando do ferimento.", 2, "Unico");
        CenaEvento montanhaIsolada = new CenaEvento("montanha_isolada", "Você chega ao local e sobe a montanha, mas não encontra nada. É uma bela vista, mas o dia foi perdido.", 2, "Montanha");
        CenaEvento caverna = new CenaEvento("caverna", "A caverna é escura e úmida. Vocâ gasta muito tempo e energia explorando e não acha nada útil hoje.", 2, "Caverna");
        CenaEvento casamata = new CenaEvento("casamata", "Você acha uma casamata grande, mas completamente destruída. Nada de útil aqui.", 2, "Unico");
        CenaEvento outraIlha = new CenaEvento("outra_ilha", "Você chega na outra ilha, mas seu bote rasga nas pedras! Apesar de abalado, consegue construir uma jangada e voltar de noite.", 2, "OutraIlha");

        CenaEvento novaEntrada = new CenaEvento("Final 1", "Final 1 - Exploração: Você encontrou uma cristalina fonte de água brotando da rocha. Você conseguiu sobrevive até o resgate!", 0, "Final");

        mapaCenas.put("acampamento", acampamento);
        mapaCenas.put("floresta_ocidental", florestaOcidental);
        mapaCenas.put("segunda_praia", segundaPraia);
        mapaCenas.put("floresta_oriental", florestaOriental);
        mapaCenas.put("montanha_isolada", montanhaIsolada);
        mapaCenas.put("caverna", caverna);
        mapaCenas.put("casamata", casamata);
        mapaCenas.put("outra_ilha", outraIlha);
        mapaCenas.put("nova_entrada", novaEntrada);
    }

    /**
     * Ponto de inicio do loop dinâmico de execução do jogo interativo.
     * Interliga as cenas e trata exceções disparadas até o jogador atingir uma condição de parada.
     */
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====== Ilha da Sobrevivência ======");
        System.out.println("Finais já desbloqueados em jogatinas anteriores: " + GerenciadorArquivos.carregarFinais());
        System.out.println("Você sobreviveu a um naufrágio e possui apenas 3 dias para achar água.");

        String cenaAtual = "acampamento";

        while (!cenaAtual.equals("FIM")) {
            Cena cena = mapaCenas.get(cenaAtual);

            try {
                cenaAtual = cena.executar(jogador, scanner);
            } catch (LocalJaVisitadoException e) {
                System.out.println("\nAviso: " + e.getMessage());
                cenaAtual = "acampamento";
            } catch (FimDeJogoException e) {
                System.out.println("\nFim de jogo: " + e.getMessage());
                break;
            }
        }

        System.out.println("\n====== Funcionalidade extra: Mapa de caminhos ======");
        System.out.println("Locais visitados nesta partida: " + jogador.getHistoricoCaminhos());
        System.out.println("Obrigado por jogar!");
        scanner.close();
    }

    /**
     * Método principal que possibilita a compilação e execução da aplicação via JVM.
     * @param args Argumentos oriundos da linha de comando (não utilizados na atual versão do jogo)
     */
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciar();
    }
}