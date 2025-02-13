// countdown.js

// Set the date we're counting down to
var countDownDate = new Date("Feb 13, 2025 00:00:00").getTime();

// Update the count down every 1 second
var x = setInterval(function () {

    // Get today's date and time
    var now = new Date().getTime();

    // Find the distance between now and the count down date
    var distance = countDownDate - now;

    // Time calculations for days, hours, minutes, and seconds
    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

    // Output the result in the respective li elements
    document.querySelector(".countdown-item:nth-child(1) h3").innerHTML = days;
    document.querySelector(".countdown-item:nth-child(2) h3").innerHTML = hours;
    document.querySelector(".countdown-item:nth-child(3) h3").innerHTML = minutes;
    document.querySelector(".countdown-item:nth-child(4) h3").innerHTML = seconds;

    // If the count down is over, display "EXPIRED" in the countdown
    if (distance < 0) {
        clearInterval(x);
        document.querySelector(".countdown-item:nth-child(1) h3").innerHTML = "00";
        document.querySelector(".countdown-item:nth-child(2) h3").innerHTML = "00";
        document.querySelector(".countdown-item:nth-child(3) h3").innerHTML = "00";
        document.querySelector(".countdown-item:nth-child(4) h3").innerHTML = "00";
        document.getElementById("demo").innerHTML = "EXPIRED";
    }
}, 1000);
