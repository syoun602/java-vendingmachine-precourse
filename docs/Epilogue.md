# 미션 회고
이번 미션은 예외사항과 클래스 책임을 나누는데 많은 시간을 쏟았습니다.

## 예외사항
 우선 예외 사항은 아래와 같이 정리해 두었습니다. 
 ```
금액 입력:
- 예외: 금액 입력 시, 금액은 숫자여야 한다.
- 예외: 금액 입력 시, 금액은 양수여야 한다.
- 예외: 금액 입력 시, 금액은 10원으로 나누어 떨어져야 한다.

추가할 상품 목록 입력:
- 예외: 추가할 상품 목록 입력 시, [상품,가격,수량];[상품,가격,수량]의 형식에 맞추어야 한다.
  - 예외: 상품, 가격, 수량은 콤마(,)로 구분되어야 한다.
  - 예외: 개별 상품은 대괄호([])로 묶어, 세미콜론(;)으로 구분되어야 한다.
  - 예외: 상품 가격은 100원 이상이며 10원으로 나누어 떨어져야 한다.

구매할 상품 입력:
- 예외: 구매할 상품 입력 시, 자판기에 존재하는 상품이어야 한다.
- 예외: 구매할 상품 입력 시, 상품 수량이 남아 있어야 한다.
- 예외: 구매할 상품 입력 시, 잔여 금액이 상품 가격보다 많거나 같아야 한다.
```
처음에는 `[상품명,가격,수량];[상품명,가격,수량]`에 대한 정규식으로 
> ^(\\[[A-Za-z0-1가-힣]+,[1-9]+[0]+,[1-9]+[0-9]*\\])(;\\[[A-Za-z0-1가-힣]+,[1-9]+[0]+,[1-9]+[0-9]*\\])*

 위와 같은 하나의 정규식을 이용해 상품 입력 예외를 처리하려 했으나, 상품 입력에 워낙 다양한 예외가 발생할 수 있어, 각각 다른 오류 메시지를 출력하고자 정규식을 간소화하고 발생할 수 있는 오류별로 예외를 처리하도록 변경했습니다. 하여 더 복잡한 코드의 상품 입력 검증 과정이 요구됐지만, 사용자 입력 시, 어느 부분을 잘못 입력한건지 직관적으로 알 수 있도록 변경됐습니다.

또한, 이전에는 검증 과정에서 입력받은 값을 int 또는 List로 변환해 값을 return 했었으나, 검증은 변환이 아닌 검증만을 담당해야 한다고 생각하여 값을 넘겨주지 않도록 변경했습니다. 예외를 발생시키지 않았다는 것은 정상적인 입력임으로 해당 입력은 service 또는 model에서 변환되도록 수정했습니다.

## 클래스 분리
이번 미션에서는 여러 개의 클래스를 분리한 후 서로 관계를 맺어 하나의 프로그램을 만들고자 노력했습니다. 특히, 각 클래스가 담당해야할 행위만을 포함하도록 노력했으며, 이들이 상호작용하기 위해 MVC 패턴을 적극 이용했습니다. 클래스 분리는 다음과 같습니다.
```
└── vendingmachine
    ├── Application.java
    ├── controller
    │   └── MachineController.java
    ├── model
    │   ├── Coin.java
    │   ├── Product.java
    │   ├── Products.java
    │   └── VendingMachine.java
    ├── service
    │   └── MachineService.java
    ├── util
    │   ├── InputValidator.java
    │   ├── ProductValidator.java
    │   └── RandomNumberGenerator.java
    └── view
        ├── InputView.java
        └── OutputView.java
```
이전 2주차에서는 MVC 패턴을 사용해 model, view, controller로 정확히 3개로만 분리하여 controller가 너무 많은 부분을 담당했었습니다. 데이터를 어떻게 처리할지 알려줌과 동시에 비즈니스 로직 및 프로그램 흐름 등 모든 부분을 담당했는데, 더 조사한 결과 controller는 오직 중계 역할만 해야함을 알게 됐습니다. 하여, controller와 model 사이에 service layer를 추가하여 controller는 무엇을 수행하라고만 일러주고 실제 처리는 service에서 이루어지도록 변경했습니다.

