package listasdegrafos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import javafx.util.Pair;

public class AlgoritmosEmGrafos extends Grafos {

    private final int[] distanciaProfundidade;
    private final int[] distanciaLargura;
    private final int[] verticePredecessorProfundidade;
    private final int[] verticePredecessorLargura;
    private final byte[] cor; // -1 = branco, 0 = cinza, 1= preto
    private final int[] distanciasCMC;
    private final int[] verticeAntecessorCMC;
    private boolean doneCMC;
    private int lastRoot;

    public ArrayList<Pair<Integer, Integer>> getArestasAGM() {
        return arestasAGM;
    }

    public int[] getVerticeAntecessorAGM() {
        return verticeAntecessorAGM;
    }

    private ArrayList< Pair< Integer, Integer>> arestasAGM;
    private final int[] verticeAntecessorAGM;
    private ArrayList<Integer> visitados;
    private ArrayList<Integer> naovisitados;

    public AlgoritmosEmGrafos(int vertices) {
        super(vertices);
        distanciaProfundidade = new int[vertices];
        distanciaLargura = new int[vertices];
        verticePredecessorProfundidade = new int[vertices];
        verticePredecessorLargura = new int[vertices];
        cor = new byte[distanciaLargura.length]; // -1 = branco, 0 = cinza, 1= preto
        distanciasCMC = new int[vertices];
        verticeAntecessorCMC = new int[vertices];
        doneCMC = false;
        this.arestasAGM = new ArrayList<>();
        this.verticeAntecessorAGM = new int[vertices];
    }

    private void buscaProfundidade(int vertice) {

        for (int i = 0; i < distanciaProfundidade.length; i++) {
            if (super.matrizAdjacencia[vertice][i] != 0 && distanciaProfundidade[i] >= distanciaProfundidade.length) {
                verticePredecessorProfundidade[i] = vertice;
                distanciaProfundidade[i] = distanciaProfundidade[vertice] + super.getPeso(vertice, i);
                buscaProfundidade(i);
            }
        }

    }

    public void iniciaBuscaEmProfundidade(int vertice) {
        for (int i = 0; i < verticePredecessorProfundidade.length; i++) {
            verticePredecessorProfundidade[i] = -1;
        }

        verticePredecessorProfundidade[vertice] = vertice;

        for (int i = 0; i < distanciaProfundidade.length; i++) {
            distanciaProfundidade[i] = distanciaProfundidade.length + 1;
        }

        distanciaProfundidade[vertice] = 0;

        buscaProfundidade(vertice);
    }

    public void iniciaBuscaEmLargura(int vertice) {
        for (int i = 0; i < verticePredecessorLargura.length; i++) {
            verticePredecessorLargura[i] = -1;
        }

        verticePredecessorLargura[vertice] = vertice;

        for (int i = 0; i < distanciaLargura.length; i++) {
            distanciaLargura[i] = distanciaLargura.length + 1;
        }

        distanciaLargura[vertice] = 0;

        for (int i = 0; i < distanciaLargura.length; i++) {// iniciar branco
            cor[i] = -1;
        }

        BuscaLargura(vertice);
    }

    private void BuscaLargura(int vertice) {
        cor[vertice] = 1;//marca o vertice atual como preto

        for (int i = 0; i < distanciaLargura.length; i++) {
            if (matrizAdjacencia[vertice][i] != 0 && cor[i] == -1) {// se for adjacente e branco
                cor[i] = 0; // marca como cinza
                verticePredecessorLargura[i] = vertice;
                distanciaLargura[i] = distanciaLargura[vertice] + super.getPeso(vertice, i);
            }
        }

        for (int i = 0; i < distanciaLargura.length; i++) {
            if (cor[i] == 0) {
                BuscaLargura(i);
            }
        }
    }

    public int iniciaDijkstra(int verticeInicial, int verticeFinal) { // para busca de um vértice específico
        if (this.doneCMC == false || verticeInicial != this.lastRoot) {
            dijkstra(verticeInicial);
            this.lastRoot = verticeInicial;
            this.doneCMC = true;
        }
        return this.distanciasCMC[verticeFinal];
    }

