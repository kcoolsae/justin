/* Implements the 'other' functionality for radio buttons and checkboxes

   If a radio button or checkbox has a data-other attribute, then this refers to the id of an
    input field that should be shown when the radio button or checkbox is selected.

    TODO: simply use the next input field in the DOM as the 'other' field, rather than requiring an id
 */
document.addEventListener("DOMContentLoaded", function () {

    const radioButtons = document.querySelectorAll('input[type="radio"][data-other]');
    const checkboxes = document.querySelectorAll('input[type="checkbox"][data-other]');

    radioButtons.forEach(radio => {
        // event listener must be registered with *all* radio buttons in the group as 'change'
        // event is only triggered when the radio button is selected, not when it is deselected
        const other = document.getElementById(radio.dataset.other);
        const sameGroupButtons = document.querySelectorAll(`input[name="${radio.name}"]`); // interpolation with back ticks!
        sameGroupButtons.forEach(button => {
            button.addEventListener('change', () => {
                other.disabled = !radio.checked;
            });
        });
    });

    checkboxes.forEach(checkbox => {
        const other = document.getElementById(checkbox.dataset.other);
        checkbox.addEventListener('input', () => {
            other.disabled = !checkbox.checked;
        });
    });

});

