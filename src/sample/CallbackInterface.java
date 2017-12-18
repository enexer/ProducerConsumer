/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 * @author student
 */
public interface CallbackInterface {
    void updateViewProducer(String val);
    void updateViewProducer(String val, boolean pom, int buffer, int list, String threadNo);
    void updateViewConsumer(String val);
    void updateViewConsumer(String val, boolean pom, int buffer, String threadNo);
}
