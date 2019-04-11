# Multithreading-simple-banking-application-on-JAVA

Developing a thread-based Java Program, capable of executing on multiple cores, for the following scenario.

A company wishes to implement a simple accounting system that allows each of its 10 departments to perform the following transactions on its 50 internal accounts.

Deposit an amount into a named account.
Withdraw an amount from a named account.
Transfer an amount between named accounts.
The application must allow for the concurrent transfer between different pairs of accounts when possible. The application must be:
1. correct,
2. fair,
3. have no deadlock, and not have any individual thread "starved".

Your application should be able to handle at least 10,000 transactions efficiently.
