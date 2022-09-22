## YANG UDAH JADI

- `library` : disini udah ada ADT Matriks, Determinan Eliminasi Gauss, Cramer, Inverse Gauss Jordan, Inverse Kofaktor
- `Utils`   : disini udah ada utils buat IO file, sama doubleToInt

## YANG PERLU DIKERJAIN
- `library SPL` : ini gw mikir keknya solver SPL juga masukin ke SPL.java (jadi di library udah bisa nemu x1, x2, dst.), jadi nanti solver SPL pakai gaussJordan dll masukin ke sini aja
- `Aplikasi` : 
    1. Menu
    2. SPL (ada menu pilihan metode, pilihan file/keyboard, input matriks, output hasil)
    3. Determinan (sama kayak SPL)
    4. Invers (sama kayak SPL)
    5. Interpolasi 
    6. Bikubik
    7. RLB
- `Utils`   : katanya harus setPrecision karena kalo ga, java suka random

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
