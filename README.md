# 2021_DI_Project_Android

## 개요
- DI 학습을 위한 토이 프로젝트

## 개발 내용(Basic)
- Github Api를 이용하여 유저 리스트를 보여줌
- 유저 선택시 상세한 정보 조회 가능(팔로워, 팔로잉, 스타, 레포 목록)
- 깃 허브 oAuth를 통한 로그인
- 웹 뷰를 통해 상세 정보 세부 페이지를 보여주도록 함

## 개발 내용(Add-ons)
- 유저정보, 썸네일 캐싱하여 추후 로딩 속도 증대
- 로그인 정보 기억하여 자동로그인 기능 구현
- 저장 토큰 암호화

## 사용할 기술 및 구조
- 언어 : Kotlin
- 빌드툴 : Gradle 6.5-all
- JDK : 안드로이드 스튜디오 내장
- minSdk : 22(5.1 롤리팝, 기기 92.3% 커버)
- target, compilSdk : 30(11.0 R, 최신)
- 비동기 처리 : RxJava
- 아키텍쳐 패턴 : MVVM
- Dependency Injection : Dagger2
- DB : Preference(설정 등 경량데이터), Room
- 네트워크 : retrofit
- 이미지 처리: glide
