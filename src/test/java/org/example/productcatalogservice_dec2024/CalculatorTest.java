package org.example.productcatalogservice_dec2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    //test_when_then
    @Test
    public void Test_WhenTwoIntegersAreAdded_RunsSuccessfully() {
       //Arrange
       Calculator calculator = new Calculator();

        //Act
        int result = calculator.add(10,20);

        //Assert
        assert(result == 30);
        assertEquals(30,result,"The result of addition is not 30");
    }


    @Test
    public void Test_DivideByZero_ResultsInArithmeticException() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act and Assert
       // int result = calculator.divide(1,0);
       assertThrows(ArithmeticException.class,()-> calculator.divide(1,0));
    }
}