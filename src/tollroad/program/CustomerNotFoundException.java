/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tollroad.program;

/**
 *
 * @author Max
 */
public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String error) {
        super(error);
    }
}
