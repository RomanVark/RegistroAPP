package ni.edu.uam.registro_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.registro_app.dao.EstudianteDao;
import ni.edu.uam.registro_app.modelos.Estudiante;

import java.time.LocalDate;

public class EstudianteController {
    EstudianteDao listado = new EstudianteDao();
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtCarrera;
    @FXML
    private DatePicker dpfechaNac;
    @FXML
    private CheckBox chktieneBeca;
    @FXML
    private Label lblRegistro;

    @FXML
    protected void guardarOnClick(){
        leerDatos();

    }

    private void  leerDatos(){
        String nomre = txtNombres.getText();
        String apellido = txtApellidos.getText();
        String carrera = txtCarrera.getText();
        LocalDate fechaNa = dpfechaNac.getValue();
        Boolean beca = chktieneBeca.isSelected();
        agregarDatos(new Estudiante(nomre,apellido,carrera,fechaNa,beca));
    }
    private void agregarDatos(Estudiante estudiante){

        listado.agregar(estudiante);
        lblRegistro.setText("Registros Guardados: " + listado.obternerRegistros().size());

    }


}
