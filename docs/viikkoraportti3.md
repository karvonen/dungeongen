#Viikkoraportti 3

Aloitin ohjelmoimalla flood-fill algoritmin jota käytetään tarkastamaan että luolaston jokainen huone on saavutettavissa. Halusin luolastojen olevan kuljettavissa pelkästään neljään pääilmansuuntaan liikkuessa vaikkakin vinottain on myös mahdollista liikkua. Kuitenkin tästä syystä flood-fill toimii vain pääilmansuuntiin. Verkkojen läpikäynti on tullut koodattua niin monta kertaa tiran tehtävissä ja tiraa pajaohjatessa ettei tässä pahemmin ollut vaikeuksia, tai uutta opittavaa.

Seuraavaksi aloin työstämään itse luolastogeneraattoria. Koska mikään algoritmi ei ole kovin yksinkertainen päätin aluksi kirjoittaa generaattorin niin että se yksinkertaisesti täyttää luolaston huoneilla (ei vielä yhdistä niitä). Tein myös apumetodin joka tarkastaa menevätkö huoneet päällekkäin. Ratkaisu tähän olikin yllättävän yksinkertainen ja löysinpä vielä hyvän visualisoinnin ongelmaa varten: https://silentmatt.com/rectangle-intersection/ http://stackoverflow.com/questions/306316/determine-if-two-rectangles-overlap-each-other. Päätin kuitenkin nyt että kaksi huonetta voivat olla ihan vierekkäin ilman että niissä on seinä välissä. Tämä tuo vähän "eloa" luolastoon kun jokainen huone ei ole täysin suorakulmainen. 

Tässä vaiheessa pystyin siis generoimaan huoneita helposti. Viikon suurin ongelma oli niiden yhdistäminen. Mietin ja testailin pariakin eri vaihtoehtoa ennen kuin päädyin nykyiseen implementaation. Tällä hetkellä luolastogeneraattori toimii näin:

1. Täytetään koko luolasto seinillä.
2. Lisätään luolastoon huoneita satunnaisesti. Huoneet voivat olla vierekkäin jolloin niissä ei ole seinää välissä mutta eivät voi olla päällekkäin.
3. Lisätään portaat alas ja ylös. Portaat ovat siis aina huoneessa koska käytäviä ei vielä ole olemassa.
4. Yhdistellään huoneita kaivamalla käytäviä. Toistetaan xx kertaa.
  1. Arvotaan huone ja yksi sen seinistä.
  2. Katsotaan saadaanko huoneesta pois päin menemällä yhdistettyä huone toiseen huoneeseen tai käytävään (eli tuleeko vastaan kuljettava ruutu)
  3. Jos vastaan tulee kuljettava ruutu, kaivetaan tunneli. Palataan kohtaan 4.
  4. Jos vastaan ei tule kuljettavaa ruutua ei kaiveta mitään. Palataan kohtaan 4.
5. Kaivetaan tunneleita satunnaisesti kunnes koko luolasto on yhtenäinen.
  1. Valitaan satunnainen kuljettava ruutu.
  2. Kaivetaan tunneli.
  3. Tarkistetaan onko luolasto yhtenäinen. Jos on, lopetataan. Jos ei ole hypätään takaisin kohtaan 5.
6. Laitetaan portaat paikoilleen kohdassa 3 arvottuihin paikkoihin. Tämä siksi että kohta 5 voi muuten ylikirjoittaa portaat tunnelilla.


En ole vielä täysin tyytyväinen tähän algoritmiin. Useimmiten luolastot ovat ihan hyvän näköisiä mutta on myös mahdollista generoida luolastoja jotka eivät edes enää ole normaalin rogueliken luolaston näköisiä vaan enemmänkin avaria yksittäisiä kammioita. Yritän parannella algoritmia tulevilla viikoilla. 

Javalabran ohjeiden mukaan private metodeihin ei tarvitse tehdä javadoccia joten en tehnyt niitä tässäkään. Ajattelin kuitenkin kirjoitella niitä alkuviikosta koodikatselmoijaa varten.

Algoritmin parantamisen lisäksi seuraavalla viikolla aloitan tietorakenteiden ohjelmoimisen. Lisäksi ajattelin refaktoroida ja siistiä koodia. Ohjelma on tähän asti edennyt asettamani aikataulun mukaan.


Ohjelmointi 8h

Suunnittelu, vaihtoehtojen tutkiminen 2h

Testaus ja räpellys 2h

yht. 12h