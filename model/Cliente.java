/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 30/10/17
* Ultima alteracao: 04/11/17
* Nome: Cliente
* Funcao: Criar objetos para representar o Cliente
***********************************************************************/

package model;


import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.util.concurrent.Semaphore;
import javafx.application.Platform;
import img.Imagem;


public class Cliente extends Thread {

  private ImageView imageCliente;//Imagem do Cliente
  private Buffer buffer;//Referencia do Buffer
  private Imagem allImage = new Imagem();//Classe que tem todas as imagens
  public int index;//Indice do Clientes

  private int posXInicial;//Posicao X inicial do Cliente
  private int posYInicial;//Posicao Y inicial do Cliente

  /*********************************************
  * Metodo: Cliente - Construtor
  * Funcao: Criar objetos da classe Cliente
  * Parametros: Buffer, ImageView
  *********************************************/
  public Cliente(Buffer buffer, ImageView imageCliente) {
    this.imageCliente = imageCliente;//ImageView da Imagem
    this.posXInicial = (int) imageCliente.getLayoutX();//Posicao X
    this.posYInicial = (int) imageCliente.getLayoutY();//Posicao Y
    this.buffer = buffer;//Referencia do Buffer
  }

  public void run() {
    try {

      moverAteSalao();//Movendo Imagem do Cliente
      buffer.getMutex().acquire();//Entra na regiao critica -----------------

      if (buffer.CLIENTES_ESPERANDO < buffer.MAX_CLIENTES) {
        this.entrarFilaEspera();//Levando Imagem para alguma cadeira da fila de espera
        buffer.CLIENTES_ESPERANDO++;//Aumentando o numero de clientes

        buffer.getSClientes().release();//Acorda o Barbeiro que estava dormindo
        
        buffer.getMutex().release();//Sai da regicao critica -----------------

        buffer.getSBarbeiro().acquire();//Fica preso caso o Barbeiro esteja ocupado
        Buffer.POSICAO[index] = null;//Liberando a cadeira da Fila de espera
        moverParaCadeiraBarbeiro(328,280);//Movendo ate cadeira do Barbeiro

        buffer.getSInterface2().release();//Semaphoro para interface

        buffer.getSInterface1().acquire();//Semaphoro para interface
        vaiEmbora(2);//Vai embora com o cabelo cortado (2)

      } else {
        buffer.getMutex().release();//Sai da regiao critica
        vaiEmbora(1);//Vai embora sem cortar o cabelo (1)
      }

    } catch (InterruptedException e) {
      System.err.println(e.toString());
    }
  }

  /*********************************************
  * Metodo: Entrar Fila de Espera
  * Funcao: Atribui um indice para o Cliente e o leva para uma cadeira
  * Parametros: void
  * Retorno: void
  *********************************************/
  private synchronized void entrarFilaEspera() throws InterruptedException {

    //Verificando qual cadeira da fila de espera esta vazia
    for (int i=0; i<4; i++) {
      if (Buffer.POSICAO[i] == null) {//Se cadeira "i" estiver vazia
        this.index = i;//Este cliente recebe o indice da cadeira
        Buffer.POSICAO[i] = this;//A cadeira fica ocupada
        break;
      }
    }

    switch (index) {//Ir para a cadeira a partir do indice
      case 0 :
        moverParaCadeiraEspera(102);//Movendo para cadeira 0
        break;
      case 1 :
        moverParaCadeiraEspera(252);//Movendo para cadeira 1
        break;
      case 2 :
        moverParaCadeiraEspera(402);//Movendo para cadeira 2
        break;
      case 3 :
        moverParaCadeiraEspera(552);//Movendo para cadeira 3
        break;
    }

  }

  /*********************************************
  * Metodo: Vai Embora
  * Funcao: Determina qual o movimento de Ir Embora para o Cliente
  * Parametros: selecao : int
  * Retorno: void
  *********************************************/
  private void vaiEmbora(int selecao) throws InterruptedException {
    if (selecao == 1) {
      moverEmbora1(-100,280);//Vai embora sem cortar o cabelo
    } else {
      moverEmbora2(760,240);//Vai embora com o cabelo cortado
    }
  }

  /*********************************************
  * Metodo: Mover Embora 1 (Sem cortar o cabelo)
  * Funcao: Move a imagem do Cliente para fora do Salao
  * Parametros: xFinal : int, yFinal : int
  * Retorno: void
  *********************************************/
  private void moverEmbora1(int xFinal, int yFinal) throws InterruptedException {
    int posX = (int) imageCliente.getLayoutX();//Posicao X atual da Imagem
    int posY = (int) imageCliente.getLayoutY();//Posicao Y atual da Imagem
    while (posY < yFinal) {
      posY++;
      moverImagem(posX, posY);//Movendo imagem do cliente
      Thread.sleep(Produtor.VELOCIDADE);//Tempo de Sleep
    }
    while (posX > xFinal) {
      posX--;
      moverImagem(posX, posY);//Movendo imagem do cliente
      Thread.sleep(Produtor.VELOCIDADE);//Tempo de Sleep
    }
  }

