function handleFormSubmit(event) {
    const x = parseFloat(document.querySelector('.menu').value);
    const y = parseFloat(document.querySelector('.input').value);
    const messages = document.querySelector('.error-messages');

    if (messages) {
        messages.innerHTML = '';
    }

    let isValid = true;
    let errorMessages = [];

    if (isNaN(x) || x < -2 || x > 2) {
        isValid = false;
        errorMessages.push('X должно быть выбрано из списка от -2 до 2!');
    }

    if (isNaN(y) || y < -5 || y > 5) {
        isValid = false;
        errorMessages.push('Y должно быть числом в диапазоне от -5 до 5!');
    }

    if (!isValid) {
        event.preventDefault();

        errorMessages.forEach(message => {
            const errorElement = document.createElement('p');
            errorElement.textContent = message;
            errorElement.style.color = 'red';
            messages.appendChild(errorElement);
        });
    }
}

document.querySelector('.btn-submit')
    .addEventListener('click', event => handleFormSubmit(event));
