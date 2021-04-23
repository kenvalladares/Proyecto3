package gui.MenuLogin.Registro;
import gui.MenuLogin.sub.Jar.datos.usuarios.UsuariosDatos;
import gui.MenuLogin.frmMenuLogin;
import recursos.clases.Hash;
import recursos.clases.Usuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmRegistro {
    public JPanel jpaPrincipal;
    private JPanel jpaRegistro;
    private JPanel jpaIzquierdo;
    private JPanel jpaDerecho;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmaPassword;
    private JPanel jpaInferior;
    private JLabel lvlUsuario;
    private JLabel lvlContraseña;
    private JLabel lvlConfirmarContraseña;
    private JButton registrarButton;
    private JLabel lvlNombre;
    private JLabel lvlCorreo;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JButton btnAtras;
    public static JFrame frameMenuRegistro = new JFrame("Menu Registro");

    public frmRegistro() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuariosDatos modSql= new UsuariosDatos();
                Usuarios mod = new Usuarios();
                String pass = new String(txtPassword.getPassword());
                String passCon = new String(txtConfirmaPassword.getPassword());
                if(txtUsuario.getText().equals("")|| pass.equals("")|| passCon.equals("")|| txtNombre.getText().equals("")
                || txtCorreo.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Error:Hay campos vacios, debe llenar todos los campos");
                }else {


                if(pass.equals(passCon)){
                    if(modSql.ExisteUsuario(txtUsuario.getText())==0){
                        if(modSql.Email(txtCorreo.getText())){
                    String nuevoPass = Hash.sha1(pass);
                    mod.setUsuario(txtUsuario.getText());
                    mod.setPassword(nuevoPass);
                    mod.setNombre(txtNombre.getText());
                    mod.setCorreo(txtCorreo.getText());
                    if(modSql.registrar(mod)){
                        JOptionPane.showMessageDialog(null,"Registro Guardado");
                        limpiar();
                    }
                else{
                    JOptionPane.showMessageDialog(null,"Error al guardar");
                }
                        }else{
                            JOptionPane.showMessageDialog(null,"El Correo Electronico no es Valido");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:El Usuario ya Existe");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Error:Las contraseñas no coinciden");
                }
            }
            }
        });
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuLogin.frameMenuRegistro.dispose();
            }
        });
    }
private void limpiar(){
        txtUsuario.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtPassword.setText("");
        txtConfirmaPassword.setText("");
}
    public static void main(String[] args) {
        frameMenuRegistro.setContentPane(new frmRegistro().jpaPrincipal);
        frameMenuRegistro.setResizable(false);
        frameMenuRegistro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMenuRegistro.pack();
        frameMenuRegistro.setLocationRelativeTo(null);
        frameMenuRegistro.setVisible(true);
    }
}
