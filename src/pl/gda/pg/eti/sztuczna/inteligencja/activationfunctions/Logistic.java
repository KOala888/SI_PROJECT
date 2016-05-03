/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gda.pg.eti.sztuczna.inteligencja.activationfunctions;
import static java.lang.Math.E;
import static java.lang.Math.pow;
/**
 *
 * @author Artur
 * https://en.wikipedia.org/wiki/Activation_function
 * SIGMOIDALNA FUNKCJA
 * http://www.ai.c-labtech.net/sn/images/rysunek2.gif
 */
public class Logistic implements ActivationFunction{
    public double beta;
    
    public Logistic(){
        beta=1.0;
    }
    
    public Logistic(double beta){
        this.beta=beta;
    }
    
    @Override
    public double calc(double input){
        return 1/(1+pow(E,-(beta*input)));
    }
    
    @Override
    public double calcDerivative(double fx){
        return fx*(1-fx);
    }
    
    
}
