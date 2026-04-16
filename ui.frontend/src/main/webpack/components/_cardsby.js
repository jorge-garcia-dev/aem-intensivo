
document.addEventListener('DOMContentLoaded', () => {
    new Swiper('.cardsby__swiper', {
        slidesPerView: 1,
        spaceBetween: 20,
        centeredSlides: false,
        loop: false,
        pagination: {
            el: '.cardsby__swiper .swiper-pagination',
            clickable: true,
        },
        navigation: {
            nextEl: '.cardsby__swiper .swiper-button-next',
            prevEl: '.cardsby__swiper .swiper-button-prev',
        },
    });
});