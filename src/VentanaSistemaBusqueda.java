import DAO.CRUDCitas;
import DAO.CRUDDoctores;
import DAO.CRUDPacientes;
import Entidades.Citas;
import Entidades.Doctores;
import Entidades.Pacientes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSistemaBusqueda extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panelPacientes;
    private JButton btnAgregarPac;
    private JButton btnBuscarPac;
    private JButton btnActualizarPac;
    private JButton btnBorrarPac;
    private JTextField txtMatricula;
    private JTextField txtNombrePac;
    private JTextField txtApellidoPac;
    private JComboBox cmbDia;
    private JComboBox cmbMes;
    private JComboBox cmbAnio;
    private JTextField txtFechaNac;
    private JButton btnLimpiar;
    private JPanel panelDoctores;
    private JButton btnAgregarDoc;
    private JButton btnBuscarDoc;
    private JButton btnActulizarDoc;
    private JButton btnBorrarDoc;
    private JButton btnLimpiarDoc;
    private JPanel panelDoc;
    private JTextField txtIdDoc;
    private JTextField txtNombreDoc;
    private JTextField txtApellidoDoc;
    private JComboBox cmbEspecialidad;
    private JSpinner spnCons;
    private JPanel panelCitas;
    private JButton btnLimpiarC;
    private JPanel panelCheck;
    private JTextField txtIdCita;
    private JComboBox cmbPac;
    private JSpinner spnNumConsC;
    private JComboBox cmbDoc;
    private JComboBox cmbDiaC;
    private JComboBox cmbMesC;
    private JTextField txtFecha;
    private JTextArea txtMotivo;
    private JTextField txtNomPac;
    private JTextField txtNomDoc;
    private JComboBox cmbAnioC;
    private JButton btnAgendar;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnCancelar;
    private JPanel miPanelPrincipal;



    public VentanaSistemaBusqueda() {

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
        btnBuscarPac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //buscar paciente
                CRUDPacientes crud = new CRUDPacientes();
                String matricula = txtMatricula.getText();
                Pacientes p = crud.buscarPacientePorMatricula(matricula);
                txtNombrePac.setText(p.getNombre());
                txtApellidoPac.setText(p.getApellido());
                txtFechaNac.setEnabled(true);
                txtFechaNac.setText(String.valueOf(p.getFechaNac()));
            }
        });

        //doctores
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

        btnBuscarDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDDoctores crud = new CRUDDoctores();
                String id = txtIdDoc.getText();
                Doctores d = crud.buscarDoctorPorId(id);
                    txtNombreDoc.setText(d.getNombre());
                    txtApellidoDoc.setText(d.getApellido());
                    cmbEspecialidad.setSelectedIndex(0);
                    spnCons.setValue(d.getNumeroConsultorio());
            }
        });

        //limpiar
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
                txtNomPac.setText(cita.getPaciente().getNombre());
                txtNomDoc.setText(cita.getDoctor().getNombre());
            }
        });
    }

    public static void main(String[] args) {
        VentanaSistemaBusqueda v = new VentanaSistemaBusqueda();
        v.setContentPane(v.miPanelPrincipal);
        v.setSize(700,500);
        v.setTitle("Clinica San Mungo");
        v.setDefaultCloseOperation(EXIT_ON_CLOSE);
        v.setLocationRelativeTo(null);
        v.setVisible(true);
    }
}
