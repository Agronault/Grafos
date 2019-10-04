package listasdegrafos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ListasDeGrafos {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        AlgoritmosEmGrafos grafo1 = new AlgoritmosEmGrafos(15);
        
        
        int choose= 3;

        if(choose == 1){
            try {
            FileReader arq = new FileReader(args[0]);
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
        //GRAFO 2----------------------------------------------------------------------------------------------------------------
        if(choose == 2){
            AlgoritmosEmGrafos grafo2 = new AlgoritmosEmGrafos(30);

        try {
            FileReader arq = new FileReader(args[1]);
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
        //-LARGURA-----------------------LARGURA--------------------------------LARGURA---------------------------------LARGURA-----------------------
    
        
        //AGM-----------------AGM----------------AGM--------------AGM------------AGM
        if(choose == 3){
        FileReader arq = new FileReader(args[2]);
        BufferedReader lerArq = new BufferedReader(arq);
        
        String vertCount = lerArq.readLine();
        String []vert1 = vertCount.split(" ");
        AlgoritmosEmGrafos AGM = new AlgoritmosEmGrafos(Integer.valueOf(vert1[0]));
        
        String linha= lerArq.readLine();
        
        while(linha!=null){
            String[] valores= linha.split(" ");
            AGM.insereArestaNaoOrientada(Integer.valueOf(valores[0]), Integer.valueOf(valores[1]), Integer.valueOf(valores[2]));
            linha= lerArq.readLine();
        }
        
        System.out.println("Peso AGM: "+AGM.iniciaAGM(0));
        }
    }
    
    
}
