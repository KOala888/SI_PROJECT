/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gda.pg.eti.sztuczna.inteligencja.activationfunctions;

/**
 *
 * @author Artur
 */
public interface ActivationFunction {
    double calc(double input);
    double calcDerivative(double input);
}
