//Anggota
/*
2702466132 - Prabu Addin Almuhasibi
2902733863 - Ariana Rahmawati
2902712133 - Indhah Pujihastuti
2902740824 - Parlindungan Ivancius
2902734941 - Ardelia Cindy Wulandari
*/

import java.util.*;
import java.io.*;

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

    public String getArtis(){
        return artis;
    }

    public double getDurasi(){
        return durasi;
    }

    void tampilkanInfo(){
        System.out.println("Judul: " + judul);
        System.out.println("Artis: " + artis);
        System.out.println("Durasi: " + durasi + " menit\n");
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

    public void tambahLagu(Lagu laguBaru){

        try{

            File file = new File("playlist.txt");

            if(!file.exists()){
                file.createNewFile();
            }

            Scanner cekFile = new Scanner(file);

            while(cekFile.hasNextLine()){
                String line = cekFile.nextLine();
                String[] data = line.split(";");

                String judul = data[0];
                String artis = data[1];

                if(judul.equalsIgnoreCase(laguBaru.getJudul()) &&
                   artis.equalsIgnoreCase(laguBaru.getArtis())){

                    System.out.println("Lagu dari artis yang sama sudah terdaftar!");
                    cekFile.close();
                    return;
                }
            }

            cekFile.close();

            FileWriter writer = new FileWriter(file, true);
            writer.write(laguBaru.getJudul() + ";" +
                         laguBaru.getArtis() + ";" +
                         laguBaru.getDurasi() + "\n");

            writer.close();

            System.out.println("Lagu berhasil ditambahkan!");

        }catch(IOException e){
            System.out.println("Terjadi kesalahan saat menyimpan file.");
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

    public void lihatLagu(){

        try{

            File file = new File("playlist.txt");

            if(!file.exists()){
                System.out.println("Belum ada lagu di playlist.");
                return;
            }

            Scanner reader = new Scanner(file);

            System.out.println("\n=== Daftar Lagu ===");

            while(reader.hasNextLine()){

                String line = reader.nextLine();
                String[] data = line.split(";");

                Lagu lagu = new Lagu(
                        data[0],
                        data[1],
                        Double.parseDouble(data[2])
                );

                lagu.tampilkanInfo();
            }

            reader.close();

        }catch(Exception e){
            System.out.println("Terjadi kesalahan saat membaca file.");
        }
    }

    public void cariLagu(String cariJudul){

        boolean ditemukan = false;

        try{

            File file = new File("playlist.txt");

            if(!file.exists()){
                System.out.println("Playlist masih kosong.");
                return;
            }

            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()){

                String line = reader.nextLine();
                String[] data = line.split(";");

                if(data[0].equalsIgnoreCase(cariJudul)){

                    Lagu lagu = new Lagu(
                            data[0],
                            data[1],
                            Double.parseDouble(data[2])
                    );

                    System.out.println("\nLagu ditemukan:");
                    lagu.tampilkanInfo();

                    ditemukan = true;
                }
            }

            reader.close();

            if(!ditemukan){
                System.out.println("Lagu belum tersedia.");
            }

        }catch(Exception e){
            System.out.println("Terjadi kesalahan saat mencari lagu.");
        }
    }

    public void rataRataDurasiLagu(){

        double total = 0;
        int jumlah = 0;

        try{

            File file = new File("playlist.txt");

            if(!file.exists()){
                System.out.println("Belum ada lagu.");
                return;
            }

            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()){

                String line = reader.nextLine();
                String[] data = line.split(";");

                total += Double.parseDouble(data[2]);
                jumlah++;
            }

            reader.close();

            if(jumlah > 0){
                double rata = total / jumlah;
                System.out.printf("Rata-rata durasi lagu: %.2f menit\n", rata);
            }else{
                System.out.println("Belum ada lagu.");
            }

        }catch(Exception e){
            System.out.println("Terjadi kesalahan.");
        }
    }
}

public class PlaylistOOP {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Admin admin = new Admin("Admin");
        Member member = new Member("Member");

        int pilihan = 0;

        do{

            System.out.println("\n=== MENU ===");
            System.out.println("1. Tambah Lagu");
            System.out.println("2. Lihat Lagu");
            System.out.println("3. Cari Lagu");
            System.out.println("4. Rata-Rata Durasi Lagu");
            System.out.println("5. Keluar");

            System.out.print("Masukkan Pilihan: ");
            try {
                pilihan = input.nextInt();
                input.nextLine(); // menghapus newline setelah nextInt
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka 1-5 ! Silakan coba lagi.");
                input.nextLine(); // menghapus input yang salah
                continue; // kembali ke awal loop 
            }

            switch(pilihan){

                case 1:

                    System.out.print("Judul Lagu: ");
                    String judul = input.nextLine();

                    System.out.print("Artis: ");
                    String artis = input.nextLine();

                    System.out.print("Durasi: ");
                    double durasi = input.nextDouble();

                    Lagu laguBaru = new Lagu(judul, artis, durasi);
                    admin.tambahLagu(laguBaru);

                    break;

                case 2:
                    member.lihatLagu();
                    break;

                case 3:
                    System.out.print("Cari Judul Lagu: ");
                    String cariJudul = input.nextLine();
                    member.cariLagu(cariJudul);
                    break;

                case 4:
                    member.rataRataDurasiLagu();
                    break;

                case 5:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        }while(pilihan != 5);

        input.close();
    }
}
