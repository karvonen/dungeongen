#Kayttöohje

##Ohjelman ajaminen:

Koska kyseessä on konsolisovellus täytyy se ajaa terminaalissa tai esimerkiksi IDE:ssä jossa on console output. Alla ohjeet ohjelman lataamiseen ja ajamiseen.

1. git clone https://github.com/karvonen/dungeongen.git tai lataa ja pura zip.

2. cd dungeongen

3. mvn package

4. java -jar target/dungeongen-1.0-SNAPSHOT.jar

##Ohjelman käyttäminen

Uusi luolasto luodaan automaattisesti ja käyttäjän ei tarvitse antaa mitään syötteitä. Pelaajan on mahdollista liikkua antamalla komentoja numeroina jossa suunnat menevät numpadin numeroiden mukaan. Esimerkiksi antamalla komennon "2" pelihahmo liikkuu alaspäin. Uusi luolasto voidaan generoida antamalla komento "w" tai liikkumalla portaiden päälle. Ohjelman suorituksen voi lopettaa komennolla "q".


