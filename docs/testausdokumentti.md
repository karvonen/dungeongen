#Testaus

Ohjelma on kattavasti yksikkötestattu. Ohjelmoin lyhyitä metodeita joita ei ole vaikea testata ja lisäksi tein integraatiotestejä testaamaan muutaman luokan kokonaisuuksia.

Jotkut testit sisältävät paljon sattumanvaraisuutta. Kyseessähän on luolastogeneraattori ja jokainen luolasto tulee olemaan uniikki. Tästä syystä pyrin hyvään yksikkötestaukseen, jos yksittäiset metodit toimivat oikein niin kokonaisuudenkin pitäisi olla toimiva.

Ohjelma ei ota syötteitä käyttäjältä, suorituskykytestaus tehtiin oletusarvoisilla (koodissa olevilla) luolastogeneraattorin asetuksilla.

Käytin aikaa myös manuaaliseen testaukseen varmistaakseni että generoidut luolastot ovat kelvollisia aina kun muutin jotain algoritmissani. Tämä olisi ollut huomattavan vaikeata toteuttaa ohjelmallisesti.

Yksikkötesteistä oli paljon hyötyä projektin aikana. En käyttänyt test driven developmenttia mutta pidin testejä kuitenkin ajantasalla. Esimerkiksi erikoistapaus jossa portaat ylös ja alas olivat mahdollista mennä samaan ruutuun tuli huomattua testien kautta.

#Suorituskykytestaus

Luolastogeneraattorille ei suoraan käyttäjän puolesta ole mahdollista antaa luolaston dimensioita mutta koodia muokkaamalla tämä onnistuu kohtuullisen helposti. Koodia ei ole kuitenkaan mitenkään optimoitu oletusarvoisen kokoisesta poikkeavan luolaston generoimiseen ja oikeastaan vain erittäin suurilla koon muutoksilla voisi olla edes pieni muutos generoimiseen käytettyyn aikaan. Testeihin generoidut luolastot olivat 30 ruutua korkeita ja 140 leveitä.

Testit suoritettiin ohittamalla käyttöliittymä. LevelGenerator luotiin ja kutsuttiin suoraan Main-luokasta. Testit ajettettiin Netbeanssin ulkopuolella Windowsin komentorivillä käyttämällä luotua .jar-tiedostoa. Käytin testaukseen System.nanoTimea koska ajat olivat aika pieniä. 

Testatessani huomasin että ensimmäinen luolaston generointi generaattorin luomisen jälkeen vei huomattavasti enemmän aikaa kuin sitä seuraavien. Päätin tehdä saman ajon aikana 100 luolastoa.


##Sadan luolaston generointi

![100 luolaston generoinnin kaavio](/docs/100generointia.png)

Tämän kaavion yksittäinen palkki esittää yhteen luolaston generoimiseen kuluneen ajan millisekunteina. Tästä huomaa erityisen hyvin miten ensimmäinen luolasto on todella hidas. En ole varma johtuuko tämä just-in-time compilationista vai siitä miten Java käsittelee uusia olioita.


Yhden luolaston generointi 100 luolaston ajossa:

* Mediaani 1.82 ms
* Keskiarvo 2.612 ms


Huoneiden lisäämisen osuus 100 luolaston ajossa:

* Mediaani 0.0245 ms
* Keskiarvo 0.0823 ms

Tässäkin on huomattavaa miten ensimmäisellä kerralla huoneiden lisäys vie yli 150 kertaisesti aikaa verrattuna mediaaniin. Tämä nostaa keskiarvon kaksinkertaiseksi.


Tunnelien kaivaminen järkevästi 100 luolaston ajossa:

* Mediaani: 0.844 ms
* Keskiarvo: 1.407 ms


Toinen tunnelien kaivamiskierros jolla saadaan kaikki huoneet yhdistettyä:

* Mediaani: 0.863 ms
* Keskiarvo: 1.363 ms


Testausalustana toimi pöytäkone: i5 4670k @ 4.2 Ghz


Suurin osa luolastoon generoimisesta ajasta käytetään siis tunneleiden kaivamiseen ja samalla tarkistamaan onko luolasto yhtenäinen. Tämä ei ole mitenkään yllättävää ja koko ominaisuuden toteuttaminen veikin projektista paljon aikaa.

Kuten aikaisemmin mainittiin, ensimmäiseen luolastoon käytetty aika nostaa keskiarvoa valtavasti ja tämän vuoksi keskiarvot antavat väärän kuvan ohjelman toimivuudesta.

##Toistettavuus

Yksittäiseen luolastoon menevän ajan testaukseen muutin main-luokan tähän muotoon. Generaattorin osien testauksessa pidin for loopin mutta siirsin ajanoton testattavan osion kohdalle. Käytin testatessani nanotimeä aikojen lyhyyden takia mutta muutin ne millisekunneiksi tätä dokumenttia varten.

```
        Scanner scanner = new Scanner(System.in);
        ArrayList<Long> ajat = new ArrayList<>();
        LevelGenerator generator = new LevelGenerator(30, 140);
        for (int i = 0; i < 100; i++) {
            long aikaAlussa = System.nanoTime();
            char[][] test = generator.generate();
            long aikaLopussa = System.nanoTime();
            System.out.println((aikaLopussa - aikaAlussa));
            ajat.add(aikaLopussa-aikaAlussa);
            generator.reset();
        }
```
