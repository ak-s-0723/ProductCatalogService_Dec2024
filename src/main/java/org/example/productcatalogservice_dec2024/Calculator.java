package org.example.productcatalogservice_dec2024;

public class Calculator {

    public Calculator() {}

    public int add(int a,int b) {
        return a+b;
    }

    public int divide(int a,int b) {
        try {
          return a/b;
        } catch (ArithmeticException ex) {
            throw ex;
        }
    }
}
