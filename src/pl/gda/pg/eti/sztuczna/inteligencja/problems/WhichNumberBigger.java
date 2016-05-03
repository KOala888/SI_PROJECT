/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gda.pg.eti.sztuczna.inteligencja.problems;

/**
 *
 * @author Artur
 */
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import pl.gda.pg.eti.sztuczna.inteligencja.activationfunctions.Logistic;
import pl.gda.pg.eti.sztuczna.inteligencja.network.Network;
public class WhichNumberBigger {
    private Random rand;
    private Network network;
    private Double[] inputData;
    private Double[] outputData;
    private Double[] networkOutput;
    
    public WhichNumberBigger(int hiddenLayersAmount, int hiddenLayerSize){
        rand=new Random();
        network=new Network(2,hiddenLayersAmount,hiddenLayerSize,2,new Logistic());
        inputData=new Double[2];
        outputData=new Double[2];
        networkOutput=new Double[2];
        network.randomWeights(-1, 1, rand);
    }
    
    public void randomTests(int n, Double min, Double max){
        int ok=0,notOk=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<2;j++){
                //This will return a pseudorandom double value between the 
                //minimum (inclusive) and the maximum (exclusive).
                inputData[j]=ThreadLocalRandom.current().nextDouble(min, max);
            }
            outputData[0]=0.0;
            outputData[1]=1.0;
            if (inputData[0] > inputData[1]){
                outputData[0]=1.0;
                outputData[1]=0.0;
            }
            networkOutput=network.calc(inputData);
            if ((abs(networkOutput[0] - outputData[0]) < 0.5) &&
                    (abs(networkOutput[1] - outputData[1]) < 0.5)){
                    ok++;
            }
            else{
                    notOk++;
            }
        }
         System.out.println("Ok: " + ok + "  Nieok: " + notOk + 
                 "   Wynik: " + (double)ok/(ok+notOk)*100.0 + "%");
    }
    
    public void randomLearning(int n, Double min, Double max, Double learningRate){
        //the more iterations and the smaller difference between example numers is
        //the more precise the network will be
        //but still, too many learning iterations will affect in network being to specialised
        //at sample data, so it may have problems with new data
        for(int i=0; i<n; i++){
            for(int j=0; j<2;j++){
                inputData[j]=ThreadLocalRandom.current().nextDouble(min, max);
            }
            outputData[0]=0.0;
            outputData[1]=1.0;
            if (inputData[0] > inputData[1]){
                outputData[0]=1.0;
                outputData[1]=0.0;
            }
            network.teachNetwork(outputData, inputData, learningRate);
        }
    }
    
    public void customTests(){
        System.out.println("0 i 0 by zakonczyc");
        Scanner reader = new Scanner(System.in);
        while(true){
            double a=reader.nextDouble();
            double b=reader.nextDouble();
            if(a==b && a==0)
                break;
            inputData[0]=a;
            inputData[1]=b;
            outputData[0]=0.0;
            outputData[1]=1.0;
            if (inputData[0] > inputData[1])
            {
                outputData[0]=1.0;
                outputData[1]=0.0;
            }
            networkOutput=network.calc(inputData);
            System.out.println("Oczekiwana odpowiedz: " + outputData[0]+ "  " +outputData[1]);
            System.out.println("Otrzymana odpowiedz: " + networkOutput[0]+ "  " +networkOutput[1]);
            if ((abs(networkOutput[0] - outputData[0]) < 0.5) &&
                    (abs(networkOutput[1] - outputData[1]) < 0.5))
                {
                    System.out.println("Ok");
                }
            else
                {
                    System.out.println("Blad");
                }
        }
    }
}
