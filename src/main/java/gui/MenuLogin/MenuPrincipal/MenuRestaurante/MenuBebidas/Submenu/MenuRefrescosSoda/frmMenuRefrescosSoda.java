package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosSoda;

import com.google.gson.Gson;
import gui.Carrito.frmCarrito;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosNaturales.frmRefrescosNaturales;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.frmMenuBebidas;
import gui.orden.frmOrden;
import recursos.clases.DetalleFactura;
import recursos.clases.DetalleOrden;
import recursos.clases.Orden;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMenuRefrescosSoda {
    private JPanel jpaPrincipal;
    public JPanel jpaRefrescosSoda;
    private JPanel jpaSuperior;
    private JButton btnAñadir;
    private JButton btnInformacion;
    private JButton btnVerOrden;
    private JButton btnTerminarCompra;
    private JButton btnAtras;
    private JPanel jpaIzquierdo;
    private JRadioButton rbtCocaCola;
    private JRadioButton rbtPepsi;
    private JRadioButton rbtFanta;
    private JRadioButton rbtSprite;
    private JTextArea txaLista;
    private int contador =1;
    public static JFrame frameRefrescosSoda = new JFrame("Menu Refrescos de Soda");
    public static JFrame frameCarrito = new JFrame("Carrito");
    public static JFrame frameOrden = new JFrame("Ordenes");
    public frmMenuRefrescosSoda() {
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuBebidas.frameRefrescosSoda.dispose();
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
                if (rbtCocaCola.isSelected()){
                    Nombre = "CocaCola";
                    Codigo = "01";
                    Precio = "35 Lps.";

                    Descripcion = "Nombre: CocaCola\n Codigo:01\n Precio:35Lps\n Descripcion: CocaCola" ;
                }
                if (rbtPepsi.isSelected()){
                    Nombre = "Pepsi";
                    Codigo = "02";
                    Precio = "30 Lps.";

                    Descripcion = "Nombre: Pepsi\n Codigo:02\n Precio:30Lps\n Descripcion: Pepsi" ;
                }
                if (rbtFanta.isSelected()){
                    Nombre = "Fanta";
                    Codigo = "03";
                    Precio = "20 Lps.";

                    Descripcion = "Nombre: Fanta\n Codigo:03\n Precio:20Lps\n Descripcion: Fanta" ;
                }
                if (rbtSprite.isSelected()){
                    Nombre = "Sprite";
                    Codigo = "04";
                    Precio = "20 Lps.";

                    Descripcion = "Nombre: Sprite\n Codigo:04\n Precio:20Lps\n Descripcion: Sprite" ;
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
                    if (rbtCocaCola.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Coca Cola");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(1);
                        DetalleFactura(1);
                    }
                    if (rbtPepsi.isSelected()){

                        orden.setCliente(1);
                        orden.setDescripcion("Pepsi");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(2);
                        DetalleFactura(2);
                    }
                    if (rbtFanta.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Fanta");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(3);
                        DetalleFactura(3);
                    }
                    if (rbtSprite.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Sprite");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(4);
                        DetalleFactura(4);
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
    public void DetalleFactura(int numero){
        Client client= ClientBuilder.newClient();
        WebTarget target = client.target(URLDETALLEFACTURA + "/addDetalleFactura");
        Invocation.Builder solicitud =target.request();
        DetalleFactura detalleFactura = new DetalleFactura();
        if (numero ==1){
            detalleFactura.setProductoid(55);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(35.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==2){
            detalleFactura.setProductoid(56);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(30.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==3){
            detalleFactura.setProductoid(57);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(20.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==4){
            detalleFactura.setProductoid(58);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(20.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(detalleFactura);
        Response post = solicitud.post(Entity.json(jsonString));
        String responseJson = post.readEntity(String.class);
        DetalleFactura data = new Gson().fromJson(responseJson, DetalleFactura.class);

    }

    public void Detalleordenes(int numero){
        Client client= ClientBuilder.newClient();
        WebTarget target = client.target(URLDETALLEORDEN + "/addDetalleOrden");
        Invocation.Builder solicitud =target.request();
        DetalleOrden detalleOrden = new DetalleOrden();
        if (numero ==1){
            detalleOrden.setProductoid(55);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(35);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==2){
            detalleOrden.setProductoid(56);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(30);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==3){
            detalleOrden.setProductoid(57);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(20);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==4){
            detalleOrden.setProductoid(58);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(20);
            detalleOrden.setNumeroOrden(contador);
        }

        Gson gson = new Gson();
        String jsonString = gson.toJson(detalleOrden);
        Response post = solicitud.post(Entity.json(jsonString));
        String responseJson = post.readEntity(String.class);
        DetalleOrden data = new Gson().fromJson(responseJson, DetalleOrden.class);

    }

    String res = "";
    String URLORDEN = "http://192.168.0.6:8080/api/v1/Orden";
    String URLDETALLEORDEN = "http://192.168.0.6:8080/api/v1/DetalleOrden";
    String URLDETALLEFACTURA = "http://192.168.0.6:8080/api/v1/DetalleFactura";
    String URLFACTURA = "http://192.168.0.6:8080/api/v1/Factura";
    public static void main(String[] args) {
        frameRefrescosSoda.setContentPane(new frmMenuRefrescosSoda().jpaRefrescosSoda);
        frameRefrescosSoda.setResizable(false);
        frameRefrescosSoda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameRefrescosSoda.pack();
        frameRefrescosSoda.setLocationRelativeTo(null);
        frameRefrescosSoda.setVisible(true);
    }
}
