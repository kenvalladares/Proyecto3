package gui.Facturar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xpath.internal.operations.Or;
import gui.Carrito.frmCarrito;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.frmMenuRestaurante;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.frmMenuBebidas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosSoda.frmMenuRefrescosSoda;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosNaturales.frmRefrescosNaturales;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.frmMenuComidas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuPizzas.frmMenuPizzas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuEnsaladas.frmMenuEnsaladas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuHambuguesa.frmMenuHamburguesas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuSopas.frmMenuSopas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuPastas.frmMenuPastas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.frmPostres;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuHelados.frmMenuHelados;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuPastel.frmMenuPastel;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuPie.frmMenuPie;
import gui.MenuLogin.sub.frmSubmenu;
import recursos.clases.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class frmFactura {
    public JPanel jpaPrincipal;
    private JPanel jpaFactura;
    private JButton btnPagar;
    private JButton btnSalir;
    private JRadioButton btnTarjetaCredito;
    private JRadioButton btnTarjetaDebito;
    private JRadioButton btnEfectivo;
    private JButton btnMenuPrincipal;
    private JTable tblDatos;
    private JScrollPane sclPanDatos;
    private JLabel lvlTotalPagar;
    private JTextArea txtTotalPagar;
    public static JFrame frameFactura = new JFrame("Factura");
    DefaultTableModel modelo;
    private double Total;
    private int contador = 1;
    public frmFactura() {
        iniciarDetalleFactura();
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Elimina Detalles Facturas
                    Client client = ClientBuilder.newClient();
                    WebTarget target = client.target(URLDETALLEFACTURA + "");
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    List<DetalleFactura> data = new Gson().fromJson(responseJson, new TypeToken<List<DetalleFactura>>(){}.getType());
                    EliminarDetalleFactura(data);
                    //Elimina Detalle Orden
                    Client client1 = ClientBuilder.newClient();
                    WebTarget target1 = client1.target(URLDETALLEORDEN + "");
                    Invocation.Builder solicitud1 = target1.request();
                    Response get1 = solicitud1.get();
                    String responseJson1 = get1.readEntity(String.class);
                    List<DetalleOrden> data1 = new Gson().fromJson(responseJson1, new TypeToken<List<DetalleOrden>>(){}.getType());
                    EliminarDetalleOrden(data1);
                    //Elimina Orden
                    Client client2 = ClientBuilder.newClient();
                    WebTarget target2 = client2.target(URLORDEN + "");
                    Invocation.Builder solicitud2 = target2.request();
                    Response get2 = solicitud2.get();
                    String responseJson2 = get2.readEntity(String.class);
                    List<Orden> data2 = new Gson().fromJson(responseJson2, new TypeToken<List<Orden>>(){}.getType());
                    EliminarOrden(data2);
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,"Pago Realizado con Exito, Imprimiendo Factura...","Exito",JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        btnMenuPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrar();
            }
        });
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public static void cerrar(){
        frmMenuRestaurante.frameComidas.dispose();
        frmMenuComidas.framePizzas.dispose();
        frmMenuPizzas.frameCarrito.dispose();
        frmMenuComidas.frameHamburguesas.dispose();
        frmMenuHamburguesas.frameCarrito.dispose();
        frmMenuComidas.frameEnsaladas.dispose();
        frmMenuEnsaladas.frameCarrito.dispose();
        frmMenuComidas.frameSopas.dispose();
        frmMenuSopas.frameCarrito.dispose();
        frmMenuComidas.framePastas.dispose();
        frmMenuPastas.frameCarrito.dispose();
        frmMenuRestaurante.framePostres.dispose();
        frmPostres.framePie.dispose();
        frmMenuPie.frameCarrito.dispose();
        frmPostres.framePastel.dispose();
        frmMenuPastel.frameCarrito.dispose();
        frmPostres.frameHelados.dispose();
        frmMenuHelados.frameCarrito.dispose();
        frmMenuRestaurante.frameBebidas.dispose();
        frmMenuBebidas.frameRefrescosSoda.dispose();
        frmMenuRefrescosSoda.frameCarrito.dispose();
        frmMenuBebidas.frameRefrescosNaturales.dispose();
        frmRefrescosNaturales.frameCarrito.dispose();
        frmCarrito.frameCarrito.dispose();
        frmCarrito.frameFactura.dispose();
        frmMenuRestaurante.frameRestaurante.dispose();
        frmSubmenu.frameRestaurante.dispose();
      }
    public void LeerDetalleFactura(List<DetalleFactura> detalleFacturas){
        try {
            modelo.setRowCount(0);
            for (DetalleFactura detalleFactura: detalleFacturas) {
                Object[] registroLeido = {detalleFactura.getId(),detalleFactura.getProductoid(),detalleFactura.getCliente(),detalleFactura.getTotal(),detalleFactura.getDescuento(),detalleFactura.getEmpleado()};
                Total = Total+detalleFactura.getTotal();
                txtTotalPagar.setText(String.valueOf(Total));
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    public void EliminarDetalleFactura(List<DetalleFactura> detalleFacturas){
        try {
            for (DetalleFactura detalleFactura: detalleFacturas) {
                Object[] registroLeido = {detalleFactura.getId(),detalleFactura.getProductoid(),detalleFactura.getCliente(),detalleFactura.getTotal(),detalleFactura.getDescuento(),detalleFactura.getEmpleado()};
                Client client= ClientBuilder.newClient();
                WebTarget target = client.target(URLDETALLEFACTURA + "/delete/"+detalleFactura.getId());
                Invocation.Builder solicitud =target.request();
                Response delete = solicitud.delete();
                String responseJson = delete.readEntity(String.class);
            }
        }catch (Exception e){
        }
    }
    public void EliminarDetalleOrden(List<DetalleOrden> detalleOrdens){
        try {
            for (DetalleOrden detalleOrden: detalleOrdens) {
                Object[] registroLeido = {detalleOrden.getId()};
                Client client= ClientBuilder.newClient();
                WebTarget target = client.target(URLDETALLEORDEN + "/delete/"+detalleOrden.getId());
                Invocation.Builder solicitud =target.request();
                Response delete = solicitud.delete();
                String responseJson = delete.readEntity(String.class);
            }
        }catch (Exception e){
        }
    }
    public void EliminarOrden(List<Orden> ordens){
        try {
            for (Orden orden: ordens) {
                Object[] registroLeido = {orden.getId()};
                Client client= ClientBuilder.newClient();
                WebTarget target = client.target(URLORDEN + "/delete/"+orden.getId());
                Invocation.Builder solicitud =target.request();
                Response delete = solicitud.delete();
                String responseJson = delete.readEntity(String.class);
            }
        }catch (Exception e){
        }
    }

    private void iniciarDetalleFactura() {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("ProductoId");
        modelo.addColumn("Cliente");
        modelo.addColumn("Total");
        modelo.addColumn("Descuento");
        modelo.addColumn("Empleado");
        try {

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URLDETALLEFACTURA + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<DetalleFactura> data = new Gson().fromJson(responseJson, new TypeToken<List<DetalleFactura>>(){}.getType());

            switch (get.getStatus()) {
                case 200:
                    LeerDetalleFactura(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch (Exception e1){
            res="Error, no se cual es";
        }
    }
    String res = "";
    String URLORDEN = "http://192.168.0.6:8080/api/v1/Orden";
    String URLDETALLEORDEN = "http://192.168.0.6:8080/api/v1/DetalleOrden";
    String URLDETALLEFACTURA = "http://192.168.0.6:8080/api/v1/DetalleFactura";
    String URLFACTURA = "http://192.168.0.6:8080/api/v1/Factura";
    public static void main(String[] args) {
        frameFactura.setContentPane(new frmFactura().jpaPrincipal);
        frameFactura.setResizable(false);
        frameFactura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFactura.pack();
        frameFactura.setLocationRelativeTo(null);
        frameFactura.setVisible(true);
    }
}
