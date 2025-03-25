package Rels;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main extends Application {
    public static List<Firma> firme=new ArrayList<>();

    public void start(Stage primaryStage){



        VBox root = new VBox(10);
        root.setPadding(new Insets(10, 10, 10, 10));


        VBox hbTop = new VBox(10);
        HBox hbBottom = new HBox(300);

        HBox hbTop1 = new HBox(10);
        hbTop1.setAlignment(Pos.CENTER);
        Label lbFirme = new Label("Lista firmi: ");
        ChoiceBox<String> cbFirme = new ChoiceBox<>();
        Button btPrikazi = new Button("Prikazi");
        hbTop1.getChildren().addAll(lbFirme, cbFirme, btPrikazi);
        TextArea taPrikaz = new TextArea();
        hbTop.getChildren().addAll(hbTop1, taPrikaz);

        VBox vbLeft = new VBox(10);
        HBox hbStatus=new HBox(10);

        Label lbUnetiOcenu = new Label("Unesite ocenu:");
        VBox vbStatus = new VBox(10);
        Label lbStatus = new Label("Status: ");
        RadioButton rbZaposlen=new RadioButton("Zaposlen u firmi");
        rbZaposlen.setSelected(false);
        RadioButton rbBivsi=new RadioButton("Bivsi zaposlen u firmi");
        rbBivsi.setSelected(false);
        RadioButton rbNezaposlen=new RadioButton("Nezaposlen u firmi");
        rbNezaposlen.setSelected(false);
        ToggleGroup tgStatus=new ToggleGroup();
        rbZaposlen.setToggleGroup(tgStatus);
        rbBivsi.setToggleGroup(tgStatus);
        rbNezaposlen.setToggleGroup(tgStatus);
        vbStatus.getChildren().addAll(lbStatus,rbZaposlen,rbBivsi,rbNezaposlen);

        Button btIzaberi=new Button("Izaberi");
        hbStatus.getChildren().addAll(vbStatus, btIzaberi);
        hbStatus.setAlignment(Pos.CENTER);

        VBox vbOcena = new VBox(10);
        Label lbOcena = new Label("Ocena (1-10): ");
        TextField tfOcena = new TextField("");
        vbOcena.getChildren().addAll(lbOcena, tfOcena);

        VBox vbBezbednost = new VBox(10);
        Label lbBezbednost = new Label("Bezbednost podataka (1-10): ");
        TextField tfBezbednost = new TextField("");
        vbBezbednost.getChildren().addAll(lbBezbednost, tfBezbednost);

        VBox vbKomentar = new VBox(10);
        Label lbKomentar = new Label("Komentar: ");
        TextArea tfKomentar = new TextArea(); //promeni u text area
        vbKomentar.getChildren().addAll(lbKomentar, tfKomentar);

        VBox vbKontakt=new VBox(10);
        Label lbKontakt=new Label("");
        TextField tfKontakt=new TextField("");
        tfKontakt.setVisible(false);
        vbKontakt.getChildren().addAll(lbKontakt, tfKontakt);
        Button btUneti = new Button("Uneti");
        Button btSortiranje=new Button("Sortirati firme");
        TextArea taSortiranje=new TextArea();
        taSortiranje.setPrefSize(700,600);

        vbLeft.getChildren().addAll(hbStatus,lbUnetiOcenu,vbOcena, vbBezbednost, vbKomentar, vbKontakt, btUneti);
        vbLeft.setAlignment(Pos.CENTER);

        /*Label lbUnetiFirmu = new Label("Unesite naziv firme:");
        VBox vbFirma = new VBox(10);
        Label lbFirma = new Label("Naziv firme: ");
        TextField tfFirma = new TextField("");
        vbFirma.getChildren().addAll(lbFirma, tfFirma);

        Button btUnetiFirmu = new Button("Uneti firmu");

        vbRight.getChildren().addAll(vbFirma, btUnetiFirmu); */

        VBox vbRight = new VBox(10);
        vbRight.setAlignment(Pos.TOP_CENTER);
        vbRight.getChildren().addAll(btSortiranje, taSortiranje);

        hbBottom.getChildren().addAll(vbLeft, vbRight);
        hbBottom.setAlignment(Pos.CENTER);

        root.getChildren().addAll(hbTop, hbBottom);


        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Error!");

//------------------------------------------------------------------------------------
        for (Firma f:firme) { cbFirme.getItems().add(f.getNaziv()); }

        btPrikazi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                taPrikaz.clear();
                //String imeFirme= cbFir'me.getValue().trim();
                //Firma izabranaFirma=new Firma(cbFirme.getValue().trim());
                //Firma fiksnaFirma=izabranaFirma;

                /*if (!cbFirme.isDisabled()) {
                    taPrikaz.appendText("Usao 1");
                    alert.setHeaderText("Nije izabrana firma!");
                    alert.showAndWait();

                } */
                    String naziv = cbFirme.getValue().trim();
                    Firma fiksnaFirma = null;
                    for (Firma firma : firme) {
                        if (naziv.equals(firma.getNaziv()))
                            fiksnaFirma = firma;
                    }
                    //TODO


                    taPrikaz.appendText(fiksnaFirma.toString());
                    if (fiksnaFirma.getKomentari().isEmpty()) {
                        taPrikaz.appendText("Nema komentara.");
                    }
                    for (int i = 0; i < fiksnaFirma.getKomentari().size(); i++)
                        taPrikaz.appendText(fiksnaFirma.getKomentari().get(i) + "\n");



            }

        });

        btUneti.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String naziv=cbFirme.getValue().trim();
                Firma izabranaFirma=null;
                for(Firma f: firme){
                    if(naziv.equals(f.getNaziv())){
                        izabranaFirma=f;
                    }
                }
                //TODO
                if(izabranaFirma.equals(null)) {
                    //System.err.println("Greska, nije izabrana firma");
                    alert.setHeaderText("Nije izabrana firma");
                    alert.showAndWait();
                }
                int ocena = new Integer(tfOcena.getText());
                if(ocena<1 || ocena>10){
                    alert.setHeaderText("Ocena mora biti u opsegu 1-10");
                    alert.showAndWait();
                }
                int bezbednost = new Integer(tfBezbednost.getText());
                if(bezbednost<1 || bezbednost>10){
                    alert.setHeaderText("Ocena bezbednosti podataka mora biti u opsegu 1-10");
                    alert.showAndWait();
                }

                String komentar=tfKomentar.getText();

               // String kontakt=tfKontakt.getText();

                //if(kontakt.isEmpty())
                  //  kontakt="Anonimno";
                //String kk="Korisnik: "+kontakt+"\n"+komentar;
                String kk=null;

                if(tfKontakt.isVisible()){
                    String kontakt=tfKontakt.getText();

                    if(kontakt.isEmpty())
                        kontakt="Anonimno";
                    kk="Korisnik: "+kontakt+"\n"+komentar;
                }
                else{
                    kk="Korisnik: Posetilac\n" + komentar;
                }

                izabranaFirma.azuriratiOcenu(ocena);
                izabranaFirma.azuriratiBezbednost(bezbednost);
                izabranaFirma.dodatiKomentar(kk);
                izabranaFirma.azuriratibrOcena();

                taPrikaz.clear();
                taPrikaz.appendText(izabranaFirma.toString());
                if(izabranaFirma.getKomentari().isEmpty()){
                    taPrikaz.appendText("Nema komentara.");
                }
                for(int i=0;i<izabranaFirma.getKomentari().size();i++)
                    taPrikaz.appendText(izabranaFirma.getKomentari().get(i) + "\n");

                tfOcena.clear();
                tfBezbednost.clear();
                tfKomentar.clear();
                tfKontakt.clear();

                rbBivsi.setSelected(false);
                rbNezaposlen.setSelected(false);
                rbZaposlen.setSelected(false);

            }
        });

        btIzaberi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rbZaposlen.isSelected() || rbBivsi.isSelected()){
                    lbKontakt.setText("Ostavite svoj kontakt, ako zelite (email, linkedin, sajt...):\n");
                    tfKontakt.setVisible(true);
                }else{
                    lbKontakt.setText("");
                    tfKontakt.setVisible(false);
                }



            }
        });
        btSortiranje.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                KomparatorFirmi komparatorFirmi=new KomparatorFirmi();
                Collections.sort(firme,komparatorFirmi);
                for(Firma f:firme){
                    taSortiranje.appendText(f.getNaziv() + "  prosecna ocena: " + f.getProsecnaOcena()+ "  bezbednost: "+ f.getProsecnaBezbednost()+"  procenat zena: "+String.format("%.2f",f.getProcenatZena())+"\n");
                }
            }
        });

        Scene scene = new Scene(root, 1150, 950);
       // root.setStyle("-fx-background-color: navy;");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bezbezdnost zena u firmi");
        primaryStage.show();
    }

    public static void main(String[] args){

        for(int i=1;i<10;i++){firme.add(new Firma("Firma" + i)); }



        launch(args);
    }
}
