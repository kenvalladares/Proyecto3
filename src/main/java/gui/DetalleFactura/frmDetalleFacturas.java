package gui.DetalleFactura;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gui.MenuLogin.sub.frmSubmenu;
import recursos.clases.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class frmDetalleFacturas {
    public JPanel jpaPrincipal;
    public JPanel jpaDetalleFacturas;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnBuscar;
    private JTextField txtId;
    private JButton btnAtras;
    private JTable tblDatos;
    private JPanel jpaDetalleFactura;
    private JPanel jpaSuperior;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JPanel jpaIzquierdo;
    private JLabel lvlCliente;
    private JLabel lvlId;
    private JLabel lvlEmpleado;
    private JLabel lvlCAI;
    private JLabel lvlTotal;
    private JLabel lvlFecha;
    private JPanel jpaDerecho;
    private JTextField txtCliente;
    private JTextField txtID;
    private JTextField txtEmpleado;
    private JTextField txtCAI;
    private JTextField txtFecha;
    private JTextField txtTotal;
    private JPanel jpaInferior;
    private JScrollPane sclPanDatos;
    public static JFrame frameDetalleFactura = new JFrame("Facturas");
    DefaultTableModel modelo;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public frmDetalleFacturas() {
        iniciarFacturas();
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/delete/"+ Integer.parseInt(txtID.getText()));
                    Invocation.Builder solicitud =target.request();

                    Response delete = solicitud.delete();

                    String responseJson = delete.readEntity(String.class);
                    switch (delete.getStatus()) {
                        case 200:
                            res = "La Factura ha sido Eliminada";
                            break;
                        default:
                            res = "Error";
                            break;
                    }
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
                frmSubmenu.frameDetalleFactura.dispose();
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();
                    WebTarget target = client.target(URL + "/id/"+txtID.getText());
                    //Invocation.Builder solicitud =target.queryParam("id",1).request();
                    Invocation.Builder solicitud =target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Factura data = new Gson().fromJson(responseJson, Factura.class);
                    switch (get.getStatus()) {
                        case 200:
                            txtID.setText(String.valueOf(data.getId()));
                            txtCliente.setText(String.valueOf(data.getCliente()));
                            txtEmpleado.setText(String.valueOf(data.getEmpleado()));
                            txtTotal.setText(String.valueOf(data.getTotal()));
                            txtCAI.setText(String.valueOf(data.getCAI()));
                            txtFecha.setText(data.getFecha());
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    //JOptionPane.showMessageDialog(null,res);
                }
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblDatos.getSelectedRow();
                txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtCliente.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtEmpleado.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtTotal.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                txtCAI.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                txtFecha.setText(modelo.getValueAt(filaSeleccionada,5).toString());

            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LlenarDatosFacturas();
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();
                    WebTarget target = client.target(URL + "");
                    Invocation.Builder solicitud =target.request();
                    Factura factura = new Factura();
                    factura.setId(Integer.parseInt(txtID.getText()));
                    factura.setCliente(Integer.parseInt(txtCliente.getText()));
                    factura.setEmpleado(Integer.parseInt(txtEmpleado.getText()));
                    factura.setCAI(Integer.parseInt(txtCAI.getText()));
                    factura.setTotal(Double.parseDouble(txtTotal.getText()));
                    factura.setFecha(txtFecha.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(factura);

                    Response put = solicitud.put(Entity.json(jsonString));


                    String responseJson = put.readEntity(String.class);
                    Factura data = new Gson().fromJson(responseJson, Factura.class);
                    switch (put.getStatus()) {
                        case 200:
                            res = "Factura Actualizada";
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();
                    WebTarget target = client.target(URL + "/addFactura");
                    Invocation.Builder solicitud =target.request();
                    Factura factura = new Factura();
                    factura.setCliente(Integer.parseInt(txtCliente.getText()));
                    factura.setEmpleado(Integer.parseInt(txtEmpleado.getText()));
                    factura.setCAI(Integer.parseInt(txtCAI.getText()));
                    factura.setTotal(Double.parseDouble(txtTotal.getText()));
                    factura.setFecha(txtFecha.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(factura);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    switch (post.getStatus()) {
                        case 201:
                            Factura data = new Gson().fromJson(responseJson, Factura.class);
                            res = "Factura Registrada Exitosamente";
                            break;
                        case 500:
                            RestApiError errorApi = new Gson().fromJson(responseJson, RestApiError.class);
                            res = errorApi.getErrorDetails();
                        default:
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                    JOptionPane.showMessageDialog(null,res);
                }
            }
        });
    }

    private void iniciarFacturas() {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Cliente");
        modelo.addColumn("Empleado");
        modelo.addColumn("CAI");
        modelo.addColumn("Total");
        modelo.addColumn("Fecha");
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Factura> data = new Gson().fromJson(responseJson, new TypeToken<List<Factura>>(){}.getType());
            switch (get.getStatus()) {
                case 200:
                    LeerDatosFacturas(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch (Exception e1){
            res="Error, no se cual es";
        }
    }
    public void LeerDatosFacturas(List<Factura> facturas){
        try {
            modelo.setRowCount(0);
            for (Factura factura1: facturas) {
                Object[] registroLeido = {factura1.getId(), factura1.getCliente(), factura1.getEmpleado(),factura1.getCAI(),factura1.getTotal(), factura1.getFecha()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    public void LlenarDatosFacturas(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "");
        Invocation.Builder solicitud = target.request();
        Response get = solicitud.get();
        String responseJson = get.readEntity(String.class);
        List<Factura> data = new Gson().fromJson(responseJson, new TypeToken<List<Factura>>(){}.getType());
        LeerDatosFacturas(data);
    }
    String res = "";
    String URL = "http://192.168.0.6:8080/api/v1/Factura";
    public static void main(String[] args) {
        frameDetalleFactura.setContentPane(new frmSubmenu().jpaPrincipal);
        frameDetalleFactura.setResizable(false);
        frameDetalleFactura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameDetalleFactura.pack();
        frameDetalleFactura.setLocationRelativeTo(null);
        frameDetalleFactura.setVisible(true);
    }
}
