# Case Study

Modern Android teknolojileri kullanılarak geliştirilmiş, **gerçek zamanlı** bir restoran sipariş yönetimi uygulamasıdır. Restoran sahipleri profillerini yönetebilir, WebSocket üzerinden gelen siparişleri anlık olarak görüntüleyebilir ve durumlarını güncelleyebilir.

## 📱 Özellikler

* **Kimlik Doğrulama:** Kullanıcı kayıt ve giriş işlemleri (Token bazlı).
* **Gerçek Zamanlı Sipariş:** Pusher WebSocket entegrasyonu ile `order.created` olaylarını dinleyerek anlık bildirim.
* **UI:** Kullanıcı deneyimini artırmak için anlık arayüz güncellemeleri.
* **Akıllı Yönlendirme & Harita:**
    * Restoranı olmayan kullanıcılar otomatik "Restoran Oluştur" ekranına yönlendirilir.
    * Restoranı olanlar "Dashboard" ekranına alınır.
    * Mapbox SDK ile Suluova merkezli harita entegrasyonu.
* **Sipariş Yönetimi:** Gelen siparişleri detaylı görüntüleme, Kabul/Red işlemleri.

## Kullanılan Teknolojiler

| Kategori | Teknoloji                           |
| :--- |:------------------------------------|
| **Dil** | Kotlin                              |
| **UI** | Jetpack Compose                     |
| **Mimari** | MVVM / Clean Architecture           |
| **DI** | Hilt                                |
| **Network** | Retrofit & OkHttp                   |
| **Realtime** | Pusher WebSocket (Socket.IO)        |
| **Local Data** | DataStore (Preferences)             |
| **Async** | Coroutines & Flow                   |
| **Image** | Coil                                |
| **Map** | Mapbox SDK                          |

## Giriş Bilgileri

* **Email:** batu@restaurant.com
* **Şifre:** pw123456

📡 API & Postman Testleri

Aşağıdaki istekleri Postman üzerinden göndererek sistemi test edebilirsiniz.


### 1. Sepet Oluşturma

**URL:** `POST http://188.34.155.223/new-qr-menu/api/v1/order/store_cart_items`

```json
{
  "restaurant_id": 65,
  "user_id": null,
  "note": null,
  "items": [
    {
      "product_id": 29,
      "quantity": 2,
      "unit_price": 45.50,
      "note": "Acısız"
    }
  ]
}
```

### 2. Sipariş Verme (Checkout)

Sipariş tamamlandığında WebSocket üzerinden `order.created` olayı tetiklenir ve restoran sahibinin ekranına anlık bildirim düşer.

**URL:** `POST http://188.34.155.223/new-qr-menu/api/v1/order/store_order`

```json
{
  "cart_id": 20,
  "delivery_name": "Test Müşteri",
  "delivery_phone": "+905551234560",
  "delivery_email": "test@test.com",
  "delivery_address": "Moda Mah. No:42",
  "delivery_city_id": 34,
  "delivery_district_id": 1641,
  "delivery_neighborhood_id": 1389,
  "delivery_latitude": "41.0082",
  "delivery_longitude": "28.9784",
  "discount_amount": 0,
  "delivery_fee": 15.00,
  "payment_method": "iyzico",
  "payment_status": "pending",
  "status": "pending"
}
```
## 📷 Ekran Görüntüleri

| Giriş | Kayıt | Kayı |
| :---: | :---: | :---: |
| <img src="https://github.com/user-attachments/assets/022c2ce2-7161-4ee9-b393-f8473702683b" width="250"/> | <img src="https://github.com/user-attachments/assets/22dfbd7f-d26e-43e4-8c88-9ec60d39c47b" width="250"/> | <img src="https://github.com/user-attachments/assets/d7df7edb-9d7d-4987-b416-ea40193e048d" width="250"/> |

| Dashboard | Restoran Oluştur 1 | Restoran Oluştur |
| :---: | :---: | :---: |
| <img src="https://github.com/user-attachments/assets/0b0e2dd6-53d1-464a-bd19-b2d539660b1a" width="250"/> | <img src="https://github.com/user-attachments/assets/63f0919f-c0e9-4525-ae21-63789cfc82b8" width="250"/> | <img src="https://github.com/user-attachments/assets/58f52847-8e46-4edb-b513-9cafac3ef479" width="250"/> |

| Restoran Detay | Sipariş Listesi | Harita |
| :---: | :---: | :---: |
| <img src="https://github.com/user-attachments/assets/0ce2fad3-b910-43e7-962e-ce42a686598e" width="250"/> | <img src="https://github.com/user-attachments/assets/338736e3-569c-47f5-ac59-58a2f91d67f3" width="250"/> | <img src="https://github.com/user-attachments/assets/ea2eee88-31c3-4556-a9d2-a7bfa56531a5" width="250"/> |

[![Download APK](https://img.shields.io/badge/Download-APK-dca502?style=for-the-badge&logo=android&logoColor=white)](https://github.com/batuhankizil/CaseStudy/releases/download/v1.0/app-debug.apk)


### 🗺️ Mapbox

```properties
# Mapbox Harita İndirme
MAPBOX_DOWNLOADS_TOKEN=sk.eyJ1IjoiYmF0dWhhbmtpemlsIiwiYSI6ImNtajBpZDk3azA0ZHAzZXF5cmNmOGdkbG0ifQ.Um6oWByCxANo89agQb4Qgw

# Mapbox Harita Gösterim
MAPBOX_PUBLIC_TOKEN=pk.eyJ1IjoiYmF0dWhhbmtpemlsIiwiYSI6ImNtajBodmU2bTA5Y2QzZHNmOGdsajZkZWQifQ.Vp_cf2kgF_SVXkFiTksnTw


