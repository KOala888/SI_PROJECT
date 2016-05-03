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

    Random Rand=new Random();
    
    public static void main(String[] args) {
        ArrayList<Double> inputData=new ArrayList(2);
        ArrayList<Double> outputData=new ArrayList(2);
        ArrayList<Double> networkOutput=new ArrayList(2);
        int ok,notOk;
        Random Rand=new Random();
        Network test=new Network(2,1,2,2,new Logistic());
        test.randomWeights(-1, 1, Rand);
        //addy inicjalizuja, potem trzeba setów uzywac bo to arraylist
        inputData.add(Rand.nextDouble() * 10.0 - 5.0);
        inputData.add(Rand.nextDouble() * 10.0 - 5.0);
        outputData.add(0.0);
        outputData.add(1.0);
        ok=0;notOk=0;
        for(int i=0; i<1000;i++){
            inputData.set(0,Rand.nextDouble() * 10.0 - 5.0);
            inputData.set(1,Rand.nextDouble() * 10.0 - 5.0);
            outputData.set(0,0.0);
            outputData.set(1,1.0);
            if (inputData.get(0) > inputData.get(1))
            {
                outputData.set(0,1.0);
                outputData.set(1,0.0);
            }
            networkOutput=test.calc(inputData);
            if ((abs(networkOutput.get(0) - outputData.get(0)) < 0.5) &&
                    (abs(networkOutput.get(1) - outputData.get(1)) < 0.5))
                {
                    ok++;
                }
            else
                {
                    notOk++;
                }
        }
        System.out.println("Ok: " + ok + "  Nieok: " + notOk + "   Wynik: " + (double)ok/(ok+notOk)*100.0 + "%");
        //nauka
         for(int i=0; i<10000000;i++){
             //the more iterations and the smaller difference between example numers is
             //the more precise the network will be
            inputData.set(0,Rand.nextDouble()/10);
            inputData.set(1,Rand.nextDouble()/10);
            outputData.set(0,0.0);
            outputData.set(1,1.0);
            if (inputData.get(0) > inputData.get(1))
            {
                outputData.set(0,1.0);
                outputData.set(1,0.0);
            }
            test.teachNetwork(outputData, inputData, 0.15);
            networkOutput=test.outputLayer.outputToArray();
        }
         //nauka skonczona
         ok=0;notOk=0;
         System.out.println("Po nauce");
         for(int i=0; i<1000;i++){
            inputData.set(0,Rand.nextDouble() * 10.0 - 5.0);
            inputData.set(1,Rand.nextDouble() * 10.0 - 5.0);
            outputData.set(0,0.0);
            outputData.set(1,1.0);
            if (inputData.get(0) > inputData.get(1))
            {
                outputData.set(0,1.0);
                outputData.set(1,0.0);
            }
            networkOutput=test.calc(inputData);
            if ((abs(networkOutput.get(0) - outputData.get(0)) < 0.5) &&
                    (abs(networkOutput.get(1) - outputData.get(1)) < 0.5))
                {
                    ok++;
                }
            else
                {
                   notOk++;
                }
         
        }
         System.out.println("Ok: " + ok + "  Nieok: " + notOk + "   Wynik: " + (double)ok/(ok+notOk)*100.0 + "%");
         
    
    System.out.println("Custom liczby, 0 i 0 by zakonczyc");
    Scanner reader = new Scanner(System.in);
    while(true){
        double a=reader.nextDouble();
        double b=reader.nextDouble();
        if(a==b && a==0)
            break;
        inputData.set(0,a);
        inputData.set(1,b);
        outputData.set(0,0.0);
        outputData.set(1,1.0);
        if (inputData.get(0) > inputData.get(1))
        {
            outputData.set(0,1.0);
            outputData.set(1,0.0);
        }
        networkOutput=test.calc(inputData);
        System.out.println("Oczekiwana odpowiedz: " + outputData.get(0)+ "  " +outputData.get(1));
        System.out.println("Otrzymana odpowiedz: " + networkOutput.get(0)+ "  " +networkOutput.get(1));
        if ((abs(networkOutput.get(0) - outputData.get(0)) < 0.5) &&
                (abs(networkOutput.get(1) - outputData.get(1)) < 0.5))
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

