import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuGarcom {
    static Connection conexao = null;
    static Scanner ler = new Scanner(System.in);
    static Scanner nomeScanner = new Scanner(System.in);


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int opcao;
        do {
            System.out.println("Menu Opções:");
            System.out.println("Informe a opção desejada: ");
            System.out.println("1 - Inserir Garçom");
            System.out.println("2 - Remover garçom pelo ID");
            System.out.println("3 - Alterar garçom pelo ID");
            System.out.println("4 - Buscar garçom pelo ID");
            System.out.println("0 - Sair");
            opcao = ler.nextInt();

            switch (opcao) {
                case 1:
                    inserirPessoa();
                    break;
                case 2:
                    removerPessoaPorID();
                    break;
                case 3:
                    alterarPessoaPorID();
                    break;
                case 4:
                    buscarPessoaPorID();
                    break;
                case 0:
                    System.out.println("Programa encerrado...");
                    break;
                default:
                    System.out.println("Opção inválida. Informe uma opção válida.");
                    break;
            }
            System.out.println();
        } while (opcao != 0);
    }
    private static void inserirPessoa() throws SQLException, ClassNotFoundException {
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
    private static void removerPessoaPorID() throws SQLException, ClassNotFoundException {
        System.out.println("Informe o ID do garçom que será removido: ");
        int idRemover = ler.nextInt();
        removerPessoa(idRemover);
    }
    private static void alterarPessoaPorID() throws SQLException, ClassNotFoundException {
        System.out.println("Informe o ID do garçom que deseja alterar: ");
        int idAlterar = ler.nextInt();
        System.out.println("Informe o novo nome: ");
        String novoNome = nomeScanner.nextLine();
        alterarPessoa(idAlterar, novoNome);
    }
    private static void buscarPessoaPorID() throws SQLException, ClassNotFoundException {
        System.out.println("Informe o ID do garçom a ser buscado: ");
        int idBuscar = ler.nextInt();
        buscarPessoa(idBuscar);
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
            stmt.setString(7, garcom.getSexo());
            stmt.setFloat(8, garcom.getSalFixo());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Garçom cadastrado com sucesso.");
        } catch (Exception e) {
            System.out.println("Não foi possível cadastrar o garçom.");
            e.printStackTrace();
        }
    }
    private static void removerPessoa(int id) throws SQLException, ClassNotFoundException {
        try {
            conexao = ConexaoBD.getInstance();
            String sql = "DELETE FROM pessoa WHERE idGarcom = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            if (rowsAffected > 0) {
                System.out.println("Garçom removido com sucesso.");
            } else {
                System.out.println("Nenhum garçom encontrado com o ID informado.");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível remover a garçom.");
            e.printStackTrace();
        }
    }
    private static void alterarPessoa(int id, String novoNome) throws SQLException, ClassNotFoundException {
        try {
            conexao = ConexaoBD.getInstance();
            String sql = "UPDATE pessoa SET nome = ? WHERE idGarcom = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, novoNome);
            stmt.setInt(2, id);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            if (rowsAffected > 0) {
                System.out.println("Garçom foi alterado.");
            } else {
                System.out.println("Nenhum garçom encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível alterar o garçom.");
            e.printStackTrace();
        }
    }
    private static void buscarPessoa(int id) throws SQLException, ClassNotFoundException {
        try {
            conexao = ConexaoBD.getInstance();
            String sql = "SELECT * FROM pessoa WHERE idGarcom = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idGarcom = rs.getInt("idGarcom");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String dataNascimento = rs.getString("dataNascimento");
                String telefone = rs.getString("telefone");
                String sexo = rs.getString("sexo");
                float salFixo = rs.getFloat("salFixo");

                Garcom pessoaEncontrada = new Garcom(idGarcom, nome, email, cpf, dataNascimento, telefone, sexo, salFixo);
                System.out.println("Garçom encontrado:");
            } else {
                System.out.println("Não há nenhum garçom cadastrado com esse ID.");
            }
            stmt.close();
        } catch (Exception e) {
            System.out.println("Não foi possível buscar o garçom.");
            e.printStackTrace();
        }
    }
}
