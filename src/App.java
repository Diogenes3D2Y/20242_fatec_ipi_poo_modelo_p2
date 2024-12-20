import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        var menu = "1- Cadastrar\n2- Listar\n3- Atualizar\n4- Apagar\n5- Listar por inicial\n0- Sair\n";
        int op = 0;
        do { 
            op = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch(op) {
                case 1: {
                    var nome = JOptionPane.showInputDialog("Nome?");
                    var fone = JOptionPane.showInputDialog("Fone?");
                    var email = JOptionPane.showInputDialog("Email?");
                    var p = new Pessoa(0, nome, fone, email);
                    var dao = new PessoaDAO();
                    dao.cadastrar(p);
                    JOptionPane.showMessageDialog(null, "Cadastro OK!");
                    break;
                }
                case 2: {
                    var pessoas = new PessoaDAO().listar();
                    StringBuilder sb = new StringBuilder();
                    for (var pessoa : pessoas) {
                        sb.append(pessoa).append("\n");  // Aqui chamamos toString() para formatar os dados
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
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
                    var codigo = Integer.parseInt(JOptionPane.showInputDialog("Código?"));
                    var p = new Pessoa(codigo);
                    var dao = new PessoaDAO();
                    dao.apagar(p);
                    JOptionPane.showMessageDialog(null, "Apagado!");
                    break;
                }
                case 5: {
                    var letra = JOptionPane.showInputDialog("Informe a letra inicial:");
                    var dao = new PessoaDAO();
                    var pessoas = dao.listarPorLetra(letra);  // Agora utilizamos o método otimizado para listar por letra

                    if (pessoas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhuma pessoa encontrada com a inicial '" + letra + "'.");
                    } else {
                        StringBuilder resultado = new StringBuilder();
                        for (var pessoa : pessoas) {
                            resultado.append(pessoa).append("\n");  // Novamente, chamamos toString()
                        }
                        JOptionPane.showMessageDialog(null, resultado.toString());
                    }
                    break;
                }
                case 0: {
                    JOptionPane.showMessageDialog(null, "Até!");
                    break;
                }
            }
        } while (op != 0);
    }
}
