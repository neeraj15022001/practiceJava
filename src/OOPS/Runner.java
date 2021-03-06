package OOPS;

class Test {
    //      The eight primitives defined in Java are -
//      int, byte, short, long, float, double, boolean, and char
//      String is non-primitive data type and non-primitive data types have default value of null
    //        Default values
    // 0
    // 0
    //0
    //0
    //0.0
    //0.0
    //false
    // L Character \u0000
    int testInteger;
    byte testByte;
    short testShort;
    float testFloat;
    double testDouble;
    long testLong;
    boolean testBoolean;
    char testCharacter;
}

public class Runner {
    public static void main(String[] args) {
//        new keyword is used to create object/instance of class
//        s1 and s2 are reference to object created from Student Class
//        Student s1 = new Student(); // Student is non-primitives ( User defined data types )
//        Student s2 = new Student(); // Instance/Object is stored in Heap Memory and References are stored in Stack
//        System.out.println(s1); //Output - OOPS.Student@36baf30c   Format - PackageName.ClassName@<Address in memory>
//      Note - Address will vary at every run as space in memory is assigned every time we run our program
//        Test t1 = new Test();
//        System.out.println(t1.testInteger);
//        System.out.println(t1.testByte);
//        System.out.println(t1.testShort);
//        System.out.println(t1.testLong);
//        System.out.println(t1.testFloat);
//        System.out.println(t1.testDouble);
//        System.out.println(t1.testBoolean);
//        System.out.println(t1.testCharacter);

/*
        //Creating Student and setting properties
        Student s1 = new Student();
        s1.name = "Manisha";
        s1.roll_number = 10;
        Student s2 = new Student();
        s2.name = "Neeraj";
        s2.roll_number = 11;
        System.out.println(s1.name + " - " + s1.roll_number);
        System.out.println(s2.name + " - " + s2.roll_number);
 */


        //Access Modifiers

         Student s;
         s = new Student();
    }
}
