$(document).ready(function () {
    // Initialize the carousel
    const carousel = $('#carouselExampleIndicators');
    carousel.carousel({
        interval: 3000 // Set your interval
    });

    // Dragging logic
    let isDragging = false;
    let startX;
    let currentX;
    let dragThreshold = 50; // Minimum drag distance to trigger the next/previous slide

    $('.carousel-inner').on('mousedown touchstart', function (e) {
        isDragging = true;
        startX = e.pageX || e.originalEvent.touches[0].pageX; // Use pageX for mouse, touches[0].pageX for touch
        $(this).css('cursor', 'grabbing'); // Change cursor style on drag
    });

    $(document).on('mousemove touchmove', function (e) {
        if (!isDragging) return;
        e.preventDefault();

        currentX = e.pageX || e.originalEvent.touches[0].pageX;

        const distance = startX - currentX;

        if (Math.abs(distance) > dragThreshold) {
            if (distance > 0) {
                carousel.carousel('next'); // Move to the next item
            } else {
                carousel.carousel('prev'); // Move to the previous item
            }
            isDragging = false; // Reset dragging
        }
    });

    $(document).on('mouseup touchend', function () {
        isDragging = false; // Reset dragging
        $('.carousel-inner').css('cursor', 'grab'); // Reset cursor style
    });
});
