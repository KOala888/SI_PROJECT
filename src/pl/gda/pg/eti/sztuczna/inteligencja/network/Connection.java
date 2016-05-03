    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gda.pg.eti.sztuczna.inteligencja.network;

/**
 *
 * @author Artur
 */
public class Connection {
    public Neuron neuron;
    public double weight;
    
    public Connection(Neuron neuron, double weight){
        this.neuron=neuron;
        this.weight=weight;
    }
}
