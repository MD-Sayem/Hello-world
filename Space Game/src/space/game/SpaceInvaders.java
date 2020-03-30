/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.game;

/**
 *
 * @author Md Abu Sayem
 */

import java.util.List;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.input.KeyCode.A;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class SpaceInvaders extends Application{
        private double t=0;
        private Pane root = new Pane();
        private Sprite player = new Sprite(300, 350, 40, 40, "player", Color.BLUE);
        private Parent createContent(){
            root.setPrefSize(600,400);
            root.getChildren().add(player);
            AnimationTimer timer = new AnimationTimer(){
                @Override
                public void handle(long now) {
                    update();
                    }
                
            };
            timer.start();
            nextLevel();
            return root;
            
        }
        private void nextLevel(){
            for(int i=0; i<5; i++){
                Sprite s= new Sprite(90+ i*100, 50 , 30 , 30, "enemy", Color.RED);
                root.getChildren().add(s);
            }
            
        }   
        private List <Sprite> sprites(){
            return root.getChildren().stream().map(n -> (Sprite)n).collect(Collectors.toList());
        }
         private void update(){
            t+=.01;
            sprites().forEach(s->{
                switch(s.type){
                    case "enemyBullet":
                        s.moveDown();
                        if(s.getBoundsInParent().intersects(player.getBoundsInParent())){
                            player.dead=true;
                            s.dead=true;
                        }
                        break;
                            
                    case "playerBullet":
                        s.moveUp();
                        sprites().stream().filter(e->e.type.equals("enemy")).forEach( enemy -> {
                        if(s.getBoundsInParent().intersects(enemy.getBoundsInParent())){
                            enemy.dead=true;
                            s.dead=true;
                        }
                        });
                        break;
                        
                    case "enemy":
                        if(t >2)
                        {
                            
                            if(Math.random() < 0.4)
                            {
                                shoot(s);
                            }
                        }
                        break;
                        
                }
            
            });
            root.getChildren().removeIf(n ->{
                Sprite s=(Sprite)n;
                return s.dead;
            });
            if(t>2)
                t=0;
        } 
       
        private void shoot(Sprite who){
              Sprite guli =new Sprite((int)who.getTranslateX()+20,(int)who.getTranslateY(), 5 ,20, who.type+ "Bullet", Color.BLACK);
              root.getChildren().add(guli);
        }

        @Override
        public void start(Stage stage)throws Exception{
           // stage.setScene(new Scene(createContent()));
            Scene scene= new Scene( createContent() );
            scene.setOnKeyPressed(e->{
               switch (e.getCode()){
                   case A:
                       player.moveLeft();
                       break;
                   case D:
                       player.moveRight();
                       break;
                   case SPACE:
                       shoot(player); 
                       break;
               } 
            
            });
            stage.setScene(scene);
            stage.show();
        }

     private static class Sprite extends Rectangle{
        boolean dead = false;
        final String type;

        Sprite(int x, int y, int w, int h, String type,Color color){
            super(w, h, color);
            this.type=type;
            setTranslateX(x);
            setTranslateY(y);

        }
        void moveLeft(){
             setTranslateX(getTranslateX() - 5);
        }

         void moveRight(){
             setTranslateX(getTranslateX() + 5);
        }

          void moveUp(){
             setTranslateY(getTranslateY() - 5);
        }

           void moveDown(){
             setTranslateY(getTranslateY() + 5);
        }
          
     }
      public static void main(String[] args) {
             launch(args);
          }
    }
