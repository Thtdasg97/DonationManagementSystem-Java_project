window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

        const datatablesSimple = document.getElementById('donation-table');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple, {
        perPage: 5});
    }
});
