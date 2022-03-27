package com;

class Animal {
    private String type;
    private int age;

    public Animal(String type, int age) {
        this.type = type;
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Dog extends Animal {

    private String gender;

    public Dog(String type, int age, String gender) {
        super(type, age);
        this.gender = gender;
    }

    public String getGender() {
        return (String)gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        Dog dog = new Dog(null, 0, null);
        dog.setType("С��");
        dog.setGender("��");
        dog.setAge(5);
        System.out.println("һֻ��Ϊ" + dog.getType() + "�Ա�Ϊ" + dog.getGender() + "��С��������" + dog.getAge() + "�꣬������˯��");
    }
}
