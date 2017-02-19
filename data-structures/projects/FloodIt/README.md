Analysis:
f(n) = n^2 roughly fits the graph. It is definitely a polynomial graph.

For my flood function, I created an iterator to iterate through the Map.Entry objects
of the color_of_tiles map. I checked to see if the entry's key (a Coord object) was inbounds and if the
entry's color was equal to color argument passed into the function. If both of those conditions are met,
I check to see if one of the coordinates to the left, up, down, or right of the entry key exist in
the flooded_list. If any adjacent coordinates did exist in the flood list, then I added the
map entry to the flooded list.

Since my function is already a time complexity of O(n^2), I was helping other students
in the lab and I noticed individuals who had working games, but their graphs
were 10x slower. This is likely because they were adding double-nested for loops that
checked for duplicates in the flooded_list, adding |flooded_list| * |color_of_tiles|.
