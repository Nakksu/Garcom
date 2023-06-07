import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Teste2 {
    static Connection conexao = null;
    static Scanner ler = new Scanner(System.in);
    static Scanner nomeScanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int idGarcom;
        String nome, email, cpf, dataNascimento, telefone, sexo;
        float salFixo;
        System.out.println("Informe os dados do garçom: ");
        System.out.println("Digite o ID");
        idGarcom = ler.nextInt();
        System.out.println("Digite o Nome");
        nome = nomeScanner.nextLine();
        System.out.println("Digite o Email");
        email = ler.next();
        System.out.println("Digite o cpf: ");
        cpf = ler.next();
        System.out.println("Digite a data de nascimento: ");
        dataNascimento = ler.next();
        System.out.println("Digite seu nº telefone: ");
        telefone = ler.next();
        System.out.println("Informe seu sexo: ");
        sexo = ler.next();
        System.out.println("Informe seu salário fixo: ");
        salFixo = ler.nextFloat();

        Garcom g = new Garcom(idGarcom, nome, email, cpf, dataNascimento, telefone, sexo, salFixo);

        inserirPessoa(g);
    }

    private static void inserirPessoa(Garcom garcom) throws SQLException, ClassNotFoundException {
        try {
            conexao = ConexaoBD.getInstance();
            String sql = "INSERT INTO pessoa (idGarcom, nome, email, cpf, dataNascimento, telefone, sexo, salFixo) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, garcom.getId());
            stmt.setString(2, garcom.getNome());
            stmt.setString(3, garcom.getEmail());
            stmt.setString(4, garcom.getCpf());
            stmt.setString(5, garcom.getDataNascimento());
            stmt.setString(6, garcom.getTelefone());
            stmt.setString(7, garcom.getSexo()); // Agora é uma String
            stmt.setFloat(8, garcom.getSalFixo());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Pessoa cadastrada com sucesso.");
        } catch (Exception e) {
            System.out.println("Não foi possível cadastrar a pessoa.");
            e.printStackTrace();
        }
    }
}
