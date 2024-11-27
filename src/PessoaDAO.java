import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class PessoaDAO {

    // Construtor que inicializa a lista (caso queira manter em memória)
    public PessoaDAO() {
    }

    // Método para cadastrar uma nova pessoa no banco
    public void cadastrar(Pessoa p) throws SQLException {
        // 1. Especificar o comando SQL (INSERT)
        var sql = "INSERT INTO tb_pessoa(nome, fone, email) VALUES (?, ?, ?)";
        // 2. Estabelecer uma conexão com o banco
        try (var conexao = ConnectionFactory.conectar();
             var ps = conexao.prepareStatement(sql)) {

            // 3. Substituir os eventuais placeholders
            ps.setString(1, p.getNome());
            ps.setString(2, p.getFone());
            ps.setString(3, p.getEmail());

            // 4. Executar o comando
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar pessoa: " + e.getMessage(), e);
        }
    }

    // Método para apagar uma pessoa do banco
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

    // Método para listar todas as pessoas do banco
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

    // Método para atualizar uma pessoa no banco
    public void atualizar(Pessoa pessoa) throws SQLException {
        var sql = "UPDATE tb_pessoa SET nome = ?, fone = ?, email = ? WHERE cod_pessoa = ?";
        try (var conexao = ConnectionFactory.conectar();
             var ps = conexao.prepareStatement(sql)) {

            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getFone());
            ps.setString(3, pessoa.getEmail());
            ps.setInt(4, pessoa.getCodigo());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Pessoa não encontrada para atualização.");
            } else {
                System.out.println("Pessoa atualizada com sucesso!");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar pessoa: " + e.getMessage(), e);
        }
    }
}
