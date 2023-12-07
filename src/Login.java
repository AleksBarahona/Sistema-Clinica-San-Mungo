import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JPanel miPanel;
    private JTextField txtUsuario;
    private JPasswordField txtContra;
    private JButton btnLogin;
    private JButton salirButton;

    public Login() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //hardcodear
                String contra = String.valueOf(txtContra.getPassword());
                if(txtUsuario.getText().equals("admin") && contra.equals("1234")){
                    //Bienvenida al sistema
                    JOptionPane.showMessageDialog(miPanel, "Bienvenido admin");
                    String[] tipoUsuario={"admin"};
                    VentanaSistema.main(tipoUsuario);
                    dispose();
                }else if (txtUsuario.getText().equals("usuario") && contra.equals("usuario")){
                    //si es el usuario
                    JOptionPane.showMessageDialog(miPanel, "Bienvenido Usuario");
                    String[] tipoUsuario={"user"};
                    VentanaSistema.main(tipoUsuario);
                    dispose();
                }else{
                    //usuario o contra no validas
                    JOptionPane.showMessageDialog(miPanel, "Usuario o contraseña incorrectos", "Login",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        Login vLogin = new Login();
        vLogin.setContentPane(vLogin.miPanel);
        vLogin.setSize(300, 300);
        vLogin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        vLogin.setVisible(true);
        vLogin.setLocationRelativeTo(null);
    }
}
