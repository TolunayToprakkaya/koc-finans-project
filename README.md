# koc-finans-project
Projemiz aşağıdakiler bileşenleri içermektedir.<br>
- Discovery Server (Spring Eureka) 
- Config Server (Spring Cloud Config) 
- API Gateway
- Customer Service
- Credit Score Service

Projemizde PostgreSQL kullanılmıştır. **kocfinans** isminde bir veritabanı oluşturulduğu takdirde tablolar ve kolonlar otomatik olarak oluşacaktır. <br>
**cust**, **crd_scr** ve **crdt_scr_st** olmak üzere 3 adet tablomuz bulunmaktadır. <br>
- cust
  * cust id
  * identity number
  * name
  * lastname
  * monthly income
  * phone
  * current credit score
  * current limit
  * create date
  * update date
  
- crd_scr
  * credit score id
  * cust id
  * credit score status id
  * create date
  * update date
  
- crdt_scr_st
  * credit score status id
  * name
  * create date
  * update date

## API
- http://localhost:9191/customer/inquireCreditScore <br>
Aşağıdaki gibi bir örnek request ile istenilen logic işlemlerini yapar ve geriye kullanıcı bilgilerini ve limitini ve kredi skorunun türünü (Onay, Red) döndürür. <br>
**Api Detayı ve Örnek Request:**
```json
POST 
Content-Type: application/json

{
    "identityNumber": 11111112127,
    "name": "Ad",
    "lastName": "Soyad",
    "monthlyIncome": 1000,
    "phone": 0001112233
}
```
- http://localhost:9191/creditScore/calculateCreditScore <br>
Kullanıcıya kredi skorunu döndürür. <br>
**Api Detayı:**
```json
GET 
Content-Type: application/json
```  
- http://localhost:9191/creditScore/{custId} <br>
Kullanıcının id' sine göre kredi skorunu ve durumunu getirir. <br>
**Api Detayı:**
```json
GET 
Content-Type: application/json
```  
- http://localhost:9191/creditScore/save <br>
Kredi skorunu kaydeder. <br>
**Api Detayı ve Örnek Request:**
```json
POST 
Content-Type: application/json

{
    "customerId": 1,
    "creditScoreStatusId": "1"
}
```

