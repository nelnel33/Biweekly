# My Biweekly Implementation of a topic of interest. 
courtesy of Jon Topielski. lol, thanks for the idea.

Because this is my "blog" I decided to write as unprofessionally as possible. 
Please excuse my grammar, spelling, and any other errors you might stumble across reading my "implementation of sorts".
----
Week 1, Day 1 - A*, eyy star
What is A*? 
It is a pathfinding algorithmn designed to traverse through a graph based on weighed edges. 
It doesn't just find a path, it finds a specific path from point start to point destination
It is one of the faster pathfinding algorithmns(compared to depth and breadth traversal) and is generally used in game design. 
Now I would tell you the pros and cons but I'm still not too clear on those details.

What about my code? 
My code takes in an NxM matrix and a start and destination point.
and outputs a linked list containing all of the matrix coordinates contraining the best traversable path.
Which could represent a map of some sort. 
example input:
start: (0,2); dest: (3,3)
[0,0,1,0]
[0,1,1,1]
[0,1,0,1]
[0,0,0,1]

example output:
[0,2],[1,2],[1,3],[2,3],[3,3].

If say the input was the same matrix as above but the destination or start points were not valid then it would simply return null because there is no valid path to traverse. e.g: start: (0,2); dest(0,0)
----
Week 1, Day 2 - are en gee?
I was watching a video on youtube the other day about Calculating Pi with Darts.
The concept is that if you have a square of length l and a circle with a diameter of length l 
then you placed it so that the circle is entirely in the square and you generate a bunch of random points on this circle/square combo
you should be able to calculate pi by counting up all the randomly generated points in the circle and the total, taking the ratio
of the circle and the total and then multiplying it by 4.

What was she doing? 
She set up a square board and drew a circle on it with the same diameter as the square's edge. 
They then proceded to "randomly" throw darts at it, counting up the total number of holes in the circle and total then calculating pi.
It was surprising accurate, so I decided to give it a try.

If you still don't understand what I'm saying, feel free to just run the file. ALL WILL BE EXPLAINED.
or watch the video: https://www.youtube.com/watch?v=M34TO71SKGk, ALL WILL BE EXPLAINED.
or to be honest, I don't care what you do.
ALL WILL BE EXPLAINED.
----

