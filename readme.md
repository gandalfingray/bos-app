## 공통 기능 해야 할 일
#### 다국어 처리
  - 서버 다국어 처리 방법
	- 1안 : 
	  - 클라이언트 locale 전송 방법 : 
	    - 클라이언트 요청마다 cookie 값에 locale을 입력해서 보낸다.(Or else...)
	    - 혹은 locale 상태 변경 api를 만들고 화면에서 locale 변경시에 이 api를 호출, 서버는 session 객체에 해당 값을 저장하도록 한다.
	  - 서버 프로그램에서 locale 값을 얻기 위해서는 session 객체의 locale attribute를 조회해서 사용한다.
	  - 메시지
	    - 서버에서 생성하여 client로 전송하는 메시지는 hard coding 하지 않고 메시지 ID를 부여하고 해당 ID에 메시지 내용을 할당한다.
	    -  
	  
  - 클라이언트 다국어 처리 방법
    - 언어별 사전 파일에 용어를 등록한다.
    - locale 선택하는 기능은 공통으로 제작한다.
    - 개별 화면에서는 static caption을 처리하는 영역에 공통함수 formatMessage("keyId")를 사용하는 형태로 호출하여 사용한다.
    - locale 별로 표시 방식이 서로 다른 항목을 위한 공통함수를 제공한다.(날짜 표시, 금액 표시 등)
  
#### 화면 공용 Data 및 함수용 js
  - Flags
    - YN
	- 날짜 선택 : 년도, 월(1~12), 일(1~31)
	- 전화번호 : 지역번호 선택, 통신사 선택
	- 등
 
  - 공통 Popup
    - 주소 선택
    
  - 공통 함수(Utility)
    - 공통 코드 조회용 DataSource 반환 모듈(완료) -> Lookup 셀 선택 시 코드 값이 보이는 문제 해결 필요(해결)
  
  - 공통 기능
    - Grid 엑셀 열 복사/그리드 붙이기
  
#### API I/O 표준
  - API 호출 공통 모듈 for UI
  - API Return 표준 객체 정의
  - 에러 Return 객체 정의 예시) ( error_code : "er0001", error_message : "*** 에러입니다. ***에게 문의하세요"}
  - Error를 return 받았을 때 표준 UI 처리 방법
    에러 유형에 따라 다르겠다
	- 로그인 에러는 로그인 화면으로 forward
	- 그외의 에러는 메시지를 화면에 띄운다.

#### 메시지 처리

#### 암호화
  - 비밀번호 저장(단방향 Hash 암호화)
  - 개인정보 관련 데이터 암호화(양방향 암복호화)
  - AWS 환경 설정 KMS 사용 DB 계정/비밀번호 암호화(applicatiom.yml 파일)

#### 외부 HTTP 서비스 호출(다른 Microservice 호출)


#### API 실행 로그 처리(Filter or Interceptor..)
  - API 접근자, 호출 URL, input parameter data( 부분 암호화 필요...), 수행 일시, 조회 결과도 저장 필요한가? 암호화해서...(?)

#### 사용자 관리 및 로그인 화면/API
  - 사용자 등록 요청 화면
  - 사용자 등록 승인 처리 화면(관리자)
  - 사용자 정보 조회/변경 화면(자기 정보 조회)
  - 비밀번호 변경
  - 

#### 공통코드 관리 화면/API

#### 메뉴/화면 관리 화면/API

#### 권한관리 화면/API



#### HTML Templating 작업
  - 목록 type 1 : 조회조건 + DataGrid
  - 목록 type 2 : 조회조건 + DateGrid 자체 Control(추가, 저장, 취소, 검색) + DataGrid
  - 목록 type 3 : DateGrid 자체 Control(추가, 저장, 취소, 검색) + DataGrid 
  - 상세 type 1 : 목록 => Modal Popup(저장, 삭제) : 2 column type
  - 상세 type 1-1 : 목록 => Modal Popup(저장, 삭제) : 3 column type
  - 상세 type 2 : Menu => 상세 화면 : 2 column type
  - 상세 type 2-1 : Menu => 상세 화면 : 3 column type
  - 목록 + 상세 type 1 : 조회조건 + Master DataGrid + Detail DatGrid (조회조건은 Master에만 해당)
  - Component 모음 page
  
- and so on .....