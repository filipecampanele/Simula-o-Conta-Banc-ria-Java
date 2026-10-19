import java.util.Scanner;

public class Main {

    static class Conta {
        private final String agencia;
        private final int numero;
        private final String nomeCliente;
        private double saldo;

        Conta(String agencia, int numero, String nomeCliente, double saldo) {
            this.agencia = agencia;
            this.numero = numero;
            this.nomeCliente = nomeCliente;
            this.saldo = saldo;
        }

        void depositar(double valor) {
            if (valor <= 0) {
                System.out.println("Valor invalido para deposito.");
                return;
            }
            saldo += valor;
            System.out.printf("Deposito de R$ %.2f realizado com sucesso.%n", valor);
        }

        void sacar(double valor) {
            if (valor <= 0) {
                System.out.println("Valor invalido para saque.");
            } else if (valor > saldo) {
                System.out.println("Saldo insuficiente.");
            } else {
                saldo -= valor;
                System.out.printf("Saque de R$ %.2f realizado com sucesso.%n", valor);
            }
        }

        void mensagemBoasVindas() {
            System.out.printf(
                "%nOla %s, obrigado por criar uma conta em nosso banco, "
                + "sua agencia e %s, conta %d e seu saldo de R$ %.2f "
                + "ja esta disponivel para saque.%n",
                nomeCliente, agencia, numero, saldo);
        }

        void mostrarSaldo() {
            System.out.printf("Saldo atual: R$ %.2f%n", saldo);
        }
    }

    // Aceita tanto 100.50 quanto 100,50
    static double lerValor(Scanner sc, String texto) {
        while (true) {
            System.out.print(texto);
            String entrada = sc.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
            }
        }
    }

    static int lerInteiro(Scanner sc, String texto) {
        while (true) {
            System.out.print(texto);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== CRIACAO DE CONTA BANCARIA ===");
        System.out.print("Nome do cliente: ");
        String nome = sc.nextLine();
        System.out.print("Agencia (ex: 067-8): ");
        String agencia = sc.nextLine();
        int numero = lerInteiro(sc, "Numero da conta: ");
        double saldo = lerValor(sc, "Saldo inicial: R$ ");

        Conta conta = new Conta(agencia, numero, nome, saldo);
        conta.mensagemBoasVindas();

        int opcao;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("0 - Sair");
            opcao = lerInteiro(sc, "Escolha: ");

            switch (opcao) {
                case 1 -> conta.mostrarSaldo();
                case 2 -> conta.depositar(lerValor(sc, "Valor do deposito: R$ "));
                case 3 -> conta.sacar(lerValor(sc, "Valor do saque: R$ "));
                case 0 -> System.out.println("Encerrando. Obrigado por usar nosso banco!");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        sc.close();
    }
}
