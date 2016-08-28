Ohjelma on kattavasti yksikkötestattu. Ohjelmoin lyhyitä metodeita joita ei ole vaikea testata ja lisäksi lisäsin joitain integraatiotestejä testaamaan muutaman luokan kokonaisuuksia.

Jotkut testit sisältävät paljon sattumanvaraisuutta. Kyseessähän on luolastogeneraattori ja jokainen luolasto tulee olemaan uniikki. Tästä syystä pyrin hyvään yksikkötestaukseen, jos yksittäiset metodit toimivat oikein niin kokonaisuudenkin pitäisi olla toimiva.


#Suorituskykytestaus

Luolastogeneraattorille ei suoraan käyttäjän puolesta ole mahdollista antaa luolaston dimensioita mutta koodia muokkaamalla tämä onnistuu kohtuullisen helposti. Koodia ei ole kuitenkaan mitenkään optimoitu oletusarvoisen kokoisesta poikkeavan luolaston generoimiseen ja oikeastaan vain erittäin suurilla koon muutoksilla voisi olla edes pieni muutos generoimiseen käytettyyn aikaan. Testeihin generoidut luolastot olivat 30 ruutua korkeita ja 140 leveitä.

Testit suoritettiin ohittamalla käyttöliittymä. LevelGenerator luotiin ja kutsuttiin suoraan Main-luokasta. Testit ajettettiin Netbeanssin ulkopuolella Windowsin komentorivillä käyttämällä luotua .jar-tiedostoa. Käytin testaukseen System.nanoTimea koska ajat olivat aika pieniä. 

Testatessani huomasin että ensimmäinen luolaston generointi generaattorin luomisen jälkeen vei huomattavasti enemmän aikaa kuin sitä seuraavien. Päätin tehdä saman ajon aikana 100 luolastoa.


Yhden luolaston generointi 100 luolaston ajossa:

*Mediaani 1.82 ms
*Keskiarvo 2.612 ms


Testausalustana toimi pöytäkone: i5 4670k @ 4.2 Ghz

