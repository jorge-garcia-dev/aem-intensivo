(function() {
    'use strict';

    // API del navegador --> detecta cuándo un elemento entra o sale del viewport
    const observer = new IntersectionObserver(function(entradas) { 
        entradas.forEach(function(entrada) {
            if (entrada.isIntersecting) {
                entrada.target.classList.add('is-visible');
                observer.unobserve(entrada.target);
            }
        });
    }, { threshold: 0.2 });

    document.querySelectorAll('.about__content, .about__image').forEach(function(i) {
        observer.observe(i);
    });

})();