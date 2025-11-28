package org.iesalandalus.programacion.robot.modelo;

import java.util.Objects;

public record Zona(int ancho, int alto) {

    public static final int ANCHO_MINIMO = 10;
    public static final int ANCHO_MAXIMO = 100;
    public static final int ALTO_MINIMO = 10;
    public static final int ALTO_MAXIMO = 100;

    public Zona {
        validarAncho(ancho);
        validarAlto(alto);
    }

    public Zona () {
        this(ANCHO_MINIMO, ALTO_MINIMO);
    }

    private void validarAncho (int ancho) {
        if (ancho < ANCHO_MINIMO || ancho > ANCHO_MAXIMO){
            throw new IllegalArgumentException("El valor del ancho no es válido.");
        }
    }

    private void validarAlto (int alto) {
        if (alto < ALTO_MINIMO || alto > ALTO_MAXIMO){
            throw new IllegalArgumentException("El valor del alto no es válido.");
        }
    }

    public Coordenada getCentro() {
        return new Coordenada(ancho/2, alto/2);
    }

    public boolean pertenece(Coordenada coordenada) {
        //Objects.requireNonNull(Coordenada, "La coordenada no puedes ser nula.");
       // return perteneceX(coordenada.x())) &&perteneceY(coordenada.y());
        boolean valor = false;
        if (perteneceX(coordenada.x()) && perteneceY(coordenada.y())) {
            valor = true;
        }
        return valor;
    }

    private boolean perteneceX( int x) {
        boolean valor = false;
        if (x <= ancho && x >= 0) {
            valor = true;
        }
        return valor;
    }

    private boolean perteneceY( int y){
        boolean valor = false;
        if (y <= alto && y >= 0) {
            valor = true;
        }
        return valor;
    }
}
