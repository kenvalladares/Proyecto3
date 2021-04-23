package gui.proveedores;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gui.MenuLogin.MenuPrincipal.Clientes.frmClientes;
import gui.MenuLogin.sub.frmSubmenu;
import recursos.clases.Mesas;
import recursos.clases.Proveedores;
import recursos.clases.RestApiError;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.*;
import java.util.List;

public class frmProveedores {
    private JPanel jpaPrincipal;
    public JPanel jpaProveedores;
    private JPanel jpaSuperior;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnAtras;
    private JButton btnBuscar;
    private JPanel jpaIzquierdo;
    private JLabel lvlNombre;
    private JLabel lvlId;
    private JLabel lvlProducto;
    private JLabel lvlFechaExp;
    private JLabel lvlFechaElaboracion;
    private JLabel lvlPrecio;
    private JPanel jpaDerecho;
    private JTextField txtNombre;
    private JTextField txtID;
    private JTextField txtProducto;
    private JTextField txtFechaExp;
    private JTextField txtPrecio;
    private JTextField txtFechaElaboracion;
    private JPanel jpaInferior;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    public static JFrame frameMenuProveedores = new JFrame("Menu Proveedores");
    DefaultTableModel modelo;

    public frmProveedores() {
        iniciarProveedores();
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmSubmenu.frameMenuProveedores.dispose();
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();
                    WebTarget target = client.target(URL + "/addProveedor");
                    Invocation.Builder solicitud =target.request();
                    Proveedores proveedores = new Proveedores();
                    proveedores.setName(txtNombre.getText());
                    proveedores.setProducto(txtProducto.getText());
                    proveedores.setFechaExp(txtFechaExp.getText());
                    proveedores.setFechaElaboracion(txtFechaElaboracion.getText());
                    proveedores.setPrecio(Double.parseDouble(txtPrecio.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(proveedores);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    switch (post.getStatus()) {
                        case 201:
                            Proveedores data = new Gson().fromJson(responseJson, Proveedores.class);
                            res = "Proveedores Registrada Exitosamente";
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
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud =target.request();

                    Proveedores proveedores = new Proveedores();
                    proveedores.setId(Integer.parseInt(txtID.getText()));
                    proveedores.setName(txtNombre.getText());
                    proveedores.setProducto(txtProducto.getText());
                    proveedores.setFechaExp(txtFechaExp.getText());
                    proveedores.setFechaElaboracion(txtFechaElaboracion.getText());
                    proveedores.setPrecio(Double.parseDouble(txtPrecio.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(proveedores);

                    Response put = solicitud.put(Entity.json(jsonString));


                    String responseJson = put.readEntity(String.class);
                    Mesas data = new Gson().fromJson(responseJson, Mesas.class);
                    switch (put.getStatus()) {
                        case 200:
                            res = "Proveedor Actualizado";
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
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "/delete/"+ txtID.getText());

                    Invocation.Builder solicitud =target.request();

                    Response delete = solicitud.delete();

                    String responseJson = delete.readEntity(String.class);
                    switch (delete.getStatus()) {
                        case 200:
                            res = "Proveedor Eliminado Exitosamente";
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
                    Proveedores data = new Gson().fromJson(responseJson, Proveedores.class);
                    switch (get.getStatus()) {
                        case 200:
                            txtID.setText(String.valueOf(data.getId()));
                            txtNombre.setText(data.getName());
                            txtProducto.setText(data.getProducto());
                            txtFechaExp.setText(data.getFechaExp());
                            txtFechaElaboracion.setText(data.getFechaElaboracion());
                            txtPrecio.setText(String.valueOf(data.getPrecio()));
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                }catch(Exception e1){
                    res = e.toString();
                }
                finally {
                   // JOptionPane.showMessageDialog(null,res);
                }
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LlenarDatosProveedores();
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblDatos.getSelectedRow();
                txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtNombre.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtProducto.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtFechaExp.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                txtFechaElaboracion.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                txtPrecio.setText(modelo.getValueAt(filaSeleccionada,5).toString());

            }
        });
    }
    private void iniciarProveedores() {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Producto");
        modelo.addColumn("FechaExp");
        modelo.addColumn("FechaElaboracion");
        modelo.addColumn("Precio");

        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Proveedores> data = new Gson().fromJson(responseJson, new TypeToken<List<Proveedores>>(){}.getType());
            switch (get.getStatus()) {
                case 200:
                    LeerDatosProveedores(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch (Exception e1){
            res="Error, no se cual es";
        }
    }
    public void LeerDatosProveedores(List<Proveedores> proveedores){
        try {
            modelo.setRowCount(0);
            for (Proveedores proveedores1: proveedores) {
                Object[] registroLeido = {proveedores1.getId(),proveedores1.getName(),proveedores1.getProducto(),proveedores1.getFechaExp(),proveedores1.getFechaElaboracion(),proveedores1.getPrecio()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    public void LlenarDatosProveedores(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "");
        Invocation.Builder solicitud = target.request();
        Response get = solicitud.get();
        String responseJson = get.readEntity(String.class);
        List<Proveedores> data = new Gson().fromJson(responseJson, new TypeToken<List<Proveedores>>(){}.getType());
        LeerDatosProveedores(data);
    }
    String res = "";
    String URL = "http://192.168.0.6:8080/api/v1/Proveedores";
    public static void main(String[] args) {
        frameMenuProveedores.setContentPane(new frmProveedores().jpaProveedores);
        frameMenuProveedores.setResizable(false);
        frameMenuProveedores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMenuProveedores.pack();
        frameMenuProveedores.setLocationRelativeTo(null);
        frameMenuProveedores.setVisible(true);
    }
}
