package gui.producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.*;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gui.MenuLogin.sub.frmSubmenu;
import recursos.clases.Clientes;
import recursos.clases.Producto;
import recursos.clases.Proveedores;
import recursos.clases.RestApiError;

public class frmProducto {
    public JPanel jpaPrincipal;
    private JPanel jpaProducto;
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
    private JLabel lvlProveedor;
    private JLabel lvlCantidad;
    private JLabel lvlPrecio;
    private JPanel jpaDerecho;
    private JTextField txtNombre;
    private JTextField txtID;
    private JTextField txtProveedor;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JPanel jpaInferior;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    public static JFrame frameMenuProducto = new JFrame("Menu Productos");
    DefaultTableModel modelo;

    public frmProducto() {
        iniciarProducto();
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmSubmenu.frameMenuProducto.dispose();
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();
                    WebTarget target = client.target(URL + "/addProducto");
                    Invocation.Builder solicitud =target.request();
                    Producto producto = new Producto();
                    producto.setName(txtNombre.getText());
                    producto.setProveedor(Integer.parseInt(txtProveedor.getText()));
                    producto.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(producto);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    switch (post.getStatus()) {
                        case 201:
                            Producto data = new Gson().fromJson(responseJson, Producto.class);
                            res = "Producto Registrada Exitosamente";
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

                    Producto producto = new Producto();
                    producto.setId(Long.parseLong(txtID.getText()));
                    producto.setName(txtNombre.getText());
                    producto.setProveedor(Integer.parseInt(txtProveedor.getText()));
                    producto.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(producto);

                    Response put = solicitud.put(Entity.json(jsonString));


                    String responseJson = put.readEntity(String.class);
                    Producto data = new Gson().fromJson(responseJson, Producto.class);

                    res = data.getName();
                    switch (put.getStatus()) {
                        case 200:
                            res = "Producto Actualizado";
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
                            res = "Producto Eliminado Exitosamente";
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                    LlenarDatosProducto();
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
                    Invocation.Builder solicitud =target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Producto data = new Gson().fromJson(responseJson, Producto.class);
                    switch (get.getStatus()) {
                        case 200:
                            txtNombre.setText(data.getName());
                            txtID.setText(String.valueOf(data.getId()));
                            txtProveedor.setText(String.valueOf(data.getProveedor()));
                            txtCantidad.setText(String.valueOf(data.getCantidad()));
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
                    //JOptionPane.showMessageDialog(null,res);
                }
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LlenarDatosProducto();
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblDatos.getSelectedRow();
                txtNombre.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtProveedor.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtCantidad.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                txtPrecio.setText(modelo.getValueAt(filaSeleccionada,4).toString());

            }
        });
    }
    private void iniciarProducto() {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Proveedor");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Producto> data = new Gson().fromJson(responseJson, new TypeToken<List<Producto>>(){}.getType());
            switch (get.getStatus()) {
                case 200:
                    LeerDatosProducto(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch (Exception e1){
            res="Error, no se cual es";
        }
    }
    public void LeerDatosProducto(List<Producto> productos){
        try {
            modelo.setRowCount(0);
            for (Producto producto: productos) {
                Object[] registroLeido = {producto.getId(),producto.getName(),producto.getProveedor(),producto.getCantidad(),producto.getPrecio()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    public void LlenarDatosProducto(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "");
        Invocation.Builder solicitud = target.request();
        Response get = solicitud.get();
        String responseJson = get.readEntity(String.class);
        List<Producto> data = new Gson().fromJson(responseJson, new TypeToken<List<Producto>>(){}.getType());
        LeerDatosProducto(data);
    }
    String res = "";
    String URL = "http://192.168.0.6:8080/api/v1/Producto";
    public static void main(String[] args) {
        frameMenuProducto.setContentPane(new frmProducto().jpaPrincipal);
        frameMenuProducto.setResizable(false);
        frameMenuProducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMenuProducto.pack();
        frameMenuProducto.setLocationRelativeTo(null);
        frameMenuProducto.setVisible(true);
    }
}
