/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 30/10/17
* Ultima alteracao: 04/11/17
* Nome: 
* Funcao: 
***********************************************************************/

package model;


import java.util.concurrent.Semaphore;
import util.Debug;


public class Buffer {

  //Semaforos para controlar as Thread Cliente e Barbeiro
  private Semaphore mutex = new Semaphore(1);//Semaphoro Mutex
  private Semaphore semaphoreClientes = new Semaphore(0);//Semaphoro dos Clientes
  private Semaphore semaphoreBarbeiro = new Semaphore(0);//Semaphoro do Barbeiro
  private Semaphore interface1 = new Semaphore(0);//Semaphoro para controlar animacao na Interface
  private Semaphore interface2 = new Semaphore(0);//Semaphoro para controlar animacao na Interface
  public static int MAX_CLIENTES = 4;//Numero maximo de Clientes na fila espera
  public static int CLIENTES_ESPERANDO = 0;//Numero atual de Clientes na fila de espera

  public static Cliente[] POSICAO = new Cliente[4];


  /*********************************************
  * Metodo: getMutex
  * Funcao: Retorna o Semaphoro Mutex
  * Parametros: void
  * Retorno: mutex : Semphore
  *********************************************/
  public Semaphore getMutex() {
    return this.mutex;
  }

  /*********************************************
  * Metodo: get Semaphoro Clientes
  * Funcao: Retorna o Semaphoro dos Clientes
  * Parametros: void
  * Retorno: clientes : Semphore
  *********************************************/
  public Semaphore getSClientes() {
    return this.semaphoreClientes;
  }

  /*********************************************
  * Metodo: get Semaphoro Barbeiro
  * Funcao: Retorna o Semaphoro do Barbeiro
  * Parametros: void
  * Retorno: barbeiro : Semphore
  *********************************************/
  public Semaphore getSBarbeiro() {
    return this.semaphoreBarbeiro;
  }

  /*********************************************
  * Metodo: get Semaphoro Interface 1
  * Funcao: Retorna o Semaphoro de controle de Interface 1
  * Parametros: void
  * Retorno: interface 1 : Semphore
  *********************************************/
  public Semaphore getSInterface1() {
    return this.interface1;
  }

  /*********************************************
  * Metodo: get Semaphoro Interface 2
  * Funcao: Retorna o Semaphoro de controle de Interface 2
  * Parametros: void
  * Retorno: interface 2 : Semphore
  *********************************************/
  public Semaphore getSInterface2() {
    return this.interface2;
  }


}//Fim class