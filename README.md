# SC2002 Project 

NTU AY2023/24 Semester 2 SC2002 Group Project - Food Order Management System (FOMS).

Food Order Management System (FOMS) is a Java console application that utilizes object-oriented concepts to efficiently manage streamlining the process of ordering, payment,
and order management for both customers and staff at Fastfood restaurants. The program is designed with a focus on reusability, extensibility, and maintainability, allowing for easy upgrades and future development. It provides flexibility to accommodate different user types and their requirements.

## Team Members

We are a group 6 from lab group FDAB, Nanyang Technological University, Singapore. There are 5 members in our group:

| Name         | Github Account                                  | Email                 |
|--------------|-------------------------------------------------|-----------------------|         
| Dana Yak | [dnyk7](https://github.com/dnyk7) | [dana0008@e.ntu.edu.sg](mailto:dana0008@e.ntu.edu.sg) |
| Isaac Wong  | [Izackyy](https://github.com/Izackyy)         | [iwong008@e.ntu.edu.sg](mailto:iwong008@e.ntu.edu.sg) |
| Jamie Tan  | [jamietannn](https://github.com/jamietannn)   | [tanp0125@e.ntu.edu.sg](mailto:tanp0125@e.ntu.edu.sg)  |
| Toh Jun Sheng  | [jxxsheng](https://github.com/jxxsheng)   | [tohj0041@e.ntu.edu.sg](mailto:tohj0041@e.ntu.edu.sg)  |
|Solis Aaron Mari Santos| [abomasumm](https://github.com/abomasumm)           | [aaron004@e.ntu.edu.sg](mailto:aaron004@e.ntu.edu.sg) |


## Features
- [x] Customer
  - [x] Select branch
  - [x] View menu of selected branch when ordering
  - [x] Place a new order
  - [x] Add, remove, edit items in cart
  - [x] Make payments (Cash, Credit/Debit, E-payment)
  - [x] Generate receipt
  - [x] Check order status
  - [x] Collect order 

- [x] Staff
  - [x] Display new orders in their branch
  - [x] View details of a particular order
  - [x] Process order

- [x] Manager
  - [x] Display new orders in their branch
  - [x] View details of a particular order
  - [x] Process order
  - [x] Display staff list of branch supervised
  - [x] Add, remove, edit menu items in their branch
  - [x] Edit availiability of menu items
- [x] Admin
  - [x] Add, edit, remove staff accounts
  - [x] Filter (branch, role etc) and display staff list
  - [x] Assign managers to each branch within the quota constraint
  - [x] Promote a staff to a Branch manager
  - [x] Transfer a staff/manager among branches
  - [x] Add/remove payment method
  - [x] Open/close branch.

## FOMS Setup instructions
### Using Eclipse
Please download all the txt files required.

1) Open Eclipse

2) Click on File > 'Import' > 'Git' > 'Projects from Git' > 'Clone URI'

3) In the Clone URI window, paste the following URL
   > 'https://github.com/Izackyy/SC2002-FOMS.git'

4) Follow prompts and finish cloning process

5) Drag and drop the txt files into SC2002-Project_SC2002FOMS folder

6) In 'Main' > 'FomsApp.java' , run as Java Application

You have successfully cloned and run SC2002 FOMS on Eclipse!

### Using Visual Studio Code

1) On your application, click 'Source Control' > 'Clone Repository'

2) Paste 'https://github.com/Izackyy/SC2002-FOMS.git' into your tool bar and create a folder

3) Click 'Run and Debug'

You have successfully cloned and run SC2002 FOMS on Visual Studio Code!

## UML Class Diagram

The UML diagram is generated by [draw.io](https://draw.io/).

You can refer to [UML](https://github.com/Izackyy/SC2002-FOMS/blob/main/SC2002FOMS/FDAB%20Group6%20UML%20diagram.png) for the UML class diagram.

## Report

You can refer to the [report](https://github.com/Izackyy/SC2002-FOMS/blob/main/SC2002FOMS/SC2002%20Report.pdf) we created on this project.

## License

[MIT](LICENSE) © Aaron, Dana, Isaac, Jamie, Jun Sheng
