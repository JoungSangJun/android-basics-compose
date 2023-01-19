### 📚 Lemonade 앱 개요

4가지 state가 있다. 처음 앱은 SELECT 상태로 시작된다.

SELECT 상태에서 이미지를 클릭하면, SQUEEZE 화면으로 바뀌고, squeezeCount가 0으로 초기화되고, lemonSize가 2~4 사이 랜덤 숫자로 지정된다.

SQUEEZE 상태에서 이미지를 클릭하면, 클릭할 때 마다. squeezeCount는 +1, lemonSize는 -1이 되어, lemonSize가 0이 되는 순간 DRINK 상태로 전환한다.

DRINK 상태에서 이미지를 클릭하면 RESTART 상태로 전환하고, lemonSize=-1이 된다.

RESTART 상태에서 이미지를 클릭하면 SELECT로 돌아간다.
---
<img width="379" alt="스크린샷 2023-01-19 오후 10 02 33" src="https://user-images.githubusercontent.com/73929644/213450376-d9680d94-bf3c-4a1d-bb13-130f769389cf.png">
<img width="379" alt="스크린샷 2023-01-19 오후 10 02 37" src="https://user-images.githubusercontent.com/73929644/213450403-b7f9e2f9-068c-42e6-a010-6c49a08a3f2a.png">
<img width="379" alt="스크린샷 2023-01-19 오후 10 02 50" src="https://user-images.githubusercontent.com/73929644/213450420-34862ddc-0230-4bb1-a8c1-062dd804b1fe.png">
<img width="379" alt="스크린샷 2023-01-19 오후 10 02 54" src="https://user-images.githubusercontent.com/73929644/213450428-c703e900-3c7f-4860-90f5-d5a830bf6d26.png">
