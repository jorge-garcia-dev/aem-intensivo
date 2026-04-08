(function() {
    'use strict';

    const observer = new IntersectionObserver(function(entradas) {
        entradas.forEach(function(entrada) {
            if (entrada.isIntersecting) {
                entrada.target.classList.add('is-visible');
                observer.unobserve(entrada.target);
            }
        });
    }, { threshold: 0.15 });

    document.querySelectorAll('.valores__card').forEach(function(card) {
        observer.observe(card);
    });

})();