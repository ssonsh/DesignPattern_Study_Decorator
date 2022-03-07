# DesignPattern_Study_Decorator

# Notion Link
https://five-cosmos-fb9.notion.site/Decorator-1e3ab5d15c3144d8b78958fa9ce7908c


# 장식자 (Decorator)

### 의도

객체에 동적으로 새로운 책임을 추가할 수 있게 한다.

기능을 추가하려면 서브 클래스를 생성하는 것보다 융통성 있는 방법을 제공한다.

<aside>
🎈 다른 이름 : 랩퍼 (Wrapper)

</aside>

***Decorator 의 명칭과 같이 목적은 Object를 꾸며주는 역할을 한다.***

- 우리가 원하는 기능으로 감싸서 사용할 수 있게 만들어주는 패턴

![image](https://user-images.githubusercontent.com/18654358/157132345-20402763-c4b9-4fe6-bf05-3b611783ceaa.png)


[Animal.java](http://Animal.java) (abstract Class)

```java
public abstract class Animal {

    abstract void speak();
}
```

Cat.java, [Dog.java](http://Dog.java) (extends Animal)

```java
public class Cat extends Animal{
    @Override
    void speak() {
        System.out.print("냥");
    }
}
/////////////////////////////////////
public class Dog extends Animal{
    @Override
    void speak() {
        System.out.print("멍");
    }
}
```

[**Deco.java](http://Deco.java) (extends Animal with Field in Animal)**

```java
public class Deco extends Animal{
    private Animal animal;
    public Deco(Animal animal){
        this.animal = animal;
    }

    @Override
    void speak() {
        this.animal.speak();
    }
}
```

<aside>
🎈 Deco 클래스 자체에는 Animal 필드를 가지고 있으며 생성자를 통해 주입받는다.
Animal 추상클래스를 상속받아 speak 함수를 재정의한다.
- 재정의된 speak 함수에서는 animal의 speak를 호출한다.

</aside>

**WithSmile.java, [WithHeart.java](http://WithHeart.java) (extends Deco)**

```java
public class WithHeart extends Deco{
    public WithHeart(Animal animal) {
        super(animal);
    }

    @Override
    void speak() {
        super.speak();
        System.out.print("😍");
    }
}
```

```java
public class WithSmile extends Deco{
    public WithSmile(Animal animal) {
        super(animal);
    }

    @Override
    void speak() {
        super.speak();
        System.out.print("😁");
    }
}
```

<aside>
🎈 Deco 클래스를 상속받아 구현한 WithHeart, WithSmile 클래스는
Deco 클래스의 speak 함수를 오버라이딩하며 추가적으로 원하는 작업을 처리한다.

</aside>

**Run!**

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();

        animals.add(new Cat());
        animals.add(new Dog());

        animals.forEach(a -> {a.speak(); System.out.println("");});

        ////////////////////////////////////////////////
        System.out.println("-- Decorator를 이용해 Smail, Heart를 붙여서 실행해보자! --");

        animals = new ArrayList<>();
        animals.add(new WithSmile(new Cat()));
        animals.add(new WithHeart(new Cat()));
        animals.add(new WithSmile(new WithHeart(new Dog())));

        animals.forEach(a -> {a.speak(); System.out.println("");});

    }
}
```

```java
C:\Users\ssh1224\.jdks\azul-15.0.5\bin\java.exe -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:57352,suspend=y,server=n -javaagent:C:\Users\ssh1224\AppData\Local\JetBrains\IntelliJIdea2021.3\captureAgent\debugger-agent.jar -Dfile.encoding=UTF-8 -classpath "D:\dev\study\DS_Decorator\out\production\DS_Decorator;C:\Program Files\JetBrains\IntelliJ IDEA 2021.3\lib\idea_rt.jar" Main
Connected to the target VM, address: '127.0.0.1:57352', transport: 'socket'
냥
멍
-- Decorator를 이용해 Smail, Heart를 붙여서 실행해보자! --
냥😁
냥😍
멍😍😁
Disconnected from the target VM, address: '127.0.0.1:57352', transport: 'socket'

Process finished with exit code 0
```

### 우리 제품에 적용시킬 수 있는 케이스는 무엇이 있을까?

- 모든 점수는 원점수 기반으로 평가가 진행된다.
- 이를 표현하는 방식을 Decorate 할 수 있을 것 같다!
    - 원점수를 등급으로!
    - 원점수를 이모티콘으로!

<aside>
🎈 객체 그 자체는 변하지 않는 녀석. **핵심 비즈니스 로직으로 볼 수 있다.** 

이 핵심 비즈니스 로직을 기반으로 하면서 동적으로 새로운 책임을 추가할 수 있는 방법이데코레이터 패턴의 주요 포인트이지 않을까?!

</aside>

![image](https://user-images.githubusercontent.com/18654358/157132405-8bcf75dc-ef6c-4af7-a96a-1d03a24711bb.png)

---

### 활용성

- 동적으로 또한 투명하게(transparent), 다시 말해 **다른 객체에 영향을 주지 않고 개개의 객체에 새로운 책임을 추가하기 위해 사용한다.**
- 제거될 수 있는 책임에 대해 사용한다.
- 실제 상속으로 서브 클래스를 계속 만드는 방법이 실질적이지 못할 때 사용한다.
    - 너무 많은 수의 독립된 확장이 가능할 때 모든 조합을 지원하기 위해 이를 상속으로 해결하면 클래스 수가 폭발적으로 많아지게 된다.
    - 아니면 클래스 정의가 숨겨진다던가, 그렇지 않더라도 서브 클래싱을 할 수 없게 될 수 있다.
    

### 구조

![image](https://user-images.githubusercontent.com/18654358/157132441-d0e2910a-2f2b-46bd-9908-3cdd763b6df8.png)

### 참여자

**Component**

- 동적으로 추가할 서비스를 가질 가능성이 있는 객체들에 대한 인터페이스

**ConcreateComponent**

- 추가적인 서비스가 실제로 정의되어야 할 필요가 있는 개체

**Decorator**

- Component 객체에 대한 참조자를 관리하면서 Component에 정의된 인터페이스를 만족하도록 인터페이스를 정의

**ConcreateDecorator**

- Component에 새롭게 추가할 서비스를 실제로 구현하는 클래스

### 관련 패턴

**Decorator 패턴은 Adapter 패턴과 관련되어 있다.**

Decorator는 어쩌면 일종의 Adapter 이다.

***즉, 원래의 Adapter는 인터페이스를 변경시켜주는 것이었지만, Decorator는 객체의 책임, 행동을 변화시킨다.***

**Composit 패턴과도 관련된다.**

Decorator는 한 구성요소만을 갖는 복합체로 볼 수 있다.

그러나 이 목적은 객체의 합성이 아니라 객체에 새로운 행동을 추가하기 위한 것이다.

**Strategy 패턴과도 관련된다.**

Decorator는 객체의 겉모양을 변경하고, Strategy는 객체의 내부를 변화시킨다.

- 이 둘 다 객체를 변경하는 두 가지 다른 대안인 셈이다.
