import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Teste1 {
    static Connection conexao = null;
    static Scanner ler = new Scanner(System.in);
    static Scanner lerScanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int numero, capacidade;
        String situacao;
        int idGarcom;

        System.out.println("Informe os dados da mesa: ");
        System.out.println("Digite o número da mesa: ");
        numero = ler.nextInt();
        System.out.println("Digite a situação: ");
        situacao = lerScanner.nextLine();
        System.out.println("Digite a capacidade: ");
        capacidade = ler.nextInt();
        System.out.println("Digite o ID do garçom: ");
        idGarcom = ler.nextInt();

        Mesa m = new Mesa(numero, capacidade, situacao, idGarcom);

        inserirMesa(m);

        System.out.println("Digite o número da mesa que será removida: ");
        int idRemover = ler.nextInt();
        removerMesa(idRemover);

        System.out.println("Digite o número da mesa que deseja alterar: ");
        int numeroMesa = ler.nextInt();
        System.out.println("Informe a nova capacidade: ");
        int capacidadeNova = lerScanner.nextInt();
        alterarMesa(numeroMesa, capacidadeNova);

        System.out.println("Digite o número da mesa a ser buscada: ");
        int idBuscar = ler.nextInt();
        buscarMesa(idBuscar);
    }

    private static void inserirMesa(Mesa mesa) throws SQLException, ClassNotFoundException {
        try {
            conexao = ConexaoBD.getInstance();
            String sql = "INSERT INTO mesa (numero, capacidade, situacao, idGarcom) VALUES (?,?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, mesa.getNumeroMesa());
            stmt.setInt(2, mesa.getCapacidade());
            stmt.setString(3, mesa.getSituacao());
            stmt.setInt(4, mesa.getIdGarcom());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Mesa cadastrada com sucesso.");
        } catch (Exception e) {
            System.out.println("Não foi possível cadastrar a mesa.");
            e.printStackTrace();
        }
    }

    private static void removerMesa(int numeroMesa) throws SQLException, ClassNotFoundException {
        try {
            conexao = ConexaoBD.getInstance();
            String sql = "DELETE FROM mesa WHERE numero = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, numeroMesa);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            if (rowsAffected > 0) {
                System.out.println("Mesa removida com sucesso.");
            } else {
                System.out.println("Nenhuma mesa encontrada com o número fornecido.");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível remover a mesa.");
            e.printStackTrace();
        }
    }

    private static void alterarMesa(int numeroMesa, int capacidade) throws SQLException, ClassNotFoundException {
        try {
            conexao = ConexaoBD.getInstance();
            String sql = "UPDATE mesa SET capacidade = ? WHERE numero = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, capacidade);
            stmt.setInt(2, numeroMesa);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            if (rowsAffected > 0) {
                System.out.println("Mesa alterada com sucesso.");
            } else {
                System.out.println("Nenhuma mesa encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível alterar a mesa.");
            e.printStackTrace();
        }
    }

    private static void buscarMesa(int numeroMesa) throws SQLException, ClassNotFoundException {
        try {
            conexao = ConexaoBD.getInstance();
            String sql = "SELECT * FROM mesa WHERE numero = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, numeroMesa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int numero = rs.getInt("numero");
                int capacidade = rs.getInt("capacidade");
                String situacao = rs.getString("situacao");
                int idGarcom = rs.getInt("idGarcom");

                Mesa mesaEncontrada = new Mesa(numero, capacidade, situacao, idGarcom);
                System.out.println("Mesa encontrada:");
                System.out.println(mesaEncontrada.toString());
            } else {
                System.out.println("Não há nenhuma mesa cadastrada com esse número.");
            }
            stmt.close();
        } catch (Exception e) {
            System.out.println("Não foi possível buscar a mesa.");
            e.printStackTrace();
        }
    }
}