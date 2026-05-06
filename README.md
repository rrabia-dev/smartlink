# SmartLink — URL Kısaltma & Analitik API

SmartLink, uzun ve karmaşık URL'leri kısa kodlara dönüştüren, her tıklamayı kaydeden ve analiz eden bir backend REST API'sidir. Bit.ly gibi servislerin arka planında çalışan sistemi sıfırdan Java ile inşa etmek amacıyla geliştirilmiştir.

## Özellikler

- Uzun URL'leri benzersiz 6 karakterlik koda kısaltma
- Kısa kod ile orijinal URL'ye otomatik yönlendirme (302 redirect)
- Her tıklamada sayaç artırma
- Link istatistikleri endpoint'i
- IP bazlı rate limiting — dakikada 10 istek limiti
- Anlamlı hata mesajları (404, 429)
- Swagger UI ile API dökümantasyonu
- PostgreSQL ile kalıcı veri saklama

## Teknolojiler

| Teknoloji | Kullanım Amacı |
|---|---|
| Java 17 | Ana programlama dili |
| Spring Boot 4.0 | Backend framework |
| Spring Data JPA | Veritabanı işlemleri |
| PostgreSQL | Kalıcı veritabanı |
| Bucket4j | Rate limiting |
| Lombok | Boilerplate kod azaltma |
| Swagger / OpenAPI | API dökümantasyonu |
| Maven | Bağımlılık yönetimi |

## Kurulum

### Gereksinimler
- Java 17+
- PostgreSQL 16

### Veritabanı Kurulumu

PostgreSQL'de `smartlink` adında bir veritabanı oluştur:

```sql
CREATE DATABASE smartlink;
```

### Uygulama Ayarları

`src/main/resources/application.properties` dosyasını düzenle:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/smartlink
spring.datasource.username=postgres
spring.datasource.password=SENIN_SIFREN
```

### Çalıştırma

```bash
./mvnw spring-boot:run
```

API `http://localhost:8080` adresinde çalışmaya başlar.

## API Kullanımı

### Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

### URL Kısalt

```
POST /api/shorten
Content-Type: text/plain
Body: https://www.uzun-adres.com
```

Yanıt:
```json
{
  "id": 1,
  "originalUrl": "https://www.uzun-adres.com",
  "shortCode": "Ds94WF",
  "clickCount": 0,
  "createdAt": "2026-05-01T12:39:49"
}
```

### Yönlendirme

```
GET /api/{shortCode}
```

Orijinal URL'ye yönlendirir, tıklanma sayacını artırır.

### İstatistik

```
GET /api/stats/{shortCode}
```

Linkin tıklanma sayısı ve detaylarını döndürür.

## Rate Limiting

Aynı IP adresinden dakikada en fazla 10 istek gönderilebilir. Limit aşılınca `429 Too Many Requests` hatası döner.

## Proje Yapısı

```
src/
└── main/
    └── java/com/smartlink/smartlink/
        ├── controller/      # HTTP isteklerini karşılar
        │   └── UrlController.java
        ├── filter/          # Rate limiting
        │   └── RateLimitFilter.java
        ├── model/           # Veritabanı entity sınıfları
        │   └── ShortUrl.java
        ├── repository/      # Veritabanı sorgu arayüzleri
        │   └── ShortUrlRepository.java
        └── service/         # İş mantığı
            └── UrlService.java
```
