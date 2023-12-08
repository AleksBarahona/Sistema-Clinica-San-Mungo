import DAO.CRUDCitas;
import DAO.CRUDDoctores;
import DAO.CRUDPacientes;
import Entidades.Citas;
import Entidades.Doctores;
import Entidades.Pacientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class VentanaSistema extends JFrame{
    private JPanel miPanelPrincipal;
    private JTabbedPane tabbedPane1;
    private JPanel panelPacientes;
    private JTextField txtMatricula;
    private JTextField txtNombrePac;
    private JTextField txtApellidoPac;
    private JComboBox cmbDia;
    private JComboBox cmbMes;
    private JComboBox cmbAnio;
    private JButton btnAgregarPac;
    private JButton btnBuscarPac;
    private JButton btnActualizarPac;
    private JButton btnBorrarPac;
    private JButton btnLimpiar;
    private JTextField txtFechaNac;
    private JPanel panelDoctores;
    private JButton btnLimpiarDoc;
    private JTextField txtIdDoc;
    private JTextField txtNombreDoc;
    private JTextField txtApellidoDoc;
    private JComboBox cmbEspecialidad;
    private JSpinner spnCons;
    private JButton btnAgregarDoc;
    private JButton btnBuscarDoc;
    private JButton btnActulizarDoc;
    private JButton btnBorrarDoc;
    private JPanel panelCitas;
    private JPanel panelDoc;
    private JButton btnLimpiarC;
    private JTextField txtIdCita;
    private JComboBox cmbPac;
    private JSpinner spnNumConsC;
    private JComboBox cmbDoc;
    private JComboBox cmbDiaC;
    private JComboBox cmbMesC;
    private JComboBox cmbAnioC;
    private JTextField txtFecha;
    private JTextArea txtMotivo;
    private JButton btnAgendar;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnCancelar;
    private JTextField txtNomPac;
    private JTextField txtNomDoc;
    private JPanel panelCheck;

    //Operaciones de botones
    public VentanaSistema() {

        //pacientes
        //limpiar formulario
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMatricula.setText("");
                txtNombrePac.setText("");
                txtApellidoPac.setText("");
                cmbDia.setSelectedIndex(0);
                cmbMes.setSelectedIndex(0);
                cmbAnio.setSelectedIndex(0);
                txtFechaNac.setText("");
            }
        });

        //agregar paciente
        btnAgregarPac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(verificarCampos(panelPacientes)){
                    try {
                        Date fecha =obtenerFecha((String) cmbDia.getSelectedItem(), cmbMes.getSelectedIndex(),
                                (String) cmbAnio.getSelectedItem());
                        Pacientes p = new Pacientes(txtMatricula.getText(), txtNombrePac.getText(), txtApellidoPac.getText(),
                                fecha);

                        //invocar metodo de insertar pacientes
                        CRUDPacientes crud = new CRUDPacientes();
                        crud.insertarPaciente(p);
                        JOptionPane.showMessageDialog(miPanelPrincipal, "Paciente agregado con exito",
                                "Clínica san mungo", JOptionPane.INFORMATION_MESSAGE);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(miPanelPrincipal, "Faltan campos por rellenar",
                            "Clinica san mungo", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        //buscar paciente
        btnBuscarPac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //buscar paciente
                CRUDPacientes crud = new CRUDPacientes();
                String matricula = txtMatricula.getText();
                Pacientes p = crud.buscarPacientePorMatricula(matricula);
                if (p == null) {
                    int respuesta =  JOptionPane.showConfirmDialog(miPanelPrincipal,
                            "No se encuentra paciente con matricula: " + matricula +
                                    "\nDesea dar de alta?","Paciente",
                            JOptionPane.YES_NO_OPTION);
                    if (respuesta == 0) {
                        //si quiere dar de alta a un paciente inexistente
                        btnAgregarPac.setEnabled(true);
                        txtNombrePac.requestFocus();
                    }else if (respuesta == 1){
                        //no quiere dar de alta
                        //metodo para limpiar formulario
                    }
                }else{
                    txtNombrePac.setText(p.getNombre());
                    txtApellidoPac.setText(p.getApellido());
                    txtFechaNac.setEnabled(true);
                    txtFechaNac.setText(String.valueOf(p.getFechaNac()));
                }
            }
        });
        //fin pacientes

        //doctores
        //limpiar
        btnLimpiarDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIdDoc.setText("");
                txtNombreDoc.setText("");
                txtApellidoDoc.setText("");
                cmbEspecialidad.setSelectedIndex(0);
                spnCons.setValue(0);
            }
        });

        //agregar doctor
        btnAgregarDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(verificarCampos(panelDoc)){
                    Doctores d = new Doctores(txtIdDoc.getText(), txtNombreDoc.getText(), txtApellidoDoc.getText(),
                            (String) cmbEspecialidad.getSelectedItem(), (Integer) spnCons.getValue());
                    //invocacion de metodo para agregar doctores
                    CRUDDoctores crud = new CRUDDoctores();
                    crud.agregardoctor(d);
                    JOptionPane.showMessageDialog(miPanelPrincipal, "Doctor agregado con exito",
                            "Clínica san mungo", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(miPanelPrincipal, "Faltan campos por rellenar",
                            "Clinica san mungo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //buscar doctor
        btnBuscarDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDDoctores crud = new CRUDDoctores();
                String id = txtIdDoc.getText();
                Doctores d = crud.buscarDoctorPorId(id);
                if (d == null) {
                    int respuesta =  JOptionPane.showConfirmDialog(miPanelPrincipal,
                            "No se encuentra doctor con ID: " + id +
                                    "\nDesea dar de alta?","Doctor",
                            JOptionPane.YES_NO_OPTION);
                    if (respuesta == 0) {
                        //si quiere dar de alta a un doctor inexistente
                        txtNombreDoc.requestFocus();
                    }else if (respuesta == 1){
                        //no quiere dar de alta
                        //metodo para limpiar formulario
                    }
                }else{
                    txtNombreDoc.setText(d.getNombre());
                    txtApellidoDoc.setText(d.getApellido());
                    cmbEspecialidad.setSelectedIndex(0);
                    spnCons.setValue(d.getNumeroConsultorio());
                }
            }
        });

        //fin doctores

        //Citas
        //mostrar matriculas y id
        //rellena combobox con matriculas de pacientes y doctores
        CRUDPacientes crud = new CRUDPacientes();
        CRUDDoctores crudD = new CRUDDoctores();
        for (Pacientes p : crud.leerArchivo()){
            cmbPac.addItem(p.getMatricula());
            txtNomPac.setText(p.getNombre());
        }
        for (Doctores d : crudD.leerArchivo()){
            cmbDoc.addItem(d.getId());
            txtNomDoc.setText(d.getNombre());
            spnNumConsC.setValue(d.getNumeroConsultorio());
        }

        //limpiar formulario
        btnLimpiarC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIdCita.setText("");
                spnNumConsC.setValue(0);
                txtMotivo.setText("");
                txtFecha.setText("");
            }
        });

        //agendar cita
        btnAgendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verificarCamposCitas(txtIdCita, txtMotivo)){
                    CRUDCitas crud = new CRUDCitas();
                    CRUDPacientes crudP = new CRUDPacientes();
                    CRUDDoctores crudD = new CRUDDoctores();
                    Doctores doctor = crudD.buscarDoctorPorId((String) cmbDoc.getSelectedItem());
                    Pacientes paciente =crudP.buscarPacientePorMatricula((String) cmbPac.getSelectedItem());

                    try {
                      Date fecha = obtenerFecha((String) cmbDiaC.getSelectedItem(), cmbMes.getSelectedIndex(),
                                (String) cmbAnioC.getSelectedItem());
                        Citas cita = new Citas(txtIdCita.getText(), doctor, paciente, txtMotivo.getText(), fecha);
                        crud.agendarCita(cita);
                        JOptionPane.showMessageDialog(miPanelPrincipal, "Cita agendada con exito",
                                "Clínica san mungo", JOptionPane.INFORMATION_MESSAGE);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                }else {
                    JOptionPane.showMessageDialog(miPanelPrincipal, "Faltan campos por rellenar",
                            "Clinica san mungo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //buscar cita

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDCitas crud = new CRUDCitas();
                CRUDPacientes crudP = new CRUDPacientes();
                CRUDDoctores crudD = new CRUDDoctores();
                String id = txtIdCita.getText();
                Citas cita = crud.buscarCitaPorId(id);
                spnCons.setValue(cita.getNumCons());
                txtFecha.setText(String.valueOf(cita.getFecha()));
                txtMotivo.setText(cita.getMotivo());
                cmbPac.setSelectedIndex(Integer.parseInt(cita.getPaciente().getMatricula()));
                cmbDoc.setSelectedIndex(Integer.parseInt(cita.getDoctor().getId()));
                txtNomPac.setText(cita.getPaciente().getNombre());
                txtNomDoc.setText(cita.getDoctor().getNombre());
            }
        });
    }

    public static void main(String[] args) {
        VentanaSistema v = new VentanaSistema();
        v.setContentPane(v.miPanelPrincipal);
        v.setSize(700,500);
        v.setTitle("Clinica San Mungo");
        v.setDefaultCloseOperation(EXIT_ON_CLOSE);
        v.setLocationRelativeTo(null);
        v.setVisible(true);
    }

    //metodo para conseguir fecha
    public Date obtenerFecha(String dia, int mes, String anio) throws ParseException {

        String fechaString = dia + "-" + (mes + 1) + "-" + anio;
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
         Date fecha = formato.parse(fechaString);
         return fecha;
    }

    //Metodo para verificar campos
    public boolean verificarCampos(JPanel panel) {
        Component[] componentes = panel.getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JTextField) {
                JTextField campo = (JTextField) componente;
                if (campo.getText().trim().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verificarCamposCitas(JTextField id, JTextArea txtMotivo){
        if ((!Objects.equals(id.getText(), "")) && (!Objects.equals(txtMotivo.getText(), ""))){
            return true;
        }else {
            return false;
        }
    }

}
