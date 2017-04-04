# Excersize-Creator
Ohjelma jolla voi luoda opetukseen ja/tai opiskeluun tarkoitettuja tehtäviä/An application for creating excersizes for teaching and/or studying

## Dokumentaatio
[Aiheen kuvaus](https://github.com/mikomikono/Excersize-Creator/blob/master/dokumentaatio/aiheenKuvausJaRakenne.md)
[Tuntikirjanpito](https://github.com/mikomikono/Excersize-Creator/blob/master/dokumentaatio/tuntikirjanpito.md)
[Pit testaus](https://htmlpreview.github.io/?https://github.com/mikomikono/Excersize-Creator/blob/master/dokumentaatio/pit/201703302034/index.html)
[Checkstyle](https://htmlpreview.github.io/?https://github.com/mikomikono/Excersize-Creator/blob/master/dokumentaatio/checkstyle/site/checkstyle.html)

## Notes
- Säädin paljon Question-rajapinnan totauttavien luokkien kanssa, jotta saisin ne toimimaan mahdollisimman hyvin. Pohdin myös ExcersizeManager luokan tarpeellisuutta QuestionManagerin ohessa, mutta tällä hetkellä koen vielä että siitä voi olla hyötyä.
- Jostain syystä en saa pitiä testaamaan vain logiikkaa onnistuneesti. Muokkasin pom.xml tiedostoa ohjeiden mukaisesti, ja se kyllä lakkasi testaamasta mainin ja käyttöliittymän, mutta jostain syystä se ei enää löytänyt logiikan testejä???? Rivikattavuus on logiikassa yli 80% mutta se putoaa 60%iin mainin ja käyttöliittymän takia....... En osaa ratkaista ongelmaa yksin, mutta ennen kolmatta dedistä ei ole enää pajoja, joten se jää ensiviikkoon.
- Herranjumala en saa multichoice -tehtäviä toimimaan oikein aaaaaaaaaaaaaaaaaaaaa
