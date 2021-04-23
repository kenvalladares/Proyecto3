package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuPastel;

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

public class frmMenuPastel {
    private JPanel jpaPrincipal;
    public JPanel jpaMenuPastel;
    private JPanel jpaSuperior;
    private JButton btnAñadir;
    private JButton btnInformacion;
    private JButton btnVerOrden;
    private JButton btnTerminarCompra;
    private JButton btnAtras;
    private JPanel jpaIzquierdo;
    private JRadioButton rbtPastelHelado;
    private JRadioButton rbtAlTiempo;
    private JRadioButton rbtDeChocolate;
    private JRadioButton rbtRellenoDeLecheCondensada;
    private JRadioButton rbtRellenoDeDulceDeLeche;
    private JTextArea txaLista;
    private int contador =1;
    public static JFrame framePastel = new JFrame("Menu de Pasteles");
    public static JFrame frameCarrito = new JFrame("Carrito");
    public static JFrame frameOrden = new JFrame("Ordenes");
    public frmMenuPastel() {
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPostres.framePastel.dispose();
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
                if (rbtPastelHelado.isSelected()){
                    Nombre = "Pastel Helado";
                    Codigo = "01";
                    Precio = "50 Lps.";

                    Descripcion = "Nombre: PastelHelado\n Codigo:01\n Precio:50Lps\n Descripcion: Trozo de pastel helado" ;
                }
                if (rbtAlTiempo.isSelected()){
                    Nombre = "Pastel al tiempo";
                    Codigo = "02";
                    Precio = "40 Lps.";

                    Descripcion = "Nombre: Pastel al tiempo\n Codigo:02\n Precio:40Lps\n Descripcion: Trozo de pastel al tiempo" ;
                }
                if (rbtDeChocolate.isSelected()){
                    Nombre = "Pastel de Chocolate";
                    Codigo = "03";
                    Precio = "60 Lps.";

                    Descripcion = "Nombre: Pastel de Chocolate\n Codigo:03\n Precio:60Lps\n Descripcion: Trozo de pastel de Chocolate" ;
                }
                if (rbtRellenoDeLecheCondensada.isSelected()){
                    Nombre = "Pastel relleno de leche condensada";
                    Codigo = "04";
                    Precio = "70 Lps.";

                    Descripcion = "Nombre: Pastel relleno de leche condensada\n Codigo:04\n Precio:70Lps\n Descripcion: Trozo de pastel relleno de leche condensada" ;
                }
                if (rbtRellenoDeDulceDeLeche.isSelected()){
                    Nombre = "Pastel relleno de dulce de leche";
                    Codigo = "04";
                    Precio = "70 Lps.";

                    Descripcion = "Nombre: Pastel relleno de dulce de leche\n Codigo:04\n Precio:70Lps\n Descripcion: Trozo de pastel relleno de dulce de leche" ;
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
                    if (rbtPastelHelado.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pastel Helado");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(1);
                        DetalleFactura(1);
                    }
                    if (rbtAlTiempo.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pastel al tiempo");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(2);
                        DetalleFactura(2);
                    }
                    if (rbtDeChocolate.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pastel de Chocolate");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(3);
                        DetalleFactura(3);
                    }
                    if (rbtRellenoDeLecheCondensada.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pastel relleno de leche condensada");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(4);
                        DetalleFactura(4);
                    }
                    if (rbtRellenoDeDulceDeLeche.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pastel relleno de dulce de leche");
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
    public void DetalleFactura(int numero){
        Client client= ClientBuilder.newClient();
        WebTarget target = client.target(URLDETALLEFACTURA + "/addDetalleFactura");
        Invocation.Builder solicitud =target.request();
        DetalleFactura detalleFactura = new DetalleFactura();
        if (numero ==1){
            detalleFactura.setProductoid(70);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(50.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==2){
            detalleFactura.setProductoid(71);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(40.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==3){
            detalleFactura.setProductoid(72);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(60.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==4){
            detalleFactura.setProductoid(73);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(70.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==5){
            detalleFactura.setProductoid(74);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(70.00);
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
            detalleOrden.setProductoid(70);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(50);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==2){
            detalleOrden.setProductoid(71);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(40);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==3){
            detalleOrden.setProductoid(72);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(60);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==4){
            detalleOrden.setProductoid(73);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(70);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==5){
            detalleOrden.setProductoid(74);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(70);
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
        framePastel.setContentPane(new frmMenuPastel().jpaMenuPastel);
        framePastel.setResizable(false);
        framePastel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePastel.pack();
        framePastel.setLocationRelativeTo(null);
        framePastel.setVisible(true);
    }
}
