package com.crypto;

import java.util.Random;

public class MainApp {
    public static void main(String[] args) {

        ECC ecc=new ECC(0,7);
        Point generator=new Point(1,1);
        System.out.println(ecc.pointAddition(generator,generator));
        System.out.println(ecc.doubleAndAdd(100,generator));

        // Elliptic Curve Diffie-Hellman Algorithm
        ecc=new ECC(3,7);
        generator=new Point(-2,1);
        Random random=new Random();
        // random number for Alice
        int a= random.nextInt(1000);
        // random number for Bob
        int b= random.nextInt(1000);

        // public key with double and add algo
        // these are the points on the elliptic curve
        Point alicePublicKey= ecc.doubleAndAdd(a,generator);
        Point bobPublicKey= ecc.doubleAndAdd(b,generator);

        // They can generate the same private key for the symmetric encryption
        Point aliceSecretKey= ecc.doubleAndAdd(a,bobPublicKey);
        Point bobSecretKey= ecc.doubleAndAdd(b,alicePublicKey);
        System.out.println("aliceSecretKey and bobSecretKey\n");
        System.out.println(aliceSecretKey);
        System.out.println(bobSecretKey);


    }
}
