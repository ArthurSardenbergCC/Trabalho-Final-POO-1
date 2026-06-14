package jogo.cenas;

import jogo.modelo.Interativa;

/**
 * Classe abstrata base para todas as cenas do jogo.
 * @author Arthur Sardenberg Castro Couto
 * @version 1.0
 */
public abstract class Cena implements Interativa {
    private String id;
    private String descricao;
    private int custoPA;

    /**
     * Construtor base para a criação de uma cena.
     * @param id O identificador único da cena
     * @param descricao O texto descritivo que sera exibido ao jogador
     * @param custoPA O custo de Pontos de Ação para entrar nesta cena
     */
    public Cena(String id, String descricao, int custoPA) {
        this.id = id;
        this.descricao = descricao;
        this.custoPA = custoPA;
    }

    /**
     * Recupera a descrição de um cenário específico
     * @return O texto que descreve o cenário
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Recupera o custo em pontos de ação do cenário escolhido
     * @return Um inteiro que representa os pontos de ação gastos
     */
    public int getCustoPA() {
        return custoPA;
    }

    /**
     * Recupera o identificador unico do cenário atual.
     * @return Uma String contendo o ID da cena (exemplo: "floresta_ocidental")
     */
    public String getId() {
        return id;
    }

    /**
     * Permite que subclasses modifiquem ou estendam a descrição se necessário.
     * @param novaDescricao O novo texto descritivo
     */
    public void setDescricao(String novaDescricao) {
        this.descricao = novaDescricao;
    }
}