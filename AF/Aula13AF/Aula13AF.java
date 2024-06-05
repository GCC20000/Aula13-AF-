import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Aula13AF {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Quantas notas serão inseridas para cada aluno?");
        int numNotas = scanner.nextInt();
        
        List<Double> pesos = new ArrayList<>();
        double somaPesos = 0;
        for (int i = 1; i <= numNotas; i++) {
            System.out.println("Digite o peso da nota " + i + " (em %):");
            double peso = scanner.nextDouble();
            pesos.add(peso);
            somaPesos += peso;
        }
        
        if (somaPesos != 100) {
            System.out.println("Erro: A soma dos pesos das notas deve ser exatamente 100%.");
            return;
        }
        
        System.out.println("Quantos alunos terão as notas calculadas?");
        int numAlunos = scanner.nextInt();
        scanner.nextLine();
        
        Map<String, List<Double>> alunosNotas = new HashMap<>();
        Map<String, Double> alunosMedias = new HashMap<>();
        
        for (int i = 1; i <= numAlunos; i++) {
            System.out.println("Digite o nome do aluno " + i + ":");
            String nomeAluno = scanner.nextLine();
            
            List<Double> notas = new ArrayList<>();
            boolean reinserir = true;
            
            while (reinserir) {
                notas.clear();
                
                for (int j = 1; j <= numNotas; j++) {
                    System.out.println("Digite a nota " + j + " para o aluno " + nomeAluno + ":");
                    double nota = scanner.nextDouble();
                    notas.add(nota);
                }
                
                double mediaFinal = calcularMediaFinal(notas, pesos);
                String condicao = calcularCondicao(mediaFinal);
                
                System.out.println("Aluno: " + nomeAluno);
                System.out.println("Média final: " + mediaFinal);
                System.out.println("Condição: " + condicao);
                
                System.out.println("Deseja reinserir os dados para o aluno " + nomeAluno + "? (Digite 'sim' para reinserir, 'nao' para prosseguir para o próximo aluno)");
                scanner.nextLine();
                String reinserirOpcao = scanner.nextLine();
                if (!reinserirOpcao.equalsIgnoreCase("sim")) {
                    reinserir = false;
                    alunosNotas.put(nomeAluno, new ArrayList<>(notas));
                    alunosMedias.put(nomeAluno, mediaFinal);
                }
            }
            
            System.out.println();
        }
        
        double mediaGeral = calcularMediaGeral(new ArrayList<>(alunosMedias.values()));
        System.out.println("A média geral das notas dos alunos é: " + mediaGeral);
        
        while (true) {
            System.out.println("Deseja consultar a nota de um aluno em especial? (Digite 'sim' para consultar, 'nao' para encerrar)");
            String consultaOpcao = scanner.nextLine();
            if (!consultaOpcao.equalsIgnoreCase("sim")) {
                break;
            }
            
            System.out.println("Digite o nome do aluno que deseja consultar:");
            String nomeConsulta = scanner.nextLine();
            
            if (alunosNotas.containsKey(nomeConsulta)) {
                System.out.println("Notas do aluno " + nomeConsulta + ": " + alunosNotas.get(nomeConsulta));
                System.out.println("Média final do aluno " + nomeConsulta + ": " + alunosMedias.get(nomeConsulta));
            } else {
                System.out.println("Aluno não encontrado.");
            }
        }
        
        scanner.close();
    }
    
    public static double calcularMediaFinal(List<Double> notas, List<Double> pesos) {
        double soma = 0;
        for (int i = 0; i < notas.size(); i++) {
            soma += (notas.get(i) * pesos.get(i) / 100);
        }
        return soma;
    }
    
    public static String calcularCondicao(double mediaFinal) {
        if (mediaFinal >= 5) {
            return "Aprovado";
        } else if (mediaFinal >= 2.1) {
            return "Necessita fazer a substitutiva";
        } else {
            return "Reprovado";
        }
    }
    
    public static double calcularMediaGeral(List<Double> mediasFinais) {
        double soma = 0;
        for (double media : mediasFinais) {
            soma += media;
        }
        return soma / mediasFinais.size();
    }
}
