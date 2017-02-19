# Project Overview

My solution for this project passes all of the test cases successfully. I have tried to detail what my code is doing thoroughly in my project.

I have two findPaths functions - one that properly passes all test cases, and one that passes all but wire5 and wire8.

What I essentially did for this project was:

1. Wrote bfs function to create map of parents

2. Wrote a function to backtrack from end coord to start coord

3. For every path(wire) I laid on the chip, I marked it as an "obstacle" in the board so that the bfs
function would avoid/not use that cell as a path since it is already being used by another wire.

4. 1-3 worked great for all test cases except two of them. To rectify this, I sorted all of the given points by their distance from src to sink, and laid the shortest wires first.

# Testing my Code

To test my code, please be sure to run ```Driver batch_test``` from Routing_student root directory. I made a few small edits to the  Driver class to fix up the file folder.

# Hours Spent

I spent roughly 7.5 hours on this assignment.
