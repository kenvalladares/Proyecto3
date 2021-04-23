package gui.orden;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xpath.internal.operations.Or;
import gui.MenuLogin.sub.frmSubmenu;
import recursos.clases.Factura;
import recursos.clases.Orden;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuHambuguesa.frmMenuHamburguesas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuPastas.frmMenuPastas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuPizzas.frmMenuPizzas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuSopas.frmMenuSopas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuEnsaladas.frmMenuEnsaladas;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosSoda.frmMenuRefrescosSoda;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuBebidas.Submenu.MenuRefrescosNaturales.frmRefrescosNaturales;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuPie.frmMenuPie;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuPastel.frmMenuPastel;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuPostres.MenuHelados.frmMenuHelados;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.awt.event.*;
import java.util.List;

public class frmOrden {
    public JPanel jpaPrincipal;
    private JPanel jpaOrden;
    private JPanel jpaSuperior;
    private JButton btnListar;
    private JTextField txtId;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel lvlId;
    private JButton btnAtras;
    private JPanel jpaInferior;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    DefaultTableModel modelo;
    public static JFrame frameOrden = new JFrame("Ordenes");

    public frmOrden() {
        iniciarOrden();
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URLORDEN + "/delete/"+ Integer.parseInt(txtId.getText()));
                    Invocation.Builder solicitud =target.request();
                    Response delete = solicitud.delete();
                    String responseJson = delete.readEntity(String.class);
                    switch (delete.getStatus()) {
                        case 200:
                            res = "La Orden ha sido Eliminada";
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                    LlenarDatosOrden();
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }
            }
        });
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuHamburguesas.frameOrden.dispose();
                frmMenuPizzas.frameOrden.dispose();
                frmMenuSopas.frameOrden.dispose();
                frmMenuEnsaladas.frameOrden.dispose();
                frmMenuPastas.frameOrden.dispose();
                frmMenuHelados.frameOrden.dispose();
                frmMenuPastel.frameOrden.dispose();
                frmMenuPie.frameOrden.dispose();
                frmRefrescosNaturales.frameOrden.dispose();
                frmMenuRefrescosSoda.frameOrden.dispose();
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblDatos.getSelectedRow();
                txtId.setText(modelo.getValueAt(filaSeleccionada,0).toString());


            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URLORDEN + "/id/"+txtId.getText());
                    Invocation.Builder solicitud =target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Orden data = new Gson().fromJson(responseJson, Orden.class);

                    res = String.valueOf(data.getId());

                    JOptionPane.showMessageDialog(null,get.getStatus());

                    switch (get.getStatus()) {
                        case 200:
                            res = String.valueOf(" ID: "+data.getId())+" Cliente: "+data.getCliente()+ " Descripcion: "+data.getDescripcion()+" Fecha: "+ data.getFecha()+ "Empleado: "+data.getEmpleado();
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                    LlenarDatosOrden();
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }
            }
        });
    }
    private void iniciarOrden() {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Cliente");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Fecha");
        modelo.addColumn("Empleado");
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URLORDEN + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Orden> data = new Gson().fromJson(responseJson, new TypeToken<List<Orden>>(){}.getType());
            switch (get.getStatus()) {
                case 200:
                    LeerDatosOrden(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch (Exception e1){
            res="Error, no se cual es";
        }
    }
    public void LeerDatosOrden(List<Orden> ordens){
        try {
            modelo.setRowCount(0);
            for (Orden orden1: ordens) {
                Object[] registroLeido = {orden1.getId(),orden1.getCliente(),orden1.getDescripcion(),orden1.getFecha(),orden1.getEmpleado()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    public void LlenarDatosOrden(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URLORDEN + "");
        Invocation.Builder solicitud = target.request();
        Response get = solicitud.get();
        String responseJson = get.readEntity(String.class);
        List<Orden> data = new Gson().fromJson(responseJson, new TypeToken<List<Orden>>(){}.getType());
        LeerDatosOrden(data);
    }
    String res = "";
    String URLORDEN = "http://192.168.0.6:8080/api/v1/Orden";
    public static void main(String[] args) {
        frameOrden.setContentPane(new frmOrden().jpaPrincipal);
        frameOrden.setResizable(false);
        frameOrden.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameOrden.pack();
        frameOrden.setLocationRelativeTo(null);
        frameOrden.setVisible(true);
    }
}
