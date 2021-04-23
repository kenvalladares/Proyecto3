package gui.empleados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gui.MenuLogin.sub.frmSubmenu;
import recursos.clases.Empleados;
import recursos.clases.Mesas;
import recursos.clases.RestApiError;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmEmpleados {
    public JPanel jpaPrincipal;
    private JPanel jpaEmpleados;
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
    private JLabel lvlPuesto;
    private JLabel lvlSueldo;
    private JLabel lvlDNI;
    private JLabel lvlTelefono;
    private JPanel jpaDerecho;
    private JTextField txtNombre;
    private JTextField txtID;
    private JTextField txtPuesto;
    private JTextField txtSueldo;
    private JTextField txtTelefono;
    private JTextField txtDNI;
    private JPanel jpaInferior;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    DefaultTableModel modelo;
    public static JFrame frmMenuEmpleados = new JFrame("Menu Empleados");

    public frmEmpleados() {
        iniciarEmpleados();
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmSubmenu.frmMenuEmpleados.dispose();
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();
                    WebTarget target = client.target(URL + "/addEmpleado");
                    Invocation.Builder solicitud =target.request();
                    Empleados empleados = new Empleados();
                    empleados.setName(txtNombre.getText());
                    empleados.setPuesto(txtPuesto.getText());
                    empleados.setSueldo(Double.parseDouble(txtSueldo.getText()));
                    empleados.setDNI(Integer.parseInt(txtDNI.getText()));
                    empleados.setTelefono(Integer.parseInt(txtTelefono.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(empleados);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    switch (post.getStatus()) {
                        case 201:
                            Empleados data = new Gson().fromJson(responseJson, Empleados.class);
                            res = "Empleado Registrado Exitosamente";
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
                    Empleados empleados = new Empleados();
                    empleados.setId(Long.parseLong(txtID.getText()));
                    empleados.setName(txtNombre.getText());
                    empleados.setPuesto(txtPuesto.getText());
                    empleados.setSueldo(Double.parseDouble(txtSueldo.getText()));
                    empleados.setDNI(Integer.parseInt(txtDNI.getText()));
                    empleados.setTelefono(Integer.parseInt(txtTelefono.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(empleados);

                    Response put = solicitud.put(Entity.json(jsonString));


                    String responseJson = put.readEntity(String.class);
                    Empleados data = new Gson().fromJson(responseJson, Empleados.class);
                    switch (put.getStatus()) {
                        case 200:
                            res = "Empleado Actualizado";
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
                            res = "Empleado Eliminado Exitosamente";
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
                    Empleados data = new Gson().fromJson(responseJson, Empleados.class);
                    switch (get.getStatus()) {
                        case 200:
                            txtID.setText(String.valueOf(data.getId()));
                            txtNombre.setText(data.getName());
                            txtPuesto.setText(data.getPuesto());
                            txtSueldo.setText(String.valueOf(data.getSueldo()));
                            txtDNI.setText(String.valueOf(data.getDNI()));
                            txtTelefono.setText(String.valueOf(data.getTelefono()));
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
                LlenarDatosEmpleados();
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblDatos.getSelectedRow();
                txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtNombre.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtPuesto.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtSueldo.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                txtDNI.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                txtTelefono.setText(modelo.getValueAt(filaSeleccionada,5).toString());

            }
        });
    }
    private void iniciarEmpleados() {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre:");
        modelo.addColumn("Puesto");
        modelo.addColumn("Sueldo");
        modelo.addColumn("DNI");
        modelo.addColumn("Telefono");
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Empleados> data = new Gson().fromJson(responseJson, new TypeToken<List<Empleados>>(){}.getType());
            switch (get.getStatus()) {
                case 200:
                    LeerDatosEmpleados(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch (Exception e1){
            res="Error, no se cual es";
        }
    }
    public void LeerDatosEmpleados(List<Empleados> empleados){
        try {
            modelo.setRowCount(0);
            for (Empleados empleados1: empleados) {
                Object[] registroLeido = {empleados1.getId(),empleados1.getName(),empleados1.getPuesto(),empleados1.getSueldo(),empleados1.getDNI(),empleados1.getTelefono()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    public void LlenarDatosEmpleados(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "");
        Invocation.Builder solicitud = target.request();
        Response get = solicitud.get();
        String responseJson = get.readEntity(String.class);
        List<Empleados> data = new Gson().fromJson(responseJson, new TypeToken<List<Empleados>>(){}.getType());
        LeerDatosEmpleados(data);
    }
    String res = "";
    String URL = "http://192.168.0.6:8080/api/v1/Empleados";
    public static void main(String[] args) {
        frmMenuEmpleados.setContentPane(new frmEmpleados().jpaPrincipal);
        frmMenuEmpleados.setResizable(false);
        frmMenuEmpleados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmMenuEmpleados.pack();
        frmMenuEmpleados.setLocationRelativeTo(null);
        frmMenuEmpleados.setVisible(true);
    }
}
