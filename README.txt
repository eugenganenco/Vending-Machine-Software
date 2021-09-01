Readme
 
 
Instructions to run the code
 
Find the “VendingMachine.jar” file in the “VendingMachine” folder and open it. This should execute the program and display the main frame. On the main frame you will find the following three buttons:
	1.	Load: this button is used to select a Json file containing the items the vending machine administrator wishes to load into the vending machine. Note: the file should have the exact structure as the Json file provided in the coding challenge description. The user can find such a file in the “VendingMachine” folder, titled “input.json”. After the administrator clicks this button, he/she will be prompted to select the directory of the file. The program checks if the file has the right extension (json). 
	2.	Buy: this button is used to access the Vending Machine GUI ( see description bellow ). However, because initially the vending machine is empty, the user needs to firstly load a file with some items in order to access the Vending Machine GUI. 
	3.	Audit: this button is used to access all the purchases made previously using the vending machine. The frame that appears after clicking this button is resizable and when the user buys multiple items, he/she might need to expand the frame to observe all the information available. Also, if the user wants to get back to the first frame of the program, he/she can click the button titled “Back”.
 
Once the user opens the Vending Machine GUI, he/she can see in the upper part of the frame a table containing all the items available for purchase. Each cell of the table will have the name of the item, the coordinate, and the price. The coordinate is made up of a letter and a number. The letter indicates the row where the item is located in the table and the number - the column. 
On the lower side of the frame, the user will spot two panels. The one on the left is used to select an item from the upper table. The user must type the coordinate of the item he/she wants to buy and then press the select button. Once the user selects an item, that item will be displayed in the right panel. There, the user will be able to purchase the item using the “Buy” button. Whenever the user desires to return to the first frame of the program, he/she can click the “Back” button. 

 
Description of the approach to solving this challenge
 
The following text details the approach I took to solve this coding challenge. I start with setting forth the framework of the program and providing some reasons explaining my decision. Later, I describe some notable aspects of the back-end and the front-end.
 
Framework
The program I have written follows the MVC pattern where the business logic of the program is separated from the GUI. This approach provides the programmer with the flexibility to experiment with the view and add multiple frames to the program. So, during the development process, I changed the view repeatedly and did not need to make any adjustments to the model which made my life a lot easier.
 
Model
The backbone of the model is a hashMap called “itemStockHashMap”. It stores the items available in the vending machine, with the name of the item being the key and the “itemStock” class ( class detailing the quantity and price of each product the user can find in the vending machine) - the value of the hashMap. Initially, the hashMap is empty, so to populate it, the program prompts the user to submit a Json file containing all the requisite data.
Another essential part of the model is the “transactions.json” file. It records all the previous transactions and the model uses this file to supply the “AuditFrame” with the necessary information. To read and write Json files I used the help of the “Json Simple” library.
I have also tried to shelter the data in the model from the view as much as possible. Therefore, when the View asks the Model to fetch the “itemStockHashMap” to create the “ItemsPanel”, the model, instead of returning the reference to the hashMap itself, returns a deep copy of it. Thus, the view is unable to make any accidental modifications to the key components of the model.
 
View
I have created the view using exclusively the “Java Swing” widget toolkit. The View is made up of a main frame called “DefaultFrame”. Using this frame, the user will get access to the “AuditFrame”, “LoadFrame”, and the “VendingMachine” frame. The first two are self-explanatory, while the latter one is more complex since it is composed of multiple panels. “VendingMachine” frame is made up of the “ItemsPanel”, which displays a table with all the items available in the vending machine, and the “Control Panel”, which is the panel that allows the user to interact with the rest of the program. “Control Panel” has a reference to the “ItemsPanel”, because the “ItemsPanel” permits communication between the “Control Panel” and the “View” object, and the “Control Panel” needs a way to update the table “ItemsPanel” whenever the user buys all the items stored in a particular coordinate in the table.
 
 
Limitations and potential improvements of this program
The program accomplishes all the requirements of the challenge. However, the program could be further improved by solving the following problems:
	1.	The program probably breaks if the user loads more than 32 types of items
	2.	The program does not store and document the items loaded by the user. Therefore, every time the user restarts the program, he/she must load the file with the items again.
3. Every time the “VendingMachineFrame” is created, the positioning of each type of item will change if the user alters the composition of the “itemStockHashMap” in the Model.
 
 
p.s sorry for the abundance of text.

