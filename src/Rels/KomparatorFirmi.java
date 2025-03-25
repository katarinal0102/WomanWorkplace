package Rels;

import java.util.Comparator;

public class KomparatorFirmi implements Comparator<Firma> {
    @Override
    public int compare(Firma f1, Firma f2){
        if(f1.getProsecnaOcena()> f2.getProsecnaOcena())
            return -1;
        else if(f1.getProsecnaOcena()<f2.getProsecnaOcena())
            return 1;
        else{
            if(f1.getProsecnaBezbednost()>f2.getProsecnaBezbednost())
                return -1;
            else if(f1.getProsecnaBezbednost()<f2.getProsecnaBezbednost()){
                return +1;
            }
            else{
                if(f1.getProcenatZena()>= f2.getProcenatZena())
                    return -1;
                else
                    return +1;

            }
        }
    }
}

