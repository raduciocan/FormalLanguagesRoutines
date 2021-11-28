# FormalLanguagesRoutinesLauncher
A centralized hub containing programs for custom grammar derivation, generation and interpretation.

The application features dedicated console GUIs for each subroutine (programs) contained.

The application implements the factory creational design pattern for handling a variable number of subroutines, as well as builder design pattern for initializing the data
model for each of it's subroutine in a final (get-only) state, in accordance with the proper object-oriented practices.

Additionaly, each specialized view implements a baseline view interface, which is extended to accomodate the different options that each subroutine requires for the proper testing of its features. Specialised vies are imnplemented as lasy singletons, due to only requiring one unique instance for each subroutine, if and only if that subroutine exists and has been properly initialised.
The factory class is also of eager initialization singleton type, due to always requiring one unique instance whenever the Main metthod is ran.
The factory design pattern allows us to quickly test different implementations of methods used in the creation process and handling of the subroutines, as well as providing a sistematic logical way of expanding the master program functionality with additional subroutines.
The builder design patterns helps divide the complexity of each subroutine into smaller modules, each ith its own responsibility.
The subroutines class constructor is private, and only accessible by the builder, which together with the subroutine-unique builder helpers constructs each subroutine with the corresponding data properly formatted for that subroutine by the implemented fileManager class.


## CURRENT AVAILABLE SUBROUTINES:


### I. Markov Derivator scheme:

Un algoritm normal in sens Markov este un sistem de rescriere SR = (V,F) unde elementele lui F
sunt date intr-o anumita ordine si exista o submultime, posibil vida, F1 ≤ F continand reguli sau
productii finale. La fiecare pas al algoritmului se aplica prima dintre regulile lui F care este aplicabila, si in plus
se rescrie cea mai din stanga aparitie a subcuvantului care este membru stang al regulii folosite. 
<dot chgaracter> - marcheaza finalul
<star character> - executarea multipla a unui pas

Implementare algoritm normal in sens Markov:
- Se citesc din fisier componentele (vocabularul si regulile de rescriere);
- Se citeste de la tastatura un cuvant, se deriveaza pe baza algoritmului (se
deriveaza pana se aplica o regula finala sau nu mai exista reguli aplicabile), se
afiseaza cuvantul obtinut, apoi utilizatorul este intrebat daca mai doreste testarea
unui cuvant si in caz afirmativ se reia procesul;

Metode subrutina:
  1. citire()
  2. afisare()
  3. derivare(propozitie)


### II. Generative grammar scheme:

Implmentare gramatica generativa: se citesc din fișier elementele componente
VN, VT, simbolul de start și producțiile (regulile de generare). Se citește de la tastatură un număr n, iar apoi se
generează n cuvinte în gramatică. Generarea are loc în modul următor: se pornește de fiecare
dată de la simbolul de start. Cât timp există producții aplicabile se aplică random una dintre
producțiile aplicabile.
Conditii verificare:
  1. VN intersects VT = empty set
  2. S is part of VN
  3. for each rule, the left member has at least one non-terminal
  4. there is at least one production with start suymbol S as the sole left member
  5. each production contain only elements found over the VN and VT alphabets
  
Metode subrutina:
  1. citire()
  2. verificare()
  3. afisare()
  4. generare()


### III. Finite deterministic automaton scheme:

Un AFD este este un 5-uplu A=(Q, Σ, δ, q0, F), in care:
Q este o multime finita de stari
Σ este o multime finita de simboli de intrare (alfabet simboluri de intrare)
δ este o functie de tranzitie: δ:Q × Σ -> Q
q0 este starea initiala
F este o multime de stari finale.

Implementare AFD (automat finit determinist): 
- se citesc din fișier elementele componente ale automatului Q, Σ, δ, q0, F. 
- Se citește de la tastatură un cuvant și se verifică, dacă este acceptat de către automat.

Metode subrutina:
  1. citire()
  2. afisare()
  3. verificare(cuvant)


  
  ## TO-DO:
  - [ ] [Improve error handling and file operations](https://github.com/raduhammer/FormalLanguagesRoutines/issues/1)
  - [ ] [Add proper GUI using JavaFX](https://github.com/raduhammer/FormalLanguagesRoutines/issues/2)
  - [ ] [Add subroutine initialization files guidelines](https://github.com/raduhammer/FormalLanguagesRoutines/issues/4)
  - [ ] [Fix the initial commit description disaster](https://github.com/raduhammer/FormalLanguagesRoutines/issues/3)
  - [ ] I should also fix the remaining romgleza ramasa prin README :)
