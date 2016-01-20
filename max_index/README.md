You are given a list of N numbers a1,a2,…,an. For each element at position i (1≤i≤N), we define Left(i) and Right(i) as:
Left(i) = closest index j such that j < i and aj>ai. If no such j exists then Left(i) = 0.
Right(i) = closest index k such that k > i and ak>ai. If no such k exists then Right(i) = 0.

We define IndexProduct(i) = Left(i) * Right(i). You need to find out the maximum IndexProduct(i) among all i.

Input Format

The first line contains an integer N, the number of integers. The next line contains the N integers describing the list a[1..N].

Constraints

1≤N≤105
1≤ai≤109

Output Format

Output the maximum IndexProduct among all indices from 1 to N.

Sample Input

5
5 4 3 4 5

Sample Output

8

Explanation

We can compute the following:

IndexProduct(1)=0
IndexProduct(2)=1×5=5
IndexProduct(3)=2×4=8
IndexProduct(4)=1×5=5
IndexProduct(5)=0

The largest of these is 8, so it is the answer.
