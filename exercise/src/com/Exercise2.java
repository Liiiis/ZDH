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
        dog.setType("小黄");
        dog.setGender("公");
        dog.setAge(5);
        System.out.println("一只名为" + dog.getType() + "性别为" + dog.getGender() + "的小狗，现在" + dog.getAge() + "岁，它正在睡觉");
    }
}
