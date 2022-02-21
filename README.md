### Structure

The project is set as a Maven project, with seperated architectural layers. We have CORE, which handles domain logic and local storage. A UI folder with handles all user interaction in the UI and our fxml files. Lastly, in the future we want to implement a server layer.
We also have a resources folder. In this folder we keep sprits and images for our app as well as our json files.
We also have some api collected json elements. These do not enage with the project at this stage, but we keept them for future expantion.

All our code files are located in src/
under src we have a main and a test folder. The main folder is explaned above but our test folders has one test per code file.
These can be run with the `mvn test` command.

The project requires Java version 16, junit 5 and javaFX version 15.

