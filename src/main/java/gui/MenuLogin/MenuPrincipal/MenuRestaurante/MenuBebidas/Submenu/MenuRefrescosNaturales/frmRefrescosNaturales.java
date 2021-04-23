package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosNaturales;

import com.google.gson.Gson;
import gui.Carrito.frmCarrito;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.frmMenuBebidas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.frmMenuBebidas;
import gui.orden.frmOrden;
import recursos.clases.*;
import sun.security.krb5.internal.crypto.Des;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmRefrescosNaturales {
    private JPanel jpaPrincipal;
    public JPanel jpaMenuRefrescosNaturales;
    private JPanel jpaSuperior;
    private JButton btnAñadir;
    private JButton btnInformacion;
    private JButton btnVerOrden;
    private JButton btnTerminarCompra;
    private JButton btnAtras;
    private JPanel jpaIzquierdo;
    private JRadioButton rbtHorchata;
    private JRadioButton rbtJamaica;
    private JRadioButton rbtLimonada;
    private JRadioButton rbtMaracuya;
    private JTextArea txaLista;
    private int contador = 1;
    public static JFrame frameRefrescosNaturales = new JFrame("Menu Refrescos Naturales");
    public static JFrame frameOrden = new JFrame("Ordenes");
    public static JFrame frameCarrito = new JFrame("Carrito");
    public frmRefrescosNaturales() {
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuBebidas.frameRefrescosNaturales.dispose();
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
                if (rbtHorchata.isSelected()){
                    Nombre = "Horchata";
                    Codigo = "01";
                    Precio = "40 Lps.";

                    Descripcion = "Nombre: Horchata\n Codigo:01\n Precio:40Lps\n Descripcion: Horchata" ;
                }
                if (rbtJamaica.isSelected()){
                    Nombre = "Jamaica";
                    Codigo = "02";
                    Precio = "35 Lps.";

                    Descripcion = "Nombre: Jamaica\n Codigo:02\n Precio:35Lps\n Descripcion: Jamaica" ;
                }
                if (rbtLimonada.isSelected()){
                    Nombre = "Limonada";
                    Codigo = "03";
                    Precio = "25 Lps.";

                    Descripcion = "Nombre: Limonada\n Codigo:03\n Precio:25Lps\n Descripcion: Limonada" ;
                }
                if (rbtMaracuya.isSelected()){
                    Nombre = "Maracuya";
                    Codigo = "04";
                    Precio = "35 Lps.";

                    Descripcion = "Nombre: Maracuya\n Codigo:04\n Precio:35Lps\n Descripcion: Maracuyá" ;
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
                    if (rbtHorchata.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Horchata");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(1);
                        DetalleFactura(1);
                    }
                    if (rbtJamaica.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Jamaica");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(2);
                        DetalleFactura(2);
                    }
                    if (rbtLimonada.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Limonada");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(3);
                        DetalleFactura(3);
                    }
                    if (rbtMaracuya.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Maracuyá");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(4);
                        DetalleFactura(4);
                    }
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(orden);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    switch (post.getStatus()) {
                        case 201:
                            Orden data = new Gson().fromJson(responseJson, Orden.class);
                            res = "Orden añadida Exitosamente";
                            break;
                        case 500:
                            RestApiError errorApi = new Gson().fromJson(responseJson, RestApiError.class);
                            res = errorApi.getErrorDetails();
                        default:
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
            detalleFactura.setProductoid(90);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(40.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==2){
            detalleFactura.setProductoid(91);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(35.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==3){
            detalleFactura.setProductoid(92);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(25.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==4){
            detalleFactura.setProductoid(93);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(35.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(detalleFactura);
        Response post = solicitud.post(Entity.json(jsonString));
        String responseJson = post.readEntity(String.class);
        switch (post.getStatus()) {
            case 201:
                DetalleFactura data = new Gson().fromJson(responseJson, DetalleFactura.class);
                res = "DetalleFactura añadida Exitosamente";
                break;
            case 500:
                RestApiError errorApi = new Gson().fromJson(responseJson, RestApiError.class);
                res = errorApi.getErrorDetails();
            default:
                break;
        }
    }
    public void Detalleordenes(int numero){
        Client client= ClientBuilder.newClient();
        WebTarget target = client.target(URLDETALLEORDEN + "/addDetalleOrden");
        Invocation.Builder solicitud =target.request();
        DetalleOrden detalleOrden = new DetalleOrden();
        if (numero ==1){
            detalleOrden.setProductoid(90);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(40);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==2){
            detalleOrden.setProductoid(91);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(35);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==3){
            detalleOrden.setProductoid(92);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(25);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==4){
            detalleOrden.setProductoid(93);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(35);
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
        frameRefrescosNaturales.setContentPane(new frmRefrescosNaturales().jpaMenuRefrescosNaturales);
        frameRefrescosNaturales.setResizable(false);
        frameRefrescosNaturales.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameRefrescosNaturales.pack();
        frameRefrescosNaturales.setLocationRelativeTo(null);
        frameRefrescosNaturales.setVisible(true);
    }
}
