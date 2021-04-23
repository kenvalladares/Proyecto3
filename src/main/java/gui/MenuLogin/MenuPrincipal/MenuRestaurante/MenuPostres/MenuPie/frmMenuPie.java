package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuPie;

import com.google.gson.Gson;
import gui.Carrito.frmCarrito;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuHelados.frmMenuHelados;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.frmPostres;
import gui.orden.frmOrden;
import recursos.clases.DetalleFactura;
import recursos.clases.DetalleOrden;
import recursos.clases.Orden;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMenuPie {
    private JPanel jpaPrincipal;
    public JPanel jpaMenuPie;
    private JPanel jpaSuperior;
    private JButton btnAñadir;
    private JButton btnInformacion;
    private JButton btnVerOrden;
    private JButton btnTerminarCompra;
    private JButton btnAtras;
    private JPanel jpaIzquierdo;
    private JRadioButton rbtDeLimon;
    private JRadioButton rbtDeManzana;
    private JRadioButton rbtDeCanela;
    private JRadioButton rbtDeMaracuya;
    private JRadioButton rbtDeNaranja;
    private JTextArea txaLista;
    private int contador = 1;
    public static JFrame framePie = new JFrame("Menu de Pies");
    public static JFrame frameCarrito = new JFrame("Carrito");
    public static JFrame frameOrden = new JFrame("Ordenes");
    public frmMenuPie() {
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPostres.framePie.dispose();
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
                if (rbtDeLimon.isSelected()){
                    Nombre = "Pie de Limon";
                    Codigo = "01";
                    Precio = "45 Lps.";

                    Descripcion = "Nombre: Pie de Limon\n Codigo:01\n Precio:45Lps\n Descripcion: Plato de Pie de Limón" ;
                }
                if (rbtDeManzana.isSelected()){
                    Nombre = "Pie de Manzana";
                    Codigo = "02";
                    Precio = "45 Lps.";

                    Descripcion = "Nombre: Pie de Manzana\n Codigo:02\n Precio:45Lps\n Descripcion: Plato de Pie de Manzana" ;
                }
                if (rbtDeCanela.isSelected()){
                    Nombre = "Pie de Canela";
                    Codigo = "03";
                    Precio = "45 Lps.";

                    Descripcion = "Nombre: Pie de Canela\n Codigo:03\n Precio:45Lps\n Descripcion: Plato de Pie de Canela" ;
                }
                if (rbtDeMaracuya.isSelected()){
                    Nombre = "Pie de Maracuya";
                    Codigo = "04";
                    Precio = "45 Lps.";

                    Descripcion = "Nombre: Pie de Maracuya\n Codigo:04\n Precio:45Lps\n Descripcion: Plato de Pie de Maracuyá" ;
                }
                if (rbtDeNaranja.isSelected()){
                    Nombre = "Pie de Naranja";
                    Codigo = "05";
                    Precio = "45 Lps.";

                    Descripcion = "Nombre: Pie de Naranja\n Codigo:04\n Precio:45Lps\n Descripcion: Plato de Pie de Naranja" ;
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
                    if (rbtDeLimon.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pie de Limon");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(1);
                        DetalleFactura(1);
                    }
                    if (rbtDeManzana.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pie de Manzana");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(2);
                        DetalleFactura(2);
                    }
                    if (rbtDeCanela.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pie de Canela");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(3);
                        DetalleFactura(3);
                    }
                    if (rbtDeMaracuya.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pie de Maracuya");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(4);
                        DetalleFactura(4);
                    }
                    if (rbtDeNaranja.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pie de Manzana");
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
            detalleOrden.setProductoid(110);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(45);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==2){
            detalleOrden.setProductoid(111);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(45);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==3){
            detalleOrden.setProductoid(112);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(45);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==4){
            detalleOrden.setProductoid(113);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(45);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==5){
            detalleOrden.setProductoid(114);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(45);
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
            detalleFactura.setProductoid(110);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(45.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==2){
            detalleFactura.setProductoid(111);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(45.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==3){
            detalleFactura.setProductoid(112);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(45.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==4){
            detalleFactura.setProductoid(113);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(45.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==5){
            detalleFactura.setProductoid(114);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(45.00);
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
        framePie.setContentPane(new frmMenuPie().jpaMenuPie);
        framePie.setResizable(false);
        framePie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePie.pack();
        framePie.setLocationRelativeTo(null);
        framePie.setVisible(true);
    }
}
