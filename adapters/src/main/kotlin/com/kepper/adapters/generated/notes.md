### IMPORTANT NOTICE

This folder only contains file that will be generated, with the scope of simulating 
Kepper will behave.

Nothing from here should make it to the final build. Only for testing


The API should be something like:

Kepper
    .openFile(filename: String)
    .selectSheet(sheetName: String)
    .read(Item::class.java)