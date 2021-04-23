package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuPastas;

import com.google.gson.Gson;
import gui.Carrito.frmCarrito;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuHambuguesa.frmMenuHamburguesas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.frmMenuComidas;
import gui.orden.frmOrden;
import recursos.clases.DetalleFactura;
import recursos.clases.DetalleOrden;
import recursos.clases.Orden;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMenuPastas {
    private JPanel jpaPrincipal;
    public JPanel jpaMenuPastas;
    private JPanel jpaSuperior;
    private JButton btnAñadir;
    private JButton btnInformacion;
    private JButton btnVerOrden;
    private JButton btnTerminarCompra;
    private JButton btnAtras;
    private JPanel jpaIzquierdo;
    private JRadioButton rbtSpaguetti;
    private JRadioButton rbtLassagna;
    private JRadioButton rbtCanelones;
    private JRadioButton rbtTornillos;
    private JRadioButton rbtCoditos;
    private JTextArea txaLista;
    public static JFrame framePastas = new JFrame("Menu Pastas");
    public static JFrame frameCarrito = new JFrame("Carrito");
    public static JFrame frameOrden = new JFrame("Ordenes");
    public int contador = 1;
    public frmMenuPastas() {
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuComidas.framePastas.dispose();
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
                if (rbtSpaguetti.isSelected()){
                    Nombre = "Spaguetti";
                    Codigo = "01";
                    Precio = "100 Lps.";

                    Descripcion = "Nombre: Spaguetti\n Codigo:01\n Precio:100Lps\n Descripcion: Con albóndigas o pequeñas tortas de carne" ;
                }
                if (rbtLassagna.isSelected()){
                    Nombre = "Lassagna";
                    Codigo = "02";
                    Precio = "120 Lps.";

                    Descripcion = "Nombre: Lassagna\n Codigo:02\n Precio:120Lps\n Descripcion: 4 capas" ;
                }
                if (rbtCanelones.isSelected()){
                    Nombre = "Canelones";
                    Codigo = "03";
                    Precio = "130 Lps.";

                    Descripcion = "Nombre: Canelones\n Codigo:03\n Precio:130 Lps\n Descripcion: Rellenos de la carne de su preferencia" ;
                }
                if (rbtTornillos.isSelected()){
                    Nombre = "Tornillos";
                    Codigo = "04";
                    Precio = "100 Lps.";

                    Descripcion = "Nombre: Tornillos\n Codigo:04\n Precio:100Lps\n Descripcion: En salsa blanca con carne de su preferencia" ;
                }
                if (rbtCoditos.isSelected()){
                    Nombre = "Coditos";
                    Codigo = "05";
                    Precio = "90 Lps.";

                    Descripcion = "Nombre: Coditos\n Codigo:05\n Precio:90Lps\n Descripcion: Fríos, acompañados de vegetales y atún" ;
                }
                txaLista.setText(Descripcion);
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
                    if (rbtSpaguetti.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Spaguetti");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(1);
                        DetalleFactura(1);
                    }
                    if (rbtLassagna.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Canelones");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(2);
                        DetalleFactura(2);
                    }
                    if (rbtCanelones.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Canelones");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(3);
                        DetalleFactura(3);
                    }
                    if (rbtTornillos.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Tornillos");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(4);
                        DetalleFactura(4);
                    }
                    if (rbtCoditos.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Coditos");
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
            detalleOrden.setProductoid(60);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(100);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==2){
            detalleOrden.setProductoid(61);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(120);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==3){
            detalleOrden.setProductoid(62);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(130);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==4){
            detalleOrden.setProductoid(63);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(100);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==5){
            detalleOrden.setProductoid(64);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(90);
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
            detalleFactura.setProductoid(60);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(100.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==2){
            detalleFactura.setProductoid(61);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(120.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==3){
            detalleFactura.setProductoid(62);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(130.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==4){
            detalleFactura.setProductoid(63);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(100.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==5){
            detalleFactura.setProductoid(64);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(90.00);
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
        framePastas.setContentPane(new frmMenuPastas().jpaMenuPastas);
        framePastas.setResizable(false);
        framePastas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePastas.pack();
        framePastas.setLocationRelativeTo(null);
        framePastas.setVisible(true);
    }
}
