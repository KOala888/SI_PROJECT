/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gda.pg.eti.sztuczna.inteligencja.Network;

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
        //try fx null?
        this.error=0;
        this.output=0;
        this.bias=0;
        this.input=new ArrayList();
        this.activationFunction=fx;
    }
    
    //do wejscia programu
    public void AddInputs(Neuron n, double w){
        //tez try z nullem
        input.add(new Connection(n,w));
    }
    //do sieci
    public void AddInputs(Layer l){
        for(Neuron n:l.neurons){
            AddInputs(n,1);
        }
    }
     
    public void randomWeights(double min, double max, Random r){
        for(Connection c: input){
            c.weight=r.nextDouble()*(max-min)+min;
        }
        //necessary?
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
       // System.out.println(error);
        //System.out.println(correctOutput);
       // System.out.println(output);
        error=correctOutput-output;
        //System.out.println(error);
    }
    
    public void correctWeights(double learningRate){
        //System.out.println(error);
        for(Connection c: input){
            c.weight+=learningRate*error*activationFunction.calcDerivative(output)*c.neuron.output;
        }
        bias+=learningRate*error*activationFunction.calcDerivative(output);
    }
    //backpropagation function
    public void castErrorsBackwards(){
        for(Connection c: input){
            //System.out.println("Przed "+c.neuron.error);
            c.neuron.error+=this.error*c.weight;
            //System.out.println("Por " +c.neuron.error);
        }
    }
}
