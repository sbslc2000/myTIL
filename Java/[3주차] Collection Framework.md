# Collection Framework

Collection은 여러 요소들을 담을 수 있는 자바의 표준화된 자료구조 프레임워크이다. 

컬렉션 프레임워크는 배열의 문제점을 해결하고, 잘 알려져있는 자료구조를 바탕으로 객체들을 효율적으로 추가, 삭제, 검색할 수 있게 하기 위하여 만들어졌다.

자바 초기에는 Vector, Stack, Hashtable 등의 컬렉션 클래스만 제공했으나 자바 1.2 이후로 표준적인 방식으로 컬렉션을 다루기 위한 컬렉션 프레임워크가 등장하였다. 


## 컬렉션 인터페이스 (Collection interface)

컬렉션 인터페이스들은 제네릭으로 표현된다.

컬렉션 프레임워크의 대표적인 인터페이스
* List : 순서가 있는 데이터의 집합으로, 데이터의 중복을 허용함
* Set : 순서가 없는 데이터의 집합으로, 데이터의 중복을 허용하지 않음
* Map : Key 와 Value 가 한 쌍으로 이루어지는 데이터의 집합으로, 순서가 없음. 키는 중복을 허용하지만, 값은 중복될 수 없음.

List, Set 인터페이스는 Collection 인터페이스를 상속받는다. 반면 Map 인터페이스는 구조상의 차이 (Key-Value   )로 인해 Collection 인터페이스를 상속받지 않고 별도로 정의된다.

Collection Interface에서 제공하는 주요 메소드는 아래와 같고, 인터페이스로 제공함으로써 사용자는 세부 구현방식을 몰라도 통일성있는 코드를 통해 이용할 수 있다.

Collection Interface 의 메서드.jpg


## 동기화된 컬렉션
컬렉션 프레임워크의 대부분의 클래스는 싱글 스레드 환경에서 사용할 수 있도록 설계되어있다.
따라서 여러 스레드가 동시에 컬렉션에 접근한다면 의도치 않게 데이터가 변경될 수 있는 불안정한 상태가 된다.

ArrayList, HashSet, HashMap은 대표적인 싱글스레드 기반으로 설계된 컬렉션 클래스인데, 이를 멀티 스레드 환경에서 쓸 수 있도록 하는 컬렉션 프레임워크는 비동기화된 메소드를 동기화된 메소드로 래핑하는 synchronizedXXX() 메서드를 제공한다.

```java
List<T> list = Collections.synchronizedList(new ArrayList<T>());

Map<K,V> map = Collections.synchronizedMap(new HashMap<K,V>());

Set<T> set = Collections.synchronizedSet(new HashSet<T>());
```


synchronized 메소드로 생성한 컬렉션 클래스는 synchronized 블럭으로 묶어서 사용해야 한다.
```java
List<T> list = Collections.synchronizedList(new ArrayList<T>());

//...

//사용시에 동기화 시켜 줍니다. (synchronized 블럭)
synchronized(list) {
    list.add(...);
}
```

