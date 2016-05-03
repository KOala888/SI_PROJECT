/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gda.pg.eti.sztuczna.inteligencja.app;
import pl.gda.pg.eti.sztuczna.inteligencja.problems.WhichNumberBigger;

/**
 *
 * @author Artur
 */
public class App {
    
    public static void main(String[] args) {
        WhichNumberBigger numbers = new WhichNumberBigger(1,2);
        numbers.randomTests(100000, -1.0, 1.0);
        numbers.randomLearning(100000,-0.1,0.1,0.15);
        numbers.randomTests(100000, -1.0, 1.0);    
        numbers.customTests();
    }
}

