package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu;

import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuEnsaladas.frmMenuEnsaladas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuHambuguesa.frmMenuHamburguesas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuPastas.frmMenuPastas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuPizzas.frmMenuPizzas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuSopas.frmMenuSopas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.frmMenuRestaurante;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMenuComidas {
    private JPanel jpaPrincipal;
    public JPanel jpaComidas;
    private JButton btnHamburguesas;
    private JButton btnPizzas;
    private JButton btnPastas;
    private JButton btnSopas;
    private JButton btnEnsaladas;
    private JButton btnAtras;
    private JLabel lvlImgPizza;
    private JLabel lvlImgPastas;
    private JLabel lvlImgSopas;
    private JLabel lvlImgHamburguesa;
    private JLabel lvlImgSalad;
    public static JFrame frameComidas = new JFrame("Menu Comidas");
    public static JFrame frameHamburguesas = new JFrame("Menu Hamburguesas");
    public static JFrame frameEnsaladas = new JFrame("Menu Ensaladas");
    public static JFrame framePizzas = new JFrame("Menu Pizzas");
    public static JFrame framePastas = new JFrame("Menu Pastas");
    public static JFrame frameSopas = new JFrame("Menu Sopas");
    public frmMenuComidas() {
        btnHamburguesas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameHamburguesas.setContentPane(new frmMenuHamburguesas().jpaMenuHamburguesas);
                frameHamburguesas.setResizable(false);
                frameHamburguesas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameHamburguesas.pack();
                frameHamburguesas.setLocationRelativeTo(null);
                frameHamburguesas.setVisible(true);
            }
        });
        btnEnsaladas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameEnsaladas.setContentPane(new frmMenuEnsaladas().jpaEnsaladas);
                frameEnsaladas.setResizable(false);
                frameEnsaladas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameEnsaladas.pack();
                frameEnsaladas.setLocationRelativeTo(null);
                frameEnsaladas.setVisible(true);
            }
        });
        btnPizzas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePizzas.setContentPane(new frmMenuPizzas().jpaMenuPizzas);
                framePizzas.setResizable(false);
                framePizzas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                framePizzas.pack();
                framePizzas.setLocationRelativeTo(null);
                framePizzas.setVisible(true);
            }
        });
        btnPastas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePastas.setContentPane(new frmMenuPastas().jpaMenuPastas);
                framePastas.setResizable(false);
                framePastas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                framePastas.pack();
                framePastas.setLocationRelativeTo(null);
                framePastas.setVisible(true);
            }
        });
        btnSopas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameSopas.setContentPane(new frmMenuSopas().jpaSopas);
                frameSopas.setResizable(false);
                frameSopas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameSopas.pack();
                frameSopas.setLocationRelativeTo(null);
                frameSopas.setVisible(true);
            }
        });
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuRestaurante.frameComidas.dispose();
            }
        });
    }

    public static void main(String[] args) {
        frameComidas.setContentPane(new frmMenuComidas().jpaComidas);
        frameComidas.setResizable(false);
        frameComidas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameComidas.pack();
        frameComidas.setLocationRelativeTo(null);
        frameComidas.setVisible(true);
    }
}