    public int[] iniciaDijkstra(int verticeInicial) { // para buscas gerais
        if (this.doneCMC == false || verticeInicial != this.lastRoot) {
            dijkstra(verticeInicial);
            this.lastRoot = verticeInicial;
            this.doneCMC = true;
        }
        return this.distanciasCMC;
    }

    private void dijkstra(int verticeInicial) {// executa dijkstra

        boolean[] done = new boolean[this.numeroVertices];

        for (int i = 0; i < this.numeroVertices; i++) {
            this.distanciasCMC[i] = Integer.MAX_VALUE; //iniciando como infinito
            this.verticeAntecessorCMC[i] = -1;        //se terminar com -1, não é alcançável
            done[i] = false;                         //ainda não foi retirado da fila
        }
        this.distanciasCMC[verticeInicial] = 0; //distancia da raiz é 0

        while (!vazio(done)) {// enquanto não resolveu tudo
            int aux = prioridade(done); //retira o menor da fila
            done[aux] = true; //marcar como visitado
            for (int i = 0; i < this.matrizAdjacencia[aux].length; i++) {
                if (this.matrizAdjacencia[aux][i] != 0 && this.distanciasCMC[aux] + this.matrizAdjacencia[aux][i] < this.distanciasCMC[i]) {
                    //verificar se o vértice é adjacente e a nova solução é melhor que a antiga
                    this.distanciasCMC[i] = this.distanciasCMC[aux] + this.matrizAdjacencia[aux][i];
                    //atribuição
                    this.verticeAntecessorCMC[i] = aux;
                }
            }
        }
    }

    private int prioridade(boolean[] done) {
        int aux = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < done.length; i++) {
            if (this.distanciasCMC[i] <= aux && done[i] == false) {
                aux = this.distanciasCMC[i];
                index = i;
            }
        }
        return index;
    }

    private boolean vazio(boolean[] done) {
        for (int i = 0; i < done.length; i++) {
            if (done[i] == false) {
                return false;
            }
        }
        return true;
    }

    public int iniciaAGM(int vertice) {
        this.arestasAGM = new ArrayList<>();
        visitados = new ArrayList<>();
        naovisitados = new ArrayList<>();

        for (int i = 0; i < this.numeroVertices; i++) {
            naovisitados.add(i);
        }

        naovisitados.remove((Integer) vertice);
        visitados.add((Integer) vertice);

        for (int i = 0; i < this.numeroVertices; i++) {
            this.verticeAntecessorAGM[i] = -1;
        }

        return AGM(vertice);
    }

    private int AGM(int vertice) { //executa a construcao da árvore
        int peso = 0;

        while (!this.naovisitados.isEmpty()) {//enquanto ainda há não visitados
            int min = Integer.MAX_VALUE;
            Integer visit = -1;
            Integer notvisit = -1;

            for (Integer visitado : this.visitados) {//faz interface de corte como demonstrado em sala
                for (Integer naovisitado : this.naovisitados) {
                    if (this.matrizAdjacencia[visitado][naovisitado] < min && this.matrizAdjacencia[visitado][naovisitado] != 0) {//menor válido
                        min = this.matrizAdjacencia[visitado][naovisitado];
                        visit = visitado;
                        notvisit = naovisitado;
                    }
                }
            }
            if (visit != -1 && notvisit != -1) {//validade
                this.arestasAGM.add(new Pair(visit, notvisit));//adicionar nova aresta (pair A->B)
                this.verticeAntecessorAGM[notvisit] = visit;
                for (int i = 0; i < this.naovisitados.size(); i++) {//é preciso percorrer por problema
                    if (this.naovisitados.get(i) == notvisit) {    //de compatibilidade índice-objeto do remove()
                        //System.out.println(visit + " : " + notvisit);
                        this.naovisitados.remove(i);//se tornou visitado
                        break;
                    }
                }
                this.visitados.add(notvisit);
                //System.out.println(min);
                peso += min;
            }

        }

        return peso;
    }
    

    public int[] getDistanciaProfundidade() {
        return distanciaProfundidade;
    }

    public int[] getVerticePaiProfundidade() {
        return verticePredecessorProfundidade;
    }

    public int[] getDistanciaLargura() {
        return distanciaLargura;
    }

    public int[] getVerticePredecessorLargura() {
        return verticePredecessorLargura;
    }

    public int[] getVerticeAntecessorCMC() {
        return verticeAntecessorCMC;
    }

}