  /*********************************************
  * Metodo: Mover Embora 2 (Com cabelo cortado)
  * Funcao: Move a imagem do Cliente para fora do Salao
  * Parametros: xFinal : int, yFinal : int
  * Retorno: void
  *********************************************/
  private void moverEmbora2(int xFinal, int yFinal) throws InterruptedException {
    allImage.trocarImagem(imageCliente, "cliente_sem_barba");//Trocando Imagem do Cliente
    int posX = (int) imageCliente.getLayoutX();//Posicao X atual da Imagem
    int posY = (int) imageCliente.getLayoutY();//Posicao Y atual da Imagem
    Thread.sleep(500);
    while (posX < xFinal || posY > yFinal) {
      if (posX < xFinal) {
        posX+=2;
      }
      if (posY > yFinal) {
        posY--;
      }
      moverImagem(posX, posY);//Movendo imagem do cliente
      Thread.sleep(Produtor.VELOCIDADE);//Tempo de Sleep
    }
  }

  /*********************************************
  * Metodo: Mover Ate Salao
  * Funcao: Move a Imagem do Cliente recem criado
  * Parametros: void
  * Retorno: void
  *********************************************/
  private void moverAteSalao() throws InterruptedException {
    int posX = posXInicial;
    int xFinal = -40;
    while (posX < xFinal) {
      posX+=2;
      moverImagem(posX, posYInicial);//Movendo imagem
      Thread.sleep(Produtor.VELOCIDADE);//Tempo de Sleep
    }
  }


  /*********************************************
  * Metodo: Mover para Cadeira Espera
  * Funcao: Move a imagem do cliente para alguma cadeira de espera
  * Parametros: xFinal : int (posicao X da cadeira)
  * Retorno: void
  *********************************************/
  private void moverParaCadeiraEspera(int xFinal) throws InterruptedException {
    int posX = (int) imageCliente.getLayoutX();
    while (posX < xFinal) {
      posX+=2;
      moverImagem(posX, posYInicial);
      Thread.sleep(Produtor.VELOCIDADE);
    }
    int posY = (int) imageCliente.getLayoutY();
    while (posY > 0) {
      posY-=2;
      moverImagem(posX, posY);
      Thread.sleep(Produtor.VELOCIDADE);
    }
    allImage.trocarImagem(imageCliente, "cliente_sentado_com_barba");//Trocando imagem do cliente
  }

  /*********************************************
  * Metodo: Mover para Cadeira do Barbeiro
  * Funcao: Move a imagem do Cliente ate a cadeira do Barbeiro
  * Parametros: xFinal : int, yFinal : int
  * Retorno: void
  *********************************************/
  private void moverParaCadeiraBarbeiro(int xFinal, int yFinal) throws InterruptedException {
    allImage.trocarImagem(imageCliente, "cliente_com_barba");//Alterando a imagem para em pe
    int posX = (int) imageCliente.getLayoutX();//Obtendo posicao X
    int posY = (int) imageCliente.getLayoutY();//Obtendo posicao Y

    if (posX < xFinal) {

      while (posX < xFinal || posY < yFinal) {
        if (posX < xFinal) {
          posX+=2;
        }
        if (posY < yFinal) {
          posY+=2;
        }
        moverImagem(posX, posY);//Movendo imagem do Cliente
        Thread.sleep(Produtor.VELOCIDADE);//Sleep de velocidade
      }

    } else {// posX > xFinal

      while (posX > xFinal || posY < yFinal) {
        if (posX > xFinal) {
          posX-=2;
        }
        if (posY < yFinal) {
          posY+=2;
        }
        moverImagem(posX, posY);//Movendo imagem do Cliente
        Thread.sleep(Produtor.VELOCIDADE);//Sleep de velocidade
      }

    }
    //Alterando a imagem para sentado
    allImage.trocarImagem(imageCliente, "cliente_sentado_com_barba");
  }

  /*********************************************
  * Metodo: Mover Imagem
  * Funcao: Move a ImageView para uma nova posicao (X,Y)
  * Parametros: ImageView, posX, posY
  * Retorno: void
  *********************************************/
  private void moverImagem(int posX, int posY) {
    Platform.runLater(new Runnable(){
      @Override
      public void run() {
        imageCliente.setLayoutX(posX);
        imageCliente.setLayoutY(posY);
      }
    });
  }

}//Fim class