import javax.swing.JOptionPane;

public class App {
  public static void main(String[] args) throws Exception {
    var menu = "1-Cadastrar\n2-Listar\n3-Atualizar\n4-Apagar\n0-Sair\n";
    int op = 0;
    do {
      op = Integer.parseInt(
          JOptionPane.showInputDialog(menu));
      switch (op) {
        case 1: {
          var nome = JOptionPane.showInputDialog("Nome?");
          var fone = JOptionPane.showInputDialog("Fone?");
          var email = JOptionPane.showInputDialog("E-mail?");
          var p = new Pessoa(0, nome, fone, email);
          var dao = new PessoaDAO();
          dao.cadastrar(p);
          JOptionPane.showMessageDialog(null, "Cadastro OK!");
          break;
        }
        case 2: {
          var pessoas = new PessoaDAO().listar();
          JOptionPane.showMessageDialog(null, pessoas);
          break;
        }
        case 3: {
          var codigo = Integer.parseInt(JOptionPane.showInputDialog("Código"));
          var nome = JOptionPane.showInputDialog("Novo nome?");
          var fone = JOptionPane.showInputDialog("Novo fone?");
          var email = JOptionPane.showInputDialog("Novo email?");
          var p = new Pessoa(codigo, nome, fone, email);
          var dao = new PessoaDAO();
          dao.atualizar(p);
          JOptionPane.showMessageDialog(null, "Atualização OK!");
          break;
        }
        case 4: {
          var codigo = Integer.parseInt(
              JOptionPane.showInputDialog("Código?"));
          var p = new Pessoa(codigo);
          var dao = new PessoaDAO();
          dao.apagar(p);
          JOptionPane.showMessageDialog(null, "Apagou!");
          break;
        }
        case 5: {
          // capturar uma letra
          // listar todas as pessoas cujo nome começa com essa letra
        }
        case 0: {
          JOptionPane.showMessageDialog(null, "Até!");
        }
      }
    } while (op != 0);
  }
}
