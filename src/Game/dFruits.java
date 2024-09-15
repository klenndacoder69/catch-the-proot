/**
 * Class Name: dFruit
 *
 * Description:
 *     The `dFruit` class extends the `Fruit` class and contains different types of fruits with their corresponding points.
 *     Each subclass represents a specific type of fruit.
 *
 * Subclasses:
 *     1. Apple
 *     2. Banana
 *     3. Cherry
 *     4. fruit1
 *     5. fruit2
 *     6. fruit3
 *     7. strawberry
 *     8. wm
 *     9. pumpkin
 */

package Game;

//dFruits class contain the different fruits with its corresponding points
class Apple extends Fruit{
    public Apple(){
        super("file:img/fruits/apple.png",20,  0);
    }
}

class Banana extends Fruit{
    public Banana(){
        super("file:img/fruits/banana.png", 50, 0);
    }
}

class Cherry extends Fruit{
    public Cherry(){
        super("file:img/fruits/cherry.png",100,  0);
    }
}
class fruit1 extends Fruit{
    public fruit1(){
        super("file:img/bad fruits/01.png", 25, 0);
    }
}

class fruit2 extends Fruit{
    public fruit2(){
        super("file:img/bad fruits/02.png", 35, 0);
    }
}

class fruit3 extends Fruit{
    public fruit3(){
        super("file:img/bad fruits/06.png", 45, 0);
    }
}

class strawberry extends Fruit{
    public strawberry() {
        super("file:img/bad fruits/badfruit1.png", -50, -1);
    }
}

class wm extends Fruit{
    public wm() {
        super("file:img/bad fruits/badfruit2.png", -25, -1);

    }
}

class pumpkin extends Fruit{
    public pumpkin() {
        super("file:img/bad fruits/badfruit3.png", -35, -1);

    }
}
