/* Shows an extra div only when a checkbox is checked */

document.addEventListener("DOMContentLoaded", function () {

    const checkboxes = document.querySelectorAll('input[type="checkbox"][data-js]');
    checkboxes.forEach(checkbox => {
        const paragraph = document.getElementById(checkbox.dataset.js);
        paragraph.hidden = !checkbox.checked;
        checkbox.addEventListener('input', () => {
            paragraph.hidden = !checkbox.checked;
        });
    });

});