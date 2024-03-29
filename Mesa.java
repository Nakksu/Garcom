public class Mesa {
    private int numeroMesa;
    private int capacidade;
    private String situacao;
    private int idGarcom;

    public Mesa(int numeroMesa, int capacidade, String situacao, int idGarcom) {
        this.numeroMesa = numeroMesa;
        this.capacidade = capacidade;
        this.situacao = situacao;
        this.idGarcom = idGarcom;

    }
    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getIdGarcom() {
        return idGarcom;
    }
}