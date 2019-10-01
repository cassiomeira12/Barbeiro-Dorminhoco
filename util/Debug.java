/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 29/10/17
* Ultima alteracao: 29/10/17
* Nome: Debug
* Funcao: Imprimir no Terminal qualquer tipo de informacao para testes
***********************************************************************/

package util;


import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;


public class Debug extends Thread {

  private boolean imprimir;//Permitir a essa Thread imprimir ou nao
  private Queue<String> filaImpressao;//Fila para armazenar os textos
  //Semaphoro para impedir a Thread de imprimir quando nao houver mais texto
  private Semaphore controlador;

  /*********************************************
  * Metodo: Pegar Garfos
  * Funcao: Alterar estado para Faminto e pega os Garfos
  * Parametros: void 
  * Retorno: void
  *********************************************/
  public Debug(boolean imprimir) {
    this.imprimir = imprimir;//Permissao de adicionar texto na Fila
    this.filaImpressao = new LinkedList<String>();//Inicializando Fila
    this.controlador = new Semaphore(0);//Inicializnado Semaphoro
    this.start();//Iniciando esta Thread
  }

  public void run() {
    try {

      while (true) {
        this.controlador.acquire();//Trava caso nao houver texto na Fila
        System.out.println(filaImpressao.poll());//Imprimindo texto da Fila
      }

    } catch (InterruptedException e) {
      System.err.println(e.toString());
    }
  }

  /*********************************************
  * Metodo: Imprimir
  * Funcao: Armazenar na Fila o texto a ser impresso
  * Parametros: texto : String
  * Retorno: void
  *********************************************/
  public void imprimir(String texto) {
    if (imprimir) {//Verificando se pode adicionar a Fila
      this.filaImpressao.add(texto);//Adicionando a Fila
      this.controlador.release();//Liberando execucao da Thread
    }
  }

}//Fim class