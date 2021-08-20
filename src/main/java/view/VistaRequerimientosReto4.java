package view;

import controller.ControladorRequerimientosReto4;
import model.vo.Requerimiento1;
import model.vo.Requerimiento2;
import model.vo.Requerimiento3;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VistaRequerimientosReto4 extends JFrame implements ActionListener {   

    static JTable  table = new JTable();
    JScrollPane sp;
    JButton btnLideresPorSalario;
    JButton btnProyectoPorTipo;
    JButton btnLideresPorNombre;

    FlowLayout flowLayout1 = new FlowLayout();
    public VistaRequerimientosReto4() {        
        super("Ejercicio GUI");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        btnLideresPorSalario = new JButton("Lideres por Salario");
        cp.add(btnLideresPorSalario);
        btnProyectoPorTipo = new JButton("Proyectos por tipo");
        cp.add(btnProyectoPorTipo);
        btnLideresPorNombre = new JButton("Lideres por nombre");
        cp.add(btnLideresPorNombre );  
        btnLideresPorSalario.addActionListener(this); 
        btnProyectoPorTipo.addActionListener(this);
        btnLideresPorNombre.addActionListener(this);        
        sp = new JScrollPane(table);
        cp.add(sp);               
    }
    public static final ControladorRequerimientosReto4 controlador = new ControladorRequerimientosReto4();
    
    public static Object[][] requerimiento1(){
        Object[][] object = new Object[0][0];;
        try{
            ArrayList<Requerimiento1> requerimiento1s = controlador.consultarRequerimiento_1();            
            int i = 0;
            object = new Object[requerimiento1s.size()][requerimiento1s.size()];     
            for (Requerimiento1 requerimiento1: requerimiento1s){
                object[i][0] = requerimiento1.getNombre();
                object[i][1] = requerimiento1.getPrimerApellido();
                object[i][2] = requerimiento1.getIdLider();
                object[i][3] = requerimiento1.getSalario(); 
                i++;                              
            }

        }catch(SQLException e){
            System.err.println("Ha ocurrido un error!"+e.getMessage());
        }
        return object;
    }

    public static Object[][] requerimiento2(){     
        Object[][] object = new Object[0][0];;
        try{
            ArrayList<Requerimiento2> requerimiento2s = controlador.consultarRequerimiento_2();
            int i = 0;
            object = new Object[requerimiento2s.size()][requerimiento2s.size()];  
            for (Requerimiento2 requerimiento2: requerimiento2s){
                object[i][0] = requerimiento2.getIdProyecto();
                object[i][1] = requerimiento2.getConstructora();
                object[i][2] = requerimiento2.getCiudad();
                object[i][3] = requerimiento2.getEstrato();
                i++; 
            }

        }catch(SQLException e){
            System.err.println(e);
        }
        return object;
    }

    public static Object[][] requerimiento3(){
        Object[][] object = new Object[0][0];
        try{
 
            ArrayList<Requerimiento3> requerimiento3s = controlador.consultarRequerimiento_3();
            int i = 0;
            object = new Object[requerimiento3s.size()][requerimiento3s.size()];  
              // El lider con Id %d se llama %s %s 
              for (Requerimiento3 requerimiento3: requerimiento3s){
                object[i][0] = requerimiento3.getIdLider();
                object[i][1] = requerimiento3.getNombre();
                object[i][2] = requerimiento3.getPrimerApellido();
                i++;
            }
        
        }catch(SQLException e){
            System.err.println(e);
        }
        return object;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnLideresPorSalario)
        {
            String[] colName = { "Nombre", "Primer Apellido", "Lider", "Salario"};
            table.setBounds(30,40,200,300);
            table = new JTable(requerimiento1(), colName);      
            System.out.println(("*** Proyectos por Tipo ***"));
            sp.setViewportView(table);  
        }

        if (e.getSource()==btnProyectoPorTipo)
        {
            String[] colName = { "Proyecto", "Constructora", "Ciudad", "Estrato"};
            table.setBounds(30,40,200,300);
            table = new JTable(requerimiento2(), colName);      
            System.out.println(("*** Proyectos por Tipo 2 ***"));
            sp.setViewportView(table);  
        }  

        if (e.getSource()==btnLideresPorNombre)
        {
            String[] colName = { "Lider", "Nombre", "Primer Apellido"};
            table.setBounds(30,40,200,300);
            table = new JTable(requerimiento3(), colName);      
            System.out.println(("*** Proyectos por Nombre ***"));
            sp.setViewportView(table);  
        }         
    }
}
