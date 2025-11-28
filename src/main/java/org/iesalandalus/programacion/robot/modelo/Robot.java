package org.iesalandalus.programacion.robot.modelo;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Robot {

    private Zona zona;
    private Coordenada coordenada;
    private Orientacion orientacion;

    public Robot() {
        this.zona = new Zona();
        setCoordenada(this.zona.getCentro());
        setOrientacion(Orientacion.NORTE);
    }

    public Robot(Zona zona) {
        this();
        setZona(zona);
        setCoordenada(zona.getCentro());
        setOrientacion(Orientacion.NORTE);
    }

    public Robot(Zona zona, Orientacion orientacion) {
        this();
        setZona(zona);
        setCoordenada(zona.getCentro());
        setOrientacion(orientacion);
    }

    public Robot(Zona zona, Orientacion orientacion, Coordenada coordenada) {
        this();
        setZona(zona);
        setCoordenada(coordenada);
        setOrientacion(orientacion);
    }

    public Robot(Robot robot) {
        //this();
        //setZona(robot.getZona());
        //setOrientacion(robot.getOrientacion());
        //setCoordenada(robot.getCoordenada());
        this();
        this.zona = robot.getZona();
        this.coordenada = robot.getCoordenada();
        this.orientacion = robot.orientacion;
    }

    public Zona getZona() {
        return new Zona(this.zona.ancho(), this.zona.alto());
    }

    private void setZona(Zona zona) {

        if (zona == null)
            throw new NullPointerException("La zona no puede ser null");
        this.zona = new Zona(zona.ancho(),zona.alto());
    }

    public Coordenada getCoordenada() {
        return new Coordenada(coordenada.x(), coordenada.y());
    }

    private void setCoordenada(Coordenada coordenada) {
       // if (this.zona.pertenece(coordenada))
            //this.coordenada = new Coordenada(coordenada.x(), coordenada.y());
        // else
        //    throw new IllegalArgumentException("Fuera del límite.");
        if (coordenada == null) {
            throw new NullPointerException("La coordenada no puede ser null");
        }
        this.coordenada = new Coordenada(coordenada.x(), coordenada.y());
    }


    public Orientacion getOrientacion() {
        return orientacion;
    }

    private void setOrientacion(Orientacion orientacion) {
        this.orientacion = orientacion;
    }

    public void avanzar() throws RobotExcepcion {
        Coordenada nuevaPosicion = null;
        try {
            switch (this.orientacion) {
                case NORTE -> nuevaPosicion = new Coordenada(this.coordenada.x(), this.coordenada.y() + 1);
                case ESTE -> nuevaPosicion = new Coordenada(this.coordenada.x() + 1, this.coordenada.y());
                case NORESTE -> nuevaPosicion = new Coordenada(this.coordenada.x() + 1, this.coordenada.y() + 1);
                case SUR -> nuevaPosicion = new  Coordenada(this.coordenada.x(), this.coordenada.y() - 1);
                case NOROESTE -> nuevaPosicion = new  Coordenada(this.coordenada.x() - 1, this.coordenada.y() + 1);
                case OESTE -> nuevaPosicion = new  Coordenada(this.coordenada.x() - 1, this.coordenada.y());
                case SUROESTE -> nuevaPosicion = new  Coordenada(this.coordenada.x() - 1, this.coordenada.y() - 1);
                case SURESTE -> nuevaPosicion = new  Coordenada(this.coordenada.x() + 1, this.coordenada.y() - 1);
            }
            setCoordenada(nuevaPosicion);
        } catch (IllegalArgumentException e) {
            throw new RobotExcepcion("Has llegado al limite de la zona");
        }
    }

    public void girarALaDerecha() {

        if (this.orientacion == Orientacion.NORTE) {
            setOrientacion(Orientacion.ESTE);
        } else if (this.orientacion == Orientacion.ESTE) {
            setOrientacion(Orientacion.SUR);
        } else if (this.orientacion == Orientacion.SUR) {
            setOrientacion(Orientacion.OESTE);
        } else if (this.orientacion == Orientacion.OESTE) {
            setOrientacion(Orientacion.NORTE);
        }
    }

    public void girarALaIzquierda() {

        if (this.orientacion == Orientacion.NORTE) {
            setOrientacion(Orientacion.OESTE);
        } else if (this.orientacion == Orientacion.OESTE) {
            setOrientacion(Orientacion.SUR);
        } else if (this.orientacion == Orientacion.SUR) {
            setOrientacion(Orientacion.ESTE);
        } else if (this.orientacion == Orientacion.ESTE) {
            setOrientacion(Orientacion.NORTE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return Objects.equals(zona, robot.zona) && Objects.equals(coordenada, robot.coordenada) && orientacion == robot.orientacion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zona, coordenada, orientacion);
    }

    @Override
    public String toString() {
        return "Robot{" +
                "zona=" + zona +
                ", coordenada=" + coordenada +
                ", orientacion=" + orientacion +
                '}';
    }
}