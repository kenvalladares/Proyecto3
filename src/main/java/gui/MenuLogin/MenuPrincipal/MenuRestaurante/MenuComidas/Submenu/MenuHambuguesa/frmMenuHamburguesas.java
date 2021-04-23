package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuHambuguesa;

import com.google.gson.Gson;
import gui.Carrito.frmCarrito;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.frmMenuComidas;
import gui.orden.frmOrden;
import recursos.clases.DetalleFactura;
import recursos.clases.DetalleOrden;
import recursos.clases.Mesas;
import recursos.clases.Orden;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMenuHamburguesas {
    private JPanel jpaPrincipal;
    public JPanel jpaMenuHamburguesas;
    private JPanel jpaSuperior;
    private JButton btnAñadir;
    private JButton btnInformacion;
    private JButton verCa;
    private JButton btnAtras;
    private JButton btnTerminarCompra;
    private JPanel jpaIzquierdo;
    private JRadioButton rbtSencilla;
    private JRadioButton rbtDoble;
    private JRadioButton rbtSuprema;
    private JRadioButton rbtMarina;
    private JRadioButton rbtPollo;
    private JTextArea txaLista;
    private JButton btnVerOrden;
    public int contador =1;
    public static JFrame frameHamburguesas = new JFrame("Menu Hamburguesas");
    public static JFrame frameCarrito = new JFrame("Carrito");
    public static JFrame frameTablaHamburguesas = new JFrame("Hamburguesas");
    public static JFrame frameOrden = new JFrame("Ordenes");
    DefaultTableModel modelo;
    public frmMenuHamburguesas() {
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuComidas.frameHamburguesas.dispose();
            }
        });

        btnAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();
                    WebTarget target = client.target(URLORDEN + "/addOrden");
                    Invocation.Builder solicitud =target.request();
                    Orden orden = new Orden();
                    if (rbtSencilla.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion(" Hamburguesa Sencilla");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(1);
                        DetalleFactura(1);
                    }
                    if (rbtDoble.isSelected()){

                        orden.setCliente(1);
                        orden.setDescripcion(" Hamburguesa Doble");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(2);
                        DetalleFactura(2);
                    }
                    if (rbtSuprema.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion(" Hamburguesa Suprema");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(3);
                        DetalleFactura(3);
                    }
                    if (rbtMarina.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion(" Hamburguesa Marina");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(4);
                        DetalleFactura(4);
                    }
                    if (rbtPollo.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion(" Hamburguesa Pollo");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(5);
                        DetalleFactura(5);
                    }
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(orden);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    Orden data = new Gson().fromJson(responseJson, Orden.class);
                    JOptionPane.showMessageDialog(null,post.getStatus());
                    switch (post.getStatus()) {
                        case 201:
                            res = "Orden añadida Exitosamente";
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                    contador++;
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }
            }
        });
        btnTerminarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameCarrito.setContentPane(new frmCarrito().jpaPrincipal);
                frameCarrito.setResizable(false);
                frameCarrito.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameCarrito.pack();
                frameCarrito.setLocationRelativeTo(null);
                frameCarrito.setVisible(true);
            }
        });
        btnInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Nombre = "";
                String Codigo = "";
                String Precio = "";
                String Descripcion ="";
                if (rbtSencilla.isSelected()){
                    Nombre = "Sencilla";
                    Codigo = "01";
                    Precio = "150 Lps.";

                    Descripcion = "Nombre: Sencilla\n Codigo:01\n Precio:150Lps\n Descripcion: Carne de Res, queso cheddar, vegetales frescos" ;
                }
                if (rbtDoble.isSelected()){
                    Nombre = "Doble";
                    Codigo = "02";
                    Precio = "250 Lps.";

                    Descripcion = "Nombre: Doble\n Codigo:02\n Precio:250Lps\n Descripcion: Doble carne, queso cheddar, cebolla, vegetales" ;
                }
                if (rbtSuprema.isSelected()){
                    Nombre = "Suprema";
                    Codigo = "03";
                    Precio = "155 Lps.";

                    Descripcion = "Nombre: Suprema\n Codigo:03\n Precio:155Lps\n Descripcion: Doble carne de res, Bacon, demás vegetales" ;
                }
                if (rbtMarina.isSelected()){
                    Nombre = "Marina";
                    Codigo = "04";
                    Precio = "170 Lps.";

                    Descripcion = "Nombre: Marina\n Codigo:04\n Precio:170Lps\n Descripcion: Carne de pescado, con vegetales frescos" ;
                }
                if (rbtPollo.isSelected()){
                    Nombre = "Pollo";
                    Codigo = "05";
                    Precio = "160 Lps.";

                    Descripcion = "Nombre: Pollo\n Codigo:05\n Precio:160Lps\n Descripcion: Carne de Pollo, vegetales, aderezo ranch o barbacoa " ;
                }
                txaLista.setText(Descripcion);
            }
        });

        btnVerOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameOrden.setContentPane(new frmOrden().jpaPrincipal);
                frameOrden.setResizable(false);
                frameOrden.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameOrden.pack();
                frameOrden.setLocationRelativeTo(null);
                frameOrden.setVisible(true);
            }
        });
    }

    public void Detalleordenes(int numero){
        Client client= ClientBuilder.newClient();
        WebTarget target = client.target(URLDETALLEORDEN + "/addDetalleOrden");
        Invocation.Builder solicitud =target.request();
        DetalleOrden detalleOrden = new DetalleOrden();
        if (numero ==1){
            detalleOrden.setProductoid(5);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(150);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==2){
            detalleOrden.setProductoid(9);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(180);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==3){
            detalleOrden.setProductoid(10);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(250);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==4){
            detalleOrden.setProductoid(16);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(170);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==5){
            detalleOrden.setProductoid(12);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(160);
            detalleOrden.setNumeroOrden(contador);
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(detalleOrden);
        Response post = solicitud.post(Entity.json(jsonString));
        String responseJson = post.readEntity(String.class);
        DetalleOrden data = new Gson().fromJson(responseJson, DetalleOrden.class);

    }
    public void DetalleFactura(int numero){
        Client client= ClientBuilder.newClient();
        WebTarget target = client.target(URLDETALLEFACTURA + "/addDetalleFactura");
        Invocation.Builder solicitud =target.request();
        DetalleFactura detalleFactura = new DetalleFactura();
        if (numero ==1){
            detalleFactura.setProductoid(5);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(150.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==2){
            detalleFactura.setProductoid(9);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(180.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==3){
            detalleFactura.setProductoid(10);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(250.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==4){
            detalleFactura.setProductoid(16);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(170.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==5){
            detalleFactura.setProductoid(12);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(160.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(detalleFactura);
        Response post = solicitud.post(Entity.json(jsonString));
        String responseJson = post.readEntity(String.class);
        DetalleFactura data = new Gson().fromJson(responseJson, DetalleFactura.class);

    }
    String res = "";
    String URLORDEN = "http://192.168.0.6:8080/api/v1/Orden";
    String URLDETALLEORDEN = "http://192.168.0.6:8080/api/v1/DetalleOrden";
    String URLDETALLEFACTURA = "http://192.168.0.6:8080/api/v1/DetalleFactura";
    String URLFACTURA = "http://192.168.0.6:8080/api/v1/Factura";
    public static void main(String[] args) {
        frameHamburguesas.setContentPane(new frmMenuHamburguesas().jpaMenuHamburguesas);
        frameHamburguesas.setResizable(false);
        frameHamburguesas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameHamburguesas.pack();
        frameHamburguesas.setLocationRelativeTo(null);
        frameHamburguesas.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
