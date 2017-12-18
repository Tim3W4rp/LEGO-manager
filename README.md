# LegoManager

Description: This project has been created as a school project for the PA165 class of 2017 at Masaryk University, Brno.

Assignment in Czech: Vžijme se opět do dětských let. Mějme firmu prodávající stavebnice Lego. Aplikace bude sloužit jako manažer, který obstarává správu stavebnic, setů skládajících se z různých kombinací a kategorií do kterých jsou zařazeny. Každá stavebnice bude mít soupis dílů které do ní patří stejně jako každý set bude obsahovat soupis stavebnic ze kterých se skládá. U každého dílu je zase potřeba uvádět ba2revné kombinace v jakých se vyrábí.Manažer obstarává správu (tedy přídávání, odebírání, úprava) stavebnic a jejich dílů stejně jako setů. Každá stavebnice se skládá z různých dílů. V nabídce jsou i sety složené z jednotlivých stavebnic. Existují různé tématické kategorie pod které se stavebnice budou řadit.

Je třeba uchovávat:
    
    Barevné kombinace u jednotlivých dílů
    Evidovat z jakých dílů v jaké barevné variantě je stavebnice slo
    Cena za jakou je stavebnice prodávána
    Je nutné evidovat věkovou hranici pro děti
    Z jakých stavebnic je složen set
    Za jakou cenu se každý set prodává (nemusí nezbytně odpovídat součtu cen stavebnic)
    Set i samotná stavebnice musí evidovat do které kategorie je přiřazena
    Kategorie také nese stručný popis

Installation: You can compile the project by using "mvn clean install" 

Usage: This project serves for educational purposes only.


REST API curl examples:

GET ALL

curl -X GET http://localhost:8080/pa165/rest/sets

GET ONE

curl -X GET http://localhost:8080/pa165/rest/sets/1

DELETE

curl -X DELETE http://localhost:8080/pa165/rest/sets/1

UPDATE

curl -H "Content-Type: application/json" -X PUT -d "{\"description\":\"eeee\",\"price\":222.78}" http://localhost:8080/pa165/rest/sets/2

POST

curl -H "Content-Type: application/json" -X POST -d "{\"description\":\"xyz\",\"price\":56.78}" http://localhost:8080/pa165/rest/sets/create


Credits:

         https://github.com/xdvora22
         https://github.com/stepan662
         https://github.com/Tim3W4rp
         https://github.com/majklllll
         
License: MIT License
         
         Copyright (c) 2017 Martin Jordán, Štěpán Granát, Michal Peška, Lukáš Dvořák
         
         Permission is hereby granted, free of charge, to any person obtaining a copy
         of this software and associated documentation files (the "Software"), to deal
         in the Software without restriction, including without limitation the rights
         to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
         copies of the Software, and to permit persons to whom the Software is
         furnished to do so, subject to the following conditions:
         
         The above copyright notice and this permission notice shall be included in all
         copies or substantial portions of the Software.
         
         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
         IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
         FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
         AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
         LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
         OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
         SOFTWARE.

