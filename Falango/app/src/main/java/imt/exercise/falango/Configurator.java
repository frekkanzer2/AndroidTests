package imt.exercise.falango;

import java.util.ArrayList;
import java.util.Random;

public class Configurator extends Object {

    private String takedName = null;

    public Configurator (){this.takedName = "";}

    public ArrayList<String> getPizzeriaList(){
        ArrayList<String> listPizzerie = new ArrayList<String>();
        listPizzerie.add("Frienno Magnanno (Cava)");
        listPizzerie.add("Pulcinella (Cava)");
        listPizzerie.add("Sotto all'arco (Cava)");
        listPizzerie.add("Nonna Nannina (Cava)");
        listPizzerie.add("Pedro's (Cava)");
        listPizzerie.add("La Smorfia (SA)");
        listPizzerie.add("I Due Fratelli (Vietri)");
        listPizzerie.add("Madegra (Vietri)");
        listPizzerie.add("Madison (Cava)");
        listPizzerie.add("Madison (Nocera Inf.)");
        listPizzerie.add("Sorbillo (SA)");
        listPizzerie.add("Il Piccolo Vesuvio (Cava)");
        listPizzerie.add("4 Farine Gourmet (Nocera Sup.)");
        listPizzerie.add("Il Piccolo Paradiso (Cava)");
        listPizzerie.add("Maximum (Cava)");
        listPizzerie.add("La Foce 2 (Cava)");
        listPizzerie.add("Corner (SA)");
        listPizzerie.add("Resilienza (SA)");
        listPizzerie.add("La Piperita (SA)");
        listPizzerie.add("Criscemunno (SA)");
        listPizzerie.add("'O Sarracin (Nocera Inf.)");
        listPizzerie.add("La Carmela (Nocera Inf.)");
        listPizzerie.add("Friariell' (Cava)");
        listPizzerie.add("Addò Ciaccio (Mercato S.S.)");
        return listPizzerie;
    }
    public ArrayList<String> getPubList(){
        ArrayList<String> listPub = new ArrayList<String>();
        listPub.add("Il Moro (Cava)");
        listPub.add("Posh Pub (Cava)");
        listPub.add("Highlander (Cava)");
        listPub.add("In Vino Veritas (Cava)");
        listPub.add("Morhum (Cava)");
        listPub.add("Be Druid's (Cava)");
        listPub.add("Opperbacco (Cava)");
        listPub.add("Black Roses Irish Pub (SA)");
        listPub.add("King's Cross (SA)");
        listPub.add("O'Haras Pub&Grill (SA)");
        listPub.add("Galleon Pub (SA)");
        listPub.add("Bad Bros (Nocera Inf.)");
        listPub.add("The Clover (Nocera Inf.)");
        listPub.add("Burger Bar Cargo (SA)");
        return listPub;
    }
    public ArrayList<String> getBarList(){
        ArrayList<String> listBar = new ArrayList<String>();
        listBar.add("Yourself (Cava)");
        listBar.add("Gisò (Cava)");
        listBar.add("La Torretta (Cava)");
        listBar.add("Madamoiselle Charlotte (Cava)");
        listBar.add("San Fransao (Cava)");
        listBar.add("Picante Wine Bar (Cava)");
        listBar.add("Amato Caffè (Cava)");
        listBar.add("Caffè D'Essai (Cava)");
        listBar.add("Rodaviva (Cava)");
        listBar.add("Sesto Senso (Cava)");
        listBar.add("Bar Sportivo (Cava)");
        listBar.add("Pinturi (Cava)");
        listBar.add("Bar Portico (Cava)");
        listBar.add("Caffetteria Centro Storico (Cava)");
        listBar.add("La Drinkeria (Cava)");
        listBar.add("Gintò (Cava)");
        listBar.add("Bar Daniel's (Cava)");
        listBar.add("Bar Remo (Cava)");
        return listBar;
    }
    public ArrayList<String> getSushiList(){
        ArrayList<String> listSushi = new ArrayList<String>();
        listSushi.add("Zen'O (Nocera Inf.)");
        listSushi.add("Xu Sushi Bar (Pagani)");
        listSushi.add("La Grande Muraglia 2 (SA)");
        listSushi.add("Imperial Sushi Wonk (SA)");
        listSushi.add("SohoSushi (Cava)");
        listSushi.add("Himiko (SA)");
        listSushi.add("Me Geisha (SA)");
        listSushi.add("Misaki Sushi (SA)");
        listSushi.add("Origami (SA)");
        listSushi.add("La Nuova Muraglia (SA)");
        listSushi.add("Ingordo (SA)");
        listSushi.add("Manko Sushi (Cava)");
        return listSushi;
    }

    public void setRandomName(ArrayList<String> list){
        Random generator = new Random();
        int randNumber = generator.nextInt(list.size());
        setTakedName(list.get(randNumber));
    }

    private void setTakedName(String name){
        this.takedName = name;
    }

    public String getTakedName(){
        return this.takedName;
    }

}
