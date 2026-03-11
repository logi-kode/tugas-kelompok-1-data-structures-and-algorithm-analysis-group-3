//Anggota
/*
2702466132 - Prabu Addin Almuhasibi
2902733863 - Ariana Rahmawati
2902712133 - Indhah Pujihastuti
2902740824 - Parlindungan Ivancius
2902734941 - Ardelia Cindy Wulandari
*/

import java.util.Scanner;

class Lagu{
    private String judul;
    private String artis;
    private double durasi;

    public Lagu(String judul, String artis, double durasi){
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    public String getJudul(){
        return judul;
    }

    public void setJudul(String judul){
        this.judul = judul;
    }

    public String getArtis(){
        return artis;
    }

    public void setArtis(String artis){
        this.artis = artis;
    }

    public double getDurasi(){
        return durasi;
    }

    public void setDurasi(double durasi){
        this.durasi = durasi;
    }

    void tamplikanInfo(){
        System.out.println("Judul: " + judul);
        System.out.println("Artis: " + artis);
        System.out.println("Durasi: " + durasi + "\n");
    }
}

class User {
    protected String name;

    public User(String name){
        this.name = name;
    }

    public void tampilkanAkses(){
        System.out.println("User");
    }
}

class Admin extends User{
    public Admin(String name){
        super(name);
    }

    @Override
    public void tampilkanAkses(){
        System.out.println("Admin: menambahkan lagu baru ke playlist");
    }

    public int tambahLagu(Lagu[] playlist, int jumlahLagu, Lagu laguBaru){
        if(jumlahLagu < playlist.length){
            playlist[jumlahLagu] = laguBaru;
            jumlahLagu++;
            System.out.println("Lagu berhasil ditambahkan");
        } else {
            System.out.println("Playlist sudah penuh!");
        }
        return jumlahLagu;
    }

    public void lihatLagu(Lagu[] playlist, int jumlahLagu){
        System.out.println("\n=== Daftar Lagu ===");
        for(int i = 0; i < jumlahLagu; i++){
            playlist[i].tamplikanInfo();
        }
    }
}

class Member extends User{
    public Member(String name){
        super(name);
    }

    @Override
    public void tampilkanAkses(){
        System.out.println("Member: melihat dan mencari lagu");
    }

    public void lihatLagu(Lagu[] playlist, int jumlahLagu){
        System.out.println("\n=== Daftar Lagu ===");
        for(int i = 0; i < jumlahLagu; i++){
            playlist[i].tamplikanInfo();
        }
    }

    public void cariLagu(Lagu[] playlist, int jumlahLagu, String cariJudul){
        for(int i = 0; i < jumlahLagu; i++){
            if(playlist[i].getJudul().equalsIgnoreCase(cariJudul)){
                System.out.println("\nLagu Ditemukan: ");
                playlist[i].tamplikanInfo();
            }
        }

        if(playlist == null){
            System.out.println("Lagu tidak ditemukan!");
        }
    }

    public void rataRataDurasiLagu(Lagu[] playlist, int jumlahLagu){
        double total = 0;

        for(int i = 0; i < jumlahLagu; i++){
            total += playlist[i].getDurasi();
        }

        if(jumlahLagu > 0){
            double rataRata = total / jumlahLagu;
            System.out.println("Rata-rata durasi lagu: " + rataRata + " menit");
        } else {
            System.out.println("Belum ada lagu");
        }
    } 
}

public class PlaylistOOP {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Lagu[] playlist = new Lagu[10];
        int jumlahLagu = 0;

        Admin admin = new Admin("Admin");
        Member member = new Member("Member");

        int pilihan;

        do{
            System.out.println("\n === MENU ===");
            System.out.println("1. Tambah Lagu");
            System.out.println("2. Lihat Lagu");
            System.out.println("3. Cari Lagu");
            System.out.println("4. Rata-Rata Durasi Lagu");
            System.out.println("5. Keluar");

            System.out.print("Masukan Pilihan: ");
            pilihan = input.nextInt();

            input.nextLine();
            switch (pilihan) {
                case 1:
                    System.out.print("Judul Lagu: ");
                    String judul = input.nextLine();

                    System.out.print("Artis: ");
                    String artis = input.nextLine();

                    System.out.print("Durasi: ");
                    double durasi = input.nextDouble();

                    Lagu laguBaru = new Lagu(judul, artis, durasi);

                    jumlahLagu = admin.tambahLagu(playlist, jumlahLagu, laguBaru);
                    break;
                
                case 2:
                    member.lihatLagu(playlist, jumlahLagu);
                    break;
                
                case 3:
                    System.out.print("Cari Judul Lagu: ");
                    String cariJudul = input.nextLine();
                    member.cariLagu(playlist, jumlahLagu, cariJudul);
                    break;
                
                case 4:
                    member.rataRataDurasiLagu(playlist, jumlahLagu);
                    break;
                
                case 5:
                    System.out.println("Program Selesai");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");;
            }
        } while (pilihan != 5);
        input.close();
    }
}
