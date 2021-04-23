package gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuEnsaladas;

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

public class frmMenuEnsaladas {
    private JPanel jpaPrincipal;
    public JPanel jpaEnsaladas;
    private JButton btnAñadir;
    private JButton btnInformacion;
    private JButton btnVerOrden;
    private JButton btnTerminarCompra;
    private JButton btnAtras;
    private JRadioButton rbtBaja;
    private JRadioButton rbtCaesar;
    private JRadioButton rbtLight;
    private JRadioButton rbtVerde;
    private JRadioButton rbtDulce;
    private JPanel jpaSuperior;
    private JPanel jpaIzquierdo;
    private JTextArea txaLista;
    private int contador =1;
    public static JFrame frameEnsaladas = new JFrame("Menu Ensaladas");
    public static JFrame frameCarrito = new JFrame("Carrito");
    public static JFrame frameOrden = new JFrame("Ordenes");
    public frmMenuEnsaladas() {
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuComidas.frameEnsaladas.dispose();
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
                if (rbtBaja.isSelected()){
                    Nombre = "Baja";
                    Codigo = "01";
                    Precio = "150 Lps.";

                    Descripcion = "Nombre: Baja\n Codigo:01\n Precio:150Lps\n Descripcion: Con verduras frescas y acompañadas de queso" ;
                }
                if (rbtCaesar.isSelected()){
                    Nombre = "Caesar";
                    Codigo = "02";
                    Precio = "250 Lps.";

                    Descripcion = "Nombre: Caesar\n Codigo:02\n Precio:250Lps\n Descripcion: Con Pollo, trocitos de huevo y aderezo Caesar" ;
                }
                if (rbtLight.isSelected()){
                    Nombre = "Light";
                    Codigo = "03";
                    Precio = "155 Lps.";

                    Descripcion = "Nombre: Light\n Codigo:03\n Precio:155Lps\n Descripcion: Sencilla con aderezo y crutones" ;
                }
                if (rbtVerde.isSelected()){
                    Nombre = "Verde";
                    Codigo = "04";
                    Precio = "170 Lps.";

                    Descripcion = "Nombre: Verde\n Codigo:04\n Precio:170Lps\n Descripcion: Vegetales verdes, pepino, manzana, chile verde, etc" ;
                }
                if (rbtDulce.isSelected()){
                    Nombre = "Dulce";
                    Codigo = "05";
                    Precio = "160 Lps.";

                    Descripcion = "Nombre: Dulce\n Codigo:05\n Precio:160Lps\n Descripcion: Acompañada de frutas dulces " ;
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
                    if (rbtBaja.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Ensalada Baja");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(1);
                        DetalleFactura(1);
                    }
                    if (rbtCaesar.isSelected()){

                        orden.setCliente(1);
                        orden.setDescripcion("Ensalada Caesar");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(2);
                        DetalleFactura(2);
                    }
                    if (rbtLight.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Pizza ExtraCheese");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(3);
                        DetalleFactura(3);
                    }
                    if (rbtVerde.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Ensalada Verde");
                        orden.setFecha("2002-04-22");
                        orden.setEmpleado(1);
                        Detalleordenes(4);
                        DetalleFactura(4);
                    }
                    if (rbtDulce.isSelected()){
                        orden.setCliente(1);
                        orden.setDescripcion("Ensalada Dulce");
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
            detalleOrden.setProductoid(50);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(150);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==2){
            detalleOrden.setProductoid(51);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(250);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==3){
            detalleOrden.setProductoid(52);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(155);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==4){
            detalleOrden.setProductoid(53);
            detalleOrden.setFecha("2002-04-22");
            detalleOrden.setTotal(170);
            detalleOrden.setNumeroOrden(contador);
        }
        if (numero ==5){
            detalleOrden.setProductoid(54);
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
            detalleFactura.setProductoid(50);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(150.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==2){
            detalleFactura.setProductoid(51);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(250.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==3){
            detalleFactura.setProductoid(52);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(155.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==4){
            detalleFactura.setProductoid(53);
            detalleFactura.setCliente(1);
            detalleFactura.setTotal(170.00);
            detalleFactura.setDescuento(0.00);
            detalleFactura.setEmpleado(1);
        }
        if (numero ==5){
            detalleFactura.setProductoid(54);
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
        frameEnsaladas.setContentPane(new frmMenuEnsaladas().jpaEnsaladas);
        frameEnsaladas.setResizable(false);
        frameEnsaladas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameEnsaladas.pack();
        frameEnsaladas.setLocationRelativeTo(null);
        frameEnsaladas.setVisible(true);
    }
}
