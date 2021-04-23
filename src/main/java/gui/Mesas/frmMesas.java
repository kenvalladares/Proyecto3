package gui.Mesas;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gui.empleados.frmEmpleados;

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
import recursos.clases.Mesas;
import recursos.clases.RestApiError;
import recursos.clases.Usuarios;

public class frmMesas {
    public JPanel jpaMesas;
    private JPanel jpaSuperior;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnAtras;
    private JButton btnBuscar;
    private JPanel jpaIzquierdo;
    private JLabel lvlAsientos;
    private JLabel lvlId;
    private JLabel lvlEstado;
    private JLabel lvlNumero;
    private JLabel lvlPiso;
    private JPanel jpaDerecho;
    private JTextField txtAsientos;
    private JTextField txtID;
    private JTextField txtEstado;
    private JTextField txtNumero;
    private JTextField txtPiso;
    private JPanel jpaInferior;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    private JPanel jpaPrincipal;
    public static JFrame frmMenuMesas = new JFrame("Menu Mesas");
    DefaultTableModel modelo;
    public frmMesas() {
        iniciarMesas();
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmSubmenu.frmMenuMesas.dispose();
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();
                    WebTarget target = client.target(URL + "/addMesa");
                    Invocation.Builder solicitud =target.request();
                    Mesas mesas = new Mesas();
                    mesas.setAsientos(Integer.parseInt(txtAsientos.getText()));
                    mesas.setEstado(txtEstado.getText());
                    mesas.setNumero(Integer.parseInt(txtNumero.getText()));
                    mesas.setPiso(Integer.parseInt(txtPiso.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(mesas);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    switch (post.getStatus()) {
                        case 201:
                            Mesas data = new Gson().fromJson(responseJson, Mesas.class);
                            res = "Mesa Registrada Exitosamente";
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
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LlenarDatosMesas();
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblDatos.getSelectedRow();
                txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtAsientos.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtEstado.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtNumero.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                txtPiso.setText(modelo.getValueAt(filaSeleccionada,4).toString());
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
                            res = "Mesa Eliminado Exitosamente";
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
                    Mesas data = new Gson().fromJson(responseJson, Mesas.class);
                    switch (get.getStatus()) {
                        case 200:
                            txtID.setText(String.valueOf(data.getId()));
                            txtAsientos.setText(String.valueOf(data.getAsientos()));
                            txtEstado.setText(data.getEstado());
                            txtNumero.setText(String.valueOf(data.getNumero()));
                            txtPiso.setText(String.valueOf(data.getPiso()));
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
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();

                    WebTarget target = client.target(URL + "");

                    Invocation.Builder solicitud =target.request();

                    Mesas mesas = new Mesas();
                    mesas.setId(Integer.parseInt(txtID.getText()));
                    mesas.setAsientos(Integer.parseInt(txtAsientos.getText()));
                    mesas.setEstado(txtEstado.getText());
                    mesas.setNumero(Integer.parseInt(txtNumero.getText()));
                    mesas.setPiso(Integer.parseInt(txtPiso.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(mesas);

                    Response put = solicitud.put(Entity.json(jsonString));


                    String responseJson = put.readEntity(String.class);
                    Mesas data = new Gson().fromJson(responseJson, Mesas.class);
                    switch (put.getStatus()) {
                        case 200:
                            res = "Mesas Actualizado";
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
    private void iniciarMesas() {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Id");
        modelo.addColumn("Asientos");
        modelo.addColumn("Estado");
        modelo.addColumn("Numero");
        modelo.addColumn("Piso");
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(URL + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Mesas> data = new Gson().fromJson(responseJson, new TypeToken<List<Mesas>>(){}.getType());
            switch (get.getStatus()) {
                case 200:
                    LeerDatosMesas(data);
                    break;
                default:
                    res = "Error";
                    break;
            }
        }catch (Exception e1){
            res="Error, no se cual es";
        }
    }
    public void LeerDatosMesas(List<Mesas> mesas){
        try {
            modelo.setRowCount(0);
            for (Mesas mesas1: mesas) {
                Object[] registroLeido = {mesas1.getId(),mesas1.getAsientos(),mesas1.getEstado(),mesas1.getNumero(),mesas1.getPiso()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){

        }
    }
    public void LlenarDatosMesas(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "");
        Invocation.Builder solicitud = target.request();
        Response get = solicitud.get();
        String responseJson = get.readEntity(String.class);
        List<Mesas> data = new Gson().fromJson(responseJson, new TypeToken<List<Mesas>>(){}.getType());
        LeerDatosMesas(data);
    }
    String res = "";
    String URL = "http://192.168.0.6:8080/api/v1/Mesas";
    public static void main(String[] args) {
        frmMenuMesas.setContentPane(new frmMesas().jpaMesas);
        frmMenuMesas.setResizable(false);
        frmMenuMesas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmMenuMesas.pack();
        frmMenuMesas.setLocationRelativeTo(null);
        frmMenuMesas.setVisible(true);
    }
}
