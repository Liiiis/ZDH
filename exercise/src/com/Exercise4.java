package com;

interface Compute {
    public int sum(); 

    public int max(int a, int b); 
}

class ComputeClass implements Compute {
    private int num1; 
    private int num2; 

    public ComputeClass(int num1, int num2) {
        
        this.num1 = num1;
        this.num2 = num2;
    }

    
    public int sum() {
        return num1 + num2;
    }

    
    public int max(int a, int b) {
        if (a >= b) {
            return a;
        } else {
            return b;
        }
    }
}

public class Exercise4 {
    public static void main(String[] args) {
        
        ComputeClass calc = new ComputeClass(45, 54);
        System.out.println("两个数的和为" + calc.sum());
        System.out.println("最大的为" + calc.max(56, 66));
    }
}
