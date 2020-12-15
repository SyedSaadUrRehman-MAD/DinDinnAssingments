# DinDinnAssignment
1.) Problem solving

Given a string str consisting of letters only and an integer n, the task is to replace every character of the given string by a character which is n times more than it. If the letter exceeds ‘z’, then start checking from ‘a’ in a cyclic manner.

Examples:
Input: str = “abc”, n = 2
Output: cde
a is moved by 2 times which results in character c
b is moved by 2 times which results in character d
c is moved by 2 times which results in character e

Input: str = “abc”, n= 28
Output: cde
a is moved 25 times, z is reached. Then the 26th character will be a, 27th b and 28th c.
b is moved 24 times, z is reached. 28-th is d.
c is moved 23 times, z is reached. 28-th is f.

Question: 
a/ Write an algorithm to solve the above issue. Please consider the complexity of the algorithm.
## contain the 26 small letters in an array A in alphabetical order
## take the input string from user that comprises with number of letters = 'n'
## take the input number step 'S' from the user by which the char in hand should move
## Check if the number of steps 'S' is greater than 26, if yes it means that the current digit with come again 'S'/26 times, Optimal steps to be moved will be 'S'%26
## if number of steps is less than 26, check if the index of the character in hand + S is greater than 26? if yes then calculate the moved index by indexOfCharInHand + S - 26
## if number of steps is less than 26 and index of character + S is less than 26, then calculate the moved index by indexOfCharInHand + S
## cap the input number to avoid number format exception when the input number is greater than maximum integer

The source code of main activity has the implementaion

b/ What is the disadvantage of using the ASCII value of the letters to solve this problem?
## This problem is equally solvable with ASCII value of the characters but it induces complexity, more traversal, capping character as we have total 127 ASCII values and capitals are different than small letters.
## The second problem i think is the number of bytes taken by an integer, however ascii is 7bit but the smallest container of data is CHAR(1byte), while dealing with ascii converting character to int which takes 4 bytes or short which take 2 bytes, takes more space than a character.

2.) Mini assignment

Please replicate the below UI and animation flow, and send us your final assignment using GitHub.

https://dribbble.com/shots/6571883-Food-Delivery-App

Requirements:
You do not need to use exact images, so please use other suitable images.
Write a networking layer to call the API and fetch data which you have to mock the data to display on the UI.
Use the following dependencies/libraries to implement:
Rxjava
Kotlin
MvRx architecture. (https://github.com/airbnb/MvRx)
