# System aukcyjny

## Wprowadzenie

Specyfikacja wymagań funkcjonalnych w ramach informatyzacji procesu sprzedaży produktów w oparciu o mechanizm aukcyjny. 

## Procesy biznesowe

---
<a id="bc1"></a>
### BC1: Sprzedaż aukcyjna 

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Opis:** Proces biznesowy opisujący sprzedaż za pomocą mechanizmu aukcyjnego. |

**Scenariusz główny:**
1. [Sprzedający](#ac1) wystawia produkt na aukcję. ([UC1](#uc1))
2. [Kupujący](#ac2) oferuje kwotę za produkt wyższą od aktualnie najwyższej oferty. ([BR1](#br1)[UC3](#uc3))
3. [Kupujący](#ac2) wygrywa aukcję ([BR2](#br2))
4. [Kupujący](#ac2) przekazuje należność Sprzedającemu.([UC4](#uc4))
5. [Sprzedający](#ac1) przekazuje produkt Kupującemu.([UC2](#uc2))

**Scenariusze alternatywne:** 

2.A. Oferta Kupującego została przebita, a [Kupujący](#ac2) pragnie przebić aktualnie najwyższą ofertę.
* 2.A.1. Przejdź do kroku 2.

3.A. Czas aukcji upłynął i [Kupujący](#ac2) przegrał aukcję. ([BR2](#br2))
* 3.A.1. Koniec przypadku użycia.

---

## Aktorzy

<a id="ac1"></a>
### AC1: Sprzedający
Osoba oferująca towar na aukcji.

<a id="ac2"></a>
### AC2: Kupujący
Osoba chcąca zakupić produkt na aukcji.

## Przypadki użycia poziomu użytkownika
### Aktorzy i ich cele

[Sprzedający](#ac1):
* [UC1](#uc1): Wystawienie produktu na aukcję
* [UC2](#uc2): Przekazanie produktu Kupujacemu.
* [UC5](#uc5): Usunięcie aukcji.
* [UC6](#uc6): Edytowanie aukcji.

[Kupujący](#ac2)
* [BR1](#br1)[UC3](#uc3): Zaoferowanie kwoty za produkt.
* [BR2](#br2): Wygranie aukcji.
* [UC4](#uc4): Przekazanie naleznosci Sprzedajacemu.

---
<a id="uc1"></a>
### UC1: Wystawienie produktu na aukcję

**Aktorzy:** [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Sprzedający](#ac1) zgłasza do systemu chęć wystawienia produktu na aukcję.
2. System prosi o podanie danych produktu i ceny wywoławczej.
3. [Sprzedający](#ac1) podaje dane produktu oraz cenę wywoławczą.
4. System weryfikuje poprawność danych.
5. System informuje o pomyślnym wystawieniu produktu na aukcję.

**Scenariusze alternatywne:** 

4.A. Podano niepoprawne lub niekompletne dane produktu.
* 4.A.1. System informuje o błędnie podanych danych.
* 4.A.2. Przejdź do kroku 2.

---

<a id="uc2"></a>
### UC2: Przekazanie produktu Kupujacemu.

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Scenariusz główny:**
1. System dostarcza dane wysylki Sprzedawcy
2. [Sprzedający](#ac1) Sprzedawca nadaje paczke na podane dane
3. [Sprzedający](#ac1) Sprzedawca wysyla do systemu potwierdzenia nadania
4. [Kupujący](#ac2) Kupujacy wysyla do systemu potwierdzenie odbioru paczki

**Scenariusze alternatywne:** 

1.A. Kupujacy dostaje z systemu adres odbioru osobictego
* 1.A.1. System wysyla mozliwe daty i godziny odbioru Kupujacemu
* 1.A.2. [Kupujący](#ac2) Kupujacy wybiera termin odbioru w systemie
* 1.A.3. [Sprzedający](#ac1) Sprzedawca dostaje informacje o wybranym terminie
* 1.A.4. [Sprzedający](#ac1) [Kupujący](#ac2) Kupujacy spotyka sie z Sprzedawca i odbiera produkt

---

<a id="uc3"></a>
### UC3: Zaoferowanie kwoty za produkt.

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) [BR1](#br1) zgłasza swoją propozycję ceny za dany produkt
2. Aktualna cena na aukcji zostaje zaktualizowana.
3. System informuje [Sprzedającego](#ac1) i [Kupującego](#ac2) o aktualnej cenie produktu.

---

<a id="uc4"></a>
### UC4: Przekazanie naleznosci Sprzedajacemu.

**Aktorzy:** [Kupujący](#ac2) [Sprzedającego](#ac1)

**Scenariusz główny:**
1. [Kupujący](#ac2) [BR1](#br1) wplaca odpowiednia kwote do Systemu
2. System informuje  i [Kupującego](#ac2) potwierdza dokonana platnosc 
3. System przekazuje przelewa pieniadze na konto [Sprzedającego](#ac1)

---

## Obiewkty biznesowe (inaczje obiekty dziedzinowe lub informatycjne)

### BO1: Aukcja

Aukcja jest formą zawierania transakcji kupna-sprzedaży, w której Sprzedający określa cenę wywoławczą produktu, natomiast Kupujący mogą oferować własną ofertę zakupu każdorazowo proponując kwotę wyższą od aktualnie oferowanej kwoty. Aukcja kończy się po upływie określonego czasu. Jeśli złożona została co najmniej jedna oferta zakupy produkt nabywa ten Kupujący, który zaproponował najwyższą kwotę. 

### BO2: Produkt

Fizyczny lub cyfrowy obiekt, który ma zostać sprzedany w ramach aukcji.

## Reguły biznesowe

<a id="br1"></a>
### BR1: Złożenie oferty

Złożenie oferty wymaga zaproponowania kwoty wyższej niż aktualnie oferowana o minimum 1,00 PLN.


<a id="br2"></a>
### BR2: Rozstrzygnięcie aukcji

Aukcję wygrywa ten z [Kupujący](#ac2)ch, który w momencie jej zakończenia (upłynięcia czasu) złożył najwyższą ofertę.

## Macierz CRUDL

| Przypadek użycia                                  | Aukcja | Produkt | 
| ------------------------------------------------- | ------ | ------- | 
| UC1: Wystawienia produktu na aukcję               |    C   |    C    |
| UC2: Przekazanie produktu Kupujacemu              |   R,U  |         | 
| UC3: Zaoferowanie kwoty za produkt                |   U,R  |         |
| UC4: Przekazanie naleznosci Sprzedajacemu         |    R   |         |
| UC5: Usunięcie aukcji                             |    D   |    D    |
| UC6: Edytowanie aukcji                            |   U,R  |   U,R   |


