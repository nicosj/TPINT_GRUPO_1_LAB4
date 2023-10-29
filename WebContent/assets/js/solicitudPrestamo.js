document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('loanForm');
    const acceptButton = document.getElementById('acceptButton');
    const modal = document.getElementById('myModal');
    const span = document.getElementsByClassName('close')[0];

    // When the user clicks on the "Aceptar" button in the form, open the modal
    acceptButton.onclick = function() {
        modal.style.display = 'block';
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = 'none';
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    }

    // Submit the form when the user clicks the "Aceptar" button in the modal
    document.getElementById('acceptButton').onclick = function() {
        form.submit();
    }

    // Close the modal when the user clicks the "Rechazar" button in the modal
    document.getElementById('rejectButton').onclick = function() {
        modal.style.display = 'none';
    }
});