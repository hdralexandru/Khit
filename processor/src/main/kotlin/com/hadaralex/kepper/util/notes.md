### GENERAL 
- we are given a row iterator to which we extract the 

### SHEETS (org.apache.poi.ss.usermodel.Sheet)

- the `lastRow()` function returns the index of the last column, starting with the index 0.
    - this means the if index is 3, columns 0,1,2,3 are taken (but might be empty! This might be an exception to the
      last row, though) ** THIS MIGHT NOT ALWAYS WORK! **
- NOTE: we can't use lastRow. according to their documentation, if the table had more
    rows that were deleted, it will also count them.

### !!! Important

WE must find the best place to close the com.hadaralex.kepper.file. We can't leave it opened.
 - maybe add a `keepOpen()` function to the builder?