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

## Postman
* **Sepet**
URL: POST http://188.34.155.223/new-qr-menu/api/v1/order/store_cart_items
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

* **Sipariş**
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



## Kurulum

```bash
git clone [https://github.com/batuhankizil/CaseStudy.git](https://github.com/batuhankizil/CaseStudy.git)

<img width="1080" height="2220" alt="Login" src="https://github.com/user-attachments/assets/694dd8ff-cd4a-422d-82bd-564054c72138" />
<img width="1080" height="2220" alt="Register" src="https://github.com/user-attachments/assets/71add11f-6672-40a0-8d9c-a5edea49df23" />
<img width="1080" height="2220" alt="Register2" src="https://github.com/user-attachments/assets/0311a444-e3ec-48ca-ad65-eaad78057946" />
<img width="1080" height="2220" alt="RestaurantCreate" src="https://github.com/user-attachments/assets/9766266d-9bf0-45fa-8093-5588eb831464" />
<img width="1080" height="2220" alt="RestaurantCreate2" src="https://github.com/user-attachments/assets/50a4a484-6df9-4c1e-9f04-db42258ca110" />
<img width="1080" height="2220" alt="Dashboard" src="https://github.com/user-attachments/assets/758d72bb-60de-4d32-9bd3-ca54199ebcef" />
<img width="1080" height="2220" alt="OrderList" src="https://github.com/user-attachments/assets/472fecf0-5e65-42f2-9c08-b67509fad428" />
<img width="1080" height="2220" alt="RestaurantDetail" src="https://github.com/user-attachments/assets/2d75c4cd-40fb-4a22-8ebc-5d2868297b6e" />
<img width="1080" height="2220" alt="Map" src="https://github.com/user-attachments/assets/58ed0b09-5b1e-4b3a-bc32-058ebac6c253" />




