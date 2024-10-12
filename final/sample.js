let currentIndex = 0;

function toggleDarkMode() {
    const body = document.body;
    body.classList.toggle('dark-mode');
    const toggle = document.getElementById('dark-mode-toggle');
    toggle.checked = body.classList.contains('dark-mode');
}

function openModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.style.display = "flex";
    const scrollableBox = modal.querySelector('.scrollable-box');
    if (scrollableBox) {
        scrollableBox.style.display = "block";  // Ensure it's visible
    }
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = "none";
}

window.onclick = function(event) {
    if (event.target.classList.contains('modal')) {
        closeModal(event.target.id);
    }
};

setTimeout(() => {
    document.querySelector('.fullscreen-text').style.display = 'none';
}, 5000);
