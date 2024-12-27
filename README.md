# Calculator Nilai UTS - Enhanced GUI

Calculator Nilai UTS adalah aplikasi berbasis Java Swing yang dirancang untuk membantu menghitung nilai rata-rata UTS mahasiswa, menampilkan hasilnya dalam tabel, serta menyediakan fitur tambahan seperti upload gambar dan pengelolaan data mahasiswa. Aplikasi ini memiliki antarmuka yang ramah pengguna dengan desain modern.

## Fitur Utama
1. **Input Data Mahasiswa**: Masukkan nama mahasiswa dan nilai UTS 1, 2, serta 3.
2. **Perhitungan Nilai Rata-rata**: Hitung nilai rata-rata UTS secara otomatis.
3. **Manajemen Data Tabel**:
   - Tambahkan data baru.
   - Update data yang sudah ada.
   - Hapus data tertentu.
4. **Upload Foto Mahasiswa**: Unggah gambar mahasiswa yang akan ditampilkan dalam tabel.
5. **Reset Form**: Bersihkan semua input dengan mudah.
6. **Validasi Input**:
   - Nama tidak boleh mengandung angka.
   - Validasi nilai UTS agar hanya menerima angka.

## Tampilan Antarmuka
### Komponen Utama:
- **Header**: Label berjudul "Calculator Nilai UTS" dengan desain menarik.
- **Panel Input**: Berisi input untuk nama, nilai UTS, tombol operasi, dan area gambar.
- **Tabel Data**: Menampilkan daftar mahasiswa beserta nilai dan gambar.

## Teknologi yang Digunakan
- **Java Swing**: Untuk membangun antarmuka grafis (GUI).
- **BufferedImage & ImageIO**: Untuk membaca dan mengelola gambar yang diunggah.
- **JTable & DefaultTableModel**: Untuk menampilkan dan mengelola data dalam tabel.

## Cara Menggunakan
1. **Masukkan Data**:
   - Isi nama mahasiswa (tanpa angka).
   - Masukkan nilai UTS 1, 2, dan 3.
2. **Hitung Nilai Rata-rata**:
   - Klik tombol "Hitung" untuk menghitung rata-rata nilai UTS.
   - Data akan ditampilkan di tabel jika berhasil.
3. **Kelola Data**:
   - Pilih baris di tabel dan gunakan tombol "Update" untuk memperbarui data.
   - Pilih baris di tabel dan klik tombol "Delete" untuk menghapus data.
4. **Unggah Gambar**:
   - Klik "Upload Image" untuk memilih dan mengunggah foto mahasiswa.
   - Gambar akan ditampilkan dalam tabel.
5. **Reset Form**:
   - Klik tombol "Reset" untuk mengosongkan semua input.

## Validasi Input
- **Nama Mahasiswa**:
  - Tidak boleh kosong.
  - Tidak boleh mengandung angka.
- **Nilai UTS**:
  - Harus berupa angka (desimal diperbolehkan).

## Struktur Kode
- **`UTSCalculator`**: Kelas utama yang menangani seluruh logika aplikasi dan antarmuka pengguna.
- **`ImageCellRenderer`**: Kelas untuk menampilkan gambar dalam sel tabel.

## Instalasi
1. Clone repository ini:
   ```bash
   git clone https://github.com/username/Calculator-Nilai-UTS.git
   ```
2. Pastikan Anda memiliki Java Development Kit (JDK) versi 8 atau lebih baru.
3. Buka proyek ini di IDE seperti IntelliJ IDEA atau Eclipse.
4. Jalankan file `UTSCalculator.java`.

## Dependensi
- Tidak ada dependensi eksternal selain JDK.

## Cuplikan Kode Penting
### Hitung Nilai Rata-rata
```java
private void hitungNilai() {
    try {
        String nama = txtNama.getText();
        if (nama.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh mengandung angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double nilai1 = Double.parseDouble(txtNilai1.getText());
        double nilai2 = Double.parseDouble(txtNilai2.getText());
        double nilai3 = Double.parseDouble(txtNilai3.getText());
        double rataRata = (nilai1 + nilai2 + nilai3) / 3;
        txtHasil.setText(String.format("%.2f", rataRata));
        tableModel.addRow(new Object[]{nama, nilai1, nilai2, nilai3, rataRata, new ImageIcon(uploadedImage)});
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Input tidak valid, masukkan angka yang benar!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
```

## Kontribusi
1. Fork repository ini.
2. Buat branch fitur baru:
   ```bash
   git checkout -b fitur-baru
   ```
3. Commit perubahan Anda:
   ```bash
   git commit -m "Menambahkan fitur baru"
   ```
4. Push ke branch:
   ```bash
   git push origin fitur-baru
   ```
5. Ajukan pull request.

## Lisensi
Proyek ini dilisensikan di bawah [MIT License](LICENSE).

---

**Dibuat dengan ❤ oleh Javier Bhagawanta, Via.**

