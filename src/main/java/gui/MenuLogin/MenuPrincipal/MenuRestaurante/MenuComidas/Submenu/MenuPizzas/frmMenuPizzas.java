package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuPizzas;

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

public class frmMenuPizzas {
    private JPanel jpaPrincipal;
    public JPanel jpaMenuPizzas;
    private JPanel jpaSuperior;
    private JButton btnAñadir;
    private JButton btnInformacion;
    private JButton btnVerOrden;
    private JButton btnTerminarCompra;
    private JButton btnAtras;
    private JPanel jpaIzquierdo;
    private JRadioButton rbtClasica;
    private JRadioButton rbtDuo;
    private JRadioButton rbtExtraCheese;
    private JRadioButton rbtRectangular;
    private JRadioButton rbt4Estaciones;
    private JTextArea txaLista;
    public static JFrame framePizzas = new JFrame("Menu Pizzas");
    public static JFrame frameCarrito = new JFrame("Carrito");
    public static JFrame frameOrden = new JFrame("Ordenes");
    public int contador =1;
    public frmMenuPizzas() {
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuComidas.framePizzas.dispose();
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
                if (rbtClasica.isSelected()){
                    Nombre = "Clasica";
                    Codigo = "01";
                    Precio = "150 Lps.";

                    Descripcion = "Nombre: Clasica\n Codigo:01\n Precio:150Lps\n Descripcion: De jamón o Pepperoni, 8 pedazos" ;
                }
                if (rbtDuo.isSelected()){
                    Nombre = "Duo";
                    Codigo = "02";
                    Precio = "250 Lps.";

                    Descripcion = "Nombre: Duo\n Codigo:02\n Precio:250Lps\n Descripcion: Mitad suprema, mitad jamón" ;
                }
                if (rbtExtraCheese.isSelected()){
                    Nombre = "Extra Cheese";
                    Codigo = "03";
                    Precio = "300 Lps.";

                    Descripcion = "Nombre: Extracheese\n Codigo:03\n Precio:300 Lps\n Descripcion: Con queso adicional y queso en las orillas" ;
                }
                if (rbtRectangular.isSelected()){
                    Nombre = "Rectangular";
                    Codigo = "04";
                    Precio = "295 Lps.";

                    Descripcion = "Nombre: Rectangular\n Codigo:04\n Precio:295Lps\n Descripcion: 12 Pedazos" ;
                }
                if (rbt4Estaciones.isSelected()){
                    Nombre = "4 Estaciones";
                    Codigo = "05";
                    Precio = "260 Lps.";

                    Descripcion = "Nombre: 4 Estaciones\n Codigo:05\n Precio:260Lps\n Descripcion: 8 pedazos, 2 de cada especialidad " ;
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
                    if (rbtClasica.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pizza Clásica");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(1);
                        DetalleFactura(1);
                    }
                    if (rbtDuo.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pizza Dúo");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(2);
                        DetalleFactura(2);
                    }
                    if (rbtExtraCheese.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pizza ExtraCheese");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(3);
                        DetalleFactura(3);
                    }
                    if (rbtRectangular.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pizza Rectangular");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(4);
                        DetalleFactura(4);
                    }
                    if (rbt4Estaciones.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pizza 4 Estaciones");
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
            detalleOrden.setProductoid(121);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(150);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==2){
            detalleOrden.setProductoid(122);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(250);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==3){
            detalleOrden.setProductoid(123);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(300);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==4){
            detalleOrden.setProductoid(124);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(295);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==5){
            detalleOrden.setProductoid(125);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(260);
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
            detalleFactura.setProductoid(121);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(150.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==2){
            detalleFactura.setProductoid(122);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(250.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==3){
            detalleFactura.setProductoid(123);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(300.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==4){
            detalleFactura.setProductoid(124);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(295.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==5){
            detalleFactura.setProductoid(125);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(260.00);
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
        framePizzas.setContentPane(new frmMenuPizzas().jpaMenuPizzas);
        framePizzas.setResizable(false);
        framePizzas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePizzas.pack();
        framePizzas.setLocationRelativeTo(null);
        framePizzas.setVisible(true);
    }
}
