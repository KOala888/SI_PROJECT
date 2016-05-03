/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gda.pg.eti.sztuczna.inteligencja.network;

import pl.gda.pg.eti.sztuczna.inteligencja.activationfunctions.ActivationFunction;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Artur
 */
public class Network {
    public Layer inputLayer;
    public Layer outputLayer;
    public ArrayList<Layer> hiddenLayers;
    
    public Network(int inputSize, int hiddenAmount, int hiddenSize, int outputSize, ActivationFunction fx){
        inputLayer=new Layer(inputSize,fx);
        outputLayer=new Layer(outputSize,fx);
        hiddenLayers=new ArrayList();
        for(int i=0; i<hiddenAmount; i++){
            hiddenLayers.add(new Layer(hiddenSize,fx));
        }
        //join layers/neurons/whatever you call it
        outputLayer.connectWith(hiddenLayers.get(hiddenAmount-1));
        for(int i=hiddenAmount-1; i>0; i--){
            hiddenLayers.get(i).connectWith(hiddenLayers.get(i-1));
        }
        hiddenLayers.get(0).connectWith(inputLayer);
    }
    
    public void randomWeights(double min, double max, Random r){
        outputLayer.randomWeights(min, max, r);
        for(Layer l:hiddenLayers){
            l.randomWeights(min, max, r);
        }
    }
    
    public Double[] calc(Double[] input){
        inputLayer.setInput(input);
        for(Layer l:hiddenLayers){
            l.calcLayer();
        }
        outputLayer.calcLayer();
        return outputLayer.outputToArray();
    }
    
    public void teachNetwork( Double[] correctOutput, Double[] input, double learningRate){
        outputLayer.zeroErrors();
        for(Layer l: hiddenLayers){
            l.zeroErrors();
        }
        calc(input);
        outputLayer.calcErrors(correctOutput);
        outputLayer.castErrorsBackwards();
        for(int i=hiddenLayers.size()-1;i>0;i--){
            hiddenLayers.get(i).castErrorsBackwards();
        }
        outputLayer.correctWeights(learningRate);
        for(Layer l:hiddenLayers){
            l.correctWeights(learningRate);
        }
    }
    
}
