package gui.MenuLogin.MenuPrincipal.Clientes;
import com.google.gson.Gson;
import javax.swing.*;
import java.awt.event.*;
import javax.ws.rs.client.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.core.Response;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import gui.MenuLogin.sub.frmSubmenu;
import recursos.clases.Clientes;
import recursos.clases.DetalleFactura;
import recursos.clases.RestApiError;

public class frmClientes {
    public JPanel jpaClientes;
    private JPanel jpaSuperior;
    private JPanel jpaIzquierdo;
    private JPanel jpaDerecho;
    private JPanel jpaInferior;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnListar;
    private JButton btnAtras;
    private JLabel lvlNombre;
    private JLabel lvlId;
    private JLabel lvlFechaNacimiento;
    private JLabel lvlTelefono;
    private JTextField txtNombre;
    private JTextField txtID;
    private JTextField txtFechaNacimiento;
    private JTextField txtTelefono;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    private JTextField txtCorreo;
    private JTextField txtDNI;
    private JLabel lvlDNI;
    private JLabel lvlCorreo;
    public static JFrame frameMenuClientes = new JFrame("Menu Clientes");

    DefaultTableModel modelo;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public frmClientes() {
        iniciarClientes();
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmSubmenu.frameMenuClientes.dispose();
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                Client client= ClientBuilder.newClient();

                WebTarget target = client.target(URL + "/addCliente");

                Invocation.Builder solicitud =target.request();
                Clientes clientes1 = new Clientes();
                clientes1.setName(txtNombre.getText());

                clientes1.setTelefono(Integer.parseInt(txtTelefono.getText()));

                clientes1.setDNI(Integer.parseInt(txtDNI.getText()));
                clientes1.setCorreo(txtCorreo.getText());
                clientes1.setFechaNacimiento(txtFechaNacimiento.getText());
                Gson gson = new Gson();
                String jsonString = gson.toJson(clientes1);
                Response post = solicitud.post(Entity.json(jsonString));
                String responseJson = post.readEntity(String.class);
                    switch (post.getStatus()) {
                    case 201:
                        Clientes data = new Gson().fromJson(responseJson, Clientes.class);
                        res = "Cliente Registrado Exitosamente";
                        break;
                    case 500:
                        RestApiError errorApi = new Gson().fromJson(responseJson, RestApiError.class);
                        res = errorApi.getErrorDetails();
                    default:
                        break;
                }
                    LlenarDatosClientes();
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
                try {
                    try{
                        Client client= ClientBuilder.newClient();

                        WebTarget target = client.target(URL + "");

                        Invocation.Builder solicitud =target.request();

                        Clientes clientes = new Clientes();
                        clientes.setId(Long.parseLong(txtID.getText()));
                        clientes.setName(txtNombre.getText());
                        clientes.setTelefono(Integer.parseInt(txtTelefono.getText()));
                        clientes.setDNI(Integer.parseInt(txtDNI.getText()));
                        clientes.setCorreo(txtCorreo.getText());
                        clientes.setFechaNacimiento(txtFechaNacimiento.getText());
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(clientes);

                        Response put = solicitud.put(Entity.json(jsonString));


                        String responseJson = put.readEntity(String.class);
                        Clientes data = new Gson().fromJson(responseJson, Clientes.class);

                        res = data.getName();
                        switch (put.getStatus()) {
                            case 200:
                                res = data.getName();
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
                }catch (Exception ex){
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

                    JOptionPane.showMessageDialog(null,delete.getStatus());

                    switch (delete.getStatus()) {
                        case 200:
                            res = "Cliente Eliminado Exitosamente";
                            break;
                        default:
                            res = "Error";
                            break;
                    }
                    LlenarDatosClientes();
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
                    Clientes data = new Gson().fromJson(responseJson, Clientes.class);
                    //res = data.getName();
                    switch (get.getStatus()) {
                        case 200:
                            txtID.setText(String.valueOf(data.getId()));
                            txtNombre.setText(data.getName());
                            txtTelefono.setText(String.valueOf(data.getTelefono()));
                            txtCorreo.setText(data.getCorreo());
                            txtDNI.setText(String.valueOf(data.getDNI()));
                            txtFechaNacimiento.setText(data.getFechaNacimiento());
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
                LlenarDatosClientes();
            }
        });

        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblDatos.getSelectedRow();
                txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtNombre.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtTelefono.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtDNI.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                txtCorreo.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                txtFechaNacimiento.setText(modelo.getValueAt(filaSeleccionada,5).toString());

            }
        });
    }

    private void iniciarClientes() {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("DNI");
        modelo.addColumn("Correo");
        modelo.addColumn("Fecha de Nacimiento");
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Clientes> data = new Gson().fromJson(responseJson, new TypeToken<List<Clientes>>(){}.getType());
            switch (get.getStatus()) {
                case 200:
                    LeerDatosClientes(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch (Exception e1){
            res="Error, no se cual es";
        }
    }
    public void LeerDatosClientes(List<Clientes> clientes){
        try {
            modelo.setRowCount(0);
            for (Clientes clientes1: clientes) {
             Object[] registroLeido = {clientes1.getId(), clientes1.getName(), clientes1.getTelefono(), clientes1.getDNI(), clientes1.getCorreo(), clientes1.getFechaNacimiento()};
            modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    public void LlenarDatosClientes(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "");
        Invocation.Builder solicitud = target.request();
        Response get = solicitud.get();
        String responseJson = get.readEntity(String.class);
        List<Clientes> data = new Gson().fromJson(responseJson, new TypeToken<List<Clientes>>(){}.getType());
        LeerDatosClientes(data);
    }
    String res = "";
    String URL = "http://192.168.0.6:8080/api/v1/Clientes";
    public static void main(String[] args) {
        frameMenuClientes.setContentPane(new frmClientes().jpaClientes);
        frameMenuClientes.setResizable(false);
        frameMenuClientes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMenuClientes.pack();
        frameMenuClientes.setLocationRelativeTo(null);
        frameMenuClientes.setVisible(true);
    }
}
