package model.vo;

public class Requerimiento1 {

    private String nombre;
    private String primerApellido;
    private Integer IdLider;
    private Integer salario;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPrimerApellido() {
        return primerApellido;
    }
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    public Integer getIdLider() {
        return IdLider;
    }
    public void setIdLider(Integer idLider) {
        IdLider = idLider;
    }
    public Integer getSalario() {
        return salario;
    }
    public void setSalario(Integer salario) {
        this.salario = salario;
    }    
}
