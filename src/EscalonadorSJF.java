import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EscalonadorSJF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Processo> tabelaProcessos = new ArrayList<>();
        Processo processoExecutando = null;  // Declare a variável aqui

        while (true) {
            exibirTabela(tabelaProcessos, processoExecutando);

            System.out.println("\n1. Criar novo processo");
            System.out.println("2. Executar processo");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    criarNovoProcesso(scanner, tabelaProcessos);
                    break;
                case 2:
                    processoExecutando = executarProcesso(tabelaProcessos);
                    break;
                case 3:
                    System.out.println("Encerrando o programa.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void criarNovoProcesso(Scanner scanner, ArrayList<Processo> tabelaProcessos) {
        System.out.print("Digite o nome do processo: ");
        String nome = scanner.next();
        System.out.print("Digite o tamanho do processo (em segundos): ");
        int tamanho = scanner.nextInt();

        Processo novoProcesso = new Processo(nome, tamanho);
        tabelaProcessos.add(novoProcesso);


        System.out.println("Processo criado com sucesso!");
    }

    private static Processo executarProcesso(ArrayList<Processo> tabelaProcessos) {
        if (tabelaProcessos.isEmpty()) {
            System.out.println("Nenhum processo para executar.");
            return null;
        }

        Collections.sort(tabelaProcessos);

        Processo processoExecutando = tabelaProcessos.get(0);
        tabelaProcessos.remove(processoExecutando);

        System.out.println("\nExecutando processo: " + processoExecutando.nome);
        processoExecutando.tempoRestante--;

        if (processoExecutando.tempoRestante > 0) {
            tabelaProcessos.add(processoExecutando);
            System.out.println("Processo ainda em execução. Será continuado posteriormente.");
        } else {
            System.out.println("Processo concluído.");
        }

        return processoExecutando;
    }

    private static void exibirTabela(ArrayList<Processo> tabelaProcessos, Processo processoEmExecucao) {
        System.out.println("\nTabela de Processos:");
        System.out.println("Nome | Tamanho | Tempo Restante | Situação");
        System.out.println("-----|---------|-----------------|----------");

        for (Processo processo : tabelaProcessos) {
            String situacao;

            if (processo == processoEmExecucao) {
                situacao = "Em Execução";
            } else {
                situacao = "Pronto";
            }

            System.out.printf("%-4s | %-7d | %-15d | %-8s\n", processo.nome, processo.tamanho,
                    processo.tempoRestante, situacao);
        }
    }

}