document.addEventListener('DOMContentLoaded', () => {
    // Получаем все ссылки навигации
    const navLinks = document.querySelectorAll('.nav-link');

    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault(); // Отменяем стандартное поведение (прыжок)

            const targetId = this.getAttribute('href'); // Получаем ID целевого раздела
            const targetSection = document.querySelector(targetId);

            if (targetSection) {
                // Выполняем плавную прокрутку
                window.scrollTo({
                    top: targetSection.offsetTop - 80, // Вычитаем высоту шапки
                    behavior: 'smooth'
                });
            }
        });
    });
});
document.addEventListener('DOMContentLoaded', () => {
    // Плавный скролл (из предыдущего примера)
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const targetId = this.getAttribute('href');
            const targetSection = document.querySelector(targetId);
            if (targetSection) {
                window.scrollTo({
                    top: targetSection.offsetTop - 80,
                    behavior: 'smooth'
                });
            }
        });
    });

    // === Новая функция: Переключение тёмной/светлой темы ===
    const themeSwitch = document.createElement('div');
    themeSwitch.textContent = 'Тёмная тема';
    themeSwitch.classList.add('theme-switch');
    document.body.prepend(themeSwitch);

    themeSwitch.addEventListener('click', () => {
        document.body.classList.toggle('dark-mode');
        if (document.body.classList.contains('dark-mode')) {
            themeSwitch.textContent = 'Светлая тема';
            localStorage.setItem('theme', 'dark'); // Сохраняем выбор темы
        } else {
            themeSwitch.textContent = 'Тёмная тема';
            localStorage.setItem('theme', 'light');
        }
    });

    // Проверяем, есть ли сохранённая тема
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme === 'dark') {
        document.body.classList.add('dark-mode');
        themeSwitch.textContent = 'Светлая тема';
    }
});