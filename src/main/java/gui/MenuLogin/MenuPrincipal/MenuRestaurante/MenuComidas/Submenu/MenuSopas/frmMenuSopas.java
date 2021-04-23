package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuSopas;

import com.google.gson.Gson;
import gui.Carrito.frmCarrito;
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

public class frmMenuSopas {
    private JPanel jpaPrincipal;
    public JPanel jpaSopas;
    private JPanel jpaSuperior;
    private JButton btnAñadir;
    private JButton btnInformacion;
    private JButton btnVerOrden;
    private JButton btnTerminarCompra;
    private JButton btnAtras;
    private JPanel jpaIzquierdo;
    private JRadioButton rbtDePollo;
    private JRadioButton rbtDeRes;
    private JRadioButton rbtMarinera;
    private JRadioButton rbtTapado;
    private JRadioButton rbtDePescado;
    private JTextArea txaLista;
    public int contador=1;
    public static JFrame frameSopas = new JFrame("Menu Sopas");
    public static JFrame frameCarrito = new JFrame("Carrito");
    public static JFrame frameOrden = new JFrame("Ordenes");
    public frmMenuSopas() {
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuComidas.frameSopas.dispose();
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
                if (rbtDePollo.isSelected()){
                    Nombre = "De Pollo";
                    Codigo = "01";
                    Precio = "90 Lps.";

                    Descripcion = "Nombre: De Pollo\n Codigo:01\n Precio:90Lps\n Descripcion: Con vegetales sanos y pollo" ;
                }
                if (rbtDeRes.isSelected()){
                    Nombre = "De Res";
                    Codigo = "02";
                    Precio = "70 Lps.";

                    Descripcion = "Nombre: De Res\n Codigo:02\n Precio:70Lps\n Descripcion: Con carne de res y verduras" ;
                }
                if (rbtMarinera.isSelected()){
                    Nombre = "Marinera";
                    Codigo = "03";
                    Precio = "130 Lps.";

                    Descripcion = "Nombre: Marinera\n Codigo:03\n Precio:130 Lps\n Descripcion: De mariscos variados" ;
                }
                if (rbtDePescado.isSelected()){
                    Nombre = "De Pescado";
                    Codigo = "04";
                    Precio = "90 Lps.";

                    Descripcion = "Nombre: De Pescado\n Codigo:04\n Precio:90Lps\n Descripcion: Con tortas de pescado" ;
                }
                if (rbtTapado.isSelected()){
                    Nombre = "Tapado";
                    Codigo = "05";
                    Precio = "130 Lps.";

                    Descripcion = "Nombre: Tapado\n Codigo:05\n Precio:130Lps\n Descripcion: Sopa típica hondureña" ;
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
                    if (rbtDePollo.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion(" Sopa De Pollo");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(1);
                        DetalleFactura(1);
                    }
                    if (rbtDeRes.isSelected()){

                        orden.setCliente(1);
                        orden.setDescripcion(" Sopa de Res");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(2);
                        DetalleFactura(2);
                    }
                    if (rbtMarinera.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion(" Sopa Marinera");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(3);
                        DetalleFactura(3);
                    }
                    if (rbtTapado.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion(" Tapado");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(4);
                        DetalleFactura(4);
                    }
                    if (rbtDePescado.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion(" Sopa de Pescado");
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
            detalleOrden.setProductoid(36);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(60);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==2){
            detalleOrden.setProductoid(37);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(70);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==3){
            detalleOrden.setProductoid(38);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(130);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==4){
            detalleOrden.setProductoid(39);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(90);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==5){
            detalleOrden.setProductoid(40);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(130);
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
            detalleFactura.setProductoid(36);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(60.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==2){
            detalleFactura.setProductoid(37);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(70.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==3){
            detalleFactura.setProductoid(38);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(130.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==4){
            detalleFactura.setProductoid(39);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(90.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==5){
            detalleFactura.setProductoid(40);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(130.00);
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
        frameSopas.setContentPane(new frmMenuSopas().jpaSopas);
        frameSopas.setResizable(false);
        frameSopas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameSopas.pack();
        frameSopas.setLocationRelativeTo(null);
        frameSopas.setVisible(true);
    }
}
