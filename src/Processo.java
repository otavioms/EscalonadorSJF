
class Processo implements Comparable<Processo> {
    String nome;
    int tamanho;
    int tempoRestante;

    public Processo(String nome, int tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.tempoRestante = tamanho;
    }

    @Override
    public int compareTo(Processo outro) {
        return Integer.compare(this.tempoRestante, outro.tempoRestante);
    }
}
