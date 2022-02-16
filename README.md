Geliştirmeye çalıştığım uygulamada bir ana sayfaya yönlendiren bir de detay sayfalarına yönlendiren örneğin bir duyuruya yapılacak başvurunun son günü bu duyuru tekrar bildirim olarak atılmalı bunun için bildirim gönderilmeli. Bu şekilde iki bildirim şekli ve iki farklı yere duruma göre bildirimler yönlendirilmeliydi. BTK Akademi Firebase ile Proje geliştirme eğitiminde Atıl Samancıoğlu hocam bir örnek yapmıştı orda pendingIntent'imiz sadece activity'e yönlendirme yapıyordu.Eğitim firebase geliştirme olduğu için Viewbinding,mvvm,navigation gibi konuları kapsamıyordu.Eğitimdeki bölüme ek olarak kendi sorunumu çözebilmek için notification'a bir uuid yolladım ve bu uuid firebaseService'de geri aldım gelen bir uuid var ise NavDeepLinkBuilder kısmına yönlendirdim ve navigation kütüphanesinin bir özelliği ile uuid bir bundle ekledim. Detay kısmında bundle'ı kullanarak uuid yi aldım ve verileri firestore'dan çektim.Örnekte olabildiğince temiz ve doğru teknikleri kullanmaya çalıştım eğer yanlış yaptığım eksik olduğum konular varsa düzeltirseniz sevinirim.

(www.linkedin.com/in/ibrahimethem)

https://user-images.githubusercontent.com/68695185/154300995-0f0e4d73-eca1-4150-b999-8fd898fc5690.mp4

![bildirim](https://user-images.githubusercontent.com/68695185/154299914-aebd1933-62ab-4b4b-b293-35872da07e0b.png)
![bildirim2](https://user-images.githubusercontent.com/68695185/154300335-f43e64d7-8957-4885-83e6-a893a0478035.png)
![bildirim3](https://user-images.githubusercontent.com/68695185/154300340-34e66bf8-07aa-4d36-8b8e-6ccc7f2b1513.png)
