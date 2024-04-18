# widyu_healthcare

# 변수 네이밍 룰
### _기본적으로 모듈명(seniors, guardians 등) 경우는 데이터 생략_
1. controller : 외부에 노출가능한 함수명 (기획자가 들어도 알만한 이름)
- add + 데이터
- update + 데이터
- get + 데이터
- 기타 기능명 : login, register, logout 등
2. service : API 서버에서 이뤄진 행위의 최종 결과
- get + 목표 데이터
- update + 목표 데이터
- add + 목표 데이터
- delete + 목표 데이터
- check + 목표 데이터
3. mapper : DB 서버에서 이뤄진 행위 
- insert + 데이터
- update + 데이터
- find + 데이터 + By + 조건
- isDuplicated + 데이터 : 중복 여부
- isExisted + 데이터 : 존재 여부  