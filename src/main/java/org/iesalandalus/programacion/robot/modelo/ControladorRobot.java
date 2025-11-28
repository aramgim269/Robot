package org.iesalandalus.programacion.robot.modelo;

import javax.naming.OperationNotSupportedException;

public class ControladorRobot  {

   private Robot robot;

    public ControladorRobot(Robot robot)  {

        if (robot == null) {
            throw new NullPointerException("El robot no puede ser nulo.");
        } else {
            this.robot = new Robot(robot);
        }
    }

    public Robot getRobot() {
        return new Robot(robot);
    }

    public void ejecutar(char comando) throws RobotExcepcion {

       if (comando == 'A' || comando =='a') {

           try {
               robot.avanzar();
           } catch (RobotExcepcion e) {
               throw new RobotExcepcion(e.getMessage());
           }

       } else if (comando == 'D' || comando == 'd') {

            robot.girarALaDerecha();

       } else if (comando == 'I' || comando == 'i') {

            robot.girarALaIzquierda();

       } else {
           throw new RobotExcepcion("Comando desconocido.");
       }
    }
}
