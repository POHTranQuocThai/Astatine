/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// Sidebar and Modal Toggling
const toggleButton = document.getElementById('toggle-btn');
const sidebar = document.getElementById('sidebar'); // Ensure sidebar is defined

function toggleSidebar() {
    sidebar.classList.toggle('close');
    toggleButton.classList.toggle('rotate');
    closeAllSubMenus();
}

function toggleSubMenu(button) {
    if (!button.nextElementSibling.classList.contains('show')) {
        closeAllSubMenus();
    }

    button.nextElementSibling.classList.toggle('show');
    button.classList.toggle('rotate');

    if (sidebar.classList.contains('close')) {
        sidebar.classList.toggle('close');
        toggleButton.classList.toggle('rotate');
    }
}

function closeAllSubMenus() {
    Array.from(sidebar.getElementsByClassName('show')).forEach(ul => {
        ul.classList.remove('show');
        ul.previousElementSibling.classList.remove('rotate');
    });
}

function closeModal() {
    const modal = document.querySelector('.modal-container');
    if (modal) modal.classList.remove('show');
}

// Table Search and Sorting
const search = document.querySelector('.input-group input');
const table_rows = document.querySelectorAll('tbody tr');
const table_headings = document.querySelectorAll('thead th');

// 1. Searching for specific data in the HTML table
search.addEventListener('input', searchTable);

function searchTable() {
    table_rows.forEach((row, i) => {
        const table_data = row.textContent.toLowerCase();
        const search_data = search.value.toLowerCase();
        row.classList.toggle('hide', table_data.indexOf(search_data) < 0);
        row.style.setProperty('--delay', i / 25 + 's');
    });

    document.querySelectorAll('tbody tr:not(.hide)').forEach((visible_row, i) => {
        visible_row.style.backgroundColor = (i % 2 == 0) ? 'transparent' : '#0000000b';
    });
}

// 2. Sorting / Ordering data of the HTML table
table_headings.forEach((head, i) => {
    let sort_asc = true;
    head.onclick = () => {
        table_headings.forEach(head => head.classList.remove('active'));
        head.classList.add('active');

        document.querySelectorAll('td').forEach(td => td.classList.remove('active'));
        table_rows.forEach(row => {
            row.querySelectorAll('td')[i].classList.add('active');
        });

        head.classList.toggle('asc', sort_asc);
        sort_asc = head.classList.contains('asc') ? false : true;

        sortTable(i, sort_asc);
    }
});

function sortTable(column, sort_asc) {
    const sortedRows = Array.from(table_rows).sort((a, b) => {
        let firstValue = a.querySelectorAll('td')[column].textContent.trim();
        let secondValue = b.querySelectorAll('td')[column].textContent.trim();

        const isNumeric = !isNaN(firstValue) && !isNaN(secondValue);
        if (isNumeric) {
            firstValue = parseFloat(firstValue);
            secondValue = parseFloat(secondValue);
            return sort_asc ? firstValue - secondValue : secondValue - firstValue;
        } else {
            return sort_asc 
                ? firstValue.localeCompare(secondValue)
                : secondValue.localeCompare(firstValue);
        }
    });

    const tbody = document.querySelector('tbody');
    tbody.innerHTML = "";
    sortedRows.forEach(row => tbody.appendChild(row));
}
