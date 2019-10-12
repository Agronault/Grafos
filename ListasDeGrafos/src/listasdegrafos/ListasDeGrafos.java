package listasdegrafos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ListasDeGrafos {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        AlgoritmosEmGrafos grafo1 = new AlgoritmosEmGrafos(15);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha qual atividade deseja executar");
        System.out.println("(1)- Busca em profundidade e largura");
        System.out.println("(2)- Dijkstra");
        System.out.println("(3)- AGM");
        int choose = scanner.nextInt();

        if (choose == 1) {
            System.out.println("Busca em Profundidade e Largura");
            System.out.println("(1)- Percorrer grafo 1");
            System.out.println("(2)- Percorrer grafo 2");
            int subchoose = scanner.nextInt();
            if (subchoose == 1) {
                try {
                    FileReader arq = new FileReader("grafo1.txt");
                    BufferedReader lerArq = new BufferedReader(arq);

                    String linha = lerArq.readLine();
                    while (linha != null) {
                        int[] vertices = new int[2];
                        vertices[0] = Integer.parseInt(linha.split(" ")[0]);
                        vertices[1] = Integer.parseInt(linha.split(" ")[1]);

                        //System.out.println(vertices[0]+" "+ vertices[1]);
                        grafo1.insereAresta(vertices[0], vertices[1], 1);

                        linha = lerArq.readLine(); // lê da segunda até a última linha
                    }
                    arq.close();
                } catch (IOException e) {
                    System.err.printf("Erro na abertura do arquivo: %s.\n",
                            e.getMessage());
                }

                System.out.println("Grafo 1________________________________________________");

                grafo1.iniciaBuscaEmProfundidade(0);
                for (int i = 0; i < grafo1.getDistanciaProfundidade().length; i++) {
                    System.out.println("Vertice Profundidade " + i + ": " + grafo1.getDistanciaProfundidade()[i]);
                }

                for (int i = 0; i < grafo1.getVerticePaiProfundidade().length; i++) {
                    System.out.println("Pai Profundidade " + i + ": " + grafo1.getVerticePaiProfundidade()[i]);
                }

                grafo1.iniciaBuscaEmLargura(0);
                for (int i = 0; i < grafo1.getDistanciaLargura().length; i++) {
                    System.out.println("Vertice Largura " + i + ": " + grafo1.getDistanciaLargura()[i]);
                }

                for (int i = 0; i < grafo1.getVerticePredecessorLargura().length; i++) {
                    System.out.println("Pai Largura " + i + ": " + grafo1.getVerticePredecessorLargura()[i]);
                }
            }
            if (subchoose == 2) {
                //GRAFO 2----------------------------------------------------------------------------------------------------------------
                AlgoritmosEmGrafos grafo2 = new AlgoritmosEmGrafos(30);

                try {
                    FileReader arq = new FileReader("grafo2.txt");
                    BufferedReader lerArq = new BufferedReader(arq);

                    String linha = lerArq.readLine();
                    while (linha != null) {
                        int[] vertices = new int[2];
                        vertices[0] = Integer.parseInt(linha.split(" ")[0]);
                        vertices[1] = Integer.parseInt(linha.split(" ")[1]);

                        //System.out.println(vertices[0]+" "+ vertices[1]);
                        grafo2.insereAresta(vertices[0], vertices[1], 1);

                        linha = lerArq.readLine(); // lê da segunda até a última linha
                    }
                    arq.close();
                } catch (IOException e) {
                    System.err.printf("Erro na abertura do arquivo: %s.\n",
                            e.getMessage());
                }

                System.out.println("Grafo 2________________________________________________");

                grafo2.iniciaBuscaEmProfundidade(0);
                for (int i = 0; i < grafo2.getDistanciaProfundidade().length; i++) {
                    System.out.println("Vertice Profundiade " + i + " " + grafo2.getDistanciaProfundidade()[i]);
                }

                for (int i = 0; i < grafo2.getVerticePaiProfundidade().length; i++) {
                    System.out.println("Pai Profundidade " + i + ": " + grafo2.getVerticePaiProfundidade()[i]);
                }

                grafo2.iniciaBuscaEmLargura(0);
                for (int i = 0; i < grafo2.getDistanciaLargura().length; i++) {
                    System.out.println("Vertice Largura " + i + ": " + grafo2.getDistanciaLargura()[i]);
                }

                for (int i = 0; i < grafo2.getVerticePredecessorLargura().length; i++) {
                    System.out.println("Pai Largura " + i + ": " + grafo2.getVerticePredecessorLargura()[i]);
                }
            }
        }

        if (choose == 2) {
            System.out.println("Dijkstra");
            System.out.println("(1)- Percorrer grafo A");
            System.out.println("(2)- Percorrer grafo B");
            int subchoose = scanner.nextInt();
            FileReader arq = null;
            if (subchoose == 1) {
                arq = new FileReader("grafoa.txt");
            } else {
                arq = new FileReader("grafob.txt");
            }
            BufferedReader lerArq = new BufferedReader(arq);

            String vertCount = lerArq.readLine();
            String[] vert1 = vertCount.split(" ");
            AlgoritmosEmGrafos Dijkstra = new AlgoritmosEmGrafos(Integer.valueOf(vert1[0]));
            String linha = lerArq.readLine();

            while (linha != null) {
                String[] valores = linha.split(" ");
                Dijkstra.insereAresta(Integer.valueOf(valores[0]), Integer.valueOf(valores[1]), Integer.valueOf(valores[2]));
                linha = lerArq.readLine();
            }
            
            System.out.println("_____________________Experimento 1_____________________");
            
            System.out.println("Peso para V-1: " + Dijkstra.iniciaDijkstra(0, Integer.valueOf(vert1[0]) - 1));
            
            int[] parent = Dijkstra.getVerticeAntecessorCMC();
            
            int aux=parent[Integer.valueOf(vert1[0]) -1];
            while(aux!=0 && aux!= -1){
                System.out.print("-->"+aux);
                if(aux == -1)
                    break;
                aux=parent[aux];
            }
            System.out.println("-->0 (Raiz)");
            
            System.out.println("_____________________Experimento 2_____________________");
            
            int[] pesos = Dijkstra.iniciaDijkstra(0);
            
            
            for(int i=0; i<parent.length; i++){
            System.out.println("Peso para "+ i+ ": "+ pesos[i]);
              
            
            aux=parent[i];
                
            while(aux!=0 && aux!= -1){
                System.out.print("-->"+aux);
                if(aux == -1)
                    break;
                aux=parent[aux];
            }
            System.out.println("-->0 (Raiz)");
            
            }
            
        }

        //AGM-----------------AGM----------------AGM--------------AGM------------AGM
        if (choose == 3) {
            System.out.println("AGM");
            System.out.println("(1)- Percorrer grafo 1");
            System.out.println("(2)- Percorrer grafo 2");
            int subchoose = scanner.nextInt();
            FileReader arq = null;
            if (subchoose == 1) {
                arq = new FileReader("grafoagm1.txt");
            } else {
                arq = new FileReader("grafoagm2.txt");
            }
            BufferedReader lerArq = new BufferedReader(arq);

            String vertCount = lerArq.readLine();
            String[] vert1 = vertCount.split(" ");
            AlgoritmosEmGrafos AGM = new AlgoritmosEmGrafos(Integer.valueOf(vert1[0]));

            String linha = lerArq.readLine();

            while (linha != null) {
                String[] valores = linha.split(" ");
                AGM.insereArestaNaoOrientada(Integer.valueOf(valores[0]), Integer.valueOf(valores[1]), Integer.valueOf(valores[2]));
                linha = lerArq.readLine();
            }

            System.out.println("Peso AGM: " + AGM.iniciaAGM(0));
        }
    }

}
