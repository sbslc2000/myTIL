# 람다식 (Lambda Expression)

* 람다식의 도입으로 인해 자바는 객체지향언어인 동시에 함수형 언어가 되었다.

## 람다식이란
람다식은 메서드를 하나의 식(expression)으로 표현한 것이다.

람다식은 함수를 간략하면서도 명확한 식으로 표현할 수 있게 해준다.

메서드를 람다식으로 표현하면 메서드의 <b>이름</b>과 <b>반환값</b>이 없어지므로, 람다식을 '익명 함수(anonymous function)' 이라고도 한다.

```java
///람다식의 예


int [] arr = new int[5];
Arrays.setAll(arr, (i) -> (int)(Math.random()*5)+1);


/* 위 람다식이 하는 일을 함수로 표현하면
int method() {
    return (int)(Math.random()*5)+1;
}
*/
```

람다식의 장점
1. 간결하면서도 이해가 쉽다.
2. 자바의 모든 메서드는 클래스에 포함되어야 하므로 클래스와 객체를 생성한 후에 메서드를 호출하는게 기본이지만, 람다식은 모든 과정 없이 자체만으로 메서드의 역할을 대신할 수 있다.
3. 메서드의 매개변수로 람다식이 들어갈 수 있고, 메서드의 결과로 반환될 수 있다. -> 메서드를 변수처럼 다루는 것이 가능하다.


## 람다식 작성법
1. 메서드에서 이름과 반환타입을 제거하고
2. 매개변수 선언부와 몸통 {} 사이에 '->'을 추가한다.

```java
    반환타입 메서드이름(매개변수 선언) {
        문장들
    }

    (매개변수 선언) -> {
        문장들
    }
```


```java 
//original max method
int max(int a, int b){
    return a > b ? a : b;
}

//lambda max method 
(int a, int b) -> { return a > b ? a : b; }
```


3. 반환값이 있는 메서드의 경우 return 문 대신 식 'expression'으로 대체할 수 있다. 
```java
//return문 사용한 lambda
(int a, int b) -> { return a > b ? a : b;}

//return문 생략한 lambda
(int a, int b) -> a > b ? a : b
```
 * 연산결과가 자동으로 반환값이 된다.
 * 문장(statement)가 아닌 식(expression) 이므로 Semicolon(;) 이나 stateblock({}) 을 생략할 수 있다.

4. 람다식의 선언된 매개변수의 타입이 추론이 가능한 경우는 생략할 수 있다. 
```java
//매개변수의 타입을 생략하지 않은 lambda
(int a, int b) -> a > b ? a : b

//매개변수의 타입을 생략한 lambda
(a, b) -> a > b ? a : b

```
* 대부분의 경우에 생략 가능하다. 람다식에 반환타입이 없는 이유도 항상 추론이 가능하기 때문이다.
* 여러개의 매개변수중 하나의 타입만 생략하는 것은 허용되지 않는다.

5. 매개변수의 타입이 없으면서 매개변수가 하나뿐인 경우에는 괄호()를 생략할 수 있다.

```java
//괄호를 생략하지 않은 lambda
(a) -> a * a

//괄호를 생략한 lambda
a -> a * a
```
* 매개변수의 타입이 있다면 생략할 수 없다.

6. 괄호{} 안의 문장이 하나일 때는 괄호{} 를 생략할 수 있다.
```java

//괄호{} 생략 전
(String name, int i) -> {
    System.out.println(name+" = "+i);
}

//괄호{} 생략 후
(String name, int i) -> System.out.println(name+" = "+i)

```
* 이때 문장의 끝에는 ';'을 붙이지 않아야 한다.

## 함수형 인터페이스

자바에서의 람다식은 익명 클래스의 객체와 동일하다.

람다식의 메서드를 실행시키려면 자바의 특성상 참조변수가 있어야 했고,

그 과정에서 하나의 메서드가 선언된 인터페이스를 정의해서 사용하는 것이 기존의 자바의 규칙들을 어기지 않으면서도 자연스러웠기 때문에

인터페이스를 통해 람다식을 다루기로 결정되었으며, 람다식을 다루기 위한 인터페이스를 '함수형 인터페이스'라고 부르기로 했다.

```java
@FunctionalInterface // 오직 하나의 추상 메서드만 정의되어 있도록 제약을 건다.
//static 메서드나 default 메서드의 개수에는 제약이 없다.
interface MyFunction { // 함수형 인터페이스 MyFunction을 정의

    public abstract int max(int a, int b); // 물론 public abstract는 생략해도 무방하다.
}
```

아래는 함수형 인터페이스 Comparator<String> 을 람다식을 통해 작성하게 되는 전후 비교 코드이다.

```java
//기존의 방식
List<String> list = Arrays.asList("a","b","c","d","e");

Collections.sort(list, new Comparator<String>() {
    public int compare(String s1, String s2) {
        return s2.compareTo(s1);
    }
})

//lambda 를 적용한 방식
Collections.sort(list,(s1,s2) -> s2.compareTo(s1));

```

