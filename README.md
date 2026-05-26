# T5_Mobile

# **Nama:** Arya Rayan Utama
# **NIM:** F1D02310106

# Fitur Aplikasi

1. Login menggunakan API  
2. Validasi email dan password  
3. Menampilkan loading saat request berjalan  
4. Menampilkan pesan login gagal  
5. Menyimpan token login menggunakan SharedPreferences  
6. Menggunakan Authorization Bearer Token  
7. Menampilkan data pasien menggunakan RecyclerView  
8. Swipe refresh data pasien  
9. Error handling saat request gagal  
10. Logout session  

## Screenshot Aplikasi

| Tampilan Login Page | Tampilan Login Gagal |
|:-------------------:|:--------------------:|
| ![Login](screenshots/login.jpg) | <img width="1080" height="2400" alt="logingagal" src="https://github.com/user-attachments/assets/b2350dae-b998-457f-ad75-928cfbc1c090" />|

| Tampilan Request Gagal | Tampilan Loading |
|:----------------------:|:----------------:|
| <img width="720" height="1600" alt="request" src="https://github.com/user-attachments/assets/d5d6efe8-dac3-43a7-93fb-5544ef4cd9be" /> | <img width="1080" height="2400" alt="loading" src="https://github.com/user-attachments/assets/309ddb7a-8103-4ac5-8c13-424c35e9cb0b" /> |

| Tampilan RecyclerView data pasien | Tampilan RecyclerView data pasien |
|:---------------------------------:|:---------------------------------:|
| <img width="720" height="1600" alt="data1" src="https://github.com/user-attachments/assets/9966c90d-cc4b-4357-ad94-89dc7f9af390" /> | <img width="720" height="1600" alt="data2" src="https://github.com/user-attachments/assets/b381a87a-7e67-416a-98ff-fdd1e6b222f1" /> |

## Cara Menjalankan Project
1. Clone repository ini.
2. Buka di **Android Studio**.
3. Pilih menu File > Open, lalu arahkan ke folder hasil clone tadi. 
4. Tunggu proses **Gradle Sync** selesai.
5. Pastikan `gradle.properties` sudah memiliki `android.useAndroidX=true` dan `android.enableJetifier=true`.
6. Pastikan Emulator atau Device Anda memiliki akses internet aktif karena aplikasi ini menggunakan API online (https://api.pahrul.my.id).
7. **Login Default**: 
   - Dapat menggunakan beberapa email login yaitu: "admin@example.com", "budi@example.com", "siti@example.com", "agus@example.com", "dewi@example.com", dan "admin@example.com".
   - Password: `password` / admin123 
