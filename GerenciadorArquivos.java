package jogo.modelo;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Gerenciador de Arquivos para a funcionalidade extra (Salvar Finais Desbloqueados).
 * @author Arthur Sardenberg Castro Couto
 * @version 1.0
 */
public class GerenciadorArquivos {
    private static final String arquivos_finais = "finais_desbloqueados.txt";

    /**
     * Salva um final no arquivo de texto.
     * @param finalId Nome ou ID do final obtido
     */
    public static void salvarFinal(String finalId) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivos_finais, true))) {
            bw.write(finalId);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Não foi possível acessar o arquivo de salvamento. Seu progresso atual não pôde ser gravado, mas o jogo continuará normalmente. ");
        }
    }

    /**
     * Lê os finais já desbloqueados pelo jogador.
     * @return Um conjunto (Set) com os finais únicos
     */
    public static Set<String> carregarFinais() {
        Set<String> finais = new HashSet<>();
        File arquivo = new File(arquivos_finais);
        if (!arquivo.exists()){
            return finais;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                finais.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Não foi possível carregar o arquivo de progresso anterior. O jogo continuará normalmente sem o histórico de finais.");
        }
        return finais;
    }
}