/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gda.pg.eti.sztuczna.inteligencja.network;

import java.util.ArrayList;
import java.util.Random;
import pl.gda.pg.eti.sztuczna.inteligencja.activationfunctions.ActivationFunction;

/**
 *
 * @author Artur
 */
public class Layer {
    public ArrayList<Neuron> neurons;
    
    public Layer(int amount, ActivationFunction fx){
        neurons=new ArrayList();
        for(int i=0; i<amount; i++){
            neurons.add(new Neuron(fx));
        }
    }
    
    //with lower layer ->
    public void connectWith(Layer l){
        for(Neuron n: neurons){
            n.AddInputs(l);
        }
    }
    
    public void randomWeights(double min, double max, Random r){
        for(Neuron n: neurons){
            n.randomWeights(min,max,r);
        }
    }
    //so far, first layer (it's output) is treated like a network input
    public void setInput(Double[] input){
        for(int i=0; i<neurons.size(); i++){
            neurons.get(i).output=input[i];
        }
    }
    
    public Double[] outputToArray(){
        Double[] Out=new Double[neurons.size()];
        for(int i=0; i<neurons.size(); i++){
           Out[i]=neurons.get(i).output;
        }
        return Out;
    }
    
    public void calcLayer(){
        for(Neuron n: neurons){
            n.calcOutput();
            n.error=0;
        }
    }
    public void zeroErrors(){
        for(Neuron n: neurons){
            n.error=0;
        }
    }
    //output layer only
    public void calcErrors(Double[] correct){
        for(int i=0; i<neurons.size(); i++){
            neurons.get(i).calcError(correct[i]);
        }
    }
    
    public void correctWeights(double learningRate){
        for(Neuron n:neurons){
            n.correctWeights(learningRate);
        }
    }
    
    public void castErrorsBackwards(){
        for(Neuron n:neurons){
            n.castErrorsBackwards();
        }
    }
}
