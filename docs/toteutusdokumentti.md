#Rakenne

Projekti on jaettu muutamaan pakettiin. Logiikka-luokka sisältää koodia joka mahdollistaa liikuttaa hahmoa generoiduissa luolastoissa vaikkakin sen alkeellisen käyttöliittymän takia hieman hankalaa. Parempi käyttöliittymä olisi täytynyt toteuttaa swingillä ja ehkä jonkin kirjaston avulla. En kuitenkaan ole näin tehnyt koska se ei ole tiralabran pääasia ja tekstikäyttöliittymä toimii hyvin.

Itse luolastogeneraattori on täysin erillään pelillisestä osiosta. Generaattori on jaettu moneen luokkaan joista yksi on itse generaattori joka käyttää muita luokkia. RoomGenerator luo ja lisää huoneita. Huoneet voivat olla täysin toistensa vieressä mikä voi antaa huoneille muitakin muotoja kuin pelkkä suorakulmio. TunnelCarver kaivaa käytäviä järkevästi ja DesperateTunnelCarver kaivaa tunneleita jos koko luolasto ei ole kokonaan yhtenäinen ensimmäisen kaivamisen jälkeen.

Käyttöliittymä koostuu yksinkertaisista print-komennoista.

Näistä erillään ovat vielä toteuttamani tietorakenteet.

Mielestäni ohjelman luokkarakenne ja metodit seuraavat hyvin single responsibility periaatetta ja kiinnitinkin siihen huomiota helpottaakseeni yksikkötestausta.


#Puutteet ja parannusehdotukset

Nykyinen algoritmi antaa luolastoille "elävämmän" olemuksen. Jos olisin toteuttamassa vaikka roguelike-peliä joka käyttäisi lisäisin siihen ehdottomasti mahdollisuuden kaivaa tunneleita (ja monissa roguelikeissa tämä onkin mahdollista). Kuitenkin jos luolasto on täysin steriili ja siinä ei ole mitään muita tunneleita kuin sellaisia jotka suoraan yhdistävät huoneita, se antaa pelin maailmasta kuvan jossa ei ole muita hahmoja kuin pelaaja. Jos luolasto taas löytyy tunneleita joille ei heti löydy selitystä voi pelaaja olettaa ettei hän olekaan ainoa joka osaa kaivaa tunneleita.

Olen algoritmiini siis loppujen lopuksi aika tyytyväinen. Lähdin toteuttamaan luolastogeneraattoria joka luo luolastoja peliä varten, en akateemisen täydellisiä luolia. Ehkä suurin parannusehdotus olisi jonkinlainen tarkistus onko luola liian avara. Nykyisellä algoritmilla voi tapahtua niin että monta huonetta on vierekkäin ja se johtaa välillä todella isoihin onkaloihin. Jos käyttäisin tätä luolastogeneraattoria oikeassa pelissä niin varmasti tekisin tällaisen tarkistuksen.

#Aika- ja tilavaativuudet

Koska luolastogeneraattorille ei voi suoraan käyttöliittymästä antaa kartan kokoa ei aika- ja tilavaatimusten analysointi ole kovin järkevää.  
 
#Suorituskyky

Luolasto generoidaan erittäin nopeasti (n. 2ms), testausdokumentista voi lukea lisää.

#Lähteet/linkit

Projektin toteutuksessa apuna käytin näitä sivustoja:
* http://www.roguebasin.com/index.php?title=Dungeon-Building_Algorithm
* http://pcg.wikidot.com/pcg-algorithm:dungeon-generation
* http://stackoverflow.com/questions/306316/determine-if-two-rectangles-overlap-each-other


