Kreş Takip Sistemi (kres_takip)

Bu proje, kreşlerde çocukların, velilerin ve temel operasyonların takibini kolaylaştırmak amacıyla geliştirilmiş Spring Boot tabanlı bir backend uygulamasıdır.
Sistem, RESTful API yaklaşımıyla tasarlanmış olup ileride bir web veya mobil arayüz ile entegre edilebilecek şekilde yapılandırılmıştır.

🎯 ​Bu proje, Orman Genel Müdürlüğü (OGM) bünyesinde gerçekleştirdiğim zorunlu staj programı kapsamında, kurumun dijital süreçlerini anlama ve modern yazılım çözümleri üretme amacıyla geliştirilmiştir.
​Proje; Java ve Spring Boot öğrenme sürecimde, gerçek hayat senaryolarına (bir kreşin yönetim ihtiyacı) dayalı olarak kurgulanmış bir Backend / API projesidir. Kurumsal bir çalışma ortamında, backend mimarisi ve veritabanı yönetimi konularındaki yetkinliklerimi pekiştirmek adına uçtan uca tasarlanmıştır.

🚀 Özellikler

👶 Çocuk bilgileri yönetimi (ekleme, listeleme, güncelleme)

👨‍👩‍👧 Veli bilgileri takibi

🏫 Kreş içi temel veri yönetimi

🔁 RESTful API mimarisi

🧱 Katmanlı mimari (Controller – Service – Repository)

🛠️ Kullanılan Teknolojiler

Dil: Java

Framework: Spring Boot

API: Spring Web (REST)

Veri Erişimi: Spring Data JPA

Veritabanı: H2 / PostgreSQL / MySQL (geliştirilmeye açık)

Build Tool: Maven

IDE: IntelliJ IDEA / Eclipse

🧩 Proje Mimarisi

controller → API endpoint’leri

service → İş mantığı

repository → Veritabanı işlemleri

entity → Veri modelleri

Bu yapı, projenin okunabilir, bakımı kolay ve ölçeklenebilir olmasını sağlar.

📁 Proje Yapısı

kres_takip/ → Kaynak kodlar ve iş mantığı

.gitignore → Gereksiz dosya ve logların hariç tutulduğu yapılandırma dosyası
