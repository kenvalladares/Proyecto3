package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu;

import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosNaturales.frmRefrescosNaturales;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosSoda.frmMenuRefrescosSoda;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.frmMenuComidas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.frmMenuRestaurante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMenuBebidas {
    private JPanel jpaPrincipal;
    public JPanel jpaBebidas;
    private JButton btnRefrescosNaturales;
    private JButton btnRefrescosSoda;
    private JButton btnAtras;
    private JLabel lvlImgRefrescoN;
    private JLabel lvlImagenRefrescoS;
    public static JFrame frameBebidas = new JFrame("Menu Bebidas");
    public static JFrame frameRefrescosNaturales = new JFrame("Menu Refrescos Naturales");
    public static JFrame frameRefrescosSoda = new JFrame("Menu Refrescos de Soda");
    public frmMenuBebidas() {
        btnRefrescosNaturales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameRefrescosNaturales.setContentPane(new frmRefrescosNaturales().jpaMenuRefrescosNaturales);
                frameRefrescosNaturales.setResizable(false);
                frameRefrescosNaturales.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameRefrescosNaturales.pack();
                frameRefrescosNaturales.setLocationRelativeTo(null);
                frameRefrescosNaturales.setVisible(true);
            }
        });
        btnRefrescosSoda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameRefrescosSoda.setContentPane(new frmMenuRefrescosSoda().jpaRefrescosSoda);
                frameRefrescosSoda.setResizable(false);
                frameRefrescosSoda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameRefrescosSoda.pack();
                frameRefrescosSoda.setLocationRelativeTo(null);
                frameRefrescosSoda.setVisible(true);
            }
        });
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuRestaurante.frameBebidas.dispose();
            }
        });
    }

    public static void main(String[] args) {
        frameBebidas.setContentPane(new frmMenuBebidas().jpaBebidas);
        frameBebidas.setResizable(false);
        frameBebidas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameBebidas.pack();
        frameBebidas.setLocationRelativeTo(null);
        frameBebidas.setVisible(true);
    }
}