이외에는 controller에서 입력 예외처리를 해주는 것이 맞는지에 대해 많이 고민했었습니다. 웹이었다면 실제로 정규식을 이용한 예외는 사용자단에서 검증되어 데이터로 넘겨받을 수 있으므로 view에서 검증하여 넘겨줄 지 아니면 view-controller를 통해 넘어온 값을 service layer에서 검증 해야되는 지에 생각해보았습니다. 하지만, view에서 사용자 화면을 나타내기 위한 동작만을 수행하게 만들고 싶었으며 service에서 처리할 경우, 재입력을 받기 위해 view를 건드려야 하므로 그대로 controller에서 처리하도록 두었습니다. 이에 대해서는 아직까지도 정확한 해답을 찾지 못하였지만, 여러가지를 고민해보며 찾아보는 과정에서 많은 배움이 있었습니다.

# 프리코스 회고
## 학습 과정과 성장한 점
프리코스에서의 지난 3주는 제게 정말 뜻깊은 시간이었습니다. 함수 분리부터 시작해 클래스 분리, 그리고 이번 3주차의 클래스를 분리한 후, 서로 관계를 맺는 하나의 프로그램을 완성하기까지 정말 많은 것을 배웠고, 그 과정이 고통보다는 즐거움으로 가득 찼습니다.

처음 1주차 과제를 맞닥뜨렸을 때, 프로그램 작성을 시작하지 못했던 것이 기억이 납니다. 단순 구현보다 객체지향 언어에 맞는 하나의 프로그램을 작성하기 위한 분리와 구조에 있어 난항을 겪었습니다. 이에 자바를 기초부터 다져야겠다는 생각으로 책 한 권을 사서 객체지향 언어란 무엇인지, 자바가 가지는 특징들이 무엇인지 차근차근 학습해 나갔습니다.

이후 제가 선택한 학습 방법은 우선 제 방식대로 구현해보는 것입니다. 지난 2주간 제가 설계한 방식으로 구현을 끝마치면, 이전 기수나 다른 교육생분들의 코드를 확인해 제 코드와 교차 검증했습니다. 그 과정에서 일급 컬렉션이나, 책임을 분리하는 방식, 여러 디자인 패턴 등 새로운 지식을 축적하여 이에 대해 찾아보고 제 코드에 적용해보는 노력을 기울였습니다. 수많은 리팩토링을 거쳐 책임을 분리하고 MVC 패턴을 이용해 각 클래스에 역할을 할당하며 변수나 함수명을 정정하여 각 클래스의 의도가 명확하도록 변경했습니다. 만족하고 내려놓더라도 다음 날이 되면 항상 욕심이 생겨 거진 매일을 걸쳐 수정했습니다.

이러한 과정에서 나날이 성장해가는 저를 볼 수 있었습니다. 특히 이번 3주차에서는 지난 경주 게임에서 처음 도입한 MVC 패턴에서 컨트롤러가 요청을 중계하는 역할 그 이상을 담당한다 생각하여 더 깊게 공부해본 후, 서비스 레이어 통해 비즈니스 로직을 따로 처리해 오직 중계 역할만을 수행하도록 단일 책임 원칙에 맞추고자 노력했습니다. 또한, 매번 제공해주신 피드백을 통해 부족한 부분을 깨닫고 이를 개선해나갔습니다. 아직도 배울 것이 수없이 많지만, 이번 기회를 통해 3주 전의 자신보다 급격히 성장하는 계기가 됐습니다. 더 좋은 개발자가 되도록 이끌어주셔서 감사합니다.


## 프리코스 이후
코드를 작성하고 수정하는 과정에서 `테스트 코드가 있었다면...`라는 생각을 정말 많이 했습니다. 로직을 수정하거나 코드를 리팩토링하는 과정에서 변경이 제대로 이루어졌는 지에 대해 확인할 때 매번 입력 과정을 거쳐서 확인하는게 굉장히 불편했습니다. 시간이 더 많았다면 테스트 코드를 작성하는 방법을 학습했겠지만, 이번 3주 동안은 자바와, 객체지향 그리고 알아보기 쉬운 코드를 만드는 것이 목표였어서 여기에 더 집중했던 것 같습니다. 앞으로도 열심히 학습하여 클린 코드와 테스트 코드를 모두 자유롭게 작성할 수 있는 사람으로 성장해나가겠습니다.