public class Pessoa {
    int idPessoa;
    String nome;
    String email;
    float peso;

    public Pessoa(){

    }
    public Pessoa(int idPessoa, String nome,String email, float peso){
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.email = email;
        this.peso = peso;
    }
    public int getCodPessoa(){
        return idPessoa;
    }
    public void setCodPessoa(int codPessoa){
        this.idPessoa = codPessoa;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public float getPeso(){
        return peso;
    }
}
