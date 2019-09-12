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

Application should be able to handle at least 10,000 transactions efficiently.

# Design of application
I developed a multi-threading Java application which is capable of running on multiple core system. This application is capable of handling more than 10,000 concurrent transactions between it’s fifty internal accounts of ten departments. It is also possible to increase the number of departments/accounts.

# Design Highlights
1. Random Input: I used Random class of Util package to generate random inputs. Department Id, Account numbers, Amount and Transaction types are randomly selected for each iteration.

2. Persistent Data: MySQL is used to store the data.

3. Absence of Deadlock and Thread starvation: Synchronized methods are used to prevent deadlocks. At any point of time only one thread can access a synchronized method. After each transaction is performed, balance is updated in the database and the updated result will be visible to all other threads.

4. Correctness: Input is validated before performing any transactions. One such example is if we need to perform a transfer between to accounts, we need two account numbers. In this application as explained above account numbers are generated randomly, in a worst case scenario we might get same account numbers. Two account numbers are validated first to see if they are same. If so, account number will be regenerated until both are different. Many other screenshots which confirms the correctness of the application are submitted along with this report.

# Results
Please see the attcahed report for detailed results and test cases
