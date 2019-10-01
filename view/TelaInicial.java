/***********************************************************************
* Autor: Cassio Meira Silva
* Matricula: 201610373
* Inicio: 30/10/17
* Ultima alteracao: 04/11/17
* Nome: Tela Inicial
* Funcao: Tela de Programa
***********************************************************************/

package view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.scene.text.Font;

import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import util.Debug;

import model.*;
import img.Imagem;


public class TelaInicial {

  private static String nomePrograma = "Barbeiro Dorminhoco";//Titulo da Janela
  private static int painelWidth = 800;//Largura da Tela
  private static int painelHeight = 600;//Altura da Tela
  private static boolean maximizarTela = false;//Desativando opcao de Maximizar a tela


  /*********************************************
  * Metodo: show
  * Funcao: Mostrar Tela do Programa
  * Parametros: void
  * Retorno: void
  *********************************************/
  public static void show(Stage palco) {

    palco.setTitle(nomePrograma);//Adicionando titulo 
    palco.setResizable(maximizarTela);//Definir se a tela pode ser Maximizada
    AnchorPane painel = new AnchorPane();//Painel onde adiciona os componentes
    painel.setPrefSize(painelWidth, painelHeight);//Adicionando tamanho ao Painel

    //--------------------------------------------------------------------------------------------
    Imagem allImage = new Imagem();//Objeto que armazenas todas as imagens


    /*******************************************
    * Adicionar ImagemView da Cadeira do Barbeiro
    ********************************************/
    ImageView imageCadeiraBarbeiro = new ImageView();
    allImage.trocarImagem(imageCadeiraBarbeiro, "cadeira_barbeiro");//Adicionando a Imagem
    imageCadeiraBarbeiro.setPreserveRatio(true);
    imageCadeiraBarbeiro.setFitWidth(275);//Adicionando largura
    imageCadeiraBarbeiro.setFitHeight(223);//Adicionando altura
    //Atribuir posicao (X,Y)
    imageCadeiraBarbeiro.setLayoutX(290);
    imageCadeiraBarbeiro.setLayoutY(290);
    painel.getChildren().add(imageCadeiraBarbeiro);//Adicionando ao painel


    /*******************************************
    * Adicionar ImagemView da Cadeira
    ********************************************/
    ImageView imageCadeira0 = new ImageView();
    allImage.trocarImagem(imageCadeira0, "cadeira");//Adicionando a Imagem
    imageCadeira0.setPreserveRatio(true);
    imageCadeira0.setFitHeight(120);//Adicionando altura
    //Atribuir posicao (X,Y)
    imageCadeira0.setLayoutX(100);
    imageCadeira0.setLayoutY(50);
    painel.getChildren().add(imageCadeira0);//Adicionando ao painel

    /*******************************************
    * Adicionar ImagemView da Cadeira
    ********************************************/
    ImageView imageCadeira1 = new ImageView();
    allImage.trocarImagem(imageCadeira1, "cadeira");//Adicionando a Imagem
    imageCadeira1.setPreserveRatio(true);
    imageCadeira1.setFitHeight(120);//Adicionando altura
    //Atribuir posicao (X,Y)
    imageCadeira1.setLayoutX(250);
    imageCadeira1.setLayoutY(50);
    painel.getChildren().add(imageCadeira1);//Adicionando ao painel

    /*******************************************
    * Adicionar ImagemView da Cadeira
    ********************************************/
    ImageView imageCadeira2 = new ImageView();
    allImage.trocarImagem(imageCadeira2, "cadeira");//Adicionando a Imagem
    imageCadeira2.setPreserveRatio(true);
    imageCadeira2.setFitHeight(120);//Adicionando altura
    //Atribuir posicao (X,Y)
    imageCadeira2.setLayoutX(400);
    imageCadeira2.setLayoutY(50);
    painel.getChildren().add(imageCadeira2);//Adicionando ao painel

    /*******************************************
    * Adicionar ImagemView da Cadeira
    ********************************************/
    ImageView imageCadeira3 = new ImageView();
    allImage.trocarImagem(imageCadeira3, "cadeira");//Adicionando a Imagem
    imageCadeira3.setPreserveRatio(true);
    imageCadeira3.setFitHeight(120);//Adicionando altura
    //Atribuir posicao (X,Y)
    imageCadeira3.setLayoutX(550);
    imageCadeira3.setLayoutY(50);
    painel.getChildren().add(imageCadeira3);//Adicionando ao painel



    /*******************************************
    * Adicionar Button Controle Clientes
    ********************************************/
    Button buttonControleClientes = new Button("Iniciar");
    buttonControleClientes.setPrefWidth(90);//Definindo Largura
    //Atribuir posicao (X,Y)
    buttonControleClientes.setLayoutX(230);
    buttonControleClientes.setLayoutY(477);
    painel.getChildren().add(buttonControleClientes);//Adicionando ao painel


    /*******************************************
    * Adicionar Button Controle Barbeiro
    ********************************************/
    Button buttonControleBarbeiro = new Button("Iniciar");
    buttonControleBarbeiro.setPrefWidth(90);//Definindo Largura
    //Atribuir posicao (X,Y)
    buttonControleBarbeiro.setLayoutX(230);
    buttonControleBarbeiro.setLayoutY(538);
    painel.getChildren().add(buttonControleBarbeiro);//Adicionando ao painel


    /*******************************************
    * Adicionar Label Velocidade dos Clientes
    ********************************************/
    Label labelVeloClientes = new Label();
    labelVeloClientes.setText("Velocidade dos Clientes");
    //Atribuir a posicao (X,Y)
    labelVeloClientes.setLayoutX(50);
    labelVeloClientes.setLayoutY(452);
    painel.getChildren().add(labelVeloClientes);//Adicionando ao painel

    /*******************************************
    * Adicionar Slider Velocidade dos Clientes
    ********************************************/
    Slider sliderClientes = new Slider();
    //Atribuindo a posicao (X,Y)
    sliderClientes.setLayoutX(50);
    sliderClientes.setLayoutY(477);
    sliderClientes.setPrefWidth(175);//Definindo largura
    sliderClientes.setMin(5);//Valor minimo
    sliderClientes.setMax(25);//Valor maximo
    sliderClientes.setValue(15);//Valor inicial
    sliderClientes.setBlockIncrement(1);
    sliderClientes.setShowTickLabels(true);
    sliderClientes.setShowTickMarks(true);
    painel.getChildren().add(sliderClientes);//Adicioando Slider ao painel


    /*******************************************
    * Adicionar Label Velocidade do Barbeiro
    ********************************************/
    Label labelVeloBarbeiro = new Label();
    labelVeloBarbeiro.setText("Velocidade do Barbeiro");
    //Atribuir a posicao (X,Y)
    labelVeloBarbeiro.setLayoutX(50);
    labelVeloBarbeiro.setLayoutY(513);
    painel.getChildren().add(labelVeloBarbeiro);//Adicionando ao painel

    /*******************************************
    * Adicionar Slider Velocidade do Barbeiro
    ********************************************/
    Slider sliderBarbeiro = new Slider();
    //Atribuindo a posicao (X,Y)
    sliderBarbeiro.setLayoutX(50);
    sliderBarbeiro.setLayoutY(538);
    sliderBarbeiro.setPrefWidth(175);//Definindo largura
    sliderBarbeiro.setMin(5);//Valor minimo
    sliderBarbeiro.setMax(25);//Valor maximo
    sliderBarbeiro.setValue(15);//Valor inicial
    sliderBarbeiro.setBlockIncrement(1);
    sliderBarbeiro.setShowTickLabels(true);
    sliderBarbeiro.setShowTickMarks(true);
    painel.getChildren().add(sliderBarbeiro);//Adicioando Slider ao painel


  
    /*******************************************
    * Adicionar ImagemView do Barbeiro
    ********************************************/
    ImageView imageBarbeiro = new ImageView();
    allImage.trocarImagem(imageBarbeiro, "barbeiro_cortando");//Adicionando a Imagem
    imageBarbeiro.setPreserveRatio(true);
    imageBarbeiro.setFitHeight(230);//Adicionando altura
    //Atribuir posicao (X,Y)
    imageBarbeiro.setLayoutX(385);
    imageBarbeiro.setLayoutY(250);
    painel.getChildren().add(imageBarbeiro);//Adicionando ao painel



    Buffer buffer = new Buffer();
    Barbeiro barbeiro = new Barbeiro(buffer,imageBarbeiro);//Thread do Barbeiro
    //Thread do Produtor que cria Threads de Clientes
    Produtor produtor = new Produtor(buffer,painel);



    buttonControleClientes.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

        if (buttonControleClientes.getText().toString().equals("Iniciar")) {
          produtor.start();
          buttonControleClientes.setText("Pausar");
        } else if (buttonControleClientes.getText().toString().equals("Pausar")) {
          produtor.setPausar(true);
          buttonControleClientes.setText("Continuar");
        } else {
          produtor.setPausar(false);
          buttonControleClientes.setText("Pausar");
        }

      }
    });


    buttonControleBarbeiro.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

        if (buttonControleBarbeiro.getText().toString().equals("Iniciar")) {
          barbeiro.start();//iniciando Thread do Barbeiro
          buttonControleBarbeiro.setText("Pausar");
        } else if (buttonControleBarbeiro.getText().toString().equals("Pausar")) {
          barbeiro.setPausar(true);
          buttonControleBarbeiro.setText("Continuar");
        } else {
          barbeiro.setPausar(false);
          buttonControleBarbeiro.setText("Pausar");
        }

      }
    });


    //Slider para controlar a velocidade
    sliderClientes.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, //
        Number oldValue, Number newValue) {
          int valor = newValue.intValue();
          produtor.setVelocidade(valor);//Alterando velocidade dos Clientes
        }
    });

    //Slider para controlar a velocidade
    sliderBarbeiro.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, //
        Number oldValue, Number newValue) {
          int valor = newValue.intValue();
          barbeiro.setVelocidade(valor);//Alterando velocidade do Barbeiro
        }
    });

    
    palco.setScene(new Scene(painel, painelWidth, painelHeight));//Adicionando Cena ao Palco
    palco.show();//Mostrando o Palco

  }//Fim Show

}//Fim class