### 함수형 인터페이스 타입의 매개변수와 반환 타입

메서드의 매개변수가 함수형 인터페이스 타입이면, 메서드를 호출할 때 람다식을 참조하는 참조변수를 매개변수로 지정해야 한다.

```java
@FucntionalInterface
interface MyFunction {
    void myMethod();
}

... 


//함수형 인터페이스를 매개변수로 갖는 메서드
void someMethod(MyFunction f) {
     f.myMethod();
}

...

MyFunction f = () -> System.out.println("myMethod()");
someMethod(f);

//참조변수 없이 직접 람다식을 작성할 수도 있음
someMethod(()->System.out.println("람다식 직접 작성 myMethod()"));

```

메서드의 반환타입이 함수형 인터페이스라면, 람다식을 참조하는 변수를 반환하거나 람다식을 직접 반환할 수 있다.

```java
@FucntionalInterface
interface MyFunction {
    void myMethod();
}

... 

MyFunction method2() {
    MyFunction f = () -> {};
    return f;
    // return () -> {}; 람다식 직접 반호나
}
```
람다식을 참조변수로 다룰 수 있다는 것은 메서드를 통해 람다식을 주고받을 수 있다는 것이고, 즉 변수처럼 메서드를 주고받는 것이 가능해졌다는 것이다.

사실상 메서드가 아닌 객체를 주고받는 것이라 근본적으로 달라진 것은 없지만, 코드가 더 간결해지고 이해하기 쉬워졌다.

### 람다식 타입의 형 변환

함수형 인터페이스로 람다식을 참조할 수 있는 것일 뿐, 람다식의 타입이 함수형 인터페이스의 타입과 일치하는 것은 아니다. 

람다식은 익명 객체이고, 익명 객체는 타입이 없다. (정확히는 타입은 있지만 컴파일러가 그 이름을 임의로 정하기 때문에 알 수 없다.)

따라서 대입 연산자를 사용할 때에 양변의 타입을 일치시키기 위해서는 형변환이 필요하다.

```java
MyFunction f = (MyFunction)( () -> {} ); //생략 가능
//Object obj = (Object) (()->{}); 불가능, 람다식은 오직 함수형 인터페이스로만 형변환이 가능 

```
람다식은  MyFunction 인터페이스를 직접 구현하지 않았지만, 이 인터페이스를 구현한 클래스의 객체와 완전히 동일하기 때문에 위와같은 형변환을 허용한다. 위 형변환은 생략 가능하다.


### 외부 변수를 참조하는 람다식

람다식은 익명 클래스의 인스턴스이므로 람다식에서 외부에 선언된 변수에 접근하는 규칙은 익명클래스에서의 접근규칙과 동일하다.

```java
@FunctionalInterface
interface MyFunction {
    void myMethod();
}

class Outer {
    int val = 10;

    class Inner {
        int val = 20;

        void method(int i) {
            int val = 30; //final 임
            i = 10; // 에러. 상수의 값을 변경할 수 없음

            MyFunction f = () -> {
                System.out.println("i = "+i);
                System.out.println("val = "+val);
                System.out.println("this.val = "+ ++this.val);
                System.out.println("Outer.this.val = "+ ++Outer.this.val);
            };

            f.myMethod();
        }
    } // end of Inner
} // end of Outer


class LambdaEx {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);
    }
}

/* 실행결과
i = 100
val = 30
this.val = 21
Outer.this.val = 11

*/

```

* 람다식 내에서 참조하는 지역변수는 final이 붙지 않았어도 상수로 간주된다.
* 외부 지역변수와 같은 이름의 람다식 매개변수는 허용되지 않는다.

## java.util.function 패키지

java.util.function 에는 매개변수와 반환타입에 따라 자주 쓰이는 형식의 메서드를 함수형 인터페이스로 미리 정의해 놓았다.
 * 매번 새로운 함수형 인터페이스를 사용하는 것 대신, 가능하다면 이 패키지의 인터페이스를 활용하는것이 권고된다. -> 메서드의 이름 통일, 재사용성이나 유지보수 측면에서 좋음

 | 함수형 인터페이스 | 메서드 | 설명|
 |---|---|---|
 java.lang.Runnable | void run() | 매개변수와 반환값이 없음
 Supplier<T> | T get() | 반환값만 있음
 Consumer<T> | void accept(T t) | 매개변수만 있음
Function<T,R> | R apply(T t) | 하나의 매개변수를 받아서 결과를 반환
Predictate<T> | boolean test(T t) | 조건식을 표현하는데 사용됨. 매개변수1개, 반환 타입은 boolean


* T는 Type, R은 Return Type 의 약자이다.
    

* Predicate
Predicate는 Function의 변형으로 , 반환타입이 boolean이라는 점만 다르다.

