For Project in Master Branch

1. Get Investor Deatils
url : http://localhost:8080/invest/myDetails
JSON : {
        "investorId": "K2"
      }
2. Create Withdrawal Notice
url : http://localhost:8080/invest/withrawalNotice
JSON : {
    "productId": "P3",
    "investorId": "K2",
    "accountNumber": "SR54667465489",
    "bankName": "Bank of World",
    "withdrawalAmount": 3565656565
      }
3. Download Statement
url :http://localhost:8080/invest/downloadStatement
JSON : {
    "investorId":"K2",
    "productId":"P3",
    "fromDate":"2023-11-19",
    "toDate":"2023-12-21"
      }
