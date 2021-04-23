package gui.MenuLogin;
import gui.MenuLogin.sub.Jar.datos.usuarios.UsuariosDatos;
import gui.MenuLogin.Registro.frmRegistro;
import gui.MenuLogin.sub.frmSubmenu;
import recursos.clases.Hash;
import recursos.clases.Usuarios;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMenuLogin {
    public JPanel jpaPrincipal;
    public JPanel jpaMenuLogin;
    private JLabel lvlUsuario;
    private JLabel lvlContraseña;
    private JPasswordField txtPassword;
    private JTextField txtUsuario;
    private JButton btnLogin;
    private JButton btnRegistro;
    public static JFrame frameMenuLogin = new JFrame("Menu Inicio de sesion");
    public static JFrame frameSubmenu = new JFrame("Menu Principal");
    public static JFrame frameMenuRegistro = new JFrame("Menu Registro");

    public frmMenuLogin() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuariosDatos modSql = new UsuariosDatos();
                Usuarios mod = new Usuarios();
                String pass = new String(txtPassword.getPassword());
                if(!txtUsuario.getText().equals("") && !pass.equals("")){
                    String nuevoPass = Hash.sha1(pass);
                    mod.setUsuario(txtUsuario.getText());
                    mod.setPassword(nuevoPass);
                    if(modSql.Login(mod)){
                        frameSubmenu.setContentPane(new frmSubmenu().jpaPrincipal);
                        frameSubmenu.setResizable(false);
                        frameSubmenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frameSubmenu.pack();
                        frameSubmenu.setLocationRelativeTo(null);
                        frameSubmenu.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Usuario o Nombre de Contraseña Incorrecto");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Debe ingresar sus datos");
                }

            }
        });
        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMenuRegistro.setContentPane(new frmRegistro().jpaPrincipal);
                frameMenuRegistro.setResizable(false);
                frameMenuRegistro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameMenuRegistro.pack();
                frameMenuRegistro.setLocationRelativeTo(null);
                frameMenuRegistro.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        frameMenuLogin.setContentPane(new frmMenuLogin().jpaMenuLogin);
        frameMenuLogin.setResizable(false);
        frameMenuLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMenuLogin.pack();
        frameMenuLogin.setLocationRelativeTo(null);
        frameMenuLogin.setVisible(true);
    }
}
