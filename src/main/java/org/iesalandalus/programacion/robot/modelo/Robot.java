package org.iesalandalus.programacion.robot.modelo;

import java.util.Objects;

public class Robot {

    private Zona zona;
    private Coordenada coordenada;
    private Orientacion orientacion;

    public Robot() {
        this.zona = new Zona();
        this.coordenada = this.zona.getCentro();
        this.orientacion = Orientacion.NORTE;
    }

    public Robot(Zona zona) {
        setZona(zona);
        setCoordenada(zona.getCentro());
        setOrientacion(Orientacion.NORTE);
    }

    public Robot(Zona zona, Orientacion orientacion) {
        setZona(zona);
        setCoordenada(zona.getCentro());
        setOrientacion(orientacion);
    }

    public Robot(Zona zona, Orientacion orientacion, Coordenada coordenada) {
        setZona(zona);
        setCoordenada(coordenada);
        setOrientacion(orientacion);
    }

    public Robot(Robot robot) {
        this.zona = robot.getZona();
        this.orientacion = robot.getOrientacion();
        this.coordenada = robot.getCoordenada();
    }

    public Zona getZona() {
        return zona;
    }

    private void setZona(Zona zona) {
        this.zona = zona;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    private void setCoordenada(Coordenada coordenada) {
        if (this.zona.pertenece(coordenada))
            this.coordenada = coordenada;
        else
            throw new IllegalArgumentException("Fuera del límite.");
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    private void setOrientacion(Orientacion orientacion) {
        this.orientacion = orientacion;
    }

    public void avanzar() throws RobotExcepcion {
        try {
            switch (this.orientacion) {
                case NORTE -> new Coordenada(this.coordenada.x(), this.coordenada.y() + 1);
                case ESTE -> new Coordenada(this.coordenada.x() + 1, this.coordenada.y());
                case NORESTE -> new Coordenada(this.coordenada.x() + 1, this.coordenada.y() + 1);
                case SUR -> new Coordenada(this.coordenada.x(), this.coordenada.y() - 1);
                case NOROESTE -> new Coordenada(this.coordenada.x() - 1, this.coordenada.y() + 1);
                case OESTE -> new Coordenada(this.coordenada.x() - 1, this.coordenada.y());
                case SUROESTE -> new Coordenada(this.coordenada.x() - 1, this.coordenada.y() - 1);
                case SURESTE -> new Coordenada(this.coordenada.x() + 1, this.coordenada.y() - 1);
            }
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