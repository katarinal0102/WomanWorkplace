package Rels;

import java.util.*;

public class Firma{
    private String naziv;
    private int brojZaposlenih;
    private float procenatZena;
    private float prosecnaOcena;
    private int Sumabezbednost;
    private float prosecnaBezbednost;
    private int brOcena;
    private int sumaOcena;
    private List<Integer> zaposleni;
    private List<String> komentari;
    private static Random random=new Random();

    public Firma(String naziv) {
        this.naziv = naziv;
        this.brojZaposlenih=random.nextInt(100);
        this.procenatZena=random.nextFloat()*100;
        this.brOcena=random.nextInt(this.brojZaposlenih)+1;
        this.Sumabezbednost=random.nextInt(10*brOcena+1-brOcena)+brOcena;
        this.sumaOcena=random.nextInt(10*brOcena+1-brOcena)+brOcena;
        this.prosecnaOcena=sumaOcena/brOcena;
        this.prosecnaBezbednost=Sumabezbednost/brOcena;
        this.komentari=new ArrayList<>();
        this.zaposleni=new ArrayList<>();
        for(int i=1;i<=brojZaposlenih;i++){
            this.zaposleni.add(i);
        }

    }

    public Firma(Firma f){
        this.naziv=f.naziv;
        this.brojZaposlenih=f.brojZaposlenih;
        this.procenatZena=f.procenatZena;
        this.prosecnaOcena=f.prosecnaOcena;
        this.Sumabezbednost=f.Sumabezbednost;
        this.prosecnaBezbednost=f.prosecnaBezbednost;

        this.sumaOcena=f.sumaOcena;
        this.komentari=new ArrayList<>();
        for (String k: f.komentari) {
            this.komentari.add(k);
        }



    }

    public String getNaziv() {
        return naziv;
    }

    public int getBrojZaposlenih() {
        return brojZaposlenih;
    }

    public float getProcenatZena() {
        return procenatZena;
    }

    public float getProsecnaOcena() {
        return prosecnaOcena;
    }

    public int getBrOcena() {
        return brOcena;
    }

    public List<Integer> getZaposleni() {
        return zaposleni;
    }

    public List<String> getKomentari() {
        return komentari;
    }

    public int getSumabezbednost() {
        return Sumabezbednost;
    }

    public float getProsecnaBezbednost() {
        return prosecnaBezbednost;
    }

    public int getSumaOcena() {
        return sumaOcena;
    }

    public static Random getRandom() {
        return random;
    }

    public void setProsecnaOcena(float ocena) {
        this.prosecnaOcena = ocena;
    }

    public void setSumabezbednost(int sumabezbednost) {
        Sumabezbednost = sumabezbednost;
    }

    public void setProsecnaBezbednost(float prosecnaBezbednost) {
        this.prosecnaBezbednost = prosecnaBezbednost;
    }

    public void setSumaOcena(int sumaOcena) {
        this.sumaOcena = sumaOcena;
    }

    public void setBrOcena(int brOcena) {
        this.brOcena = brOcena;
    }

    public void setKomentari(List<String> komentari) {
        this.komentari = komentari;
    }

    /*public String ispisKomentara(List<String> k){
        if(k.isEmpty()){
            return "*Nema komentara*";
        }
        for(int i=0;i<k.size();i++)
            return k.get(i) + "\n";
        return "\n";
    }*/

    public void azuriratiOcenu(int ocena){
        sumaOcena=sumaOcena+ocena;
        setProsecnaOcena(new Float(sumaOcena/(brOcena+1)));

    }

    public void azuriratiBezbednost(int bez){
        Sumabezbednost=getSumabezbednost()+bez;
        prosecnaBezbednost=Sumabezbednost/(brOcena+1);
    }

    public void dodatiKomentar(String komentar){
        if(komentar.equals("")){}
        else{
        komentari.add(komentar);}
    }

    public void azuriratibrOcena(){
        brOcena++;
    }

    @Override
    public String toString(){
        return "--broj recenzija:" + brOcena + "\n--broj zaposlenih: "+this.brojZaposlenih+"\n--procenat zena u firmi: "+String.format("%.2f",this.procenatZena)+"\n--odnos prema zenama u firmi: "+String.format("%.2f",this.prosecnaOcena)+"\n--bezbednost podataka: "+String.format("%.2f",this.prosecnaBezbednost)+"\n\n--------------------------------------------------------------------------\n\n";


    }
}
