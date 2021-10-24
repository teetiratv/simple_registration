# Simple Registration

Application ที่ใช้ในการลงทะเบียนแล้ว ยืนยันตัวตน (แบบ basic) ปล.ในส่วนของการ Test ผมจะ Test แค่ตามโจทย์ที่ให้มานะครับในส่วนของการทำ Auth ผมไม่ได้ทำ เพราะ code บางส่วนผมก็ไม่ Copy มา ครั้งแรกเลยที่ผมทำในส่วนของการสร้าง token ปกติใช้อย่างเดียว แล้วก็เป็นแบบ basic นะครับ ไม่มีการทำสิทธิใดๆ 

## Getting Started

โปรเจคนี้ทำขึ้นมาเพื่อทดสอบเท่านั้น! จะเป็น Java Application (Spring) ที่ใช้ในการลงทะเบียนและเชื่อมต่อเข้ากับ Database
Postgres

### Prerequisites

โปรแกรมที่ใช้ในการจัดการ Application

```
- Container Runtime(Docker,etc)
- Docker Compose
- Java Version 17
- Maven 3
```

### Installing

Docker Compose >= 3

```
$ docker compose up --build -d 
```

Docker Compose < 3

```
$ docker-compose up --build -d 
```

## Running the tests

### Unit Test

```
$ mvn clean test
```

### Unit Test And Build Report Coverage

```
$ mvn clean verify
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Acknowledgments

* Container
* Spring
* JPA
* Rest
* Java