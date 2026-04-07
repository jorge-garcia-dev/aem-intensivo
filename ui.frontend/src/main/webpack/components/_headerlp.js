(function() {
    'use strict';
    
    document.addEventListener('click', function(e) {
        const hamburger = e.target.closest('.header-lp__hamburger');
        
        if (hamburger) {
            const nav = document.querySelector('.header-lp__nav');
            const spans = hamburger.querySelectorAll('span');
            
            if (nav) {
                nav.classList.toggle('active');
                hamburger.classList.toggle('active');
            }
        }
    });
})();