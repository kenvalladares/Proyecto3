package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuHelados;

import com.google.gson.Gson;
import gui.Carrito.frmCarrito;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosSoda.frmMenuRefrescosSoda;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.frmPostres;
import gui.orden.frmOrden;
import recursos.clases.DetalleFactura;
import recursos.clases.DetalleOrden;
import recursos.clases.Orden;
import sun.security.krb5.internal.crypto.Des;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMenuHelados {
    private JPanel jpaPrincipal;
    public JPanel jpaMenuHelados;
    private JPanel jpaSuperior;
    private JButton btnAñadir;
    private JButton btnInformacion;
    private JButton btnVerOrden;
    private JButton btnTerminarCompra;
    private JButton btnAtras;
    private JPanel jpaIzquierdo;
    private JRadioButton rbtPistacho;
    private JRadioButton rbtVainilla;
    private JRadioButton rbtChocolate;
    private JRadioButton rbtFresa;
    private JRadioButton rbtCombinado;
    private JTextArea txaLista;
    private int contador = 1;
    public static JFrame frameHelados = new JFrame("Menu de Helados");
    public static JFrame frameCarrito = new JFrame("Carrito");
    public static JFrame frameOrden = new JFrame("Ordenes");
    public frmMenuHelados() {
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPostres.frameHelados.dispose();
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
                if (rbtPistacho.isSelected()){
                    Nombre = "Helado de Pistacho";
                    Codigo = "01";
                    Precio = "25 Lps.";

                    Descripcion = "Nombre: Helado de Pistacho\n Codigo:01\n Precio:25Lps\n Descripcion: Tazón de helado de Pistacho" ;
                }
                if (rbtVainilla.isSelected()){
                    Nombre = "Helado de Vainilla";
                    Codigo = "02";
                    Precio = "25 Lps.";

                    Descripcion = "Nombre: Helado de Vainilla\n Codigo:02\n Precio:25Lps\n Descripcion: Tazón de helado de Vainilla" ;
                }
                if (rbtChocolate.isSelected()){
                    Nombre = "Helado de Chocolate";
                    Codigo = "03";
                    Precio = "25 Lps.";

                    Descripcion = "Nombre: Helado de Chocolate\n Codigo:03\n Precio:25Lps\n Descripcion: Tazón de helado de Chocolate" ;
                }
                if (rbtFresa.isSelected()){
                    Nombre = "Helado de Fresa";
                    Codigo = "04";
                    Precio = "25 Lps.";

                    Descripcion = "Nombre: Helado de Fresa\n Codigo:04\n Precio:25Lps\n Descripcion: Tazón de helado de Fresa" ;
                }
                if (rbtCombinado.isSelected()){
                    Nombre = "Helado de sabores combinados";
                    Codigo = "05";
                    Precio = "30 Lps.";

                    Descripcion = "Nombre: Helado de sabores combinados\n Codigo:04\n Precio:30Lps\n Descripcion: Helado de sabores combinados" ;
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
                    if (rbtPistacho.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Helado de Pistacho");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(1);
                        DetalleFactura(1);
                    }
                    if (rbtVainilla.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Helado de Vainilla");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(2);
                        DetalleFactura(2);
                    }
                    if (rbtChocolate.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Helado de Chocolate");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(3);
                        DetalleFactura(3);
                    }
                    if (rbtFresa.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Helado de Fresa");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(4);
                        DetalleFactura(4);
                    }
                    if (rbtCombinado.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Helado COmbinado");
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
            detalleOrden.setProductoid(80);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(25);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==2){
            detalleOrden.setProductoid(81);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(25);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==3){
            detalleOrden.setProductoid(82);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(25);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==4){
            detalleOrden.setProductoid(83);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(25);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==5){
            detalleOrden.setProductoid(84);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(30);
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
            detalleFactura.setProductoid(80);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(25.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==2){
            detalleFactura.setProductoid(81);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(25.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==3){
            detalleFactura.setProductoid(82);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(25.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==4){
            detalleFactura.setProductoid(83);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(25.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==5){
            detalleFactura.setProductoid(84);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(30.00);
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
        frameHelados.setContentPane(new frmMenuHelados().jpaMenuHelados);
        frameHelados.setResizable(false);
        frameHelados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameHelados.pack();
        frameHelados.setLocationRelativeTo(null);
        frameHelados.setVisible(true);
    }
}
