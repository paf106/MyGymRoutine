
package com.MyGymRoutine.myapp.data.model.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRegister {

    @SerializedName("idCliente")
    @Expose
    private int idCliente;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("apellidos")
    @Expose
    private String apellidos;
    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("contrasena")
    @Expose
    private String contrasena;
    @SerializedName("correoElectronico")
    @Expose
    private String correoElectronico;
    @SerializedName("fechaNacimiento")
    @Expose
    private String fechaNacimiento;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("peso")
    @Expose
    private double peso;
    @SerializedName("altura")
    @Expose
    private double altura;
    @SerializedName("frecuenciaDeporte")
    @Expose
    private String frecuenciaDeporte;
    @SerializedName("imagenRuta")
    @Expose
    private String imagenRuta;
    @SerializedName("patologias")
    @Expose
    private String patologias;
    @SerializedName("idPersonal")
    @Expose
    private int idPersonal;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseRegister() {
    }

    /**
     * 
     * @param apellidos
     * @param patologias
     * @param idPersonal
     * @param fechaNacimiento
     * @param peso
     * @param imagenRuta
     * @param nombre
     * @param idCliente
     * @param altura
     * @param usuario
     * @param contrasena
     * @param telefono
     * @param frecuenciaDeporte
     * @param correoElectronico
     */
    public ResponseRegister(int idCliente, String nombre, String apellidos, String usuario, String contrasena, String correoElectronico, String fechaNacimiento, String telefono, double peso, double altura, String frecuenciaDeporte, String imagenRuta, String patologias, int idPersonal) {
        super();
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.peso = peso;
        this.altura = altura;
        this.frecuenciaDeporte = frecuenciaDeporte;
        this.imagenRuta = imagenRuta;
        this.patologias = patologias;
        this.idPersonal = idPersonal;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ResponseRegister withIdCliente(int idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ResponseRegister withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public ResponseRegister withApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ResponseRegister withUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ResponseRegister withContrasena(String contrasena) {
        this.contrasena = contrasena;
        return this;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public ResponseRegister withCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
        return this;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public ResponseRegister withFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ResponseRegister withTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public ResponseRegister withPeso(double peso) {
        this.peso = peso;
        return this;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public ResponseRegister withAltura(double altura) {
        this.altura = altura;
        return this;
    }

    public String getFrecuenciaDeporte() {
        return frecuenciaDeporte;
    }

    public void setFrecuenciaDeporte(String frecuenciaDeporte) {
        this.frecuenciaDeporte = frecuenciaDeporte;
    }

    public ResponseRegister withFrecuenciaDeporte(String frecuenciaDeporte) {
        this.frecuenciaDeporte = frecuenciaDeporte;
        return this;
    }

    public String getImagenRuta() {
        return imagenRuta;
    }

    public void setImagenRuta(String imagenRuta) {
        this.imagenRuta = imagenRuta;
    }

    public ResponseRegister withImagenRuta(String imagenRuta) {
        this.imagenRuta = imagenRuta;
        return this;
    }

    public String getPatologias() {
        return patologias;
    }

    public void setPatologias(String patologias) {
        this.patologias = patologias;
    }

    public ResponseRegister withPatologias(String patologias) {
        this.patologias = patologias;
        return this;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public ResponseRegister withIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
        return this;
    }

}
