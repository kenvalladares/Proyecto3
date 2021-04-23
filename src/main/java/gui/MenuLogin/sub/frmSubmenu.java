package gui.MenuLogin.sub;

import gui.MenuLogin.MenuPrincipal.Clientes.frmClientes;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.frmMenuRestaurante;
import gui.MenuLogin.frmMenuLogin;
import gui.DetalleFactura.frmDetalleFacturas;
import gui.Mesas.frmMesas;
import gui.empleados.frmEmpleados;
import gui.producto.frmProducto;
import gui.proveedores.frmProveedores;
import gui.usuarios.frmUsuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmSubmenu {
    public JPanel jpaPrincipal;
    private JPanel jpaSubmenu;
    private JButton btnRestaurante;
    private JButton btnClientes;
    private JButton btnFacturas;
    private JButton btnAtras;
    private JLabel lvlImgRestaurant;
    private JLabel lvlFacturas;
    private JLabel lvlImgClientes;
    private JButton btnEmpleados;
    private JButton btnUsuarios;
    private JButton btnProveedores;
    private JButton btnProducto;
    private JButton btnMesas;
    private JLabel lvlImgproveedor;
    private JLabel lvlImgProducto;
    private JLabel lvlEmpleados;
    private JLabel lvlUsuarios;
    public static JFrame frameSubmenu = new JFrame("Menu Principal");
    public static JFrame frameMenuClientes = new JFrame("Menu Clientes");
    public static JFrame frameRestaurante = new JFrame("Menu Restaurante");
    public static JFrame frameDetalleFactura = new JFrame("Detalles Facturas");
    public static JFrame frameMenuProveedores = new JFrame("Menu Proveedores");
    public static JFrame frameMenuProducto = new JFrame("Menu Productos");
    public static JFrame frmMenuEmpleados = new JFrame("Menu Empleados");
    public static JFrame frmMenuMesas = new JFrame("Menu Mesas");
    public static JFrame frmUsuarios = new JFrame("Menu Usuarios");

    public frmSubmenu() {
        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMenuClientes.setContentPane(new frmClientes().jpaClientes);
                frameMenuClientes.setResizable(false);
                frameMenuClientes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameMenuClientes.pack();
                frameMenuClientes.setLocationRelativeTo(null);
                frameMenuClientes.setVisible(true);
            }
        });
        btnRestaurante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameRestaurante.setContentPane(new frmMenuRestaurante().jpaRestaurante);
                frameRestaurante.setResizable(false);
                frameRestaurante.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameRestaurante.pack();
                frameRestaurante.setLocationRelativeTo(null);
                frameRestaurante.setVisible(true);
            }
        });
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuLogin.frameSubmenu.dispose();
            }
        });
        btnFacturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameDetalleFactura.setContentPane(new frmDetalleFacturas().jpaPrincipal);
                frameDetalleFactura.setResizable(false);
                frameDetalleFactura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameDetalleFactura.pack();
                frameDetalleFactura.setLocationRelativeTo(null);
                frameDetalleFactura.setVisible(true);
            }
        });
        btnProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMenuProveedores.setContentPane(new frmProveedores().jpaProveedores);
                frameMenuProveedores.setResizable(false);
                frameMenuProveedores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameMenuProveedores.pack();
                frameMenuProveedores.setLocationRelativeTo(null);
                frameMenuProveedores.setVisible(true);
            }
        });
        btnProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMenuProducto.setContentPane(new frmProducto().jpaPrincipal);
                frameMenuProducto.setResizable(false);
                frameMenuProducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameMenuProducto.pack();
                frameMenuProducto.setLocationRelativeTo(null);
                frameMenuProducto.setVisible(true);
            }
        });
        btnEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuEmpleados.setContentPane(new frmEmpleados().jpaPrincipal);
                frmMenuEmpleados.setResizable(false);
                frmMenuEmpleados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmMenuEmpleados.pack();
                frmMenuEmpleados.setLocationRelativeTo(null);
                frmMenuEmpleados.setVisible(true);
            }
        });
        btnMesas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuMesas.setContentPane(new frmMesas().jpaMesas);
                frmMenuMesas.setResizable(false);
                frmMenuMesas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmMenuMesas.pack();
                frmMenuMesas.setLocationRelativeTo(null);
                frmMenuMesas.setVisible(true);
            }
        });
        btnUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmUsuarios.setContentPane(new frmUsuarios().jpaPrincipal);
                frmUsuarios.setResizable(false);
                frmUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmUsuarios.pack();
                frmUsuarios.setLocationRelativeTo(null);
                frmUsuarios.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        frameSubmenu.setContentPane(new frmSubmenu().jpaPrincipal);
        frameSubmenu.setResizable(false);
        frameSubmenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameSubmenu.pack();
        frameSubmenu.setLocationRelativeTo(null);
        frameSubmenu.setVisible(true);
    }
}
