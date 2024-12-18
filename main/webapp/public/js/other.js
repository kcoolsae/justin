/*
 * other.js
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

/* Provides handling of radio buttons and checkboxes with 'Other…'. */

document.addEventListener("DOMContentLoaded", function () {
    const others = document.querySelectorAll('.justin-other');
    others.forEach(other => {
        const otherText = other.querySelector('input[type="text"]');

        /* in case of radio buttons */
        const radioButtons = other.querySelectorAll('input[type="radio"]');
        if (radioButtons.length > 0) {
            const otherRadio = radioButtons[radioButtons.length - 1]
            otherText.disabled = !otherRadio.checked;

            radioButtons.forEach(button => {
                button.addEventListener('change', () => {
                    otherText.disabled = !otherRadio.checked;
                });
            });
        }

        /* in case of checkboxes */
        const checkBox = other.querySelector('input[type="checkbox"]');
        if (checkBox != null) {
            otherText.disabled = !checkBox.checked;

            checkBox.addEventListener('change', () => {
                otherText.disabled = !checkBox.checked;
            });
        }

    });
});
