/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 30/10/17
* Ultima alteracao: 04/11/17
* Nome: Barbeiro
* Funcao: Criar objetos para representar o Barbeiro
***********************************************************************/

package model;


import javafx.scene.image.ImageView;
import java.util.concurrent.Semaphore;
import img.Imagem;


public class Barbeiro extends Thread {

  private ImageView imageBarbeiro;//Imagem do Barbeiro
  private Imagem allImage;//Classe que possui todas as Imagens
  private Buffer buffer;//Classe que gerencia a fila de Clientes

  private int posXInicial;//Posicao X Inicial da Imagem
  private int posYInicial;//Posicao Y Inicial da Imagem

  private int velocidade = 25;//Velocidade de execucao do Barbeiro
  private boolean pausar = false;//Pausa a execucao da Thread

  /*********************************************
  * Metodo: Barbeiro - Construtor
  * Funcao: Constroi objetos da Classe Barbeiro
  * Parametros: void
  *********************************************/
  public Barbeiro(Buffer buffer, ImageView imageBarbeiro) {
    this.imageBarbeiro = imageBarbeiro;//Atribuindo a Imagem do Barbeiro
    this.posXInicial = (int) imageBarbeiro.getLayoutX();//Pegando a posicao X inicial da Imagem
    this.posYInicial = (int) imageBarbeiro.getLayoutY();//Pegando a posicao Y inicial da Imagem
    this.allImage = new Imagem();//Iniciando objeto allImage
    this.buffer = buffer;//Pegando referencia do objeto Buffer
  }

  public void run() {
    try {

      while (true) {

        verificarPausa();//Verificando se o usuario quer Pausar o programa

        dormir();//Mudanca da Imagem do Barbeiro
        buffer.getSClientes().acquire();//Barbeiro dorme se nao tiver clientes
        cortarCabelo();//Acordando o Barbeiro, muda a Imagem
        buffer.getMutex().acquire();//Barbeiro entra na regiao critica ----------------
        buffer.CLIENTES_ESPERANDO--;//Chama o cliente da Fila
        buffer.getSBarbeiro().release();//Cliente vai cortar o cabelo
        buffer.getMutex().release();//Barbeiro sai da regiao critica ------------------

        verificarPausa();//Verificando se o usuario quer Pausar o programa
        
        buffer.getSInterface2().acquire();//Semaphoro para interface
        Thread.sleep(((this.velocidade-15)*500)/2 + 500);//Sleep de espera
        
        buffer.getSInterface1().release();//Semaphoro para interface
        Thread.sleep(1000);//Tempo ate o cliente sair da cadeira
      }

    } catch (InterruptedException e) {
      System.err.println(e.toString());
    }
  }

  /*********************************************
  * Metodo: Verificar Pausa
  * Funcao: Verifica se o usuario Pausou o Programa
  * Parametros: void
  * Retorno: void
  *********************************************/
  private void verificarPausa() {
    synchronized(this) {
      if (pausar) {//Verificando se pode Pausar a execucao
        try {
          wait();//Trava a Thread
        } catch(InterruptedException e) {
          System.err.println(e.toString());
        }
      }
    }
  }

  /*********************************************
  * Metodo: setPausar
  * Funcao: Atribui a pausa ou o desbloqueio da Thread
  * Parametros: pausar : boolean
  * Retorno: void
  *********************************************/
  public synchronized void setPausar(boolean pausar) {
    this.pausar = pausar;//Pausa ou Continua a execucao
    this.notify();//Acorda a Thread
  }

  /*********************************************
  * Metodo: Dormir
  * Funcao: Trocar imagem do barbeiro e o coloca na cadeira
  * Parametros: void
  * Retorno: void
  *********************************************/
  private void dormir() {
    int posX = 300;//Posicao X onde o Barbeiro deve ficar sobre a cadeira
    int posY = 260;//Posicao Y onde o Barbeiro deve ficar sobre a cadeira
    allImage.trocarImagem(imageBarbeiro, "barbeiro_dormindo");//Trocando imagem
    Imagem.moverImagem(imageBarbeiro, posX, posY);//Colocando na cadeira
  }

  /*********************************************
  * Metodo: Cortar Cabelo
  * Funcao: Troca imagem do barbeiro e o coloca em pe ao lado da cadeira
  * Parametros: void
  * Retorno: void
  *********************************************/
  private void cortarCabelo() {
    allImage.trocarImagem(imageBarbeiro, "barbeiro_cortando");//Trocando imagem
    Imagem.moverImagem(imageBarbeiro, posXInicial, posYInicial);//Mudando a posicao
  }

  /*********************************************
  * Metodo: setVelocidade
  * Funcao: Modifica a velocidade de execucao dessa Thread
  * Parametros: int : Velocidade
  * Retorno: void
  *********************************************/
  public void setVelocidade(int velocidade) {
    int temp = 40;
    this.velocidade = temp-velocidade;//Tranformando velocidade em tempo de espera
  }

}//Fim class