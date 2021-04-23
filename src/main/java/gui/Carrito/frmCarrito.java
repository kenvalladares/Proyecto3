package gui.Carrito;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuHambuguesa.frmMenuHamburguesas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuEnsaladas.frmMenuEnsaladas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuPastas.frmMenuPastas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuPizzas.frmMenuPizzas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuSopas.frmMenuSopas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuPie.frmMenuPie;
import gui. MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuPastel.frmMenuPastel;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuHelados.frmMenuHelados;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosNaturales.frmRefrescosNaturales;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosSoda.frmMenuRefrescosSoda;
import gui.Facturar.frmFactura;
import recursos.clases.Clientes;
import recursos.clases.DetalleOrden;
import recursos.clases.Factura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class frmCarrito {
    public JPanel jpaPrincipal;
    private JPanel jpaInferior;
    private JLabel lvlTotalPagar;
    private JTextArea txtTotalPagar;
    private JButton btnAtras;
    private JButton btnPagar;
    private JPanel jpaSuperior;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    private double Total;
    private int contador = 1;
    int idd = 0;
    public static JFrame frameCarrito = new JFrame("Carrito");
    public static JFrame frameFactura = new JFrame("Factura");
    DefaultTableModel modelo;
    public frmCarrito() {
        iniciarDetalleOrden();
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuHamburguesas.frameCarrito.dispose();
                frmMenuSopas.frameCarrito.dispose();
                frmMenuPizzas.frameCarrito.dispose();
                frmMenuPastas.frameCarrito.dispose();
                frmMenuEnsaladas.frameCarrito.dispose();
                frmMenuHelados.frameCarrito.dispose();
                frmMenuPastel.frameCarrito.dispose();
                frmMenuPie.frameCarrito.dispose();
                frmMenuRefrescosSoda.frameCarrito.dispose();
                frmRefrescosNaturales.frameCarrito.dispose();
            }
        });
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AñadirFactura();
                frameFactura.setContentPane(new frmFactura().jpaPrincipal);
                frameFactura.setResizable(false);
                frameFactura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameFactura.pack();
                frameFactura.setLocationRelativeTo(null);
                frameFactura.setVisible(true);
            }
        });
    }
    public void LeerDetalleOrden(List<DetalleOrden> detalleOrdens){
        try {
            modelo.setRowCount(0);
            for (DetalleOrden detalleOrden: detalleOrdens) {
                idd = (int) detalleOrden.getId();
                Object[] registroLeido = {detalleOrden.getId(), detalleOrden.getProductoid(),detalleOrden.getFecha(),detalleOrden.getTotal(), detalleOrden.getNumeroOrden()};
                Total = Total+detalleOrden.getTotal();
                txtTotalPagar.setText(String.valueOf(Total));
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    private void iniciarDetalleOrden() {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("ProductoId");
        modelo.addColumn("Fecha");
        modelo.addColumn("Total");
        modelo.addColumn("NumeroOrden");
        try {

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URLDETALLEORDEN + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<DetalleOrden> data = new Gson().fromJson(responseJson, new TypeToken<List<DetalleOrden>>(){}.getType());

            switch (get.getStatus()) {
                case 200:
                    LeerDetalleOrden(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch (Exception e1){
            res="Error, no se cual es";
        }
    }
    public void AñadirFactura(){
        Client client= ClientBuilder.newClient();
        WebTarget target = client.target(URLFACTURA + "/addFactura");
        Invocation.Builder solicitud =target.request();
        Factura factura = new Factura();
        factura.setCliente(idd-167+contador);
        factura.setEmpleado(1);
        factura.setCAI(idd+1000+contador);
        contador++;
        factura.setTotal(Total);
        factura.setFecha("2021-04-22");
        Gson gson = new Gson();
        String jsonString = gson.toJson(factura);
        Response post = solicitud.post(Entity.json(jsonString));
        String responseJson = post.readEntity(String.class);
        Factura data = new Gson().fromJson(responseJson, Factura.class);
        contador++;
    }
    String res = "";
    String URLDETALLEORDEN = "http://192.168.0.6:8080/api/v1/DetalleOrden";
    String URLFACTURA = "http://192.168.0.6:8080/api/v1/Factura";
    public static void main(String[] args) {
        frameCarrito.setContentPane(new frmCarrito().jpaPrincipal);
        frameCarrito.setResizable(false);
        frameCarrito.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCarrito.pack();
        frameCarrito.setLocationRelativeTo(null);
        frameCarrito.setVisible(true);
    }
}
