package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres;

import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.frmMenuComidas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuHelados.frmMenuHelados;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuPastel.frmMenuPastel;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuPie.frmMenuPie;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.frmMenuRestaurante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPostres {
    private JPanel jpaPrincipal;
    public JPanel jpaPostres;
    private JButton btnPastel;
    private JButton btnHelado;
    private JButton btnPie;
    private JButton btnAtras;
    private JLabel lvlImagenPastel;
    private JLabel lvlImgHelado;
    private JLabel lvlImgPie;
    public static JFrame framePostres = new JFrame("Menu Postres");
    public static JFrame frameHelados = new JFrame("Menu de Helados");
    public static JFrame framePastel = new JFrame("Menu de Pasteles");
    public static JFrame framePie = new JFrame("Menu de Pies");
    public frmPostres() {
        btnHelado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameHelados.setContentPane(new frmMenuHelados().jpaMenuHelados);
                frameHelados.setResizable(false);
                frameHelados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameHelados.pack();
                frameHelados.setLocationRelativeTo(null);
                frameHelados.setVisible(true);
            }
        });
        btnPastel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePastel.setContentPane(new frmMenuPastel().jpaMenuPastel);
                framePastel.setResizable(false);
                framePastel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                framePastel.pack();
                framePastel.setLocationRelativeTo(null);
                framePastel.setVisible(true);
            }
        });
        btnPie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                framePie.setContentPane(new frmMenuPie().jpaMenuPie);
                framePie.setResizable(false);
                framePie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                framePie.pack();
                framePie.setLocationRelativeTo(null);
                framePie.setVisible(true);
            }
        });
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuRestaurante.framePostres.dispose();
            }
        });
    }

    public static void main(String[] args) {
        framePostres.setContentPane(new frmPostres().jpaPostres);
        framePostres.setResizable(false);
        framePostres.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePostres.pack();
        framePostres.setLocationRelativeTo(null);
        framePostres.setVisible(true);
    }
}
