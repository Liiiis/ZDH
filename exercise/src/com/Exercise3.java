package com;

class Total {
    private double totalMoney = 50.00;

    public double getTotalMoney() {
        return totalMoney;
    }
}

class Seal extends Total {
    private double discount;
    private double totalPrice;

    public Seal() {
        super();
    }

    public double sealX() {
        discount = 0.5;
        totalPrice = 78;
        return discount * totalPrice;
    }

}

public class Exercise3 {
    public static void main(String[] args) {
        Seal seal = new Seal();
        System.out.println("打折后的价格是" + seal.sealX() + "差价为" + (seal.getTotalMoney() - seal.sealX()) + "元");
    }
}