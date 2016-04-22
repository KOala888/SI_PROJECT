/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gda.pg.eti.sztuczna.inteligencja.app;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import pl.gda.pg.eti.sztuczna.inteligencja.activationfunctions.Logistic;
import pl.gda.pg.eti.sztuczna.inteligencja.Network.Network;
import pl.gda.pg.eti.sztuczna.inteligencja.Network.Layer;
import pl.gda.pg.eti.sztuczna.inteligencja.Network.Neuron;
import pl.gda.pg.eti.sztuczna.inteligencja.Network.Connection;

/**
 *
 * @author Artur
 */
public class App {

    ArrayList<Double> DaneWe=new ArrayList(2);
    ArrayList<Double> DaneWy=new ArrayList(2);
    ArrayList<Double> WyjscieSieci=new ArrayList(2);
    Random Rand=new Random();

    private void setTest(){
        DaneWe.add(Rand.nextDouble() * 10.0 - 5.0);
        DaneWe.add(Rand.nextDouble() * 10.0 - 5.0);
        DaneWe.add(0.0);
        DaneWe.add(1.0);
        if (DaneWe.get(0) > 0.0)
        {
            DaneWy.set(0,1.0);
            DaneWy.set(1,0.0);
        }
    }
    
    public static void main(String[] args) {
        ArrayList<Double> DaneWe=new ArrayList(2);
        ArrayList<Double> DaneWy=new ArrayList(2);
        ArrayList<Double> WyjscieSieci=new ArrayList(2);
        int ok,nieok;
        Random Rand=new Random();
        Network test=new Network(2,1,2,2,new Logistic());
        test.randomWeights(-1, 1, Rand);
        //addy inicjalizuja, potem trzeba setów uzywac bo to arraylist
        DaneWe.add(Rand.nextDouble() * 10.0 - 5.0);
        DaneWe.add(Rand.nextDouble() * 10.0 - 5.0);
        DaneWy.add(0.0);
        DaneWy.add(1.0);
        ok=0;nieok=0;
        for(int i=0; i<1000;i++){
            DaneWe.set(0,Rand.nextDouble() * 10.0 - 5.0);
            DaneWe.set(1,Rand.nextDouble() * 10.0 - 5.0);
            DaneWy.set(0,0.0);
            DaneWy.set(1,1.0);
            if (DaneWe.get(0) > DaneWe.get(1))
            {
                DaneWy.set(0,1.0);
                DaneWy.set(1,0.0);
            }
            WyjscieSieci=test.calc(DaneWe);
            if ((abs(WyjscieSieci.get(0) - DaneWy.get(0)) < 0.5) &&
                    (abs(WyjscieSieci.get(1) - DaneWy.get(1)) < 0.5))
                {
                    ok++;
                }
            else
                {
                    nieok++;
                }
        }
        System.out.println("Ok: " + ok + "  Nieok: " + nieok + "   Wynik: " + (double)ok/(ok+nieok)*100.0 + "%");
        //nauka
         for(int i=0; i<10000;i++){
            DaneWe.set(0,Rand.nextDouble() * 10.0 - 5.0);
            DaneWe.set(1,Rand.nextDouble() * 10.0 - 5.0);
            DaneWy.set(0,0.0);
            DaneWy.set(1,1.0);
            if (DaneWe.get(0) > DaneWe.get(1))
            {
                DaneWy.set(0,1.0);
                DaneWy.set(1,0.0);
            }
            test.teachNetwork(DaneWy, DaneWe, 0.15);
            WyjscieSieci=test.outputLayer.outputToArray();
        }
         //nauka skonczona
         ok=0;nieok=0;
         System.out.println("Po nauce");
         for(int i=0; i<1000;i++){
            DaneWe.set(0,Rand.nextDouble() * 10.0 - 5.0);
            DaneWe.set(1,Rand.nextDouble() * 10.0 - 5.0);
            DaneWy.set(0,0.0);
            DaneWy.set(1,1.0);
            if (DaneWe.get(0) > DaneWe.get(1))
            {
                DaneWy.set(0,1.0);
                DaneWy.set(1,0.0);
            }
            WyjscieSieci=test.calc(DaneWe);
            if ((abs(WyjscieSieci.get(0) - DaneWy.get(0)) < 0.5) &&
                    (abs(WyjscieSieci.get(1) - DaneWy.get(1)) < 0.5))
                {
                    ok++;
                }
            else
                {
                   nieok++;
                }
         
        }
         System.out.println("Ok: " + ok + "  Nieok: " + nieok + "   Wynik: " + (double)ok/(ok+nieok)*100.0 + "%");
         
    
    System.out.println("Custom liczby, 0 i 0 by zakonczyc");
    Scanner reader = new Scanner(System.in);
    while(true){
        double a=reader.nextDouble();
        double b=reader.nextDouble();
        if(a==b && a==0)
            break;
        DaneWe.set(0,a);
        DaneWe.set(1,b);
        DaneWy.set(0,0.0);
        DaneWy.set(1,1.0);
        if (DaneWe.get(0) > DaneWe.get(1))
        {
            DaneWy.set(0,1.0);
            DaneWy.set(1,0.0);
        }
        WyjscieSieci=test.calc(DaneWe);
        System.out.println("Oczekiwana odpowiedz: " + DaneWy.get(0)+ "  " +DaneWy.get(1));
        System.out.println("Otrzymana odpowiedz: " + WyjscieSieci.get(0)+ "  " +WyjscieSieci.get(1));
        if ((abs(WyjscieSieci.get(0) - DaneWy.get(0)) < 0.5) &&
                (abs(WyjscieSieci.get(1) - DaneWy.get(1)) < 0.5))
            {
                System.out.println("Ok");
            }
        else
            {
                System.out.println("Blad");
            }
    }
    
    //////////////////////////////////////////////////////////////////////////////
    ///////////////////////PORA NA NASTEPNY PROBLEM JAKIS/////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    
    }
}

