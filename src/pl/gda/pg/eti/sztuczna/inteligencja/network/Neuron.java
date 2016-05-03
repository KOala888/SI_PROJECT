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
public class Neuron {
    public ActivationFunction activationFunction;
    public ArrayList<Connection> input;
    public double output;
    public double bias;
    public double error;
    
    public Neuron(ActivationFunction fx){
        this.error=0;
        this.output=0;
        this.bias=0;
        this.input=new ArrayList();
        this.activationFunction=fx;
    }
    
   
    public void AddInputs(Neuron n, double w){
        input.add(new Connection(n,w));
    }
    
    public void AddInputs(Layer l){
        for(Neuron n:l.neurons){
            AddInputs(n,1);
        }
    }
     
    public void randomWeights(double min, double max, Random r){
        for(Connection c: input){
            c.weight=r.nextDouble()*(max-min)+min;
        }
        bias=r.nextDouble()*(max-min)+min;
    }
    
    public void calcOutput(){
        output=0;
        for(Connection c:input){
            output+=c.weight*c.neuron.output;
        }
        output+=bias;
        output=activationFunction.calc(output);
    }
    
    public void calcError(double correctOutput){
        error=correctOutput-output;
    }
    
    public void correctWeights(double learningRate){
        for(Connection c: input){
            c.weight+=learningRate*error*activationFunction.calcDerivative(output)*c.neuron.output;
        }
        bias+=learningRate*error*activationFunction.calcDerivative(output);
    }
    //backpropagation algorithm
    public void castErrorsBackwards(){
        for(Connection c: input){
            c.neuron.error+=this.error*c.weight;;
        }
    }
}
