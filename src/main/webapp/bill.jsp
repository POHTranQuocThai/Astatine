<%-- 
    Document   : bill
    Created on : Feb 25, 2025, 9:55:00 PM
    Author     : Ma Tan Loc - CE181795
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/bill.css"/>
        <title>Astatine 04 | Invoice</title>
        <link rel="shortcut icon" type="image/png" href="assets/img/Tittle-web-icon/Logo_Dark.ico" />

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Icon New-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />
    </head>
    <body>
        <div class="container">
            <div class="header">
                <div class="brand">
                    <img src="https://storage.googleapis.com/a1aa/image/Obv0vEsxclWplkSU207pO1oqCUxBfbcr3skUI36XdgQ.jpg" alt="Brand logo">
                    <span>Brand</span>
                </div>
                <div class="invoice-info">
                    <p>Date</p>
                    <p class="date">April 26, 2023</p>
                    <p>Invoice #</p>
                    <p class="invoice-number">BRA-00335</p>
                </div>
            </div>
            <div class="company-info">
                <div class="supplier">
                    <p><strong>Supplier Company INC</strong></p>
                    <p>Number: 23456789</p>
                    <p>VAT: 23456789</p>
                    <p>6622 Abshire Mills</p>
                    <p>Port Orlofurt, 05820</p>
                    <p>United States</p>
                </div>
                <div class="customer">
                    <p><strong>Customer Company</strong></p>
                    <p>Number: 123456789</p>
                    <p>VAT: 23456789</p>
                    <p>9552 Vandervort Spurs</p>
                    <p>Paradise, 43325</p>
                    <p>United States</p>
                </div>
                <div style="clear: both;"></div>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Product details</th>
                        <th>Price</th>
                        <th>Qty.</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1.</td>
                        <td>Montly accounting services</td>
                        <td>$150.00</td>
                        <td>1</td>
                        <td>$150.00</td>
                    </tr>
                    <tr>
                        <td>2.</td>
                        <td>Taxation consulting (hour)</td>
                        <td>$60.00</td>
                        <td>2</td>
                        <td>$120.00</td>
                    </tr>
                    <tr>
                        <td>3.</td>
                        <td>Bookkeeping services</td>
                        <td>$50.00</td>
                        <td>1</td>
                        <td>$50.00</td>
                    </tr>
                </tbody>
            </table>
            <div class="totals">
                <p>Net total: <strong>$320.00</strong></p>
                <p>VAT total: <strong>$64.00</strong></p>
                <p class="total">Total: $384.00</p>
            </div>
            <div class="payment-details">
                <p><strong>PAYMENT DETAILS</strong></p>
                <p>Banks of Banks</p>
                <p>Bank/Sort Code: 1234567</p>
                <p>Account Number: 123456678</p>
                <p>Payment Reference: BRA-00335</p>
            </div>
            <div class="notes">
                <p><strong>Notes</strong></p>
                <p>Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.</p>
            </div>
            <div class="footer">
                <p>Supplier Company | info@company.com | +1-202-555-0106</p>
            </div>
        </div>
    </body>
</html>
