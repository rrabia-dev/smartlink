# SmartLink — URL Kısaltma & Analitik API

SmartLink, uzun ve karmaşık URL'leri kısa kodlara dönüştüren, her tıklamayı kaydeden ve analiz eden bir backend REST API'sidir. Bit.ly gibi servislerin arka planında çalışan sistemi sıfırdan Java ile inşa etmek amacıyla geliştirilmiştir.

## Proje Hakkında

Bu proje, modern backend geliştirme pratiklerini öğrenmek ve uygulamak amacıyla geliştirilmektedir. Temel URL kısaltma işlevinin yanı sıra tıklama analitiği, önbellekleme ve güvenlik özellikleri de eklenmesi planlanmaktadır.

Projenin ilham kaynağı: Bit.ly, TinyURL gibi servislerin arka planda nasıl çalıştığını anlamak ve aynı sistemi sıfırdan inşa etmek.

## Özellikler

- Uzun URL'leri benzersiz 6 karakterlik koda dönüştürme
- Kısa kod ile orijinal URL'ye otomatik yönlendirme (302 redirect)
- Her tıklamada sayaç artırma
- H2 bellek içi veritabanı (geliştirme ortamı için)
- PostgreSQL'e geçişe hazır mimari

## Teknolojiler

| Teknoloji | Kullanım Amacı |
|---|---|
| Java 17 | Ana programlama dili |
| Spring Boot 4.0 | Backend framework |
| Spring Data JPA | Veritabanı işlemleri |
| H2 Database | Geliştirme ortamı veritabanı |
| Lombok | Boilerplate kod azaltma |
| Maven | Bağımlılık yönetimi ve build |

## Kurulum ve Çalıştırma

### Gereksinimler
- Java 17 veya üzeri

### Adımlar

1. Repoyu klonla:
```bash
git clone https://github.com/rrabia-dev/smartlink.git
cd smartlink
```

2. Uygulamayı başlat:
```bash
./mvnw spring-boot:run
```

3. API `http://localhost:8080` adresinde çalışmaya başlar.

## API Kullanımı

### URL Kısalt
Uzun bir URL'yi sisteme gönderirsin, sistem sana kısa bir kod üretir ve veritabanına kaydeder.

Başarılı yanıt:
```json
{
  "id": 1,
  "originalUrl": "https://www.cok-uzun-bir-adres.com/kategori/alt-kategori?param=deger",
  "shortCode": "Ds94WF",
  "clickCount": 0,
  "createdAt": "2026-05-01T12:39:49"
}
```

### Kısa Linkle Yönlendirme
Kısa kodu kullanarak orijinal adrese yönlendirilirsin. Her çağrıda tıklanma sayısı bir artar.