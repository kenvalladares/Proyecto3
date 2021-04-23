package gui.MenuLogin.MenuPrincipal.MenuRestaurante;

import com.google.gson.Gson;
import gui.MenuLogin.MenuPrincipal.MenuRestaurante.MenuComidas.Submenu.MenuEnsaladas.frmMenuEnsaladas;
import recursos.clases.Clientes;
import recursos.clases.Mesas;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMesas {
    private JPanel jpaPrincipal;
    public JPanel jpaMesas;
    private JPanel jpaSuperior;
    private JButton btnAñadir;
    private JButton btnInformacion;
    private JButton btnAtras;
    private JPanel jpaIzquierdo;
    private JRadioButton rbtMesaParaDos;
    private JRadioButton rbtMesaParaCuatro;
    private JRadioButton rbtMesaParaSeis;
    private JRadioButton rbtFamiliar;
    private JTextArea txaLista;
    public static JFrame frameMesas = new JFrame("Menu Mesas");

    public frmMesas() {
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmMenuRestaurante.frameMesas.dispose();
            }
        });
        btnAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client= ClientBuilder.newClient();
                    WebTarget target = client.target(URL + "/addMesa");
                    Invocation.Builder solicitud =target.request();
                    Mesas mesas = new Mesas();
                    if (rbtMesaParaDos.isSelected()){
                        mesas.setAsientos(2);
                        mesas.setEstado("Reservado");
                        mesas.setNumero(15);
                        mesas.setPiso(1);
                    }
                    if (rbtMesaParaCuatro.isSelected()){
                        mesas.setAsientos(4);
                        mesas.setEstado("Reservado");
                        mesas.setNumero(13);
                        mesas.setPiso(2);
                    }
                    if (rbtMesaParaSeis.isSelected()){
                        mesas.setAsientos(6);
                        mesas.setEstado("Reservado");
                        mesas.setNumero(20);
                        mesas.setPiso(2);
                    }
                    if (rbtFamiliar.isSelected()){
                        mesas.setAsientos(8);
                        mesas.setEstado("Reservado");
                        mesas.setNumero(10);
                        mesas.setPiso(3);
                    }
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(mesas);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    Mesas data = new Gson().fromJson(responseJson, Mesas.class);
                    JOptionPane.showMessageDialog(null,post.getStatus());

                    switch (post.getStatus()) {
                        case 201:
                            res = "Mesa añadida Exitosamente";
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
        btnInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Asientos = 0;
                String Estado ="";
                int Numero =0;
                int Piso = 0;
                String Descripcion ="";
                if (rbtMesaParaDos.isSelected()){
                    Asientos =2;
                    Estado ="Reservado";
                    Numero = 15;
                    Piso = 1;
                    Descripcion = "Mesa para Dos\n Estado:Libre\n Piso:1\n Asientos: 2" ;
                }
                if (rbtMesaParaCuatro.isSelected()){
                    Asientos =4;
                    Estado ="Reservado";
                    Numero = 13;
                    Piso = 2;
                    Descripcion = "Mesa para Cuatro\n Estado:Libre\n Piso:2\n Asientos: 4" ;
                }
                if (rbtMesaParaSeis.isSelected()){
                    Asientos =6;
                    Estado ="Reservado";
                    Numero = 20;
                    Piso = 2;
                    Descripcion = "Mesa para Seis\n Estado:Libre\n Piso:2\n Asientos: 6" ;
                }
                if (rbtFamiliar.isSelected()){
                    Asientos =8;
                    Estado ="Reservado";
                    Numero = 10;
                    Piso = 3;
                    Descripcion = "Mesa Familiar \n Estado:Libre\n Piso:3\n Asientos: 8 (Ampliable)" ;
                }
                txaLista.setText(Descripcion);
            }
        });
    }
    String res = "";
    String URL = "http://192.168.0.6:8080/api/v1/Mesas";
    public static void main(String[] args) {
        frameMesas.setContentPane(new frmMesas().jpaMesas);
        frameMesas.setResizable(false);
        frameMesas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMesas.pack();
        frameMesas.setLocationRelativeTo(null);
        frameMesas.setVisible(true);
    }
}
