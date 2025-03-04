/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 30/10/17
* Ultima alteracao: 04/11/17
* Nome: Principal
* Funcao: Chamar Tela do Programa
***********************************************************************/

import view.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class Principal extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage palco) {
    
    TelaInicial.show(palco);//Chamando Tela do Programa
    
    /*********************************************
    * Metodo: setOnCloseRequest
    * Funcao: Finaliza o programa por completo ao Fechar
    * Parametros: EventHandler
    * Retorno: void
    *********************************************/
    palco.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent t) {
        t.consume();
        palco.close();
        System.exit(0);
      }
    });
    
  }//Fim start

}//Fim class