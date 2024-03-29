# Trapping Rain Water Problem

## The Problem

An element of array can store water if there are higher bars on left and right. 
We can find amount of water to be stored in every element by finding the heights 
of bars on left and right sides. The idea is to compute amount of water that can
be stored in every element of array. For example, consider the array
`[3, 0, 0, 2, 0, 4]`, We can trap "3*2 units" of water between 3 an 2, "1 unit" 
on top of bar 2 and "3 units" between 2 and 4. See below diagram also.

The concept is illustrated as shown:

![DP Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/Figures/42/trapping_rain_water.png)

# Recursive Staircase Problem

## The Problem

There are `n` stairs, a person standing at the bottom wants to reach the top. The person can climb either `1` or `2` stairs at a time. _Count the number of ways, the person can reach the top._

![](https://cdncontribute.geeksforgeeks.org/wp-content/uploads/nth-stair.png)
