import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class PessoaDAO {
    private List<Pessoa> listaDePessoas;

    // Construtor que inicializa a lista em memória (pode ser útil para cache, mas o foco é o banco de dados)
    public PessoaDAO() {
        this.listaDePessoas = new ArrayList<>();
    }

    // Método para cadastrar uma nova pessoa no banco de dados
    public void cadastrar(Pessoa p) throws SQLException {
        var sql = "INSERT INTO tb_pessoa(nome, fone, email) VALUES (?, ?, ?)";
        try (var conexao = ConnectionFactory.conectar();
             var ps = conexao.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getFone());
            ps.setString(3, p.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar pessoa: " + e.getMessage(), e);
        }
    }

    // Método para apagar uma pessoa do banco de dados
    public void apagar(Pessoa p) throws SQLException {
        var sql = "DELETE FROM tb_pessoa WHERE cod_pessoa = ?";
        try (var conexao = ConnectionFactory.conectar();
             var ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, p.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao apagar pessoa: " + e.getMessage(), e);
        }
    }

    // Método para listar todas as pessoas do banco de dados
    public List<Pessoa> listar() throws SQLException {
        var pessoas = new ArrayList<Pessoa>();
        var sql = "SELECT * FROM tb_pessoa";
        try (var conexao = ConnectionFactory.conectar();
             var ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                var codigo = rs.getInt("cod_pessoa");
                var nome = rs.getString("nome");
                var fone = rs.getString("fone");
                var email = rs.getString("email");
                var p = new Pessoa(codigo, nome, fone, email);
                pessoas.add(p);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar pessoas: " + e.getMessage(), e);
        }
        return pessoas;
    }

    // Método para atualizar os dados de uma pessoa no banco de dados
    public void atualizar(Pessoa p) throws SQLException {
        var sql = "UPDATE tb_pessoa SET nome = ?, fone = ?, email = ? WHERE cod_pessoa = ?";
        try (var conexao = ConnectionFactory.conectar();
             var ps = conexao.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getFone());
            ps.setString(3, p.getEmail());
            ps.setInt(4, p.getCodigo());

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Atualização realizada com sucesso!");
            } else {
                System.out.println("Pessoa não encontrada.");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar pessoa: " + e.getMessage(), e);
        }
    }

    // Método para listar todas as pessoas cujo nome começa com a letra fornecida
    public List<Pessoa> listarPorLetra(String letra) throws SQLException {
        var pessoas = new ArrayList<Pessoa>();
        var sql = "SELECT * FROM tb_pessoa WHERE nome LIKE ?";
        try (var conexao = ConnectionFactory.conectar();
             var ps = conexao.prepareStatement(sql)) {

            ps.setString(1, letra + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                var codigo = rs.getInt("cod_pessoa");
                var nome = rs.getString("nome");
                var fone = rs.getString("fone");
                var email = rs.getString("email");
                var p = new Pessoa(codigo, nome, fone, email);
                pessoas.add(p);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar pessoas por letra: " + e.getMessage(), e);
        }
        return pessoas;
    }
}
