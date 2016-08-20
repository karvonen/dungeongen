
Rakenne

Projekti on jaettu muutamaan pakettiin. Ohjelma sisältää mahdollisuuden liikuttaa hahmoa generoiduissa luolastoissa vaikkakin sen alkeellisen käyttöliittymän takia hieman hankalaa. Ei olisi kuitenkaan mahdotonta toteuttaa parempaa käyttöliittymää, en kuitenkaan ole näin tehnyt koska se ei ole tiralabran pääasia.

Itse luolastogeneraattori on täysin erillään pelillisestä osiosta. Generaattori on jaettu moneen luokkaan joista yksi on itse generaattori joka käyttää muita luokkia. RoomGenerator luo ja lisää huoneita, TunnelCarver kaivaa käytäviä järkevästi ja DesperateTunnelCarver kaivaa tunneleita jos koko luolasto ei ole kokonaan yhtenäinen.

Käyttöliittymä koostuu yksinkertaisista print-komennoista.

Näistä erillään ovat vielä toteuttamani tietorakenteet.

Mielestäni ohjelman luokkarakenne ja metodit seuraavat hyvin single responsibility periaatetta ja kiinnitinkin siihen huomiota.


Puutteet ja parannusehdotukset

Generaatioalgoritmia voisi luultavasti nopeuttaa tekemällä tunneleiden kaivamisesta "järkevempää". Kuitenkin nykyinen algoritmi antaa luolastoille "elävämmän" olemuksen. Jos olisin toteuttamassa vaikka roguelike-peliä joka käyttäisi lisäisin siihen ehdottomasti mahdollisuuden kaivaa tunneleita (ja monissa roguelikeissa tämä onkin mahdollista). Kuitenkin jos luolasto on täysin steriili ja siinä ei ole mitään muita tunneleita kuin sellaisia jotka suoraan yhdistävät huoneita, se antaa pelin maailmasta kuvan jossa ei ole muita hahmoja kuin pelaaja. Jos luolasto taas löytyy tunneleita joille ei heti löydy selitystä voi pelaaja olettaa ettei hän olekaan ainoa joka osaa kaivaa tunneleita.

Olen algoritmiini siis loppujen lopuksi aika tyytyväinen. Lähdin toteuttamaan luolastogeneraattoria joka luo luolastoja peliä varten, en akateemisen täydellisiä luolia.

 

