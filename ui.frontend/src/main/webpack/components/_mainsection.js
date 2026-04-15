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

    // Animamos las columnas principales y las cards de contacto
    const elementsToAnimate = document.querySelectorAll('.contact-main__info, .contact-main__form-wrapper, .contact-card');
    elementsToAnimate.forEach(function(el) {
        el.classList.add('reveal-on-scroll'); // Aseguramos que tengan la clase base
        observer.observe(el);
    });

    // --- 2. LÓGICA DE FORMULARIO (Validación y Envío) ---
    const form = document.getElementById('contactForm');
    if (!form) return; // Fail-safe por si el componente no está en la página

    const submitBtn = document.getElementById('submitBtn');
    const charCounter = document.getElementById('currentChars');
    
    const fields = {
        name: { el: document.getElementById('name'), reg: /^[a-zA-ZÀ-ÿ\s]{3,}$/ },
        email: { el: document.getElementById('email'), reg: /^[^\s@]+@[^\s@]+\.[^\s@]+$/ },
        phone: { el: document.getElementById('phone'), reg: /^[0-9\s]*$/ },
        message: { 
            el: document.getElementById('message'), 
            reg: /^[a-zA-ZÀ-ÿ0-9\s.,¿?¡!]+$/, 
            min: 20, 
            max: 300 
        }
    };

    const validateForm = () => {
        const isNameValid = fields.name.reg.test(fields.name.el.value.trim());
        const isEmailValid = fields.email.reg.test(fields.email.el.value.trim());
        const isPhoneValid = fields.phone.reg.test(fields.phone.el.value.trim());
        
        // Validación del mensaje: Longitud + Sanitización estricta
        const msgValue = fields.message.el.value;
        const msgLength = msgValue.trim().length;
        const isMsgSanitized = fields.message.reg.test(msgValue);
        const isMsgValid = msgLength >= fields.message.min && msgLength <= fields.message.max && isMsgSanitized;

        if (charCounter) {
            charCounter.textContent = msgLength;
            // Feedback visual: si mete un caracter prohibido, el contador se pone rojo
            charCounter.style.color = isMsgSanitized ? "#666" : "#dc3545";
        }

        submitBtn.disabled = !(isNameValid && isEmailValid && isPhoneValid && isMsgValid);
    };

    // Listeners de validación
    Object.keys(fields).forEach(function(key) {
        const field = fields[key];
        
        field.el.addEventListener('input', validateForm);

        field.el.addEventListener('blur', function() {
            const group = field.el.closest('.form-group');
            // Si el campo no es válido y no está vacío, mostramos error
            const isValid = (key === 'message') 
                ? (field.el.value.trim().length >= field.min) 
                : field.reg.test(field.el.value.trim());

            if (!isValid && field.el.value !== "") {
                group.classList.add('has-error');
            } else {
                group.classList.remove('has-error');
            }
        });
    });

    // Evento de Envío (Consola y Alerta según requerimiento)
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const data = {
            name: fields.name.el.value,
            email: fields.email.el.value,
            message: fields.message.el.value
        };

        console.log('Formulario enviado:', data);
        alert('¡Formulario enviado con éxito!');
        
        form.reset();
        validateForm();
    });

})();