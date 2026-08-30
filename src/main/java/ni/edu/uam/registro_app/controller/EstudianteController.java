package ni.edu.uam.registro_app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ComboBox<String> combGenero;
    @FXML
    private ComboBox<String> combFacultad;
    @FXML
    private TableView<Estudiante> tbtablaRegistros;
    @FXML
    private TableColumn<Estudiante, String> colNombres;
    @FXML
    private TableColumn<Estudiante, String> colApellidos;
    @FXML
    private TableColumn<Estudiante, String> colCarrera;
    @FXML
    private TableColumn<Estudiante, String> colFacultad;




    public void initialize() {

        combGenero.getItems().addAll("Masculino", "Femenino", "Otro");

        combFacultad.getItems().addAll("UAM College", "Facultad de Odontología",
                "Facultad de Ciencias Médicas", "Facultad de Marketing, Diseño y Ciencias de la Comunicación",
                "Facultad de Ciencias Administrativas y Económicas",
                "Facultad de Ingeniería y Arquitectura",
                "Facultad de Ciencias Jurídicas, Humanidades y Relaciones Internacionales",
                "Language Center");

        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        colFacultad.setCellValueFactory(new PropertyValueFactory<>("facultad"));
    }

    private void cargarTabla(){
        ObservableList<Estudiante> datos = FXCollections.observableArrayList(
                listado.obternerRegistros()
        );

        tbtablaRegistros.setItems(datos);
    }


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
        String genero = combGenero.getValue();
        String facultad = combFacultad.getValue();
        agregarDatos(new Estudiante(nomre,apellido,carrera,fechaNa,beca, genero, facultad));
    }
    private void agregarDatos(Estudiante estudiante){

        listado.agregar(estudiante);
        cargarTabla();
        lblRegistro.setText("Registros Guardados: " + listado.obternerRegistros().size());

    }


}
