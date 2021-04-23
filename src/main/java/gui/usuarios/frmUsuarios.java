package gui.usuarios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gui.MenuLogin.MenuPrincipal.Clientes.frmClientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import gui.MenuLogin.sub.frmSubmenu;
import recursos.clases.Clientes;
import recursos.clases.RestApiError;
import recursos.clases.Usuarios;

public class frmUsuarios {
    public JPanel jpaUsuarios;
    private JPanel jpaSuperior;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnAtras;
    private JButton btnBuscar;
    private JPanel jpaIzquierdo;
    private JLabel lvlUsuario;
    private JLabel lvlId;
    private JLabel lvlPassword;
    private JLabel lvlNombre;
    private JLabel lvlCorreo;
    private JPanel jpaDerecho;
    private JTextField txtUsuarios;
    private JTextField txtID;
    private JTextField txtPassword;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JPanel jpaInferior;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    public JPanel jpaPrincipal;
    public static JFrame frmUsuarios = new JFrame("Menu Usuarios");
    DefaultTableModel modelo;
    public frmUsuarios() {
        iniciarUsuarios();
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmSubmenu.frmUsuarios.dispose();
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();
                    WebTarget target = client.target(URL + "/addUsuario");
                    Invocation.Builder solicitud =target.request();
                    Usuarios usuarios = new Usuarios();
                    usuarios.setUsuario(txtUsuarios.getText());
                    usuarios.setPassword(txtPassword.getText());
                    usuarios.setNombre(txtNombre.getText());
                    usuarios.setCorreo(txtCorreo.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(usuarios);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    switch (post.getStatus()) {
                        case 201:
                            Usuarios data = new Gson().fromJson(responseJson, Usuarios.class);
                            res = "Usuario Registrado Exitosamente";
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
                            res = "Usuario Eliminado Exitosamente";
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
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblDatos.getSelectedRow();
                txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtUsuarios.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtPassword.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtNombre.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                txtCorreo.setText(modelo.getValueAt(filaSeleccionada,4).toString());
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
                    Usuarios data = new Gson().fromJson(responseJson, Usuarios.class);
                    switch (get.getStatus()) {
                        case 200:
                            txtID.setText(String.valueOf(data.getId()));
                            txtUsuarios.setText(data.getUsuario());
                            txtPassword.setText(data.getPassword());
                            txtNombre.setText(data.getNombre());
                            txtCorreo.setText(data.getCorreo());
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
                LlenarDatosUsuarios();
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud =target.request();

                    Usuarios usuarios = new Usuarios();
                    usuarios.setId(Integer.parseInt(txtID.getText()));
                    usuarios.setUsuario(txtUsuarios.getText());
                    usuarios.setPassword(txtPassword.getText());
                    usuarios.setCorreo(txtCorreo.getText());
                    usuarios.setNombre(txtNombre.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(usuarios);

                    Response put = solicitud.put(Entity.json(jsonString));


                    String responseJson = put.readEntity(String.class);
                    Usuarios data = new Gson().fromJson(responseJson, Usuarios.class);
                    switch (put.getStatus()) {
                        case 200:
                            res = "Usuario Actualizado";
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
    }
    private void iniciarUsuarios() {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Usuario");
        modelo.addColumn("Password");
        modelo.addColumn("Nombre");
        modelo.addColumn("Correo");
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Usuarios> data = new Gson().fromJson(responseJson, new TypeToken<List<Usuarios>>(){}.getType());
            switch (get.getStatus()) {
                case 200:
                    LeerDatosUsuarios(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch (Exception e1){
            res="Error, no se cual es";
        }
    }
    public void LeerDatosUsuarios(List<Usuarios> usuarios){
        try {
            modelo.setRowCount(0);
            for (Usuarios usuarios1: usuarios) {
                Object[] registroLeido = {usuarios1.getId(),usuarios1.getUsuario(),usuarios1.getPassword(),usuarios1.getNombre(),usuarios1.getCorreo()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    public void LlenarDatosUsuarios(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "");
        Invocation.Builder solicitud = target.request();
        Response get = solicitud.get();
        String responseJson = get.readEntity(String.class);
        List<Usuarios> data = new Gson().fromJson(responseJson, new TypeToken<List<Usuarios>>(){}.getType());
        LeerDatosUsuarios(data);
    }
    String res = "";
    String URL = "http://192.168.0.6:8080/api/v1/Usuarios";
    public static void main(String[] args) {
        frmUsuarios.setContentPane(new frmUsuarios().jpaPrincipal);
        frmUsuarios.setResizable(false);
        frmUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmUsuarios.pack();
        frmUsuarios.setLocationRelativeTo(null);
        frmUsuarios.setVisible(true);
    }
}
