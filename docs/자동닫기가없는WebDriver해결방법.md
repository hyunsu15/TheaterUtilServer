# 요약
- 함수형인터페이스를 활용해 문제해결함.
- 메소드이름이 진짜 중요하다고 다시 한번 느낌.
- 라이브러리 개발자의 하위호환대응에 새삼 놀랐다.

# 문제
셀리니움은 오토 클로징을 따로 지원하지않는다.
그렇기에 끄는 것을 신경써줘야한다.
하지만 일일히 내가 껏는지 확인해줘야한다면, 너무 귀찮은 일이라고 생각했다.
그래서 이것을 공통로직으로 만들수 있다고 생각했다.

# 방안
## (채택) CustomWebDriverTemplate 선언
```declarative
public class CustomWebDriverTemplate {
    public  <T,R> R execute(BiFunction<T, ? super WebDriver, R> fun,T input){
        WebDriver driver = getSettingDriver();
        try{
            return fun.apply(input,driver);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BaseException("셀리니움 에러.\n"+e.getMessage());
        }finally {
            driver.quit();
        }
 }
...
```
호출예시
```declarative
customWebDriverTemplate.execute(useCase::execute,signIn)
```

# 이득
webdriver.quit를 신경안써도 된다.

# TMI
## 메소드 이름을 중복안되고 직관적으로 짓어야한다는것을 새삼 느꼈다.
나는 꽤나 완성도가 있는 셀리니움이 autoCloseable을 지원하지 않은 것에 새삼 놀랐다. 
이 이유를 찾아보니, autoCloseable를 지원하기 전부터 close()를 메소드가 있었고, 다른 용도로 사용하고 있다고한다. 
하위호환문제로 closeable를 사용할수 없다고한다.

하위호환을 위해, 기능을 그대로 지원하는 것으로 보인다.
출처: https://hello70825.tistory.com/541