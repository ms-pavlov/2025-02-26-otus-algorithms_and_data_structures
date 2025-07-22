package org.example;

import org.example.model.*;

public class Program {

    public static void main(String[] args) {
        System.out.println("SingleArray");
        new Tester(SingleArray::new, 100_000).run();
        System.out.println("VectorArray 10");
        new Tester(new VectorArrayFactory(10), 100_000).run();
        System.out.println("VectorArray 100");
        new Tester(new VectorArrayFactory(100), 100_000).run();
        System.out.println("VectorArray 100");
        new Tester(new VectorArrayFactory(1_000), 100_000).run();
        System.out.println("FactorArray");
        new Tester(FactorArray::new, 100_000).run();
        System.out.println("MatrixArray 10");
        new Tester(new MatrixArrayFactory(10), 100_000).run();
        System.out.println("MatrixArray 100");
        new Tester(new MatrixArrayFactory(100), 100_000).run();
        System.out.println("MatrixArray 1000");
        new Tester(new MatrixArrayFactory(1_000), 100_000).run();
        System.out.println("SpaceArray 10");
        new Tester(new SpaceArrayFactory(10), 100_000).run();
        System.out.println("SpaceArray 100");
        new Tester(new SpaceArrayFactory(100), 100_000).run();
        System.out.println("SpaceArray 1000");
        new Tester(new SpaceArrayFactory(1_000), 100_000).run();

    }
}
