package gui.MenuLogin.MenuPrincipal.MenuRestaurante;

import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.frmMenuBebidas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.frmMenuComidas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.frmPostres;
import gui.MenuLogin.sub.frmSubmenu;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMenuRestaurante {
    private JPanel jpaPrincipal;
    private JButton btnComidas;
    private JButton btnBebidas;
    private JButton btnPostres;
    public JPanel jpaRestaurante;
    private JLabel lvlimagenComida;
    private JLabel lvlImagenBebidas;
    private JLabel lvlPostre;
    private JButton btnAtras;
    private JButton btnMesas;
    public static JFrame frameRestaurante = new JFrame("Menu Restaurante");
    public static JFrame frameComidas = new JFrame("Menu Comidas");
    public static JFrame frameBebidas = new JFrame("Menu Bebidas");
    public static JFrame framePostres = new JFrame("Menu Postres");
    public static JFrame frameMesas = new JFrame("Menu Mesas");
    public frmMenuRestaurante() {
        btnComidas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameComidas.setContentPane(new frmMenuComidas().jpaComidas);
                frameComidas.setResizable(false);
                frameComidas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameComidas.pack();
                frameComidas.setLocationRelativeTo(null);
                frameComidas.setVisible(true);
            }
        });
        btnBebidas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameBebidas.setContentPane(new frmMenuBebidas().jpaBebidas);
                frameBebidas.setResizable(false);
                frameBebidas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameBebidas.pack();
                frameBebidas.setLocationRelativeTo(null);
                frameBebidas.setVisible(true);
            }
        });
        btnPostres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePostres.setContentPane(new frmPostres().jpaPostres);
                framePostres.setResizable(false);
                framePostres.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                framePostres.pack();
                framePostres.setLocationRelativeTo(null);
                framePostres.setVisible(true);
            }
        });
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmSubmenu.frameRestaurante.dispose();
            }
        });
        btnMesas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMesas.setContentPane(new frmMesas().jpaMesas);
                frameMesas.setResizable(false);
                frameMesas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameMesas.pack();
                frameMesas.setLocationRelativeTo(null);
                frameMesas.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        frameRestaurante.setContentPane(new frmMenuRestaurante().jpaRestaurante);
        frameRestaurante.setResizable(false);
        frameRestaurante.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameRestaurante.pack();
        frameRestaurante.setLocationRelativeTo(null);
        frameRestaurante.setVisible(true);
    }
}