```java
Predicate<String> isEmptyStr = s -> s.length() == 0;
String s = "";

System.out.println(isEmptyStr.test(s)); // result : true

```

* 매개변수가 2개인 함수형 인터페이스는 이름 앞에 "Bi" 가 붙는다.

    * Consumer<T> --->  BiConsumer<T,U>
    * Function<T,R> ---> BiFunction<T,U,R>
    * Predicate<T> ---> BiPredicate<T,U>
      * U는 알파벳 T의 다음 문자이기 때문에 사용되었다. 
      * Supplier의 경우 매개변수가 없기 때문에 BiSupplier 역시 존재하지 않는다.


* 2개 이상의 매개변수를 갖는 함수형 인터페이스가 필요하다면 직접 만들어서 써야한다.

## 컬렉션 프레임워크에서 사용되는 함수형 인터페이스
https://inma.tistory.com/153
모 블로그 링크로 대신함.

## Function의 합성과 Predicate 의 결합

java.util.function 패키지의 함수형 인터페이스에는 추상메서드 외에도 디폴트 메서드와 static 메서드가 정의되어 있다.

```java
//Function
default <V> Function<T,R> andThen(Function<? super R, ? extends V> after)
default <V> Function<V,R> compose(Function<? super V, ? extends T> before)
static <T> Function<T,T> identity()

//Predicate
default Predicate<T> and(Predicate<? super T> other)
default Predicate<T> or(Predicate<? super T> other)
default Predicate<T> negate()
static <T> Predicate<T> isEqual(Object targetRef)
```

* Function의 합성
  * f.andThen(g) 는 f를 먼저 적용한 후 g를 적용하며,
  * f.compose(g) 는 g를 먼저 적용한 후 f를 적용한다.
  * f.identity() 는 항등함수가 필요할 때 사용한다.

```java
//Function 합성 예시
Function<String,Integer> f = (s) -> Integer.parseInt(s,16); // 문자열을 숫자로 변환하는 함수
Function<Integer,String> g = (i) -> Integer.toBinaryString(i); // 숫자를 2진 문자열로 변환하는 함수

Function<String,String> h = f.andThen(g);
//h는 문자열을 2진 문자열로 반환하는 함수 ( "FF" -> 255 -> 1111111 )

Function<Integer, Integer> i = f.compose(g);
// i 는 숫자를 16진수로 변경하는 함수 ( 2 -> "10" -> 16)
```


* Predicate의 결합

여러 Predicate를 사용하여 하나의 새로운 Predicate로 사용할 수 있다.

```java
Predicate<Integer> p = i -> i < 100;
Predicate<Integer> q = i -> i < 200;
Predicate<Integer> r = i -> i % 2 == 0;
Predicate<Integer> notP = p.negate() // i >= 100;

Predicate<Integer> all = notP.and(q.or(r));
// i >= 100 && ( i < 200 | i % 2 == 0 )
```

``` java
//isEqual() 사용법
// isEqual() 의 매개변수로 비교대상을 하나 지정하고, test() 의 매개변수로 비교할 값을 넣는다.

Predicate<String> p = Predicate.isEqual("hi");
boolean result = p.test("hello");//false

```


### 메서드 참조

람다식이 <b>하나의 메서드만 호출하는 경우</b>에 '메서드 참조(method reference) 라는 방법으로 람다식을 간단히 할 수 있다.

예를들어, 문자열을 정수로 변환하는 람다식에 관하여 얘기하자면

```java
Function<String , Integer> f = (String s) -> Integer.parseInt(s);
```

위 상황에서 파라미터인 s를 받아 parseInt에 넘겨주는 코드 부분은 거추장스러울 뿐더러 컴파일러가 알아서 처리할 수 있을 정도로 추정이 쉽다.

따라서 아래와 같이 수정하여 사용할수 있게 하였다.
```java
Function<String, Integer> f = Integer::parseInt;
```

두번째 예시를 보자면

 ```java
Bifunction<String,String,Boolean> f = (s1,s2) -> s1.equals(s2);
 ```

위 코드는 참조변수의 타입에서 두개의 String 타입의 매개변수를 받는다는 것을 알 수 있으므로, 메서드 참조를 사용할 수 있다.

```java 

BiFunctional<String,String,Boolean> f = String::equals;
//매개변수를 생략하면 equals만 남는데, equals 라는 클래스는 다른 클래스에도 존재할 수 있기 때문에 클래스 이름은 반드시 필요하다.
```

* 이미 생성한 객체의 메서드를 람다식에서 사용할 때에도 사용할 수 있으며, 이 경우에는 클래스 이름 대신 참조변수를 적어서 사용할 수 있다.

```java
MyClass obj = new MyClass();
Function<String, Boolean> f = x -> obj.equals(x);

Function<String,Boolean> f2 = obj::equals;
```
