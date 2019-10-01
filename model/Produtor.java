/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 30/10/17
* Ultima alteracao: 04/11/17
* Nome: Produtor
* Funcao: Thread para gerar os clientes
***********************************************************************/

package model;


import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;
import img.Imagem;
import model.*;


public class Produtor extends Thread {

  private Buffer buffer;//Referencia do Buffer
  private AnchorPane painel;//Referencia ao painel da TelaInicial
  private Imagem allImage = new Imagem();//Classe que possui todas as Imagens
  public static int VELOCIDADE = 35;//Velocidade do Produtor

  private boolean pausar = false;//Pausa a execucao da Thread

  /*********************************************
  * Metodo: Produtor - Construtor
  * Funcao: Criar objeto da classe Produtor
  * Parametros: AnchorPane
  *********************************************/
  public Produtor(Buffer buffer, AnchorPane painel) {
    this.buffer = buffer;
    this.painel = painel;
  }

  public void run() {
    try {
      
      while (true) {
        verificarPausa();//Verificando se o usuario quer Pausar o programa
        criarCliente();//Cria um novo cliente
        Thread.sleep((VELOCIDADE-15)*325 + 3500);//Tempo de Sleep
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
  * Metodo: Criar Cliente
  * Funcao: Cria uma nova Thread do Cliente
  * Parametros: void
  * Retorno: void
  *********************************************/
  private void criarCliente() {
    Platform.runLater(new Runnable(){
      @Override
      public void run() {
        /*******************************************
        * Adicionar ImagemView do Cliente
        ********************************************/
        ImageView imageCliente = new ImageView();
        allImage.trocarImagem(imageCliente, "cliente_com_barba");//Adicionando a Imagem
        imageCliente.setPreserveRatio(true);
        imageCliente.setFitWidth(200);//Adicionando largura
        imageCliente.setFitHeight(150);//Adicionando altura
        //Atribuir posicao (X,Y)
        imageCliente.setLayoutX(-100);
        imageCliente.setLayoutY(110);
        painel.getChildren().add(imageCliente);//Adicionando ao painel

        Cliente cliente = new Cliente(buffer,imageCliente);
        cliente.start();
      }
    });
  }

  /*********************************************
  * Metodo: setVelocidade
  * Funcao: Modifica a velocidade de execucao dessa Thread
  * Parametros: int : Velocidade
  * Retorno: void
  *********************************************/
  public void setVelocidade(int velocidade) {
    int temp = 40;
    this.VELOCIDADE = temp-velocidade;//Tranformando velocidade em tempo de espera
  }

}//Fim class