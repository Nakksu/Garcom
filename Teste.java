import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Teste{
    static Connection conexao = null;
    static Scanner ler = new Scanner(System.in);
    static Scanner nomeScanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int idPessoa;
        String nome, email;
        float peso;
        System.out.println("Digite os dados da Pessoa");
        System.out.println("Digite o ID");
        idPessoa = ler.nextInt();
        System.out.println("Digite o Nome");
        nome = nomeScanner.nextLine();
        System.out.println("Digite o Email");
        email = ler.next();
        System.out.println("Digite o Peso");
        peso = Float.parseFloat(ler.next());

        Pessoa p = new Pessoa(idPessoa, nome, email, peso);

        inserirPessoa(p);

    }

    private static void inserirPessoa(Pessoa plida) throws SQLException, ClassNotFoundException {
        try {
            conexao = ConexaoBD.getInstance();
            String sql = "insert into pessoa (idPessoa, nome, email, peso) values (?,?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, plida.getCodPessoa());
            stmt.setString(2, plida.getNome());
            stmt.setString(3, plida.getEmail());
            stmt.setDouble(4, plida.getPeso());
            stmt.execute();
            stmt.close();
            System.out.println("Pessoa Cadastrada com Sucesso");
        } catch (Exception e) {
            System.out.println("NÃO FOI POSSIVEL CADASTRAR PESSOA");
            e.printStackTrace();
        }
    }
}