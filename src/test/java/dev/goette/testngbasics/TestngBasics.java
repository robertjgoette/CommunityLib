package dev.goette.testngbasics;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestngBasics {

    @BeforeClass
    static void setup(){
        System.out.println("BEFORE CLASS");
    }
    @BeforeMethod
    void smallerSetup(){
        System.out.println("BEFORE EVERY TEST METHOD");
    }
    @Test(priority = 1)// Default to alphanumeric
    void test1(){
        System.out.println("Hello");
    }

    @Test(priority = 2)
    void test2(){
        System.out.println("Will fail");
        throw new RuntimeException("FAIL");
    }
    @Test(priority = 3)
    void test3(){
        System.out.println("Helloa");
    }
    @Test(priority = 4)
    void test4(){
        System.out.println("Hellob");
    }
    @Test(priority = 1)
    void test5(){
        System.out.println("Helloc");
    }


}
